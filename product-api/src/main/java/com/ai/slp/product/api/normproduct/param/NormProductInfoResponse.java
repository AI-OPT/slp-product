package com.ai.slp.product.api.normproduct.param;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 标准品详情信息<br>
 *
 * Date: 2016年4月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class NormProductInfoResponse extends NormProductBase {

    /**
     * 创建者名称
     */
    private String createName;

    /**
     * 操作人
     */
    private Long operId;
    /**
     * 市场价
     */
    private Long marketPrice;

    /**
     * 操作人名称
     */
    private String operName;

    /**
     * 操作时间
     */
    private Date operTime;
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
     * 单位
     */
    private String unit;
    /**
     * 属性与属性值对应关系
     */
    private Map<Long,Set<Long>> attrAndValueIds;

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
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

    public Long getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Long marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public Map<Long, Set<Long>> getAttrAndValueIds() {
        return attrAndValueIds;
    }

    public void setAttrAndValueIds(Map<Long, Set<Long>> attrAndValueIds) {
        this.attrAndValueIds = attrAndValueIds;
    }
}
