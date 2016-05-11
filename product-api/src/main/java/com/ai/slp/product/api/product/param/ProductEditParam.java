package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseInfo;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 商品管理待编辑查询参数
 * 
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public class ProductEditParam extends BaseInfo{
    private static final long serialVersionUID = 1L;

	/**
     * 请求查询的页码
     * 默认为1
     */
    private Integer pageNo = 1;

    /**
     * 每页显示条数,默认每页20条
     */
    private Integer pageSize =30;
    /**
     * 商品名称
     */
    private String prodName;
    /**
     * 商品ID
     */
    private String prodId;
    /**
     * 商品类目ID,必填
     */
    @NotBlank(message = "类目标识不能为空")
    private String productCatId;
    /**
     * 商品类型
     */
    private String productType;

    /**
     * 最高销售价
     */
    private Long highSalePrice;
    
    /**
     * 最低销售价
     */
    private Long lowSalePrice;
    /**
     * 状态
     * 0
     * 1未编辑2已编辑
     * 3审核中4审核未通过
     * 5在售
     * 6仓库中（审核通过放入） 61售罄下架62废弃下架63自动下架
     * 7停用8废弃
     */
    private String state;

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Long getHighSalePrice() {
        return highSalePrice;
    }

    public void setHighSalePrice(Long highSalePrice) {
        this.highSalePrice = highSalePrice;
    }

    public Long getLowSalePrice() {
        return lowSalePrice;
    }

    public void setLowSalePrice(Long lowSalePrice) {
        this.lowSalePrice = lowSalePrice;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
