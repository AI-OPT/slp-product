package com.ai.slp.product.util;


import com.ai.opt.sdk.components.sequence.util.SeqUtil;

/**
 * 定义属性标识序列的工具类 
 *
 */

public final class SequenceUtil {
    private SequenceUtil() {
    }

    /**
     * 属性定义标识序列
     */
    //属性标识
    private static final String PROD_ATTR_DEF$ATTR_ID$SEQ = "PROD_ATTR_DEF$ATTR_ID$SEQ";
    //商品标识
    private static final String PRODUCT$PROD_ID$SEQ = "PRODUCT$PROD_ID$SEQ";
    //商品日志标识
    private static final String PRODUCT_LOG$LOG_ID$SEQ = "PRODUCT_LOG$LOG_ID$SEQ";
    //库存组标识
    private static final String STORAGE_GROUP$STORAGE_GROUP_ID$SEQ = "STORAGE_GROUP$STORAGE_GROUP_ID$SEQ";
    //库存组日志标识
    private static final String STORAGE_GROUP_LOG$LOG_ID$SEQ = "STORAGE_GROUP_LOG$LOG_ID$SEQ";
    //库存标识
    private static final String STORAGE$STORAGE_ID$SEQ = "STORAGE$STORAGE_ID$SEQ";
    //库存日志标识
    private static final String STORAGE_LOG$LOG_ID$SEQ = "STORAGE_LOG$LOG_ID$SEQ";
    //预警标识
    private static final String WARN_RECEIVE_STAFF$WARN_RECEIVE_STAFF_ID$SEQ = "WARN_RECEIVE_STAFF$WARN_RECEIVE_STAFF_ID$SEQ";
    //属性值标识
    private static final String PROD_ATTRVALUE_DEF$ATTRVALUE_DEF_ID$SEQ = "PROD_ATTRVALUE_DEF$ATTRVALUE_DEF_ID$SEQ";
    //类目属性标识
    private static final String PROD_CAT_ATTR$CAT_ATTR_ID$SEQ = "PROD_CAT_ATTR$CAT_ATTR_ID$SEQ";
    //类目属性值标识
    private static final String PROD_CAT_ATTR_VALUE$CAT_ATTR_VALUE_ID$SEQ = "PROD_CAT_ATTR_VALUE$CAT_ATTR_VALUE_ID$SEQ";
    //类目标识
    private static final String PRODUCT_CAT$PRODUCT_CAT_ID$SEQ = "PRODUCT_CAT$PRODUCT_CAT_ID$SEQ";
    //商品价格日志标识
    private static final String PROD_PRICE_LOG$LOG_ID$SEQ = "PROD_PRICE_LOG$LOG_ID$SEQ";
    //标准品属性标识
    private static final String STANDED_PROD_ATTR$STANDED_PROD_ATTR_ID$SEQ = "STANDED_PROD_ATTR$STANDED_PROD_ATTR_ID$SEQ";
    //标准品属性日志标识
    private static final String STANDED_PROD_ATTR_LOG$LOG_ID$SEQ = "STANDED_PROD_ATTR_LOG$LOG_ID$SEQ";
    //标准品标识
    private static final String STANDED_PRODUCT$STANDED_PROD_ID$SEQ = "STANDED_PRODUCT$STANDED_PROD_ID$SEQ";
    //标准品日志标识
    private static final String STANDED_PRODUCT_LOG$LOG_ID$SEQ = "STANDED_PRODUCT_LOG$LOG_ID$SEQ";
    //sku标识
    private static final String PROD_SKU$SKU_ID$SEQ = "PROD_SKU$SKU_ID$SEQ";
    //sku日志标识
    private static final String PROD_SKU_LOG$LOG_ID$SEQ = "PROD_SKU_LOG$LOG_ID$SEQ";
    //sku属性标识
    private static final String PROD_SKU_ATTR$SKU_ATTR_ID$SEQ = "PROD_SKU_ATTR$SKU_ATTR_ID$SEQ";
    //sku库存标识
    private static final String SKU_STORAGE$SKU_STORAGE_ID$SEQ = "SKU_STORAGE$SKU_STORAGE_ID$SEQ";
    //商品销售总表标识
    private static final String PROD_SALE_ALL$PRO_SALE_ID$SEQ = "PROD_SALE_ALL$PRO_SALE_ID$SEQ";
    //商品受众标识
    private static final String PROD_AUDIENCES$PROD_AUDIENCES_ID$SEQ = "PROD_AUDIENCES$PROD_AUDIENCES_ID$SEQ";
    //商品目标地域标识
    private static final String PROD_TARGET_AREA$TARGET_AREA_ID$SEQ = "PROD_TARGET_AREA$TARGET_AREA_ID$SEQ";
    //商品图片标识
    private static final String PROD_PICTURE$PRO_PICTURE_ID$SEQ = "PROD_PICTURE$PRO_PICTURE_ID$SEQ";
    //商品图片日志标识
    private static final String PROD_PICTURE_LOG$LOG_ID$SEQ = "PROD_PICTURE_LOG$LOG_ID$SEQ";
    //商品属性标识
    private static final String PROD_ATTR$PROD_ATTR_ID$SEQ = "PROD_ATTR$PROD_ATTR_ID$SEQ";
    //商品属性日志标识
    private static final String PROD_ATTR_LOG$LOG_ID$SEQ = "PROD_ATTR_LOG$LOG_ID$SEQ";
    //商品状态日志标识
    private static final String PRODUCT_STATE_LOG$LOG_ID$SEQ = "PRODUCT_STATE_LOG$LOG_ID$SEQ";
    
