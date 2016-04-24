package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.normproduct.param.AttrDefResponse;
import com.ai.slp.product.api.normproduct.param.NormProductAttrValResponse;

import java.util.List;
import java.util.Map;

/**
 * 商城商品详情中对象<br>
 *
 *
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class ProductInfo extends BaseInfo {
    /**
     * 商品标识
     */
    private String productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品类型
     */
    private String productType;

    /**
     * 关键属性集合,包括属性对应标准品属性值集合
     */
    private Map<AttrDefResponse,List<NormProductAttrValResponse>> keyAttrs;

    /**
     * 销售属性集合,包括属性对应标准品属性值集合
     */
    private Map<AttrDefResponse,List<NormProductAttrValResponse>> saleAttrs;

}
