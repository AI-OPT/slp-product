package com.ai.slp.product.api.webfront.interfaces;

import java.util.HashMap;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.slp.product.api.webfront.param.ProductHomeRequest;
import com.ai.slp.product.api.webfront.param.ProductHomeResponse;
import com.ai.slp.product.api.webfront.param.ProductHomeRecommendResponse;

/**
 * 商城获取商品信息接口 Date: 2016年5月24日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author zhangqiang7
 */
public interface IHomeProductSV {
    @interface QueryDataProduct {
    }

    /**
     * 获取流量商品
     * 
     * @param queryProductRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhangqiang7
     * @ApiCode PRODUCT_0001
     */
    public ProductHomeResponse queryDataProduct(ProductHomeRequest productHomeRequest)
            throws BusinessException, SystemException;

    @interface QueryFlowProduct {
    }
    
    /**
     * 获取话费商品
     * 
     * @param queryProductRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhangqiang7
     * @ApiCode PRODUCT_0002
     */
    public ProductHomeResponse queryFlowProduct(ProductHomeRequest productHomeRequest)
            throws BusinessException, SystemException;
    
    @interface QueryProductCat {
    }

    /**
     * 获取在售充值商品种类
     * 
     * @param queryProductCatRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhangqiang7
     * @ApiCode PRODUCT_0003
     */
    public HashMap<String, String> queryProductCat(ProductHomeRequest productHomeRequest)
            throws BusinessException, SystemException;

    @interface QueryRecommendProduct{}
    
    /**
     * 获取推荐商品
     * @param queryProductRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhangqiang7
     * @ApiCode PRODUCT_0004
     */
    public ProductHomeRecommendResponse queryRecommendProduct(ProductHomeRequest productHomeRequest)
            throws BusinessException, SystemException;

}
