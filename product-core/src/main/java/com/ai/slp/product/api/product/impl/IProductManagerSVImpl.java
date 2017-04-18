package com.ai.slp.product.api.product.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.components.ses.SESClientFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.paas.ipaas.search.vo.Result;
import com.ai.paas.ipaas.search.vo.SearchCriteria;
import com.ai.paas.ipaas.search.vo.SearchOption;
import com.ai.slp.product.api.product.interfaces.IProductManagerSV;
import com.ai.slp.product.api.product.param.OtherSetOfProduct;
import com.ai.slp.product.api.product.param.ProdNoKeyAttr;
import com.ai.slp.product.api.product.param.ProdStateLog;
import com.ai.slp.product.api.product.param.ProductCheckParam;
import com.ai.slp.product.api.product.param.ProductEditQueryReq;
import com.ai.slp.product.api.product.param.ProductEditUp;
import com.ai.slp.product.api.product.param.ProductInfoForUpdate;
import com.ai.slp.product.api.product.param.ProductInfoQuery;
import com.ai.slp.product.api.product.param.ProductPriorityParam;
import com.ai.slp.product.api.product.param.ProductQueryInfo;
import com.ai.slp.product.api.product.param.ProductStorageSale;
import com.ai.slp.product.api.product.param.ProductStorageSaleParam;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.constants.SearchConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.bo.product.ProdPicture;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.product.ProductLog;
import com.ai.slp.product.dao.mapper.bo.product.ProductStateLog;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.service.atom.interfaces.IProdCatDefAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdPictureAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductStateLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.ISkuStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.business.impl.search.ProductSearchImpl;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IProductManagerBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageNumBusiSV;
import com.ai.slp.product.service.business.interfaces.search.IProductSearch;
import com.ai.slp.product.service.business.interfaces.search.ISKUIndexBusiSV;
import com.ai.slp.product.util.CommonUtils;
import com.ai.slp.product.util.ConvertUtils;
import com.ai.slp.product.util.CriteriaUtils;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.IPaasStorageUtils;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * Created by jackieliu on 16/6/6.
 */
@Service(validation = "true")
@Component
public class IProductManagerSVImpl implements IProductManagerSV {
    private static final Logger logger = LoggerFactory.getLogger(IProductManagerSVImpl.class);
    @Autowired
    IProductManagerBusiSV productManagerBusiSV;
    @Autowired
    IProductBusiSV productBusiSV;
    @Autowired
    ISKUIndexBusiSV skuIndexManage;
    @Autowired
    IProductAtomSV productAtomSV;
    
    @Autowired
    IProdCatDefAtomSV catDefAtomSV;
    
