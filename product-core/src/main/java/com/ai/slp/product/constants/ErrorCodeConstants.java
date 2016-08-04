package com.ai.slp.product.constants;

/**
 * Created by jackieliu on 16/6/14.
 */
public final class ErrorCodeConstants {
    /**
     * 租户标识为空
     */
    public static final String TENANT_ID_NULL = "1000";
    /**
     * 销售商（商户）标识为空
     */
    public static final String SUPPLIER_ID_NULL = "2000";

    public final class ProductCat{
        /**
         * 类目不存在
         */
        public static final String CAT_NO_EXIST = "1006";
    }

    public final class Product{
        /**
         * sku不存在,或无效
         */
        public static final String SKU_NO_EXIST = "1001";
        /**
         * 销售商品不存在
         */
        public static final String PRODUCT_NO_EXIST = "1002";
    }

    public final class Storage{
        /**
         * 库存不足
         */
        public static final String UNDER_STOCK = "1003";
        /**
         * 价格不符合
         */
        public static final String PRICE_UN_MATCH = "1004";
    }

    public final class ProdAudiences{
        /**
         * 受众不符合
         */
        public static final String UNMATCHED = "1005";
    }
    
}
