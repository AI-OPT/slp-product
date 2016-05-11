package com.ai.slp.product.api.productcat.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.productcat.interfaces.IAttrAndValDefSV;

/**
 * 属性值添加修改请求参数<br>
 * 
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public class AttrValParam extends BaseInfo {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 属性ID
	 */
	@NotBlank(message = "属性ID不能为空",
            groups = {IAttrAndValDefSV.UpdateAttrVal.class,
			IAttrAndValDefSV.AddAttrVal.class})
	private Long attrId;
	
	/**
	 * 属性值ID
	 */
	private String attrvalueDefId;
	
	/**
	 * 属性值名称
	 */
	@NotBlank(message = "属性值不能为空",
	        groups = {IAttrAndValDefSV.AddAttrVal.class,
	                IAttrAndValDefSV.UpdateAttrVal.class})
	private String attrValueName;
	
	/**
	 * 首字母
	 */
	private String firstLetter;
    
    /**
     * 操作人ID
     */
	@NotNull(message = "操作人ID不能为空",
	        groups = {IAttrAndValDefSV.AddAttrVal.class,
	                IAttrAndValDefSV.UpdateAttrVal.class})
    private Long operId;
    
    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

	public Long getAttrId() {
		return attrId;
	}

	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}

	public String getAttrvalueDefId() {
		return attrvalueDefId;
	}

	public void setAttrvalueDefId(String attrvalueDefId) {
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
