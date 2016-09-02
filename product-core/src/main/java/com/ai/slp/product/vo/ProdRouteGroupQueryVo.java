package com.ai.slp.product.vo;

/**
 * 销售商品和路由组查询信息
 * Created by jackieliu on 16/9/2.
 */
public class ProdRouteGroupQueryVo {
    /**
     * 租户Id，必填
     */
    private String tenantId;
    /**
     * 请求查询的页码,默认为1
     */
    private Integer pageNo = 1;

    /**
     * 每页显示条数,默认每页10条
     */
    private Integer pageSize =10;
    /**
     * 商城商品标识
     */
    private String prodId;
    /**
     * 商品名称
     */
    private String prodName;
    /**
     * 标准品ID
     */
    private String standedProdId;
    /**
     * 标准品名称
     */
    private String standedProdName;
    /**
     * 配货组标识
     */
    private String routeGroupId;

    protected Integer limitStart;

    protected Integer limitEnd;
    /**
     * 排序信息
     */
    private String orderByClause;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getStandedProdId() {
        return standedProdId;
    }

    public void setStandedProdId(String standedProdId) {
        this.standedProdId = standedProdId;
    }

    public String getStandedProdName() {
        return standedProdName;
    }

    public void setStandedProdName(String standedProdName) {
        this.standedProdName = standedProdName;
    }

    public String getRouteGroupId() {
        return routeGroupId;
    }

    public void setRouteGroupId(String routeGroupId) {
        this.routeGroupId = routeGroupId;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart = limitStart;
    }

    public Integer getLimitEnd() {
        return limitEnd;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd = limitEnd;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }
}
