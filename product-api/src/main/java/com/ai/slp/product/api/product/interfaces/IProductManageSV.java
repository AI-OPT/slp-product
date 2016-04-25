package com.ai.slp.product.api.product.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.product.param.Product;
import com.ai.slp.product.api.product.param.ProductCheckingParam;
import com.ai.slp.product.api.product.param.ProductEditParam;
import com.ai.slp.product.api.product.param.ProductRefuseParam;

/**
 * 商品管理接口
 * 
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public interface IProductManageSV {
    
    /**
     * 商品管理查询商品编辑状态
     * 
     * @param productEditParam
     * @return 满足条件的商品集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROMAN_0100
     */
    public PageInfo<Product> queryProductEdit(ProductEditParam productEditParam) throws BusinessException, SystemException;
    @interface QueryProductEdit {}
    
    /**
     * 商品管理查询商品被拒绝信息
     * 
     * @param productRefuseParam
     * @return 满足条件的商品集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROMAN_0101
     */
    public PageInfo<Product> queryProductRefuse(ProductRefuseParam productRefuseParam) throws BusinessException, SystemException;
    @interface QueryProductRefuse {}
    
    /**
     * 商品管理查询商品审核状态
     * 
     * @param productCheckingParam
     * @return 满足条件的商品集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROMAN_0102
     */
    public PageInfo<Product> queryProductCheck(ProductCheckingParam productCheckingParam) throws BusinessException, SystemException;
    @interface QueryProductCheck {}
}
