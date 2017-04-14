package com.ai.slp.product.api.product.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseListResponse;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.product.param.ProdAttrMap;
import com.ai.slp.product.api.product.param.ProdTargetAreaInfo;
import com.ai.slp.product.api.product.param.Product4List;
import com.ai.slp.product.api.product.param.ProductEditQueryReq;
import com.ai.slp.product.api.product.param.ProductInfo;
import com.ai.slp.product.api.product.param.ProductInfoQuery;
import com.ai.slp.product.api.product.param.ProductListQuery;
import com.ai.slp.product.api.product.param.SkuInfoMultSave;
import com.ai.slp.product.api.product.param.SkuSetForProduct;
import com.ai.slp.product.api.product.param.StoGroupInfoQuery;
import com.ai.slp.product.api.product.param.StorageInfoQuery;
import com.ai.slp.product.api.product.param.TargetAreaForProd;

/**
 * 商城商品操作<br>
 *
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
@Path("/product")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IProductSV {
    /**
     * 分页查询非废弃的销售商品列表<br>
     * 用于销售价设置
     * @param productQuery 查询对象
     * @return 商品信息列表
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @RestRelativeURL product/searchProdList
     * @ApiCode PRODUCT_0100
     */
	@POST
	@Path("/searchProdList")
    public PageInfoResponse<Product4List> queryProductPage(ProductListQuery productQuery)
        throws BusinessException,SystemException;
    @interface QueryProductList{}

    /**
     * 根据商品标识查询商品详情<br>
     *
     * @param productInfoQuery 查询对象
     * @return 商品信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @RestRelativeURL product/searchProdInfo
     * @ApiCode PRODUCT_0101
     */
    @POST
	@Path("/searchProdInfo")
    public ProductInfo queryProductById(ProductInfoQuery productInfoQuery)
        throws BusinessException,SystemException;
    @interface QueryProductById{}

    /**
     * 更新商品SKU信息<br>
     *
     * @param saveInfo 商品对应SKU属性和属性值信息
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @RestRelativeURL product/updateSKUInfo
     * @ApiCode PRODUCT_0103
     */
    @POST
	@Path("/updateSKUInfo")
    public BaseResponse saveMultSKUInfo(SkuInfoMultSave saveInfo)
            throws BusinessException,SystemException;
    @interface SaveMultSKUInfo{}

    /**
     * 查询单个商城商品下的sku集合信息
     *
     * @param query 商品信息
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @RestRelativeURL product/searchSKUInfo
     * @ApiCode PRODUCT_0104
     */
    @POST
	@Path("/searchSKUInfo")
    public SkuSetForProduct querySkuSetForProduct(ProductInfoQuery query)
            throws BusinessException,SystemException;
    @interface QuerySkuSetForProduct{}

    /**
     * 查询单个商品的非关键属性
     *
     * @param productInfoQuery 商品标识信息
     * @return 非关键属性
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @RestRelativeURL product/searchNoKeyInfo
     * @ApiCode PRODUCT_0105
     */
    @POST
	@Path("/searchNoKeyInfo")
    public ProdAttrMap queryNoKeyAttrInfo(ProductInfoQuery productInfoQuery)
            throws BusinessException,SystemException;
    @interface QueryNoKeyAttrInfo{}
    
    /**
     * 根据商品ID查询满足条件的商品目标地域集合
     * @param productEditParam 商品标识信息
     * @return 商品目标地域对象
     * @throws BusinessException
     * @throws SystemException
     * @author jiawen
     * @ApiDocMethod
     * @RestRelativeURL product/searchProdTargetArea
     * @ApiCode PRODUCT_0106
     */
    @POST
    @Path("/searchProdTargetArea")
    public PageInfoResponse<TargetAreaForProd> searchProdTargetArea(ProductEditQueryReq productEditParam)
            throws BusinessException,SystemException;
        @interface SearchProdTargetArea{}

    /**
     * 查询单个库存组下的sku集合信息
     *
     * @param query 库存组信息
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @RestRelativeURL product/searchSKUInfoStoGroup
     * @ApiCode PRODUCT_0107
     */
    @POST
    @Path("/searchSKUInfoStoGroup")
    public SkuSetForProduct querySkuSetForGroup(StoGroupInfoQuery query)
            throws BusinessException,SystemException;
    @interface QuerySkuSetForGroup{}

    /**
     * 查询单个库存下的sku集合信息,包括废弃的库存
     *
     * @param query 库存信息
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @RestRelativeURL product/searchSKUInfoStorage
     * @ApiCode PRODUCT_0108
     */
    @POST
    @Path("/searchSKUInfoStorage")
    public SkuSetForProduct querySkuSetForStorage(StorageInfoQuery query)
            throws BusinessException,SystemException;
    @interface querySkuSetForStorage{}

    /**
     * 查询单个商品的目标地域信息
     *
     * @param query
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @RestRelativeURL product/queryAreaInfosOfProduct
     * @ApiCode PRODUCT_0109
     */
    @POST
    @Path("/queryAreaInfosOfProduct")
    public BaseListResponse<ProdTargetAreaInfo> queryAreaInfosOfProduct(ProductInfoQuery query)
            throws BusinessException,SystemException;
    
}
