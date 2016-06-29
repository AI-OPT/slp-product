package com.ai.slp.product.api.storageserver.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.slp.product.api.storageserver.interfaces.IStorageNumSV;
import com.ai.slp.product.api.storageserver.param.StorageNumBackReq;
import com.ai.slp.product.api.storageserver.param.StorageNumRes;
import com.ai.slp.product.api.storageserver.param.StorageNumUseReq;
import com.ai.slp.product.api.storageserver.param.StorageNumUserReq;
import com.ai.slp.product.service.business.interfaces.IProdSaleAllBusiSV;
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
    @Autowired
    IProdSaleAllBusiSV prodSaleAllBusiSV;
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
    @Deprecated
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
        CommonCheckUtils.checkTenantId(backReq.getTenantId(),"");
        storageNumBusiSV.backStorageNum(backReq.getTenantId(),backReq.getSkuId(),backReq.getStorageNum());
        BaseResponse response = new BaseResponse();
        ResponseHeader header = new ResponseHeader();
        header.setResultCode(ExceptCodeConstants.Special.SUCCESS);
        header.setIsSuccess(true);
        header.setResultMessage("OK");
        response.setResponseHeader(header);
        return response;
    }

    /**
     * 增加商品销量<br>
     *
     * @param numReq SKU销量信息
     * @return 增加结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_NUM_0102
     */
    @Override
    public BaseResponse addSaleNumOfProduct(StorageNumUserReq numReq) throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(numReq.getTenantId(),"");
        //添加使用正数
        prodSaleAllBusiSV.updateSaleNum(numReq.getTenantId(),numReq.getSkuId(),numReq.getSkuNum());
        BaseResponse response = new BaseResponse();
        response.setResponseHeader(new ResponseHeader(true,ExceptCodeConstants.Special.SUCCESS,"OK"));
        return response;
    }

    @Override
    public BaseResponse backSaleNumOfProduct(StorageNumUserReq numReq) throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(numReq.getTenantId(),"");
        //减少使用负数
        prodSaleAllBusiSV.updateSaleNum(numReq.getTenantId(),numReq.getSkuId(),-numReq.getSkuNum());
        BaseResponse response = new BaseResponse();
        response.setResponseHeader(
                new ResponseHeader(true,ExceptCodeConstants.Special.SUCCESS,"OK"));
        return response;
    }

    @Override
    public StorageNumRes useStorageNum(StorageNumUseReq numReq) throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(numReq.getTenantId(),"");
        return storageNumBusiSV.userNumWithAudiAndPrice(numReq);
    }
}
