package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.normproduct.param.AttrDefResponse;
import com.ai.slp.product.api.normproduct.param.NormProductAttrValResponse;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 商城商品详情中对象<br>
 *
 *
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class ProductInfo extends BaseInfo {
    /**
     * 商品标识
     */
    private String productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品类型
     */
    private String productType;
    /**
     * 生效时间
     */
    private Date activeTime;
    /**
     * 失效时间
     */
    private Date inactiveTime;
    /**
     * 有效周期
     */
    private Integer activeCycle;
    /**
     * 周期
     */
    private String unit;

    /**
     * 关键属性集合,包括属性对应标准品属性值集合
     */
    private Map<AttrDefResponse,List<NormProductAttrValResponse>> keyAttrs;

    /**
     * 销售属性集合,包括属性对应标准品属性值集合
     */
    private Map<AttrDefResponse,List<NormProductAttrValResponse>> saleAttrs;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public Date getInactiveTime() {
        return inactiveTime;
    }

    public void setInactiveTime(Date inactiveTime) {
        this.inactiveTime = inactiveTime;
    }

    public Integer getActiveCycle() {
        return activeCycle;
    }

    public void setActiveCycle(Integer activeCycle) {
        this.activeCycle = activeCycle;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Map<AttrDefResponse, List<NormProductAttrValResponse>> getKeyAttrs() {
        return keyAttrs;
    }

    public void setKeyAttrs(Map<AttrDefResponse, List<NormProductAttrValResponse>> keyAttrs) {
        this.keyAttrs = keyAttrs;
    }

    public Map<AttrDefResponse, List<NormProductAttrValResponse>> getSaleAttrs() {
        return saleAttrs;
    }

    public void setSaleAttrs(Map<AttrDefResponse, List<NormProductAttrValResponse>> saleAttrs) {
        this.saleAttrs = saleAttrs;
    }
}
