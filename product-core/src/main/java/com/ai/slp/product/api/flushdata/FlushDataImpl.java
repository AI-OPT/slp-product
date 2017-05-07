package com.ai.slp.product.api.flushdata;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.components.ses.SESClientFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.paas.ipaas.search.ISearchClient;
import com.ai.slp.product.api.flushdata.interfaces.IFlushDataSV;
import com.ai.slp.product.api.flushdata.params.FlushDataRequest;
import com.ai.slp.product.api.product.param.ProductQueryInfo;
import com.ai.slp.product.api.productcomment.param.PictureVO;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.constants.SearchConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.attach.ProdSkuInfoSes;
import com.ai.slp.product.dao.mapper.bo.ProdComment;
import com.ai.slp.product.dao.mapper.bo.ProdCommentCriteria;
import com.ai.slp.product.dao.mapper.bo.ProdCommentPicture;
import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.bo.product.ProdPicture;
import com.ai.slp.product.dao.mapper.bo.product.ProdSaleAll;
import com.ai.slp.product.dao.mapper.bo.product.ProdTargetArea;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.storage.SkuStorage;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.dao.mapper.interfaces.ProdCommentMapper;
import com.ai.slp.product.search.bo.CategoryInfo;
import com.ai.slp.product.search.bo.ImageInfo;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.search.bo.SaleAreaInfo;
import com.ai.slp.product.service.atom.interfaces.IProdCatDefAtomSV;
import com.ai.slp.product.service.atom.interfaces.comment.IProdCommentPictureAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdPictureAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSaleAllAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdTargetAreaAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.ISkuStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.business.impl.StorageNumDbBusiSVImpl;
import com.ai.slp.product.service.business.interfaces.INormProductBusiSV;
import com.ai.slp.product.util.CommonUtils;
import com.ai.slp.product.util.ConvertUtils;
import com.ai.slp.product.util.IPaasStorageUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;


/**
 * 批量刷新商品缓存信息服务
 * Date: 2017年5月3日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author
 */
@Service
@Component
public class FlushDataImpl implements IFlushDataSV{

	@Autowired
	IProdSkuAtomSV prodSkuAtomSV;

	@Autowired
	IProdPictureAtomSV prodPictureAtomSV;
	@Autowired
	IProdCommentPictureAtomSV prodCommentPictureAtomSV;
	@Autowired
	IProdTargetAreaAtomSV prodTargetAreaAtomSV;
	@Autowired
	INormProductBusiSV normProductBusiSV;
	@Autowired
	IProductAtomSV productAtomSV;
	@Autowired
	ISkuStorageAtomSV skuStorageAtomSV;
	@Autowired
	IProdSaleAllAtomSV prodSaleAllAtomSV;
	@Autowired
	IProdAttrAtomSV prodAttrAtomSV;
	@Autowired
	IProdCatDefAtomSV prodCatDefAtomSV;
	@Autowired
	IStorageAtomSV storageAtomSV;
	@Autowired
	IStorageGroupAtomSV storageGroupAtomSV;
	@Autowired
	StorageNumDbBusiSVImpl storageNumDbBusiSV;
	@Autowired
	ProdCommentMapper prodCommentMapper;

