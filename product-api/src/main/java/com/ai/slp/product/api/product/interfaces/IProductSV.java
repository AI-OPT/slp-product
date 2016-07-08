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
import java.util.List;

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
     * 查询商品列表<br>
     *
     * @param productQuery 查询对象
     * @return 商品信息列表
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode PRODUCT_0100
     */
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
     * @ApiCode PRODUCT_0101
     */
    public ProductInfo queryProductById(ProductInfoQuery productInfoQuery)
        throws BusinessException,SystemException;
    @interface QueryProductById{}

    /**
     * 批量更新SKU销售价<br>
     *
     * @param skuSalPrices sku销售价结婚
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode PRODUCT_0102
     */
    public BaseResponse updateMultSKUSalePrice(List<ProdSkuSalPrice> skuSalPrices)
            throws BusinessException,SystemException;
    @interface UpdateMultSKUSalePrice{}

    /**
     * 更新商品SKU信息<br>
     *
     * @param saveInfo 商品对应SKU属性和属性值信息
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode PRODUCT_0103
     */
    public BaseResponse saveMultSKUInfo(SkuInfoMultSave saveInfo)
            throws BusinessException,SystemException;
    @interface SaveMultSKUInfo{}

    /**
     * 查询单个商城商品下的sku集合信息
     *
     * @param query sku销售价
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode PRODUCT_0104
     */
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
     * @ApiCode PRODUCT_0105
     */
    public ProdAttrMap queryNoKeyAttrInfo(ProductInfoQuery productInfoQuery)
            throws BusinessException,SystemException;
    @interface QueryNoKeyAttrInfo{}
}
