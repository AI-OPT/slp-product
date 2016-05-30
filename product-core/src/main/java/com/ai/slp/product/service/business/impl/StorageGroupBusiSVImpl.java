package com.ai.slp.product.service.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.api.storage.param.*;
import com.ai.slp.product.constants.*;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.*;
import com.ai.slp.product.service.business.interfaces.IStorageBusiSV;
import com.ai.slp.route.api.routequery.interfaces.IRouteQuerySV;
import com.ai.slp.route.api.routequery.param.RouteGroupQueryResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.dao.mapper.bo.ProdPriceLog;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroupLog;
import com.ai.slp.product.service.atom.interfaces.IProdPriceLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageGroupBusiSV;

/**
 * 库存组操作 Created by jackieliu on 16/5/5.
 */
@Service
@Transactional
public class StorageGroupBusiSVImpl implements IStorageGroupBusiSV {
	private static Logger logger = LoggerFactory.getLogger(StorageGroupBusiSVImpl.class);
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

	/**
	 * 添加库存组
	 *
	 * @param storageGroup
	 * @return
	 */
	@Override
	public int addGroup(STOStorageGroup storageGroup) {
		// 查询标准品是否有销售属性
		String tenantId = storageGroup.getTenantId();
		String standedProdId = storageGroup.getProdId();
		StandedProduct standedProduct = standedProductAtomSV.selectById(tenantId, standedProdId);
		if (standedProduct == null) {
			logger.warn("未找到对应标准品,租户ID:{},标准品标识:{}", tenantId, standedProdId);
			throw new BusinessException("", "未找到对应标准品信息,租户id:" + tenantId + ",标准品标识:" + standedProdId);
		}
		// 添加库存组信息,状态默认为停用
		StorageGroup group = new StorageGroup();
		BeanUtils.copyProperties(group, storageGroup);
		// 默认为停用状态
		group.setState(StorageConstants.StorageGroup.State.STOP);
		// 添加库存组日志
		int installNum = storageGroupAtomSV.installGroup(group);
		if (installNum > 0) {
			StorageGroupLog groupLog = new StorageGroupLog();
			BeanUtils.copyProperties(groupLog, group);
			storageGroupLogAtomSV.install(groupLog);
		}
		// 添加商品
		productBusiSV.addProductWithStorageGroup(group, storageGroup.getCreateId());
		return installNum;
	}

