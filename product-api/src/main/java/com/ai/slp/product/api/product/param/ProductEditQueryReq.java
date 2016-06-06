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
public class ProductEditQueryReq extends BaseInfo{
    private static final long serialVersionUID = 1L;

	/**
     * 请求查询的页码
     * 默认为1
     */
    private Integer pageNo = 1;

    /**
     * 每页显示条数,默认每页30条
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
     * 商品类型<br>
     * null:全部、1实物、2虚拟
     */
    private String productType;

    /**
     * 状态
     * 0:新增
     * 1:未编辑;2:已编辑
     * 3:审核中;4:审核未通过
     * 5:在售
     * 6:仓库中（审核通过放入） 61:售罄下架 62:废弃下架
     * 7:废弃
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
