package com.ai.slp.product.api.storage.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseListResponse;
import com.ai.opt.base.vo.BaseMapResponse;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import com.ai.slp.product.api.storage.param.NameUpReq;
import com.ai.slp.product.api.storage.param.STOStorage;
import com.ai.slp.product.api.storage.param.STOStorageGroup;
import com.ai.slp.product.api.storage.param.SkuPriceOfGroupPnReq;
import com.ai.slp.product.api.storage.param.SkuStorageAdd;
import com.ai.slp.product.api.storage.param.SkuStorageInfo;
import com.ai.slp.product.api.storage.param.StoGroupAStatus;
import com.ai.slp.product.api.storage.param.StoGroupStatus;
import com.ai.slp.product.api.storage.param.StoNoSkuSalePriceReq;
import com.ai.slp.product.api.storage.param.StoSkuSalePrice;
import com.ai.slp.product.api.storage.param.StorageGroup4List;
import com.ai.slp.product.api.storage.param.StorageGroup4SaleList;
import com.ai.slp.product.api.storage.param.StorageGroupOfNormProdPage;
import com.ai.slp.product.api.storage.param.StorageGroupQuery;
import com.ai.slp.product.api.storage.param.StorageGroupQueryPage;
import com.ai.slp.product.api.storage.param.StorageGroupRes;
import com.ai.slp.product.api.storage.param.StorageGroupRestwo;
import com.ai.slp.product.api.storage.param.StorageGroupSalePrice;
import com.ai.slp.product.api.storage.param.StoragePriorityCharge;
import com.ai.slp.product.api.storage.param.StorageRes;
import com.ai.slp.product.api.storage.param.StorageStatus;
import com.ai.slp.product.api.storage.param.StorageUniQuery;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.business.impl.StorageNumDbBusiSVImpl;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageGroupBusiSV;
import com.ai.slp.product.util.CommonUtils;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * Created by jackieliu on 16/5/4.
 */
@Service(validation = "true")
@Component
public class IStorageSVImpl implements IStorageSV {
	private static final Logger logger = LoggerFactory.getLogger(IStorageSVImpl.class);
	@Autowired
	IStorageGroupBusiSV storageGroupBusiSV;
	@Autowired
	IStorageBusiSV storageBusiSV;
	@Autowired
	IProductAtomSV productAtomSV;
	@Autowired
	IProductBusiSV productBusiSV;
	@Autowired
	StorageNumDbBusiSVImpl storageNumDbBusiSV;
	@Autowired
	IStandedProductAtomSV standedProductAtomSV;
	@Autowired
	IStorageGroupAtomSV storageGroupAtomSV;
	
	/**
	 * 添加标准品库存组<br>
	 *
	 * @param storageGroup
	 *            库存组对象
	 * @return 添加结果
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiDocMethod
	 */
	@Override
	public BaseResponse createStorageGroup(STOStorageGroup storageGroup) throws BusinessException, SystemException {
		CommonUtils.checkTenantId(storageGroup.getTenantId());
		String groupId = storageGroupBusiSV.addGroup(storageGroup);
		return CommonUtils.addSuccessResHeader(new BaseResponse(),groupId);
	}

