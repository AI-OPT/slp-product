package com.ai.slp.product.api.normproduct.param;

/**
 * 类目属性组请求参数
 * 
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class ProAttrGroupParam extends ProductCatBase {
    
    /**
     * 类目属性组ID
     */
    private long attrGroupId;
    
    /**
     * 商品类目ID
     */
    private long productCatId;
    
    /**
     * 属性组名称
     */
    private String attrGroupName;
    
    /**
     * logo地址
     */
    private String logoURL;
    
    /**
     * 属性数量-通过属性组ID在商品属性定义表中统计
     */
    private long attrNum;
    
    public long getAttrGroupId() {
        return attrGroupId;
    }

    public void setAttrGroupId(long attrGroupId) {
        this.attrGroupId = attrGroupId;
    }

    public long getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(long productCatId) {
        this.productCatId = productCatId;
    }

    public String getAttrGroupName() {
        return attrGroupName;
    }

    public void setAttrGroupName(String attrGroupName) {
        this.attrGroupName = attrGroupName;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }
    
    
}
