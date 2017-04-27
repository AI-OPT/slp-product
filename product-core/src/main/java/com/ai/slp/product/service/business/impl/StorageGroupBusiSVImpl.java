package com.ai.slp.product.service.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.paas.ipaas.search.vo.Result;
import com.ai.paas.ipaas.search.vo.SearchCriteria;
import com.ai.paas.ipaas.search.vo.SearchOption;
import com.ai.slp.product.api.storage.param.NameUpReq;
import com.ai.slp.product.api.storage.param.STOStorageGroup;
import com.ai.slp.product.api.storage.param.StorageGroup4List;
import com.ai.slp.product.api.storage.param.StorageGroupOfNormProdPage;
import com.ai.slp.product.api.storage.param.StorageGroupQueryPage;
import com.ai.slp.product.api.storage.param.StorageGroupRes;
import com.ai.slp.product.api.storage.param.StorageGroupRestwo;
import com.ai.slp.product.api.storage.param.StorageGroupSalePrice;
import com.ai.slp.product.api.storage.param.StorageRes;
import com.ai.slp.product.constants.ProdPriceLogConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.constants.RouteConstants;
import com.ai.slp.product.constants.StandedProductConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.attach.StorageGroupAttach4List;
import com.ai.slp.product.dao.mapper.bo.ProdPriceLog;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.bo.product.ProdSku;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.storage.SkuStorage;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroupLog;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdPriceLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.ISkuStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageLogAtomSV;
import com.ai.slp.product.service.business.impl.search.ProductSearchImpl;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageGroupBusiSV;
import com.ai.slp.product.service.business.interfaces.search.IProductSearch;
import com.ai.slp.product.util.IPaasStorageUtils;
import com.ai.slp.product.vo.StoGroupPageQueryVo;
import com.ai.slp.route.api.routegroupmanage.interfaces.IRouteGroupManageSV;
import com.ai.slp.route.api.routegroupmanage.param.RouteGroupStateRequest;
import com.ai.slp.route.api.routegroupmanage.param.RouteGroupStateResponse;

/**
 * 库存组操作 Created by jackieliu on 16/5/5.
 */
@Service
@Transactional
public class StorageGroupBusiSVImpl implements IStorageGroupBusiSV {
	private static final Logger logger = LoggerFactory.getLogger(StorageGroupBusiSVImpl.class);
	@Autowired
	IStandedProductAtomSV standedProductAtomSV;
	@Autowired
	IStorageGroupAtomSV storageGroupAtomSV;
	@Autowired
	IStorageGroupLogAtomSV storageGroupLogAtomSV;
	@Autowired
	IProductAtomSV productAtomSV;
	@Autowired
	IProductBusiSV productBusiSV;
	@Autowired
	IStorageAtomSV storageAtomSV;
	@Autowired
	IStorageLogAtomSV storageLogAtomSV;
	@Autowired
	IProdPriceLogAtomSV prodPriceLogAtomSV;
	@Autowired
	IStorageBusiSV storageBusiSV;
	@Autowired
	ISkuStorageAtomSV skuStorageAtomSV;
	@Autowired
	StorageNumDbBusiSVImpl storageNumDbBusiSV;
	@Autowired
	IProdCatAttrAtomSV prodCatAttrAtomSV;
	@Autowired
	IProdSkuAtomSV prodSkuAtomSV;

	/**
	 * 添加库存组
	 *
	 * @param storageGroup
	 * @return 新增库存组ID
	 */
	@Override
	public String addGroup(STOStorageGroup storageGroup) {
		StorageGroup group = addGroupObj(storageGroup);
		if(group!=null){
			return group.getStorageGroupId();
		}
		return null;
	}
	
	/**
	 * 添加库存组
	 * 
	 * @param storageGroup
	 * @return
	 * @author Gavin
	 * @UCUSER
	 */
	@Override
	public StorageGroup addGroupObj(STOStorageGroup storageGroup) {
		//通过是否查询到相关属性来设定库存组是否有销售属性
		StorageGroup group = new StorageGroup();
		BeanUtils.copyProperties(group, storageGroup);
		// 添加库存组信息,状态默认为停用
		group.setState(StorageConstants.StorageGroup.State.STOP);
		int installNum = storageGroupAtomSV.installGroup(group);
		return group;
	}
	
	
	/**
	 * 添加库存组
	 * 
	 * @param storageGroup
	 * @return
	 * @author Gavin
	 * @UCUSER
	 */
	@Override
	public StorageGroup addGroupObj4Service(StandedProduct standedProduct) {
		//通过是否查询到相关属性来设定库存组是否有销售属性
		StorageGroup group = new StorageGroup();
		//BeanUtils.copyProperties(group, storageGroup);
		//与,商品共享主建
		//group.setStorageGroupId(standedProduct.getStandedProdId());
		group.setTenantId(standedProduct.getTenantId());
		group.setCreateId(standedProduct.getOperId());
		group.setStandedProdId(standedProduct.getStandedProdId());
		group.setSupplierId(standedProduct.getSupplierId());
		group.setStorageGroupName(StorageConstants.StorageGroup.DEFAULT_NAME);
		// 添加库存组信息,状态默认为停用
		group.setState(StorageConstants.StorageGroup.State.STOP);
		int installNum = storageGroupAtomSV.installGroup(group,standedProduct.getStandedProdId());
		return group;
	}

