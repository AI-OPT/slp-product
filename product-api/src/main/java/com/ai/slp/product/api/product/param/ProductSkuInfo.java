package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 销售商品下SKU单品的信息
 * Created by jackieliu on 16/5/20.
 */
public class ProductSkuInfo extends BaseResponse {
    private static final long serialVersionUID = 1L;
	/**
     * sku单品标识
     */
    private String skuId;
    /**
     * 销售商品标识
     */
    private String productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 属性串
     */
    private String saleAttrs;
    /**
     * SKU单品的状态
     */
    private String state;
    /**
     * 商品图片标识
     */
    private String vfsId;
    /**
     * 库存可用量
     */
    private long usableNum;
    /**
     * 销售价
     */
    private Long salePrice;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getVfsId() {
        return vfsId;
    }

    public void setVfsId(String vfsId) {
        this.vfsId = vfsId;
    }

    public long getUsableNum() {
        return usableNum;
    }

    public void setUsableNum(long usableNum) {
        this.usableNum = usableNum;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }
}
