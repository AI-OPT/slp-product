package com.ai.slp.product.service.atom.impl.product;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.api.product.param.ProductEditQueryReq;
import com.ai.slp.product.api.product.param.ProductQueryInfo;
import com.ai.slp.product.api.product.param.ProductRouteGroupInfo;
import com.ai.slp.product.api.product.param.ProductStorageSaleParam;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.attach.ProductAttachMapper;
import com.ai.slp.product.dao.mapper.bo.product.ProdAttr;
import com.ai.slp.product.dao.mapper.bo.product.ProdAttrCriteria;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.product.ProductCriteria;
import com.ai.slp.product.dao.mapper.interfaces.product.ProdAttrMapper;
import com.ai.slp.product.dao.mapper.interfaces.product.ProductMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.vo.ProdRouteGroupQueryVo;

/**
 * Created by jackieliu on 16/5/5.
 */
@Component
public class ProductAtomSVImpl implements IProductAtomSV {
	@Autowired
	ProductMapper productMapper;
	@Autowired
	ProdAttrMapper prodAttrMapper;
	@Autowired
	ProductAttachMapper productAttachMapper;
	/**
	 * 添加销售商品信息
	 *
	 * @param product
	 * @return
	 */
	@Override
	public int installProduct(Product product) {
//		product.setProdId(SequenceUtil.createProductProdId());
		if (product.getCreateTime() == null){
			product.setCreateTime(DateUtils.currTimeStamp());
		}
		product.setOperTime(product.getCreateTime());
		return productMapper.insert(product);
	}

	/**
	 * 查询指定库存组下的销售商品
	 *
	 * @param tenantId
	 * @param groupId
	 * @return
	 */
	@Override
	public Product selectByGroupId(String tenantId, String groupId) {
		ProductCriteria example = new ProductCriteria();
		example.setLimitStart(0);
		example.setLimitEnd(1);
		//example.setOrderByClause("CREATE_TIME desc");
		example.createCriteria().andTenantIdEqualTo(tenantId).andStorageGroupIdEqualTo(groupId);
		List<Product> products = productMapper.selectByExample(example);
		if (products == null || products.isEmpty()) {
			return null;
		}
		//排序
		//Collections.sort(products, new productsComparator());
		
		return  products.get(0);
	}

	/**
	 * 查询指定商品
	 *
	 * @param prodId
	 * @return
	 */
	@Override
	public Product selectByProductId(String prodId) {
		Product product = productMapper.selectByPrimaryKey(prodId);
		return product;
	}
	
	/**
	 * 查询指定商品
	 *
	 * @param tenantId
	 * @param prodId
	 * @return
	 */
	@Override
	public Product selectByProductId(String tenantId, String prodId) {
		Product product = productMapper.selectByPrimaryKey(prodId);
		return product;
	}

	
	
	/**
	 * 查询指定商品
	 *
	 * @param tenantId
	 * @param supplierId
	 * @param prodId
	 * @return
	 */
	@Override
	public Product selectByProductId(String tenantId, String supplierId, String prodId) {
		Product product = productMapper.selectByPrimaryKey(prodId);
		if (product==null || product.getTenantId()==null || product.getSupplierId()==null || !product.getTenantId().equals(tenantId)|| !product.getSupplierId().equals(supplierId)){
			product = null;
		}
		return product;
	}

	/**
	 * 根据标识更新商品信息
	 *
	 * @param product
	 * @return
	 */
	@Override
	public int updateById(Product product) {
		product.setOperTime(DateUtils.currTimeStamp());
		Product record = new Product();
		record.setOperTime(DateUtils.currTimeStamp());
		record.setState(product.getState());
		record.setProdId(product.getProdId());
		return productMapper.updateByPrimaryKeySelective(record);
	}
	

	@Override
	public int updateByStandedProdId(Product product) {
		product.setOperTime(DateUtils.currTimeStamp());
		ProductCriteria example = new ProductCriteria();
		example.createCriteria().andStandedProdIdEqualTo(product.getStandedProdId());
		return productMapper.updateByExampleSelective(product, example);
	}
	@Override
	public int updateProdInfo(String prodId,String prodName,String productType) {
		return productAttachMapper.updateProdInfo(prodId, prodName, productType);
	}

