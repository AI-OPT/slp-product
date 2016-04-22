package com.ai.slp.product.api.normproduct.interfaces;

import java.util.List;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.slp.product.api.normproduct.param.AttrDefResponse;
import com.ai.slp.product.api.normproduct.param.ProductAttrDef;
import com.ai.slp.product.api.normproduct.param.ProductAttrVal;
import com.ai.slp.product.api.normproduct.param.ProductAttrValParam;
import com.ai.slp.product.api.normproduct.param.ProductCatInfo;
import com.ai.slp.product.api.normproduct.param.ProductCatParam;

/**
 * 商品类目接口<br>
 * 
 * Date: 2016年4月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public interface IProductCatSV {

    /**
     * 商品类目查询<br>
     * 
     * @param productCatParam
     * @return 商品类目集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROCAT_0100
     */
	public List<ProductCatInfo> queryProductCat(ProductCatParam productCatParam)
			throws BusinessException, SystemException;
	@interface QueryProductCat {}

	/**
	 * 商品类目保存和修改<br>
	 * 
	 * @param pcrList
	 * @return 服务返回基本信息
	 * @throws BusinessException
	 * @throws SystemException
	 * @author lipeng
	*  @ApiCode PROCAT_0101
	 */
	public BaseResponse addOrUpdateProductCat(List<ProductCatParam> pcpList) throws BusinessException, SystemException;
	@interface AddOrUpdateProductCat {}

	/**
	 * 商品类目删除<br>
	 * 
	 * @param productCatParam
	 * @return 服务返回基本信息
	 * @throws BusinessException
	 * @throws SystemException
	 * @author lipeng
	*  @ApiCode PROCAT_0102
	 */
	public BaseResponse deleteProductCat(ProductCatParam productCatParam) throws BusinessException, SystemException;
	@interface DeleteProductCat {}
	
	/**
     * 商品类目属性查询<br>
     * 
     * @param productCatParam
     * @return 符合条件的属性集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
     * @ApiCode PROCAT_0103
     */
    public List<ProductAttrDef> queryProductCatAttr(ProductCatParam productCatParam) throws BusinessException, SystemException;
    @interface QsueryProductCatAttr {}
    
    
    /**
     * 商品类目属性的属性值查询
     * 
     * @return 类目属性值集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROCAT_0104
     */
    public List<ProductAttrVal> queryProductCatAttrVal() throws BusinessException, SystemException;
    @interface QueryProductCatAttrVal {}
    
    /**
     * 商品类目属性添加<br>
     * 
     * @param lspad
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROCAT_0105
     */
    public BaseResponse addProductCatAttr(List<AttrDefResponse> lspad) throws BusinessException, SystemException;
    @interface AddProductCatAttr {}
    
    /**
     * 商品类目属性值删除
     * 
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROCAT_0106
     */
    public BaseResponse deleteProductCatAttrVal(ProductAttrValParam productAttrValParam) throws BusinessException, SystemException;
    @interface DeleteProductCatAttrVal {}
}
