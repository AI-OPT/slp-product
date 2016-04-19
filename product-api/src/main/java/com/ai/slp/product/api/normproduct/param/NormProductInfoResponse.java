package com.ai.slp.product.api.normproduct.param;

import java.util.Date;

/**
 * 标准品详情信息<br>
 *
 * Date: 2016年4月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class NormProductInfoResponse extends NormProductBase {

    /**
     * 创建者名称
     */
    private String createUserName;

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

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Long getOperUserId() {
        return operUserId;
    }

    public void setOperUserId(Long operUserId) {
        this.operUserId = operUserId;
    }

    public String getOperUserName() {
        return operUserName;
    }

    public void setOperUserName(String operUserName) {
        this.operUserName = operUserName;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }
}
