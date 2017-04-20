package com.ai.slp.product.api.normproduct.param;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.normproduct.interfaces.INormProductSV;

/**
 * 标准品将保存信息<br>
 *
 * Date: 2016年4月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class NormProdSaveRequest extends BaseInfo {
    private static final long serialVersionUID = 1L;

	/**
     * 类目ID,必填.
     */
/*    @NotBlank(message = "类目ID不能为空",
            groups = {INormProductSV.SaveProductInfo.class
                    ,INormProductSV.UpdateProductInfo.class
                    ,INormProductSV.UpdateProductAndStoGroup.class
                    ,INormProductSV.CreateProductAndStoGroup.class
            })*/
    private String productCatId;

    /**
     * 标准品ID,更新时必填
     */
    @NotBlank(message = "标准品ID不能为空",
            groups = { INormProductSV.UpdateProductInfo.class
            		,INormProductSV.UpdateProductAndStoGroup.class})
    private String productId;

    /**
     * 标准品名称,必填
     */
    @NotBlank(message = "标准品名称不能为空",
            groups = { INormProductSV.SaveProductInfo.class
                    ,INormProductSV.UpdateProductInfo.class
                    ,INormProductSV.UpdateProductAndStoGroup.class
                    ,INormProductSV.CreateProductAndStoGroup.class})
    private String productName;

    /**
     * 标准品状态,必填<br>
     *     1可使用;2不可使用
     */
    @NotBlank(message = "标准品状态不能为空",
            groups = { INormProductSV.SaveProductInfo.class
                    ,INormProductSV.UpdateProductInfo.class
                    ,INormProductSV.UpdateProductAndStoGroup.class
                    ,INormProductSV.CreateProductAndStoGroup.class
            })
    private String state;

    /**
     * 标准品类型,必填<br>
     * 1实物;2虚拟
     */
    @NotBlank(message = "标准品类型不能为空",
            groups = { INormProductSV.SaveProductInfo.class,
                    INormProductSV.UpdateProductInfo.class
                    ,INormProductSV.UpdateProductAndStoGroup.class
                    ,INormProductSV.CreateProductAndStoGroup.class})
    private String productType;
    /**
     * 创建人ID,添加时必填<br>
     */
    @NotNull(message = "创建人ID不能为空",
            groups = { INormProductSV.SaveProductInfo.class
            		,INormProductSV.CreateProductAndStoGroup.class})
    private Long createId;

    /**
     * 操作人ID,更新时必填<br>
     */
    @NotNull(message = "创建人ID不能为空",
            groups = { INormProductSV.UpdateProductInfo.class
            		,INormProductSV.UpdateProductAndStoGroup.class})
    private Long operId;
    /**
     * 标准品属性值集合
     */
    private List<AttrValRequest> attrValList;
    /**
     * (新增字段)
     * 商户ID--(-1:自运营)
     */
    @NotBlank(message = "商户ID不能为空",
            groups = { INormProductSV.SaveProductInfo.class,
                    INormProductSV.UpdateProductInfo.class
                    ,INormProductSV.UpdateProductAndStoGroup.class
                    ,INormProductSV.CreateProductAndStoGroup.class})
    private String supplierId;
    

    public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

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

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public List<AttrValRequest> getAttrValList() {
        return attrValList;
    }

    public void setAttrValList(List<AttrValRequest> attrValList) {
        this.attrValList = attrValList;
    }
}
