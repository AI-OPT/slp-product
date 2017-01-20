package com.ai.slp.product.dao.mapper.attach;

import java.sql.Timestamp;

/**
 * Created by jackieliu on 16/8/18.
 */
public class CatAttrValAttach {
	/**
	 * 类目属性值id
	 */
    private String catAttrValueId;
    /**
	 * 属性值标识
	 */
    private String attrvalueDefId;
    /**
	 * 租户id
	 */
    private String tenantId;
    /**
	 * 属性标识
	 */
    private Long attrId;
    /**
	 * 属性值标识
	 */
    private String attrValueId;
    /**
	 * 属性值名称
	 */
    private String attrValueName;
    /**
	 * 首字母
	 */
    private String firstLetter;
    /**
	 * 序号
	 */
    private Short serialNumber;
    /**
	 * 备注
	 */
    private String remark;
    /**
	 * 状态
	 */
    private String state;
    /**
	 * 操作人id
	 */
    private Long operId;
    /**
	 * 操作时间
	 */
    private Timestamp operTime;

    public String getCatAttrValueId() {
        return catAttrValueId;
    }

    public void setCatAttrValueId(String catAttrValueId) {
        this.catAttrValueId = catAttrValueId;
    }

    public String getAttrvalueDefId() {
        return attrvalueDefId;
    }

    public void setAttrvalueDefId(String attrvalueDefId) {
        this.attrvalueDefId = attrvalueDefId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public String getAttrValueId() {
        return attrValueId;
    }

    public void setAttrValueId(String attrValueId) {
        this.attrValueId = attrValueId;
    }

    public String getAttrValueName() {
        return attrValueName;
    }

    public void setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public Short getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Short serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public Timestamp getOperTime() {
        return operTime;
    }

    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
    }
}
