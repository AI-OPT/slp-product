package com.ai.slp.product.api.normproduct.param;

import com.ai.slp.product.api.normproduct.interfaces.INormProductSV;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 标准品将保存信息<br>
 *
 * Date: 2016年4月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class SaveProductInfoRequest extends NormProductBase {

    /**
     * 有效期类型<br>
     * 1:固定有效期;2:灵活有效期
     */
    private String activeType;
    /**
     * 生效时间<br>
     * 若有效期类型为"固定有效期",则不能为空
     */
    private Date activeTime;
    /**
     * 失效时间<br>
     * 若有效期类型为"固定有效期",则不能为空
     */
    private Date inActiveTime;
    /**
     * 有效周期<br>
     * 若有效期类型为"灵活有效期",则不能为空
     */
    private Long activeCycle;
    /**
     * 单位<br>
     * 若有效期类型为"灵活有效期",则不能为空
     */
    private String unit;
    /**
     * 操作人ID<br>
     * 更新时不能为空
     */
    @NotNull(message = "创建人ID不能为空",
            groups = { INormProductSV.UpdateProductInfo.class})
    private Long operId;
    /**
     * 操作时间<br>
     * 若为空,更新时则填充服务接收时间
     */
    private Date operTime;
    /**
     * 标准品属性值集合
     */
    private List<NormProductAttrValRequest> attrValList;

    public List<NormProductAttrValRequest> getAttrValList() {
        return attrValList;
    }

    public void setAttrValList(List<NormProductAttrValRequest> attrValList) {
        this.attrValList = attrValList;
    }

    public String getActiveType() {
        return activeType;
    }

    public void setActiveType(String activeType) {
        this.activeType = activeType;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public Date getInActiveTime() {
        return inActiveTime;
    }

    public void setInActiveTime(Date inActiveTime) {
        this.inActiveTime = inActiveTime;
    }

    public Long getActiveCycle() {
        return activeCycle;
    }

    public void setActiveCycle(Long activeCycle) {
        this.activeCycle = activeCycle;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }
}
