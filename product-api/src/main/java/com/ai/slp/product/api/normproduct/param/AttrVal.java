package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 单个属性值查询返回类
 * 
 * Date: 2016年5月4日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class AttrVal extends BaseResponse{
    
    /**
     * 属性值名称
     */
    private String attrValueName;
    
    /**
     * 属性值首字母
     */
    private String firstLetter;

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
