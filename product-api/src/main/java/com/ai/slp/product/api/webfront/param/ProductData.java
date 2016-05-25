package com.ai.slp.product.api.webfront.param;

import java.io.Serializable;
import java.util.List;

public class ProductData implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * 销售价格
     */
    private Double salePrice;

    /**
     * 评价数量
     */
    private int commentIdCount;

    /**
     * 主图片路径
     */
    private String pictureUrl;

    /**
     * 副图片组
     */
    private List<String> pictureUrlList;

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public int getCommentIdCount() {
        return commentIdCount;
    }

    public void setCommentIdCount(int commentIdCount) {
        this.commentIdCount = commentIdCount;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public List<String> getPictureUrlList() {
        return pictureUrlList;
    }

    public void setPictureUrlList(List<String> pictureUrlList) {
        this.pictureUrlList = pictureUrlList;
    }

}
