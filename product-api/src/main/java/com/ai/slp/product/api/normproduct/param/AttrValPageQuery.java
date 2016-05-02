package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.normproduct.interfaces.IAttrAndValDefSV;

import javax.validation.constraints.NotNull;

/**
 * 属性值分页查询请求参数<br>
 *
 * Date: 2016年5月2日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author liutong5
 */
public class AttrValPageQuery extends BaseInfo{
    /**
     * 属性值ID
     */
    private Long attrvalueDefId;

    /**
     * 属性值名称
     */
    private String attrValueName;

    public Long getAttrvalueDefId() {
        return attrvalueDefId;
    }

    public void setAttrvalueDefId(Long attrvalueDefId) {
        this.attrvalueDefId = attrvalueDefId;
    }

    public String getAttrValueName() {
        return attrValueName;
    }

    public void setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName;
    }
}
