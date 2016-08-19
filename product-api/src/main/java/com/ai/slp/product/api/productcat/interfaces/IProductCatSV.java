package com.ai.slp.product.api.productcat.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseListResponse;
import com.ai.opt.base.vo.BaseMapResponse;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.productcat.param.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Set;

/**
 * 商品类目管理接口<br>
 * 
 * Date: 2016年4月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
@Path("/productCat")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IProductCatSV {

    /**
     * 商品类目分页查询<br>
     * 
     * @param pageQuery
     * @return 商品类目查询条件
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode PRODUCT_CAT_0100
    *  @RestRelativeURL productCat/pageCat
     */
	@POST
	@Path("/pageCat")
	public PageInfoResponse<ProductCatInfo> queryPageProductCat(ProductCatPageQuery pageQuery)
			throws BusinessException, SystemException;
	@interface QueryPageProductCat {}

	/**
	 * 商品类目批量添加<br>
	 * 
	 * @param pcpList
	 * @return 服务返回基本信息
	 * @throws BusinessException
	 * @throws SystemException
	 * @author lipeng16
	*  @ApiCode PRODUCT_CAT_0101
	*  @RestRelativeURL productCat/addCat
	 */
	@POST
	@Path("/addCat")
	public BaseResponse createProductCat(List<ProductCatParam> pcpList) throws BusinessException, SystemException;
	@interface CreateProductCat {}
	
	/**
	 * 商品类目修改<br>
	 * 
	 * @param productCatParam
	 * @return 服务返回基本信息
	 * @throws BusinessException
	 * @throws SystemException
	 * @author lipeng16
	*  @ApiCode PRODUCT_CAT_0102
	*  @RestRelativeURL productCat/saveCat
	 */
	@POST
	@Path("/saveCat")
	public BaseResponse updateProductCat(ProductCatParam productCatParam) throws BusinessException, SystemException;
	@interface UpdateProductCat {}

	/**
	 * 商品类目删除<br>
	 * 删除类目时，需要判断是否已经有关联的标准品（包括废弃状态的）<br>
	 * 类目一旦删除，其下包含的子类目一并删除，与选择好的属性、属性值解除关联关系<br>
	 * 
	 * @param catUniqueReq
	 * @return 服务返回基本信息
	 * @throws BusinessException
	 * @throws SystemException
	 * @author lipeng16
	*  @ApiCode PRODUCT_CAT_0104
	*  @RestRelativeURL productCat/removeCat
	 */
	@POST
	@Path("/removeCat")
	public BaseResponse deleteProductCat(ProductCatUniqueReq catUniqueReq) throws BusinessException, SystemException;
	@interface DeleteProductCat {}

	/**
	 * 查询指定类目下某种类型的属性标识和属性值标识的集合<br>
	 * 类型分为:关键属性,销售属性,非关键属性
	 *
	 * @param attrQuery
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiCode PRODUCT_CAT_0105
	 * @RestRelativeURL productCat/catAttrAndVal
	 */
	@POST
	@Path("/catAttrAndVal")
	public BaseMapResponse<Long,Set<String>> queryAttrAndValIdByCatAndType(AttrQueryForCat attrQuery)
			throws BusinessException,SystemException;
	@interface QueryAttrAndValIdByCatAndType{}
    
    /**
     * 删除商品类目属性或属性值
     * 
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
    *  @ApiCode PRODUCT_CAT_0107
    *  @RestRelativeURL productCat/removeCatAttrOrVal
     */
	@POST
	@Path("/removeCatAttrOrVal")
    public BaseResponse deleteProductCatAttrOrVal(ProdCatAttrVal catAttrVal) throws BusinessException, SystemException;
    @interface DeleteProductCatAttrOrVal {}

	/**
	 * 依据商品类目和属性类型添加类目属性<br>
	 * 类型分为:关键属性,销售属性,非关键属性
	 * 
	 * @param addCatAttrParam
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author lipeng16
	 * @ApiCode PRODUCT_CAT_0108
	 * @RestRelativeURL productCat/addCatAttr
	 */
	@POST
	@Path("/addCatAttr")
	public BaseResponse addAttrForCatAndType(ProdCatAttrAddParam addCatAttrParam) throws BusinessException,SystemException;
	@interface AddAttrForCatAndType {}

	/**
	 * 查询指定标识的类目信息
	 *
	 * @param catUniqueReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiCode PRODUCT_CAT_0109
	 * @RestRelativeURL productCat/cat
	 */
	@POST
	@Path("/cat")
	public ProductCatInfo queryByCatId(ProductCatUniqueReq catUniqueReq)
			throws BusinessException,SystemException;
	@interface QueryByCatId{}

	/**
	 * 查询类目的类目链
	 *
	 * @param catUniqueReq
	 * @return 从当前类目一直到根类目的类目链
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiCode PRODUCT_CAT_0110
	 * @RestRelativeURL productCat/linkCat
	 * @deprecated 请使用IProductCatCacheSV中的queryLinkOfCatById代替
	 */
	@Deprecated
	@POST
	@Path("/linkCat")
	public List<ProductCatInfo> queryLinkOfCatById(ProductCatUniqueReq catUniqueReq)
			throws BusinessException,SystemException;
	@interface QueryLinkOfCatById{}

	/**
	 * 查询指定类目下某种类型的属性集合<br>
	 * 类型分为:关键属性,销售属性,非关键属性
	 *
	 * @param attrQuery 查询类目信息
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiCode PRODUCT_CAT_0111
	 * @RestRelativeURL productCat/catByType
	 */
	@POST
	@Path("/catAttrByType")
	public BaseListResponse<ProdCatAttrDef> queryAttrByCatAndType(AttrQueryForCat attrQuery)
			throws BusinessException,SystemException;
	@interface QueryAttrByCatAndType{}

	/**
	 * 根据名称或首字母查询
	 *
	 * @param catQuery 类目查询信息
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiCode PRODUCT_CAT_0112
	 * @RestRelativeURL productCat/catByName
     */
	@POST
	@Path("/catByName")
	public List<ProdCatInfo> queryCatByNameOrFirst(ProductCatQuery catQuery)
			throws BusinessException,SystemException;
	@interface QueryCatByNameOrFirst{}

	/**
	 * 更新类目属性信息
	 *
	 * @param updateReq 类目属性和属性值信息
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 * @ApiCode PRODUCT_CAT_0113
	 * @RestRelativeURL productCat/saveCatAttr
	 */
	@POST
	@Path("/saveCatAttr")
	public BaseResponse updateCatAttrAndVal(ProdCatAttrUpdateReq updateReq)
			throws BusinessException,SystemException;
	@interface UpdateCatAttrAndVal{}

}
