package com.ai.slp.product.search.api;

public interface ISKUIndexManage {

    boolean updateSKUIndex(String productId);

    boolean deleteSKUIndexBySKUId(String skuId);

    boolean deleteSKUIndexByProductId(String productId);
}
