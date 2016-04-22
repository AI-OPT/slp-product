package com.ai.slp.product.api.normproduct.param;

public class ProductAttr extends ProductCatBase{
    /**
     * 属性ID
     */
    private long attrId;

    /**
     * 属性名称
     */
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
    //TODO一期不做
    private char isCustom;

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
