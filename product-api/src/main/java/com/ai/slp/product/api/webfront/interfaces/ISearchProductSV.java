package com.ai.slp.product.api.webfront.interfaces;


import java.util.List;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.slp.product.api.webfront.param.ProductData;
import com.ai.slp.product.api.webfront.param.ProductQueryRequest;
import com.ai.slp.product.api.webfront.param.ProductQueryResponse;

/**
 * web商城---查询商品结果页面
 * Date: 2016年5月24日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author zhanglh
 */
public interface ISearchProductSV {
    /**
     * 查询商品
     * @param request
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhanglh
     * @ApiCode
     */
    ProductQueryResponse queryProductPage(ProductQueryRequest request) throws BusinessException, SystemException;
    
    /**
     * 查询热销推荐产品
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhanglh
     * @ApiCode
     */
    List<ProductData> queryHotSellProduct(ProductQueryRequest request) throws BusinessException, SystemException;
}
