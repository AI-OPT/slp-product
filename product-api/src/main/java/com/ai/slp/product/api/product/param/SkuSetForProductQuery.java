package com.ai.slp.product.api.product.param;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.product.interfaces.IProductSV;

/**
 * sku集合的查询对象<br>
 * 目前用于查询单个sku集合的信息
 *
 * Date: 2016年4月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class SkuSetForProductQuery extends BaseInfo {
    private static final long serialVersionUID = 1L;
	/**
     * 商城商品标识
     */
    @NotBlank(message = "商城商品标识不能为空",groups = {IProductSV.QuerySkuSetForProduct.class})
    private String prodId;

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }
}
