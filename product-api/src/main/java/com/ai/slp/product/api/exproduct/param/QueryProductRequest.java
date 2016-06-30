package com.ai.slp.product.api.exproduct.param;

import com.ai.opt.base.vo.BaseInfo;

public class QueryProductRequest extends BaseInfo{

    private static final long serialVersionUID = 1L;
    /**
     * 用户ID，必填
     */
    private String userId;
    
    /**
     * 用户类型，必填
     */
    private String userType;
   
    /**
     * 商品类目，必填
     */
    private String productCatId;
    /**
     * 充值类型
     */
    private String rechargeType;
    /**
     * 获取商品范围类型
     */
    private String prodRangeType;
    /**
     * 分页大小，必填
     */
    private Integer pageSize;
    /**
     * 页数，必填
     */
    private Integer pageNo;
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    public String getProductCatId() {
        return productCatId;
    }
    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }
    public String getRechargeType() {
        return rechargeType;
    }
    public void setRechargeType(String rechargeType) {
        this.rechargeType = rechargeType;
    }
    public String getProdRangeType() {
        return prodRangeType;
    }
    public void setProdRangeType(String prodRangeType) {
        this.prodRangeType = prodRangeType;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getPageNo() {
        return pageNo;
    }
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    } 

}
