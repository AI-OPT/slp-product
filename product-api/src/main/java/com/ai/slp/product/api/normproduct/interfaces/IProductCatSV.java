package com.ai.slp.product.api.normproduct.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.slp.product.api.normproduct.param.*;

/**
 * 商品类目管理接口<br>
 * 
 * Date: 2016年4月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public interface IProductCatSV {

    /**
     * 商品类目查询<br>
     * 第一次打开页面时只显示一级类目列表<br>
     * 点扩展符号"+"显示对应父类目下的下级类目列表<br>
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
	 * 商品类目保存<br>
	 * 
	 * @param pcrList
	 * @return 服务返回基本信息
	 * @throws BusinessException
	 * @throws SystemException
	 * @author lipeng
	*  @ApiCode PROCAT_0101
	 */
	public BaseResponse addProductCat(List<ProductCatParam> pcpList) throws BusinessException, SystemException;
	@interface AddProductCat {}
	
	/**
	 * 商品类目修改<br>
	 * 
	 * @param productCatParam
	 * @return 服务返回基本信息
	 * @throws BusinessException
	 * @throws SystemException
	 * @author lipeng
	*  @ApiCode PROCAT_0102
	 */
	public BaseResponse updateProductCat(ProductCatParam productCatParam) throws BusinessException, SystemException;
	@interface UpdateProductCat {}

	/**
	 * 商品类目删除<br>
	 * 删除类目时，需要判断是否已经有关联的标准品（包括废弃状态的）<br>
	 * 类目一旦删除，其下包含的子类目一并删除，与选择好的属性、属性值解除关联关系<br>
	 * 
	 * @param productCatParam
	 * @return 服务返回基本信息
	 * @throws BusinessException
	 * @throws SystemException
	 * @author lipeng
	*  @ApiCode PROCAT_0103
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
     * @ApiCode PROCAT_0104
     */
    public List<AttrDefInfo> queryProductCatAttr(ProductCatParam productCatParam) throws BusinessException, SystemException;
    @interface QueryProductCatAttr {}
    
    
    /**
     * 商品类目属性的属性值查询
     * 
     * @return 类目属性值集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROCAT_0105
     */
    public List<AttrValInfo> queryProductCatAttrVal() throws BusinessException, SystemException;
    @interface QueryProductCatAttrVal {}
    
    /**
     * 商品类目属性添加<br>
     * 
     * @param lspad
     * @return 服务返回基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROCAT_0106
     */
    public BaseResponse addProductCatAttr(List<AttrDefResponse> lspad) throws BusinessException, SystemException;
    @interface AddProductCatAttr {}
    
    /**
     * 商品类目属性属性值删除
     * 
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng
    *  @ApiCode PROCAT_0107
     */
    public BaseResponse deleteProductCatAttrVal(AttrValParam productAttrValParam) throws BusinessException, SystemException;
    @interface DeleteProductCatAttrVal {}

	/**
	 * 查询指定类目下某种类型的属性集合<br>
	 * 类型分为:关键属性,销售属性,非关键属性
	 *
	 * @param attrQuery 查询类目信息
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiCode PROCAT_0108
	 */
	public Map<AttrDefInfo,List<AttrValInfo>> queryAttrByCatAndType(AttrQueryForCat attrQuery)
		throws BusinessException,SystemException;
	@interface QueryAttrByCatAndType{}

	/**
	 * 查询指定标准品下某种类型的属性集合<br>
	 * 类型分为:关键属性,销售属性
	 *
	 * @param attrQuery 查询标准品信息
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiCode PROCAT_0109
	 */
	public Map<AttrDefInfo,List<AttrValInfo>> queryAttrByNormProduct(AttrQueryForNormProduct attrQuery)
			throws BusinessException,SystemException;
	@interface QueryAttrByNormProduct{}
}
