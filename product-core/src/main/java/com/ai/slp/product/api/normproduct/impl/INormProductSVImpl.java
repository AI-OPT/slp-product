package com.ai.slp.product.api.normproduct.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.normproduct.interfaces.INormProductSV;
import com.ai.slp.product.api.normproduct.param.*;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * Created by jackieliu on 16/4/27.
 */
@Service
@Component
public class INormProductSVImpl implements INormProductSV {
    @Override
    public PageInfo<NormProductResponse> queryNormProduct(NormProductRequest productRequest) throws BusinessException, SystemException {
        return null;
    }

    @Override
    public PageInfo<InvalidNormProductResponse> queryInvalidProduct(NormProductRequest productRequest) throws BusinessException, SystemException {
        return null;
    }

    @Override
    public NormProductInfoResponse queryProducById(SimpleProductRequest invalidRequest) throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse saveProductInfo(NormProductSaveRequest productInfoRequest) throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse updateProductInfo(NormProductSaveRequest productInfoRequest) throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse discardProduct(SimpleProductRequest invalidRequest) throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse updateMarketPrice(MarketPrice4Update marketPrice) throws BusinessException, SystemException {
        return null;
    }
}
