package com.ai.slp.product.service.atom.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.slp.product.constants.StandedProductConstants;
import com.ai.slp.product.dao.mapper.attach.StandedProdAttachMapper;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.bo.StandedProductCriteria;
import com.ai.slp.product.dao.mapper.interfaces.StandedProductMapper;
import com.ai.slp.product.dao.mapper.interfaces.product.ProductMapper;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;
import com.ai.slp.product.vo.StandedProdPageQueryVo;

/**
 * 标准品原子操作 Created by jackieliu on 16/4/28.
 */
@Component
public class StandedProductAtomSVImpl implements IStandedProductAtomSV {
	@Autowired
	StandedProductMapper standedProductMapper;
	@Autowired
	ProductMapper productMapper;
	@Autowired
	StandedProdAttachMapper standedProdAttachMapper;
	@Override
	public int installObj(StandedProduct standedProduct) {
		if (standedProduct == null){
			return 0;
		}
		// 设置标准品标识
		standedProduct.setStandedProdId(SequenceUtil.genStandedProductId());
		// 设置添加时间
		if (standedProduct.getCreateTime() == null){
			standedProduct.setCreateTime(DateUtil.getSysDate());
		}
		standedProduct.setOperTime(standedProduct.getCreateTime());
		standedProduct.setOperId(standedProduct.getCreateId());
		return standedProductMapper.insertSelective(standedProduct);
	}
	
	@Override
	public int installObj(StandedProduct standedProduct,String productId) {
		if (standedProduct == null){
			return 0;
		}
		// 设置标准品标识
		standedProduct.setStandedProdId(productId);
		// 设置添加时间
		if (standedProduct.getCreateTime() == null){
			standedProduct.setCreateTime(DateUtil.getSysDate());
		}
		standedProduct.setOperTime(standedProduct.getCreateTime());
		standedProduct.setOperId(standedProduct.getCreateId());
		return standedProductMapper.insertSelective(standedProduct);
	}

	@Override
	public int updateObj(StandedProduct standedProduct) {
		if (standedProduct == null){
			return 0;
		}
		// 不允许更新创建人,创建时间,所属类目
		standedProduct.setCreateTime(null);
		standedProduct.setCreateId(null);
		standedProduct.setProductCatId(null);
		standedProduct.setOperTime(DateUtil.getSysDate());
		StandedProductCriteria example = new StandedProductCriteria();
		example.createCriteria().andTenantIdEqualTo(standedProduct.getTenantId())
				.andStandedProdIdEqualTo(standedProduct.getStandedProdId());
		return standedProductMapper.updateByExampleSelective(standedProduct, example);
	}

	@Override
	public int updateStandedProductInfo(StandedProduct standedProduct) {
		return standedProdAttachMapper.updateStandedProductInfo(standedProduct.getStandedProdId(),
				standedProduct.getStandedProductName(),
				standedProduct.getProductType(),
				standedProduct.getState());
	}
	
	@Override
	public StandedProduct selectById(String tenantId, String standedProdId) {
		if (StringUtils.isBlank(tenantId) || StringUtils.isBlank(standedProdId)){
			return null;
		}
		StandedProduct product = standedProductMapper.selectByPrimaryKey(standedProdId);
		if(product!=null&&!tenantId.equalsIgnoreCase(product.getTenantId())	){
			return null;
		}
		return product;
	}

