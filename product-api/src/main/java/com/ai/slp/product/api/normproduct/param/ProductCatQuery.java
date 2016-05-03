package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.normproduct.interfaces.IProductCatSV;

import javax.validation.constraints.NotNull;

/**
 * 类目查询请求参数<br>
 *
 * Date: 2016年5月3日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author liutong5
 */
public class ProductCatQuery extends BaseInfo {
    /**
     * 查询内容,类目名或首字母<br>
     *
     */
    private String queryVal;

    /**
     * 父类目
     */
    private Long parentProductCatId;

    public String getQueryVal() {
        return queryVal;
    }

    public void setQueryVal(String queryVal) {
        this.queryVal = queryVal;
    }

    public Long getParentProductCatId() {
        return parentProductCatId;
    }

    public void setParentProductCatId(Long parentProductCatId) {
        this.parentProductCatId = parentProductCatId;
    }
}
