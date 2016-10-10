package com.ai.slp.product.api.productserver.param;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 商品查询
 *
 * Date: 2016年10月10日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class ProdInfoQuery extends BaseInfo {
    private static final long serialVersionUID = 1L;
	/**
     * 查询标识,可以是商品编码,skuId,商品标识<br>
     * 必填<br>
     * 根据接口传入所需信息
     */
    @NotBlank(message = "标识不能为空")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
