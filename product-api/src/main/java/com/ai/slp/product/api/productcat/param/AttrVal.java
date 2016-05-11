package com.ai.slp.product.api.productcat.param;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 单个属性值查询返回类
 * 
 * Date: 2016年5月4日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public class AttrVal extends BaseResponse{
    
    private static final long serialVersionUID = 1L;

    /**
	 * 属性ID
	 */
    private Long attrId;
    
	/**
     * 属性值名称
     */
    private String attrValueName;
    
    /**
     * 属性值首字母
     */
    private String firstLetter;

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

	public Long getAttrId() {
		return attrId;
	}

	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}
    
}
