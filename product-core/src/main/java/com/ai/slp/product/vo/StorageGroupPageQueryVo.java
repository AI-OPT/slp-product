package com.ai.slp.product.vo;

import java.sql.Timestamp;

/**
 * 价格管理中设置最低最高销售价中库存组列表查询条件
 */
public class StorageGroupPageQueryVo {
	/**
	 * 租户Id，必填
	 */
	private String tenantId;
	/**
	 * 销售商(商户)标识
	 */
	private String supplierId;
	/**
     * 请求查询的页码
     * 默认为1
     */
    private Integer pageNo = 1;
    /**
     * 每页显示条数,默认每页20条
     */
    private Integer pageSize =20;
    /**
     * 库存组标识
     */
    private String storageGroupId;
    /**
     * 库存组名称
     */
    private String storageGroupName;
    /**
     * 标准品标识
     */
    private String standedProdId;
    /**
     * 标准品名称
     */
    private String standedProductName;
    /**
     * 创建时间范围开始时间
     */
    private Timestamp createTimeStart;
    /**
     * 创建时间范围结束时间
     */
    private Timestamp createTimeEnd;
    
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getStorageGroupId() {
		return storageGroupId;
	}
	public void setStorageGroupId(String storageGroupId) {
		this.storageGroupId = storageGroupId;
	}
	public String getStandedProdId() {
		return standedProdId;
	}
	public void setStandedProdId(String standedProdId) {
		this.standedProdId = standedProdId;
	}
	public String getStandedProductName() {
		return standedProductName;
	}
	public void setStandedProductName(String standedProductName) {
		this.standedProductName = standedProductName;
	}
	public String getStorageGroupName() {
		return storageGroupName;
	}
	public void setStorageGroupName(String storageGroupName) {
		this.storageGroupName = storageGroupName;
	}
	public Timestamp getCreateTimeStart() {
		return createTimeStart;
	}
	public void setCreateTimeStart(Timestamp createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public Timestamp getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(Timestamp createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
    
}
