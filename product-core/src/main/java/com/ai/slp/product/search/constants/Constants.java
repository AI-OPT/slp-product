package com.ai.slp.product.search.constants;

/**
 * Created by xin on 16-5-25.
 */
public class Constants {
    public static final String SearchNameSpace = "com.ai.slp.search.productinfo";

    public static class QUERY_SQL {
        public static final String MAIN_SQL = "SELECT product.BASIC_ORG_ID as basicOrgId, product.UP_TIME AS UP_TIME, product.RECHARGE_TYPE AS rechageType, product.IS_SALE_NATIONWIDE as SaleNationwide,product.TENANT_ID as tenantid, sku.SKU_ID as skuid ,sku.SKU_NAME as skuname,product.PROD_ID as producetid,product.PRODUCT_SELL_POINT as productsellpoint,product.PROD_NAME as producetname,product.PRODUCT_CAT_ID as productCatId FROM prod_sku sku INNER JOIN product product ON sku.PROD_ID = product.PROD_ID AND product.STATE = '5' AND product.PROD_ID = ?";
        public static final String FETCH_ATTR_INFO_SQL = "SELECT standed_prod_attr.ATTR_ID as attrID, standed_prod_attr.ATTR_VALUE_NAME as attrValue from product inner join standed_prod_attr on product.STANDED_PROD_ID = standed_prod_attr.STANDED_PROD_ID where product.PROD_ID = ?";
        public static final String FETCH_CATEGORY_INFO_SQL = "select PRODUCT_CAT_ID,PRODUCT_CAT_NAME,PARENT_PRODUCT_CAT_ID from product_cat a where a.PRODUCT_CAT_ID = ? ";
        public static final String FETCH_SALE_NUMBER_SQL = "select  SALE_NUM  from PROD_SALE_ALL where PROD_ID = ?";
        public static final String FETCH_IMAGE_FROM_SKU_SQL = "SELECT  PIC_TYPE, VFS_ID FROM PROD_CAT_ATTR catAttr, PROD_SKU_ATTR skuAttr, PROD_PICTURE picture WHERE catAttr.ATTR_ID = skuAttr.ATTR_ID AND skuAttr.ATTRVALUE_DEF_ID = picture.ATTRVALUE_DEF_ID AND catAttr.PRODUCT_CAT_ID = ? AND catAttr.ATTR_TYPE = '2' AND catAttr.IS_PICTURE = 'Y' AND skuAttr.SKU_ID = ? AND picture.IS_MAIN_PIC = 'Y'";
        public static final String FETCH_IMAGE_FROM_PROD_SQL = "SELECT PIC_TYPE, VFS_ID,IS_MAIN_PIC FROM PROD_PICTURE WHERE  PROD_ID = ?";
        public static final String FETCH_PRICE_SQL = "SELECT  skuStor.SALE_PRICE as SALE_PRICE FROM sku_storage skuStor, storage WHERE skuStor.STORAGE_ID = storage.STORAGE_ID AND skuStor.SKU_ID = ? AND storage.PROD_ID = ? AND skuStor.STATE = '1' AND storage.STATE LIKE '1%'";
        public static final String FETCH_AUDIENCE_SQL = "SELECT USER_TYPE,USER_ID FROM prod_audiences where PROD_ID = ? AND STATE = '1'";
        public static final String FETCH_SEAL_AREA = "select PROV_CODE from PROD_TARGET_AREA WHERE PROD_ID = ? AND STATE = '1' ";
    }
}
