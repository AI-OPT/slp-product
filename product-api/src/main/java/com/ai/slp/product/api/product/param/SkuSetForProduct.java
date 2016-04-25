package com.ai.slp.product.api.product.param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 某个商城商品的SKU单品信息集合<br>
 * 目前用于商城商品库存管理和具有销售属性的商城商品的销售价编辑
 *
 * Date: 2016年4月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class SkuSetForProduct implements Serializable {
    /**
     * sku单品信息集合
     * k:sku标识;v:sku对象
     */
    private Map<String,ProdSkuInfo> skuInfoMap;
    /**
     * sku属性串对应的属性标识
     * k:sku属性串;v:sku标识
     */
    private Map<String,String> saleAttrsMap;
    /**
     * 属性串级别,用于属性合并显示
     * k:属性串;v:属性串对应的下级属性串
     * 示例:
     *  属性Aid:属性值A1id-->{
     *      属性Aid:属性值A1id;属性Bid:属性B1id,
     *      属性Aid:属性值A1id;属性Bid:属性B2id
     *      }
     *  属性Aid:属性值A1id;属性Bid:属性B1id-->{
     *      属性Aid:属性值A1id;属性Bid:属性B1id;属性Cid:属性C1id,
     *      属性Aid:属性值A1id;属性Bid:属性B1id;属性Cid:属性C2id,
     *      }
     */
    private Map<String,Set<String>> attrsAndChild;

    public Map<String, ProdSkuInfo> getSkuInfoMap() {
        return skuInfoMap;
    }

    public void setSkuInfoMap(Map<String, ProdSkuInfo> skuInfoMap) {
        this.skuInfoMap = skuInfoMap;
    }

    public Map<String, String> getSaleAttrsMap() {
        return saleAttrsMap;
    }

    public void setSaleAttrsMap(Map<String, String> saleAttrsMap) {
        this.saleAttrsMap = saleAttrsMap;
    }

    public Map<String, Set<String>> getAttrsAndChild() {
        return attrsAndChild;
    }

    public void setAttrsAndChild(Map<String, Set<String>> attrsAndChild) {
        this.attrsAndChild = attrsAndChild;
    }
}
