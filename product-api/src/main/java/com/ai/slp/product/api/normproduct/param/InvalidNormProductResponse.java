package com.ai.slp.product.api.normproduct.param;

import java.util.Date;

/**
 * 废弃标准品列表返回信息<br>
 *
 * Date: 2016年4月18日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class InvalidNormProductResponse extends NormProductResponse {

    /**
     * 操作人
     */
    private Long operUserId;

    /**
     * 操作人名称
     */
    private String operUserName;

    /**
     * 操作时间
     */
    private Date operTime;

    public Long getOperUserId() {
        return operUserId;
    }

    public void setOperUserId(Long operUserId) {
        this.operUserId = operUserId;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public String getOperUserName() {
        return operUserName;
    }

    public void setOperUserName(String operUserName) {
        this.operUserName = operUserName;
    }
}
