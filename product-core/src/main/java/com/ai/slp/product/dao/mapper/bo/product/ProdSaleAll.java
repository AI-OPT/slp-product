package com.ai.slp.product.dao.mapper.bo.product;

import java.sql.Timestamp;

public class ProdSaleAll {
    private Long proSaleId;

    private String tenantId;

    private String prodId;

    private String skuId;

    private Long saleNum;

    private Timestamp updateTime;

    public Long getProSaleId() {
        return proSaleId;
    }

    public void setProSaleId(Long proSaleId) {
        this.proSaleId = proSaleId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId == null ? null : skuId.trim();
    }

    public Long getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Long saleNum) {
        this.saleNum = saleNum;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}