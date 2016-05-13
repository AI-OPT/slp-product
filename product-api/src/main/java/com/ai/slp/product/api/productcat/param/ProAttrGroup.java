package com.ai.slp.product.api.productcat.param;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 类目属性组返回参数
 * 
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public class ProAttrGroup extends BaseResponse{
    private static final long serialVersionUID = 1L;

	/**
     * 属性组ID
     */
    private long attrGroupId;
    
    /**
     * 商品类目ID
     */
    private long productCatId;
    
    /**
     * 属性组名称
     */
    private String attrGroupName;
    
    /**
     * 属性数量
     */
    private int attrValNum;
    
    /**
     * logo地址
     */
    private String logoURL;

    public long getAttrGroupId() {
        return attrGroupId;
    }

    public void setAttrGroupId(long attrGroupId) {
        this.attrGroupId = attrGroupId;
    }

    public long getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(long productCatId) {
        this.productCatId = productCatId;
    }

    public String getAttrGroupName() {
        return attrGroupName;
    }

    public void setAttrGroupName(String attrGroupName) {
        this.attrGroupName = attrGroupName;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

	public int getAttrValNum() {
		return attrValNum;
	}

	public void setAttrValNum(int attrValNum) {
		this.attrValNum = attrValNum;
	}
    
    
}