	/**
	 * 更新库存组
	 *
	 * @param storageGroup
	 * @return
	 */
	@Override
	public int updateGroupName(StorageGroupUpName storageGroup) {
		// 查询库存组是否存在
		StorageGroup group = storageGroupAtomSV.queryByGroupId(storageGroup.getTenantId(),
				storageGroup.getStorageGroupId());
		if (group == null)
			throw new BusinessException("",
					"要更新库存组信息不存在,租户ID:" + storageGroup.getTenantId() + ",库存组标识:" + storageGroup.getStorageGroupId());
		// 已废弃,不允许变更
		if (StorageConstants.StorageGroup.State.DISCARD.equals(group.getState())
				|| StorageConstants.StorageGroup.State.AUTO_DISCARD.equals(group.getState()))
		{
			throw new BusinessException("", "库存组已经废弃,不允许更新信息");
		}
		// 设置可更新信息
		group.setStorageGroupName(storageGroup.getStorageGroupName());
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
	public List<StorageGroupRes> queryGroupInfoByNormProId(String tenantId, String productId) {
		StandedProduct standedProduct = standedProductAtomSV.selectById(tenantId, productId);
		if (standedProduct == null)
			throw new BusinessException("", "未找到对应的标准品信息,租户ID:" + tenantId + ",标准品标识:" + productId);
		// 查询出标准品下的所有库存组,创建时间倒序
		List<StorageGroupRes> groupInfoList = new ArrayList<>();
		List<StorageGroup> groupList = storageGroupAtomSV.queryOfStandedProd(tenantId, productId);
		for (StorageGroup storageGroup : groupList) {
			groupInfoList.add(genStorageGroupInfo(storageGroup));
		}
		return groupInfoList;
	}

	/**
	 * 查询单个库存组的信息
	 *
	 * @param tenantId
	 * @param groupId
	 * @return
	 */
	@Override
	public StorageGroupRes queryGroupInfoByGroupId(String tenantId, String groupId) {
		StorageGroup storageGroup = storageGroupAtomSV.queryByGroupId(tenantId, groupId);
		if (storageGroup == null) {
			logger.warn("租户ID:" + tenantId + ",库存组标识:" + groupId);
			throw new BusinessException("", "未找到对应的标准品信息,租户ID:" + tenantId + ",库存组标识:" + groupId);
		}
		StandedProduct standedProduct = standedProductAtomSV.selectById(tenantId, storageGroup.getStandedProdId());
		if (standedProduct == null) {
			logger.warn("租户ID:" + tenantId + ",库存组标识:" + groupId);
			throw new BusinessException("",
					"未找到对应的标准品信息,租户ID:" + tenantId + ",标准品标识:" + storageGroup.getStandedProdId());
		}
		return genStorageGroupInfo(storageGroup);
	}

	/**
	 * 根据库存组信息产生接口返回库存组信息,包括库存组下的库存信息
	 * 
	 * @param group
	 * @return
	 */
	private StorageGroupRes genStorageGroupInfo(StorageGroup group) {
		StorageGroupRes groupInfo = new StorageGroupRes();
		BeanUtils.copyProperties(groupInfo, group);
		// 填充库存组信息
		// 查询对应商品信息
		Product product = productAtomSV.selectByGroupId(group.getTenantId(), group.getStorageGroupId());
		if (product != null)
			groupInfo.setProdId(product.getProdId());
		// groupInfo.setCreateName("");//TODO... 添加创建者账户
		// groupInfo.setOperName("");//TODO... 添加操作者账户
		// 库存总量
		Map<Short, List<StorageRes>> storageMap = new HashMap<>();
		// ====填充库存集合信息
		// 当前启用的优先级
		Short activePriority = null;
		// 库存组库存总量
		long storageTotal = 0;
		// 查询库存组的库存集合
		List<Storage> storageList = storageAtomSV.queryOfGroup(group.getTenantId(), group.getStorageGroupId());
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
				if (activePriority == null)
					activePriority = storage.getPriorityNumber();
				// 若库存优先级与启用优先级不一致,则直接跳过
				if (activePriority != storage.getPriorityNumber())
					continue;
				// 添加库存总量
				storageTotal += storage.getTotalNum();
			}
		}
		groupInfo.setStorageTotal(storageTotal);
		groupInfo.setStorageList(storageMap);
		return groupInfo;
	}

	@Override
	public int updateStorageGroupPrice(StorageGroupSalePrice salePrice) {
		if (salePrice == null)
			return 0;
		if (salePrice.getGroupId() == null || salePrice.getOperId() == null)
			throw new BusinessException("", "找不到指定的库存组=" + salePrice.getGroupId() + ",和操作人=" + salePrice.getOperId());
		// 判断库存是否废弃
		if (storageGroupAtomSV.queryByGroupId(salePrice.getTenantId(), salePrice.getGroupId()).getState().equals("3"))
			throw new BusinessException("", "废弃状态的库存组不能更新价格信息");
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
			if (storageGroup.getHighSalePrice() != null)
				prodPriceLog.setUpdatePeice2(storageGroup.getHighSalePrice());
			prodPriceLogAtomSV.insert(prodPriceLog);
		}
		return storageGroupAtomSV.updateStorGroupPrice(storageGroup);
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
	public void updateGroupState(String tenantId, String groupId, String state, Long operId) {
		// 查询库存组是否存在
		StorageGroup storageGroup = storageGroupAtomSV.queryByGroupId(tenantId, groupId);
		if (storageGroup == null) {
			logger.warn("要查询库存组不存在,租户ID:" + tenantId + ",库存组标识:" + groupId);
			throw new BusinessException("", "库存组不存在,租户ID:" + tenantId + ",库存组标识:" + groupId);
		}
		String oldState = storageGroup.getState();
		if (oldState.equals(state)) {
			throw new BusinessException("", "状态已经变更,不需要重复变更");
		}
		// 查看是否为废弃状态
		if (StorageConstants.StorageGroup.State.DISCARD.equals(oldState)
				|| StorageConstants.StorageGroup.State.AUTO_DISCARD.equals(oldState))
		{
			throw new BusinessException("", groupId + "库存组已废弃,不允许变更状态");
		}
		// 启用
		if (StorageConstants.StorageGroup.State.ACTIVE.equals(state)) {
			startGroup(storageGroup, operId);
			// 停用
		} else if (StorageConstants.StorageGroup.State.STOP.equals(state)) {
			stopGroup(storageGroup, operId);
			// 废弃处理
		} else if (StorageConstants.StorageGroup.State.DISCARD.equals(state)) {
			discardGroup(storageGroup, operId);
		} else
			throw new BusinessException("", "无法识别的状态");
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
		// 检查路由组是否已为启用状态
		IRouteQuerySV iRouteQuerySV = DubboConsumerFactory.getService("iRouteQuerySV");
		RouteGroupQueryResult queryResult = iRouteQuerySV.routeGroupDetailQuery(storageGroup.getRouteGroupId());
		if (queryResult==null || !RouteConstants.RouteGroup.State.RIGHT.equals(queryResult.getState())){
			throw new BusinessException("","对应路由组状态不正常,无法启用库存组");
		}
		// 判断标准品的状态是否可用
		String staProdState = standedProductAtomSV.selectById(storageGroup.getTenantId(), storageGroup.getStandedProdId()).getState();
		if(!StandedProductConstants.STATUS_ACTIVE.equals(staProdState)){
			throw new BusinessException("", "该库存组对应的标准品的状态不可用,不能启用");
		}
		// 判断库存组下SKU库存有没有价格1.通过库存组查询所有库存,2.通过库存ID查找所有SKU库存的销售价是否存在
		// 3.若存在则继续下一步,若不存在则提示添加价格后才可以启用.
		List<Storage> storageList = storageAtomSV.queryOfGroup(storageGroup.getTenantId(), storageGroup.getStorageGroupId());
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
		// 库存组设置为启用状态
		storageGroup.setState(StorageConstants.StorageGroup.State.ACTIVE);
		storageGroup.setOperId(operId);
		// 添加日志信息
		if (storageGroupAtomSV.updateById(storageGroup) > 0) {
			StorageGroupLog groupLog = new StorageGroupLog();
			BeanUtils.copyProperties(groupLog, storageGroup);
			storageGroupLogAtomSV.install(groupLog);
		}
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
		// 添加日志信息
		if (storageGroupAtomSV.updateById(storageGroup) > 0) {
			StorageGroupLog groupLog = new StorageGroupLog();
			BeanUtils.copyProperties(groupLog, groupLog);
			storageGroupLogAtomSV.install(groupLog);
		}
		// 商品进行停用下架
		Product product = productAtomSV.selectByGroupId(storageGroup.getTenantId(), storageGroup.getStorageGroupId());
		if (product != null && ProductConstants.Product.State.IN_SALE.equals(product.getState())) {
			productBusiSV.offSale(product.getTenantId(), product.getProdId(), operId);
		}
	}

	/**
	 * 库存组废弃
	 * 
	 * @param storageGroup
	 * @param operId
	 */
	private void discardGroup(StorageGroup storageGroup, Long operId) {
		// 商品废弃
		Product product = productAtomSV.selectByGroupId(storageGroup.getTenantId(), storageGroup.getStorageGroupId());
		if (product != null) {
			productBusiSV.discardProduct(product.getTenantId(), product.getProdId(), operId);
		}
		//TODO... 删除搜索引擎中商品信息
		// 废弃库存组下所有库存和SKU库存
		List<Storage> storageList = storageAtomSV.queryOfGroup(storageGroup.getTenantId(),storageGroup.getStorageGroupId());
		for (Storage storage : storageList) {
			storageBusiSV.discardStorage(storage, operId);
		}
		// 库存组废弃
		storageGroup.setState(StorageConstants.StorageGroup.State.DISCARD);
		storageGroup.setOperId(operId);
		// 添加日志
		if (storageGroupAtomSV.updateById(storageGroup) > 0) {
			StorageGroupLog groupLog = new StorageGroupLog();
			BeanUtils.copyProperties(groupLog, groupLog);
			storageGroupLogAtomSV.install(groupLog);
		}

	}

	@Override
	public PageInfoResponse<StorageGroupRes> queryGroupByProdIdForSalePrice(StorageGroupOfNormProdPage infoQuery) {
		// 分页查询库存组信息
		PageInfoResponse<StorageGroup> StorageGroupPage = storageGroupAtomSV.queryPageOfStandedProd(
				infoQuery.getTenantId(), infoQuery.getStandedProdId(), infoQuery.getPageNo(), infoQuery.getPageSize());
		// 统计条件结果数量
		int count = storageGroupAtomSV.queryCountNoDiscard(infoQuery.getTenantId(), infoQuery.getStandedProdId());
		if (StorageGroupPage == null)
			throw new BusinessException("",
					"未找到对应的库存组信息,租户ID:" + infoQuery.getTenantId() + ",标准品标识:" + infoQuery.getStandedProdId());
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

}