	@Override
	public StorageGroup addGroupObj(STOStorageGroup storageGroup,String storageGroupId) {
		//通过是否查询到相关属性来设定库存组是否有销售属性
		StorageGroup group = new StorageGroup();
		BeanUtils.copyProperties(group, storageGroup);
		group.setState(StorageConstants.StorageGroup.State.STOP);
		// 添加库存组信息,状态默认为停用
		if(null==storageGroupId){
			storageGroupAtomSV.installGroup(group);
		}else{
			storageGroupAtomSV.installGroup(group,storageGroupId);
		}
		return group;
	}

	/**
	 * 更新库存组
	 *
	 * @param storageGroup
	 * @return
	 */
	@Override
	public int updateGroupName(NameUpReq storageGroup) {
		// 查询库存组是否存在
		StorageGroup group = storageGroupAtomSV.queryByGroupIdAndSupplierId(
				storageGroup.getTenantId(),storageGroup.getSupplierId(),storageGroup.getId());
		if (group == null){
			throw new BusinessException("",
					"要更新库存组信息不存在,租户ID:" + storageGroup.getTenantId() + ",库存组标识:" + storageGroup.getId());
		}
		// 已废弃,不允许变更
		if (StorageConstants.StorageGroup.State.DISCARD.equals(group.getState())
				|| StorageConstants.StorageGroup.State.AUTO_DISCARD.equals(group.getState()))
		{
			throw new BusinessException("", "库存组已经废弃,不允许更新信息");
		}
		// 设置可更新信息
		group.setStorageGroupName(storageGroup.getName());
		// group.setSerialNumber(storageGroup.getSerialNumber());
		group.setOperId(storageGroup.getOperId());
		int updateNum = storageGroupAtomSV.updateById(group);
		// 添加库存组日志
		if (updateNum > 0) {
			StorageGroupLog groupLog = new StorageGroupLog();
			BeanUtils.copyProperties(groupLog, group);
			storageGroupLogAtomSV.install(groupLog);
		}
		return updateNum;
	}

	/**
	 * 查询指定标准品下的库存组信息,包括库存信息
	 *
	 * @param tenantId
	 * @param productId
	 * @return
	 */
	@Override
	public List<StorageGroup> queryGroupInfoByNormProId(String tenantId,String supplierId, String productId) {
		// 查询出标准品下的所有库存组,创建时间倒序
		List<StorageGroup> groupList = storageGroupAtomSV.queryOfStandedProd(tenantId,supplierId, productId);
		if (CollectionUtil.isEmpty(groupList)){
			logger.warn("查询库存组列表为空,租户ID:{},销售商ID:{},标准品ID:{}",tenantId,supplierId,productId);
		}
		
		return groupList;
	}
/*	public List<StorageGroupRes> queryGroupInfoByNormProId(String tenantId,String supplierId, String productId) {
		StandedProduct standedProduct = standedProductAtomSV.selectById(tenantId, productId);
		if (standedProduct == null) {
			logger.warn("未找到对应的标准品信息,租户ID:{},标准品标识:{}",tenantId,productId);
			throw new BusinessException("", "未找到对应的标准品信息");
		}
		// 查询出标准品下的所有库存组,创建时间倒序
		List<StorageGroupRes> groupInfoList = new ArrayList<>();
		List<StorageGroup> groupList = storageGroupAtomSV.queryOfStandedProd(tenantId,supplierId, productId);
		if (CollectionUtil.isEmpty(groupList)){
			logger.warn("查询库存组列表为空,租户ID:{},销售商ID:{},标准品ID:{}",tenantId,supplierId,productId);
		}
		for(int i= groupList.size()-1;i>=0;i--){
			groupInfoList.add(genStorageGroupInfo(groupList.get(i)));
		}
		return groupInfoList;
	}*/

	/**
	 * 查询单个库存组的信息
	 *
	 * @param tenantId
	 * @param groupId
	 * @return
	 */
	@Override
	public StorageGroupRes queryGroupInfoByGroupId(String tenantId,String supplierId, String groupId) {
		StorageGroup storageGroup = storageGroupAtomSV.queryByGroupIdAndSupplierId(tenantId,supplierId, groupId);
		if (storageGroup == null) {
			logger.warn("未找到对应的标准品信息,租户ID:" + tenantId + ",库存组标识:" + groupId);
			throw new BusinessException("", "未找到对应的标准品信息,租户ID:" + tenantId + ",库存组标识:" + groupId);
		}
		//查看标准品
		/*StandedProduct standedProduct = standedProductAtomSV.selectById(tenantId, storageGroup.getStandedProdId());
		if (standedProduct == null) {
			logger.warn("未找到对应的标准品信息,租户ID:" + tenantId + ",标准品标识:" + storageGroup.getStandedProdId());
			throw new BusinessException("",
					"未找到对应的标准品信息,租户ID:" + tenantId + ",标准品标识:" + storageGroup.getStandedProdId());
		}*/
		return genStorageGroupInfo(storageGroup);
	}

