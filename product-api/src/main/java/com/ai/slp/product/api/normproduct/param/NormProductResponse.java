package com.ai.slp.product.api.normproduct.param;

/**
 * 标准品查询返回信息<br>
 *
 * Date: 2016年4月18日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class NormProductResponse extends NormProductBase {

    /**
     * 类目名称
     */
    private String catName;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 规格型号
     */
    private String model;

    /**
     * 创建者名称
     */
    private String createUserName;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
