package com.ai.slp.product.util;

import com.ai.slp.product.dao.mapper.bo.ProductCat;
import org.junit.Test;

/**
 * Created by jackieliu on 16/7/22.
 */
public class IPaasCatUtilsTest {

    @Test
    public void printCacheKey() {
        String tenantId = "SLP";
        ProductCat cat = new ProductCat();
        cat.setParentProductCatId("10000010000000");
        System.out.println(IPaasCatUtils.genMcsCatInfoKey(tenantId));
        System.out.println(IPaasCatUtils.genMcsCatLevelKey(tenantId));
        System.out.println(IPaasCatUtils.genMcsCatChildKey(tenantId, cat.getParentProductCatId()));
        System.out.println(IPaasCatUtils.genMcsCatParentKey(tenantId));
    }

}
