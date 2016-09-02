package com.ai.slp.product.api.normproduct.param;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Set;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 标准品详情信息<br>
 *
 * Date: 2016年4月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class NormProdInfoResponse extends BaseResponse {
    private static final long serialVersionUID = 1L;

	/**
     * 租户Id
     */
    private String tenantId;

    /**
     * 类目ID<br>
     */
    private String productCatId;

    /**
     * 标准品ID
     */
    private String productId;

    /**
     * 标准品名称
     */
    private String productName;

    /**
     * 标准品状态
     * 0:废弃;1:可使用;2:不可使用
     */
    private String state;

    /**
     * 标准品类型
     * 1实物;2虚拟
     */
    private String productType;
    /**
     * 市场价
     */
    private Long marketPrice;

    /**
     * 创建时间<br>
     */
    private Timestamp createTime;

    /**
     * 创建人ID<br>
     */
    private Long createId;
    /**
     * 创建者名称
     */
    private String createName;

    /**
     * 操作人
     */
    private Long operId;

    /**
     * 操作人名称
     */
    private String operName;

    /**
     * 操作时间
     */
    private Timestamp operTime;

    /**
     * 属性与属性值id对应关系
     */
    private Map<Long,Set<String>> attrAndValueIds;
    
    /**
     * 属性与属性文本值对应关系
     */
    private Map<Long,String> attrAndValueMap;
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

	public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
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

    public Long getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Long marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
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

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public Timestamp getOperTime() {
        return operTime;
    }

    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
    }

    public Map<Long, Set<String>> getAttrAndValueIds() {
        return attrAndValueIds;
    }

    public void setAttrAndValueIds(Map<Long, Set<String>> attrAndValueIds) {
        this.attrAndValueIds = attrAndValueIds;
    }

	public Map<Long,String> getAttrAndValueMap() {
		return attrAndValueMap;
	}

	public void setAttrAndValueMap(Map<Long,String> attrAndValueMap) {
		this.attrAndValueMap = attrAndValueMap;
	}
}
