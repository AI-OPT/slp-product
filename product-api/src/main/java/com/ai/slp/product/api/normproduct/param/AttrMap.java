package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseResponse;

import java.util.List;
import java.util.Map;

/**
 * 标准品指定类型属性和属性值返回结果
 *
 * Date: 2016年5月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author liutong5
 */
public class AttrMap extends BaseResponse {
    private static final long serialVersionUID = 1L;
	/**
     * 属性与属性值的对应关系
     */
    public Map<Long,List<Long>> attrAndVal;
    /**
     * 属性对象集合<br>
     *     K:属性标识;V:属性对象
     */
    public Map<Long,ProdCatAttrInfo> attrDefMap;
    /**
     * 属性值对象集合<br>
     *     K:属性值标识;V:属性值对象
     */
    public Map<Long,AttrValInfo> attrValDefMap;

    public Map<Long, List<Long>> getAttrAndVal() {
        return attrAndVal;
    }

    public void setAttrAndVal(Map<Long, List<Long>> attrAndVal) {
        this.attrAndVal = attrAndVal;
    }

    public Map<Long, ProdCatAttrInfo> getAttrDefMap() {
        return attrDefMap;
    }

    public void setAttrDefMap(Map<Long, ProdCatAttrInfo> attrDefMap) {
        this.attrDefMap = attrDefMap;
    }

    public Map<Long, AttrValInfo> getAttrValDefMap() {
        return attrValDefMap;
    }

    public void setAttrValDefMap(Map<Long, AttrValInfo> attrValDefMap) {
        this.attrValDefMap = attrValDefMap;
    }
}