    //商品评论日志标识
    private static final String PROD_COMMENT$COMMENT_ID$SEQ = "PROD_COMMENT$COMMENT_ID$SEQ";
    //商品评论图片标识
    private static final String PROD_COMMENT_PICTURE$PROD_COMMENT_PIC_ID$SEQ = "PROD_COMMENT_PICTURE$PROD_COMMENT_PIC_ID$SEQ";
    //商品回复评论标识
    private static final String PROD_COMMENT_REPLY$REPLY_ID$SEQ = "PROD_COMMENT_REPLY$REPLY_ID$SEQ";
    
    
    public static Long prodCommentReplyDefId() {
    	return SeqUtil.getNewId(PROD_COMMENT_REPLY$REPLY_ID$SEQ);
    }
    public static Long createProdCommentDefId() {
        return SeqUtil.getNewId(PROD_COMMENT$COMMENT_ID$SEQ);
    }
    
    public static Long createProdCommentPictureDefId() {
        return SeqUtil.getNewId(PROD_COMMENT_PICTURE$PROD_COMMENT_PIC_ID$SEQ);
    }
    
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

    public static Long genProdSaleAllId(){
        return SeqUtil.getNewId(PROD_SALE_ALL$PRO_SALE_ID$SEQ);
    }

    public static Long genProdAudiencesId(){
        return SeqUtil.getNewId(PROD_AUDIENCES$PROD_AUDIENCES_ID$SEQ);
    }

    public static Long genProdTargetAreaId() {
        return SeqUtil.getNewId(PROD_TARGET_AREA$TARGET_AREA_ID$SEQ);
    }

    public static Long genProdPictureId(){
        return SeqUtil.getNewId(PROD_PICTURE$PRO_PICTURE_ID$SEQ);
    }

    public static String genProdPictureLogId(){
        return SeqUtil.getNewId(PROD_PICTURE_LOG$LOG_ID$SEQ,16);
    }

    public static Long genProdAttrId(){
        return SeqUtil.getNewId(PROD_ATTR$PROD_ATTR_ID$SEQ);
    }

    public static String genProdAttrLogId(){
        return SeqUtil.getNewId(PROD_ATTR_LOG$LOG_ID$SEQ,16);
    }

    public static String genProductStateLogId(){
    	return SeqUtil.getNewId(PRODUCT_STATE_LOG$LOG_ID$SEQ, 16);
    }
    
    public static String genProdCommentId(){
        return SeqUtil.getNewId(STANDED_PRODUCT$STANDED_PROD_ID$SEQ,16);
    }
}
