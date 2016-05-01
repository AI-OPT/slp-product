package com.ai.slp.product.api.normproduct.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
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
     * @ApiCode NORM_PRODUCT_0100
     */
    public PageInfoWrapper<NormProductResponse> queryNormProduct(NormProductRequest productRequest)
            throws BusinessException,SystemException;
    @interface QueryNormProduct {}

    /**
     * 查询指定标准品标识的标准品信息. <br>
     *
     * @param invalidRequest 标准品查询条件
     * @return 标准品详细信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode NORM_PRODUCT_0102
     */
    public NormProductInfoResponse queryProducById(NormProductUniqueReq invalidRequest)
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
     * @ApiCode NORM_PRODUCT_0103
     */
    public BaseResponse createProductInfo(NormProductSaveRequest productInfoRequest)
            throws BusinessException,SystemException;
    @interface SaveProductInfo {}

    /**
     * 更新标准品信息. <br>
     * 不允许变更为废弃状态,要进行废弃操作,请使用废弃接口.
     *
     * @param productInfoRequest 标准品信息
     * @return 标准品更新结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode NORM_PRODUCT_0104
     */
    public BaseResponse updateProductInfo(NormProductSaveRequest productInfoRequest)
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
     * @ApiCode NORM_PRODUCT_0105
     */
    public BaseResponse discardProduct(NormProductUniqueReq invalidRequest)
            throws BusinessException,SystemException;
    @interface DiscardProduct {}

    /**
     * 更新标准品市场价. <br>
     *
     * @param marketPrice 标准品市场价
     * @return 更新结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode NORM_PRODUCT_0106
     */
    public BaseResponse updateMarketPrice(MarketPrice4Update marketPrice)
            throws BusinessException,SystemException;
    @interface UpdateMarketPrice{}
}
