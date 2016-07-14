package com.ai.slp.product.api.storageserver.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.slp.product.api.storageserver.param.StorageNumBackReq;
import com.ai.slp.product.api.storageserver.param.StorageNumRes;
import com.ai.slp.product.api.storageserver.param.StorageNumUseReq;
import com.ai.slp.product.api.storageserver.param.StorageNumUserReq;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 库存数量操作<br>
 *
 * Date: 2016年5月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
@Path("/storageNum")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IStorageNumSV {

    /**
     * 使用库存,即减库存量<br>
     * 接口无法满足需求,逐步废弃,使用新方法STORAGE_NUM_0104代替
     *
     * @param numReq 使用单品数量信息
     * @return 库存量减少信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_NUM_0100
     * @RestRelativeURL storageNum/useStorageNum
     */
    @POST
    @Path("/useStorageNum")
    @Deprecated
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
     * @RestRelativeURL storageNum/backStorageNum
     */
    @POST
    @Path("/backStorageNum")
    public BaseResponse backStorageNum(StorageNumBackReq backReq)
        throws BusinessException,SystemException;
    @interface BackStorageNum{}

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
     * @RestRelativeURL storageNum/addSaleNumOfSku
     */
    @POST
    @Path("/addSaleNumOfSku")
    public BaseResponse addSaleNumOfProduct(StorageNumUserReq numReq)
            throws BusinessException,SystemException;
    @interface AddSaleNumOfProduct{};

    /**
     * 减少商品销量<br>
     *
     * @param numReq SKU销量信息
     * @return 减少结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_NUM_0103
     * @RestRelativeURL storageNum/backSaleNumOfSku
     */
    @POST
    @Path("/backSaleNumOfSku")
    public BaseResponse backSaleNumOfProduct(StorageNumUserReq numReq)
            throws BusinessException,SystemException;
    @interface BackSaleNumOfProduct{};

    /**
     * 使用库存,即减库存量<br>
     *
     * @param numReq 使用单品数量信息
     * @return 库存量减少信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_NUM_0104
     * @RestRelativeURL storageNum/useStorageNumV1
     */
    @POST
    @Path("/useStorageNumV1")
    public StorageNumRes useStorageNum(StorageNumUseReq numReq)
            throws BusinessException,SystemException;

}
