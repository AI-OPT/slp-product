package com.ai.slp.product.service.atom.impl.product;

import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.api.product.param.ProductEditQueryReq;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.product.ProductCriteria;
import com.ai.slp.product.dao.mapper.interfaces.product.ProductMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jackieliu on 16/5/5.
 */
@Component
public class ProductAtomSVImpl implements IProductAtomSV {
	@Autowired
	ProductMapper productMapper;
//	@Autowired
//	ProductAttachMapper productAttachMapper;

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
		if (product!=null && !product.getTenantId().equals(tenantId))
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
//		if (ProductConstants.Product.State.ADD.equals(queryReq.getState()))
//			example.setOrderByClause("CREATE_TIME desc");//创建时间倒序
//		else
			example.setOrderByClause("OPER_TIME desc");//操作时间倒序
		ProductCriteria.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(queryReq.getProductCatId()))
			criteria.andProductCatIdEqualTo(queryReq.getProductCatId());
		if (StringUtils.isNotBlank(queryReq.getProductType()))
			criteria.andProductTypeEqualTo(queryReq.getProductType());
		if (CollectionUtil.isEmpty(queryReq.getStateList()))
			criteria.andStateIn(queryReq.getStateList());
		if (StringUtils.isNotBlank(queryReq.getProdId()))
			criteria.andProdIdLike("%"+queryReq.getProdId()+"%");
		if (StringUtils.isNotBlank(queryReq.getProdName()))
			criteria.andProdNameLike("%"+queryReq.getProdName()+"%");

		PageInfo<Product> pageInfo = new PageInfo<>();
		// 设置总数
		pageInfo.setCount(productMapper.countByExample(example));
		if (queryReq.getPageNo() != null && queryReq.getPageSize() != null) {
			example.setLimitStart((queryReq.getPageNo()-1)*queryReq.getPageSize());
			example.setLimitEnd(queryReq.getPageSize());
		}
		pageInfo.setPageNo(queryReq.getPageNo());
		pageInfo.setPageSize(queryReq.getPageSize());
		pageInfo.setResult(productMapper.selectByExample(example));
		return pageInfo;
	}

	@Override
	public Product queryProductByGroupId(String tenantId, String groupId) {
		ProductCriteria example = new ProductCriteria();
		example.createCriteria().andTenantIdEqualTo(tenantId).andStorageGroupIdEqualTo(groupId);
		return productMapper.selectByExample(example).get(0);
	}
}
