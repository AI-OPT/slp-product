package com.ai.slp.product.api.product.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
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
    public PageInfoWrapper<ProductEditUp> queryProductEdit(ProductEditParam productEditParam) throws BusinessException, SystemException;
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
    public PageInfoWrapper<ProductEditUp> queryProductRefuse(ProductRefuseParam productRefuseParam) throws BusinessException, SystemException;
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
    public PageInfoWrapper<ProductEditUp> queryProductCheck(ProductCheckingParam productCheckingParam) throws BusinessException, SystemException;
    @interface QueryProductCheck {}

    /**
     * 审核调用方法<br>
     * 通过上架类型 1审核通过后立即上架 2审核通过后放入仓库 3定时上架<br>
     * 判断改变为哪种状态<br>
     * 销售商品状态 4审核未通过<br>
     * 拒绝后把拒绝类型和原因存入销售商品流程日志表<br>
     *
     * @param productCheckParam
     * @return 基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
     *  @ApiCode PROMAN_0103
     */
    public BaseResponse productCheck(ProductCheckParam productCheckParam) throws BusinessException, SystemException;
    @interface ProductCheck {}

    /**
     * 商品申请优先审核调用方法<br>
     *
     * @param productPriorityParam
     * @return 基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
     *  @ApiCode PROMAN_0104
     */
    public BaseResponse productPriority(ProductPriorityParam productPriorityParam) throws BusinessException, SystemException;
    @interface ProductPriority {}
    

    /**
     * 查询单个商品的受众信息<br>
     *
     * @param productInfoQuery 单个商品的标识信息
     * @return 单个商品的受众信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode PROMAN_0105
     */
    public AudiencesSetOfProduct queryAudiencesOfProduct(ProductInfoQuery productInfoQuery)
            throws BusinessException,SystemException;
    @interface QueryAudiencesOfProduct{}


}







