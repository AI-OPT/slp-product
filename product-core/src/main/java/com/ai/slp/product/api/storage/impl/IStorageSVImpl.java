package com.ai.slp.product.api.storage.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseListResponse;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import com.ai.slp.product.api.storage.param.*;
import com.ai.slp.product.service.business.interfaces.IStorageBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageGroupBusiSV;
import com.ai.slp.product.util.CommonCheckUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jackieliu on 16/5/4.
 */
@Service(validation = "true")
@Component
public class IStorageSVImpl implements IStorageSV {
	@Autowired
	IStorageGroupBusiSV storageGroupBusiSV;
	@Autowired
	IStorageBusiSV storageBusiSV;

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
		CommonCheckUtils.checkTenantId(storageGroup.getTenantId(), "");
		String groupId = storageGroupBusiSV.addGroup(storageGroup);
		return returnIdToMsg(groupId);
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
		CommonCheckUtils.checkTenantId(infoQuery.getTenantId(), "");
		return storageGroupBusiSV.queryGroupInfoByGroupId(infoQuery.getTenantId(), infoQuery.getGroupId());
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
		CommonCheckUtils.checkTenantId(infoQuery.getTenantId(), "");
		List<StorageGroupRes> groupResList = storageGroupBusiSV.queryGroupInfoByNormProId(
				infoQuery.getTenantId(), infoQuery.getProductId());
		BaseListResponse<StorageGroupRes> groupRes = new BaseListResponse<>();
		groupRes.setResult(groupResList);
		groupRes.setResponseHeader(new ResponseHeader(true,ExceptCodeConstants.Special.SUCCESS,""));
		return groupRes;
	}

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
	public BaseResponse chargeStorageGroupStatus(StorageGroupStatus groupStatus)
			throws BusinessException, SystemException {
		CommonCheckUtils.checkTenantId(groupStatus.getTenantId(), "");
		storageGroupBusiSV.updateGroupState(groupStatus.getTenantId(), groupStatus.getGroupId(), groupStatus.getState(),
				groupStatus.getOperId());
		BaseResponse baseResponse = new BaseResponse();
		ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstants.Special.SUCCESS,"");
		baseResponse.setResponseHeader(responseHeader);
		return baseResponse;
	}

	/**
	 * 查询虚拟库存组列表<br>
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
		return null;
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
		CommonCheckUtils.checkTenantId(stoStorage.getTenantId(), "");
		String storageId = storageBusiSV.saveStorage(stoStorage);
		return returnIdToMsg(storageId);
	}

	/**
	 * 新增库存组合库存返回ID
	 * @param storageId
	 * @return
	 */
	private BaseResponse returnIdToMsg(String storageId) {
		BaseResponse baseResponse = new BaseResponse();
		ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstants.Special.SUCCESS,storageId);
		baseResponse.setResponseHeader(responseHeader);
		return baseResponse;
	}

	/**
	 * 查询标准品库存信息<br>
	 *
	 * @param storageId
	 *            库存标识
	 * @return 标准品库存信息
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiDocMethod
	 */
	@Override
	public StorageRes queryStorageById(String storageId) throws BusinessException, SystemException {
		if(StringUtils.isEmpty(storageId)){
			throw new BusinessException("", "库存ID不存在,库存ID"+storageId);
		}
		return storageBusiSV.queryStorageById(storageId);
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
	public BaseResponse chargeStorageStatus(StorageStatus storageStatus) throws BusinessException, SystemException {
		CommonCheckUtils.checkTenantId(storageStatus.getTenantId(), "");
		storageBusiSV.changeStorageStats(storageStatus.getTenantId(), storageStatus.getStorageId(),
				storageStatus.getState(), storageStatus.getOperId());
		BaseResponse baseResponse = new BaseResponse();
		ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstants.Special.SUCCESS,"");
		baseResponse.setResponseHeader(responseHeader);
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
		CommonCheckUtils.checkTenantId(priorityCharge.getTenantId(), "");
		storageBusiSV.updateStoragePriority(priorityCharge);
		BaseResponse baseResponse = new BaseResponse();
		ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstants.Special.SUCCESS,"");
		baseResponse.setResponseHeader(responseHeader);
		return baseResponse;
	}

	/**
	 * 更新库存组信息
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
	public BaseResponse updateStorageGroupName(StorageGroupUpName storageGroup)
			throws BusinessException, SystemException {
		CommonCheckUtils.checkTenantId(storageGroup.getTenantId(), "");
		int updateNum = storageGroupBusiSV.updateGroupName(storageGroup);
		BaseResponse baseResponse = new BaseResponse();
		ResponseHeader responseHeader = new ResponseHeader(updateNum>0 ? true : false,ExceptCodeConstants.Special.SUCCESS,"");
		baseResponse.setResponseHeader(responseHeader);
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
		CommonCheckUtils.checkTenantId(salePrice.getTenantId(), "");
		storageGroupBusiSV.updateStorageGroupPrice(salePrice);
		BaseResponse baseResponse = new BaseResponse();
		ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstants.Special.SUCCESS,"");
		baseResponse.setResponseHeader(responseHeader);
		return baseResponse;
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
		CommonCheckUtils.checkTenantId(groupQuery.getTenantId(), "");
		return storageBusiSV.queryGroupsForSalePrice(groupQuery);
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
		CommonCheckUtils.checkTenantId(infoQuery.getTenantId(), "");
		return storageGroupBusiSV.queryGroupByProdIdForSalePrice(infoQuery);
	}

	/**
	 * 批量更新库存销售价<br>
	 *
	 * @param salePrice
	 *            库存批量销售价信息
	 * @return 操作结果
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiDocMethod
	 */
	@Override
	public BaseResponse updateMultiStorageSalePrice(StorageSalePrice salePrice)
			throws BusinessException, SystemException {
		// 判断集合元素
		CommonCheckUtils.checkTenantId(salePrice.getTenantId(), "");
		storageBusiSV.updateMultiStorageSalePrice(salePrice);
		BaseResponse baseResponse = new BaseResponse();
		ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstants.Special.SUCCESS,"修改库存销售价成功");
		baseResponse.setResponseHeader(responseHeader);
		return baseResponse;
	}

	 
    /**
     * 查看SKU库存信息和SKU单品
     *
     * @return 
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
	@Override
	public List<SkuStorageAndProd> querySkuStorageById(String tenantId,String storageId) throws BusinessException, SystemException {
		CommonCheckUtils.checkTenantId(tenantId,"");
		if(StringUtils.isEmpty(storageId)){
			throw new BusinessException("", "库存标识不能为空");
		}
		return storageBusiSV.querySkuStorageById(tenantId,storageId);
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
	public BaseResponse addSkuStorage(List<SkuStorageAdd> skuStorageAddList) throws BusinessException, SystemException {
		if(CollectionUtil.isEmpty(skuStorageAddList)){
			throw new BusinessException("", "新增信息集合为空");
		}
		int addNum = storageBusiSV.insertSkuStorage(skuStorageAddList);
		BaseResponse baseResponse = new BaseResponse();
		ResponseHeader responseHeader = new ResponseHeader(addNum>0 ? true : false,ExceptCodeConstants.Special.SUCCESS,"新增SKU库存信息成功(有销售属性)");
		baseResponse.setResponseHeader(responseHeader);
		return baseResponse;
	}
}
