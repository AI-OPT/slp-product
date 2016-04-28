package com.ai.slp.product.api.normproduct.param;

import javax.validation.constraints.NotNull;

import com.ai.slp.product.api.normproduct.interfaces.IProdAttrDefSV;
import com.ai.slp.product.api.normproduct.interfaces.IProductCatSV;

public class ProductAttr extends ProductCatBase{
    /**
     * 属性ID
     */
    @NotNull(message = "属性ID不能为空", 
            groups = { IProdAttrDefSV.UpdateProductAttr.class,
                    IProdAttrDefSV.DeleteProductAttr.class})
    private long attrId;

    /**
     * 属性名称
     */
    @NotNull(message = "属性名称不能为空", 
            groups = { IProdAttrDefSV.AddProductAttr.class})
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