	/**
	 * 根据库存组标识查询库存组信息<br>
	 *
	 * @param infoQuery
	 *            库存组对象查询条件
	 * @return 查询到的库存组信息
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiDocMethod
	 */
	@Override
	public StorageGroupRes queryGroupInfoByGroupId(StorageGroupQuery infoQuery)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(infoQuery.getTenantId());
		StorageGroupRes groupRes = storageGroupBusiSV.queryGroupInfoByGroupId(
				infoQuery.getTenantId(),infoQuery.getSupplierId(), infoQuery.getGroupId());
		CommonUtils.addSuccessResHeader(groupRes,"");
		return groupRes;
	}

	/**
	 * 根据标准品标识查询库存组信息<br>
	 *
	 * @param infoQuery
	 *            库存组对象查询条件
	 * @return 查询到的库存组信息
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiDocMethod
	 */
	@Override
	public BaseListResponse<StorageGroupRes> queryGroupInfoByNormProdId(StorageGroupQuery infoQuery)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(infoQuery.getTenantId());
		
		/*StandedProduct standedProduct = standedProductAtomSV.selectById(infoQuery.getTenantId(), infoQuery.getProductId());
		if (standedProduct == null) {
			logger.warn("未找到对应的标准品信息,租户ID:{},标准品标识:{}",infoQuery.getTenantId(), infoQuery.getProductId());
			throw new BusinessException("", "未找到对应的标准品信息");
		}*/
		
		
	/*	List<StorageGroup> groupList = storageGroupBusiSV.queryGroupInfoByNormProId(
				infoQuery.getTenantId(),infoQuery.getSupplierId(), infoQuery.getProductId());*/
		
		List<StorageGroupRes> groupInfoList = new ArrayList<>();
		List<StorageGroup> groupLists = storageGroupAtomSV.queryOfStandedProd(infoQuery.getTenantId(),infoQuery.getSupplierId(), infoQuery.getProductId());
		if (CollectionUtil.isEmpty(groupLists)){
			logger.warn("查询库存组列表为空,租户ID:{},销售商ID:{},标准品ID:{}",infoQuery.getTenantId(),infoQuery.getSupplierId(), infoQuery.getProductId());
		}
		for(int i= groupLists.size()-1;i>=0;i--){
			groupInfoList.add(storageGroupBusiSV.genStorageGroupInfo(groupLists.get(i)));
		}
		
		
		BaseListResponse<StorageGroupRes> groupRes = new BaseListResponse<>();
		groupRes.setResult(groupInfoList);
		CommonUtils.addSuccessResHeader(groupRes,"Ok");
		return groupRes;
	}
	
