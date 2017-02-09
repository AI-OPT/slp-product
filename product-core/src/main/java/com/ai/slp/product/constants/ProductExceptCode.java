package com.ai.slp.product.constants;

/**
 * 错误返回编码 
 *
 */
public class ProductExceptCode {

    public final static class ErrorCode {
        private ErrorCode() {
        }
        //10001值空
        public static final String PARAM_NULL_ERROR = "10001";
        //   10002值错误     
        public static final String PARAM_VALUE_ERROR = "10002";
        //10003电话错误
        public static final String PHONE_NOTONE_ERROR = "10003";
        //10004邮箱错误
        public static final String EMAIL_NOTONE_ERROR = "10004";
    }
    public static final String SUCCESS_INFO = "查询成功";
    
}