    @Autowired
    IProdPictureAtomSV prodPictureAtomSV;
    @Autowired
    IStandedProductAtomSV standedProductAtomSV;
    @Autowired
    IStorageGroupAtomSV storageGroupAtomSV;
    @Autowired
    IStorageNumBusiSV storageNumBusiSV;
    @Autowired
    IStorageAtomSV storageAtomSV;
    @Autowired
    ISkuStorageAtomSV skuStorageAtomSV;
    @Autowired
    IProductLogAtomSV productLogAtomSV;
    @Autowired
    IProductStateLogAtomSV productStateLogAtomSV;
    /**
     * 商品管理查询商品编辑状态<br>
     * 状态 1未编辑2已编辑<br>
     * 按生成时间排序<br>
     *
     * @param productEditParam
     * @return 满足条件的商品集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public PageInfoResponse<ProductEditUp> queryProductEdit(ProductEditQueryReq productEditParam) throws BusinessException, SystemException {
    	PageInfoResponse<ProductEditUp> response = new PageInfoResponse<>();
    	ResponseHeader responseHeader = null;
    	try{
        CommonUtils.checkTenantId(productEditParam.getTenantId());
        CommonUtils.checkSupplierId(productEditParam.getSupplierId());
        List<ProductEditUp> productEditUps = new ArrayList<>();
        /**
         * 查询ES缓存
         */
        IProductSearch productSearch = new ProductSearchImpl();
		int startSize = 1;
		int maxSize = 1;
		// 最大条数设置
		int pageNo = productEditParam.getPageNo();
		int size = productEditParam.getPageSize();
		if (productEditParam.getPageNo() == 1) {
			startSize = 0;
		} else {
			startSize = (pageNo - 1) * size;
		}
		maxSize = size;
		List<SearchCriteria> searchfieldVos = CriteriaUtils.commonConditions(productEditParam);
		Result<SKUInfo> result = productSearch.searchByCriteria(searchfieldVos, startSize, maxSize, null);
		response.setCount(0);
		response.setPageNo(productEditParam.getPageNo());
    	response.setPageSize(productEditParam.getPageSize());
        if(!CollectionUtils.isEmpty(result.getContents())){
        	for(SKUInfo skuInfo : result.getContents()){
        		ProductEditUp productEditUp = ConvertUtils.convertToProductEditUp(skuInfo);
        		productEditUps.add(productEditUp);
        	}
        	response.setCount((int)result.getCount());
        }/*else{
        String tenantId = productEditParam.getTenantId();
        PageInfo<Product> products = productManagerBusiSV.queryPageForEdit(productEditParam);
        if(!CollectionUtils.isEmpty(products.getResult())){
        for (Product product:products.getResult()){
            ProductEditUp productEditUp = new ProductEditUp();
            BeanUtils.copyProperties(productEditUp,product);
            //设置类目名称
            ProductCat cat = catDefAtomSV.selectById(tenantId,product.getProductCatId());
            if (cat!=null){
            	productEditUp.setProductCatName(cat.getProductCatName());
            }
            //查询主预览图
            ProdPicture prodPicture = prodPictureAtomSV.queryMainOfProd(product.getProdId());
            if (prodPicture!=null){
                productEditUp.setProPictureId(prodPicture.getProPictureId());
                productEditUp.setVfsId(prodPicture.getVfsId());
                productEditUp.setPicType(prodPicture.getPicType());
            }
             productEditUps.add(productEditUp);
        	}
        }
        BeanUtils.copyProperties(response,products);
        }*/
        response.setResult(productEditUps);
        responseHeader = new ResponseHeader(true,CommonConstants.OPERATE_SUCCESS,"success");
    	}catch(Exception e){
    		logger.error("查询商品审核状态发生异常:",e);
    		if(e instanceof BusinessException){
    			responseHeader = new ResponseHeader(false,((BusinessException) e).getErrorCode(),((BusinessException) e).getErrorMessage());
    		}else{
    			responseHeader = new ResponseHeader(false,CommonConstants.OPERATE_FAIL,"查询失败");
    		}
    	}
    	response.setResponseHeader(responseHeader);
        return response;
    }

    /**
     * 商品管理查询商品被拒绝信息-与商品审核中查询共用<br>
     * 拒绝时间为操作时间<br>
     *
     * @param productRefuseParam
     * @return 满足条件的商品集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public PageInfoResponse<ProductEditUp> queryProductRefuse(ProductEditQueryReq productRefuseParam) throws BusinessException, SystemException {
    	CommonUtils.checkTenantId(productRefuseParam.getTenantId());
        CommonUtils.checkSupplierId(productRefuseParam.getSupplierId());
    	return productManagerBusiSV.queryProductRefuse(productRefuseParam);
    }

    /**
     * 审核是否通过调用方法<br>
     * 通过上架类型 1审核通过后立即上架 2审核通过后放入仓库 3定时上架<br>
     * 判断改变为哪种状态<br>
     * 销售商品状态 4审核未通过<br>
     * 拒绝后把拒绝类型和原因存入销售商品流程日志表<br>
     *
     * @param productCheckParam
     * @return 基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public BaseResponse productCheck(ProductCheckParam productCheckParam) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(productCheckParam.getTenantId());
        productManagerBusiSV.auditProduct(productCheckParam);
        return CommonUtils.genSuccessResponse("OK");
    }

    /**
     * 商品申请优先审核调用方法<br>
     *
     * @param productPriorityParam
     * @return 基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public BaseResponse productPriority(ProductPriorityParam productPriorityParam) throws BusinessException, SystemException {
        return null;
    }

    /**
     * 查询单个商品的其他设置信息<br>
     *
     * @param productInfoQuery 单个商品的标识信息
     * @return 单个商品的受众信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     */
    @Override
    public OtherSetOfProduct queryOtherSetOfProduct(ProductInfoQuery productInfoQuery) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(productInfoQuery.getTenantId());
        OtherSetOfProduct otherSet =  productManagerBusiSV.queryOtherSetOfProd(
                productInfoQuery.getTenantId(),productInfoQuery.getSupplierId(),productInfoQuery.getProductId());
        CommonUtils.addSuccessResHeader(otherSet,"OK");
        return otherSet;
    }

    /**
     * 分页查询仓库中的商品列表<br>
     * 商品状态:6仓库中（审核通过放入） 61售罄下架 定时上架-通过上架类型判断  62废弃下架<br>
     *
     * @param productStorageSaleParam
     * @return 商品管理售中与仓库商品返回类集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public PageInfoResponse<ProductStorageSale> queryStorageProdByState(ProductStorageSaleParam productStorageSaleParam) throws BusinessException, SystemException {
    	CommonUtils.checkTenantId(productStorageSaleParam.getTenantId());
        return productManagerBusiSV.queryStorageProdByState(productStorageSaleParam);
    }

    /**
     * 对商品进行上架处理
     *
     * @param query
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     */
    
    @Override
    public BaseResponse changeToInSale(ProductInfoQuery query) throws BusinessException, SystemException {
    	//boolean ccsMqFlag=false;
    	Long operId = query.getOperId();
    	Product product = null;
    	SKUInfo skuInfo = null;
    	/**
         * 从ES缓存中获取商品信息
         */
        IProductSearch productSearch = new ProductSearchImpl();
		int startSize = 0;
		int maxSize = 1;
		List<SearchCriteria> searchfieldVos = new ArrayList<>();
		searchfieldVos.add(new SearchCriteria(com.ai.slp.product.constants.SearchFieldConfConstants.PRODUCT_ID, query.getProductId(),new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		Result<SKUInfo> result = productSearch.searchByCriteria(searchfieldVos, startSize, maxSize, null);
		if(!CollectionUtils.isEmpty(result.getContents())){
			skuInfo = result.getContents().get(0);
			product = ConvertUtils.convertToProduct(skuInfo);
		}
        if (product == null){
            throw new BusinessException("","未找到相关的商品信息,租户ID:"+query.getTenantId()+",商品标识:"+query.getProductId());
        }
	   	//从配置中心获取mq_enable
        //去除异步操作
	  	//ccsMqFlag=MQConfigUtil.getCCSMqFlag();
    	//if (!ccsMqFlag) {
    		CommonUtils.checkTenantId(query.getTenantId());
            String tenantId = product.getTenantId();
            //查询标准品是否为"可使用"状态
           /* String standProdState = "";
            if(null!=skuInfo&&!StringUtils.isEmpty(skuInfo.getStandprodstate())){
            	standProdState = skuInfo.getStandprodstate();
            }else{
            	StandedProduct standedProduct = standedProductAtomSV.selectById(tenantId,product.getProdId());
            	if(null!=standedProduct){
            		standProdState = standedProduct.getState();
            	}
            }
            if (!StandedProductConstants.STATUS_ACTIVE.equals(standProdState)){
                logger.warn("未找到指定的标准品或标准品状态为不可用,租户ID:{},商户ID:{},标准品ID:{}",tenantId,product.getSupplierId(),product.getStandedProdId());
                throw new BusinessException("","未找到相关的商品信息或商品状态为不可用");
            }*/
            
            //若商品状态是"停用下架"或"售罄下架"
            if(ProductConstants.Product.State.STOP.equals(product.getState())
                    || ProductConstants.Product.State.SALE_OUT.equals(product.getState())){
                changeToSaleForStop(product, operId);
            }
/*            //若商品既不是"停用下架"和"售罄下架",也不是"仓库中",则不允许上架
            else if(!ProductConstants.Product.State.IN_STORE.equals(product.getState())){
                logger.warn("商品当前状态不允许上架,租户ID:{},商品标识:{},当前状态:{}", tenantId,product.getProdId(),product.getState());
                throw new BusinessException("","商品当前状态不允许上架");
            }*/

            //1.库存组不存在,或已废弃
            //StorageGroup storageGroup = storageGroupAtomSV.queryByGroupIdAndSupplierId( tenantId,product.getSupplierId(),product.getStorageGroupId());
            //获取库存组的cacheKey
            ICacheClient cacheClient = IPaasStorageUtils.getClient();
    		String groupKey = IPaasStorageUtils.genMcsStorageGroupKey(tenantId,product.getProdId());
    		//设置库存组状态
    		String storageGroupState = cacheClient.hget(groupKey, StorageConstants.IPass.McsParams.GROUP_SERIAL_HTAGE);
            /*if(null!=skuInfo){
            	storageGroupState = skuInfo.getStoragegroupstate();
            }*/
            if ( StorageConstants.StorageGroup.State.DISCARD.equals(storageGroupState)
                    || StorageConstants.StorageGroup.State.AUTO_DISCARD.equals(storageGroupState)){
                throw new BusinessException("","对应库存组不存在或已废弃,无法上架,租户ID:"+tenantId +"库存组ID:"+product.getStorageGroupId());
            }
            //判断已启用库存下的SKU库存是否均设置价格
            if (skuStorageAtomSV.countOfNoPrice(tenantId,product.getStorageGroupId())>0){
            	throw new BusinessException("","启用库存下存在未设置价格的库存,无法上架");
            }
            //查询当前库存组可用量 
            Long usableNum  = null;
            if(null==skuInfo){
            	usableNum = storageNumBusiSV.queryNowUsableNumOfGroup(tenantId,product.getStorageGroupId());
            }else{
            	usableNum = skuInfo.getUsablenum();
            }
            //库存组停用或当前库存可用为零,
            //直接切换至"售罄下架"
            if (StorageConstants.StorageGroup.State.STOP.equals(storageGroupState)
                    ||StorageConstants.StorageGroup.State.AUTO_STOP.equals(storageGroupState)
                    ||usableNum==null || usableNum<=0){
                changeToStop(storageGroupState,product, operId);
            }
            productBusiSV.changeToInSale(product,operId);
            //将商品添加至搜索引擎
            //skuIndexManage.updateSKUIndex(product.getProdId(),product.getUpTime().getTime());
            //查询es
        	List<SearchCriteria> searchCriterias = new ArrayList<SearchCriteria>();
        	searchCriterias.add(new SearchCriteria("productid",
        			product.getProdId(),
        			new SearchOption(SearchOption.SearchLogic.must,  SearchOption.SearchType.querystring)));
        	
    		// 最大条数设置
    		int pageNo = 1;
    		int size = 20;
    		if (pageNo == 1) {
    			startSize = 0;
    		} else {
    			startSize = (pageNo - 1) * size;
    		}
    		maxSize = size;
    		IProductSearch search = new ProductSearchImpl();
        	Result<SKUInfo> infoResult = search.searchByCriteria(searchCriterias, startSize,  maxSize, null);
        	if (CollectionUtil.isEmpty(infoResult.getContents())) {
        		logger.error("查询商品失败");
        		throw new BusinessException("查询es中的商品信息失败");
    		}
        	SKUInfo skuInfos = infoResult.getContents().get(0);
        	skuInfos.setState(ProductConstants.Product.State.IN_SALE);
        	skuInfos.setUptime(DateUtils.currTimeStamp().getTime());
        	List<SKUInfo> skuInfoList = new ArrayList<>();
        	skuInfoList.add(skuInfos);
        	SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace).bulkInsert (skuInfoList);
            return CommonUtils.addSuccessResHeader(new BaseResponse(),"");
		} /*else {
			BaseResponse response = CommonUtils.genSuccessResponse("");
			//发送消息
			MDSClientFactory.getSenderClient(NormProdConstants.MDSNS.MDS_NS_CHANGETOINSALE_TOPIC).send(JSON.toJSONString(query), 0);
			ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstants.Special.SUCCESS, "成功");
			response.setResponseHeader(responseHeader);
			return response;  
		}
    }*/

    /**
     * 为编辑页面查询商品非关键属性
     *
     * @param query
     * @return
     * @throws BusinessException
     * @throws SystemException
     */
    @Override
    public ProdNoKeyAttr queryNoKeyAttrOfProd(ProductInfoQuery query) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(query.getTenantId());
        return productBusiSV.queryNoKeyAttrForEdit(query.getTenantId(),query.getProductId());
    }

    /**
     * 更新商品信息
     * @param product
     * @return
     * @throws BusinessException
     * @throws SystemException
     */
    @Override
    public BaseResponse updateProduct(ProductInfoForUpdate product) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(product.getTenantId());
        productManagerBusiSV.updateProdEdit(product);
        return CommonUtils.genSuccessResponse("OK");
    }

    /**
     * 对商品进行手动下架处理
     * @param query
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author jiawen
     */
	@Override
	public BaseResponse changeToInStore(ProductInfoQuery query) throws BusinessException, SystemException {
		Product product = null;
    	int startSize = 0;
    	int maxSize = 1;
    	/**
         * 从ES缓存中获取商品信息
         */
        /**IProductSearch productSearch = new ProductSearchImpl();
		List<SearchCriteria> searchfieldVos = new ArrayList<>();
		searchfieldVos.add(new SearchCriteria(com.ai.slp.product.constants.SearchFieldConfConstants.PRODUCT_ID, query.getProductId(),new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		Result<SKUInfo> result = productSearch.searchByCriteria(searchfieldVos, startSize, maxSize, null);
		if(CollectionUtils.isEmpty(result.getContents())){
			product = productAtomSV.selectByProductId(query.getTenantId(),query.getSupplierId(),query.getProductId());
		}else{
			skuInfo = result.getContents().get(0);
			product = ConvertUtils.convertToProduct(skuInfo);
		}
        if (product == null) {
            throw new SystemException("", "未找到相关的商品信息,租户ID:" + query.getTenantId() + ",商品标识:" + query.getProductId());
        }
        //若商品不是在售,则直接返回
        if (!ProductConstants.Product.State.IN_SALE.equals(product.getState())){
        	return null;
        }
		
		boolean ccsMqFlag=false;
	   	//从配置中心获取mq_enable
	  	ccsMqFlag=MQConfigUtil.getCCSMqFlag();
		if (!ccsMqFlag) {*/
			CommonUtils.checkTenantId(query.getTenantId());
			//将商品从搜索引擎中移除
	        //skuIndexManage.deleteSKUIndexByProductId(product.getProdId());
			//查询es
        	List<SearchCriteria> searchCriterias = new ArrayList<SearchCriteria>();
        	searchCriterias.add(new SearchCriteria("productid",
        			query.getProductId(),new SearchOption(SearchOption.SearchLogic.must,  SearchOption.SearchType.querystring)));
        	
    		// 最大条数设置
    		int pageNo = 1;
    		int size = 20;
    		if (pageNo == 1) {
    			startSize = 0;
    		} else {
    			startSize = (pageNo - 1) * size;
    		}
    		maxSize = size;
    		IProductSearch search = new ProductSearchImpl();
        	Result<SKUInfo> infoResult = search.searchByCriteria(searchCriterias, startSize,  maxSize, null);
        	if (CollectionUtil.isEmpty(infoResult.getContents())) {
        		logger.error("查询商品失败");
        		throw new BusinessException("查询es中的商品信息失败");
    		}
        	SKUInfo skuInfos = infoResult.getContents().get(0);
        	skuInfos.setState(ProductConstants.Product.State.IN_STORE);
        	skuInfos.setUptime(DateUtils.currTimeStamp().getTime());
        	List<SKUInfo> skuInfoList = new ArrayList<>();
        	skuInfoList.add(skuInfos);
        	SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace).bulkInsert (skuInfoList);
	        productBusiSV.changeSaleToStore(query.getTenantId(),query.getSupplierId(),product,query.getOperId());
	        return CommonUtils.genSuccessResponse("");
		} /*else {
			BaseResponse response = CommonUtils.genSuccessResponse("");
			//发送消息
			MDSClientFactory.getSenderClient(NormProdConstants.MDSNS.MDS_NS_CHANGETOINSTORE_TOPIC).send(JSON.toJSONString(query), 0);
			ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstants.Special.SUCCESS, "成功");
			response.setResponseHeader(responseHeader);
			return response; 
		}
	}*/

	/**
	 * 查询在售商品 -- 按上架时间排序
	 * 
	 * @param queryInSale
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author jiawen
	 */
	@Override
	public PageInfoResponse<ProductEditUp> searchInSale(ProductQueryInfo queryInSale) throws BusinessException, SystemException {
		PageInfoResponse<ProductEditUp> response = new PageInfoResponse<>();
		ResponseHeader responseHeader = null;
		try{
		CommonUtils.checkTenantId(queryInSale.getTenantId());
        CommonUtils.checkSupplierId(queryInSale.getSupplierId());
        List<ProductEditUp> productEditUps = new ArrayList<>();
        /**
         * 查询ES缓存
         */
        IProductSearch productSearch = new ProductSearchImpl();
		int startSize = 1;
		int maxSize = 1;
		// 最大条数设置
		int pageNo = queryInSale.getPageNo();
		int size = queryInSale.getPageSize();
		if (queryInSale.getPageNo() == 1) {
			startSize = 0;
		} else {
			startSize = (pageNo - 1) * size;
		}
		maxSize = size;
		List<SearchCriteria> searchfieldVos = CriteriaUtils.commonConditions(queryInSale);
		Result<SKUInfo> result = productSearch.searchByCriteria(searchfieldVos, startSize, maxSize, null);
		response.setCount(0);
    	response.setPageNo(queryInSale.getPageNo());
    	response.setPageSize(queryInSale.getPageSize());
        if(!CollectionUtils.isEmpty(result.getContents())){
        	for(SKUInfo skuInfo : result.getContents()){
        		ProductEditUp productEditUp = ConvertUtils.convertToProductEditUp(skuInfo);
        		productEditUps.add(productEditUp);
        	}
        	response.setCount((int)result.getCount());
        }/*else{
        String tenantId = queryInSale.getTenantId();
        PageInfo<Product> productPage = productManagerBusiSV.queryInSale(queryInSale);
        //组装prodIdList
        List<String> prodIdList=new ArrayList<String>();
        for (Product product:productPage.getResult()){
        	prodIdList.add(product.getProdId());
        }
        //一次查询出所有的图片
		List<ProdPicture> prodPictureList = prodPictureAtomSV.queryMainOfProdList(prodIdList);
        for (Product product:productPage.getResult()){
            ProductEditUp productEditUp = new ProductEditUp();
            BeanUtils.copyProperties(productEditUp,product);
            //设置类目名称
            ProductCat cat = catDefAtomSV.selectById(tenantId,product.getProductCatId());
            if (cat!=null){
    			productEditUp.setProductCatName(cat.getProductCatName());
    		}
    		ProdPicture prodPicture=getProdPictureByProdId(product.getProdId(),prodPictureList);
            if (prodPicture!=null){
                productEditUp.setProPictureId(prodPicture.getProPictureId());
                productEditUp.setVfsId(prodPicture.getVfsId());
                productEditUp.setPicType(prodPicture.getPicType());
            }
             productEditUps.add(productEditUp);
        	}
        	BeanUtils.copyProperties(response,productPage);
        }*/
        response.setResult(productEditUps);
        responseHeader = new ResponseHeader(true,CommonConstants.OPERATE_SUCCESS,"success");
		}catch(Exception e){
			logger.error("查询在售商品发生异常:",e);
			if(e instanceof BusinessException){
				responseHeader = new ResponseHeader(false,((BusinessException) e).getErrorCode(),((BusinessException) e).getErrorMessage());
			}else{
				responseHeader = new ResponseHeader(false,CommonConstants.OPERATE_FAIL,"查询在售商品失败");
			}
		}
		response.setResponseHeader(responseHeader);;
        return response;
	}

	/**
	 * 查询商品审核
	 * 
	 * @param queryInfo
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author jiawen
	 */
	@Override
	public PageInfoResponse<ProductEditUp> searchAudit(ProductQueryInfo queryInfo)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(queryInfo.getTenantId());
        CommonUtils.checkSupplierId(queryInfo.getSupplierId());
        return productManagerBusiSV.queryPageForAudit(queryInfo);
	}

	/**
	 * 查询商品拒绝原因
	 * @param queryInfo
     * @return 满足条件的商品集合
     * @throws BusinessException
     * @throws SystemException
     * @author jiawen
	 */
	@Override
	public ProdStateLog queryRefuseByPordId(ProductInfoQuery queryInfo)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(queryInfo.getTenantId());
		ProdStateLog stateLogRes = productManagerBusiSV.queryRefuseByProdId(queryInfo);
		CommonUtils.addSuccessResHeader(stateLogRes, "OK");
    	return stateLogRes;
	}
	
	 public void changeToSaleForStop(Product product,Long operId){
	        String tenantId = product.getTenantId();
	        //若商品状态不是"停用下架",也不是"售罄下架",则不进行处理
	        if(!ProductConstants.Product.State.STOP.equals(product.getState()) && !ProductConstants.Product.State.SALE_OUT.equals(product.getState())){
	            return;
	        }
	        //获取库存组的cacheKey
            ICacheClient cacheClient = IPaasStorageUtils.getClient();
    		String groupKey = IPaasStorageUtils.genMcsStorageGroupKey(tenantId,product.getProdId());
    		//设置库存组状态
    		String storageGroupState = cacheClient.hget(groupKey, StorageConstants.IPass.McsParams.GROUP_SERIAL_HTAGE);
    		if(StringUtils.isEmpty(storageGroupState)){
    			//查询库存组是否为"启用"状态
    			StorageGroup storageGroup = storageGroupAtomSV.queryByGroupIdAndSupplierId(tenantId,product.getSupplierId(),product.getStorageGroupId());
    			if (storageGroup==null){
    				throw new BusinessException("","对应库存组不存在,租户ID:"+tenantId+"库存组ID:"+product.getStorageGroupId());
    			}
    			storageGroupState = storageGroup.getState();
    		}
	        //库存组为停用或自动停用
	        if (StorageConstants.StorageGroup.State.STOP.equals(storageGroupState)|| StorageConstants.StorageGroup.State.AUTO_STOP.equals(storageGroupState)){
	            //若商品为"停用下架"则不处理
	            if (ProductConstants.Product.State.STOP.equals(product.getState())){
	                return;
	            }//若商品为"售罄下架",则变更为"停用下架"
	            else if(ProductConstants.Product.State.SALE_OUT.equals(product.getState())){
	                changeToStop(storageGroupState,product,operId);
	                return;
	            }
	        }
	        if (!StorageConstants.StorageGroup.State.ACTIVE.equals(storageGroupState)&& !StorageConstants.StorageGroup.State.AUTO_ACTIVE.equals(storageGroupState)){
	            throw new BusinessException("","库存组不是[启用]状态,租户ID:"+tenantId+"库存组ID:"+product.getStorageGroupId());
	        }
	        //检查缓存中商品的库存是否大于零
	        Long userNum = storageNumBusiSV.queryNowUsableNumOfGroup(tenantId,product.getStorageGroupId());
	        //若缓存中数据为零,则检查数据库中数据
	        if (userNum==null || userNum<1){
	            List<Storage> storageList = storageAtomSV.queryActive(tenantId,product.getProdId(),true);
	            //若存在可用量大于零的库存,则表示有库存,则设置为1,为概数
	            if (!CollectionUtil.isEmpty(storageList)){
	                userNum = 1l;
	            }
	        }
	        //若原状态为"售罄下架",且现在没有库存,则不处理
	        if (userNum<1&& ProductConstants.Product.State.SALE_OUT.equals(product.getState())){
	            return;
	        }
	        //直接切换至"售罄下架"
	        if (userNum<1){
	            product.setState(ProductConstants.Product.State.SALE_OUT);
	        }else { //切换至上架
	            product.setUpTime(DateUtils.currTimeStamp());
	            product.setState(ProductConstants.Product.State.IN_SALE);
	        }
	        //停用/售罄下架进行上架时,没有操作人
	        if (operId!=null){
	        	product.setOperId(operId);
	        }
	        //添加日志
	        updateProdAndStatusLog(product);
	        if (userNum>0) {
            //添加搜索引擎
            //skuIndexManage.updateSKUIndex(product.getProdId(),product.getUpTime().getTime());
        	//查询es
        	List<SearchCriteria> searchCriterias = new ArrayList<SearchCriteria>();
        	searchCriterias.add(new SearchCriteria("productid",
        			product.getProdId(),
        			new SearchOption(SearchOption.SearchLogic.must,  SearchOption.SearchType.querystring)));
        	
    		IProductSearch search = new ProductSearchImpl();
        	Result<SKUInfo> infoResult = search.searchByCriteria(searchCriterias, 0,  1, null);
        	if (CollectionUtil.isEmpty(infoResult.getContents())) {
        		logger.error("查询商品失败");
        		throw new BusinessException("查询es中的商品信息失败");
    		}
        	SKUInfo skuInfos = infoResult.getContents().get(0);
        	skuInfos.setState(product.getState());
        	skuInfos.setUptime(DateUtils.currTimeStamp().getTime());
        	List<SKUInfo> skuInfoList = new ArrayList<>();
        	skuInfoList.add(skuInfos);
        	SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace).bulkInsert (skuInfoList);
	        }
	    }
	 
	 private void changeToStop(String storageGroupState,Product product,Long operId){
	        //若库存组为"停用"或"自动停用"则设置为"停用下架"
	        if (StorageConstants.StorageGroup.State.AUTO_STOP.equals(storageGroupState)|| StorageConstants.StorageGroup.State.STOP.equals(storageGroupState)) {
	            product.setState(ProductConstants.Product.State.STOP);
	        }
	        //否则为"售罄停用"
	        else{
	        	product.setState(ProductConstants.Product.State.SALE_OUT);
	        }
	        //当库存售光时,操作者ID为null
	        if (operId!=null){
	        	product.setOperId(operId);
	        }
	        //添加日志
	        updateProdAndStatusLog(product);
	        //搜索中删除商品数据
	        //skuIndexManage.deleteSKUIndexByProductId(product.getProdId());
	        //查询es
        	List<SearchCriteria> searchCriterias = new ArrayList<SearchCriteria>();
        	searchCriterias.add(new SearchCriteria("productid",
        			product.getProdId(),
        			new SearchOption(SearchOption.SearchLogic.must,  SearchOption.SearchType.querystring)));
        	
    		IProductSearch search = new ProductSearchImpl();
        	Result<SKUInfo> infoResult = search.searchByCriteria(searchCriterias, 0,  1, null);
        	if (CollectionUtil.isEmpty(infoResult.getContents())) {
        		logger.error("查询商品失败");
        		throw new BusinessException("查询es中的商品信息失败");
    		}
        	SKUInfo skuInfos = infoResult.getContents().get(0);
        	skuInfos.setState(product.getState());
        	skuInfos.setUptime(DateUtils.currTimeStamp().getTime());
        	List<SKUInfo> skuInfoList = new ArrayList<>();
        	skuInfoList.add(skuInfos);
        	SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace).bulkInsert (skuInfoList);
	        
	    }
	 
	 public void updateProdAndStatusLog(Product product){
	        if (productAtomSV.updateById(product)>0){
	            ProductLog log = new ProductLog();
	            BeanUtils.copyProperties(log,product);
	            productLogAtomSV.install(log);
	            //商品状态日志表
	            ProductStateLog productStateLog = new ProductStateLog();
	            BeanUtils.copyProperties(productStateLog, product);
	            productStateLogAtomSV.insert(productStateLog);
	        }
	    }
	
	@SuppressWarnings("unused")
	private ProdPicture getProdPictureByProdId(String prodId,List<ProdPicture> list) {
		if(list!=null&&list.size()>0){
			for(ProdPicture pic:list){
				if(pic.getProdId().equalsIgnoreCase(prodId)){
					return pic;
				}
			}
		}
		return null;
	}
}
