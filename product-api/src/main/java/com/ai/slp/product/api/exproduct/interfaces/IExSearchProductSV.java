package com.ai.slp.product.api.exproduct.interfaces;


import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.slp.product.api.exproduct.param.QueryProductRequest;
import com.ai.slp.product.api.exproduct.param.QueryProductResponse;

/**
 * 提供给下游用户的查询商品服务
 * Date: 2016年6月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author zhanglh
 */
public interface IExSearchProductSV {
    /**
     * 查询商品
     * @param request
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhanglh
     * @ApiCode EX_PROD_0001
     */
    QueryProductResponse queryProductPage(QueryProductRequest request) throws BusinessException, SystemException;
    
}
