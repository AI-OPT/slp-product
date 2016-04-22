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
	 * 商品类目保存和修改<br>
	 * 编辑类目时，操作中的按钮为“添加子分类”<br>
	 * 如是叶子类目，点击时需判断<br>
	 * 如果该类目下已关联类目属性，则需提示，请先删除类目属性才能添加下一级类目<br>
	 * 如没有类目属性，点击出现下一级类目添加框<br>
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
	 * 删除类目时，需要判断是否已经有关联的标准品（包括废弃状态的）<br>
	 * 类目一旦删除，其下包含的子类目一并删除，与选择好的属性、属性值解除关联关系<br>
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
     * 商品类目属性属性值删除
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
