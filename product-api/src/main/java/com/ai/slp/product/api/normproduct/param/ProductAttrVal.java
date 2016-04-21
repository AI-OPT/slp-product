package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 属性值返回参数
 * 
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class ProductAttrVal extends BaseResponse {

    /**
     * 属性值ID
     */
    private long attrvalueDefId;
    
    /**
     * 属性值名称
     */
    private String attrValueName;
    
    /**
     * 首字母
     */
    private String firstLetter;

    public long getAttrvalueDefId() {
        return attrvalueDefId;
    }

    public void setAttrvalueDefId(long attrvalueDefId) {
        this.attrvalueDefId = attrvalueDefId;
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
