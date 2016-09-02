package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseInfo;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 配货组信息查询
 *
 * Date: 2016年9月2日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author liutong5
 */
public class RouteGroupQuery extends BaseInfo{
    private static final long serialVersionUID = 1L;
    /**
     * 商户标识,必填
     * -1:自营
     */
    @NotBlank(message = "商户标识不能为空")
    private String supplierId;
    /**
     * 请求查询的页码
     * 默认为1
     */
    private Integer pageNo = 1;

    /**
     * 每页显示条数,默认每页10条
     */
    private Integer pageSize =10;
    /**
     * 商品名称
     */
    private String prodName;
    /**
     * 商品ID
     */
    private String prodId;
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

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
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

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
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
}
