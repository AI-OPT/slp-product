package com.ai.slp.product.api.normproduct.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.slp.product.api.normproduct.interfaces.INormProductSV;
import com.ai.slp.product.api.normproduct.param.*;
import com.ai.slp.product.service.business.interfaces.INormProductBusiSV;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 标准品接口
 * Created by jackieliu on 16/4/27.
 */
@Service(validation = "true")
@Component
public class INormProductSVImpl implements INormProductSV {
    private static Logger logger = LoggerFactory.getLogger(INormProductSVImpl.class);
    //标准品处理对象
    @Autowired
    INormProductBusiSV normProductBusiSV;

    @Override
    public PageInfo<NormProductResponse> queryNormProduct(NormProductRequest productRequest) throws BusinessException, SystemException {
        return normProductBusiSV.queryForPage(productRequest);
    }

    @Override
    public NormProductInfoResponse queryProducById(SimpleProductRequest invalidRequest) throws BusinessException, SystemException {
        return normProductBusiSV.queryById(invalidRequest.getTenantId(),invalidRequest.getProductId());
    }

    @Override
    public BaseResponse createProductInfo(NormProductSaveRequest productInfoRequest) throws BusinessException, SystemException {
        normProductBusiSV.installNormProd(productInfoRequest);
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    @Override
    public BaseResponse updateProductInfo(NormProductSaveRequest productInfoRequest) throws BusinessException, SystemException {
        if (StringUtils.isBlank(productInfoRequest.getTenantId())
                || StringUtils.isBlank(productInfoRequest.getProductId()))
            throw new BusinessException("","租户标识和标准品标识均不能为空");
        normProductBusiSV.updateNormProd(productInfoRequest);
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    @Override
    public BaseResponse discardProduct(SimpleProductRequest invalidRequest) throws BusinessException, SystemException {
        normProductBusiSV.discardProduct(
                invalidRequest.getTenantId(),invalidRequest.getProductId(),
                invalidRequest.getOperId(),invalidRequest.getOperTime());
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    @Override
    public BaseResponse updateMarketPrice(MarketPrice4Update marketPrice) throws BusinessException, SystemException {
        return null;
    }
}
