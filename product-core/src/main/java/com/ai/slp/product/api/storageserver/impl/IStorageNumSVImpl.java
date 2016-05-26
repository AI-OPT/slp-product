package com.ai.slp.product.api.storageserver.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.slp.product.api.storageserver.interfaces.IStorageNumSV;
import com.ai.slp.product.api.storageserver.param.StorageNumBackReq;
import com.ai.slp.product.api.storageserver.param.StorageNumRes;
import com.ai.slp.product.api.storageserver.param.StorageNumUserReq;
import com.ai.slp.product.service.business.interfaces.IStorageNumBusiSV;
import com.ai.slp.product.util.CommonCheckUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jackieliu on 16/5/26.
 */
@Service(validation = "true")
@Component
public class IStorageNumSVImpl implements IStorageNumSV {
    @Autowired
    IStorageNumBusiSV storageNumBusiSV;
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
    @Override
    public StorageNumRes useStorageNum(StorageNumUserReq numReq) throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(numReq.getTenantId(),"");
        return storageNumBusiSV.userStorageNum(numReq.getTenantId(),numReq.getSkuId(),numReq.getSkuNum());
    }

    /**
     * 使用库存,即减库存量<br>
     *
     * @param backReq 使用单品数量信息
     * @return 库存量减少信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_NUM_0100
     */
    @Override
    public BaseResponse backStorageNum(StorageNumBackReq backReq) throws BusinessException, SystemException {
        return null;
    }
}
