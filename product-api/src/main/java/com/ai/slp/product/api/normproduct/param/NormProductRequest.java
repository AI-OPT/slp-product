package com.ai.slp.product.api.normproduct.param;

import java.util.Date;

/**
 * 标准品列表查询参数<br>
 *
 * Date: 2016年4月18日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class NormProductRequest extends NormProductBase {

    /**
     * 请求查询的页码<br>
     *  默认为1
     */
    private Integer pageNo = 1;

    /**
     * 每页显示条数<br>
     * 默认为20
     */
    private Integer pageSize = 20;

    /**
     * 操作人账户标识
     */
    private Long operId;

    /**
     * 创建时间范围的开始时间
     */
    private Date createStartTime;

    /**
     * 创建时间范围的截止时间
     */
    private Date createEndTime;

    /**
     * 操作/废弃时间范围的开始时间
     */
    private Date operStartTime;
    /**
     * 操作/废弃时间范围的截止时间
     */
    private Date operEndTime;

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

    public Date getCreateStartTime() {
        return createStartTime;
    }

    public void setCreateStartTime(Date createStartTime) {
        this.createStartTime = createStartTime;
    }

    public Date getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(Date createEndTime) {
        this.createEndTime = createEndTime;
    }

    public Date getOperStartTime() {
        return operStartTime;
    }

    public void setOperStartTime(Date operStartTime) {
        this.operStartTime = operStartTime;
    }

    public Date getOperEndTime() {
        return operEndTime;
    }

    public void setOperEndTime(Date operEndTime) {
        this.operEndTime = operEndTime;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }
}
