package com.ai.slp.product.api.prodsupply.param;

import com.ai.opt.base.vo.BaseInfo;

import java.util.Date;

/**
 * 根据标准品查询的供应商品信息<br>
 *
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class ProdSupplyForNorm extends BaseInfo {
    /**
     * 供应商品标识
     */
    private String supplyId;
    /**
     * 供应商品名称
     */
    private String supplyName;
    /**
     * 商品类目标识
     */
    private String productCatId;
    /**
     * 商品类目名称
     */
    private String productCatName;
    /**
     * 成本价
     */
    private Long costPrice;
    /**
     * 操作时间
     */
    private Date operTime;
    /**
     * 路由标识
     */
    private String routeId;
    /**
     * 路由名称
     */
    private String routeName;
    /**
     * 供应商ID
     */
    private Long sellerId;
    /**
     * 供应商名称
     */
    private String sellerName;
    /**
     * 标准品类型
     * 0:全部;1实物;2虚拟
     */
    private String productType;
    /**
     * 商品可用量
     */
    private Long usableNum;

    public String getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(String supplyId) {
        this.supplyId = supplyId;
    }

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

    public String getProductCatName() {
        return productCatName;
    }

    public void setProductCatName(String productCatName) {
        this.productCatName = productCatName;
    }

    public Long getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Long costPrice) {
        this.costPrice = costPrice;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Long getUsableNum() {
        return usableNum;
    }

    public void setUsableNum(Long usableNum) {
        this.usableNum = usableNum;
    }
}
