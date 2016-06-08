package com.ai.slp.product.api.webfront.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.PageInfo;

public class ProductQueryRequest extends BaseInfo {

    private static final long serialVersionUID = 1L;

    /**
     * 商品类目标识
     */
    private String productCatId;

    /**
     * 面额属性值ID
     */
    private String attrDefId;

    /**
     * 基础运营商
     */
    private String basicOrgIdIs;

    /**
     * 所在地区，必填
     */
    private String areaCode;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * skuID
     */
    private String skuId;

    /**
     * 卖点
     */
    private String productSellPoint;

    /**
     * 是否按销售量排序
     */
    private String saleNumOrderFlag;

    /**
     * 是否按价格排序
     */
    private String priceOrderFlag;

    /**
     * 配货地区
     */
    private String distributionArea;

    private PageInfo<ProductData> pageInfo;

    public PageInfo<ProductData> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<ProductData> pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

    public String getAttrDefId() {
        return attrDefId;
    }

    public void setAttrDefId(String attrDefId) {
        this.attrDefId = attrDefId;
    }

    public String getBasicOrgIdIs() {
        return basicOrgIdIs;
    }

    public void setBasicOrgIdIs(String basicOrgIdIs) {
        this.basicOrgIdIs = basicOrgIdIs;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getProductSellPoint() {
        return productSellPoint;
    }

    public void setProductSellPoint(String productSellPoint) {
        this.productSellPoint = productSellPoint;
    }

    public String getSaleNumOrderFlag() {
        return saleNumOrderFlag;
    }

    public void setSaleNumOrderFlag(String saleNumOrderFlag) {
        this.saleNumOrderFlag = saleNumOrderFlag;
    }

    public String getPriceOrderFlag() {
        return priceOrderFlag;
    }

    public void setPriceOrderFlag(String priceOrderFlag) {
        this.priceOrderFlag = priceOrderFlag;
    }

    public String getDistributionArea() {
        return distributionArea;
    }

    public void setDistributionArea(String distributionArea) {
        this.distributionArea = distributionArea;
    }

}
