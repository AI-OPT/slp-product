package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.product.interfaces.IProductSV;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * SKU单品信息<br>
 *
 *
 * Date: 2016年4月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class ProdSkuInfo extends BaseInfo {
    /**
     * sku单品标识
     * 在商城商品库存管理中,会根据标识进行更新操作
     */
    private String skuId;
    /**
     * 商品标识
     */
    @NotNull(message = "商品标识不能为空",
            groups = { IProductSV.SaveMultSKUInfo.class })
    private String prodId;
    /**
     * 库存组标识
     */
    @NotNull(message = "库存组标识不能为空",
            groups = { IProductSV.SaveMultSKUInfo.class })
    private Long storageGroupId;
    /**
     * 属性串<br>
     * 若不存在SKU单品标识,则使用属性串进行查重更新或添加
     */
    @NotNull(message = "属性串不能为空",
            groups = { IProductSV.SaveMultSKUInfo.class })
    private String saleAttrs;
    /**
     * SKU单品的状态
     */
    private String state;
    /**
     * 库存量
     */
    private long totalNum;
    /**
     * 操作人ID
     */
    @NotNull(message = "操作人不能为空",
            groups = { IProductSV.SaveMultSKUInfo.class })
    private Long operId;
    /**
     * 操作时间<br>
     * 若不填写,则设置接收时间
     */
    private Date operTime;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public Long getStorageGroupId() {
        return storageGroupId;
    }

    public void setStorageGroupId(Long storageGroupId) {
        this.storageGroupId = storageGroupId;
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

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
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
}
