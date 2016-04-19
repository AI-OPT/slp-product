package com.ai.slp.product.api.normproduct.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.normproduct.param.*;

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

    /**
     * 查询指定标准品标识的标准品信息. <br>
     *
     * @param invalidRequest 标准品查询条件
     * @return 标准品详细信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode PRODUCT_0102
     */
    public NormProductInfoResponse queryProducById(SimpleProductRequest invalidRequest)
            throws BusinessException,SystemException;
    @interface QueryProducById {}

    /**
     * 添加标准品信息. <br>
     *
     * @param productInfoRequest 标准品信息
     * @return 标准品保存结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode PRODUCT_0103
     */
    public BaseResponse saveProductInfo(SaveProductInfoRequest productInfoRequest)
            throws BusinessException,SystemException;
    @interface SaveProductInfo {}

    /**
     * 更新标准品信息. <br>
     *
     * @param productInfoRequest 标准品信息
     * @return 标准品更新结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode PRODUCT_0104
     */
    public BaseResponse updateProductInfo(SaveProductInfoRequest productInfoRequest)
            throws BusinessException,SystemException;
    @interface UpdateProductInfo {}

    /**
     * 废弃标准品. <br>
     *
     * @param invalidRequest 标准品废弃请求参数
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode PRODUCT_0105
     */
    public BaseResponse discardProduct(SimpleProductRequest invalidRequest) throws BusinessException,SystemException;
    @interface DiscardProduct {}
}
