package com.ai.slp.product.api.storage.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseListResponse;
import com.ai.opt.base.vo.BaseMapResponse;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.storage.param.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 标准品库存操作<br>
 *
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
@Path("/storage")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
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
     * @ApiCode STORAGE_0100
     * @RestRelativeURL storage/createGroup
     */
    @POST
    @Path("/createGroup")
    public BaseResponse createStorageGroup(STOStorageGroup storageGroup)
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
     * @ApiCode STORAGE_0101
     * @RestRelativeURL storage/queryGroupById
     */
    @POST
    @Path("/queryGroupById")
    public StorageGroupRes queryGroupInfoByGroupId(StorageGroupQuery infoQuery)
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
     * @ApiCode STORAGE_0102
     * @RestRelativeURL storage/queryGroupsByNormProdId
     */
    @POST
    @Path("/queryGroupsByNormProdId")
    public BaseListResponse<StorageGroupRes> queryGroupInfoByNormProdId(StorageGroupQuery infoQuery)
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
     * @ApiCode STORAGE_0103
     * @RestRelativeURL storage/chargeGroupStatus
     */
    @POST
    @Path("/chargeGroupStatus")
    public BaseResponse chargeStorageGroupStatus(StoGroupStatus groupStatus)
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
     * @ApiCode STORAGE_0104
     * @RestRelativeURL storage/groupList
     */
    @POST
    @Path("/groupList")
    public PageInfoResponse<StorageGroup4List> queryGroup(StorageGroupQueryPage groupQuery)
            throws BusinessException,SystemException;
    @interface QueryGroup{}

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
     * @RestRelativeURL storage/saveStorage
     */
    @POST
    @Path("/saveStorage")
    public BaseResponse saveStorage(STOStorage stoStorage)
            throws BusinessException,SystemException;
    @interface SaveStorage{}

    /**
     * 查询库存信息<br>
     *
     * @param query 库存标识
     * @return 标准品库存信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0106
     * @RestRelativeURL storage/queryStorageById
     */
    @POST
    @Path("/queryStorageById")
    public StorageRes queryStorageById(StorageUniQuery query)
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
     * @ApiCode STORAGE_0107
     * @RestRelativeURL storage/chargeStorageStatus
     */
    @POST
    @Path("/chargeStorageStatus")
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
     * @ApiCode STORAGE_0108
     * @RestRelativeURL storage/chargeStoragePriority
     * @deprecated 此接口暂不使用
     */
    @Deprecated
    @POST
    @Path("/chargeStoragePriority")
    public BaseResponse chargeStoragePriority(StoragePriorityCharge priorityCharge)
            throws BusinessException,SystemException;
    @interface ChargeStoragePriority{}

    /**
     * 更新库存组名称
     *
     * @param storageGroup 库存组信息
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0109
     * @RestRelativeURL storage/updateGroupName
     */
    @POST
    @Path("/updateGroupName")
    public BaseResponse updateStorageGroupName(NameUpReq storageGroup)
        throws BusinessException,SystemException;
    @interface UpdateStorageGroupName{}

    /**
     * 更新库存组最低最高销售价信息
     *
     * @param salePrice 库存组销售价信息
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0110
     * @RestRelativeURL storage/updateGroupSalePrice
     */
    @POST
    @Path("/updateGroupSalePrice")
    public BaseResponse updateStorageGroupSalePrice(StorageGroupSalePrice salePrice)
            throws BusinessException,SystemException;
    @interface UpdateStorageGroupSalePrice{}

    /**
     * 查询标准品列表,包含标准品的库存组,适用于商城商品定最低最高销售价<br>
     *  库存组不包括废弃状态的
     *
     * @param groupQuery 库存组查询信息对象
     * @return 库存组列表
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0111
     * @RestRelativeURL storage/queryGroupsForSalePrice
     */
    @POST
    @Path("/queryGroupsForSalePrice")
    public PageInfoResponse<StorageGroup4SaleList> queryGroupsForSalePrice(StorageGroupQueryPage groupQuery)
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
     * @ApiCode STORAGE_0112
     * @RestRelativeURL storage/queryGroupsByIdForSalePrice
     */
    @POST
    @Path("/queryGroupsByIdForSalePrice")
    public PageInfoResponse<StorageGroupRes> queryGroupByProdIdForSalePrice(StorageGroupOfNormProdPage infoQuery)
            throws BusinessException,SystemException;
    @interface QueryGroupByProdIdForSalePrice{}

    /**
     * 批量更新没有销售属性的库存销售价<br>
     *
     * @param salePrice 库存批量销售价信息
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0113
     * @RestRelativeURL storage/updateStorageSalePrice
     */
    @POST
    @Path("/updateStorageSalePrice")
    public BaseResponse updateMultiStorageSalePrice(StoNoSkuSalePriceReq salePrice)
            throws BusinessException,SystemException;
    @interface UpdateMultiStorageSalePrice{}
    
    /**
     * 查看SKU库存信息
     *
     * @param query 库存标识
     * @return map K:skuId, V:sku库存信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     * @ApiCode STORAGE_0114
     * @RestRelativeURL storage/querySkuStorageById
     */
    @POST
    @Path("/querySkuStorageById")
    public BaseMapResponse<String, SkuStorageInfo> querySkuStorageById(StorageUniQuery query) throws BusinessException,SystemException;
    @interface QuerySkuStorageById{}
    
    /**
     * 新增SKU库存信息(有销售属性)
     *
     * @param skuStorageAddList
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     * @ApiCode STORAGE_0115
     * @RestRelativeURL storage/addSkuStorages
     */
    @POST
    @Path("/addSkuStorages")
    public BaseResponse addSkuStorage(List<SkuStorageAdd> skuStorageAddList) throws BusinessException,SystemException;
    @interface AddSkuStorage {}

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
    @POST
    @Path("/updateStorageName")
    public BaseResponse updateStorageName(NameUpReq req)
        throws BusinessException,SystemException;
    @interface  UpdateStorageName {}

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
    @POST
    @Path("/updateSkuStoSalePrice")
    public BaseResponse updateSkuStorageSalePrice(StoSkuSalePrice salePrice)
            throws BusinessException,SystemException;
    @interface UpdateSkuStorageSalePrice{}

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
     * @RestRelativeURL storage/chargeGroupAutoStatus
     */
    @POST
    @Path("/chargeGroupAutoStatus")
    public BaseResponse chargeGroupStatusAuto(StoGroupAStatus aStatus)
            throws BusinessException,SystemException;
    @interface ChargeGroupStatusAuto{}

    /**
     * 查看库存组下某个级别的SKU价格
     *
     * @return map K:SKUID,V:价格(单位:厘)
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode STORAGE_0119
     * @RestRelativeURL storage/querySkuPriceByGroupPn
     */
    @POST
    @Path("/querySkuPriceByGroupPn")
    public BaseMapResponse<String,Long> querySkuPriceByGroupPn(SkuPriceOfGroupPnReq groupPnReq)
            throws BusinessException,SystemException;
    
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
    @POST
    @Path("/queryGroupAllById")
    public StorageGroupRestwo queryGroupInfoAllByGroupId(StorageGroupQuery infoQuery)
            throws BusinessException,SystemException;
    @interface QueryGroupInfoAllById{}

}
