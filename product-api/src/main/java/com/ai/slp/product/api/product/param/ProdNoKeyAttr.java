package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseResponse;

import java.util.List;

/**
 * 商品管理的非关键属性
 * Created by jackieliu on 16/6/13.
 */
public class ProdNoKeyAttr extends BaseResponse {
    private static final long serialVersionUID = 1L;

    /**
     * 属性(包含属性值)集合
     */
    private List<CatAttrInfoForProd> attrInfoForProdList;

    public List<CatAttrInfoForProd> getAttrInfoForProdList() {
        return attrInfoForProdList;
    }

    public void setAttrInfoForProdList(List<CatAttrInfoForProd> attrInfoForProdList) {
        this.attrInfoForProdList = attrInfoForProdList;
    }
}
