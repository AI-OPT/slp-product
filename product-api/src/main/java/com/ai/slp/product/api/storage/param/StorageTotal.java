package com.ai.slp.product.api.storage.param;

import java.util.List;

import com.ai.opt.base.vo.BaseInfo;

public class StorageTotal extends BaseInfo{
    /**
     * 单品SKU标识ID
     */
    private String skuId;
    /**
     * SKU属性串
     */
    private String saleAttrs;
    /**
     * 库存量
     */
    private long totalNum;
    /**
     * 属性集合，将SKU字符串解析
     */
    private List<String> attrs;
    
    public List<String> getAttrs() {
        String[] strs = saleAttrs.split(";");
        for(String str:strs){
            attrs.add(str);
        }
        return attrs;
    }
    public void setAttrs(List<String> attrs) {
        this.attrs = attrs;
    }
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
    public long getTotalNum() {
        return totalNum;
    }
    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    
}
