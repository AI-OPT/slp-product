package com.ai.slp.product.api.storageserver.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.slp.product.api.storageserver.param.StorageNumBackReq;
import com.ai.slp.product.api.storageserver.param.StorageNumUserReq;
import com.ai.slp.product.api.storageserver.param.StorageNumRes;

/**
 * 库存数量操作<br>
 *
 * Date: 2016年5月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public interface IStorageNumSV {

    /**
     * 使用库存,即减库存量<br>
     *
     * @param numReq 使用单品数量信息
     * @return 库存量减少信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_NUM_0100
     */
    public StorageNumRes useStorageNum(StorageNumUserReq numReq)
        throws BusinessException,SystemException;
    @interface UseStorageNum{};
    /**
     * 退回库存量<br>
     *
     * @param backReq 退回单品数量信息
     * @return 退回结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_NUM_0101
     */
    public BaseResponse backStorageNum(StorageNumBackReq backReq)
        throws BusinessException,SystemException;
    @interface BackStorageNum{}
}
