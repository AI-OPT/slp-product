package com.ai.slp.product.api.normproduct.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.paas.ipaas.search.vo.Result;
import com.ai.paas.ipaas.search.vo.SearchCriteria;
import com.ai.paas.ipaas.search.vo.SearchOption;
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
import com.ai.slp.product.constants.SearchFieldConfConstants;
import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.dao.mapper.bo.StandedProdAttr;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.service.atom.interfaces.IProdCatDefAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProdAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.business.impl.search.ProductSearchImpl;
import com.ai.slp.product.service.business.interfaces.INormProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IProdSkuBusiSV;
import com.ai.slp.product.service.business.interfaces.search.IProductSearch;
import com.ai.slp.product.util.CommonUtils;
import com.ai.slp.product.util.DataUtils;
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
    @Autowired
	IStandedProductAtomSV standedProductAtomSV;
    @Autowired
	IProdCatDefAtomSV catDefAtomSV;
    @Autowired
	IStandedProdAttrAtomSV standedProdAttrAtomSV;
    @Autowired
	IStorageGroupAtomSV storageGroupAtomSV;
    IProductSearch productSearch = new ProductSearchImpl();
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
       // return normProductBusiSV.queryForPage(productRequest);
        PageInfo<StandedProduct> productPageInfo = normProductBusiSV.queryForPage(productRequest);
    	
        PageInfoResponse<NormProdResponse> normProdPageInfo = new PageInfoResponse<NormProdResponse>();
		BeanUtils.copyProperties(normProdPageInfo, productPageInfo);
		// 添加结果集
		List<StandedProduct> productList = productPageInfo.getResult();
		List<NormProdResponse> normProductList = new ArrayList<NormProdResponse>();
		normProdPageInfo.setResult(normProductList);

		if(!CollectionUtils.isEmpty(productList)){
		for (StandedProduct standedProduct : productList) {
			NormProdResponse normProduct = new NormProdResponse();
			BeanUtils.copyProperties(normProduct, standedProduct);
			ProductCat productCat = catDefAtomSV.selectAllStateById(standedProduct.getTenantId(),
					standedProduct.getProductCatId());
			if (productCat != null){
				normProduct.setCatName(productCat.getProductCatName());
			}
			normProduct.setCatId(standedProduct.getProductCatId());
			normProduct.setProductId(standedProduct.getStandedProdId());
			normProduct.setProductName(standedProduct.getStandedProductName());
			normProductList.add(normProduct);
		}
		}
        
		return normProdPageInfo;
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
    	NormProdInfoResponse response = new NormProdInfoResponse();
    	//查询es
    	List<SearchCriteria> searchCriterias = new ArrayList<SearchCriteria>();
    	searchCriterias.add(new SearchCriteria(SearchFieldConfConstants.TENANT_ID,
    			invalidRequest.getTenantId(),
    			new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
    	searchCriterias.add(new SearchCriteria("productid",
    			invalidRequest.getProductId(),
    			new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
    	
    	Result<SKUInfo> result = productSearch.searchByCriteria(searchCriterias, 0, 10, null);
    	if (CollectionUtil.isEmpty(result.getContents())) {
    		response.setResponseHeader(new ResponseHeader(false, ExceptCodeConstants.Special.SYSTEM_ERROR, "查询商品失败"));
    		return response;
		}
    	SKUInfo product = result.getContents().get(0);
    	
		// 标准品信息填充返回值
		//BeanUtils.copyProperties(response, product);
    	response.setTenantId(product.getTenantid());
    	response.setProductCatId(product.getProductcategoryid());
    	response.setProductId(product.getProductid());
    	response.setProductName(product.getProductname());
    	response.setState(product.getStandprodstate());
    	response.setProductType(product.getProducttype());
    	response.setMarketPrice(product.getMarketprice());
    	response.setCreateTime(new Timestamp(product.getCreatetime()));
    	response.setOperTime(new Timestamp(product.getOpertime()));
    	response.setOperId(DataUtils.getLongVal(product.getOperid()));
    	response.setSupplierId(product.getSupplierid());
    	
    	
		Map<Long, Set<String>> attrAndValueIds = new HashMap<>();
		Map<Long, String> attrAndValueMap = new HashMap<>();
		// 查询属性信息
    	/*StandedProduct product = normProductBusiSV.queryById(invalidRequest.getTenantId(),invalidRequest.getProductId());
    	
    	NormProdInfoResponse response = new NormProdInfoResponse();
		// 标准品信息填充返回值
		BeanUtils.copyProperties(response, product);
		response.setProductId(product.getStandedProdId());
		response.setProductName(product.getStandedProductName());
		Map<Long, Set<String>> attrAndValueIds = new HashMap<>();
		Map<Long, String> attrAndValueMap = new HashMap<>();*/
		// 查询属性信息
		List<StandedProdAttr> attrList = standedProdAttrAtomSV.queryByNormProduct(invalidRequest.getTenantId(),invalidRequest.getProductId());
		for (StandedProdAttr prodAttr : attrList) {
			Set<String> attrVal = attrAndValueIds.get(prodAttr.getAttrId());
			if (attrVal == null) {
				attrVal = new HashSet<>();
			}
			attrVal.add(prodAttr.getAttrvalueDefId());
			if (!attrAndValueIds.containsKey(prodAttr.getAttrId())){
				attrAndValueIds.put(prodAttr.getAttrId(), attrVal);
			}
			if(StringUtils.isBlank(prodAttr.getAttrvalueDefId())){
				attrAndValueMap.put(prodAttr.getAttrId(), prodAttr.getAttrValueName());
			}
		}
		response.setAttrAndValueIds(attrAndValueIds);
		response.setAttrAndValueMap(attrAndValueMap);
    	
    	return response;
    	//return normProductBusiSV.queryById(invalidRequest.getTenantId(),invalidRequest.getProductId());
    }
/*    public NormProdInfoResponse queryProducById(NormProdUniqueReq invalidRequest) throws BusinessException, SystemException {
    	return normProductBusiSV.queryById(invalidRequest.getTenantId(),invalidRequest.getProductId());
    }
*/
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
        CommonUtils.checkTenantId(request.getTenantId());
        String standProdId = normProductBusiSV.installNormProd(request);
        return CommonUtils.genSuccessResponse(standProdId);
    }
    
    /**
     * 添加标准品信息.(同时生成库存组、商品、sku) 
     */
    @Override
	public BaseResponse createProductAndStoGroup(NormProdSaveRequest request) throws BusinessException, SystemException {
		String tenantId = request.getTenantId();
		CommonUtils.checkTenantId(tenantId);
        String productId = normProductBusiSV.installNormProdAndPtoGroup(request);
        return CommonUtils.genSuccessResponse(productId);
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
	  		if (StringUtils.isBlank(productInfoRequest.getTenantId())
	                || StringUtils.isBlank(productInfoRequest.getProductId()) || StringUtils.isBlank(productInfoRequest.getSupplierId())){
	    		throw new BusinessException("","租户标识标和准品标识,商户标识均不能为空");
	    	}
	        normProductBusiSV.updateNormProdAndStoGroup(productInfoRequest);
	        return CommonUtils.genSuccessResponse("");
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
    	String tenantId = invalidRequest.getTenantId();
    	String productId = invalidRequest.getProductId();
    	String supplierId = invalidRequest.getSupplierId();
    	//StandedProduct standedProduct = standedProductAtomSV.selectById(tenantId, productId);
    	//查询es
    	
    	List<SearchCriteria> searchCriterias = new ArrayList<SearchCriteria>();
    	searchCriterias.add(new SearchCriteria(SearchFieldConfConstants.TENANT_ID,
    			invalidRequest.getTenantId(),
    			new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
    	searchCriterias.add(new SearchCriteria("productid",
    			invalidRequest.getProductId(),
    			new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
    	
    	Result<SKUInfo> result = productSearch.searchByCriteria(searchCriterias, 0, 10, null);
    	if (result.getContents()==null) {
			throw new BusinessException("查询信息失败");
		}
    	SKUInfo product = result.getContents().get(0);
    	
    	
		if (product == null){
			throw new BusinessException("", "不存在指定商品,租户ID:" + tenantId + ",商品标识:" + productId);
		}


		// 查询没有废弃的库存组
		int noDiscardNum = storageGroupAtomSV.countNoDiscard(tenantId, productId);
		if (noDiscardNum > 0){
			throw new BusinessException("", "该商品下存在[" + noDiscardNum + "]个未废弃库存组");
		}
    	StandedProduct standedProduct = new StandedProduct();
    	//BeanUtils.copyProperties(standedProduct2, standedProduct);
    	standedProduct.setTenantId(product.getTenantid());
    	standedProduct.setStandedProdId(product.getProductid());
    	standedProduct.setOperTime(new Timestamp(product.getOpertime()));
    	standedProduct.setCreateTime(new Timestamp(product.getCreatetime()));
    	
    	
        normProductBusiSV.discardProduct(standedProduct,invalidRequest.getOperId());
        return CommonUtils.genSuccessResponse("");
    }
/*    public BaseResponse discardProduct(NormProdUniqueReq invalidRequest) throws BusinessException, SystemException {
    	normProductBusiSV.discardProduct(
    			invalidRequest.getTenantId(),invalidRequest.getProductId(),
    			invalidRequest.getOperId(), invalidRequest.getSupplierId());
    	return CommonUtils.genSuccessResponse("");
    }
*/
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
    		CommonUtils.checkTenantId(marketPrice.getTenantId());
	        normProductBusiSV.updateMarketPrice(marketPrice);
	        BaseResponse baseResponse = new BaseResponse();
	        ResponseHeader responseHeader = new ResponseHeader();
	        responseHeader.setIsSuccess(true);
	        responseHeader.setResultCode("");
	        baseResponse.setResponseHeader(responseHeader);
	        return baseResponse;
    }
/*    @Override
    public BaseResponse updateMarketPrice(MarketPriceUpdate marketPrice) throws BusinessException, SystemException {
    	       CommonUtils.checkTenantId(marketPrice.getTenantId());
        normProductBusiSV.updateMarketPrice(marketPrice);
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    	
    	StandedProduct standedProduct = standedProductAtomSV.selectById(marketPrice.getTenantId(),
				marketPrice.getProductId());
		
		// 判断此租户下是否存在次标准品
		if (standedProduct == null){
			throw new BusinessException("",
					"找不到指定的租户=" + marketPrice.getTenantId() + "下的标准品=" + marketPrice.getProductId() + "信息");
		}
    	// 判断商户ID是否为传入的商户ID
    	if (!marketPrice.getSupplierId().equals(standedProduct.getSupplierId())){
			throw new BusinessException("",
					"标准品所属商户ID:" + standedProduct.getSupplierId() + "与当前商户ID:" + marketPrice.getSupplierId() + "不一致!");
		}
    	
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
    		MDSClientFactory.getSenderClient(NormProdConstants.MDSNS.MDS_NS_MARKETPRICE_TOPIC).send(JSON.toJSONString(marketPrice), 0);
    		ResponseHeader responseHeader = new ResponseHeader(true,
    				ExceptCodeConstants.Special.SUCCESS, "成功");
    		response.setResponseHeader(responseHeader);
    		return response; 
    	}
    }
*/    
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
