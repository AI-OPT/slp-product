package com.ai.slp.product.api.normproduct.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.slp.product.api.normproduct.interfaces.INormProductSV;
import com.ai.slp.product.api.normproduct.param.AttrMap;
import com.ai.slp.product.api.normproduct.param.AttrQuery;
import com.ai.slp.product.api.normproduct.param.MarketPriceUpdate;
import com.ai.slp.product.api.normproduct.param.NormProdAndKeyAttrRes;
import com.ai.slp.product.api.normproduct.param.NormProdInfoResponse;
import com.ai.slp.product.api.normproduct.param.NormProdRequest;
import com.ai.slp.product.api.normproduct.param.NormProdResponse;
import com.ai.slp.product.api.normproduct.param.NormProdSaveRequest;
import com.ai.slp.product.api.normproduct.param.NormProdUniqueReq;
import com.ai.slp.product.constants.NormProdConstants;
import com.ai.slp.product.service.business.interfaces.INormProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IProdSkuBusiSV;
import com.ai.slp.product.util.CommonUtils;
import com.ai.slp.product.util.MQConfigUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;

/**
 * 标准品接口
 * Created by jackieliu on 16/4/27.
 */
@Service(validation = "true")
@Component
public class INormProductSVImpl implements INormProductSV {
	private static final Logger LOGGER = LoggerFactory.getLogger(INormProductSVImpl.class);
    //标准品处理对象
    @Autowired
    INormProductBusiSV normProductBusiSV;
    @Autowired
    IProdSkuBusiSV prodSkuBusiSV;
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
        long startTime = System.nanoTime();
        LOGGER.info("=====开始INormProductSVImpl.queryNormProduct,商品列表查询,当前时间戳:"+startTime);
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
    	BaseResponse response = new BaseResponse();
        long startTime = System.currentTimeMillis();
        LOGGER.info("=====开始INormProductSVImpl.createProductInfo,商品添加,当前时间戳:"+startTime);
        CommonUtils.checkTenantId(request.getTenantId());
        PageInfoResponse<String> normProdIdResponse = normProductBusiSV.installNormProd(request);
        long endTime = System.currentTimeMillis();
        LOGGER.info("=====结束INormProductSVImpl.createProductInfo,商品添加,当前时间戳:{}",endTime,(endTime-startTime));
       // return CommonUtils.genSuccessResponse(normProdId);
        return CommonUtils.genSuccessResponse(normProdIdResponse.getResult().get(0));
        //return response.setResponseHeader(normProdIdResponse.getResponseHeader());
    }
    
    /**
     * 添加标准品信息.(同时生成标准品属性、库存组、sku) 
     */
    @Override
	public BaseResponse createProductAndStoGroup(NormProdSaveRequest request) throws BusinessException, SystemException {
        long startTime = System.currentTimeMillis();
        LOGGER.info("===== 开始 INormProductSVImpl.createProductAndStoGroup,商品添加,当前时间戳:"+startTime);
		String tenantId = request.getTenantId();
		CommonUtils.checkTenantId(tenantId);
        PageInfoResponse<String> normProdIdResponse = normProductBusiSV.installNormProdAndPtoGroup(request);
        long endTime = System.currentTimeMillis();
        LOGGER.info("===== 结束 INormProductSVImpl.createProductAndStoGroup,商品添加,当前时间戳:{},用时:{}",endTime,(endTime-startTime));
        if(StringUtils.isEmpty(normProdIdResponse.getResult().get(0))){
        	/* BaseResponse baseResponse = new BaseResponse();
             baseResponse.setResponseHeader(new ResponseHeader(true, ExceptCodeConstants.Special.SYSTEM_ERROR,"添加失败！"));*/
             return normProdIdResponse;
        }else{
        	LOGGER.info("end createProduct");
        	return CommonUtils.genSuccessResponse(normProdIdResponse.getResult().get(0));
        	//return normProdIdResponse;
        }
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
                || StringUtils.isBlank(productInfoRequest.getProductId()) || StringUtils.isBlank(productInfoRequest.getSupplierId())){
        	throw new BusinessException("","租户标识标和准品标识,商户标识均不能为空");
        }
        normProductBusiSV.updateNormProd(productInfoRequest);
        return CommonUtils.genSuccessResponse("");
    	
    }
    
    @Override
	public BaseResponse updateProductAndStoGroup(NormProdSaveRequest productInfoRequest)
			throws BusinessException, SystemException {
    	/*if (StringUtils.isBlank(productInfoRequest.getTenantId())
                || StringUtils.isBlank(productInfoRequest.getProductId()) || StringUtils.isBlank(productInfoRequest.getSupplierId())){
    		throw new BusinessException("","租户标识标和准品标识,商户标识均不能为空");
    	}
        normProductBusiSV.updateNormProdAndStoGroup(productInfoRequest);
        return CommonUtils.genSuccessResponse("");*/
    	
    	boolean ccsMqFlag=false;
	   	//从配置中心获取mq_enable
	  	ccsMqFlag=MQConfigUtil.getCCSMqFlag();
	  	if (!ccsMqFlag) {
	  		if (StringUtils.isBlank(productInfoRequest.getTenantId())
	                || StringUtils.isBlank(productInfoRequest.getProductId()) || StringUtils.isBlank(productInfoRequest.getSupplierId())){
	    		throw new BusinessException("","租户标识标和准品标识,商户标识均不能为空");
	    	}
	        normProductBusiSV.updateNormProdAndStoGroup(productInfoRequest);
	        return CommonUtils.genSuccessResponse("");
		} else {
			//消息模式下，异步调用服务
			BaseResponse response = CommonUtils.genSuccessResponse("");
			//发送消息
			MDSClientFactory.getSenderClient(NormProdConstants.MDSNS.MDS_NS_PRODUCT_TOPIC).send(JSON.toJSONString(productInfoRequest), 0);
			ResponseHeader responseHeader = new ResponseHeader(true,
					ExceptCodeConstants.Special.SUCCESS, "成功");
			response.setResponseHeader(responseHeader);
			return response; 
		}
    	
	}

    /**
     * 废弃标准品,并级联废弃销售商品和库存信息. <br>
     * 只有在库存组为停用状态时,方可废弃
     * @param invalidRequest 标准品废弃请求参数
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     */
    @Override
    public BaseResponse discardProductWithStorage(NormProdUniqueReq invalidRequest) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(invalidRequest.getTenantId());
        normProductBusiSV.discardProductWithProduct(
                invalidRequest.getTenantId(),invalidRequest.getProductId(),
                invalidRequest.getOperId(), invalidRequest.getSupplierId()
        );
        return CommonUtils.genSuccessResponse("");
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
        return CommonUtils.genSuccessResponse("");
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
 /*       CommonUtils.checkTenantId(marketPrice.getTenantId());
        normProductBusiSV.updateMarketPrice(marketPrice);
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;*/
    	
    	boolean ccsMqFlag=false;
	   	//从配置中心获取mq_enable
	  	ccsMqFlag=MQConfigUtil.getCCSMqFlag();
    	if (!ccsMqFlag) {
    		CommonUtils.checkTenantId(marketPrice.getTenantId());
	        normProductBusiSV.updateMarketPrice(marketPrice);
	        BaseResponse baseResponse = new BaseResponse();
	        ResponseHeader responseHeader = new ResponseHeader();
	        responseHeader.setIsSuccess(true);
	        responseHeader.setResultCode("");
	        baseResponse.setResponseHeader(responseHeader);
	        return baseResponse;
		} else {
			BaseResponse response = new BaseResponse();
			//发送消息
			MDSClientFactory.getSenderClient(NormProdConstants.MDSNS.MDS_NS_PRODUCT_TOPIC).send(JSON.toJSONString(marketPrice), 0);
			ResponseHeader responseHeader = new ResponseHeader(true,
					ExceptCodeConstants.Special.SUCCESS, "成功");
			response.setResponseHeader(responseHeader);
			return response; 
		}
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

    /**
     * 分页查询标准品信息,包括标准品下的关键属性.<br>
     *
     * @param productRequest 查询标准品信息
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     */
    @Override
    public PageInfoResponse<NormProdAndKeyAttrRes> queryNormProductAndKeyAttr(NormProdRequest productRequest)
            throws BusinessException, SystemException {
        CommonUtils.checkTenantId(productRequest.getTenantId());
        PageInfoResponse<NormProdAndKeyAttrRes> pageRes = normProductBusiSV.queryProdAndKeyAttr(productRequest);
        CommonUtils.addSuccessResHeader(pageRes,"OK");
        return pageRes;
    }
}
