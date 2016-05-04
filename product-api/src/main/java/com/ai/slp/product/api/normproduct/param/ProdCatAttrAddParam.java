package com.ai.slp.product.api.normproduct.param;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Set;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.normproduct.interfaces.IProductCatSV;

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
    @NotNull(message = "商品类目标识不能为空",groups = {IProductCatSV.AddAttrForCatAndType.class})
    private String productCatId;
    /**
     * 属性类型,包括关键属性 非关键属性 销售属性
     */
    @NotNull(message = "属性类型不能为空",groups = {IProductCatSV.AddAttrForCatAndType.class})
    private String attrType;
    /**
     * 操作人ID
     */
    @NotNull(message = "操作人标识不能为空",groups = {IProductCatSV.AddAttrForCatAndType.class})
    private long operId;
    
    /**
     * 操作时间
     */
    private Timestamp operTime;
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

    public long getOperId() {
        return operId;
    }

    public void setOperId(long operId) {
        this.operId = operId;
    }

    public Timestamp getOperTime() {
        return operTime;
    }

    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
    }

    public Map<Long, Set<String>> getAttrAndVal() {
        return attrAndVal;
    }

    public void setAttrAndVal(Map<Long, Set<String>> attrAndVal) {
        this.attrAndVal = attrAndVal;
    }
}