/*	public BaseListResponse<StorageGroupRes> queryGroupInfoByNormProdId(StorageGroupQuery infoQuery)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(infoQuery.getTenantId());
		List<StorageGroupRes> groupResList = storageGroupBusiSV.queryGroupInfoByNormProId(
				infoQuery.getTenantId(),infoQuery.getSupplierId(), infoQuery.getProductId());
		BaseListResponse<StorageGroupRes> groupRes = new BaseListResponse<>();
		groupRes.setResult(groupResList);
		CommonUtils.addSuccessResHeader(groupRes,"Ok");
		return groupRes;
	}*/

	/**
	 * 更改标准品库存组状态<br>
	 * 包括启用,停用,废弃
	 *
	 * @param groupStatus
	 *            要设置的库存组状态对象
	 * @return 添加结果
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiDocMethod
	 */
	@Override
	public BaseResponse chargeStorageGroupStatus(StoGroupStatus groupStatus)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(groupStatus.getTenantId());
		String tenantId = groupStatus.getTenantId(),
				groupId = groupStatus.getGroupId();
		storageGroupBusiSV.updateGroupState(tenantId, groupStatus.getSupplierId(),
				groupId,groupStatus.getState(),groupStatus.getOperId());
		return CommonUtils.addSuccessResHeader(new BaseResponse(),"OK");
	}

	/**
	 * 查询库存组列表<br>
	 *
	 * @param groupQuery
	 *            库存组查询信息对象
	 * @return 库存组列表
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiDocMethod
	 */
	@Override
	public PageInfoResponse<StorageGroup4List> queryGroup(StorageGroupQueryPage groupQuery)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(groupQuery.getTenantId());
		//根据查询条件查询符合条件的库存组
		PageInfoResponse<StorageGroup4List> groupRes = storageGroupBusiSV.queryPageForGroupList(groupQuery);
		CommonUtils.addSuccessResHeader(groupRes,"");
		return groupRes;
	}

	/**
	 * 保存标准品库存信息<br>
	 *
	 * @param stoStorage
	 *            要保存的库存对象
	 * @return 保存结果
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiDocMethod
	 */
	@Override
	public BaseResponse saveStorage(STOStorage stoStorage) throws BusinessException, SystemException {
		
		
		/*String tenantId = stoStorage.getTenantId();
		Long operId = stoStorage.getOperId();
		String groupId = stoStorage.getStorageGroupId();*/
		
//		查询指定库存组下的销售商品
	/*	Product product = productAtomSV.selectByGroupId(tenantId, groupId);
		
		if(product == null){
			logger.warn("未找到对应的商品信息,租户ID:{},销售商ID:{},库存组ID:{},库存ID:{}",
					stoStorage.getTenantId());
			throw new BusinessException("", "找不到指定标示的商品:" + groupId);
		}*/
		
		CommonUtils.checkTenantId(stoStorage.getTenantId());
		String storageId = storageBusiSV.saveStorage(stoStorage);
		return CommonUtils.addSuccessResHeader(new BaseResponse(),storageId);
		
		/*boolean ccsMqFlag=false;
	   	//从配置中心获取mq_enable
	  	ccsMqFlag=MQConfigUtil.getCCSMqFlag();
		if (!ccsMqFlag) {
			CommonUtils.checkTenantId(stoStorage.getTenantId());
			String storageId = storageBusiSV.saveStorage(stoStorage);
			return CommonUtils.addSuccessResHeader(new BaseResponse(),storageId);
		} else {
			BaseResponse response = new BaseResponse();
			//发送消息
			MDSClientFactory.getSenderClient(NormProdConstants.MDSNS.MDS_NS_STORAGE_TOPIC).send(JSON.toJSONString(stoStorage), 0);
			ResponseHeader responseHeader = new ResponseHeader(true,
					ExceptCodeConstants.Special.SUCCESS, "成功");
			response.setResponseHeader(responseHeader);
			return response; 
		}*/
	}

	/**
	 * 查询标准品库存信息<br>
	 *
	 * @param query 库存唯一标识查询条件
	 * @return 标准品库存信息
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiDocMethod
	 */
	@Override
	public StorageRes queryStorageById(StorageUniQuery query)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(query.getTenantId());
		StorageRes res = storageBusiSV.queryStorageById(
				query.getTenantId(),query.getSupplierId(),query.getStorageId());
		CommonUtils.addSuccessResHeader(res,"");
		return res;
	}

	/**
	 * 更改库存状态<br>
	 * 包括启用,停用,废弃
	 *
	 * @param storageStatus
	 *            要设置的库存状态对象
	 * @return 更新结果
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiDocMethod
	 */
	@Override
	public BaseResponse chargeStorageStatus(StorageStatus storageStatus)
			throws BusinessException, SystemException {
		String tenantId = storageStatus.getTenantId();
		Long operId = storageStatus.getOperId();
		CommonUtils.checkTenantId(storageStatus.getTenantId());
		Storage storage = storageBusiSV.changeStorageStats(tenantId, storageStatus.getSupplierId(),
				storageStatus.getStorageId(),storageStatus.getState(), operId);
		//若启用库存,则刷新缓存
		if (StorageConstants.Storage.State.ACTIVE.equals(storageStatus.getState())){
			storageNumDbBusiSV.flushStorageCache(tenantId,storage);
			//若商品为售罄下架,则进行上架处理
			Product product = productAtomSV.selectByGroupId(tenantId,storage.getStorageGroupId());
			if (product!=null && ProductConstants.Product.State.SALE_OUT.equals(product.getState())){
				productBusiSV.changeToInSale(tenantId,storageStatus.getSupplierId(),product.getProdId(),operId);
			}
		}else if(StorageConstants.Storage.State.STOP.equals(storageStatus.getState())){
			storageNumDbBusiSV.subStorageCache(tenantId,storage);
		}
		BaseResponse baseResponse = new BaseResponse();
		CommonUtils.addSuccessResHeader(baseResponse,"");
		return baseResponse;
	}

	/**
	 * 变更库存组中库存优先级
	 *
	 * @param priorityCharge
	 *            优先级变更信息
	 * @return 操作结果
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiDocMethod
	 */
	@Override
	public BaseResponse chargeStoragePriority(StoragePriorityCharge priorityCharge)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(priorityCharge.getTenantId());
		storageBusiSV.updateStoragePriority(priorityCharge);
		BaseResponse baseResponse = new BaseResponse();
		CommonUtils.addSuccessResHeader(baseResponse,"");
		return baseResponse;
	}

	/**
	 * 更新库存组名称
	 *
	 * @param storageGroup
	 *            库存组信息
	 * @return 操作结果
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiDocMethod
	 */
	@Override
	public BaseResponse updateStorageGroupName(NameUpReq storageGroup)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(storageGroup.getTenantId());
		storageGroupBusiSV.updateGroupName(storageGroup);
		BaseResponse baseResponse = new BaseResponse();
		CommonUtils.addSuccessResHeader(baseResponse,"");
		return baseResponse;
	}

	/**
	 * 更新库存组销售价信息
	 *
	 * @param salePrice
	 *            库存组销售价信息
	 * @return 操作结果
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiDocMethod
	 */
	@Override
	public BaseResponse updateStorageGroupSalePrice(StorageGroupSalePrice salePrice)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(salePrice.getTenantId());
		storageGroupBusiSV.updateStorageGroupPrice(salePrice);
		return CommonUtils.genSuccessResponse("");
	}

	/**
	 * 查询标准品列表,包含标准品的库存组,适用于商城商品定销售价初始页面<br>
	 * 库存组不包括废弃状态的
	 *
	 * @param groupQuery
	 *            库存组查询信息对象
	 * @return 库存组列表
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiDocMethod
	 */
	@Override
	public PageInfoResponse<StorageGroup4SaleList> queryGroupsForSalePrice(StorageGroupQueryPage groupQuery)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(groupQuery.getTenantId());
		PageInfoResponse<StorageGroup4SaleList> pageRes = storageBusiSV.queryGroupsForSalePrice(groupQuery);
		CommonUtils.addSuccessResHeader(pageRes,"");
		return pageRes;
	}

	/**
	 * 根据标准品标识查询库存组信息<br>
	 * 库存组不包括废弃状态的
	 *
	 * @param infoQuery
	 *            库存组对象查询条件
	 * @return 查询到的库存组信息
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiDocMethod
	 */
	@Override
	public PageInfoResponse<StorageGroupRes> queryGroupByProdIdForSalePrice(StorageGroupOfNormProdPage infoQuery)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(infoQuery.getTenantId());
		return storageGroupBusiSV.queryGroupByProdIdForSalePrice(infoQuery);
	}

	/**
	 * 批量更新库存销售价<br>
	 * 无销售属性
	 * @param salePrice
	 *            库存批量销售价信息
	 * @return 操作结果
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiDocMethod
	 */
	@Override
	public BaseResponse updateMultiStorageSalePrice(StoNoSkuSalePriceReq salePrice)
			throws BusinessException, SystemException {
		// 判断集合元素 
		CommonUtils.checkTenantId(salePrice.getTenantId());
		storageBusiSV.updateNoSkuStoSalePrice(salePrice);
		BaseResponse baseResponse = new BaseResponse();
		CommonUtils.addSuccessResHeader(baseResponse,"修改库存销售价成功");
		return baseResponse;
	}

	 
    /**
     * 查看SKU库存信息
     *
     * @return 
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
	@Override
	public BaseMapResponse<String, SkuStorageInfo> querySkuStorageById(StorageUniQuery query)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(query.getTenantId());
		Map<String,SkuStorageInfo> prodMap = storageBusiSV.querySkuStorageById(
				query.getTenantId(),query.getSupplierId(),query.getStorageId());
		BaseMapResponse<String,SkuStorageInfo> prodRes = new BaseMapResponse<>();
		prodRes.setResult(prodMap);
		prodRes.setResponseHeader(new ResponseHeader(true,ExceptCodeConstants.Special.SUCCESS,"OK"));
		return prodRes;
	}

	/**
     * 新增SKU库存信息(有销售属性)
     *
     * @param skuStorageAddList
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
	@Override
	public BaseResponse addSkuStorage(List<SkuStorageAdd> skuStorageAddList)
			throws BusinessException, SystemException {
		if(CollectionUtil.isEmpty(skuStorageAddList)){
			throw new BusinessException("", "新增信息集合为空");
		}
		storageBusiSV.insertSkuStorage(skuStorageAddList);
		BaseResponse baseResponse = new BaseResponse();
		CommonUtils.addSuccessResHeader(baseResponse,"增加SKU库存信息成功");
		return baseResponse;
	}

	/**
	 * 更新库存名称信息
	 *
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiCode STORAGE_0116
	 * @RestRelativeURL storage/updateStorageName
	 */
	@Override
	public BaseResponse updateStorageName(NameUpReq req)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(req.getTenantId());
		//更新库存名称
		STOStorage stoStorage = new STOStorage();
		stoStorage.setTenantId(req.getTenantId());
		stoStorage.setSupplierId(req.getSupplierId());
		stoStorage.setStorageId(req.getId());
		stoStorage.setStorageName(req.getName());
		stoStorage.setOperId(req.getOperId());
		storageBusiSV.updateStorageNameWarn(stoStorage);
		return CommonUtils.genSuccessResponse("");
	}

	/**
	 * 批量更新有销售属性的库存销售价<br>
	 *
	 * @param salePrice 库存批量销售价信息
	 * @return 操作结果
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiDocMethod
	 * @ApiCode STORAGE_0117
	 * @RestRelativeURL storage/updateSkuStoSalePrice
	 */
	@Override
	public BaseResponse updateSkuStorageSalePrice(StoSkuSalePrice salePrice)
			throws BusinessException, SystemException {
		// 判断集合元素
		CommonUtils.checkTenantId(salePrice.getTenantId());
		int num = storageBusiSV.updateSkuStoSalePrice(salePrice);
		return CommonUtils.genSuccessResponse(num+"");
	}

	/**
	 * 更改标准品库存组自动状态<br>
	 * 包括自动启动,自动停用
	 *
	 * @param aStatus 要设置的库存组状态对象
	 * @return 添加结果
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiDocMethod
	 * @ApiCode STORAGE_0118
	 * @RestRelativeURL storage/chargeGroupStatus
	 */
	@Override
	public BaseResponse chargeGroupStatusAuto(StoGroupAStatus aStatus) throws BusinessException, SystemException {
		String tenantId = aStatus.getTenantId(),groupId = aStatus.getGroupId();
		CommonUtils.checkTenantId(aStatus.getTenantId());
		switch (aStatus.getState()) {
			//如果为启用,则刷新缓存
			case StorageConstants.StorageGroup.State.AUTO_ACTIVE:
				if(storageGroupBusiSV.changeGroupAutoStart(tenantId, aStatus.getGroupId())){
					storageGroupBusiSV.flushStorageCache(tenantId, groupId);
					// 若对应商品为"62停用下架",则进行自动上架.
					Product product = productAtomSV.selectByGroupId(tenantId, groupId);
					if (product != null && ProductConstants.Product.State.STOP.equals(product.getState())) {
						productBusiSV.changeToSaleForStop(product, null);
					}
				}
				break;
			case StorageConstants.StorageGroup.State.AUTO_STOP:
				storageGroupBusiSV.changeGroupAutoStop(tenantId,aStatus.getGroupId());
				break;
			default:
				throw new BusinessException("","不支持状态["+aStatus.getState()+"]");
		}
		return CommonUtils.genSuccessResponse("");
	}

	/**
	 * 查看库存组下某个级别的SKU价格
	 *
	 * @param groupPnReq
	 * @return map K:SKUID,V:价格(单位:厘)
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiDocMethod
	 * @ApiCode STORAGE_0119
	 * @RestRelativeURL storage/querySkuPriceByGroupPn
	 */
	@Override
	public BaseMapResponse<String, Long> querySkuPriceByGroupPn(SkuPriceOfGroupPnReq groupPnReq)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(groupPnReq.getTenantId());
		Map<String,Long> priceMap = storageGroupBusiSV.querySkuPriceOfGroupPn(groupPnReq.getTenantId(),groupPnReq.getSupplierId(),
				groupPnReq.getGroupId(),groupPnReq.getPriorityNum());
		BaseMapResponse<String, Long> mapResponse = new BaseMapResponse<>();
		mapResponse.setResult(priceMap);
		CommonUtils.addSuccessResHeader(mapResponse,"");
		return mapResponse;
	}

	/**
     * 根据库存组标识查询库存组信息<br>
     *
     * @param infoQuery 库存组对象查询条件
     * @return 查询到的库存组详细
     * @throws BusinessException
     * @throws SystemException
     * @author jiawen
     * @ApiDocMethod
     * @ApiCode STORAGE_0120
     * @RestRelativeURL storage/queryGroupInfoAllByGroupId
     */
	@Override
	public StorageGroupRestwo queryGroupInfoAllByGroupId(StorageGroupQuery infoQuery)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(infoQuery.getTenantId());
		StorageGroupRestwo groupRes = storageGroupBusiSV.queryGroupInfoAllByGroupId(
				infoQuery.getTenantId(),infoQuery.getSupplierId(), infoQuery.getGroupId());
		CommonUtils.addSuccessResHeader(groupRes,"");
		return groupRes;
	}

}
