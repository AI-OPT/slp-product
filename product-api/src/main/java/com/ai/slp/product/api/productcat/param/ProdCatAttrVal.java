package com.ai.slp.product.api.productcat.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.productcat.interfaces.IProductCatSV;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 类目属性值对象<br>
 *
 *
 * Date: 2016年5月3日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author liutong5
 */
public class ProdCatAttrVal extends BaseInfo{
    private static final long serialVersionUID = 1L;
	/**
     * 类目与属性关联标识/类目与属性值的关联标识
     */
    @NotBlank(message = "标识不能为空",groups = {IProductCatSV.DeleteProductCatAttrOrVal.class})
    private String id;
    /**
     * 商品类目标识
     */
    @NotBlank(message = "类目标识不能为空",groups = {IProductCatSV.DeleteProductCatAttrOrVal.class})
    private String productCatId;

    /**
     * 数据类型,必填<br>
     * 1:属性;2:属性值
     */
    @NotBlank(message = "数据的类型不能为空",groups = {IProductCatSV.DeleteProductCatAttrOrVal.class})
    private String objType;

    /**
     * 操作人
     */
    @NotNull(message = "操作人标识不能为空",groups = {IProductCatSV.DeleteProductCatAttrOrVal.class})
    private Long operId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public Long getOperId() {
        return operId;
    }
}
