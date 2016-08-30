package com.ai.slp.product.service.atom.impl.product;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.api.product.param.ProductEditQueryReq;
import com.ai.slp.product.api.product.param.ProductStorageSaleParam;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.product.ProductCriteria;
import com.ai.slp.product.dao.mapper.interfaces.product.ProdAudiencesMapper;
import com.ai.slp.product.dao.mapper.interfaces.product.ProductMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;

/**
 * Created by jackieliu on 16/5/5.
 */
@Component
public class ProductAtomSVImpl implements IProductAtomSV {
	@Autowired
	ProductMapper productMapper;
	ProdAudiencesMapper prodAudiencesMapper;

	/**
	 * 添加销售商品信息
	 *
	 * @param product
	 * @return
	 */
	@Override
	public int installProduct(Product product) {
		product.setProdId(SequenceUtil.createProductProdId());
		if (product.getCreateTime() == null)
			product.setCreateTime(DateUtils.currTimeStamp());
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
		example.setOrderByClause("CREATE_TIME desc");
		example.createCriteria().andTenantIdEqualTo(tenantId).andStorageGroupIdEqualTo(groupId);
		List<Product> products = productMapper.selectByExample(example);
		return products == null || products.isEmpty() ? null : products.get(0);
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
		if (product==null || !product.getTenantId().equals(tenantId))
			product = null;
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
		if (product==null || !product.getTenantId().equals(tenantId)
				|| !product.getSupplierId().equals(supplierId))
			product = null;
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
		return productMapper.updateByPrimaryKey(product);
	}
	

	@Override
	public int updateByStandedProdId(Product product) {
		product.setOperTime(DateUtils.currTimeStamp());
		ProductCriteria example = new ProductCriteria();
		example.createCriteria().andStandedProdIdEqualTo(product.getStandedProdId());
		return productMapper.updateByExampleSelective(product, example);
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
		ProductCriteria.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(queryReq.getProductCatId()))
			criteria.andProductCatIdEqualTo(queryReq.getProductCatId());
		if (StringUtils.isNotBlank(queryReq.getProductType()))
			criteria.andProductTypeEqualTo(queryReq.getProductType());
		if (!CollectionUtil.isEmpty(queryReq.getStateList()))
			criteria.andStateIn(queryReq.getStateList());
		if (StringUtils.isNotBlank(queryReq.getProdId()))
			criteria.andProdIdLike("%"+queryReq.getProdId()+"%");
		if (StringUtils.isNotBlank(queryReq.getProdName()))
			criteria.andProdNameLike("%"+queryReq.getProdName()+"%");
		//对商户标识的查询
		if (StringUtils.isNotBlank(queryReq.getSupplierId())) {
			criteria.andSupplierIdLike("%"+queryReq.getSupplierId()+"%");
		}
		
		//获取页数和每页条数
		int pageNo = queryReq.getPageNo();
		int pageSize = queryReq.getPageSize();

		return pageQuery(example, pageNo, pageSize);
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
		example.setOrderByClause("OPER_TIME asc");//操作时间倒序
		ProductCriteria.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(queryReq.getProductCatId()))
			criteria.andProductCatIdEqualTo(queryReq.getProductCatId());
		if (StringUtils.isNotBlank(queryReq.getProductType()))
			criteria.andProductTypeEqualTo(queryReq.getProductType());
		if (!CollectionUtil.isEmpty(queryReq.getStateList()))
			criteria.andStateIn(queryReq.getStateList());
		if (StringUtils.isNotBlank(queryReq.getProdId()))
			criteria.andProdIdLike("%"+queryReq.getProdId()+"%");
		if (StringUtils.isNotBlank(queryReq.getProdName()))
			criteria.andProdNameLike("%"+queryReq.getProdName()+"%");
		//获取页数和每页条数
		int pageNo = queryReq.getPageNo();
		int pageSize = queryReq.getPageSize();

		return pageQuery(example, pageNo, pageSize);
	}

}
