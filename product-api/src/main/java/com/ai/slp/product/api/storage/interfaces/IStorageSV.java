package com.ai.slp.product.api.storage.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.slp.product.api.storage.param.*;

import java.util.List;

/**
 * 标准品库存操作<br>
 *
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public interface IStorageSV {

    /**
     * 添加标准品库存组<br>
     *
     * @param storageGroup 库存组对象
     * @return 添加结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0300
     */
    public BaseResponse installStorage(STOStorageGroup storageGroup)
        throws BusinessException,SystemException;
    @interface InstallStorage{}

    /**
     * 根据库存组标识查询库存组信息<br>
     *
     * @param infoQuery 库存组对象查询条件
     * @return 查询到的库存组信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0301
     */
    public StorageGroupInfo queryGroupInfoById(StorageGroupInfoQuery infoQuery)
            throws BusinessException,SystemException;
    @interface QueryGroupInfoById{}

    /**
     * 根据标准品标识查询库存组信息<br>
     *
     * @param infoQuery 库存组对象查询条件
     * @return 查询到的库存组信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0302
     */
    public List<StorageGroupInfo> queryGroupInfoByNormProId(StorageGroupInfoQuery infoQuery)
            throws BusinessException,SystemException;
    @interface QueryGroupInfoByProductId{}

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
     * @ApiCode STORAGE_0303
     */
    public BaseResponse chargeStorageGroupStatus(StorageGroupStatus groupStatus)
            throws BusinessException,SystemException;
    @interface ChargeStorageGroupStatus{}

    /**
     * 查询标准品库存组列表<br>
     *
     * @param groupQuery 库存组查询信息对象
     * @return 库存组列表
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0304
     */
    public PageInfoWrapper<STOStorageGroup4List> queryGroupList(STOStorageGroupQuery groupQuery)
            throws BusinessException,SystemException;
    @interface QueryGroupList{}

    /**
     * 保存标准品库存信息<br>
     *
     * @param stoStorage 要保存的库存对象
     * @return 保存结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0305
     */
    public BaseResponse saveStorage(STOStorage stoStorage)
            throws BusinessException,SystemException;
    @interface SaveStorage{}

    /**
     * 查询标准品库存信息<br>
     *
     * @param storageId 库存标识
     * @return 标准品库存信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0306
     */
    public STOStorage queryStorageById(String storageId)
            throws BusinessException,SystemException;
    @interface QueryStorageById{}

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
     * @ApiCode STORAGE_0307
     */
    public BaseResponse chargeStorageStatus(StorageStatus storageStatus)
            throws BusinessException,SystemException;
    @interface ChargeStorageStatus{}

    /**
     * 变更库存组中库存优先级
     *
     * @param priorityCharge 优先级变更信息
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0308
     */
    public BaseResponse chargeStoragePriority(StoragePriorityCharge priorityCharge)
            throws BusinessException,SystemException;
    @interface ChargeStoragePriority{}

    /**
     * 更新库存组信息
     *
     * @param storageGroup 库存组信息
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0309
     */
    public BaseResponse updateStorageGroup(STOStorageGroup storageGroup)
        throws BusinessException,SystemException;
    @interface UpdateStorageGroup{}

    /**
     * 更新库存组销售价信息
     *
     * @param salePrice 库存组销售价信息
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0310
     */
    public BaseResponse updateStorageGroupSalePrice(StorageGroupSalePrice salePrice)
            throws BusinessException,SystemException;
    @interface UpdateStorageGroupSalePrice{}

    /**
     * 查询标准品列表,包含标准品的库存组,适用于商城商品定销售价<br>
     *  库存组不包括废弃状态的
     *
     * @param groupQuery 库存组查询信息对象
     * @return 库存组列表
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0311
     */
    public PageInfoWrapper<STOStorageGroup4SaleList> queryGroupListForSalePrice(STOStorageGroupQuery groupQuery)
            throws BusinessException,SystemException;
    @interface QueryGroupListForSalePrice{}

    /**
     * 根据标准品标识查询库存组信息<br>
     *  库存组不包括废弃状态的
     * @param infoQuery 库存组对象查询条件
     * @return 查询到的库存组信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0312
     */
    public List<StorageGroupInfo> queryGroupByProdIdForSalePrice(StorageGroupInfoQuery infoQuery)
            throws BusinessException,SystemException;
    @interface QueryGroupByProdIdForSalePrice{}

    /**
     * 批量更新库存销售价<br>
     *
     * @param salePriceList 库存批量销售价信息
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0313
     */
    public BaseResponse updateMultiStorageSalePrice(List<STOStorageSalePrice> salePriceList)
            throws BusinessException,SystemException;
    @interface UpdateMultiStorageSalePrice{}
}