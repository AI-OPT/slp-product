package com.ai.slp.product.api.productcat.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.productcat.interfaces.IAttrAndValDefSV;

/**
 * 增加修改属性参数 
 * 
 * Date: 2016年5月4日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public class AttrParam extends BaseInfo{
    
    private static final long serialVersionUID = 1L;

	/**
     * 属性标识
     */
	@NotBlank(message = "属性ID不能为空", 
            groups = { IAttrAndValDefSV.UpdateAttr.class})
    private Long attrId;

    /**
     * 属性名称
     */
	@NotBlank(message = "属性名称不能为空", 
            groups = { IAttrAndValDefSV.CreateAttrs.class,
                    IAttrAndValDefSV.UpdateAttr.class})
    private String attrName;

    /**
     * 值输入方式:
     * 1.下拉单选 2.多选 3.可输入文本框（单行）4.可输入文本框（多行）
     * 5.日期时间 6.日期时间段
     */
	@NotBlank(message = "值输入方式不能为空",
			groups = { IAttrAndValDefSV.CreateAttrs.class})
    private String valueWay;
    
    /**
     * 属性名称首字母大写-由用户输入
     */
    private String firstLetter;
    
    /**
     * 是否允许用户自定义属性值
     */
    private String isAllowCustom;
    
    /**
     * 操作人ID
     */
    @NotNull(message = "操作人ID不能为空",
            groups = { IAttrAndValDefSV.UpdateAttr.class,
                    IAttrAndValDefSV.DeleteAttr.class,
                    IAttrAndValDefSV.CreateAttrs.class})
    private long operId;

    public long getAttrId() {
        return attrId;
    }

    public void setAttrId(long attrId) {
        this.attrId = attrId;
    }

    public long getOperId() {
        return operId;
    }

    public void setOperId(long operId) {
        this.operId = operId;
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

    public String getIsCustom() {
        return isAllowCustom;
    }

    public void setIsCustom(String isCustom) {
        this.isAllowCustom = isCustom;
    }
    
    
}