	@Override
	public PageInfo<StandedProduct> queryForPage(StandedProdPageQueryVo request) {
		StandedProductCriteria example = new StandedProductCriteria();
		StandedProductCriteria.Criteria criteria = example.createCriteria();
		criteria.andTenantIdEqualTo(request.getTenantId());
		// 类目id
		if (StringUtils.isNotBlank(request.getProductCatId())){
			criteria.andProductCatIdEqualTo(request.getProductCatId());
		}
		// 商品标识
		if (StringUtils.isNotBlank(request.getProductId())){
			//精确查询
			criteria.andStandedProdIdEqualTo(request.getProductId());
			//criteria.andStandedProdIdLike("%" + request.getProductId() + "%");
		}
		// 商品名称
		if (StringUtils.isNotBlank(request.getProductName())){
			criteria.andStandedProductNameLike("%" + request.getProductName() + "%");
		}
		// 商品状态
		if (StringUtils.isNotBlank(request.getState())){
			criteria.andStateEqualTo(request.getState());
		}
		// 商品类型
		if (StringUtils.isNotBlank(request.getProductType())){
			criteria.andProductTypeEqualTo(request.getProductType());
		}
		// 添加时间 开始时间
		if (request.getCreateStartTime() != null){
			criteria.andCreateTimeGreaterThanOrEqualTo(DateUtils.toTimeStamp(request.getCreateStartTime()));
		}
		// 添加时间 截止时间
		if (request.getCreateEndTime() != null){
			criteria.andCreateTimeLessThanOrEqualTo(DateUtils.toTimeStamp(request.getCreateEndTime()));
		}
		// 操作时间 开始时间
		if (request.getOperStartTime() != null){
			criteria.andOperTimeGreaterThanOrEqualTo(DateUtils.toTimeStamp(request.getOperStartTime()));
		}
		// 操作时间 截止时间
		if (request.getOperEndTime() != null){
			criteria.andOperTimeLessThanOrEqualTo(DateUtils.toTimeStamp(request.getOperEndTime()));
		}
		// 操作人
		if (request.getOperId() != null){
			criteria.andOperIdEqualTo(request.getOperId());
		}
		
		//商户ID	(需求新增)
		if (StringUtils.isNotBlank(request.getSupplierId())) {
			criteria.andSupplierIdEqualTo(request.getSupplierId());
		}
		
		//	example.setOrderByClause("OPER_TIME desc");//操作时间倒序
		example.setOrderByClause("CREATE_TIME desc");//创建时间倒序
		PageInfo<StandedProduct> pageInfo = new PageInfo<>();
		// 设置总数
		pageInfo.setCount(standedProductMapper.countByExample(example));
		if (request.getPageNo() != null && request.getPageSize() != null) {
			example.setLimitStart((request.getPageNo() - 1) * request.getPageSize());
			example.setLimitEnd(request.getPageSize());
		}
		pageInfo.setPageNo(request.getPageNo());
		pageInfo.setPageSize(request.getPageSize());
		pageInfo.setResult(standedProductMapper.selectByExample(example));
		return pageInfo;
	}

	@Override
	public int queryByCatId(String tenantID,String catId,boolean hasDiscard) {
		StandedProductCriteria example = new StandedProductCriteria();
		StandedProductCriteria.Criteria criteria = example.createCriteria()
				.andTenantIdEqualTo(tenantID).andProductCatIdEqualTo(catId);
		//如果不包括无效,则进行状态检查
		if (!hasDiscard){
			criteria.andStateNotEqualTo(StandedProductConstants.STATUS_DISCARD);
		}
		return standedProductMapper.countByExample(example);
	}

	@Override
	public int updateMarketPrice(String tenantId, String standedProductId, long marketPrice, Long operId) {
		return standedProdAttachMapper.updateStandedProductMarketPrice(standedProductId, marketPrice+"");
	}

	/**
	 * 通过标准品名称查ID集合(模糊查询)
	 */
	@Override
	public List<String> queryIdByName(String standedProductName) {
		StandedProductCriteria example = new StandedProductCriteria();
		example.createCriteria().andStandedProductNameLike(standedProductName);
		List<StandedProduct> standedProductList = standedProductMapper.selectByExample(example);
		if (CollectionUtil.isEmpty(standedProductList)){
			return null;
		}
		// 遍历结果集获取ID集合
		List<String> standedProductIdList = new ArrayList<>();
		for (StandedProduct standedProduct : standedProductList) {
			standedProductIdList.add(standedProduct.getStandedProdId());
		}
		return standedProductIdList;
	}


}
