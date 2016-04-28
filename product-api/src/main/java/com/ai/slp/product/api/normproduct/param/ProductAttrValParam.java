package com.ai.slp.product.api.normproduct.param;

import javax.validation.constraints.NotNull;

import com.ai.slp.product.api.normproduct.interfaces.IProdAttrDefSV;

/**
 * 属性值定义请求参数<br>
 * 
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class ProductAttrValParam extends ProductCatBase {
	
	/**
	 * 属性ID
	 */
    @NotNull(message = "属性ID不能为空",
            groups = {IProdAttrDefSV.DeleteProductAttrVal.class,
                    IProdAttrDefSV.UpdateProductAttrVal.class})
	private long attrId;
	
	/**
	 * 属性值ID
	 */
	private long attrvalueDefId;
	
	/**
	 * 属性值名称
	 */
	@NotNull(message = "属性值不能为空",
	        groups = {IProdAttrDefSV.AddProductAttrVal.class})
	private String attrValueName;
	
	/**
	 * 首字母
	 */
	private String firstLetter;

	public Long getAttrId() {
		return attrId;
	}

	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}

	public Long getAttrvalueDefId() {
		return attrvalueDefId;
	}

	public void setAttrvalueDefId(Long attrvalueDefId) {
		this.attrvalueDefId = attrvalueDefId;
	}

	public String getAttrValueName() {
		return attrValueName;
	}

	public void setAttrValueName(String attrValueName) {
		this.attrValueName = attrValueName;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}
	
	
}
