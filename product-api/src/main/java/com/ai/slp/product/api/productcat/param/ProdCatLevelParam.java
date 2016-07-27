package com.ai.slp.product.api.productcat.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.productcat.interfaces.IProductCatCacheSV;

import javax.validation.constraints.NotNull;

/**
 * 类目级别查询请求信息<br>
 *
 * Date: 2016年07月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class ProdCatLevelParam extends BaseInfo {

    /**
     * 类目级别,必填
     */
    @NotNull(message = "类目级别不能为空",groups = {
        IProductCatCacheSV.QueryByLevel.class
    })
    private Short catLevel;

    public Short getCatLevel() {
        return catLevel;
    }

    public void setCatLevel(Short catLevel) {
        this.catLevel = catLevel;
    }
}
