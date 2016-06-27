package com.ai.slp.product.service.business.interfaces.search;

public interface ISKUIndexManage {

    boolean updateSKUIndex(String productId);

    boolean deleteSKUIndexBySKUId(String skuId);

    boolean deleteSKUIndexByProductId(String productId);
}
