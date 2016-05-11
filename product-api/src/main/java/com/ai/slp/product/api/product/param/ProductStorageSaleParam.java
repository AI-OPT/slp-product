package com.ai.slp.product.api.product.param;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 商品管理售中与仓库商品查询参数
 * 
 * Date: 2016年4月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public class ProductStorageSaleParam extends BaseInfo{

    private static final long serialVersionUID = 1L;
    
    /**
     * 请求查询的页码
     * 默认为1
     */
    private Integer pageNo = 1;

    /**
     * 每页显示条数,默认每页20条
     */
    private Integer pageSize =30;

	/**
     * 商品名称
     */
    private String prodName;
    
    /**
     * 商品ID
     */
    private String prodId;

    /**
     * 上架起始时间
     */
    private Timestamp upBeginTime;
    
    /**
     * 上架截止时间
     */
    private Timestamp upEndTime; 

    /**
     * 商品类目ID
     */
    private String productCatId;

    /**
     * 商品类型:实物、虚拟
     */
    private String productType;
    
    /**
     * 最高销售价
     */
    private long highSalePrice;
    
    /**
     * 最低销售价
     */
    private long lowSalePrice;
    
    /**
     * 最高销量
     */
    private long highSaleNum;
    
    /**
     * 最低销量
     */
    private long lowSaleNum;
    
    /**
     * 剩余最大库存量
     */
    private long highStorageNum;
    
    /**
     * 剩余最小库存量
     */
    private long lowStorageNum;
    
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

    public Timestamp getUpBeginTime() {
        return upBeginTime;
    }

    public void setUpBeginTime(Timestamp upBeginTime) {
        this.upBeginTime = upBeginTime;
    }

    public Timestamp getUpEndTime() {
        return upEndTime;
    }

    public void setUpEndTime(Timestamp upEndTime) {
        this.upEndTime = upEndTime;
    }

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public long getHighSalePrice() {
        return highSalePrice;
    }

    public void setHighSalePrice(long highSalePrice) {
        this.highSalePrice = highSalePrice;
    }

    public long getLowSalePrice() {
        return lowSalePrice;
    }

    public void setLowSalePrice(long lowSalePrice) {
        this.lowSalePrice = lowSalePrice;
    }

    public long getHighSaleNum() {
        return highSaleNum;
    }

    public void setHighSaleNum(long highSaleNum) {
        this.highSaleNum = highSaleNum;
    }

    public long getLowSaleNum() {
        return lowSaleNum;
    }

    public void setLowSaleNum(long lowSaleNum) {
        this.lowSaleNum = lowSaleNum;
    }

    public long getHighStorageNum() {
        return highStorageNum;
    }

    public void setHighStorageNum(long highStorageNum) {
        this.highStorageNum = highStorageNum;
    }

    public long getLowStorageNum() {
        return lowStorageNum;
    }

    public void setLowStorageNum(long lowStorageNum) {
        this.lowStorageNum = lowStorageNum;
    }

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

    
    
}
