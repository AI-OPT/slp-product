package com.ai.slp.product.api.normproduct.param;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.normproduct.interfaces.IAttrAndValDefSV;

public class AttrParam extends BaseInfo{
    /**
     * 属性ID
     */
    @NotNull(message = "属性ID不能为空", 
            groups = { IAttrAndValDefSV.UpdateAttr.class,
                    IAttrAndValDefSV.DeleteAttr.class})
    private long attrId;

    /**
     * 属性名称
     */
    @NotNull(message = "属性名称不能为空", 
            groups = { IAttrAndValDefSV.AddAttr.class})
    private String attrName;

    /**
     * 值输入方式
     */
    private String valueWay;
    
    /**
     * 属性名称首字母大写-由用户输入
     */
    private String firstLetter;
    
    /**
     * 是否允许用户自定义属性值
     */
    private char isCustom;

    /**
     * 状态
     * 1有效0无效
     */
    private String state;
    
    /**
     * 操作人ID
     */
    private long operId;
    
    /**
     * 操作时间
     */
    private Date operTime;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public char getIsCustom() {
        return isCustom;
    }

    public void setIsCustom(char isCustom) {
        this.isCustom = isCustom;
    }
    
    
}
