package com.ai.slp.product.api.productcat.param;

import javax.validation.constraints.NotNull;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.productcat.interfaces.IProductCatSV;

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
     * 类目与属性关系标识
     */
    @NotNull(message = "关系标识不能为空",groups = {IProductCatSV.DeleteProductCatAttrOrVal.class})
    private String catAttrId;
    /**
     * 商品类目标识
     */
    @NotNull(message = "类目标识不能为空",groups = {IProductCatSV.DeleteProductCatAttrOrVal.class})
    private String productCatId;
    /**
     * 属性ID
     */
    @NotNull(message = "属性标识不能为空",groups = {IProductCatSV.DeleteProductCatAttrOrVal.class})
    private long attrId;
    /**
     * 属性值ID<br>
     * 为空时,表示删除整个属性
     */
    private String attrvalueDefId;

    /**
     * 属性类型,包括 1:关键属性 2:销售属性 3:非关键属性
     */
    @NotNull(message = "属性类型不能为空",groups = {IProductCatSV.DeleteProductCatAttrOrVal.class})
    private String attrType;
    /**
     * 操作人
     */
    @NotNull(message = "操作人标识不能为空",groups = {IProductCatSV.DeleteProductCatAttrOrVal.class})
    private long operId;

    public String getCatAttrId() {
        return catAttrId;
    }

    public void setCatAttrId(String catAttrId) {
        this.catAttrId = catAttrId;
    }

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

    public long getAttrId() {
        return attrId;
    }

    public void setAttrId(long attrId) {
        this.attrId = attrId;
    }

    public String getAttrvalueDefId() {
        return attrvalueDefId;
    }

    public void setAttrvalueDefId(String attrvalueDefId) {
        this.attrvalueDefId = attrvalueDefId;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public long getOperId() {
        return operId;
    }

    public void setOperId(long operId) {
        this.operId = operId;
    }
}
