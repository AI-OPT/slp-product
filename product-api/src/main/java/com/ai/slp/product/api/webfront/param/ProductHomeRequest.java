package com.ai.slp.product.api.webfront.param;

import com.ai.opt.base.vo.BaseInfo;

public class ProductHomeRequest extends BaseInfo{

    private static final long serialVersionUID = 1L;
    /**
     * 商品类目
     */
    private String productCatId;
    /**
     * 属性名称
     */
    private String productAttrName;
    /**
     * 属性值
     */
    private String productAttrValue;
    /**
     * 运营商
     */
    private String basicOrgIdIs;
    /**
     * 所属地区，必填
     */
    private String areaCode;
    /**
     * 用户类型
     */
    private String usertype;
    /**
     * 用户ID
     */
    private String userid;
    /**
     * 属性值定义ID
     */
    private String attrValueDefID;
    
    public String getProductCatId() {
        return productCatId;
    }
    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }
    public String getProductAttrName() {
        return productAttrName;
    }
    public void setProductAttrName(String productAttrName) {
        this.productAttrName = productAttrName;
    }
    public String getAreaCode() {
        return areaCode;
    }
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    public String getProductAttrValue() {
        return productAttrValue;
    }
    public void setProductAttrValue(String productAttrValue) {
        this.productAttrValue = productAttrValue;
    }
    public String getUsertype() {
        return usertype;
    }
    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getBasicOrgIdIs() {
        return basicOrgIdIs;
    }
    public void setBasicOrgIdIs(String basicOrgIdIs) {
        this.basicOrgIdIs = basicOrgIdIs;
    }
    public String getAttrValueDefID() {
        return attrValueDefID;
    }
    public void setAttrValueDefID(String attrValueDefID) {
        this.attrValueDefID = attrValueDefID;
    } 

}
