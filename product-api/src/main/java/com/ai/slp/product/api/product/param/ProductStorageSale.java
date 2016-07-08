package com.ai.slp.product.api.product.param;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 商品管理售中与仓库商品返回类
 * 
 * Date: 2016年4月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public class ProductStorageSale implements Serializable{

    private static final long serialVersionUID = 1L;

	/**
     * 商品名称
     */
    private String prodName;
    
    /**
     * 商品ID
     */
    private String prodId;

    /**
     * 商品类目ID
     */
    private String productCatId;
    
    /**
     * 商品类目名称
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
     * 价格
     */
    private double salePrice;
    
    /**
     * 剩余库存量
     */
    private long storageNum;

    /**
     * 总销量
     */
    private long saleNum;

    /**
     * 状态
     * 0新增
     * 1未编辑2已编辑
     * 3审核中4审核未通过
     * 5在售
     * 6仓库中（审核通过放入） 61售罄下架62废弃下架63自动下架
     * 7停用8废弃
     */
    private String state;
    /**
     * 状态名称，页面展示状态名称
     */
    private String stateName;
    /**
     * 上架时间
     */
    private Timestamp upTime;
    /**
     * 下架时间
     */
    private Timestamp downTime;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 操作时间
     */
    private Timestamp operTime;
    
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

	public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
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

    public Long getProPictureId() {
        return proPictureId;
    }

    public void setProPictureId(Long proPictureId) {
        this.proPictureId = proPictureId;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public long getStorageNum() {
        return storageNum;
    }

    public void setStorageNum(long storageNum) {
        this.storageNum = storageNum;
    }

    public long getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(long saleNum) {
        this.saleNum = saleNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    
    public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
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

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
