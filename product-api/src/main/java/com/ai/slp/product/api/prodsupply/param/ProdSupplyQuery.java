package com.ai.slp.product.api.prodsupply.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.prodsupply.interfaces.IProdSupplySV;

import javax.validation.constraints.NotNull;

/**
 * 供应商品查询对象<br>
 *
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class ProdSupplyQuery extends BaseInfo {
    /**
     * 路由标识
     */
    @NotNull(message = "路由标识不能为空",groups = {IProdSupplySV.QueryProdSupplyOfRoute.class})
    private String routeId;
    /**
     * 供应商品id
     */
    private String supplyId;
    /**
     * 标准品id
     */
    @NotNull(message = "标准品标识不能为空",groups = {IProdSupplySV.QueryProdSupplyOfNorm.class})
    private String standedProdId;
    /**
     * 商品名称
     */
    private String supplyName;
    /**
     * 类目标识
     */
    private String catId;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(String supplyId) {
        this.supplyId = supplyId;
    }

    public String getStandedProdId() {
        return standedProdId;
    }

    public void setStandedProdId(String standedProdId) {
        this.standedProdId = standedProdId;
    }

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }
}
