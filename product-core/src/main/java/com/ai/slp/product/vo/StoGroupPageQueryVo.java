package com.ai.slp.product.vo;

import java.sql.Timestamp;

/**
 * 库存管理中虚拟库存列表查询条件
 * Created by jackieliu on 16/8/1.
 */
public class StoGroupPageQueryVo {
    private String tenantId;
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
     * 销售商(商户)标识
     */
    private String supplierId;
    /**
     * 库存组标识
     */
    private String storageGroupId;
    /**
     * 状态 null:全部;1:启用;2:停用;3:废弃
     */
    private String state;
    /**
     * 操作者ID
     */
    private Long operId;
    /**
     * 标准品标识
     */
    private String standedProdId;
    /**
     * 标准品名称
     */
    private String standedProductName;
    /**
     * 库存组名称
     */
    private String storageGroupName;
    /**
     * 查询中操作时间的开始值
     */
    private Timestamp operTimeStart;
    /**
     * 查询中操作时间的结束值
     */
    private Timestamp operTimeEnd;
    /**
     * 创建时间范围开始时间
     */
    private Timestamp createTimeStart;
    /**
     * 创建时间范围结束时间
     */
    private Timestamp createTimeEnd;

    /**
     * 排序
     */
    protected String orderByClause;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
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

    public Timestamp getOperTimeStart() {
        return operTimeStart;
    }

    public void setOperTimeStart(Timestamp operTimeStart) {
        this.operTimeStart = operTimeStart;
    }

    public Timestamp getOperTimeEnd() {
        return operTimeEnd;
    }

    public void setOperTimeEnd(Timestamp operTimeEnd) {
        this.operTimeEnd = operTimeEnd;
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

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

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
}
