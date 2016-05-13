package com.ai.slp.product.api.productcat.param;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 商品类目分页查询请求参数<br>
 *
 * Date: 2016年4月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author liutong5
 */
public class ProductCatPageQuery extends BaseInfo {
    private static final long serialVersionUID = 1L;

	/**
     * 请求查询的页码
     * 默认为1
     */
    private Integer pageNo = 1;

    /**
     * 每页显示条数
     */
    private Integer pageSize =20;

    /**
     * 父类目
     */
    private String parentProductCatId;

    /**
     * 商品类目ID
     */
    private String productCatId;

    /**
     * 商品类目名称
     */
    private String productCatName;

    /**
     * 是否有子分类
     */
    private String isChild;

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

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

    public String getProductCatName() {
        return productCatName;
    }

    public void setProductCatName(String productCatName) {
        this.productCatName = productCatName;
    }

    public String getIsChild() {
        return isChild;
    }

    public void setIsChild(String isChild) {
        this.isChild = isChild;
    }

    public String getParentProductCatId() {
        return parentProductCatId;
    }

    public void setParentProductCatId(String parentProductCatId) {
        this.parentProductCatId = parentProductCatId;
    }
}
