package com.ai.slp.product.api.product.param;

import java.io.Serializable;

/**
 * 商品受众信息<br>
 *
 *
 * Date: 2016年4月26日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class ProdAudiencesInfo implements Serializable {
    private static final long serialVersionUID = 1L;
	/**
     * 商品受众标识id
     */
    private Long prodAudiencesId;
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
    private String userId;
    /**
     * 用户名称,企业名称
     */
    private String userName;
    /**
     * 用户登录名
     */
    private String loginAccount;
    /**
     * 状态
     */
    private String state;
    /**
     * 操作人标识id
     */
    private long operId;

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

    public Long getProdAudiencesId() {
        return prodAudiencesId;
    }

    public void setProdAudiencesId(Long prodAudiencesId) {
        this.prodAudiencesId = prodAudiencesId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }
}
