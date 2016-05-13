package com.ai.slp.product.api.productcat.param;

import java.sql.Date;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 类目属性组请求参数
 * 
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public class ProAttrGroupParam extends BaseInfo {
    
    private static final long serialVersionUID = 1L;

	/**
     * 类目属性组ID
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
     * logo地址
     */
    private String logoURL;
    
    /**
     * 属性数量-通过属性组ID在商品属性定义表中统计
     */
    private long attrNum;

    /**
     * 状态
     * 1有效0无效
     */
    private String state;
    
    /**
     * 操作人ID
     */
    private long operId;
    
    /**
     * 操作时间
     */
    private Date operTime;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getOperId() {
        return operId;
    }

    public void setOperId(long operId) {
        this.operId = operId;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }
    
    
    
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

	public long getAttrNum() {
		return attrNum;
	}

	public void setAttrNum(long attrNum) {
		this.attrNum = attrNum;
	}
    
    
}
