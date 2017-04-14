package com.ai.slp.product.service.business.interfaces;

import com.ai.slp.product.api.storageserver.param.StorageNumRes;
import com.ai.slp.product.api.storageserver.param.StorageNumUseReq;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.vo.SkuStorageVo;

import java.util.Map;

/**
 * SKU库存组操作
 * Created by jackieliu on 16/5/25.
 */
public interface IStorageNumBusiSV {
    /**
     * 使用库存量
     * @param tenantId 租户ID
     * @param skuId 单品标识ID
     * @param skuNum 单品数量
     * @return
     */
    public StorageNumRes userStorageNum(String tenantId,String skuId,int skuNum,Long price);

    /**
     * 使用库存量,包含商品受众检查
     * @param useReq 使用信息
     * @return
     */
    public StorageNumRes userNumWithAudiAndPrice(StorageNumUseReq useReq);

    /**
     * 回退库存量
     * @param tenantId 租户id
     * @param skuId 单品标识
     * @param storageNum 库存回退集合
     */
    public void backStorageNum(String tenantId,String skuId,Map<String,Integer> storageNum);

    /**
     * 查询SKU的库存和价格信息
     * @param tenantId
     * @param skuId
     * @return
     */
    public SkuStorageVo queryStorageOfSku(String tenantId,String skuId);
    
    /**
     * 查询SKU的库存和价格信息  专为订单使用
     * @param tenantId
     * @param skuId
     * @return
     */
    public SkuStorageVo queryStorageOfSku4ShopCart(String skuId,Product product);
    
    /**
     * 查询SKU的库存和价格信息
     * 
     * @param skuId
     * @param product
     * @return
     * @author Gavin
     * @UCUSER
     */
    public SkuStorageVo queryStorage(String skuId, Product product);

    /**
     * 查询当前库存组的可用量
     * @param tenantId
     * @param groupId
     * @return
     */
    public Long queryNowUsableNumOfGroup(String tenantId,String groupId);
}
