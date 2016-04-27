package com.ai.slp.product.api.product.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.product.param.*;

/**
 * 商品管理接口
 * 
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public interface IProductEditUpSV {
    
    /**
     * 商品管理查询商品编辑状态<br>
     * 状态 1未编辑2已编辑<br>
     * 按生成时间排序<br>
     * 
     * @param productEditParam
     * @return 满足条件的商品集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROMAN_0100
     */
    public PageInfo<ProductEditUp> queryProductEdit(ProductEditParam productEditParam) throws BusinessException, SystemException;
    @interface QueryProductEdit {}
    
    /**
     * 商品管理查询商品被拒绝信息-与商品审核中查询共用<br>
     * 拒绝时间为操作时间<br>
     * 
     * @param productRefuseParam
     * @return 满足条件的商品集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROMAN_0101
     */
    public PageInfo<ProductEditUp> queryProductRefuse(ProductRefuseParam productRefuseParam) throws BusinessException, SystemException;
    @interface QueryProductRefuse {}
    
    /**
     * 商品管理查询商品审核状态-与商品审核中查询共用<br>
     * 状态：3审核中<br>
     * 提交时间为操作时间<br>
     * 
     * @param productCheckingParam
     * @return 满足条件的商品集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROMAN_0102
     */
    public PageInfo<ProductEditUp> queryProductCheck(ProductCheckingParam productCheckingParam) throws BusinessException, SystemException;
    @interface QueryProductCheck {}

    /**
     * 查询单个商品的受众信息<br>
     *
     * @param productInfoQuery 单个商品的标识信息
     * @return 单个商品的受众信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode PROMAN_0103
     */
    public AudiencesSetOfProduct queryAudiencesOfProduct(ProductInfoQuery productInfoQuery)
        throws BusinessException,SystemException;
    @interface QueryAudiencesOfProduct{}

    //查询目标区域

}
