package com.ai.slp.product.api.productcat.param;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 单个属性查询返回参数
 * 
 * Date: 2016年5月4日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public class AttrInfo extends BaseResponse{
    private static final long serialVersionUID = 1L;

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
     * 是否允许用户自定义属性值
     */
    private String isAllowCustom;

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

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public String getValueWay() {
        return valueWay;
    }

    public void setValueWay(String valueWay) {
        this.valueWay = valueWay;
    }

    public String getIsAllowCustom() {
        return isAllowCustom;
    }

    public void setIsAllowCustom(String isAllowCustom) {
        this.isAllowCustom = isAllowCustom;
    }
    
    
}
