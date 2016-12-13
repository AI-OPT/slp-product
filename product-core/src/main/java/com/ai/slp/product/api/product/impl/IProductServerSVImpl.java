package com.ai.slp.product.api.product.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.api.product.interfaces.IProductServerSV;
import com.ai.slp.product.api.product.param.*;
import com.ai.slp.product.api.webfront.param.ProductImage;
import com.ai.slp.product.api.webfront.param.ProductSKUResponse;
import com.ai.slp.product.service.business.interfaces.IProdSkuBusiSV;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IProductManagerBusiSV;
import com.ai.slp.product.util.CommonUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jackieliu on 16/5/31.
 */
@Service(validation = "true")
@Component
public class IProductServerSVImpl implements IProductServerSV {
    @Autowired
    IProductBusiSV productBusiSV;
    @Autowired
    IProdSkuBusiSV prodSkuBusiSV;
    @Autowired
    IProductManagerBusiSV productManagerBusiSV;
    /**
     * 根据销售商品sku标识查询商品单品详情信息<br>
     *
     * @param skuInfoQuery 查询对象
     * @return 商品sku信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     */
    @Override
    public ProductSkuInfo queryProductSkuById(SkuInfoQuery skuInfoQuery) throws BusinessException, SystemException {
        ProductSKUResponse skuResponse = prodSkuBusiSV.querySkuDetail(skuInfoQuery.getTenantId(),skuInfoQuery.getSkuId(),null);
        ProductSkuInfo skuInfo = new ProductSkuInfo();
        BeanUtils.copyProperties(skuInfo,skuResponse);
        //添加主图
        List<ProductImage> imageList = skuResponse.getProductImageList();
        if (!CollectionUtil.isEmpty(imageList)){
            ProductImage productImage = imageList.get(0);
            skuInfo.setVfsId(productImage.getVfsId());
            skuInfo.setPicType(productImage.getPicType());
        }
        return skuInfo;
    }

    /**
     * 根据销售商品关联路由组ID
     *
     * @param productInfoQuery
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     */
    @Override
    public ProductRoute queryRouteGroupOfProd(ProductInfoQuery productInfoQuery) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(productInfoQuery.getTenantId());
        return productBusiSV.queryRouteGroupOfProd(
                productInfoQuery.getTenantId(),productInfoQuery.getSupplierId(),productInfoQuery.getProductId());
    }

    /**
     * 设置商品的配货组
     *
     * @param setInfo
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @RestRelativeURL productManager/changeRouteGroup
     * @ApiCode PRODUCT_SERVER_0102
     */
    @Override
    public BaseResponse changeRouteGroup(RouteGroupSet setInfo) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(setInfo.getTenantId());
        productManagerBusiSV.changeRouteGroup(setInfo.getTenantId(),setInfo.getSupplierId(),
                setInfo.getProdId(),setInfo.getRouteGroupId(),setInfo.getOperId());
        return CommonUtils.genSuccessResponse("");
    }

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
     * @ApiCode PRODUCT_SERVER_0102
     */
    @Override
    public PageInfoResponse<ProductRouteGroupInfo> queryProductAndRouteGroup(RouteGroupQuery query) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(query.getTenantId());
        PageInfoResponse<ProductRouteGroupInfo>  response = productManagerBusiSV.queryProdAndRouteGroup(query);
        CommonUtils.addSuccessResHeader(response,"");
        return response;
    }

    /**
     * 根据销售商品sku标识查询商品单品详情信息(订单专用)
     * @param skuInfoQuery 查询对象
     * @return 商品sku信息
     * @throws BusinessException
     * @throws SystemException
     * @author Gavin
     * @ApiDocMethod
     * @RestRelativeURL productServer/searchProdInfo4ShopCart
     * @ApiCode PRODUCT_SERVER_0103
     */
	@Override
	public ProductSkuInfo queryProductSkuById4ShopCart(SkuInfoQuery skuInfoQuery)
			throws BusinessException, SystemException {
			ProductSKUResponse skuResponse = prodSkuBusiSV.querySkuDetail4ShopCart(skuInfoQuery.getTenantId(),skuInfoQuery.getSkuId(),null);
	        ProductSkuInfo skuInfo = new ProductSkuInfo();
	        BeanUtils.copyProperties(skuInfo,skuResponse);
	        
	        return skuInfo;
	}
}
