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
     *属性名称首字母大写
     */
    private String firstLetter;
    
    /**
     * 值输入方式
     */
    private String valueWay;
    
    /**
     * 属性值数量-通过在标准品属性值表中统计有次属性ID的属性值的数量
     */
    private int attrValNum;

    /**
     * 是否允许用户自定义属性值
     */
    private String isAllowCustom;
    
    /**
     * 操作人ID
     */
    private long operId;
    
    /**
     * 操作时间
     */
    private Date operTime;

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

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

    public String getIsAllowCustom() {
        return isAllowCustom;
    }

    public void setIsAllowCustom(String isAllowCustom) {
        this.isAllowCustom = isAllowCustom;
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
