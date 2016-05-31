package com.ai.slp.product.api.product.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.slp.product.api.product.interfaces.IProductServerSV;
import com.ai.slp.product.api.product.param.ProductInfoQuery;
import com.ai.slp.product.api.product.param.ProductRoute;
import com.ai.slp.product.api.product.param.ProductSkuInfo;
import com.ai.slp.product.api.product.param.SkuInfoQuery;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.util.CommonCheckUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jackieliu on 16/5/31.
 */
@Service(validation = "true")
@Component
public class IProductServerSVImpl implements IProductServerSV {
    @Autowired
    IProductBusiSV productBusiSV;
    /**
     * 根据销售商品sku标识查询商品单品详情信息<br>
     *
     * @param skuInfoQuery 查询对象
     * @return 商品sku信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode PRODUCT_SERVER_0100
     */
    @Override
    public ProductSkuInfo queryProductSkuById(SkuInfoQuery skuInfoQuery) throws BusinessException, SystemException {
        return null;
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
     * @ApiCode PRODUCT_SERVER_0101
     */
    @Override
    public ProductRoute queryRouteGroupOfProd(ProductInfoQuery productInfoQuery) throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(productInfoQuery.getTenantId(),"");
        return productBusiSV.queryRouteGroupOfProd(productInfoQuery.getTenantId(),productInfoQuery.getProductId());
    }
}
