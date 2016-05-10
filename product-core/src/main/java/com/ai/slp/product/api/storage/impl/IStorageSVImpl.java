package com.ai.slp.product.api.storage.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.slp.product.api.common.param.PageInfoForRes;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import com.ai.slp.product.api.storage.param.*;
import com.ai.slp.product.service.business.interfaces.IStorageGroupBusiSV;
import com.ai.slp.product.util.CommonCheckUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jackieliu on 16/5/4.
 */
@Service
@Component
public class IStorageSVImpl implements IStorageSV {
    @Autowired
    IStorageGroupBusiSV groupBusiSV;
    /**
     * 添加标准品库存组<br>
     *
     * @param storageGroup 库存组对象
     * @return 添加结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0100
     */
    @Override
    public BaseResponse installStorageGroup(StorageGroup storageGroup) throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(storageGroup.getTenantId(),"");
        groupBusiSV.addGroup(storageGroup);
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    /**
     * 根据库存组标识查询库存组信息<br>
     *
     * @param infoQuery 库存组对象查询条件
     * @return 查询到的库存组信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0101
     */
    @Override
    public StorageGroupRes queryGroupInfoByGroupId(StorageGroupQuery infoQuery) throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(infoQuery.getTenantId(),"");
        return groupBusiSV.queryGroupInfoByGroupId(infoQuery.getTenantId(),infoQuery.getGroupId());
    }

    /**
     * 根据标准品标识查询库存组信息<br>
     *
     * @param infoQuery 库存组对象查询条件
     * @return 查询到的库存组信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0102
     */
    @Override
    public List<StorageGroupRes> queryGroupInfoByNormProId(StorageGroupQuery infoQuery) throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(infoQuery.getTenantId(),"");
        return groupBusiSV.queryGroupInfoByNormProId(infoQuery.getTenantId(),infoQuery.getProductId());
    }

    /**
     * 更改标准品库存组状态<br>
     * 包括启用,停用,废弃
     *
     * @param groupStatus 要设置的库存组状态对象
     * @return 添加结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0103
     */
    @Override
    public BaseResponse chargeStorageGroupStatus(StorageGroupStatus groupStatus) throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(groupStatus.getTenantId(),"");
        groupBusiSV.updateGroupState(groupStatus.getTenantId(),groupStatus.getGroupId(),groupStatus.getState(),groupStatus.getOperId());
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    /**
     * 查询虚拟库存组列表<br>
     *
     * @param groupQuery 库存组查询信息对象
     * @return 库存组列表
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0104
     */
    @Override
    public PageInfoForRes<StorageGroup4List> queryGroup(StorageGroupQueryPage groupQuery) throws BusinessException, SystemException {
        return null;
    }

    /**
     * 保存标准品库存信息<br>
     *
     * @param stoStorage 要保存的库存对象
     * @return 保存结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0105
     */
    @Override
    public BaseResponse saveStorage(Storage stoStorage) throws BusinessException, SystemException {
        return null;
    }

    /**
     * 查询标准品库存信息<br>
     *
     * @param storageId 库存标识
     * @return 标准品库存信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0106
     */
    @Override
    public StorageRes queryStorageById(String storageId) throws BusinessException, SystemException {
        return null;
    }

    /**
     * 更改标准品库存状态<br>
     * 包括启用,停用,废弃
     *
     * @param storageStatus 要设置的库存状态对象
     * @return 更新结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0107
     */
    @Override
    public BaseResponse chargeStorageStatus(StorageStatus storageStatus) throws BusinessException, SystemException {
        return null;
    }

    /**
     * 变更库存组中库存优先级
     *
     * @param priorityCharge 优先级变更信息
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0108
     */
    @Override
    public BaseResponse chargeStoragePriority(StoragePriorityCharge priorityCharge) throws BusinessException, SystemException {
        return null;
    }

    /**
     * 更新库存组信息
     *
     * @param storageGroup 库存组信息
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0109
     */
    @Override
    public BaseResponse updateStorageGroupName(StorageGroupUpName storageGroup) throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(storageGroup.getTenantId(),"");
        groupBusiSV.updateGroupName(storageGroup);
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    /**
     * 更新库存组销售价信息
     *
     * @param salePrice 库存组销售价信息
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0110
     */
    @Override
    public BaseResponse updateStorageGroupSalePrice(StorageGroupSalePrice salePrice) throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(salePrice.getTenantId(),"");
        groupBusiSV.updateStorageGroupPrice(salePrice);
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    /**
     * 查询标准品列表,包含标准品的库存组,适用于商城商品定销售价<br>
     * 库存组不包括废弃状态的
     *
     * @param groupQuery 库存组查询信息对象
     * @return 库存组列表
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0111
     */
    @Override
    public PageInfoForRes<StorageGroup4SaleList> queryGroupsForSalePrice(StorageGroupQueryPage groupQuery) throws BusinessException, SystemException {
        return null;
    }

    /**
     * 根据标准品标识查询库存组信息<br>
     * 库存组不包括废弃状态的
     *
     * @param infoQuery 库存组对象查询条件
     * @return 查询到的库存组信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0112
     */
    @Override
    public PageInfoForRes<StorageGroupRes> queryGroupByProdIdForSalePrice(StorageGroupOfNormProdPage infoQuery) throws BusinessException, SystemException {
        return null;
    }

    /**
     * 批量更新库存销售价<br>
     *
     * @param salePriceList 库存批量销售价信息
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0113
     */
    @Override
    public BaseResponse updateMultiStorageSalePrice(List<StorageSalePrice> salePriceList) throws BusinessException, SystemException {
        return null;
    }
}
