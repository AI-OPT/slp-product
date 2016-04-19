package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.normproduct.interfaces.INormProductSV;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 标准品信息的基础信息<br>
 *
 * Date: 2016年4月18日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public abstract class NormProductBase extends BaseInfo {
    /**
     * 类目ID
     */
    @NotNull(message = "类目ID不能为空", groups = { INormProductSV.QueryNormProduct.class })
    private Long catId;

    /**
     * 标准品ID
     */
    private String productId;

    /**
     * 标准品名称
     */
    private String productName;

    /**
     * 标准品状态
     * 0:全部;1可上架;2不可上架;3待处理;4废弃
     */
    @NotNull(message = "标准品状态不能为空", groups = { INormProductSV.QueryInvalidProduct.class })
    private String productStatus;

    /**
     * 标准品类型
     * 0:全部;1实物;2虚拟
     */
    private String productType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人ID
     */
    private Long createUserId;

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
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

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getcreateUserId() {
        return createUserId;
    }

    public void setcreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
}