	/**
	 * 根据库存组信息产生接口返回库存组信息,包括库存组下的库存信息
	 * 
	 * @param group
	 * @return
	 */
//	private StorageGroupRes genStorageGroupInfo(StorageGroup group) {
	@Override
	public StorageGroupRes genStorageGroupInfo(StorageGroup group) {
		StorageGroupRes groupInfo = new StorageGroupRes();
		BeanUtils.copyProperties(groupInfo, group);
		// 填充库存组信息
		// 查询对应商品信息
		//Product product = productAtomSV.selectByGroupId(group.getTenantId(), group.getStorageGroupId());
		//查询es
    	List<SearchCriteria> searchCriteria = new ArrayList<SearchCriteria>();
    	searchCriteria.add(new SearchCriteria("storagegroupid",
    			group.getStorageGroupId(),
    			new SearchOption(SearchOption.SearchLogic.must,SearchOption.SearchType.querystring)));
    	
    	int startSize = 1;
		int maxSize = 1;
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
		Result<SKUInfo> infoResult = search.searchByCriteria(searchCriteria,startSize,maxSize, null);
    	if (CollectionUtil.isEmpty(infoResult.getContents())) {
    		logger.error("查询商品失败");
    		throw new BusinessException("查询es中的商品信息失败");
		}
    	SKUInfo product = infoResult.getContents().get(0);
		
		
		if (product != null){
			groupInfo.setProdId(product.getProductid());
		}
		// 库存总量
		Map<Short, List<StorageRes>> storageMap = new HashMap<>();
		// ====填充库存集合信息
		// 当前启用的优先级
		Short activePriority = null;
		// 库存组库存总量
		long storageTotal = 0;
		// 查询库存组的库存集合
		List<Storage> storageList = storageAtomSV.queryOfGroup(group.getTenantId(), group.getStorageGroupId());
		groupInfo.setStorageNum(storageList.size());
		for (Storage storage : storageList) {
			List<StorageRes> stoStorageList = storageMap.get(storage.getPriorityNumber());
			if (stoStorageList == null) {
				stoStorageList = new ArrayList<>();
				storageMap.put(storage.getPriorityNumber(), stoStorageList);
			}
			StorageRes stoStorage = new StorageRes();
			BeanUtils.copyProperties(stoStorage, storage);
			stoStorageList.add(stoStorage);
			// 如果库存为启用状态
			if (StorageConstants.StorageGroup.State.ACTIVE.equals(storage.getState())
					|| StorageConstants.StorageGroup.State.AUTO_ACTIVE.equals(storage.getState()))
			{
				// 若为设置启用优先级,则设置第一个启用库存的优先级为启用优先级
				if (activePriority == null){
					activePriority = storage.getPriorityNumber();
				}
				// 若库存优先级与启用优先级不一致,则直接跳过
				if (activePriority != storage.getPriorityNumber()){
					continue;
				}
				// 添加库存总量
//				storageTotal += storage.getTotalNum();
				storageTotal += storage.getUsableNum();
			}
			//若没有销售属性,则填充销售价
			//if(StorageConstants.StorageGroup.isSaleAttr.NO_SALE_ATTR.equals(group.getIsSaleAttr())){
				//查询库存对应SKU库存的信息.
				List<SkuStorage> skuStoList = skuStorageAtomSV.queryByStorageId(storage.getStorageId(),true);
				if (!CollectionUtil.isEmpty(skuStoList)){
					stoStorage.setSalePrice(skuStoList.get(0).getSalePrice());
				}
			//}
		}
		groupInfo.setStorageTotal(storageTotal);
		groupInfo.setStorageList(storageMap);
		return groupInfo;
	}

	@Override
	public int updateStorageGroupPrice(StorageGroupSalePrice salePrice) {
		if (salePrice == null){
			return 0;
		}
		// 判断库存是否废弃
		StorageGroup group = storageGroupAtomSV.queryByGroupIdAndSupplierId(
				salePrice.getTenantId(),salePrice.getSupplierId(),salePrice.getStorageGroupId());
		if (group == null || storageGroupAtomSV.isDiscard(group)){
			throw new BusinessException("", "库存组不存在或已废弃");
		}
		// 填充价格等基本信息
		StorageGroup storageGroup = new StorageGroup();
		BeanUtils.copyProperties(storageGroup, salePrice);
		int updateNum = storageGroupAtomSV.updateStorGroupPrice(storageGroup);
		// 写入日志信息
		if (updateNum > 0) {
			ProdPriceLog prodPriceLog = new ProdPriceLog();
			BeanUtils.copyProperties(prodPriceLog, storageGroup);
			prodPriceLog.setObjId(storageGroup.getStorageGroupId());
			// 设置类型为库存组
			prodPriceLog.setObjType(ProdPriceLogConstants.ProdPriceLog.ObjType.STORAGE_GROUP);
			prodPriceLog.setUpdatePrice(storageGroup.getLowSalePrice());
			if (storageGroup.getHighSalePrice() != null){
				prodPriceLog.setUpdatePeice2(storageGroup.getHighSalePrice());
			}
			prodPriceLogAtomSV.insert(prodPriceLog);
		}
		return updateNum;
	}

