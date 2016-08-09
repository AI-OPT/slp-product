package com.ai.slp.product.api.product.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.product.interfaces.IProductSV;
import com.ai.slp.product.api.product.param.*;
import com.ai.slp.product.service.business.interfaces.IProdSkuBusiSV;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.util.CommonUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jackieliu on 16/4/27.
 */
@Service(validation = "true")
@Component
public class IProductSVImpl implements IProductSV {
    @Autowired
    IProductBusiSV productBusiSV;
    @Autowired
    IProdSkuBusiSV prodSkuBusiSV;
    
    /**
     * 分页查询非废弃的销售商品列表<br>
     * 用于销售价设置
     * @param productQuery 查询对象
     * @return 商品信息列表
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     */
    @Override
    public PageInfoResponse<Product4List> queryProductPage(ProductListQuery productQuery)
            throws BusinessException, SystemException {
    	CommonUtils.checkTenantId(productQuery.getTenantId(),"");
        return productBusiSV.queryProductPage(productQuery);
    }

    /**
     * 根据商品标识查询商品详情<br>
     *
     * @param queryInfo 查询对象
     * @return 商品信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     */
    @Override
    public ProductInfo queryProductById(ProductInfoQuery queryInfo)
            throws BusinessException, SystemException {
        CommonUtils.checkTenantId(queryInfo.getTenantId());
        return productBusiSV.queryByProdId(
                queryInfo.getTenantId(),queryInfo.getSupplierId(),queryInfo.getProductId());
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
     */
    @Override
    public BaseResponse saveMultSKUInfo(SkuInfoMultSave saveInfo)
            throws BusinessException, SystemException {
        CommonUtils.checkTenantId(saveInfo.getTenantId());
        prodSkuBusiSV.updateSkuOfProduct(saveInfo);
        return CommonUtils.genSuccessResponse("");
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
     */
    @Override
    public SkuSetForProduct querySkuSetForProduct(ProductInfoQuery query)
            throws BusinessException, SystemException {
        CommonUtils.checkTenantId(query.getTenantId());
        return prodSkuBusiSV.querySkuByProdId(query.getTenantId(),query.getSupplierId(),query.getProductId());
    }

    /**
     * 查询单个商品的非关键属性
     *
     * @param queryInfo 商品标识信息
     * @return 非关键属性
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     */
    @Override
    public ProdAttrMap queryNoKeyAttrInfo(ProductInfoQuery queryInfo)
            throws BusinessException, SystemException {
        CommonUtils.checkTenantId(queryInfo.getTenantId());
        return productBusiSV.queryNoKeyAttrOfProduct(
                queryInfo.getTenantId(),queryInfo.getSupplierId(),queryInfo.getProductId());
    }
}
