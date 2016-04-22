package com.ai.slp.product.api.storage.param;

import java.util.Date;

/**
 * 虚拟库存组列表信息<br>
 *
 * Date: 2016年4月21日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class STOStorageGroupQuery extends StorageGroupStatus {
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
    private Date operTimeStart;
    /**
     * 查询中操作时间的结束值
     */
    private Date operTimeEnd;

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

    public Date getOperTimeStart() {
        return operTimeStart;
    }

    public void setOperTimeStart(Date operTimeStart) {
        this.operTimeStart = operTimeStart;
    }

    public Date getOperTimeEnd() {
        return operTimeEnd;
    }

    public void setOperTimeEnd(Date operTimeEnd) {
        this.operTimeEnd = operTimeEnd;
    }
}