	/**
	 * 变更库存组状态
	 *
	 * @param tenantId
	 *            租户ID
	 * @param groupId
	 *            库存组ID
	 * @param state
	 *            要变更状态
	 * @param operId
	 *            操作者ID
	 */
	@Override
	public void updateGroupState(String tenantId,String supplierId, String groupId, String state, Long operId) {
		// 查询库存组是否存在
		StorageGroup storageGroup = storageGroupAtomSV.queryByGroupIdAndSupplierId(tenantId,supplierId, groupId);
		if (storageGroup == null) {
			logger.warn("要查询库存组不存在,租户ID:" + tenantId + ",库存组标识:" + groupId);
			throw new BusinessException("", "指定库存组不存在");
		}
		String oldState = storageGroup.getState();
		if (oldState.equals(state)) {
			throw new BusinessException("", "状态已经变更,不需要重复变更");
		}
		// 查看是否为废弃状态
		if (StorageConstants.StorageGroup.State.DISCARD.equals(oldState)
				|| StorageConstants.StorageGroup.State.AUTO_DISCARD.equals(oldState)){
			throw new BusinessException("", groupId + "库存组已废弃,不允许变更状态");
		}
		switch (state){
			//启用
			case StorageConstants.StorageGroup.State.ACTIVE:
				startGroup(storageGroup, operId);
				break;
			//停用
			case StorageConstants.StorageGroup.State.STOP:
				stopGroup(storageGroup, operId);
				break;
			//废弃
			case StorageConstants.StorageGroup.State.DISCARD:
				discardGroup(storageGroup, operId,false);
				break;
			case StorageConstants.StorageGroup.State.AUTO_DISCARD:
				discardGroup(storageGroup, operId,true);
				break;
			//不识别
			default:
				logger.warn("无法识别库存组状态:{}",state);
				throw new BusinessException("", "无法识别的状态:" + state);
		}
	}

	/**
	 * 库存组启用
	 * 
	 * @param storageGroup
	 */
	private void startGroup(StorageGroup storageGroup, Long operId) {
		// 检查是否已配置路由组
		if (StringUtils.isBlank(storageGroup.getRouteGroupId())) {
			throw new BusinessException("", "库存组没有配置路由信息,不能启用");
		}
		// 判断标准品的状态是否可用
		String staProdState = standedProductAtomSV.selectById(storageGroup.getTenantId(), storageGroup.getStandedProdId()).getState();
		if(!StandedProductConstants.STATUS_ACTIVE.equals(staProdState)){
			throw new BusinessException("", "该库存组对应的标准品的状态不可用,不能启用");
		}
		// 判断库存组下SKU库存是否已经设置价格
		List<Storage> storageList = storageAtomSV.queryActive(
				storageGroup.getTenantId(), storageGroup.getStorageGroupId(),false);
		// 如果该库存组下有库存则判断库存下的SKU库存是否有销售价
		if(!CollectionUtil.isEmpty(storageList)){
			List<String> storageIdList = new ArrayList<>();
			for(Storage storage : storageList){
				storageIdList.add(storage.getStorageId());
			}
			if (skuStorageAtomSV.queryNoPriceOfStorageByIdList(storageIdList)>0){
				throw new BusinessException("","该库存组下存在未指定销售价的单品,库存组无法启用");
			}
		}
		// 检查路由组是否已为启用状态
		IRouteGroupManageSV iRouteQuerySV = DubboConsumerFactory.getService(IRouteGroupManageSV.class);
		RouteGroupStateRequest stateRequest = new RouteGroupStateRequest();
		stateRequest.setTenantId(storageGroup.getTenantId());
		stateRequest.setRouteGroupId(storageGroup.getRouteGroupId());
		RouteGroupStateResponse queryResult = iRouteQuerySV.findRouteGroupState(stateRequest);
		if (queryResult==null || !RouteConstants.RouteGroup.State.RIGHT.equals(queryResult.getState())){
			throw new BusinessException("","对应路由组状态不正常,无法启用库存组");
		}
		// 库存组设置为启用状态
		storageGroup.setState(StorageConstants.StorageGroup.State.ACTIVE);
		storageGroup.setOperId(operId);
		// 添加日志信息
		if (storageGroupAtomSV.updateById(storageGroup) > 0) {
			StorageGroupLog groupLog = new StorageGroupLog();
			BeanUtils.copyProperties(groupLog, storageGroup);
			storageGroupLogAtomSV.install(groupLog);
		}
		//刷新缓存
		flushStorageCache(storageGroup);
		// 若对应商品为"62停用下架",则进行自动上架.
		Product product = productAtomSV.selectByGroupId(storageGroup.getTenantId(), storageGroup.getStorageGroupId());
		if (product != null && ProductConstants.Product.State.STOP.equals(product.getState())) {
			productBusiSV.changeToSaleForStop(product.getTenantId(), product.getProdId(), operId);
		}
	}

