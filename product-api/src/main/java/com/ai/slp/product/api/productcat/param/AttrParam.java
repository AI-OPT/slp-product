package com.ai.slp.product.api.productcat.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.productcat.interfaces.IAttrAndValDefSV;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

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
     * 属性标识,修改属性时不能为空
     */
	@NotNull(message = "属性ID不能为空", 
            groups = { IAttrAndValDefSV.UpdateAttr.class})
    private Long attrId;

    /**
     * 属性名称,添加和修改属性时不能为空
     */
	@NotBlank(message = "属性名称不能为空", 
            groups = { IAttrAndValDefSV.CreateAttrs.class,
                    IAttrAndValDefSV.UpdateAttr.class})
    private String attrName;

    /**
     * 值输入方式(添加属性时不能为空):
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
     * 操作人ID,修改、删除、添加属性时不能为空
     */
    @NotNull(message = "操作人ID不能为空",
            groups = { IAttrAndValDefSV.UpdateAttr.class,
                    IAttrAndValDefSV.DeleteAttr.class,
                    IAttrAndValDefSV.CreateAttrs.class})
    private Long operId;

    public String getIsAllowCustom() {
		return isAllowCustom;
	}

	public void setIsAllowCustom(String isAllowCustom) {
		this.isAllowCustom = isAllowCustom;
	}

	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}

	public Long getAttrId() {
        return attrId;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
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
