package com.ai.slp.product.api.productcat.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseListResponse;
import com.ai.slp.product.api.productcat.interfaces.IProductCatCacheSV;
import com.ai.slp.product.api.productcat.param.ProductCatInfo;
import com.ai.slp.product.api.productcat.param.ProductCatUniqueReq;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * Created by jackieliu on 16/7/21.
 */
@Service
@Component
public class IProductCatCacheSVImpl implements IProductCatCacheSV {
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
    @Override
    public ProductCatInfo queryByCatId(ProductCatUniqueReq catUniqueReq) throws BusinessException, SystemException {
        return null;
    }

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
    @Override
    public BaseListResponse<ProductCatInfo> queryLinkOfCatById(ProductCatUniqueReq catUniqueReq) throws BusinessException, SystemException {
        return null;
    }

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
    @Override
    public BaseListResponse<ProductCatInfo> queryChildOfCatById(ProductCatUniqueReq catUniqueReq) throws BusinessException, SystemException {
        return null;
    }
}