	/**
	 * 库存组停用
	 * 
	 * @param storageGroup
	 * @param operId
	 */
	private void stopGroup(StorageGroup storageGroup, Long operId) {
		// 库存组变更为停用
		storageGroup.setState(StorageConstants.StorageGroup.State.STOP);
		storageGroup.setOperId(operId);
		// 商品进行停用下架
		Product product = productAtomSV.selectByGroupId(storageGroup.getTenantId(), storageGroup.getStorageGroupId());
		if (product != null && ProductConstants.Product.State.IN_SALE.equals(product.getState())) {
			productBusiSV.offSale(storageGroup,product, operId);
		}
		// 添加日志信息
		if (storageGroupAtomSV.updateById(storageGroup) > 0) {
			StorageGroupLog groupLog = new StorageGroupLog();
			BeanUtils.copyProperties(groupLog, storageGroup);
			storageGroupLogAtomSV.install(groupLog);
		}
		//将缓存中库存组状态改为停用
		ICacheClient cacheClient = IPaasStorageUtils.getClient();
		//获取库存组的cacheKey
		String groupKey = IPaasStorageUtils.genMcsStorageGroupKey(
				storageGroup.getTenantId(),storageGroup.getStorageGroupId());
		//设置库存组状态
		cacheClient.hset(groupKey,StorageConstants.IPass.McsParams.GROUP_STATE_HTAGE,storageGroup.getState());
	}

	/**
	 * 库存组废弃
	 * 
	 * @param storageGroup
	 * @param operId
	 */
	private void discardGroup(StorageGroup storageGroup, Long operId,boolean isAuto) {
		// 商品废弃
		Product product = productAtomSV.selectByGroupId(storageGroup.getTenantId(), storageGroup.getStorageGroupId());
		if (product != null) {
			productBusiSV.discardProduct(product.getTenantId(), product.getProdId(), operId);
		}
		// 废弃库存组下所有库存和SKU库存
		List<Storage> storageList = storageAtomSV.queryOfGroup(
				storageGroup.getTenantId(),storageGroup.getStorageGroupId(),false);
		for (Storage storage : storageList) {
			storageBusiSV.discardStorage(storageGroup.getTenantId(),storage, operId,true);
		}
		// 库存组废弃
		if (isAuto){
			storageGroup.setState(StorageConstants.StorageGroup.State.AUTO_DISCARD);
		}
		else{
			storageGroup.setState(StorageConstants.StorageGroup.State.DISCARD);
		}
		storageGroup.setOperId(operId);
		// 添加日志
		if (storageGroupAtomSV.updateById(storageGroup) > 0) {
			StorageGroupLog groupLog = new StorageGroupLog();
			BeanUtils.copyProperties(groupLog, storageGroup);
			storageGroupLogAtomSV.install(groupLog);
		}
		cleanGroupCache(storageGroup.getTenantId(),storageGroup.getStorageGroupId());
	}

	@Override
	public PageInfoResponse<StorageGroupRes> queryGroupByProdIdForSalePrice(StorageGroupOfNormProdPage infoQuery) {
		// 分页查询库存组信息
		PageInfoResponse<StorageGroup> StorageGroupPage = storageGroupAtomSV.queryPageOfStandedProd(
				infoQuery.getTenantId(), infoQuery.getSupplierId(),infoQuery.getStandedProdId(),
				infoQuery.getPageNo(), infoQuery.getPageSize());
		// 统计条件结果数量
		int count = storageGroupAtomSV.countNoDiscard(infoQuery.getTenantId(), infoQuery.getStandedProdId());
		if (StorageGroupPage == null){
			throw new BusinessException("",
					"未找到对应的库存组信息,租户ID:" + infoQuery.getTenantId() + ",标准品标识:" + infoQuery.getStandedProdId());
		}
		// 获取分页查询到的库存组集合-用于查询对应的库存与库存组信息对象
		List<StorageGroup> storageGroupList = StorageGroupPage.getResult();
		// 新建返回分页结果对象的结果集
		List<StorageGroupRes> storageGroupResList = new ArrayList<>();
		for (StorageGroup storageGroup : storageGroupList) {
			storageGroupResList.add(genStorageGroupInfo(storageGroup));
		}
		// 新建返回对象
		PageInfoResponse<StorageGroupRes> storageGroupResPage = new PageInfoResponse<>();
		storageGroupResPage.setResult(storageGroupResList);
		storageGroupResPage.setPageNo(infoQuery.getPageNo());
		storageGroupResPage.setPageSize(infoQuery.getPageSize());
		storageGroupResPage.setCount(count);
		return storageGroupResPage;
	}

	/**
	 * 切换库存组优先级
	 *
	 * @param tenantId
	 * @param groupId
	 * @param nowPriority
	 */
	@Override
	public void changeUsePriority(String tenantId, String groupId, int nowPriority) {
		//查看当前优先级下是否有可用的库存量

		//
	}

