package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseResponse;

import java.util.List;
import java.util.Map;

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

    /**
     * 属性对应属性值集合
     * K:属性标识,V:属性值集合
     */
    private Map<Long,List<ProdAttrValInfo>> attrValMap;

    public List<CatAttrInfoForProd> getAttrInfoForProdList() {
        return attrInfoForProdList;
    }

    public void setAttrInfoForProdList(List<CatAttrInfoForProd> attrInfoForProdList) {
        this.attrInfoForProdList = attrInfoForProdList;
    }

    public Map<Long, List<ProdAttrValInfo>> getAttrValMap() {
        return attrValMap;
    }

    public void setAttrValMap(Map<Long, List<ProdAttrValInfo>> attrValMap) {
        this.attrValMap = attrValMap;
    }
}
