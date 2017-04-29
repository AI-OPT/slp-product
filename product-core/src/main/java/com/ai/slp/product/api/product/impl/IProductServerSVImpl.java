package com.ai.slp.product.api.product.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.paas.ipaas.search.vo.Result;
import com.ai.paas.ipaas.search.vo.SearchCriteria;
import com.ai.paas.ipaas.search.vo.SearchOption;
import com.ai.slp.product.api.product.interfaces.IProductServerSV;
import com.ai.slp.product.api.product.param.ProductInfoQuery;
import com.ai.slp.product.api.product.param.ProductRoute;
import com.ai.slp.product.api.product.param.ProductRouteGroupInfo;
import com.ai.slp.product.api.product.param.ProductSkuInfo;
import com.ai.slp.product.api.product.param.RouteGroupQuery;
import com.ai.slp.product.api.product.param.RouteGroupSet;
import com.ai.slp.product.api.product.param.SkuInfoQuery;
import com.ai.slp.product.api.webfront.param.ProductImage;
import com.ai.slp.product.api.webfront.param.ProductSKUResponse;
import com.ai.slp.product.constants.SearchFieldConfConstants;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.service.business.impl.search.ProductSearchImpl;
import com.ai.slp.product.service.business.interfaces.IProdSkuBusiSV;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IProductManagerBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageNumBusiSV;
import com.ai.slp.product.service.business.interfaces.search.IProductSearch;
import com.ai.slp.product.util.CommonUtils;
import com.ai.slp.product.vo.SkuStorageVo;
import com.alibaba.dubbo.config.annotation.Service;

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
    
    @Autowired
	IStorageNumBusiSV storageNumBusiSV;
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
        //
        ProductSkuInfo skuInfo = new ProductSkuInfo();
        BeanUtils.copyProperties(skuInfo,skuResponse);
        if(skuInfo.getUsableNum()==null){
        	skuInfo.setUsableNum(0L);
        }
        if(skuInfo.getSalePrice()==null){
        	skuInfo.setSalePrice(0L);
        }
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
			//ProductSKUResponse skuResponse = prodSkuBusiSV.querySkuDetail4ShopCart(skuInfoQuery.getTenantId(),skuInfoQuery.getSkuId(),null);
			//查询es
			IProductSearch productSearch = new ProductSearchImpl();
	    	List<SearchCriteria> searchCriterias = new ArrayList<SearchCriteria>();
	    	searchCriterias.add(new SearchCriteria(SearchFieldConfConstants.TENANT_ID,
	    			skuInfoQuery.getTenantId(),
	    			new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
	    	searchCriterias.add(new SearchCriteria("productid",
	    			skuInfoQuery.getSkuId(),
	    			new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
	    	
	    	Result<SKUInfo> result = productSearch.searchByCriteria(searchCriterias, 0, 1, null);
	    	if (CollectionUtil.isEmpty(result.getContents())) {
	    		throw new BusinessException("在es中查询商品失败");
			}
	    	SKUInfo product = result.getContents().get(0);
	    	
	    	
	    	Product prod = new Product();
	    	prod.setTenantId(product.getTenantid());
	    	prod.setStorageGroupId(product.getStoragegroupid());
			// 获取当前库存和价格
//			SkuStorageVo skuStorageVo = storageNumBusiSV.queryStorageOfSku(skuInfoQuery.getTenantId(), skuInfoQuery.getSkuId());
			SkuStorageVo skuStorageVo = storageNumBusiSV.queryStorageOfSku4ShopCart(skuInfoQuery.getSkuId(),prod);
			//数据组装	
	        ProductSkuInfo skuInfo = new ProductSkuInfo();
//	        BeanUtils.copyProperties(skuInfo,skuResponse);
	        skuInfo.setSupplierId(product.getSupplierid());
	        skuInfo.setState(product.getState());
	        skuInfo.setProdName(product.getProductcatname());
	        skuInfo.setProdId(product.getProductid());
	        skuInfo.setSkuName(product.getProductname());
	        
	        skuInfo.setVfsId(product.getImageinfo().getVfsid());
	        skuInfo.setPicType(product.getImageinfo().getImagetype());
	        
	        skuInfo.setSalePrice(skuStorageVo.getSalePrice());
	        skuInfo.setUsableNum(skuStorageVo.getUsableNum());
	        
	        if(skuInfo.getUsableNum()==null){
	        	skuInfo.setUsableNum(0L);
	        }
	        if(skuInfo.getSalePrice()==null){
	        	skuInfo.setSalePrice(0L);
	        }
	        //添加主图
	      /*  List<ProductImage> imageList = skuResponse.getProductImageList();
	        if (!CollectionUtil.isEmpty(imageList)){
	            ProductImage productImage = imageList.get(0);
	            skuInfo.setVfsId(productImage.getVfsId());
	            skuInfo.setPicType(productImage.getPicType());
	        }*/
	        return skuInfo;
	}
}
