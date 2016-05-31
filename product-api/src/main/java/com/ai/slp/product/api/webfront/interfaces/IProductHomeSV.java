package com.ai.slp.product.api.webfront.interfaces;

import java.util.List;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.slp.product.api.webfront.param.ProductHomeResponse;
import com.ai.slp.product.api.webfront.param.ProductHomeRequest;

public interface IProductHomeSV {
    /**
     * 获取首页流量、话费数据
     * @param productHomeRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhanglh
     * @ApiCode
     */
    public List<ProductHomeResponse> queryHomeDataProduct(ProductHomeRequest request)throws BusinessException, SystemException;
    /**
     * 获取推荐产品
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhanglh
     * @ApiCode
     */
    public List<ProductHomeResponse> queryHotProduct(ProductHomeRequest request)throws BusinessException, SystemException;
    
}
