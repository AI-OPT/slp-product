package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;

import java.sql.Timestamp;

/**
 * 虚拟库存组列表信息<br>
 *
 * Date: 2016年4月21日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StorageGroupQueryPage extends BaseInfo {
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
    private String groupId;
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
    private String prodId;
    /**
     * 标准品名称
     */
    private String prodName;
    /**
     * 库存组名称
     */
    private String groupName;
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
}
