package com.ai.slp.product.api.normproduct.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.normproduct.param.ProductCatRequest;
import com.ai.slp.product.api.normproduct.param.ProductCatResponse;

/**
 * 商品类目接口<br>
 * 
 * Date: 2016年4月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author Onle
 */
public interface IProductCatSvc {
	
	/**
	 * 商品类目查询<br>
	 * 
	 * @param productCatRequest
	 * @return 商品类目集合
	 * @throws BusinessException
	 * @throws SystemException
	 * @author Onle
	 * @ApiDocMethod
	 * @ApiCode PRODUCT_0200
	 */
	public PageInfo<ProductCatResponse> queryPrdouctCat(ProductCatRequest productCatRequest)
			throws BusinessException, SystemException;
	@interface QueryPrdouctCat {}

	/**
	 * 商品类目保存和修改<br>
	 * 
	 * @param productCatRequest
	 * @return 服务返回基本信息
	 * @throws BusinessException
	 * @throws SystemException
	 * @author Onle
	 * @ApiDocMethod
	 * @ApiCode PRODUCT_0201
	 */
	public BaseResponse saveOrUpdate(ProductCatRequest productCatRequest)
			throws BusinessException, SystemException;
	@interface SaveOrUpdate {}
	
	/**
	 * 商品类目删除<br>
	 * 
	 * @param productCatRequest
	 * @return 服务返回基本信息
	 * @throws BusinessException
	 * @throws SystemException
	 * @author Onle
	 * @ApiDocMethod
	 * @ApiCode PRODUCT_0202
	 */
	public BaseResponse delete(ProductCatRequest productCatRequest)
			throws BusinessException, SystemException;
	@interface Delete {}

}
