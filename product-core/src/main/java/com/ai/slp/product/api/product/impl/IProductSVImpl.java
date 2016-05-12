package com.ai.slp.product.api.product.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.product.interfaces.IProductSV;
import com.ai.slp.product.api.product.param.*;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.util.CommonCheckUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jackieliu on 16/4/27.
 */
@Service(validation = "true")
@Component
public class IProductSVImpl implements IProductSV {
    @Autowired
    IProductBusiSV productBusiSV;
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
    @Override
    public PageInfoResponse<Product4List> queryProductList(ProductListQuery productQuery) throws BusinessException, SystemException {
        return null;
    }

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
    @Override
    public ProductInfo queryProductById(ProductInfoQuery productInfoQuery) throws BusinessException, SystemException {
        return null;
    }

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
    @Override
    public BaseResponse updateMultSKUSalePrice(List<ProdSkuSalPrice> skuSalPrices) throws BusinessException, SystemException {
        return null;
    }

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
    @Override
    public BaseResponse saveMultSKUInfo(SkuInfoMultSave saveInfo) throws BusinessException, SystemException {
        return null;
    }

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
    @Override
    public SkuSetForProduct querySkuSetForProduct(SkuSetForProductQuery query) throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(query.getTenantId(),"");
        return productBusiSV.querySkuByProdId(query.getTenantId(),query.getProdId());
    }
}
