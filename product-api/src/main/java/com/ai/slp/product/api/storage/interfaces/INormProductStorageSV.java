package com.ai.slp.product.api.storage.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.slp.product.api.storage.param.STOStorage;
import com.ai.slp.product.api.storage.param.STOStorageGroup;
import com.ai.slp.product.api.storage.param.StorageGroupStatus;
import com.ai.slp.product.api.storage.param.StorageStatus;

/**
 * 标准品库存操作<br>
 *
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public interface INormProductStorageSV {

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
     * 更改标准品库存组状态<br>
     * 包括启用,停用,废弃
     *
     * @param groupStatus 要设置的库存组状态对象
     * @return 添加结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0301
     */
    public BaseResponse chargeStorageGroupStatus(StorageGroupStatus groupStatus)
            throws BusinessException,SystemException;
    @interface ChargeStorageGroupStatus{}

    /**
     * 保存标准品库存信息<br>
     *
     * @param stoStorage 要保存的库存对象
     * @return 保存结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0302
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
     * @ApiCode STORAGE_0303
     */
    public STOStorage queryStorageById(String storageId)
            throws BusinessException,SystemException;
    @interface QueryStorageById{}

    /**
     * 更改标准品库存组状态<br>
     * 包括启用,停用,废弃
     *
     * @param storageStatus 要设置的库存状态对象
     * @return 更新结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0304
     */
    public BaseResponse chargeStorageStatus(StorageStatus storageStatus)
            throws BusinessException,SystemException;
    @interface ChargeStorageStatus{}



}
