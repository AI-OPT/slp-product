package com.ai.slp.product.api.normproduct.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.slp.product.api.normproduct.interfaces.INormProductSV;
import com.ai.slp.product.api.normproduct.param.*;
import com.ai.slp.product.api.storage.param.STOStorageGroup;
import com.ai.slp.product.service.business.interfaces.INormProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageGroupBusiSV;
import com.ai.slp.product.util.CommonUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 标准品接口
 * Created by jackieliu on 16/4/27.
 */
@Service(validation = "true")
@Component
public class INormProductSVImpl implements INormProductSV {
    //标准品处理对象
    @Autowired
    INormProductBusiSV normProductBusiSV;
    @Autowired
    IStorageGroupBusiSV storageGroupBusiSV;
    /**
     * 标准品列表查询. <br>
     *
     * @param productRequest 查询条件
     * @return 符合条件的标准品信息集合
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     */
    @Override
    public PageInfoResponse<NormProdResponse> queryNormProduct(NormProdRequest productRequest) throws BusinessException, SystemException {
        return normProductBusiSV.queryForPage(productRequest);
    }

    /**
     * 查询指定标准品标识的标准品信息. <br>
     *
     * @param invalidRequest 标准品查询条件
     * @return 标准品详细信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     */
    @Override
    public NormProdInfoResponse queryProducById(NormProdUniqueReq invalidRequest) throws BusinessException, SystemException {
        return normProductBusiSV.queryById(invalidRequest.getTenantId(),invalidRequest.getProductId());
    }

    /**
     * 添加标准品信息. <br>
     *
     * @param request 标准品信息
     * @return 标准品保存结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     */
    @Override
    public BaseResponse createProductInfo(NormProdSaveRequest request) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(request.getTenantId());
        String normProdId = normProductBusiSV.installNormProd(request);
        //自动添加一个库存组
        STOStorageGroup storageGroup = new STOStorageGroup();
        storageGroup.setTenantId(request.getTenantId());
        storageGroup.setCreateId(request.getOperId());
        storageGroup.setStandedProdId(normProdId);
        storageGroup.setSupplierId(request.getSupplierId());
        storageGroup.setStorageGroupName(request.getProductName());
        storageGroupBusiSV.addGroup(storageGroup);
        return CommonUtils.genSuccessResponse(normProdId);
    }

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
     */
    @Override
    public BaseResponse updateProductInfo(NormProdSaveRequest productInfoRequest) throws BusinessException, SystemException {
        if (StringUtils.isBlank(productInfoRequest.getTenantId())
                || StringUtils.isBlank(productInfoRequest.getProductId()) || StringUtils.isBlank(productInfoRequest.getSupplierId()))
            throw new BusinessException("","租户标识标和准品标识,商户标识均不能为空");
        normProductBusiSV.updateNormProd(productInfoRequest);
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    /**
     * 废弃标准品. <br>
     *
     * @param invalidRequest 标准品废弃请求参数
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     */
    @Override
    public BaseResponse discardProduct(NormProdUniqueReq invalidRequest) throws BusinessException, SystemException {
        normProductBusiSV.discardProduct(
                invalidRequest.getTenantId(),invalidRequest.getProductId(),
                invalidRequest.getOperId(), invalidRequest.getSupplierId());
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    /**
     * 更新标准品市场价. <br>
     *
     * @param marketPrice 标准品市场价
     * @return 更新结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     */
    @Override
    public BaseResponse updateMarketPrice(MarketPriceUpdate marketPrice) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(marketPrice.getTenantId(),"");
        CommonUtils.checkSupplierId(marketPrice.getSupplierId(),"");
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
     */
    @Override
    public AttrMap queryAttrByNormProduct(AttrQuery attrQuery) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(attrQuery.getTenantId(),"");
        return normProductBusiSV.queryAttrOfProduct(attrQuery.getTenantId(),attrQuery.getProductId(),attrQuery.getAttrType());
    }

    /**
     * 制定商品销售价中标准品列表查询.<br>
     *     库存组数量为非废弃的数量
     *
     * @param productRequest 查询标准品信息
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     */
    @Override
    public PageInfoResponse<NormProdResponse> queryNormProductForSalePrice(NormProdRequest productRequest)
            throws BusinessException, SystemException {
    	return  normProductBusiSV.queryNormProductForSalePrice(productRequest);
    }
}
