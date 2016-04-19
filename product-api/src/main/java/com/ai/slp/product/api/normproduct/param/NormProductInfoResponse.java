package com.ai.slp.product.api.normproduct.param;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    /**
     * 关键属性集合,包括属性对应标准品属性值集合
     */
    private Map<AttrDefResponse,List<NormProductAttrValResponse>> keyAttrs;
    /**
     * 非关键属性集合,包括属性对应标准品属性值集合
     */
    private Map<AttrDefResponse,List<NormProductAttrValResponse>> nonKeyAttrs;

    /**
     * 销售属性集合,包括属性对应标准品属性值集合
     */
    private Map<AttrDefResponse,List<NormProductAttrValResponse>> saleAttrs;

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

    public Map<AttrDefResponse, List<NormProductAttrValResponse>> getKeyAttrs() {
        return keyAttrs;
    }

    public void setKeyAttrs(Map<AttrDefResponse, List<NormProductAttrValResponse>> keyAttrs) {
        this.keyAttrs = keyAttrs;
    }

    public Map<AttrDefResponse, List<NormProductAttrValResponse>> getNonKeyAttrs() {
        return nonKeyAttrs;
    }

    public void setNonKeyAttrs(Map<AttrDefResponse, List<NormProductAttrValResponse>> nonKeyAttrs) {
        this.nonKeyAttrs = nonKeyAttrs;
    }

    public Map<AttrDefResponse, List<NormProductAttrValResponse>> getSaleAttrs() {
        return saleAttrs;
    }

    public void setSaleAttrs(Map<AttrDefResponse, List<NormProductAttrValResponse>> saleAttrs) {
        this.saleAttrs = saleAttrs;
    }
}
