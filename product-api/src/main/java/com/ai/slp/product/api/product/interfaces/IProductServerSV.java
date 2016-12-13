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
 * 商城商品服务提供接口<br>
 *
 * Date: 2016年5月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
@Path("/productServer")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IProductServerSV {
    /**
     * 根据销售商品sku标识查询商品单品详情信息<br>
     *
     * @param skuInfoQuery 查询对象
     * @return 商品sku信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @RestRelativeURL productServer/searchProdInfo
     * @ApiCode PRODUCT_SERVER_0100
     */
	@POST
	@Path("/searchProdInfo")
    public ProductSkuInfo queryProductSkuById(SkuInfoQuery skuInfoQuery)
            throws BusinessException,SystemException;
    @interface QueryProducSkutById{}

    /**
     * 根据销售商品关联路由组ID
     * @param productInfoQuery
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @RestRelativeURL productServer/routeGroup
     * @ApiCode PRODUCT_SERVER_0101
     */
    @POST
	@Path("/routeGroup")
    public ProductRoute queryRouteGroupOfProd(ProductInfoQuery productInfoQuery)
            throws BusinessException,SystemException;
    @interface QueryRouteGroupOfProd{}

    /**
     * 设置商品的配货组
     * @param setInfo
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @RestRelativeURL productServer/changeRouteGroup
     * @ApiCode PRODUCT_SERVER_0102
     */
    @POST
    @Path("/changeRouteGroup")
    public BaseResponse changeRouteGroup(RouteGroupSet setInfo)
            throws BusinessException,SystemException;


    /**
     * 查询商品和配货组信息
     *
     * @param query
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @RestRelativeURL productServer/queryProductAndRouteGroup
     * @ApiCode PRODUCT_SERVER_0103
     */
    @POST
    @Path("/queryProductAndRouteGroup")
    public PageInfoResponse<ProductRouteGroupInfo> queryProductAndRouteGroup(RouteGroupQuery query)
            throws BusinessException,SystemException;
    
    /**
     * 根据销售商品sku标识查询商品单品详情信息(订单专用)
     * @param skuInfoQuery 查询对象
     * @return 商品sku信息
     * @throws BusinessException
     * @throws SystemException
     * @author Gavin
     * @ApiDocMethod
     * @RestRelativeURL productServer/queryProductSkuById4ShopCart
     * @ApiCode PRODUCT_SERVER_0104
     */
    @POST
	@Path("/queryProductSkuById4ShopCart")
    public ProductSkuInfo queryProductSkuById4ShopCart(SkuInfoQuery skuInfoQuery)
            throws BusinessException,SystemException;
}