	/**
	 * 待编辑商品分页查询
	 *
	 * @param queryReq
	 * @return
	 * @author lipeng16
	 */
	@Override
	public PageInfo<Product> selectPageForEdit(ProductEditQueryReq queryReq) {
		ProductCriteria example = new ProductCriteria();
		example.setOrderByClause("OPER_TIME desc");//操作时间倒序
//		example.setOrderByClause("CREATE_TIME desc");//创建时间倒序
		ProductCriteria.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(queryReq.getProductCatId())){
			criteria.andProductCatIdEqualTo(queryReq.getProductCatId());
		}
		if (StringUtils.isNotBlank(queryReq.getProductType())){
			criteria.andProductTypeEqualTo(queryReq.getProductType());
		}
		if (!CollectionUtil.isEmpty(queryReq.getStateList())){
			criteria.andStateIn(queryReq.getStateList());
		}
		if (StringUtils.isNotBlank(queryReq.getProdId())){
			criteria.andProdIdEqualTo(queryReq.getProdId());
		}
		if (StringUtils.isNotBlank(queryReq.getProdName())){
			criteria.andProdNameLike("%"+queryReq.getProdName()+"%");
		}
		//对商户标识的查询
		if (StringUtils.isNotBlank(queryReq.getSupplierId())) {
			criteria.andSupplierIdEqualTo(queryReq.getSupplierId());
		}
		//根据标准品ID模糊查询
		if (StringUtils.isNotBlank(queryReq.getStandedProdId())) {
			//精确查询
			criteria.andStandedProdIdEqualTo(queryReq.getStandedProdId());
			//criteria.andStandedProdIdLike("%"+queryReq.getStandedProdId()+"%");
		}
		//获取页数和每页条数
		int pageNo = queryReq.getPageNo();
		int pageSize = queryReq.getPageSize();

