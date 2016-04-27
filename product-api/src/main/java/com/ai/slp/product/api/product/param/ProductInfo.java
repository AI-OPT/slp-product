package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseInfo;

import java.util.Date;
import java.util.Map;
import java.util.Set;

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
     * 商品买点
     */
    private String sellPoint;
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
     * 上架类型
     */
    private String upshelfType;
    /**
     * 上架时间,若上架类型为定时上架时,才有效
     */
    private Date upTime;
    /**
     * 是否全国范围销售
     */
    private String isSaleNationwide;

    /**
     * 属性与属性值对应关系
     */
    private Map<Long,Set<Long>> attrAndValueIds;

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

    public Map<Long, Set<Long>> getAttrAndValueIds() {
        return attrAndValueIds;
    }

    public void setAttrAndValueIds(Map<Long, Set<Long>> attrAndValueIds) {
        this.attrAndValueIds = attrAndValueIds;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public String getUpshelfType() {
        return upshelfType;
    }

    public void setUpshelfType(String upshelfType) {
        this.upshelfType = upshelfType;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public String getIsSaleNationwide() {
        return isSaleNationwide;
    }

    public void setIsSaleNationwide(String isSaleNationwide) {
        this.isSaleNationwide = isSaleNationwide;
    }
}
