package com.ai.slp.product.service.business.impl;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.opt.sdk.components.ses.SESClientFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.paas.ipaas.search.ISearchClient;
import com.ai.paas.ipaas.search.common.JsonBuilder;
import com.ai.paas.ipaas.search.vo.Result;
import com.ai.paas.ipaas.search.vo.SearchCriteria;
import com.ai.paas.ipaas.search.vo.SearchOption;
import com.ai.slp.product.api.storage.param.*;
import com.ai.slp.product.constants.ErrorCodeConstants;
import com.ai.slp.product.constants.NormProdConstants;
import com.ai.slp.product.constants.ProdPriceLogConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.constants.SearchConstants;
import com.ai.slp.product.constants.SkuStorageConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.bo.ProdPriceLog;
import com.ai.slp.product.dao.mapper.bo.product.ProdSku;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.storage.SkuStorage;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.dao.mapper.bo.storage.StorageLog;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdPriceLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProdAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.*;
import com.ai.slp.product.service.atom.interfaces.storage.ISkuStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageLogAtomSV;
import com.ai.slp.product.service.business.impl.search.ProductSearchImpl;
import com.ai.slp.product.service.business.interfaces.IStorageBusiSV;
import com.ai.slp.product.service.business.interfaces.search.IProductSearch;
import com.ai.slp.product.service.business.interfaces.search.ISKUIndexBusiSV;
import com.ai.slp.product.util.DataUtils;
import com.ai.slp.product.util.IPaasStorageUtils;
import com.ai.slp.product.util.MQConfigUtil;
import com.ai.slp.product.util.SkuStorageListComparator;
import com.ai.slp.product.util.StoNoSkuSalePriceComparator;
import com.ai.slp.product.util.StorageComparator;
import com.ai.slp.product.vo.StorageGroupPageQueryVo;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

/**
 * 库存业务操作
 *
 * Created by jackieliu on 16/5/5.
 */
@Service
@Transactional
public class StorageBusiSVImpl implements IStorageBusiSV {
	private static final Logger logger = LoggerFactory.getLogger(StorageBusiSVImpl.class);
	@Autowired
	IStorageGroupAtomSV storageGroupAtomSV;
	@Autowired
	IStorageAtomSV storageAtomSV;
	@Autowired
	IStorageLogAtomSV storageLogAtomSV;
	@Autowired
	ISkuStorageAtomSV skuStorageAtomSV;
	@Autowired
	IProdPriceLogAtomSV prodPriceLogAtomSV;
	@Autowired
	IStandedProductAtomSV standedProductAtomSV;
	@Autowired
	IProductAtomSV productAtomSV;
	@Autowired
	IProdSkuAtomSV prodSkuAtomSV;
	@Autowired
	IProdSkuLogAtomSV prodSkuLogAtomSV;
	@Autowired
	IStandedProdAttrAtomSV standedProdAttrAtomSV;
	@Autowired
	IProdCatAttrAtomSV prodCatAttrAtomSV;
	@Autowired
	IProductLogAtomSV productLogAtomSV;
	@Autowired
    IProductStateLogAtomSV productStateLogAtomSV;
	@Autowired
	ISKUIndexBusiSV iskuIndexManage;
	@Autowired
	StorageNumDbBusiSVImpl storageNumDbBusiSV;

	/**
	 * 废弃库存
	 *
	 * @param storage 要废弃的库存信息
	 */
	@Override
	public void discardStorage(String tenantId,Storage storage, Long operId,boolean isAuto) {
		Product product = productAtomSV.selectByGroupId(tenantId,storage.getStorageGroupId());
		//库存为启用,且商品为在售,则不允许进行废弃
		if (StorageConstants.Storage.State.ACTIVE.equals(storage.getState())
				&& product!=null
				&& ProductConstants.Product.State.IN_SALE.equals(product.getState())){
			throw new BusinessException("","对应商品在销售中,不允许废弃");
		}
		//如果库存为启用,需要从缓存中删除库存对应缓存信息
		if (StorageConstants.Storage.State.ACTIVE.equals(storage.getState())){
			storageNumDbBusiSV.subStorageCache(tenantId,storage);
		}
		//判断是否为自动废弃
		if (isAuto){
			storage.setState(StorageConstants.Storage.State.AUTO_DISCARD);
		}
		else{
			storage.setState(StorageConstants.Storage.State.DISCARD);
		}
		storage.setOperId(operId);
		if (storageAtomSV.updateById(storage) > 0) {
			StorageLog storageLog = new StorageLog();
			BeanUtils.copyProperties(storageLog, storage);
			storageLogAtomSV.installLog(storageLog);
		}
		// 废弃SKU库存
		skuStorageAtomSV.discardSkuStorageOfStorage(storage.getStorageId(), storage.getOperId());
	}

