package com.ai.slp.product.api.product.param;

/**
 * 商品审核参数
 * 
 * Date: 2016年4月26日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class ProductCheckParam extends ProductBase {
    
    /**
     * 商品ID
     */
    private String prodId;
    
    /**
     * 上架类型 1审核通过后立即上架 2审核通过后放入仓库 3定时上架
     */
    private String upshelfType;

    /**
     * 拒绝原因-被拒绝参数
     */
    private String refuseReason;
    
    /**
     * 拒绝描述-被拒绝参数
     */
    private String refuseDes;
}
