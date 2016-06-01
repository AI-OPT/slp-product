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
     * 属性值ID
     */
    private String attrDefId;

    /**
     * 基础运营商
     */
    private String basicOrgIdIs;

    /**
     * 所在地区
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

}
