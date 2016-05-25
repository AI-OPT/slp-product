package com.ai.slp.product.api.webfront.interfaces;

import java.util.List;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.slp.product.api.webfront.param.ProductHome;

public interface IProductHomeSV {
    /**
     * 获取流量
     * @param productHomeRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhanglh
     * @ApiCode
     */
    public List<ProductHome> queryFlowDataProduct()throws BusinessException, SystemException;
    /**
     * 获取话费
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhanglh
     * @ApiCode
     */
    public List<ProductHome> queryPhoneBillProduct()throws BusinessException, SystemException;
    /**
     * 获取推荐产品
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhanglh
     * @ApiCode
     */
    public List<ProductHome> queryHotProduct()throws BusinessException, SystemException;
    
}
