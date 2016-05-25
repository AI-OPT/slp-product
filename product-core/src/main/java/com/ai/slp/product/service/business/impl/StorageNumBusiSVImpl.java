package com.ai.slp.product.service.business.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.slp.product.api.product.param.SkuInfo;
import com.ai.slp.product.api.storageserver.param.StorageNumRes;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.bo.product.ProdSku;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.service.atom.impl.product.ProdSkuAtomSVImpl;
import com.ai.slp.product.service.atom.impl.product.ProductAtomSVImpl;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.business.interfaces.IStorageNumBusiSV;
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
        //查询商品是否为"在售"状态
        Product product = productAtomSV.selectByProductId(tenantId,skuInfo.getProdId());
        if (product==null || ProductConstants.Product.State.IN_SALE.equals(product.getState())){
            logger.warn("销售商品不存在,或已下架,租户ID:{},SKU标识:{},SKU数量:{},销售商品标识{}"
                    ,tenantId,skuId,skuNum,skuInfo.getProdId());
            throw new BusinessException("","销售商品不存在,或已下架状态");
        }

        //查询销售商品所属库存组
        //查询库存组是否为"启用"状态
        //查询是否在促销期内

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
