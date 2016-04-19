package com.ai.slp.product.api.normproduct.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.normproduct.param.InvalidNormProductResponse;
import com.ai.slp.product.api.normproduct.param.NormProductRequest;
import com.ai.slp.product.api.normproduct.param.NormProductResponse;

/**
 * 标准品处理接口<br>
 *
 * Date: 2016年4月18日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public interface INormProductSV {

    /**
     * 标准品列表查询. <br>
     *
     * @param productRequest 查询条件
     * @return 符合条件的标准品信息集合
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode PRODUCT_0100
     */
    public PageInfo<NormProductResponse> queryNormProduct(NormProductRequest productRequest)
            throws BusinessException,SystemException;
    @interface QueryNormProduct {}

    /**
     * 废弃标准品列表查询. <br>
     *
     * @param productRequest 查询条件
     * @return 符合条件的废弃标准品信息集合
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode PRODUCT_0101
     */
    public PageInfo<InvalidNormProductResponse> queryInvalidProduct(NormProductRequest productRequest)
        throws BusinessException,SystemException;
    @interface QueryInvalidProduct {}

}
