package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.normproduct.interfaces.INormProductSV;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 标准品信息的基础信息<br>
 *
 * Date: 2016年4月18日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public abstract class NormProductBase extends BaseInfo {
    /**
     * 类目ID,不能为空
     */
    @NotBlank(message = "类目ID不能为空",
            groups = { INormProductSV.QueryNormProduct.class
                    ,INormProductSV.SaveProductInfo.class
                    ,INormProductSV.UpdateProductInfo.class
            })
    private String productCatId;

    /**
     * 标准品ID
     */
    @NotBlank(message = "标准品ID不能为空",
            groups = { INormProductSV.UpdateProductInfo.class })
    private String productId;

    /**
     * 标准品名称
     */
    @NotBlank(message = "类目ID不能为空",
            groups = { INormProductSV.SaveProductInfo.class
                    ,INormProductSV.UpdateProductInfo.class })
    private String productName;

    /**
     * 标准品状态<br>
     * 添加/更新信息时,不能为NULL<br>
     * NULL:全部;1可上架;2不可上架;3待处理;4废弃
     */
    @NotBlank(message = "标准品状态不能为空",
            groups = { INormProductSV.SaveProductInfo.class
                    ,INormProductSV.UpdateProductInfo.class
            })
    private String state;

    /**
     * 标准品类型<br>
     * 添加/更新信息时,不能为NULL<br>
     * NULL:全部;1实物;2虚拟
     */
    @NotBlank(message = "标准品类型不能为空",
            groups = { INormProductSV.SaveProductInfo.class,
                    INormProductSV.UpdateProductInfo.class })
    private String productType;

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

}
