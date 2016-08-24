package com.ai.slp.product.api.product.param;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * SKU单品信息
 *
 * Date: 2016年5月10日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class SkuInfo implements Serializable {
    private static final long serialVersionUID = 1L;
	/**
     * sku单品标识
     */
    private String skuId;
    /**
     * 属性串
     */
    private String saleAttrs;
    /**
     * SKU单品的状态
     */
    private String state;
    /**
     * 库存量<br>
     * 根据库存查询时,才有库存量
     */
    private Long totalNum;
    /**
     * 销售价<br>
     * 根据库存查询时,才有库存量
     */
    private Long salePrice;
    /**
     * 操作人ID
     */
    private Long operId;
    /**
     * 操作时间
     */
    private Timestamp operTime;
    /**
     * SKU属性及属性值信息
     */
    private List<SkuAttrVal> valForSkuList;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSaleAttrs() {
        return saleAttrs;
    }

    public void setSaleAttrs(String saleAttrs) {
        this.saleAttrs = saleAttrs;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public Timestamp getOperTime() {
        return operTime;
    }

    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
    }

    public List<SkuAttrVal> getValForSkuList() {
        return valForSkuList;
    }

    public void setValForSkuList(List<SkuAttrVal> valForSkuList) {
        this.valForSkuList = valForSkuList;
    }

}
