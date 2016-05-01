package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.BaseResponse;

import java.util.Date;

/**
 * 标准品属性值返回信息<br>
 *
 * Date: 2016年4月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class NormProdAttrValResponse extends BaseResponse {
    /**
     * 租户Id，必填
     */
    private String tenantId;
    /**
     * 标准品属性值ID
     */
    private Long productAttrValId;
    /**
     * 标准品标识
     */
    private String productId;
    /**
     * 属性ID
     */
    private Long attrId;
    /**
     * 属性值id
     */
    private Long attrValId;
    /**
     * 属性值
     */
    private String attrVal;
    /**
     * 属性值2,用于范围型属性值
     */
    private String attrVal2;
    /**
     * 序列号
     */
    private Long serialNumber;
    /**
     * 状态
     */
    private String state;
    /**
     * 操作人ID
     */
    private Long operId;
    /**
     * 操作时间
     */
    private Date operTime;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Long getProductAttrValId() {
        return productAttrValId;
    }

    public void setProductAttrValId(Long productAttrValId) {
        this.productAttrValId = productAttrValId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public String getAttrVal() {
        return attrVal;
    }

    public void setAttrVal(String attrVal) {
        this.attrVal = attrVal;
    }

    public String getAttrVal2() {
        return attrVal2;
    }

    public void setAttrVal2(String attrVal2) {
        this.attrVal2 = attrVal2;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
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

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public Long getAttrValId() {
        return attrValId;
    }

    public void setAttrValId(Long attrValId) {
        this.attrValId = attrValId;
    }
}