	@Override
	public void flushStorageCache(String tenantId,String groupId){
		StorageGroup storageGroup = storageGroupAtomSV.queryByGroupId(tenantId, groupId);
		if (storageGroup == null) {
			logger.warn("要查询库存组不存在,租户ID:" + tenantId + ",库存组标识:" + groupId);
			throw new BusinessException("", "库存组不存在,租户ID:" + tenantId + ",库存组标识:" + groupId);
		}
		flushStorageCache(storageGroup);
	}

	/**
	 * 刷新库存组缓存
	 * @param storageGroup
     */
	public void flushStorageCache(StorageGroup storageGroup){
		if (storageGroup==null){
			return;
		}
		String tenantId = storageGroup.getTenantId(),groupId = storageGroup.getStorageGroupId();
		ICacheClient cacheClient = IPaasStorageUtils.getClient();
		//获取库存组的cacheKey
		String groupKey = IPaasStorageUtils.genMcsStorageGroupKey(tenantId,groupId);
		//查询所有截止时间在当前时间之后的促销的库存,不包括废弃库存
		List<Storage> storageList = storageAtomSV.queryTimeStorageOfGroup(storageGroup.getStorageGroupId(),false);
		List<Short> priorityNumList = new ArrayList<>();
		logger.info("====刷新促销优先级缓存(开始)====");
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
		logger.info("====刷新促销优先级缓存(结束)====");
		//查询当前启用库存
		List<Storage> activeList = storageAtomSV.queryActive(tenantId,groupId,false);
		logger.info("====刷新[非]促销优先级缓存(开始)====");
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
		logger.info("====刷新[非]促销优先级缓存(结束)====");
		//设置库存组状态
		cacheClient.hset(groupKey,StorageConstants.IPass.McsParams.GROUP_STATE_HTAGE,storageGroup.getState());
		logger.info("===库存组当前状态:{}",storageGroup.getState());
	}

	/**
	 * 分页查询指定条件的库存组
	 *
	 * @param groupQuery
	 * @return
	 */
	@Override
	public PageInfoResponse<StorageGroup4List> queryPageForGroupList(StorageGroupQueryPage groupQuery) {
		StoGroupPageQueryVo queryVo = new StoGroupPageQueryVo();
		BeanUtils.copyProperties(queryVo,groupQuery);
		PageInfo<StorageGroupAttach4List> groupPage =  storageGroupAtomSV.queryForGroupList(queryVo);
		PageInfoResponse<StorageGroup4List> groupPageRes = new PageInfoResponse<StorageGroup4List>();
		groupPageRes.setCount(groupPage.getCount());
		groupPageRes.setPageSize(groupPage.getPageSize());
		groupPageRes.setPageNo(groupPage.getPageNo());
		groupPageRes.setPageCount(groupPage.getPageCount());
		List<StorageGroup4List> group4Lists = new ArrayList<>();
		for (StorageGroupAttach4List groupAttach:groupPage.getResult()){
			StorageGroup4List group4List = new StorageGroup4List();
			BeanUtils.copyProperties(group4List,groupAttach);
			//库存组中库存数量
			group4List.setStorageTotal(storageAtomSV.sumTotalOfGroup(groupAttach.getStorageGroupId(),false));
			//库存中库存的个数
			group4List.setStorageNum(storageAtomSV.countOfGroup(groupAttach.getStorageGroupId(),false));
			//操作者名称 TODO...
			group4Lists.add(group4List);
		}
		groupPageRes.setResult(group4Lists);
		return groupPageRes;
	}

	/**
	 * 情况库存组的优先级
	 *
	 * @param tenantId
	 * @param groupId
     */
	public void cleanGroupCache(String tenantId,String groupId){
		//查询所有截止时间在当前时间之后的促销的库存,不包括废弃库存
		List<Storage> storageList = storageAtomSV.queryTimeStorageOfGroup(groupId,false);
		logger.info("====清空促销优先级缓存(开始)====");
		if (!CollectionUtil.isEmpty(storageList)){
			storageList = storageAtomSV.queryTimeStorageOfGroup(groupId,true);
			cleanTimeStorageCache(tenantId,groupId,storageList);
		}
		ICacheClient cacheClient = IPaasStorageUtils.getClient();
		//获取库存组的cacheKey
		String groupKey = IPaasStorageUtils.genMcsStorageGroupKey(tenantId,groupId);
		//获取当前优先级,
		String priority = cacheClient.hget(groupKey,StorageConstants.IPass.McsParams.GROUP_SERIAL_HTAGE);
		//清除A
		cacheClient.expire(groupKey,0);
		//清除B
		String cachekey = IPaasStorageUtils.genMcsGroupSerialPriceKey(tenantId,groupId,priority);
		cacheClient.expire(cachekey,0);
		//清除C
		cachekey = IPaasStorageUtils.genMcsSerialSkuUsableKey(tenantId,groupId,priority);
		cacheClient.expire(cachekey,0);
		//清除D
		cachekey = IPaasStorageUtils.genMcsGroupSerialStartTimeKey(tenantId,groupId);
		cacheClient.expire(cachekey,0);
		//清除F
		cachekey = IPaasStorageUtils.genMcsPriorityUsableKey(tenantId,groupId,priority);
		cacheClient.expire(cachekey,0);
		//查询库存组对应商品下的SKU
		Product product = productAtomSV.selectByGroupId(tenantId,groupId);
		if (product==null){
			return;
		}
		List<ProdSku> skuList = prodSkuAtomSV.querySkuOfProd(tenantId,product.getProdId());
		for (ProdSku prodSku:skuList){
			cachekey = IPaasStorageUtils.genMcsSkuStorageUsableKey(tenantId,groupId,priority,prodSku.getSkuId());
			cacheClient.expire(cachekey,0);
		}
	}

