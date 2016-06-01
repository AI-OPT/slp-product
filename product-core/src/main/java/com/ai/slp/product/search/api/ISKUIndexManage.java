package com.ai.slp.product.search.api;

public interface ISKUIndexManage {

    boolean updateSKUIndex(String productId);

    boolean deleteProductIndex(String skuId);
}
