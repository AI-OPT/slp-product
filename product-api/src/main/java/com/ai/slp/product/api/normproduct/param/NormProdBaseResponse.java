package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseResponse;

import java.sql.Timestamp;

/**
 * 标准品信息的基础信息,用于返回信息<br>
 *
 * Date: 2016年4月18日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public abstract class NormProdBaseResponse extends BaseResponse {
    /**
     * 租户Id，必填
     */
    private String tenantId;
    /**
     * 租户密码，可选
     */
    private String tenantPwd;

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
     * 创建时间<br>
     */
    private Timestamp createTime;

    /**
     * 创建人ID<br>
     */
    private Long createId;

    public String getTenantId() {
        return tenantId;
    }
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
    public String getTenantPwd() {
        return tenantPwd;
    }
    public void setTenantPwd(String tenantPwd) {
        this.tenantPwd = tenantPwd;
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
}