package com.ai.slp.product.api.webfront.param;

import com.ai.opt.base.vo.BaseResponse;

import java.util.Map;

/**
 * 快充返回信息
 * Created by jackieliu on 16/6/2.
 */
public class FastProductInfoRes extends BaseResponse{
    private static final long serialVersionUID = 1l;

    private Map<String,FastSkuProdInfo> nationMap;

    private Map<String,FastSkuProdInfo> localMap;

    public Map<String, FastSkuProdInfo> getNationMap() {
        return nationMap;
    }

    public void setNationMap(Map<String, FastSkuProdInfo> nationMap) {
        this.nationMap = nationMap;
    }

    public Map<String, FastSkuProdInfo> getLocalMap() {
        return localMap;
    }

    public void setLocalMap(Map<String, FastSkuProdInfo> localMap) {
        this.localMap = localMap;
    }
}
