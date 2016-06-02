package com.ai.slp.product.api.webfront.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.slp.product.api.webfront.param.*;

import java.util.List;

public interface IProductHomeSV {
    /**
     * 获取首页流量、话费数据
     * @param productHomeRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhanglh
     * @ApiCode PROD_HOME_0100
     */
    public List<ProductHomeResponse> queryHomeDataProduct(ProductHomeRequest request)throws BusinessException, SystemException;
    /**
     * 获取推荐产品
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhanglh
     * @ApiCode PROD_HOME_0101
     */
    public List<ProductHomeResponse> queryHotProduct(ProductHomeRequest request)throws BusinessException, SystemException;


    /**
     * 获取快充产品
     * @param request
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode PROD_HOME_0102
     */
    public ListWrapperForRes<FastProductInfoRes> queryFastProduct(FastProductReq request)throws BusinessException, SystemException;
    @interface QueryFastProduct{}

}
