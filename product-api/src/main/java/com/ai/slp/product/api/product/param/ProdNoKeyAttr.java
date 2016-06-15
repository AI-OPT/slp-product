package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseResponse;

import java.util.List;
import java.util.Map;

/**
 * 商品管理的非关键属性
 * Created by jackieliu on 16/6/13.
 */
public class ProdNoKeyAttr extends BaseResponse {
    /**
     * 销售商品的关键属性集合
     */
    private Map<CatAttrInfoForProd,List<ProdAttrValInfo>> attrMap;

    public Map<CatAttrInfoForProd, List<ProdAttrValInfo>> getAttrMap() {
        return attrMap;
    }

    public void setAttrMap(Map<CatAttrInfoForProd, List<ProdAttrValInfo>> attrMap) {
        this.attrMap = attrMap;
    }
}
