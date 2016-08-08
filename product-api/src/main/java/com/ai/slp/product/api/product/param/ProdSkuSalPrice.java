package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.product.interfaces.IProductSV;

import javax.validation.constraints.NotNull;

/**
 * SKU销售价对称<br>
 *
 *
 * Date: 2016年4月24日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class ProdSkuSalPrice extends BaseInfo {
    private static final long serialVersionUID = 1L;
	/**
     * sku单品标识
     */
    @NotNull(message = "SKU单品标识不能为空",
            groups = { IProductSV.UpdateMultSKUSalePrice.class })
    private String skuId;
    /**
     * 销售价
     */
    private Long salePrice;
    /**
     * 操作人ID
     */
    @NotNull(message = "操作人不能为空",
            groups = { IProductSV.UpdateMultSKUSalePrice.class })
    private Long operId;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }
}
