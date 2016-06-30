package com.ai.slp.product.service.business.interfaces.exsearch;

public interface IExSKUIndexManage {

    boolean updateSKUIndex(String productId);

    boolean deleteSKUIndexBySKUId(String skuId);

    boolean deleteSKUIndexByProductId(String productId);
}
