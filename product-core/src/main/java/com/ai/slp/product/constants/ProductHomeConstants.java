package com.ai.slp.product.constants;

public class ProductHomeConstants {
    //首页查询最大条数限制
    public static final int MAX_SIZE = 8;
    //热门搜索查询最大条数限制
    public static final int HOT_MAX_SIZE = 5;
    //热点查询排序字段
    public static final String ORDER_SALE_NUM_NAME = "salenum";
   //按价格查询排序字段
    public static final String ORDER_PRICE_NAME = "price";
    //话费充值类目ID
    public static final String PHONE_BILL_PRO_CAT_ID = "10000010010000";
    //流量充值类目ID
    public static final String FLOW_PRO_CAT_ID = "10000010020000";
    //降序排序
    public static final String ORDER_DESC_ID = "DESC";
    //升序排序
    public static final String ORDER_ASC_ID = "ASC";
    //充值方式
    public static final String FILL_TYPE = "D";
    //全国销售代码
    public static final String TYPENATION_WIDE = "Y";
    
    
    public static final class UserType{
        /**
         * 企业
         */
        public static final String ENTERPRISE = "11";
        /**
         * 个人 
         */
        public static final String PERSONAL = "10";
        /**
         * 代理
         */
        public static final String AGENCY = "12";
        /**
         * 供应商
         */
        public static final String SUPPLY = "13";
       
        
    }
}