		return pageQuery(example, pageNo, pageSize);
	}
	/**
	 * 商品审核分页查询
	 *
	 * @param queryReq
	 * @return
	 * @author jiawen
	 */
	@Override
	public PageInfo<Product> selectPageForAudit(ProductQueryInfo queryReq) {
		ProductCriteria example = new ProductCriteria();
		
		//example.setOrderByClause("OPER_TIME desc");//操作时间倒序
		
		ProductCriteria.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(queryReq.getProductCatId())){
			criteria.andProductCatIdEqualTo(queryReq.getProductCatId());
		}
		if (StringUtils.isNotBlank(queryReq.getProductType())){
			criteria.andProductTypeEqualTo(queryReq.getProductType());
		}
		if (!CollectionUtil.isEmpty(queryReq.getStateList())){
			criteria.andStateIn(queryReq.getStateList());
		}
		if (StringUtils.isNotBlank(queryReq.getProdId())){
			criteria.andProdIdEqualTo(queryReq.getProdId());
		}
		if (StringUtils.isNotBlank(queryReq.getProdName())){
			criteria.andProdNameLike("%"+queryReq.getProdName()+"%");
		}
		//对商户标识的查询
		if (StringUtils.isNotBlank(queryReq.getSupplierId())) {
			criteria.andSupplierIdEqualTo(queryReq.getSupplierId());
		}
		//根据标准品ID模糊查询
		if (StringUtils.isNotBlank(queryReq.getStandedProdId())) {
			criteria.andStandedProdIdEqualTo(queryReq.getStandedProdId());
		}
		// 操作时间 开始时间
		if (queryReq.getOperStartTime() != null){
			criteria.andOperTimeGreaterThanOrEqualTo(DateUtils.toTimeStamp(queryReq.getOperStartTime()));
		}
		// 操作时间 截止时间
		if (queryReq.getOperEndTime() != null){
			criteria.andOperTimeLessThanOrEqualTo(DateUtils.toTimeStamp(queryReq.getOperEndTime()));
		}
		//获取页数和每页条数
		int pageNo = queryReq.getPageNo();
		int pageSize = queryReq.getPageSize();
		
		return pageQuery(example, pageNo, pageSize);
	}

	/**
	 * 分页查询商品信息,包括路由组标识
	 *
	 * @param queryVo
	 * @return
	 */
	@Override
	public PageInfo<ProductRouteGroupInfo> selectPageForRouteGroup(ProdRouteGroupQueryVo queryVo) {
		PageInfo<ProductRouteGroupInfo> pageInfo = new PageInfo<>();
		// 设置总数
		int count = productAttachMapper.countProductAndRouteGroup(queryVo);
		int pageNo = queryVo.getPageNo(),
				pageSize = queryVo.getPageSize();
		pageInfo.setCount(count);
		//设置分页查询条件
		queryVo.setLimitStart((pageNo-1)*pageSize);
		queryVo.setLimitEnd(pageSize);
		
		//queryVo.setOrderByClause("p.CREATE_TIME desc");
		
		//设置页数和每页条数
		pageInfo.setPageNo(pageNo);
		pageInfo.setPageSize(pageSize);
		//设置结果集
		pageInfo.setResult(productAttachMapper.queryProductAndRouteGroupPage(queryVo));
		//设置总页数
		int pageCount = count/pageSize+(count%pageSize>0 ? 1 : 0);
		pageInfo.setPageCount(pageCount);
		return pageInfo;
	}

	private PageInfo<Product> pageQuery(ProductCriteria example, int pageNo, int pageSize) {
		PageInfo<Product> pageInfo = new PageInfo<>();
		// 设置总数
		int count = productMapper.countByExample(example);
		pageInfo.setCount(count);
		//设置分页查询条件
		example.setLimitStart((pageNo-1)*pageSize);
		example.setLimitEnd(pageSize);
		//设置页数和每页条数
		pageInfo.setPageNo(pageNo);
		pageInfo.setPageSize(pageSize);
		//设置结果集
		pageInfo.setResult(productMapper.selectByExample(example));
		//设置总页数
		int pageCount = count/pageSize+(count%pageSize>0 ? 1 : 0);
		pageInfo.setPageCount(pageCount);
		return pageInfo;
	}

	@Override
	public Product queryProductByGroupId(String tenantId, String groupId) {
		ProductCriteria example = new ProductCriteria();
		example.createCriteria().andTenantIdEqualTo(tenantId).andStorageGroupIdEqualTo(groupId);
		return productMapper.selectByExample(example).get(0);
	}

	@Override
	public PageInfo<Product> selectStorProdByState(ProductStorageSaleParam queryReq) {
		ProductCriteria example = new ProductCriteria();
		
		//example.setOrderByClause("OPER_TIME asc");//操作时间倒序
		
		ProductCriteria.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(queryReq.getProductCatId())){
			criteria.andProductCatIdEqualTo(queryReq.getProductCatId());
		}
		if (StringUtils.isNotBlank(queryReq.getProductType())){
			criteria.andProductTypeEqualTo(queryReq.getProductType());
		}
		if (!CollectionUtil.isEmpty(queryReq.getStateList())){
			criteria.andStateIn(queryReq.getStateList());
		}
		if (StringUtils.isNotBlank(queryReq.getProdId())){
			criteria.andProdIdEqualTo(queryReq.getProdId());
		}
		if (StringUtils.isNotBlank(queryReq.getProdName())){
			criteria.andProdNameLike("%"+queryReq.getProdName()+"%");
		}
		//获取页数和每页条数
		int pageNo = queryReq.getPageNo();
		int pageSize = queryReq.getPageSize();

		return pageQuery(example, pageNo, pageSize);
	}

	/**
	 * 查询在售商品 -- 按上架时间排序
	 * @param queryReq
	 * @return
	 * @author jiawen
	 */
	@Override
	public PageInfo<Product> selectPageForInsale(ProductQueryInfo queryReq) {

		ProductCriteria example = new ProductCriteria();
		
		
		//example.setOrderByClause("UP_TIME desc");//上架时间倒序
		
		ProductCriteria.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(queryReq.getProductCatId())){
			criteria.andProductCatIdEqualTo(queryReq.getProductCatId());
		}
		if (StringUtils.isNotBlank(queryReq.getProductType())){
			criteria.andProductTypeEqualTo(queryReq.getProductType());
		}
		if (!CollectionUtil.isEmpty(queryReq.getStateList())){
			criteria.andStateIn(queryReq.getStateList());
		}
		if (StringUtils.isNotBlank(queryReq.getProdId())){
			//criteria.andProdIdLike("%"+queryReq.getProdId()+"%");
			criteria.andProdIdEqualTo(queryReq.getProdId());
		}
		if (StringUtils.isNotBlank(queryReq.getProdName())){
			criteria.andProdNameLike("%"+queryReq.getProdName()+"%");
			//criteria.andProdNameEqualTo(queryReq.getProdName());
		}
		//对商户标识的查询
		if (StringUtils.isNotBlank(queryReq.getSupplierId())){
			//criteria.andSupplierIdLike("%"+queryReq.getSupplierId()+"%");
			criteria.andSupplierIdEqualTo(queryReq.getSupplierId());
		} 
		//根据标准品ID模糊查询
		if (StringUtils.isNotBlank(queryReq.getStandedProdId())){
			//criteria.andStandedProdIdLike("%"+queryReq.getStandedProdId()+"%");
			criteria.andStandedProdIdEqualTo(queryReq.getStandedProdId());
		} 
		// 上架时间 开始时间
		if (queryReq.getUpStartTime() != null){
			criteria.andUpTimeGreaterThanOrEqualTo(DateUtils.toTimeStamp(queryReq.getUpStartTime()));
		}
			
		// 上架时间 截止时间
		if (queryReq.getUpEndTime() != null){
			criteria.andUpTimeLessThanOrEqualTo(DateUtils.toTimeStamp(queryReq.getUpEndTime()));
		}
		//获取页数和每页条数
		int pageNo = queryReq.getPageNo();
		int pageSize = queryReq.getPageSize();

		return pageQuery(example, pageNo, pageSize);
	
	}
	
	/**
	 * 查询符合条件的商品---不排序
	 *
	 * @param queryReq
	 * @return
	 * @author jiawen
	 */
	@Override
	public PageInfo<Product> selectPageForRefuse(ProductEditQueryReq queryReq) {
		ProductCriteria example = new ProductCriteria();
		
		//example.setOrderByClause("OPER_TIME asc");//操作时间倒序
		
		ProductCriteria.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(queryReq.getProductCatId())){
			criteria.andProductCatIdEqualTo(queryReq.getProductCatId());
		}
		if (StringUtils.isNotBlank(queryReq.getProductType())){
			criteria.andProductTypeEqualTo(queryReq.getProductType());
		}
		if (!CollectionUtil.isEmpty(queryReq.getStateList())){
			criteria.andStateIn(queryReq.getStateList());
		}
		if (StringUtils.isNotBlank(queryReq.getProdId())){
			criteria.andProdIdEqualTo(queryReq.getProdId());
		}
		if (StringUtils.isNotBlank(queryReq.getProdName())){
			criteria.andProdNameLike("%"+queryReq.getProdName()+"%");
		}
		//对商户标识的查询
		if (StringUtils.isNotBlank(queryReq.getSupplierId())){
			criteria.andSupplierIdEqualTo(queryReq.getSupplierId());
		} 
		//根据标准品ID模糊查询
		if (StringUtils.isNotBlank(queryReq.getStandedProdId())){
			criteria.andStandedProdIdEqualTo(queryReq.getStandedProdId());
		} 
		//获取页数和每页条数
		int pageNo = queryReq.getPageNo();
		int pageSize = queryReq.getPageSize();
		
		return pageQuery(example, pageNo, pageSize);
	}

	/**
	 * 查询除指定商品ID以外的商品是否已包含指定商品编码
	 *
	 * @param tenantId
	 * @param prodId
	 * @param prodCode
	 * @return
	 */
	@Override
	public int countOfProdCodeOutProdId(String tenantId, String prodId, String prodCode,boolean hasDiscard) {
		/*if (StringUtils.isBlank(tenantId)
				||StringUtils.isBlank(prodId)
				||StringUtils.isBlank(prodCode))
			return 0;
		ProductCriteria example = new ProductCriteria();
		ProductCriteria.Criteria criteria = example.createCriteria();
		criteria.andTenantIdEqualTo(tenantId)
				.andProdCodeEqualTo(prodCode)
				.andProdIdNotEqualTo(prodId);
		if (!hasDiscard)
			criteria.andStateNotEqualTo(ProductConstants.Product.State.DISCARD);
		return productMapper.countByExample(example);*/
		return 1;
	}

	@Override
	public List<ProdAttr> queryAttrVal(String tenantId, String prodId, Long attrId) {
		ProdAttrCriteria example = new ProdAttrCriteria();
        example.setDistinct(true);
        example.createCriteria().andTenantIdEqualTo(tenantId)
                .andProdIdEqualTo(prodId)
                .andAttrIdEqualTo(attrId)
                .andStateEqualTo(ProductConstants.ProdSkuAttr.State.ACTIVE);
        return prodAttrMapper.selectByExample(example);
	}

	@Override
	public int updateProdState(String state, String prodId) {
		return productAttachMapper.updateProdState(state, prodId);
	}

	@Override
	public int updateProdStatus(String prodId, String state, String operId) {
		return productAttachMapper.updateProdStateNew(prodId, state, operId);
	}

	@Override
	public int updateProdInfo(Product record) {
		
		return productMapper.updateByPrimaryKey(record);
	}

	
	
}
