package com.ai.slp.product.api.productcat.param;

import java.io.Serializable;
import java.util.List;

/**
 * 查询属性和属性值的MAP集合的返回属性类型
 * 
 * Date: 2016年5月3日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public class AttrDef implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     *属性标识
     */
    private Long attrId;
    
    /**
     *属性名称
     */
    private String attrName;
    
    /**
     *属性首字母
     */
    private String firstLetter;
    
    /**
     *值输入方式
     */
    private String valueWay;

    /**
     * 属性值
     */
    private List<AttrValDef> valDefList;

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
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

    public List<AttrValDef> getValDefList() {
        return valDefList;
    }

    public void setValDefList(List<AttrValDef> valDefList) {
        this.valDefList = valDefList;
    }
}
