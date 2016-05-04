package com.ai.slp.product.api.normproduct.param;

import java.sql.Timestamp;
import java.util.Date;

import com.ai.opt.base.vo.BaseResponse;
/**
 * 属性分页查询返回参数
 * 
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class AttrDefInfo {
    
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
     * 属性值数量-通过在标准品属性值表中统计有此属性ID的有效属性值的数量
     */
    private int attrValNum;

    /**
     * 是否允许用户自定义属性值
     */
    private String isAllowCustom;
    
    /**
     *操作人
     */
    private String oper;
    
    /**
     * 操作人ID
     */
    private Long operId;
    
    /**
     * 操作时间
     */
    private Timestamp operTime;

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

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

}
