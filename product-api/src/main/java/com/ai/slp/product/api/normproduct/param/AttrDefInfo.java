package com.ai.slp.product.api.normproduct.param;

import java.util.Date;

import com.ai.opt.base.vo.BaseResponse;
/**
 * 属性返回参数
 * 
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class AttrDefInfo extends BaseResponse {
    
    /**
     * 属性ID
     */
    private long attrId;
    
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
     * 属性值数量-通过在标准品属性值表中统计有次属性ID的属性值的数量
     */
    private int attrValNum;
    
    /**
     * 是否上传照片
     * Y是N否
     */
    private char isPicture;
    
    /**
     * 是否允许用户自定义属性值
     */
    //TODO一期不做
    private char isCustom;
    
    /**
     * 是否必填
     * Y是N否
     */
    private char isNecessary;
    
    /**
     * 序列号
     */
    private long serialNumber;
    
    /**
     * 操作人ID
     */
    private long operId;
    
    /**
     * 操作时间
     */
    private Date operTime;

    public long getAttrId() {
        return attrId;
    }

    public void setAttrId(long attrId) {
        this.attrId = attrId;
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

    public int getAttrValNum() {
        return attrValNum;
    }

    public void setAttrValNum(int attrValNum) {
        this.attrValNum = attrValNum;
    }

    public char getIsPicture() {
        return isPicture;
    }

    public void setIsPicture(char isPicture) {
        this.isPicture = isPicture;
    }

    public char getIsCustom() {
        return isCustom;
    }

    public void setIsCustom(char isCustom) {
        this.isCustom = isCustom;
    }

    public char getIsNecessary() {
        return isNecessary;
    }

    public void setIsNecessary(char isNecessary) {
        this.isNecessary = isNecessary;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public long getOperId() {
        return operId;
    }

    public void setOperId(long operId) {
        this.operId = operId;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

}
