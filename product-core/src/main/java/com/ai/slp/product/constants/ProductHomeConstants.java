package com.ai.slp.product.constants;

public class ProductHomeConstants {
    //首页查询最大条数限制
    public static final int MAX_SIZE = 8;
    //热门搜索查询最大条数限制
    public static final int HOT_MAX_SIZE = 5;
    //热点查询排序字段
    public static final String ORDER_FILE_NAME = "salenum";
    //话费充值类目ID
    public static final String PHONE_BILL_PRO_CAT_ID = "10000010010000";
    //流量充值类目ID
    public static final String FLOW_PRO_CAT_ID = "10000010020000";
    
    public final class UserType{
        /**
         * 企业
         */
        public static final String ENTERPRISE = "ENTERPRISE";
        /**
         * 个人 
         */
        public static final String PERSONAL = "PERSONAL";
        /**
         * 代理
         */
        public static final String AGENCY = "AGENCY";
        
    }
}
