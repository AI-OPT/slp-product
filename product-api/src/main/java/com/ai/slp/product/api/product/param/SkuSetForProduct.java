package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseResponse;

import java.util.List;

/**
 * 某个商城商品的SKU单品信息集合
 *
 * Date: 2016年4月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class SkuSetForProduct extends BaseResponse {
    private static final long serialVersionUID = 1L;

	/**
     * 商品标识
     */
    private String prodId;

    /**
     * 库存组标识
     */
    private String storageGroupId;

    /**
     * sku单品信息集合
     */
    private List<SkuInfo> skuInfoList;

    /**
     * 属性的集合,根据属性顺序排序<br>
     */
    private List<SkuAttrInfo> attrInfoList;

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getStorageGroupId() {
        return storageGroupId;
    }

    public void setStorageGroupId(String storageGroupId) {
        this.storageGroupId = storageGroupId;
    }

    public List<SkuInfo> getSkuInfoList() {
        return skuInfoList;
    }

    public void setSkuInfoList(List<SkuInfo> skuInfoList) {
        this.skuInfoList = skuInfoList;
    }

    public List<SkuAttrInfo> getAttrInfoList() {
        return attrInfoList;
    }

    public void setAttrInfoList(List<SkuAttrInfo> attrInfoList) {
        this.attrInfoList = attrInfoList;
    }
}
