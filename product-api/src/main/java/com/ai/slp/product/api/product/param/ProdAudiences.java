package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 商品受众信息<br>
 *
 *
 * Date: 2016年4月26日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class ProdAudiences extends BaseInfo {
    /**
     * 商品受众标识id
     */
    private Long audiencesId;
    /**
     * 商品标识id
     */
    private String prodId;
    /**
     * 受众类型
     */
    private String userType;
    /**
     * 用户唯一标识
     */
    private Long userId;
    /**
     * 状态
     */
    private String state;
    /**
     * 操作人标识id
     */
    private long operId;

    public Long getAudiencesId() {
        return audiencesId;
    }

    public void setAudiencesId(Long audiencesId) {
        this.audiencesId = audiencesId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getOperId() {
        return operId;
    }

    public void setOperId(long operId) {
        this.operId = operId;
    }
}
