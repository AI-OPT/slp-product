package com.ai.slp.product.service.business.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.slp.product.api.storageserver.param.StorageNumRes;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.bo.product.ProdSku;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.business.interfaces.IStorageNumBusiSV;
import com.ai.slp.product.util.IPassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by jackieliu on 16/5/25.
 */
@Service
@Transactional
public class StorageNumBusiSVImpl implements IStorageNumBusiSV {
    private static Logger logger = LoggerFactory.getLogger(StorageNumBusiSVImpl.class);
    @Autowired
    IProdSkuAtomSV skuAtomSV;
    @Autowired
    IProductAtomSV productAtomSV;
    @Autowired
    IStorageGroupAtomSV storageGroupAtomSV;
    /**
     * 使用库存量
     *
     * @param tenantId 租户ID
     * @param skuId    单品标识ID
     * @param skuNum   单品数量
     * @return
     */
    @Override
    public StorageNumRes userStorageNum(String tenantId, String skuId, int skuNum) {
        //查询SKU所属销售商品
        ProdSku skuInfo = skuAtomSV.querySkuById(tenantId,skuId);
        if (skuInfo==null){
            logger.warn("单品信息不存在,租户ID:{},SKU标识:{},SKU数量:{}",tenantId,skuId,skuNum);
            throw new BusinessException("","单品信息不存在,单品标识:"+skuId);
        }
        //1.查询商品是否为"在售"状态
        Product product = productAtomSV.selectByProductId(tenantId,skuInfo.getProdId());
        if (product==null || ProductConstants.Product.State.IN_SALE.equals(product.getState())){
            logger.warn("销售商品不存在,或已下架,租户ID:{},SKU标识:{},SKU数量:{},销售商品标识{}"
                    ,tenantId,skuId,skuNum,skuInfo.getProdId());
            throw new BusinessException("","销售商品不存在,或已下架状态");
        }
        String groupId = product.getStorageGroupId();
        //获取缓存客户端
        ICacheClient cacheClient = MCSClientFactory.getCacheClient(StorageConstants.IPass.McsParams.STORAGE_MCS);
        //2.检查库存组状态是否为"启用"
        //获取库存组的cacheKey
        String groupKey = IPassUtils.genMcsStorageGroupKey(tenantId,groupId);
        //获取当前库存组状态
        String groupState = cacheClient.hget(groupKey,StorageConstants.IPass.McsParams.GROUP_STATE_HTAGE);
        //若库存组不是启用状态,则不允许使用
        if (!StorageConstants.StorageGroup.State.ACTIVE.equals(groupState)
                && !StorageConstants.StorageGroup.State.AUTO_ACTIVE.equals(groupState)){
            logger.warn("库存组没有启用,无法使用,租户ID:{},库存组ID:{}"
                    ,tenantId,groupId);
            throw new BusinessException("","库存组没有启用,无法使用");
        }
        //3.确认当前使用优先级
        //3.1确认当前是否在促销期内
        String serialsKey = IPassUtils.genMcsGroupSerialStartTimeKey(tenantId,groupId);

        //确认优先级
        String serial = "";
        //查询优先级的价格
        String priceKey = IPassUtils.genMcsGroupSerialPriceKey(tenantId,groupId,serial);
        //若不存在促销优先级价格,则使用库存组指定优先级
        if (!cacheClient.exists(priceKey)){
            serial = cacheClient.hget(groupKey,StorageConstants.IPass.McsParams.GROUP_SERIAL_HTAGE);
            priceKey = IPassUtils.genMcsGroupSerialPriceKey(tenantId,groupId,serial);
            //判断库存组当前优先级可用量是否满足
            String groupUsable = cacheClient.hget(groupKey,StorageConstants.IPass.McsParams.GROUP_USABLE_HTAGE);
            if (skuNum>Long.parseLong(groupUsable)){
                logger.warn("库存组没有启用,无法使用,租户ID:{},库存组ID:{},库存优先级{},库存可用量{}"
                        ,tenantId,groupId,serial,groupUsable);
                throw new BusinessException("","该商品库存不足");
            }
        }
        //4.获取当前优先级中SKU的销售价
        long salePrice = Long.parseLong(cacheClient.hget(priceKey,skuId));
        //库存是否充足
        String usableNumKey = IPassUtils.genMcsGroupSerialUsableKey(tenantId,groupId,serial);
        //若减少库存之后,剩余库存小于零,表示库存不足
        if (cacheClient.hincrBy(usableNumKey,skuId,-skuNum)<0){
            logger.warn("该商品库存不足,租户ID:{},库存组ID:{}"
                    ,tenantId,groupId);
            throw new BusinessException("","该商品库存不足");
        }

        return null;
    }

    /**
     * 回退库存量
     *
     * @param tenantId   租户id
     * @param skuId      单品标识
     * @param storageNum 库存回退集合
     */
    @Override
    public void backStorageNum(String tenantId, String skuId, Map<String, Integer> storageNum) {

    }
}
