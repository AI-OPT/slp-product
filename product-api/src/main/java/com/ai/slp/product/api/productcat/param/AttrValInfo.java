package com.ai.slp.product.api.productcat.param;

import java.sql.Timestamp;

/**
 * 属性值分页查询返回参数
 * 
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public class AttrValInfo {

    /**
     * 类目与属性值关联标识,
     * 只在查询类目的关联属性值时需要
     */
    private String catAttrValId;
    /**
     * 属性值ID
     */
    private String attrvalueDefId;
    
    /**
     * 属性值名称
     */
    private String attrValueName;
    
    /**
     * 属性值首字母
     */
    private String firstLetter;

    /**
     * 操作人ID
     */
    private Long operId;
    
    /**
     *操作人
     */
    private String operName;
    
    /**
     * 操作时间
     */
    private Timestamp operTime;
    /**
     * 顺序号
     */
    private Short serialNumber;

    public String getCatAttrValId() {
        return catAttrValId;
    }

    public void setCatAttrValId(String catAttrValId) {
        this.catAttrValId = catAttrValId;
    }

    public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getOper() {
        return operName;
    }

    public void setOper(String oper) {
        this.operName = oper;
    }

    public String getAttrvalueDefId() {
        return attrvalueDefId;
    }

    public void setAttrvalueDefId(String attrvalueDefId) {
        this.attrvalueDefId = attrvalueDefId;
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

    public Short getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Short serialNumber) {
        this.serialNumber = serialNumber;
    }
}
