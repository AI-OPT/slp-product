package com.ai.slp.product.api.webfront.param;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

public class ProductData extends BaseResponse {
    private static final long serialVersionUID = 1L;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * sku单品标识
     */
    private String skuId;

    /**
     * 商品ID
     */
    private String prodId;

    /**
     * 商品名称
     */
    private String prodName;

    /**
     * 销售价格
     */
    private float salePrice;

    /**
     * 评价数量
     */
    private int commentIdCount;

    /**
     * 主图片路径
     */
    private ProductImage imageinfo;

    /**
     * 副图片组
     */
    private List<ProductImage> thumbnail;

    /**
     * 代理商集合
     */
    private List<ProductAttrInfo> agentList;

    /**
     * 面额集合
     */
    private List<ProductAttrInfo> accountList;

    /**
     * 地区
     */
    private List<ProductAttrInfo> areaList;
    /**
     * 类目条件
     */
    private String productCatId;

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public int getCommentIdCount() {
        return commentIdCount;
    }

    public void setCommentIdCount(int commentIdCount) {
        this.commentIdCount = commentIdCount;
    }

    public ProductImage getImageinfo() {
        return imageinfo;
    }

    public void setImageinfo(ProductImage imageinfo) {
        this.imageinfo = imageinfo;
    }

    public List<ProductImage> getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(List<ProductImage> thumbnail) {
        this.thumbnail = thumbnail;
    }

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

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public List<ProductAttrInfo> getAgentList() {
        return agentList;
    }

    public void setAgentList(List<ProductAttrInfo> agentList) {
        this.agentList = agentList;
    }

    public List<ProductAttrInfo> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<ProductAttrInfo> accountList) {
        this.accountList = accountList;
    }

    public List<ProductAttrInfo> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<ProductAttrInfo> areaList) {
        this.areaList = areaList;
    }

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

}
