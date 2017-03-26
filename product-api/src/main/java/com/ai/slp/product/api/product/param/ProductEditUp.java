package com.ai.slp.product.api.product.param;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
/**
 * 商品编辑上架返回类
 * 
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public class ProductEditUp implements Serializable{

    private static final long serialVersionUID = 1L;

	/**
     * 商品ID
     */
    private String prodId;
    
    /**
     * 标准品ID
     * 
     */
    private String standedProdId;
    
    /**
     * 商品类目ID
     */
    private String productCatId;
    
    /**
     * 类目名称
     */
    private String productCatName;
    
    /**
     * 商品类型
     */
    private String productType;
    /**
     * 商品类型名称，页面展示类型名称
     */
    private String productTypeName;
    
    /**
     * 商品图ID
     */
    private Long proPictureId;

    /**
     * 图片文件模块ID
     */
    private String vfsId;

    /**
     * 图片类型
     */
    private String picType;
    /**
     * 图片地址,为前端预留字段
     */
    private String picUrl;
    
    /**
     * 商品名称
     */
    private String prodName;
    
    /**
     * 总库存
     */
    private long totalNum;

    /**
     * 状态
     * 0:新增
     * 1:未编辑;2:已编辑
     * 3:审核中;4:审核未通过
     * 5:在售
     * 6:仓库中（审核通过放入、手动下架放入） 61:售罄下架 62:废弃下架
     * 7:废弃
     */
    private String state;
    /**
     * 状态名称，页面展示状态名称
     */
    private String stateName;
    /**
     * 商户标识,必填
     * -1:自营
     *  0:全部
     */
    private String supplierId;
    /**
     *商品受众userType
     * 
     */
    private List<String> userTypes;
    

    
	public String getStandedProdId() {
		return standedProdId;
	}

	public void setStandedProdId(String standedProdId) {
		this.standedProdId = standedProdId;
	}

	public List<String> getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(List<String> userTypes) {
		this.userTypes = userTypes;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
     * 申请优先级:1优先0普通
     */
    private int priorityNumber;
    
    /**
     * 优先理由
     */
    private String priorityReason;
    
    /**
     * 生成时间
     */
    private Timestamp createTime;
    /**
     * 申请优先
     */
    private String precedence;

    /**
     * 拒绝原因-被拒绝参数
     */
    private String refuseReason;
    
    /**
     * 拒绝描述-被拒绝参数
     */
    private String refuseDes;

    /**
     * 操作时间
     */
    private Timestamp operTime;
    /**
     * 上架时间
     */
    private Timestamp upTime;
    /**
     * 下架时间
     */
    private Timestamp downTime;

    public Timestamp getUpTime() {
		return upTime;
	}

	public void setUpTime(Timestamp upTime) {
		this.upTime = upTime;
	}

	public Timestamp getDownTime() {
		return downTime;
	}

	public void setDownTime(Timestamp downTime) {
		this.downTime = downTime;
	}

	public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

    public String getProductCatName() {
        return productCatName;
    }

    public void setProductCatName(String productCatName) {
        this.productCatName = productCatName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPriorityNumber() {
        return priorityNumber;
    }

    public void setPriorityNumber(int priorityNumber) {
        this.priorityNumber = priorityNumber;
    }

    public String getPriorityReason() {
        return priorityReason;
    }

    public void setPriorityReason(String priorityReason) {
        this.priorityReason = priorityReason;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getRefuseDes() {
        return refuseDes;
    }

    public void setRefuseDes(String refuseDes) {
        this.refuseDes = refuseDes;
    }

    public String getPrecedence() {
        return precedence;
    }

    public void setPrecedence(String precedence) {
        this.precedence = precedence;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getOperTime() {
        return operTime;
    }

    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
    }

    public Long getProPictureId() {
        return proPictureId;
    }

    public void setProPictureId(Long proPictureId) {
        this.proPictureId = proPictureId;
    }

    public String getVfsId() {
        return vfsId;
    }

    public void setVfsId(String vfsId) {
        this.vfsId = vfsId;
    }

    public String getPicType() {
        return picType;
    }

    public void setPicType(String picType) {
        this.picType = picType;
    }

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}
