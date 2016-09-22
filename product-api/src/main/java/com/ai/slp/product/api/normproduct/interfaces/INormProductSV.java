package com.ai.slp.product.api.normproduct.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.normproduct.param.*;

/**
 * 标准品处理接口<br>
 *
 * Date: 2016年4月18日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
@Path("/normProduct")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface INormProductSV {

    /**
     * 标准品列表查询. <br>
     *
     * @param productRequest 查询条件
     * @return 符合条件的标准品信息集合
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode NORM_PRODUCT_0100
     * @RestRelativeURL normProduct/queryList
     */
    @POST
    @Path("/queryList")
    public PageInfoResponse<NormProdResponse> queryNormProduct(NormProdRequest productRequest)
            throws BusinessException,SystemException;
    @interface QueryNormProduct {}

    /**
     * 查询指定标准品标识的标准品信息. <br>
     *
     * @param invalidRequest 标准品查询条件
     * @return 标准品详细信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode NORM_PRODUCT_0102
     * @RestRelativeURL normProduct/queryById
     */
    @POST
    @Path("/queryById")
    public NormProdInfoResponse queryProducById(NormProdUniqueReq invalidRequest)
            throws BusinessException,SystemException;
    @interface QueryProducById {}

    /**
     * 添加标准品信息. <br>
     *
     * @param request 标准品信息
     * @return 标准品保存结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode NORM_PRODUCT_0103
     * @RestRelativeURL normProduct/add
     */
    @POST
    @Path("/add")
    public BaseResponse createProductInfo(NormProdSaveRequest request)
            throws BusinessException,SystemException;
    @interface SaveProductInfo {}

    /**
     * 更新标准品信息. <br>
     * 不允许变更为废弃状态,要进行废弃操作,请使用废弃接口.
     *
     * @param productInfoRequest 标准品信息
     * @return 标准品更新结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode NORM_PRODUCT_0104
     * @RestRelativeURL normProduct/update
     */
    @POST
    @Path("/update")
    public BaseResponse updateProductInfo(NormProdSaveRequest productInfoRequest)
            throws BusinessException,SystemException;
    @interface UpdateProductInfo {}

    /**
     * 废弃标准品. <br>
     *
     * @param invalidRequest 标准品废弃请求参数
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode NORM_PRODUCT_0105
     * @RestRelativeURL normProduct/discard
     */
    @POST
    @Path("/discard")
    public BaseResponse discardProduct(NormProdUniqueReq invalidRequest)
            throws BusinessException,SystemException;
    @interface DiscardProduct {}

    /**
     * 更新标准品市场价. <br>
     *
     * @param marketPrice 标准品市场价
     * @return 更新结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode NORM_PRODUCT_0106
     * @RestRelativeURL normProduct/updateMarketPrice
     */
    @POST
    @Path("/updateMarketPrice")
    public BaseResponse updateMarketPrice(MarketPriceUpdate marketPrice)
            throws BusinessException,SystemException;
    @interface UpdateMarketPrice{}



    /**
     * 查询指定标准品下某种类型的属性集合<br>
     * 类型分为:关键属性,销售属性
     *
     * @param attrQuery 查询标准品信息
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode NORM_PRODUCT_0107
     * @RestRelativeURL normProduct/queryAttrsByIdAndType
     */
    @POST
    @Path("/queryAttrsByIdAndType")
    public AttrMap queryAttrByNormProduct(AttrQuery attrQuery)
            throws BusinessException,SystemException;
    @interface QueryAttrByNormProduct{}

    /**
     * 制定商品销售价中标准品列表查询.<br>
     *     库存组数量为非废弃的数量
     *
     * @param productRequest 查询标准品信息
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode NORM_PRODUCT_0108
     * @RestRelativeURL normProduct/queryListForSalePrice
     */
    @POST
    @Path("/queryListForSalePrice")
    public PageInfoResponse<NormProdResponse> queryNormProductForSalePrice(NormProdRequest productRequest)
            throws BusinessException,SystemException;
    @interface QueryNormProductForSalePrice {}


    /**
     * 分页查询标准品信息,包括标准品下的关键属性.<br>
     *
     * @param productRequest 查询标准品信息
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode NORM_PRODUCT_0109
     * @RestRelativeURL normProduct/queryProdAndKeyAttrList
     */
    @POST
    @Path("/queryProdAndKeyAttrList")
    public PageInfoResponse<NormProdAndKeyAttrRes> queryNormProductAndKeyAttr(NormProdRequest productRequest)
            throws BusinessException,SystemException;
    @interface QueryNormProductAndKeyAttr {}
    
    /**
     * 添加标准品信息.(同时生成标准品属性、库存组、sku) <br>
     *
     * @param request 标准品信息
     * @return 标准品保存结果
     * @throws BusinessException
     * @throws SystemException
     * @author jiaxs
     * @ApiDocMethod
     * @ApiCode NORM_PRODUCT_0110
     * @RestRelativeURL normProduct/createProductAndStoGroup
     */
    @POST
    @Path("/createProductAndStoGroup")
    public BaseResponse createProductAndStoGroup(NormProdSaveRequest request)
            throws BusinessException,SystemException;
    @interface CreateProductAndStoGroup {}
    
    /**
     * 更新标准品信息.(同时修改标准品属性、库存、sku) <br>
     * @param productInfoRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author jiaxs
     * @ApiDocMethod
     * @ApiCode	NORM_PRODUCT_0111
     * @RestRelativeURL normProduct/updateProductAndStoGroup
     */
    @POST
    @Path("/updateProductAndStoGroup")
    public BaseResponse updateProductAndStoGroup(NormProdSaveRequest productInfoRequest)
            throws BusinessException,SystemException;
    @interface UpdateProductAndStoGroup {}

    /**
     * 废弃标准品,并级联废弃销售商品和库存信息. <br>
     *
     * @param invalidRequest 标准品废弃请求参数
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode NORM_PRODUCT_0112
     * @RestRelativeURL normProduct/discardwithprod
     */
    @POST
    @Path("/discardwithprod")
    public BaseResponse discardProductWithStorage(NormProdUniqueReq invalidRequest)
            throws BusinessException,SystemException;
    @interface discardProductWithStorage {}
}
