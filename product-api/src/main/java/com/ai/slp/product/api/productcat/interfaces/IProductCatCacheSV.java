package com.ai.slp.product.api.productcat.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseListResponse;
import com.ai.slp.product.api.productcat.param.ProdCatLevelParam;
import com.ai.slp.product.api.productcat.param.ProductCatInfo;
import com.ai.slp.product.api.productcat.param.ProductCatUniqueReq;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 商品类目缓存方式查询接口<br>                                                                                                       
 *
 * Date: 2016年7月21日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author liutong5
 */
@Path("/prodCatCache")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IProductCatCacheSV {

    /**
     * 查询指定标识的类目信息
     *
     * @param catUniqueReq
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode PROD_CAT_CACHE_0100
     * @RestRelativeURL prodCatCache/cat
     */
    @POST
    @Path("/cat")
    public ProductCatInfo queryByCatId(ProductCatUniqueReq catUniqueReq)
            throws BusinessException,SystemException;
    @interface QueryByCatId{}

    /**
     * 查询类目的类目链
     *
     * @param catUniqueReq
     * @return 从当前类目一直到根类目的类目链
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode PROD_CAT_CACHE_0101
     * @RestRelativeURL prodCatCache/linkCat
     */
    @POST
    @Path("/linkCat")
    public BaseListResponse<ProductCatInfo> queryLinkOfCatById(ProductCatUniqueReq catUniqueReq)
            throws BusinessException,SystemException;
    @interface QueryLinkOfCatById{}

    /**
     * 查询类目的下级类目集合
     *
     * @param catUniqueReq
     * @return 当前级别的下级类目集合信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode PROD_CAT_CACHE_0102
     * @RestRelativeURL prodCatCache/childCat
     */
    @POST
    @Path("/childCat")
    public BaseListResponse<ProductCatInfo> queryChildOfCatById(ProductCatUniqueReq catUniqueReq)
            throws BusinessException,SystemException;
    @interface QueryChildOfCatById{}

    /**
     * 查询指定级别的类目集合
     *
     * @param levelParam
     * @return 指定级别的类目集合
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode PROD_CAT_CACHE_0103
     * @RestRelativeURL prodCatCache/levelCat
     */
    @POST
    @Path("/levelCat")
    public BaseListResponse<ProductCatInfo> queryByLevel(ProdCatLevelParam levelParam)
            throws BusinessException,SystemException;
    @interface QueryByLevel{}

}
