package com.ai.slp.product.service.business.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.slp.product.api.storageserver.param.StorageNumRes;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.bo.product.ProdSku;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.storage.SkuStorage;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.ISkuStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.business.interfaces.IStorageNumBusiSV;
import com.ai.slp.product.util.IPassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Iterator;
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
    @Autowired
    IStorageAtomSV storageAtomSV;
    @Autowired
    ISkuStorageAtomSV skuStorageAtomSV;
    @Autowired
    StorageNumDbBusiSVImpl numDbBusiSV;
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
        //1. 查询商品是否为"在售"状态
        Product product = productAtomSV.selectByProductId(tenantId,skuInfo.getProdId());
        if (product==null || ProductConstants.Product.State.IN_SALE.equals(product.getState())){
            logger.warn("销售商品不存在,或已下架,租户ID:{},SKU标识:{},SKU数量:{},销售商品标识{}"
                    ,tenantId,skuId,skuNum,skuInfo.getProdId());
            throw new BusinessException("","销售商品不存在,或已下架状态");
        }
        String groupId = product.getStorageGroupId();
        //获取缓存客户端
        ICacheClient cacheClient = MCSClientFactory.getCacheClient(StorageConstants.IPass.McsParams.STORAGE_MCS);
        //2. 检查库存组状态是否为"启用"
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

        //3. 确认当前使用优先级
        //3.1 确认当前是否在促销期内
        String serialsKey = IPassUtils.genMcsGroupSerialStartTimeKey(tenantId,groupId);
        long nowTime = System.currentTimeMillis();
        //TODO... 确认是否在促销内 ZREVRANGEBYSCORE nowTime 0
        //使用优先级
        String serial = "";
        //优先级价格对应KEY
        String priceKey = IPassUtils.genMcsGroupSerialPriceKey(tenantId,groupId,serial);
        //优先级中库存可用量对应KEY
        String priorityUsable = IPassUtils.genMcsPriorityUsableKey(tenantId,groupId,serial);
        /*3.2 以下情况使用正常优先级
         *  A.促销价格不存在,则表明促销已过期;
         *  B.促销优先级库存可用量不存在,则表明促销已过期
         *  C.促销优先级库存可用量小于1,则表明促销商品已售完,切换正常优先级.
         */
        if (!cacheClient.exists(priceKey)
                || !cacheClient.exists(priorityUsable)
                || Long.parseLong(cacheClient.get(priorityUsable))<1){
            //使用库存组指定优先级
            serial = cacheClient.hget(groupKey,StorageConstants.IPass.McsParams.GROUP_SERIAL_HTAGE);
            priceKey = IPassUtils.genMcsGroupSerialPriceKey(tenantId,groupId,serial);
            //库存组当前优先级库存可用量
            priorityUsable = IPassUtils.genMcsPriorityUsableKey(tenantId,groupId,serial);
            //若促销价格不存在,表明促销已过期,删除当前优先级的促销时间
            if (!cacheClient.exists(priceKey)) {
                //TODO...删除促销期的优先级时间 ZREM serialsKey serial
            }
        }

        //4.获取当前优先级中SKU的销售价
        long salePrice = Long.parseLong(cacheClient.hget(priceKey,skuId));
        //5.进行减sku库存
        String usableNumKey = IPassUtils.genMcsSerialSkuUsableKey(tenantId,groupId,serial);
        //若减少库存之后,剩余库存小于零,表示库存不足
        if (cacheClient.hincrBy(usableNumKey,skuId,-skuNum)<0){
            logger.warn("该商品库存不足,租户ID:{},库存组ID:{}"
                    ,tenantId,groupId);
            throw new BusinessException("","该商品库存不足");
        }
        //6.进行减少优先级库存可用量
        cacheClient.decrBy(priorityUsable,skuNum);
        //7.确认库存量的库存来源
        String skuStoragekey = IPassUtils.genMcsSkuStorageUsableKey(tenantId,groupId,serial,skuId);
        //8.组装返回值
        StorageNumRes numRes = new StorageNumRes();
        BeanUtils.copyProperties(numRes,product);
        numRes.setSkuId(skuId);
        numRes.setSkuName(skuInfo.getSkuName());
        numRes.setSalePrice(salePrice);
        numRes.setStorageNum(getSkuNumSource(cacheClient,skuStoragekey,skuNum));
        //变更数据库信息
        numDbBusiSV.userStorageNum(numRes.getStorageNum(),true);
        return numRes;
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
        //检查SKU是否存在
        Iterator<String> iterator = storageNum.keySet().iterator();
        ICacheClient cacheClient = MCSClientFactory.getCacheClient(StorageConstants.IPass.McsParams.STORAGE_MCS);
        while (iterator.hasNext()){
            String skuStorageId = iterator.next();
            Integer skuNum = storageNum.get(skuStorageId);
            //1. 根据SKU库存查询所属优先级
            //1.1 查询SKU库存信息
            SkuStorage skuStorage = skuStorageAtomSV.queryById(skuStorageId,true);
            if (skuStorage==null){
                logger.warn("库存回退过程中,未找到对应SKU库存,SKU库存标识:{}",skuStorageId);
                continue;
            }
            //1.2 查询SKU库存对应库存信息
            Storage storage = storageAtomSV.queryById(skuStorage.getStorageId());
            if (storage==null){
                logger.warn("库存回退过程中,未找到对应库存,SKU库存标识:{},库存标识:{}"
                        ,skuStorageId,skuStorage.getStorageId());
                continue;
            }
            String groupId = storage.getStorageGroupId(),serial = storage.getSerialNumber().toString();
            //2. 回退缓存中库存所用量
            //2.1 回退优先级中,SKU可用量
            String skuUsableKey = IPassUtils.genMcsSerialSkuUsableKey(tenantId,groupId,serial);
            if (cacheClient.exists(skuUsableKey)){
                cacheClient.hincrBy(skuUsableKey,skuId,skuNum);
            }
            //2.2 回退优先级中,SKU库存可用量
            String skuStorageKey = IPassUtils.genMcsSkuStorageUsableKey(tenantId,groupId,serial,skuId);
            if (cacheClient.exists(skuStorageKey)){
                //TODO... 返回SKU库存的库存量,增加权重
            }
            String priorityUsable = IPassUtils.genMcsPriorityUsableKey(tenantId,groupId,serial);
            //2.3 回退优先级中库存可用量
            if (cacheClient.exists(priorityUsable)){
                cacheClient.incrBy(priorityUsable,skuNum);
            }
        }
        //调用数据库异步处理方法
        numDbBusiSV.userStorageNum(storageNum,false);
    }

    /**
     * 获知商品使用量的SKU库存来源
     *
     * @param cacheClient
     * @param cacheKey sku库存缓存KEY
     * @param skuNum 单品数量
     * @return
     */
    private Map<String,Integer> getSkuNumSource(ICacheClient cacheClient,String cacheKey,int skuNum){
        Map<String,Integer> skuNumMap = new HashMap<>();
        //查询库存量大于零的 ZRANGE 1  +inf

        //修改库存 ZINCRBY -skuNum
        return skuNumMap;
    }
}
