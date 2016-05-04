package com.ai.slp.product.api.normproduct.param;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 属性定义信息<br>
 *
 * Date: 2016年4月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class AttrDefResponse extends BaseInfo {
    /**
     * 属性ID
     */
    private Long attrId;
    /**
     * 属性组ID
     */
    private Long attrGroupId;
    /**
     * 商品类目ID
     */
    private long productCatId;
    /**
     * 属性名称
     */
    private String attrName;
    /**
     * 属性类型
     */
    private String attrType;
    /**
     * 值输入方式
     */
    private String valueWay;
    /**
     * 是否上传照片
     */
    private Boolean isPicture;
    /**
     * 是否必填
     */
    private Boolean isNecessary;
    /**
     * 序列号-用于排序
     */
    private Long serialNumber;
    /**
     * 状态
     */
    private String state;
    /**
     * 操作人ID
     */
    private Long operId;
    /**
     * 操作时间
     */
    private Timestamp operTime;

    public Timestamp getOperTime() {
        return operTime;
    }

    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public Long getAttrGroupId() {
        return attrGroupId;
    }

    public void setAttrGroupId(Long attrGroupId) {
        this.attrGroupId = attrGroupId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getValueWay() {
        return valueWay;
    }

    public void setValueWay(String valueWay) {
        this.valueWay = valueWay;
    }

    public Boolean getPicture() {
        return isPicture;
    }

    public void setPicture(Boolean picture) {
        isPicture = picture;
    }

    public Boolean getNecessary() {
        return isNecessary;
    }

    public void setNecessary(Boolean necessary) {
        isNecessary = necessary;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
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
}
