package com.ai.slp.product.api.product.param;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 某个商城商品的SKU单品信息集合
 *
 * Date: 2016年4月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class SkuSetForProduct implements Serializable {
    /**
     * 商品标识
     */
    private String prodId;

    /**
     * 库存组标识
     */
    private String storageGroupId;
    /**
     * SKU总数
     */
    private int skuNum;
    /**
     * sku单品信息集合
     * k:sku标识;v:sku对象
     */
    private Map<String,SkuInfo> skuInfoMap;
    /**
     * sku属性串对应的属性标识<br>
     * k:sku属性串;v:sku标识
     */
    private Map<String,String> saleAttrsMap;

    /**
     * 属性标识和属性值标识的集合<br>
     * K:属性标识;V:属性值标识集合(标准品属性值)
     */
    private Map<Long,Set<String>> attrAndValIdMap;

    /**
     * 属性和属性值对象的集合<br>
     * K:属性对象;V:属性值集合
     */
    private Map<SkuAttrInfo,List<SkuAttrValInfo>> attrAndValInfoMap;

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

    public int getSkuNum() {
        return skuNum;
    }

    public void setSkuNum(int skuNum) {
        this.skuNum = skuNum;
    }

    public Map<String, SkuInfo> getSkuInfoMap() {
        return skuInfoMap;
    }

    public void setSkuInfoMap(Map<String, SkuInfo> skuInfoMap) {
        this.skuInfoMap = skuInfoMap;
    }

    public Map<String, String> getSaleAttrsMap() {
        return saleAttrsMap;
    }

    public void setSaleAttrsMap(Map<String, String> saleAttrsMap) {
        this.saleAttrsMap = saleAttrsMap;
    }

    public Map<Long, Set<String>> getAttrAndValIdMap() {
        return attrAndValIdMap;
    }

    public void setAttrAndValIdMap(Map<Long, Set<String>> attrAndValIdMap) {
        this.attrAndValIdMap = attrAndValIdMap;
    }

    public Map<SkuAttrInfo, List<SkuAttrValInfo>> getAttrAndValInfoMap() {
        return attrAndValInfoMap;
    }

    public void setAttrAndValInfoMap(Map<SkuAttrInfo, List<SkuAttrValInfo>> attrAndValInfoMap) {
        this.attrAndValInfoMap = attrAndValInfoMap;
    }
}
