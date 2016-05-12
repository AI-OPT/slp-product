package com.ai.slp.product.api.normproduct.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.slp.product.api.normproduct.interfaces.INormProductSV;
import com.ai.slp.product.api.normproduct.param.*;
import com.ai.slp.product.service.business.interfaces.INormProductBusiSV;
import com.ai.slp.product.util.CommonCheckUtils;
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
    public PageInfoResponse<NormProdResponse> queryNormProduct(NormProdRequest productRequest) throws BusinessException, SystemException {
        return normProductBusiSV.queryForPage(productRequest);
    }

    @Override
    public NormProdInfoResponse queryProducById(NormProdUniqueReq invalidRequest) throws BusinessException, SystemException {
        return normProductBusiSV.queryById(invalidRequest.getTenantId(),invalidRequest.getProductId());
    }

    @Override
    public BaseResponse createProductInfo(NormProdSaveRequest productInfoRequest) throws BusinessException, SystemException {
        normProductBusiSV.installNormProd(productInfoRequest);
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    @Override
    public BaseResponse updateProductInfo(NormProdSaveRequest productInfoRequest) throws BusinessException, SystemException {
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
    public BaseResponse discardProduct(NormProdUniqueReq invalidRequest) throws BusinessException, SystemException {
        normProductBusiSV.discardProduct(
                invalidRequest.getTenantId(),invalidRequest.getProductId(),
                invalidRequest.getOperId());
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    @Override
    public BaseResponse updateMarketPrice(MarketPriceUpdate marketPrice) throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(marketPrice.getTenantId(),"");
        normProductBusiSV.updateMarketPrice(marketPrice);
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    /**
     * 查询指定标准品下某种类型的属性集合<br>
     * 类型分为:关键属性,销售属性
     *
     * @param attrQuery 查询标准品信息
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode ATTR_VAL_0210
     */
    @Override
    public AttrMap queryAttrByNormProduct(AttrQuery attrQuery) throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(attrQuery.getTenantId(),"");
        return normProductBusiSV.queryAttrOfProduct(attrQuery.getTenantId(),attrQuery.getProductId(),attrQuery.getAttrType());
    }

    /**
     * 制定商品销售价中标准品列表查询.<br>
     *
     * @param productRequest 查询标准品信息
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode ATTR_VAL_0211
     */
    @Override
    public PageInfoResponse<NormProdResponse> queryNormProductForSalePrice(NormProdRequest productRequest) throws BusinessException, SystemException {
    	return  normProductBusiSV.queryNormProductForSalePrice(productRequest);
    }
}
