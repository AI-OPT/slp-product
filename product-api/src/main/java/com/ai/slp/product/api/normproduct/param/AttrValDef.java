package com.ai.slp.product.api.normproduct.param;

/**
 * 查询属性和属性值的MAP集合的返回属性值类型
 * 
 * Date: 2016年5月3日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class AttrValDef {
    
    /**
     *租户ID
     */
    private String tenantId;
    
    /**
     *属性值标识
     */
    private String attrvalueDefId;
    
    /**
     *属性标识
     */
    private Long attrId;
    
    /**
     *属性值名字
     */
    private String attrValueName;
    
    /**
     *属性值首字母
     */
    private String firstLetter;
    
    public String getTenantId() {
        return tenantId;
    }
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
    public String getAttrvalueDefId() {
        return attrvalueDefId;
    }
    public void setAttrvalueDefId(String attrvalueDefId) {
        this.attrvalueDefId = attrvalueDefId;
    }
    public Long getAttrId() {
        return attrId;
    }
    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }
    public String getAttrValueName() {
        return attrValueName;
    }
    public void setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName;
    }
    public String getFirstLetter() {
        return firstLetter;
    }
    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }
    
    
}
