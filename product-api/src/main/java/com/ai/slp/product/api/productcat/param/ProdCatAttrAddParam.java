package com.ai.slp.product.api.productcat.param;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Set;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.productcat.interfaces.IProductCatSV;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 增加类目的类目属性参数
 * 
 * Date: 2016年4月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 * update liutong5 at 2016/05/04
 */
public class ProdCatAttrAddParam extends BaseInfo{
    /**
     * 商品类目ID
     */
    @NotBlank(message = "商品类目标识不能为空",groups = {IProductCatSV.AddAttrForCatAndType.class})
    private String productCatId;
    /**
     * 属性类型,包括关键属性 非关键属性 销售属性
     */
    @NotBlank(message = "属性类型不能为空",groups = {IProductCatSV.AddAttrForCatAndType.class})
    private String attrType;
    /**
     * 操作人ID
     */
    @NotBlank(message = "操作人标识不能为空",groups = {IProductCatSV.AddAttrForCatAndType.class})
    private Long operId;

    /**
     * 属性及对应属性值<br>
     * K:属性标识,V:属性值标识的集合
     */
    private Map<Long,Set<String>> attrAndVal;

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public Map<Long, Set<String>> getAttrAndVal() {
        return attrAndVal;
    }

    public void setAttrAndVal(Map<Long, Set<String>> attrAndVal) {
        this.attrAndVal = attrAndVal;
    }
}