	/**
	 * 库存组自动启用
	 *
	 * @param tenantId
	 * @param groupId
	 */
	@Override
	public boolean changeGroupAutoStart(String tenantId, String groupId) {
		//查询库存组是否存在
		StorageGroup group = storageGroupAtomSV.queryByGroupId(tenantId,groupId);
		//查询库存组是否废弃
		if (group == null ){
			logger.warn("tenantId:{},groupId:{}",tenantId,groupId);
			throw new BusinessException("","库存组不存在");
		}
		//库存组状态若不是自动停用,则不进行处理
		if (!StorageConstants.StorageGroup.State.AUTO_STOP.equals(group.getState())){
			return false;
		}
		//自启用状态
		group.setState(StorageConstants.StorageGroup.State.AUTO_ACTIVE);
		// 添加日志
		if (storageGroupAtomSV.updateById(group) > 0) {
			StorageGroupLog groupLog = new StorageGroupLog();
			BeanUtils.copyProperties(groupLog, group);
			storageGroupLogAtomSV.install(groupLog);
		}
		return true;
	}

	/**
	 * 库存组自动停用
	 *
	 * @param tenantId
	 * @param groupId
	 */
	@Override
	public boolean changeGroupAutoStop(String tenantId, String groupId) {
		//查询库存组是否存在
		StorageGroup group = storageGroupAtomSV.queryByGroupId(tenantId,groupId);
		//查询库存组是否废弃
		if (group == null ){
			logger.warn("tenantId:{},groupId:{}",tenantId,groupId);
			throw new BusinessException("","库存组不存在");
		}
		//库存组状态若不是启动状态,则不进行处理
		if (!StorageConstants.StorageGroup.State.ACTIVE.equals(group.getState())
				&& StorageConstants.StorageGroup.State.AUTO_ACTIVE.equals(group.getState())){
			return false;
		}
		Product product = productAtomSV.queryProductByGroupId(tenantId,groupId);
		//若对应商品为在售,则进行下架处理
		if (product!=null && ProductConstants.Product.State.IN_SALE.equals(product.getState())){
			productBusiSV.offSale(tenantId,product.getSupplierId(),product.getProdId(),null);
		}
		//自启用状态
		group.setState(StorageConstants.StorageGroup.State.AUTO_STOP);
		// 添加日志
		if (storageGroupAtomSV.updateById(group) > 0) {
			StorageGroupLog groupLog = new StorageGroupLog();
			BeanUtils.copyProperties(groupLog, group);
			storageGroupLogAtomSV.install(groupLog);
		}
		//将缓存中库存组状态改为停用
		ICacheClient cacheClient = IPaasStorageUtils.getClient();
		//获取库存组的cacheKey
		String groupKey = IPaasStorageUtils.genMcsStorageGroupKey(tenantId,groupId);
		//设置库存组状态
		cacheClient.hset(groupKey,StorageConstants.IPass.McsParams.GROUP_STATE_HTAGE,group.getState());
		return true;
	}

	/**
	 * 查询库存组下某个优先级中sku的价格
	 *
	 * @param tenantId
	 * @param supplierId
	 * @param pn
	 * @return
	 */
	@Override
	public Map<String, Long> querySkuPriceOfGroupPn(String tenantId, String supplierId,String groupId, Short pn) {
		//确认库存组是否存在
		StorageGroup group = storageGroupAtomSV.queryByGroupIdAndSupplierId(tenantId,supplierId,groupId);
		//查询库存组是否废弃
		if (group == null ){
			logger.warn("tenantId:{},groupId:{}",tenantId,groupId);
			throw new BusinessException("","库存组不存在");
		}
		//查询库存组下改优先级中非废弃的库存信息
		List<Storage> storageList = storageAtomSV.queryStorageByGroupIdAndPriority(groupId,pn,false);
		if (CollectionUtil.isEmpty(storageList)){
			logger.warn("tenantId:{},groupId:{},statu:{}",tenantId,groupId,group.getState());
			throw new BusinessException("","库存组的优先级中没有有效库存");
		}
		//查询库存对应的SKU库存信息
		Storage storage = storageList.get(0);
		List<SkuStorage> skuStorageList = skuStorageAtomSV.queryByStorageId(storage.getStorageId(),false);
		Map<String,Long> skuPriceMap = new HashMap<>();
		for (SkuStorage skuStorage:skuStorageList){
			skuPriceMap.put(skuStorage.getSkuId(),skuStorage.getSalePrice());
		}
		return skuPriceMap;
	}