	/**
	 * 刷新商品数据
	 */
	@Override
	public BaseResponse flushProductData(FlushDataRequest request) {
		//查询所有符合条件商品
		ProductQueryInfo productQueryInfo = new ProductQueryInfo();
		if(!StringUtils.isBlank(request.getProdName())){
			productQueryInfo.setProdName(request.getProdName());
		}
		productQueryInfo.setPageNo(request.getPageNo());
		productQueryInfo.setPageSize(request.getPageSize());
		List<String> states = new ArrayList<>();
		states.add("5");
		productQueryInfo.setStateList(states);
        PageInfo<Product> products = productAtomSV.selectPageForInsale(productQueryInfo);
        Integer count = 0;
        List<String> idList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(products.getResult())) {
			/**
			 * 查询数据库组装信息
			 */
			for (Product product : products.getResult()) {
				List<ProdSkuInfoSes> skuInfoSesList = prodSkuAtomSV.queryOfProdForSearch(product.getProdId());
				if (CollectionUtil.isEmpty(skuInfoSesList)) {
					continue;
				}
				List<SKUInfo> skuInfoList = fillSkuInfo(skuInfoSesList);
				if(CollectionUtils.isEmpty(skuInfoList)){
					continue;
				}
				ISearchClient searchClient = SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace);
				searchClient.bulkInsert(skuInfoList);
				searchClient.refresh();
				count++;
				idList.add(skuInfoList.get(0).getProductid());
			}
		}
		return CommonUtils.genSuccessResponse("共刷入:"+count.toString()+"条商品数据;/n"+"商品ID有:"+JSONArray.toJSONString(idList));
	}

	/**
	 * 刷新商品评论
	 */
	@Override
	public BaseResponse flushCommentData(FlushDataRequest request) {
		ProdCommentCriteria example = new ProdCommentCriteria();
		example.setLimitStart((request.getPageNo()-1)*request.getPageSize());
		example.setLimitEnd(request.getPageSize());
		List<ProdComment> prodComments = prodCommentMapper.selectByExample(example);
		Map<String, List<PictureVO>> pictureMap = new HashMap<>();
		if(!CollectionUtils.isEmpty(prodComments)){
			 for (ProdComment prodComment : prodComments) {
				List<ProdCommentPicture> prodCommentPictures = prodCommentPictureAtomSV.queryPictureListByCommentId(prodComment.getCommentId());
				List<PictureVO> pictureVOs = new ArrayList<>();
				for (ProdCommentPicture prodCommentPicture : prodCommentPictures) {
					PictureVO pictureVO = new PictureVO();
					BeanUtils.copyProperties(pictureVO, prodCommentPicture);
					pictureVOs.add(pictureVO);
				}
				pictureMap.put(prodComment.getCommentId(), pictureVOs);
			}
		}
		/**
		 * 加缓存
		 */
		Integer count = ConvertUtils.flushCommentInfo(prodComments, pictureMap);
		return CommonUtils.genSuccessResponse("共刷入:"+count.toString()+"条商品评论数据");
	}
	
	/**
	 * 填充商品数据
	 * @param skuInfoSesList
	 * @return
	 * @author
	 */
	public List<SKUInfo> fillSkuInfo(List<ProdSkuInfoSes> skuInfoSesList) {
		List<SKUInfo> skuInfoList = new ArrayList<>();
		for (ProdSkuInfoSes prodSkuInfo : skuInfoSesList) {
			SKUInfo skuInfo = new SKUInfo();
			BeanUtils.copyProperties(skuInfo, prodSkuInfo);
			Timestamp upTime = prodSkuInfo.getProdUpTime();
			skuInfo.setUptime(upTime == null ? DateUtil.getCurrentTimeMillis() : upTime.getTime());
			// 类目
			skuInfo.setCategoryinfos(new ArrayList<CategoryInfo>());
			// 设置类目名称
			ProductCat cat = prodCatDefAtomSV.selectById(prodSkuInfo.getTenantid(), prodSkuInfo.getProductcategoryid());
			if (cat != null) {
				skuInfo.setProductcatname(cat.getProductCatName());
			}

			fetchCategory(skuInfo, prodSkuInfo.getProductcategoryid());
			// 属性
			skuInfo.setAttrinfos(prodAttrAtomSV.queryAttrOfProdId(prodSkuInfo.getProductid()));
			// 销售量
			ProdSaleAll prodSaleAll = prodSaleAllAtomSV.querySaleAllOfSku(prodSkuInfo.getTenantid(),
					prodSkuInfo.getSkuid());
			skuInfo.setSalenum(prodSaleAll == null ? 0 : prodSaleAll.getSaleNum());
			// 图片
			fillSKUImageInfo(skuInfo, prodSkuInfo.getProductcategoryid(), prodSkuInfo.getProductid(),
					prodSkuInfo.getSkuid());
			// 价格
			/*
			 * skuInfo.setPrice(skuStorageAtomSV.queryPriceOfSku(
			 * prodSkuInfo.getTenantid(),prodSkuInfo.getProductid(),prodSkuInfo.
			 * getSkuid()));
			 */
			Long priceOfSku = skuStorageAtomSV.queryPriceOfSku(prodSkuInfo.getTenantid(), prodSkuInfo.getProductid(),
					prodSkuInfo.getSkuid());
			if (priceOfSku != null) {
				skuInfo.setPrice(priceOfSku);
			}
			// 市场价
			Product product = productAtomSV.selectByProductId(prodSkuInfo.getTenantid(),
					prodSkuInfo.getSkuid());
			if(null==product){
				continue;
			}
			skuInfo.setOperid(null==product.getOperId()?"0":product.getOperId()+"");
			skuInfo.setMarketprice(null==product.getMarketPrice() ? 0 : product.getMarketPrice());
			// 是否全国销售
			skuInfo.setSalenationwide(product.getIsSaleNationwide());
			// 发票
			skuInfo.setIsinvoice(product.getIsInvoice());
			// 上架类型
			skuInfo.setUpshelftype(product.getUpshelfType());
			// 图文描述
			skuInfo.setProdetailcontent(product.getProDetailContent());
			skuInfo.setStoragegroupid(product.getStorageGroupId());
			// 商品状态
			if (product.getState() != null) {
				skuInfo.setState(product.getState());
			}
			// 标准品状态
			StandedProduct standedProduct = normProductBusiSV.queryById(prodSkuInfo.getTenantid(),
					prodSkuInfo.getProductid());
			if(null==standedProduct){
				continue;
			}
			if (null!=standedProduct.getState()) {
				skuInfo.setStandprodstate(standedProduct.getState());
			}
			// 标准品的producttype(实物 虚拟)
			if (standedProduct.getProductType() == null) {
				throw new BusinessException("标准品的ProductType 为null");
			}
			skuInfo.setProducttype(standedProduct.getProductType());
			// 標準品创建时间
			skuInfo.setCreatetime(standedProduct.getCreateTime().getTime());
			skuInfo.setSupplierid(standedProduct.getSupplierId());
			// 受众
			// skuInfo.setAudiences(fillSKUAudiences(prodSkuInfo.getTenantid(),prodSkuInfo.getProductid()));
			// 销售地域
			List<SaleAreaInfo> areaInfoList = new ArrayList<>();
			// 若不是全国销售,则查询销售地域
			if (ProductConstants.Product.IsSaleNationwide.NO.equals(prodSkuInfo.getSalenationwide())) {
				areaInfoList = fillSKUSaleArea(prodSkuInfo.getTenantid(), prodSkuInfo.getProductid());
			}
			skuInfo.setSaleareainfos(areaInfoList);
			//刷新redis
			//查询Storagegroup
			StorageGroup storageGroup = storageGroupAtomSV.queryByGroupId(CommonConstants.TENANT_ID, product.getStorageGroupId());
			String tenantId = CommonConstants.TENANT_ID,groupId = storageGroup.getStorageGroupId();
			ICacheClient cacheClient = IPaasStorageUtils.getClient();
			//获取库存组的cacheKey
			String groupKey = IPaasStorageUtils.genMcsStorageGroupKey(tenantId,groupId);
			cacheClient.hset(groupKey,StorageConstants.IPass.McsParams.GROUP_STATE_HTAGE,"1");
			
			//skustorage
			List<Storage> storages = storageAtomSV.queryOfGroup(CommonConstants.TENANT_ID, storageGroup.getStorageGroupId());
			if(!CollectionUtils.isEmpty(storages)){
				Storage storage = new Storage();
				SkuStorage skuStorage = new SkuStorage();
				BeanUtils.copyProperties(storage, storages.get(0));
				List<SkuStorage> skuStorages = skuStorageAtomSV.queryByStorageId(storage.getStorageId());
				if(!CollectionUtils.isEmpty(skuStorages)){
					BeanUtils.copyProperties(skuStorage, skuStorages.get(0));
				}
				Short priority = storage.getPriorityNumber();
				flushStorageCache(groupId);
	            Set<String> skuIds = new HashSet<>();
	            skuIds.add(skuInfo.getSkuid());
	            flushStorageCache(storage, skuIds);
				//优先级总可用量(F)
		        String priorityUsableKey = IPaasStorageUtils.genMcsPriorityUsableKey(tenantId,groupId,priority.toString());
		        String skuUsableKey = IPaasStorageUtils.genMcsSerialSkuUsableKey(tenantId,groupId,priority.toString());
				 //设库存
	            String skuStorageKey = IPaasStorageUtils.genMcsSkuStorageUsableKey(
	                    tenantId,groupId,storage.getPriorityNumber().toString(),skuStorage.getSkuId());
	            if(null!=skuStorage.getSkuStorageId()){
	            	cacheClient.zadd(skuStorageKey,1000,skuStorage.getSkuStorageId());
	            }
	            //设置SKU库存可用量
	            if(null!=skuStorage.getUsableNum()){
	            	cacheClient.hincrBy(skuUsableKey,skuStorage.getSkuId(),skuStorage.getUsableNum());
	            	//设置优先级可用量
	            	cacheClient.incrBy(priorityUsableKey,skuStorage.getUsableNum());
	            }
	            String priceKey = IPaasStorageUtils.genMcsGroupSerialPriceKey(tenantId, groupId, priority.toString());
	            cacheClient.hset(priceKey, skuInfo.getSkuid(), String.valueOf(skuInfo.getPrice()));
	            skuInfoList.add(skuInfo);
			}
		}
		return skuInfoList;
	}
	
	
	/**
	 * 填充图片信息
	 * @param skuInfo
	 * @param prodCatId
	 * @param prodId
	 * @param skuId
	 * @author
	 */
	public void fillSKUImageInfo(SKUInfo skuInfo, String prodCatId, String prodId, String skuId) {
		ProdPicture prodPicture = prodPictureAtomSV.queryMainOfProd(prodId);
		ImageInfo imageInfo = prodPicture == null ? null
				: new ImageInfo(prodPicture.getPicType(), prodPicture.getVfsId());

		skuInfo.setImageinfo(imageInfo);
		// 查询该商品其他属性值的主图
		// skuInfo.setThumbnail(prodPictureAtomSV.queryAttrValOfProd(prodId));
	}

	/**
	 * 填充商品地域信息
	 * @param tenantId
	 * @param productId
	 * @return
	 * @author
	 */
	public List<SaleAreaInfo> fillSKUSaleArea(String tenantId, String productId) {
		List<ProdTargetArea> targetAreaList = prodTargetAreaAtomSV.searchProdTargetArea(tenantId, productId);
		List<SaleAreaInfo> saleAreaInfoList = new ArrayList<>();
		if (CollectionUtil.isEmpty(targetAreaList)) {
			return saleAreaInfoList;
		}
		for (ProdTargetArea targetArea : targetAreaList) {
			saleAreaInfoList.add(new SaleAreaInfo(targetArea.getProvCode().toString()));
		}
		return saleAreaInfoList;
	}

	/**
	 * 获取类目信息
	 * @param skuInfo
	 * @param prodCatId
	 * @author
	 */
	private void fetchCategory(SKUInfo skuInfo, String prodCatId) {
		ProductCat productCat = prodCatDefAtomSV.selectById(prodCatId);
		if (productCat == null) {
			return;
		}

		CategoryInfo categoryInfo = new CategoryInfo(productCat.getProductCatId(), productCat.getProductCatName());
		skuInfo.addCategoryInfo(categoryInfo);
		String categoryId = productCat.getParentProductCatId();

		if (!"0".equals(categoryId)) {
			fetchCategory(skuInfo, categoryId);
		} else {
			skuInfo.setRootcategorid(categoryId);
		}
	}
	
	/**
	 * 刷新库存缓存
	 * @param storage
	 * @param skuIds
	 * @author
	 */
	private void flushStorageCache(Storage storage,Set<String> skuIds){
		   String tenantId = CommonConstants.TENANT_ID;
		   ICacheClient cacheClient = IPaasStorageUtils.getClient();
	        Short priority = storage.getPriorityNumber();
	        String groupId = storage.getStorageGroupId();
	        //优先级总可用量(F)
	        String priorityUsableKey = IPaasStorageUtils.genMcsPriorityUsableKey(tenantId,groupId,priority.toString());
	        String skuUsableKey = IPaasStorageUtils.genMcsSerialSkuUsableKey(tenantId,groupId,priority.toString());
	        //查询库存下SKU库存
	        List<SkuStorage> skuStorageList = skuStorageAtomSV.queryByStorageId(storage.getStorageId());
	        for (SkuStorage skuStorage:skuStorageList){
	            //若没有将sku可用量进行初始操作,则需要将sku可用量设置为零
	            if (skuIds!=null && !skuIds.contains(skuStorage.getSkuId())){
	                cacheClient.hset(skuUsableKey,skuStorage.getSkuId(),"0");
	            }
	            //设置SKU库存
	            String skuStorageKey = IPaasStorageUtils.genMcsSkuStorageUsableKey(
	                    tenantId,groupId,storage.getPriorityNumber().toString(),skuStorage.getSkuId());
	            cacheClient.zadd(skuStorageKey,skuStorage.getUsableNum(),skuStorage.getSkuStorageId());
	            //设置SKU库存可用量
	            cacheClient.hincrBy(skuUsableKey,skuStorage.getSkuId(),skuStorage.getUsableNum());
	            //设置优先级可用量
	            cacheClient.incrBy(priorityUsableKey,skuStorage.getUsableNum());
	        }
	    }
	   
	   /**
		 * 刷新库存组缓存
		 * @param storageGroup
	     */
		public void flushStorageCache(String storageGroupId){
			StorageGroup storageGroup = storageGroupAtomSV.queryByGroupId(CommonConstants.TENANT_ID, storageGroupId);
			if(null==storageGroup){
				return;
			}
			String tenantId = storageGroup.getTenantId(),groupId = storageGroup.getStorageGroupId();
			ICacheClient cacheClient = IPaasStorageUtils.getClient();
			//获取库存组的cacheKey
			String groupKey = IPaasStorageUtils.genMcsStorageGroupKey(tenantId,groupId);
			//查询所有截止时间在当前时间之后的促销的库存,不包括废弃库存
			List<Storage> storageList = storageAtomSV.queryTimeStorageOfGroup(storageGroup.getStorageGroupId(),false);
			List<Short> priorityNumList = new ArrayList<>();
			//如果为空,则查询所有有效期截止时间在当前时间之后的促销优先级,包括已废弃
			if (CollectionUtil.isEmpty(storageList)){
				storageList = storageAtomSV.queryTimeStorageOfGroup(storageGroup.getStorageGroupId(),true);
				cleanTimeStorageCache(tenantId,groupId,storageList);
			} else{
				for (Storage storage : storageList) {
					//若已经处理,则进行下一个
					if (priorityNumList.contains(storage.getPriorityNumber())){
						continue;
					}
					priorityNumList.add(storage.getPriorityNumber());
					storageNumDbBusiSV.flushPriorityStorage(tenantId, groupId, storage.getPriorityNumber(), false);
				}
			}
			//查询当前启用库存
			List<Storage> activeList = storageAtomSV.queryActive(tenantId,groupId,false);
			//存在当前启用库存,则加载当前启用库存所属优先级
			if (!CollectionUtil.isEmpty(activeList)){
				Storage storage = activeList.get(0);
				storageNumDbBusiSV.flushPriorityStorage(tenantId,groupId,storage.getPriorityNumber(),false);
				//设置当前启用优先级
				cacheClient.hset(groupKey,StorageConstants.IPass.McsParams.GROUP_SERIAL_HTAGE,storage.getPriorityNumber().toString());
			}
			//若无启用库存,则将当前的库存设置为零
			else {
				//获取当前优先级,
				String priority = cacheClient.hget(groupKey,StorageConstants.IPass.McsParams.GROUP_SERIAL_HTAGE);
				cleanStorageCache(tenantId,groupId,priority);
			}
			//设置库存组状态
			cacheClient.hset(groupKey,StorageConstants.IPass.McsParams.GROUP_STATE_HTAGE,storageGroup.getState());
		}
		
		/**
		 * 清空缓存中库存数据
		 */
		private void cleanStorageCache(String tenantId,String groupId,String priority){
			//将对应的c,e,f库存使用量设置为零
			ICacheClient cacheClient = IPaasStorageUtils.getClient();
			//c 利用HINCRBY的特性,会将没有的field设置为零,然后进行处理
			String usableNumKey = IPaasStorageUtils.genMcsSerialSkuUsableKey(tenantId,groupId,priority);
			Map<String,String> usableNumMap = cacheClient.hgetAll(usableNumKey);
			if (usableNumMap!=null && usableNumMap.size()>0) {
				String[] mapKey = new String[usableNumMap.size()];
				usableNumMap.keySet().toArray(mapKey);
				cacheClient.hdel(usableNumKey, mapKey);
			}
			//e 的数量先不做处理
			//f 将优先级库存可用量设置为零
			String priorityUsableKey = IPaasStorageUtils.genMcsPriorityUsableKey(tenantId,groupId,priority);
			cacheClient.set(priorityUsableKey,"0");
		}
		
		/**
		 * 清空促销库存
		 */
		private void cleanTimeStorageCache(String tenantId,String groupId,List<Storage> storageList){
			if (CollectionUtil.isEmpty(storageList)){
				return;
			}
			ICacheClient cacheClient = IPaasStorageUtils.getClient();
			List<String> cKeyList = new ArrayList<>();
			List<String> fKeyList = new ArrayList<>();
			for (Storage storage:storageList){
				String priority = storage.getPriorityNumber().toString();
				cKeyList.add(IPaasStorageUtils.genMcsSerialSkuUsableKey(tenantId,groupId,priority));
				fKeyList.add(IPaasStorageUtils.genMcsPriorityUsableKey(tenantId,groupId,priority));
			}
			if (!CollectionUtil.isEmpty(cKeyList)){
				cacheClient.del(cKeyList.toArray(new String[cKeyList.size()]));
			}
			if (!CollectionUtil.isEmpty(fKeyList)){
				cacheClient.del(fKeyList.toArray(new String[fKeyList.size()]));
			}

		}

}
