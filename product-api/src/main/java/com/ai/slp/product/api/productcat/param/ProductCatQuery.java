package com.ai.slp.product.api.productcat.param;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 类目查询请求参数<br>
 *
 * Date: 2016年5月3日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author liutong5
 */
public class ProductCatQuery extends BaseInfo {
    private static final long serialVersionUID = 1L;

	/**
     * 查询内容,类目名或首字母<br>
     *
     */
    private String queryVal;

    /**
     * 父类目
     */
    private String parentProductCatId;

    public String getQueryVal() {
        return queryVal;
    }

    public void setQueryVal(String queryVal) {
        this.queryVal = queryVal;
    }

    public String getParentProductCatId() {
        return parentProductCatId;
    }

    public void setParentProductCatId(String parentProductCatId) {
        this.parentProductCatId = parentProductCatId;
    }
}