	/**
	 * 设置库存组的关联路由组的标识
	 *
	 * @param tenantId
	 * @param groupId
	 * @param routeGroupId
	 * @param operId
	 */
	@Override
	public void changeRouteGroupId(String tenantId, String groupId, String routeGroupId, Long operId) {
		//确认库存组是否存在
		StorageGroup group = storageGroupAtomSV.queryByGroupId(tenantId,groupId);
		//查询库存组是否废弃
		if (group == null ){
			logger.warn("tenantId:{},groupId:{}",tenantId,groupId);
			throw new BusinessException("","库存组不存在");
		}
		//设置路由组/配货组标识
		group.setRouteGroupId(routeGroupId);
		if (operId!=null){
			group.setOperId(operId);
		}
		// 添加日志
		if (storageGroupAtomSV.updateById(group) > 0) {
			StorageGroupLog groupLog = new StorageGroupLog();
			BeanUtils.copyProperties(groupLog, group);
			storageGroupLogAtomSV.install(groupLog);
		}
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

	@Override
	public StorageGroupRestwo queryGroupInfoAllByGroupId(String tenantId, String supplierId, String groupId) {
		StorageGroup storageGroup = storageGroupAtomSV.queryByGroupIdAndSupplierId(tenantId,supplierId, groupId);
		if (storageGroup == null) {
			logger.warn("未找到对应的标准品信息,租户ID:" + tenantId + ",库存组标识:" + groupId);
			throw new BusinessException("", "未找到对应的标准品信息,租户ID:" + tenantId + ",库存组标识:" + groupId);
		}
		//查看标准品
		StandedProduct standedProduct = standedProductAtomSV.selectById(tenantId, storageGroup.getStandedProdId());
		if (standedProduct == null) {
			logger.warn("未找到对应的标准品信息,租户ID:" + tenantId + ",标准品标识:" + storageGroup.getStandedProdId());
			throw new BusinessException("",
					"未找到对应的标准品信息,租户ID:" + tenantId + ",标准品标识:" + storageGroup.getStandedProdId());
		}
		return genStorageGroupInfoAll(storageGroup);
	}

	private StorageGroupRestwo genStorageGroupInfoAll(StorageGroup group) {
		StorageGroupRestwo groupInfo = new StorageGroupRestwo();
		BeanUtils.copyProperties(groupInfo, group);
		// 填充库存组信息
		// 查询对应商品信息
		Product product = productAtomSV.selectByGroupId(group.getTenantId(), group.getStorageGroupId());
		if (product != null){
			groupInfo.setProdId(product.getProdId());
		}
		// 库存总量
		Map<Short, List<StorageRes>> storageMap = new HashMap<>();
		// ====填充库存集合信息
		// 当前启用的优先级
		Short activePriority = null;
		// 库存组库存总量
		long storageTotal = 0;
		// 查询库存组的库存集合
		List<Storage> storageList = storageAtomSV.queryOfGroup(group.getTenantId(), group.getStorageGroupId());
		groupInfo.setStorageNum(storageList.size());
		for (Storage storage : storageList) {
			List<StorageRes> stoStorageList = storageMap.get(storage.getPriorityNumber());
			if (stoStorageList == null) {
				stoStorageList = new ArrayList<>();
				storageMap.put(storage.getPriorityNumber(), stoStorageList);
			}
			StorageRes stoStorage = new StorageRes();
			BeanUtils.copyProperties(stoStorage, storage);
			stoStorageList.add(stoStorage);
			// 如果库存为启用状态
			if (StorageConstants.StorageGroup.State.ACTIVE.equals(storage.getState())
					|| StorageConstants.StorageGroup.State.AUTO_ACTIVE.equals(storage.getState()))
			{
				// 若为设置启用优先级,则设置第一个启用库存的优先级为启用优先级
				if (activePriority == null){
					activePriority = storage.getPriorityNumber();
				}
				// 若库存优先级与启用优先级不一致,则直接跳过
				if (activePriority != storage.getPriorityNumber()){
					continue;
				}
				// 添加库存总量
				storageTotal += storage.getTotalNum();
			}
			//若没有销售属性,则填充销售价
			if(StorageConstants.StorageGroup.isSaleAttr.NO_SALE_ATTR.equals(group.getIsSaleAttr())){
				//查询库存对应SKU库存的信息.
				List<SkuStorage> skuStoList = skuStorageAtomSV.queryByStorageId(storage.getStorageId(),true);
				if (!CollectionUtil.isEmpty(skuStoList)){
					stoStorage.setSalePrice(skuStoList.get(0).getSalePrice());
				}
			}
		}
		groupInfo.setStorageTotal(storageTotal);
		groupInfo.setStorageList(storageMap);
		return groupInfo;
	}

}
