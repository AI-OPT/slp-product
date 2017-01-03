package com.ai.slp.product.api.product.param;

import java.io.Serializable;

/**
 * 商品信息返回数据,包括路由组信息
 *
 * Date: 2016年9月2日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author liutong5
 */
public class ProductRouteGroupInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 销售商品标识
     */
    private String productId;

    /**
     * 销售商品名称
     */
    private String productName;

    /**
     * 标准品标识
     */
    private String standedProdId;

    /**
     * 标准品名称
     */
    private String standedProdName;

    /**
     * 配货组标识
     */
    private String routeGroupId;

    /**
     * 商品状态
     */
    private String state;
    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

    public String getStandedProdId() {
        return standedProdId;
    }

    public void setStandedProdId(String standedProdId) {
        this.standedProdId = standedProdId;
    }

    public String getStandedProdName() {
        return standedProdName;
    }

    public void setStandedProdName(String standedProdName) {
        this.standedProdName = standedProdName;
    }

    public String getRouteGroupId() {
        return routeGroupId;
    }

    public void setRouteGroupId(String routeGroupId) {
        this.routeGroupId = routeGroupId;
    }
}
