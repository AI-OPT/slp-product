package com.ai.slp.product.api.product.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.product.param.*;

import javax.ws.rs.Consumes;
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
     * 商品管理查询商品编辑状态<br>
     * 状态 1未编辑2已编辑<br>
     * 按生成时间排序<br>
     * 
     * @param productEditParam
     * @return 满足条件的商品集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode PROMAN_0100
     */
    public PageInfoResponse<ProductEditUp> queryProductEdit(ProductEditQueryReq productEditParam) throws BusinessException, SystemException;
    @interface QueryProductEdit {}
    
    /**
     * 商品管理查询商品被拒绝信息-与商品审核中查询共用<br>
     * 拒绝时间为操作时间<br>
     * 
     * @param productRefuseParam
     * @return 满足条件的商品集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode PROMAN_0101
     */
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
    *  @ApiCode PROMAN_0102
     */
    public PageInfoResponse<ProductEditUp> queryProductCheck(ProductEditQueryReq productCheckingParam) throws BusinessException, SystemException;
    @interface QueryProductCheck {}

    /**
     * 审核是否通过调用方法<br>
     * 通过上架类型 1审核通过后立即上架 2审核通过后放入仓库 3定时上架<br>
     * 判断改变为哪种状态<br>
     * 销售商品状态 4审核未通过<br>
     * 拒绝后把拒绝类型和原因存入销售商品流程日志表<br>
     *
     * @param productCheckParam
     * @return 基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     *  @ApiCode PROMAN_0103
     */
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
     *  @ApiCode PROMAN_0104
     */
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
     * @ApiCode PROMAN_0105
     */
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
     *  @ApiCode PROMAN_0106
     */
    public PageInfoResponse<ProductStorageSale> queryStorageProdByState(ProductStorageSaleParam productStorageSaleParam) throws BusinessException,SystemException;
    @interface QueryStorageProdByState {}

    /**
     * 对商品进行上架处理
     * @param query
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode PROMAN_0107
     */
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
     * @ApiCode PROMAN_0108
     */
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
     * @ApiCode PROMAN_0109
     */
    public BaseResponse updateProduct(ProductInfoForUpdate product)
    		throws BusinessException,SystemException;
    @interface SaveProduct{}
}







