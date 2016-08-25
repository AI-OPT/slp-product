package com.ai.slp.product.api.normproduct.param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 标准品返回信息,包括关键属性及属性值<br>
 *
 * Date: 2016年8月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class NormProdAndKeyAttrRes implements Serializable {
    /**
     * 租户Id
     */
    private String tenantId;
    /**
     * 商户ID--(-1:自运营)
     */
    private String supplierId;
    /**
     * 类目ID<br>
     */
    private String productCatId;
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
     * 0:废弃;1:可使用;2:不可使用
     */
    private String state;
    /**
     * 标准品类型
     * 1实物;2虚拟
     */
    private String productType;

    /**
     * 属性与属性值集合
     */
    private Map<Long,List<AttrValInfo>> keyAttrMap;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

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

    public Map<Long, List<AttrValInfo>> getKeyAttrMap() {
        return keyAttrMap;
    }

    public void setKeyAttrMap(Map<Long, List<AttrValInfo>> keyAttrMap) {
        this.keyAttrMap = keyAttrMap;
    }
}