	/**
	 * 自动停用库存
	 *
	 * @param storage
	 */
	@Override
	public void autoStopStorage(Storage storage) {
		//售罄后自动停用,上级库存组停用自动停用
		// TODO...
	}

	/**
	 * 更改库存状态
	 *
	 * @param tenantId
	 * @param storageId
	 * @param state
	 * @param operId
	 */
	@Override
	public Storage changeStorageStats(String tenantId,String supplierId, String storageId, String state, Long operId) {
		// 查询库存是否存在
		Storage storage = storageAtomSV.queryNoDiscardById(storageId);
		if (storage == null) {
			throw new BusinessException("", "库存不存在或已废弃,库存标识:" + storageId);
		}
		// 查询状态是否变更
		if (storage.getState().equals(state)) {
			throw new BusinessException("", "状态已变更,不需重复变更");
		}

		switch (state) {
		case StorageConstants.Storage.State.ACTIVE:// 转启用
			// 调用启用库存方法
			activeStorage(tenantId,supplierId,storage,operId);
			break;
		case StorageConstants.Storage.State.STOP:// 转停用
			// 调用停用库存方法
			stopStorage(tenantId,storage,operId);
			break;
		case StorageConstants.Storage.State.DISCARD:// 转废弃
			discardStorage(tenantId,storage,operId,false);
			break;
		default:
			throw new BusinessException("", "无法识别的状态:" + state);
		}
		return storage;
	}

	/**
	 * 停用库存
	 * 商品必须为为非在售状态
	 *
	 * @param storage
	 * @author lipeng16
	 */
	private void stopStorage(String tenantId,Storage storage, Long operId) {
		//判断商品是否在售
		Product product = productAtomSV.selectByGroupId(tenantId,storage.getStorageGroupId());
		if (product!=null && ProductConstants.Product.State.IN_SALE.equals(product.getState())){
			throw new BusinessException("","对应商品在销售中,不允许停用");
		}
		//更新库存状态
		storage.setOperId(operId);
		storage.setState(StorageConstants.Storage.State.STOP);
		if (storageAtomSV.updateById(storage)>0){
			StorageLog storageLog = new StorageLog();
			BeanUtils.copyProperties(storageLog, storage);
			storageLogAtomSV.installLog(storageLog);
		}
	}

