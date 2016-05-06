package com.ai.slp.product.service.business.interfaces;

import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;

import java.sql.Timestamp;

/**
 * 商城商品业务操作
 * Created by jackieliu on 16/5/5.
 */
public interface IProductBusiSV {

    /**
     * 添加商城商品
     * @param group
     * @return
     */
    public int addProductWithStorageGroup(StorageGroup group, Long operId);
}
