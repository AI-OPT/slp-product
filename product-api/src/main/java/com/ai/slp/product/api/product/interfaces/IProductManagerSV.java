package com.ai.slp.product.api.product.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.product.param.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 商品管理接口
 * 
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
@Path("/productManager")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IProductManagerSV {
    
    /**
     * 商品管理分页查询商品待编辑商品列表<br>
     * 操作时间倒序排列<br>
     * 0新增\n
     * 
     * @param productEditParam
     * @return 满足条件的商品集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     * @RestRelativeURL productManager/stateSearch
     * @ApiCode PROMAN_0100
     */
	@POST
	@Path("/stateSearch")
    public PageInfoResponse<ProductEditUp> queryProductEdit(ProductEditQueryReq productEditParam) throws BusinessException, SystemException;
    @interface QueryProductEdit {}
    
    /**
     * 商品管理查询商品被拒绝信息-与商品审核中查询共用<br>
     * 操作时间倒序排列<br>
     * 
     * @param productRefuseParam
     * @return 满足条件的商品集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     * @RestRelativeURL productManager/searchRefuseInfo
    *  @ApiCode PROMAN_0101
     */
    @POST
	@Path("/searchRefuseInfo")
    public PageInfoResponse<ProductEditUp> queryProductRefuse(ProductEditQueryReq productRefuseParam) throws BusinessException, SystemException;
    @interface QueryProductRefuse {}
    
    /**
     * 商品管理查询商品审核状态-与商品审核中查询共用<br>
     * 状态：3审核中<br>
     * 提交时间为操作时间<br>
     * 
     * @param productCheckingParam
     * @return 满足条件的商品集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     * @RestRelativeURL productManager/searchCheckInfo
    *  @ApiCode PROMAN_0102
     */
    @POST
	@Path("/searchCheckInfo")
    public PageInfoResponse<ProductEditUp> queryProductCheck(ProductEditQueryReq productCheckingParam) throws BusinessException, SystemException;
    @interface QueryProductCheck {}

    /**
     * 商品审核通过/拒绝<br>
     *
     * @param productCheckParam
     * @return 基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     * @RestRelativeURL productManager/check
     *  @ApiCode PROMAN_0103
     */
    @POST
	@Path("/check")
    public BaseResponse productCheck(ProductCheckParam productCheckParam)
            throws BusinessException, SystemException;
    @interface ProductCheck {}

    /**
     * 商品申请优先审核调用方法<br>
     *
     * @param productPriorityParam
     * @return 基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     * @RestRelativeURL productManager/priorityCheck
     *  @ApiCode PROMAN_0104
     */
    @POST
	@Path("/priorityCheck")
    public BaseResponse productPriority(ProductPriorityParam productPriorityParam)
            throws BusinessException, SystemException;
    @interface ProductPriority {}
    

    /**
     * 查询单个商品的其他设置内容<br>
     *
     * @param productInfoQuery 单个商品的标识信息
     * @return 单个商品的其他设置内容
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @RestRelativeURL productManager/searchOtherInfo
     * @ApiCode PROMAN_0105
     */
    @POST
	@Path("/searchOtherInfo")
    public OtherSetOfProduct queryOtherSetOfProduct(ProductInfoQuery productInfoQuery)
            throws BusinessException,SystemException;
    @interface QueryAudiencesOfProduct{}


    /**
     * 查询仓库中的全部商品<br>
     * 不判断状态返回所有商品<br>
     * 根据状态不同返回不同类型的集合：<br>
     * 5在售61售罄下架62废弃下架<br>
     * 待上架:6仓库中（审核通过放入） 61售罄下架 定时上架-通过上架类型判断<br>
     *
     * @param productStorageSaleParam
     * @return 商品管理售中与仓库商品返回类集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     * @RestRelativeURL productManager/searchAll
     *  @ApiCode PROMAN_0106
     */
    @POST
	@Path("/searchAll")
    public PageInfoResponse<ProductStorageSale> queryStorageProdByState(ProductStorageSaleParam productStorageSaleParam) throws BusinessException,SystemException;
    @interface QueryStorageProdByState {}

    /**
     * 对商品进行上架处理
     * @param query
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @RestRelativeURL productManager/toInSale
     * @ApiCode PROMAN_0107
     */
    @POST
	@Path("/toInSale")
    public BaseResponse changeToInSale(ProductInfoQuery query)
        throws BusinessException,SystemException;
    @interface ChangeToInSale {}

    /**
     * 为编辑页面查询商品非关键属性
     * @param query
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @RestRelativeURL productManager/searchNoKeyInfo
     * @ApiCode PROMAN_0108
     */
    @POST
	@Path("/searchNoKeyInfo")
    public ProdNoKeyAttr queryNoKeyAttrOfProd(ProductInfoQuery query)
            throws BusinessException,SystemException;
    @interface QueryNoKeyAttrOfProd {}
    
    /**
     * 商品信息进行更新
     * @param product
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @RestRelativeURL productManager/update
     * @ApiCode PROMAN_0109
     */
    @POST
	@Path("/update")
    public BaseResponse updateProduct(ProductInfoForUpdate product)
    		throws BusinessException,SystemException;
    @interface SaveProduct{}
    
    /**
     * 对商品进行手动下架处理
     * @param query
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author jiawen
     * @RestRelativeURL productManager/toInStore
     * @ApiCode PROMAN_0110
     */
    @POST
	@Path("/toInStore")
    public BaseResponse changeToInStore(ProductInfoQuery query)
        throws BusinessException,SystemException;
    @interface ChangeToInStore {}
    
    /**
     * 查询在售商品  -- 按上架时间倒序
     * 
     * @param productEditParam
     * @return 满足条件的商品集合
     * @throws BusinessException
     * @throws SystemException
     * @author jiawen
     * @RestRelativeURL productManager/searchInSale
     * @ApiCode PROMAN_0111
     */
    @POST
    @Path("/searchInSale")
    public PageInfoResponse<ProductEditUp> searchInSale(ProductQueryInfo queryInSale) throws BusinessException, SystemException;
    @interface SearchInSale {}
    /**
     * 查询商品审核 
     * 
     * @param ProductQueryInfo
     * @return 满足条件的商品集合
     * @throws BusinessException
     * @throws SystemException
     * @author jiawen
     * @RestRelativeURL productManager/searchAudit
     * @ApiCode PROMAN_0112
     */
    @POST
    @Path("/searchAudit")
    public PageInfoResponse<ProductEditUp> searchAudit(ProductQueryInfo queryInfo) throws BusinessException, SystemException;
    @interface SearchAudit {}
    
}