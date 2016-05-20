package com.ai.slp.product.util;


import com.ai.opt.sdk.components.sequence.util.SeqUtil;

public final class SequenceUtil {

    /**
     * 属性定义标识序列
     */
    private static final String PROD_ATTR_DEF$ATTR_ID$SEQ = "PROD_ATTR_DEF$ATTR_ID$SEQ";
    private static final String PRODUCT$PROD_ID$SEQ = "PRODUCT$PROD_ID$SEQ";
    private static final String PRODUCT_LOG$LOG_ID$SEQ = "PRODUCT_LOG$LOG_ID$SEQ";
    private static final String STORAGE_GROUP$STORAGE_GROUP_ID$SEQ = "STORAGE_GROUP$STORAGE_GROUP_ID$SEQ";
    private static final String STORAGE_GROUP_LOG$LOG_ID$SEQ = "STORAGE_GROUP_LOG$LOG_ID$SEQ";
    private static final String STORAGE$STORAGE_ID$SEQ = "STORAGE$STORAGE_ID$SEQ";
    private static final String STORAGE_LOG$LOG_ID$SEQ = "STORAGE_LOG$LOG_ID$SEQ";
    private static final String WARN_RECEIVE_STAFF$WARN_RECEIVE_STAFF_ID$SEQ = "WARN_RECEIVE_STAFF$WARN_RECEIVE_STAFF_ID$SEQ";
    private static final String PROD_ATTRVALUE_DEF$ATTRVALUE_DEF_ID$SEQ = "PROD_ATTRVALUE_DEF$ATTRVALUE_DEF_ID$SEQ";
    private static final String PROD_CAT_ATTR$CAT_ATTR_ID$SEQ = "PROD_CAT_ATTR$CAT_ATTR_ID$SEQ";
    private static final String PROD_CAT_ATTR_VALUE$CAT_ATTR_VALUE_ID$SEQ = "PROD_CAT_ATTR_VALUE$CAT_ATTR_VALUE_ID$SEQ";
    private static final String PRODUCT_CAT$PRODUCT_CAT_ID$SEQ = "PRODUCT_CAT$PRODUCT_CAT_ID$SEQ";
    private static final String PROD_PRICE_LOG$LOG_ID$SEQ = "PROD_PRICE_LOG$LOG_ID$SEQ";
    private static final String STANDED_PROD_ATTR$STANDED_PROD_ATTR_ID$SEQ = "STANDED_PROD_ATTR$STANDED_PROD_ATTR_ID$SEQ";
    private static final String STANDED_PROD_ATTR_LOG$LOG_ID$SEQ = "STANDED_PROD_ATTR_LOG$LOG_ID$SEQ";
    private static final String STANDED_PRODUCT$STANDED_PROD_ID$SEQ = "STANDED_PRODUCT$STANDED_PROD_ID$SEQ";
    private static final String STANDED_PRODUCT_LOG$LOG_ID$SEQ = "STANDED_PRODUCT_LOG$LOG_ID$SEQ";
    private static final String PROD_SKU$SKU_ID$SEQ = "PROD_SKU$SKU_ID$SEQ";
    private static final String PROD_SKU_LOG$LOG_ID$SEQ = "PROD_SKU_LOG$LOG_ID$SEQ";
    private static final String PROD_SKU_ATTR$SKU_ATTR_ID$SEQ = "PROD_SKU_ATTR$SKU_ATTR_ID$SEQ";
    private static final String SKU_STORAGE$SKU_STORAGE_ID$SEQ = "SKU_STORAGE$SKU_STORAGE_ID$SEQ";

    public static Long createAttrDefId() {
        return SeqUtil.getNewId(PROD_ATTR_DEF$ATTR_ID$SEQ);
    }

    public static String createProductProdId(){
        return SeqUtil.getNewId(PRODUCT$PROD_ID$SEQ,16);
    }

    public static String createProductLogId(){
        return SeqUtil.getNewId(PRODUCT_LOG$LOG_ID$SEQ,16);
    }

    public static String genStorageGroupId(){
        return SeqUtil.getNewId(STORAGE_GROUP$STORAGE_GROUP_ID$SEQ,13);
    }

    public static String genStorageGroupLogId(){
        return SeqUtil.getNewId(STORAGE_GROUP_LOG$LOG_ID$SEQ,16);
    }

    public static String genStorageId(){
    	return SeqUtil.getNewId(STORAGE$STORAGE_ID$SEQ, 18);
    }
    public static String genStorageLogId(){
        return SeqUtil.getNewId(STORAGE_LOG$LOG_ID$SEQ,16);
    }

    public static String genWarnReceiveStaffId(){
        return SeqUtil.getNewId(WARN_RECEIVE_STAFF$WARN_RECEIVE_STAFF_ID$SEQ,16);
    }

    public static String genProdAttrvalueDefId(){
        return SeqUtil.getNewId(PROD_ATTRVALUE_DEF$ATTRVALUE_DEF_ID$SEQ,12);
    }

    public static String genProdCatAttrId(){
        return SeqUtil.getNewId(PROD_CAT_ATTR$CAT_ATTR_ID$SEQ,12);
    }

    public static String genProdCatAttrValId(){
        return SeqUtil.getNewId(PROD_CAT_ATTR_VALUE$CAT_ATTR_VALUE_ID$SEQ,12);
    }

    public static String genProductCatId(){
        return SeqUtil.getNewId(PRODUCT_CAT$PRODUCT_CAT_ID$SEQ,14);
    }

    public static String genProdPriceLogId(){
        return SeqUtil.getNewId(PROD_PRICE_LOG$LOG_ID$SEQ,16);
    }

    public static Long genStandedProdAttrId(){
        return SeqUtil.getNewId(STANDED_PROD_ATTR$STANDED_PROD_ATTR_ID$SEQ);
    }

    public static String genStandedProdAttrLogId(){
        return SeqUtil.getNewId(STANDED_PROD_ATTR_LOG$LOG_ID$SEQ,16);
    }

    public static String genStandedProductId(){
        return SeqUtil.getNewId(STANDED_PRODUCT$STANDED_PROD_ID$SEQ,16);
    }

    public static String genStandedProductLogId(){
        return SeqUtil.getNewId(STANDED_PRODUCT_LOG$LOG_ID$SEQ,16);
    }

    public static String genSkuLogId(){
        return SeqUtil.getNewId(PROD_SKU_LOG$LOG_ID$SEQ,16);
    }

    public static Long genSkuAttrId(){
        return SeqUtil.getNewId(PROD_SKU_ATTR$SKU_ATTR_ID$SEQ);
    }

    public static String genProdSkuId(){
        return SeqUtil.getNewId(PROD_SKU$SKU_ID$SEQ,16);
    }

	public static String genskuStorageId() {
		return SeqUtil.getNewId(SKU_STORAGE$SKU_STORAGE_ID$SEQ,19);
	}
}
