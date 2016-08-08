package com.ai.slp.product.api.normproduct.param;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 标准品查询返回信息<br>
 *
 * Date: 2016年4月18日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class NormProdResponse implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
     * 类目ID
     */
    private String catId;

    /**
     * 标准品ID
     */
    private String productId;

    /**
     * 标准品名称
     */
    private String productName;

    /**
     * 标准品状态<br>
     * 0废弃;1可使用;2不可使用
     */
    private String state;

    /**
     * 标准品类型<br>
     * 1实物;2虚拟
     */
    private String productType;
    /**
     * 类型名称
     */
    private String productTypeName;
    /**
     * 市场价
     */
    private Long marketPrice;
    /**
     * 库存组的数量
     */
    private int storageGroupNum;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 创建人ID
     */
    private Long createId;
    /**
     * 操作者ID
     */
    private Long operId;
    /**
     * 操作时间
     */
    private Timestamp operTime;
    /**
     * 类目名称
     */
    private String catName;

    /**
     * 创建者名称
     */
    private String createName;
    /**
     * 操作者名称
     */
    private String operName;
    /**
     * (新增字段)
     * 商户ID--(-1:自运营)
     */
    private String supplierId;
    
    public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Long getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Long marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
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

    public int getStorageGroupNum() {
        return storageGroupNum;
    }

    public void setStorageGroupNum(int storageGroupNum) {
        this.storageGroupNum = storageGroupNum;
    }

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
}
