package com.ai.slp.product.api.productserver.param;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 销售商品下SKU单品的信息
 * Created by jackieliu on 16/5/20.
 */
public class ProductInfoOfSku extends BaseResponse {
    private static final long serialVersionUID = 1L;
    /**
     * 销售商(租户)标识
     */
    private String supplierId;
    /**
     * sku单品标识
     */
    private String skuId;
    /**
     * sku名称
     */
    private String skuName;
    /**
     * 类目标识
     */
    private String productCatId;
    /**
     * 标准品标识
     */
    private String standedProdId;
    /**
     * 销售商品标识
     */
    private String prodId;
    /**
     * 商品编码
     */
    private String prodCode;
    /**
     * 商品名称
     */
    private String prodName;
    /**
     * 有效期类型<br>
     * 1固定有效期【生效时间、失效时间必填】<br>
     * 2灵活有效期（购买后一定时间有效）【有效周期、单位必填】
     */
    private String activeType;
    /**
     * 生效时间
     */
    private Timestamp activeTime;
    /**
     * 失效时间
     */
    private Timestamp inactiveTime;
    /**
     * 有效周期
     */
    private Short activeCycle;
    /**
     * 周期,D:天;M:月;Y:年
     */
    private String unit;
    /**
     * 是否提供发票<br>
     * Y:是;N:否
     */
    private String isInvoice;
    /**
     * 上架类型<br>
     *   1审核通过后立即上架
     *   2审核通过后放入仓库
     *   4预售
     */
    private String upshelfType;

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

    public String getStandedProdId() {
        return standedProdId;
    }

    public void setStandedProdId(String standedProdId) {
        this.standedProdId = standedProdId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getActiveType() {
        return activeType;
    }

    public void setActiveType(String activeType) {
        this.activeType = activeType;
    }

    public Timestamp getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Timestamp activeTime) {
        this.activeTime = activeTime;
    }

    public Timestamp getInactiveTime() {
        return inactiveTime;
    }

    public void setInactiveTime(Timestamp inactiveTime) {
        this.inactiveTime = inactiveTime;
    }

    public Short getActiveCycle() {
        return activeCycle;
    }

    public void setActiveCycle(Short activeCycle) {
        this.activeCycle = activeCycle;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(String isInvoice) {
        this.isInvoice = isInvoice;
    }

    public String getUpshelfType() {
        return upshelfType;
    }

    public void setUpshelfType(String upshelfType) {
        this.upshelfType = upshelfType;
    }
}