	/**
	 * 批量更新库存销售价
	 *
	 * @param salePriceReq
	 * @return
	 * @author lipeng16
	 */
	@Override
	public int updateNoSkuStoSalePrice(StoNoSkuSalePriceReq salePriceReq) {
		//K:优先级;V:价格
		List<StoNoSkuSalePrice> salePriceList = salePriceReq.getStorageSalePrice();
		if (CollectionUtil.isEmpty(salePriceList)){
			return 0;
		}
		int count = 0;
		Long operId = salePriceReq.getOperId();
		
		//对salePriceList进行排序   
		Collections.sort(salePriceList, new StoNoSkuSalePriceComparator());
		List<SKUInfo> list = new ArrayList<>();
		Map<String, Object> data = new HashMap<String, Object>();
		List<String> prodIdsList = new ArrayList<String>();
		List<Map<String,Object>> prodDatasList = new ArrayList<Map<String,Object>>(); 
		for(StoNoSkuSalePrice salePrice:salePriceList){
			// 库存标识为空,库存对应价格为空,库存销售价小于等于0,均不处理
			if (salePrice.getPriorityNumber()==null
					|| salePrice.getSalePrice() == null || salePrice.getSalePrice() <= 0) {
				logger.warn("库存标识为空或销售价不大于零,库存组标识[{}],销售价[{}]", salePrice.getGroupId(), salePrice.getPriorityNumber());
				continue;
			}
			count += updateSkuPrice(salePrice.getGroupId(),null,salePrice.getPriorityNumber(),salePrice.getSalePrice(),operId);
			
			
			//查询es里的标准品
//	    	List<SearchCriteria> searchCriterias = new ArrayList<SearchCriteria>();
//	    	searchCriterias.add(new SearchCriteria("storagegroupid",
//	    			salePrice.getGroupId(),
//	    			new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
//	    	
//	    	int startSize = 1;
//			int maxSize = 1;
//			// 最大条数设置
//			int pageNo = 1;
//			int size = 20;
//			if (pageNo == 1) {
//				startSize = 0;
//			} else {
//				startSize = (pageNo - 1) * size;
//			}
//			maxSize = size;
//			IProductSearch productSearch = new ProductSearchImpl();
//	    	Result<SKUInfo> result = productSearch.searchByCriteria(searchCriterias, startSize, maxSize, null);
//	    	if (CollectionUtil.isEmpty(result.getContents())) {
//	    		logger.error("查询商品失败");
//	    		throw new BusinessException("查询es中的商品信息失败");
//			}
//	    	SKUInfo product = result.getContents().get(0);
			
			String prodId = salePrice.getGroupId();//groupId与prodId共享主键
	
			//更新ES销售价
			prodIdsList.add(prodId);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("price", salePrice.getSalePrice());
			prodDatasList.add(map);
			
		}
		//SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace).bulkInsert(list);
		ISearchClient searchClient = SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace);
		searchClient.bulkUpdate(prodIdsList, prodDatasList);
		searchClient.refresh();
		return count;
	}

	/**
	 * 批量更新有销售属性库存销售价
	 *
	 * @param salePrice
	 * @return
	 * @author lipeng16
	 */
	@Override
	public int updateSkuStoSalePrice(StoSkuSalePrice salePrice) {
		String groupId = salePrice.getGroupId();
		Product product = productAtomSV.selectByGroupId(salePrice.getTenantId(),groupId);
		if (product == null){
			throw new BusinessException("","未找到对应商品信息");
		}
		Map<String,Long> priceMap = salePrice.getStorageSalePrice();
		//查询商品对应得SKU集合
//		List<ProdSku> skuList = prodSkuAtomSV.querySkuOfProd(salePrice.getTenantId(),product.getProdId());
		int count = 0;
		if (priceMap==null || priceMap.isEmpty()){
			return count;
		}
		for (Map.Entry<String,Long> entry:priceMap.entrySet()){
			count += updateSkuPrice(groupId,entry.getKey(),salePrice.getPriorityNum(),
					entry.getValue(),salePrice.getOperId());
		}
		return count;
	}

	private int updateSkuPrice(String groupId,String skuId,Short priorityNum,Long price,Long operId){
		//更新销售价 skuid  根 groupid 共享主建
		return skuStorageAtomSV.updateById4Service(groupId, priorityNum, price, operId);
	}
	/**
	 * 启用库存
	 * 
	 * @param storage
	 */
	private void activeStorage(String tenantId, String supplierId, Storage storage, Long operId) {
		//检查是否有启用库存
		int count = storageAtomSV.countOfActiveNoPrioritySelf(
				storage.getStorageGroupId(), storage.getPriorityNumber());
		if (count > 0) {
			throw new BusinessException("", "其他优先级存在启用库存,无法启用当前库存");
		}
		//判断库存下SKU库存是否已设置价格
		if (skuStorageAtomSV.queryNoPriceOfStorageById(storage.getStorageId())>0){
			throw new BusinessException("", "该库存价格设置不完整,无法启用");
		}
		//更新库存状态
		storage.setOperId(operId);
		storage.setState(StorageConstants.Storage.State.ACTIVE);
		if (storageAtomSV.updateById(storage)>0){
			StorageLog storageLog = new StorageLog();
			BeanUtils.copyProperties(storageLog, storage);
			storageLogAtomSV.installLog(storageLog);
		}
	}

	/**
	 * 查询标准品列表,包含标准品的库存组,适用于库存组定最低最高销售价初始页面信息查询<br>
	 * 库存组不包括废弃状态的
	 */
	@Override
	public PageInfoResponse<StorageGroup4SaleList> queryGroupsForSalePrice(StorageGroupQueryPage groupQuery) {
		StorageGroupPageQueryVo queryVo = new StorageGroupPageQueryVo();
		BeanUtils.copyProperties(queryVo, groupQuery);
		PageInfo<StorageGroup> StorageGroupPage = storageGroupAtomSV.queryPageOfSearch(queryVo);
		// 设置返回对象
		PageInfoResponse<StorageGroup4SaleList> StorageGroup4SaleListPage = new PageInfoResponse<>();
		StorageGroup4SaleListPage.setPageNo(groupQuery.getPageNo());
		StorageGroup4SaleListPage.setPageSize(groupQuery.getPageSize());
		StorageGroup4SaleListPage.setPageCount(StorageGroupPage.getPageCount());

		List<StorageGroup> storageGroupList = StorageGroupPage.getResult();
		// 新建结果集
		List<StorageGroup4SaleList> storGroup4SaleList = new ArrayList<>();
		for (StorageGroup storageGroup : storageGroupList) {
			StorageGroup4SaleList storageGroup4SaleList = new StorageGroup4SaleList();
			BeanUtils.copyProperties(storageGroup4SaleList, storageGroup);
			// 通过ID查标准品名称
			storageGroup4SaleList.setStandedProductName(standedProductAtomSV
					.selectById(groupQuery.getTenantId(), storageGroup.getStandedProdId()).getStandedProductName());
			// 填充结果集
			storGroup4SaleList.add(storageGroup4SaleList);
		}
		// 设置结果集
		StorageGroup4SaleListPage.setResult(storGroup4SaleList);
		return StorageGroup4SaleListPage;
	}

	/**
	 * 保存库存信息
	 * @return 新增库存ID
	 */
	@Override
	public String saveStorage(STOStorage stoStorage) {
		
		String tenantId = stoStorage.getTenantId();
		Long operId = stoStorage.getOperId();
		String groupId = stoStorage.getStorageGroupId();
		
//		查询指定库存组下的销售商品
		Product product = productAtomSV.selectByGroupId(tenantId, groupId);
		
		
		if(product == null){
			logger.warn("未找到对应的商品信息,租户ID:{},销售商ID:{},库存组ID:{},库存ID:{}",
					stoStorage.getTenantId());
			throw new BusinessException("", "找不到指定标示的商品:" + groupId);
		}
		String isSaleAttr = product.getIsSaleAttr();
		// 新增库存信息
		Storage storage = new Storage();
		BeanUtils.copyProperties(storage, stoStorage);
		storage.setProdId(product.getProdId());
		storage.setIsSaleAttr(isSaleAttr);
		// 设置优先级
		storage.setPriorityNumber(stoStorage.getPriorityNumber());
		storage.setCreateId(stoStorage.getOperId());
		//新增可用量为库存量
		storage.setUsableNum(stoStorage.getTotalNum());
		
		
		int saveNum = storageAtomSV.insertStorage(storage);
		
		
		if (saveNum <= 0) {
			logger.error("Install storage num is 0.\r\n{}", new Gson().toJson(storage));
			throw new BusinessException("","添加库存失败");
		}
		// 添加库存日志
//		StorageLog storageLog = new StorageLog();
//		BeanUtils.copyProperties(storageLog, storage);
		
//		storageLogAtomSV.installLog(storageLog);
		
		// 如果有销售属性,则添加SKU对应的库存信息
/*		if (isSaleAttr.equals(ProductConstants.Product.IsSaleAttr.YES)) {
			for (Map.Entry<String,Long> entry:stoStorage.getSkuStorageNum().entrySet()){
				Long price = getPriceOfSku(groupId,entry.getKey(),storage.getPriorityNumber());
				installSkuStorage(entry.getKey(),storage.getStorageId(),entry.getValue(),price,operId);
			}
		} else 
		if (isSaleAttr.equals(ProductConstants.Product.IsSaleAttr.NO)) {*/
			//通过商品id查出商品SKU信息,更新SKU库存信息
			
//			String skuId = prodSkuAtomSV.querySkuOfProd(tenantId, product.getProdId()).get(0).getSkuId();
			String skuId = product.getProdId();

			////////////
			Long salePrice = getSalePrice(tenantId, groupId, storage, skuId);
			/////////////
//			Long salePrice = getPriceOfSku(groupId,skuId,storage.getPriorityNumber());
			
			installSkuStorage(skuId,storage.getStorageId(),storage.getTotalNum(),salePrice,operId,stoStorage.getPriorityNumber(),stoStorage.getStorageGroupId());
			
			
	/*	} else {
			throw new BusinessException("", "不是有效的是否有销售属性状态" + isSaleAttr);
		}*/
		return storage.getStorageId();
		
	}

	private Long getSalePrice(String tenantId, String groupId, Storage storage, String skuId) {
		// 获取缓存客户端
		ICacheClient cacheClient = IPaasStorageUtils.getClient();
		// 获取库存组的cacheKey
		String groupKey = IPaasStorageUtils.genMcsStorageGroupKey(tenantId, groupId);

		// 确认当前使用优先级
		String priority = DataUtils.toStr(storage.getPriorityNumber());
		// 获取当前优先级中SKU的销售价
//			Long salePrice = getSalePrice(tenantId, groupId, skuId, price, cacheClient, priority);
		// 4.获取当前优先级中SKU的销售价
		String priceKey = IPaasStorageUtils.genMcsGroupSerialPriceKey(tenantId, groupId, priority);
		Long salePrice = null; 
		if (cacheClient.hget(priceKey, skuId) != null) {
			salePrice = Long.parseLong(cacheClient.hget(priceKey, skuId));
		}
		return salePrice;
	}

	/**
	 * 修改库存信息,只能修改名称和最低预警库存量-适用有和无销售属性两种状态
	 */
	@Override
	public int updateStorageNameWarn(STOStorage stoStorage) {
		String tenantId = stoStorage.getTenantId(),
				supplierId = stoStorage.getSupplierId(),
				storageId = stoStorage.getStorageId();
		if (StringUtils.isBlank(stoStorage.getStorageName()) && stoStorage.getWarnNum()==null){
			return 0;
		}
		Storage storage0 = storageAtomSV.queryAllStateStorage(stoStorage.getStorageId());
		if (storage0 == null
				|| StorageConstants.Storage.State.DISCARD.equals(storage0.getState())
				|| StorageConstants.Storage.State.AUTO_DISCARD.equals(storage0.getState())) {
			logger.warn("tenantId:{},supplierId:{},storageId:{}",tenantId,supplierId,storageId);
			throw new BusinessException("", "库存不存在或已废弃" + storageId);
		}
		StorageGroup group = storageGroupAtomSV.queryByGroupIdAndSupplierId(
				tenantId,supplierId,storage0.getStorageGroupId());
		if (group==null){
			logger.warn("tenantId:{},supplierId:{},storageId:{}",tenantId,supplierId,storageId);
			throw new BusinessException("", "未找到对应库存信息,库存ID:" + storageId);
		}
		Long operId = stoStorage.getOperId();
		// 如果存在则修改库存信息,只能修改名称和最低预警库存量 
		Storage storage = new Storage();
		storage.setStorageId(storageId);
		if (StringUtils.isNotBlank(stoStorage.getStorageName())) {
			storage.setStorageName(stoStorage.getStorageName());
			storage0.setStorageName(stoStorage.getStorageName());
		}
		if (stoStorage.getWarnNum() == null) {
			storage.setWarnNum(storage.getWarnNum());
			storage0.setWarnNum(storage.getWarnNum());
		}
		storage.setOperId(operId);
		int updateNum = storageAtomSV.updateById(storage);
		if (updateNum > 0) {
			// 更新库存日志信息
			StorageLog storageLog = new StorageLog();
			BeanUtils.copyProperties(storageLog, storage0);
			storageLogAtomSV.installLog(storageLog);
		}
		return updateNum;
	}

	/**
	 * 查看库存信息
	 */
	@Override
	public StorageRes queryStorageById(String tenantId,String supplierId,String storageId) {
		Storage storage = storageAtomSV.queryAllStateStorage(storageId);
		if (storage == null) {
			logger.warn("tenantId:{},supplierId:{},storageId:{}",tenantId,supplierId,storageId);
			throw new BusinessException("", "找不到指定标识的库存,库存ID" + storageId);
		}
		StorageGroup group = storageGroupAtomSV.queryByGroupIdAndSupplierId(
				tenantId,supplierId,storage.getStorageGroupId());
		if (group==null){
			logger.warn("tenantId:{},supplierId:{},storageId:{}",tenantId,supplierId,storageId);
			throw new BusinessException("", "未找到对应库存信息,库存ID:" + storageId);
		}
		StorageRes storageRes = new StorageRes();
		BeanUtils.copyProperties(storageRes, storage);
		return storageRes;
	}

	/**
	 * 查看SKU库存
	 */
	@Override
	public Map<String,SkuStorageInfo> querySkuStorageById(String tenantId, String supplierId, String storageId) {
		// 通过库存标识查询SKU库存集合
		List<SkuStorage> skuStorageList = skuStorageAtomSV.queryByStorageId(storageId,true);
		if (CollectionUtil.isEmpty(skuStorageList)) {
			return Collections.emptyMap();
		}
		Map<String,SkuStorageInfo> skuStoMap = new HashMap<>();
		// 通过SKU库存的SKU标识查对应的商品SKU
		for (SkuStorage skuStorage : skuStorageList) {
			String skuId = skuStorage.getSkuId();
			ProdSku prodSku = prodSkuAtomSV.querySkuById(tenantId,skuId,true);
			if (prodSku == null) {
				continue;
			}
			SkuStorageInfo skuStorageAndProd = new SkuStorageInfo();
			skuStorageAndProd.setSkuId(skuId);
			skuStorageAndProd.setSkuStorageId(storageId);
			skuStorageAndProd.setTotalNum(skuStorage.getTotalNum());
			skuStorageAndProd.setSaleAttrs(prodSku.getSaleAttrs());
			// 填充返回值
			skuStoMap.put(skuId,skuStorageAndProd);
		}
		return skuStoMap;
	}

	/**
	 * 新增SKU库存
	 */
	@Override
	public int insertSkuStorage(List<SkuStorageAdd> skuStorageAddList) {
		SkuStorage skuStorage = null;
		int count = 0;
		for (SkuStorageAdd skuStorageAdd : skuStorageAddList) {
			skuStorage = new SkuStorage();
			BeanUtils.copyProperties(skuStorage, skuStorageAdd);
			skuStorage.setState(SkuStorageConstants.SkuStorage.State.ACTIVE);
			int addNum = skuStorageAtomSV.install(skuStorage);
			count += addNum;
		}
		return count;
	}

	@Override
	public void updateStoragePriority(StoragePriorityCharge stoPriorityCharge) {
		if (stoPriorityCharge == null){
			logger.warn("库存组优先级变更信息为空");
			throw new BusinessException("","库存组优先级变更信息为空");
		}
		// 检查将移动优先级是否一致
		if (stoPriorityCharge.getNewLevel() == stoPriorityCharge.getOldLevel()){
			return;
		}
		// 获取库存组标识
		String groupId = stoPriorityCharge.getStorageGroupId();
		String tenantId = stoPriorityCharge.getTenantId();
		// 检查库存组是否存在
		if (storageGroupAtomSV.queryByGroupIdAndSupplierId(tenantId,stoPriorityCharge.getSupplierId(), groupId) == null) {
			logger.warn("找不到指定的库存组,租户表{},库存组标识{}",tenantId,groupId );
			throw new BusinessException("","指定库存组不存在,租户ID:"+tenantId+",库存组ID:"+groupId);
		}
		// 获取要优先级库存
		List<Storage> oldStorageList = storageAtomSV.queryStorageByGroupIdAndPriority(groupId,stoPriorityCharge.getOldLevel(),true);
		List<Storage> newStorageList = storageAtomSV.queryStorageByGroupIdAndPriority(groupId,stoPriorityCharge.getNewLevel(),true);
		changeStoragePriority(oldStorageList,stoPriorityCharge.getNewLevel());
		changeStoragePriority(newStorageList,stoPriorityCharge.getOldLevel());
	}

	/**
	 * 变更库存优先级
	 * @param storageList
	 * @param newLevel
     */
	private void changeStoragePriority(List<Storage> storageList,short newLevel){
		if (CollectionUtil.isEmpty(storageList)){
			return;
		}
		for (Storage storage:storageList){
			storage.setPriorityNumber(newLevel);
			if (storageAtomSV.updateById(storage)>0){
				StorageLog storageLog = new StorageLog();
				BeanUtils.copyProperties(storageLog, storage);
				storageLogAtomSV.installLog(storageLog);
			}
		}
	}

	private Long getPriceOfSku(String groupId,String skuId,Short priorityNum){
		List<SkuStorage> storageList = skuStorageAtomSV.queryPriorityOfGroup(groupId,skuId,priorityNum);
		if (CollectionUtil.isEmpty(storageList)){
			return null;
		}
		SkuStorage storage = storageList.get(0);
		return storage.getSalePrice();
	}

	/**
	 * 添加SKU库存
	 * @return sku库存标识
	 */
	private String installSkuStorage(String skuId,String storageId,Long totalNum,Long price,Long operId,Short priorityNumber,String storageGroupId){
		// 新增SKU虚拟库存,数据来自虚拟库存和单品SKU
		SkuStorage skuStorage = new SkuStorage();
		skuStorage.setSkuId(skuId);
		skuStorage.setStorageId(storageId);
		skuStorage.setTotalNum(totalNum);
		skuStorage.setPriorityNumber(priorityNumber);
		skuStorage.setStorageGroupId(storageGroupId);
		if (price != null) {
			skuStorage.setSalePrice(price);
		}
		skuStorage.setState(SkuStorageConstants.SkuStorage.State.ACTIVE);
		skuStorage.setOperId(operId);
		skuStorage.setUsableNum(totalNum);
		skuStorageAtomSV.install(skuStorage);
		return skuStorage.getSkuStorageId();
	}
}
