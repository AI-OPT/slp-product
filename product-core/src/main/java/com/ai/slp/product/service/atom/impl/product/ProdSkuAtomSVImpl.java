package com.ai.slp.product.service.atom.impl.product;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.attach.ProdFastSkuAttach;
import com.ai.slp.product.dao.mapper.attach.ProdSkuAttachMapper;
import com.ai.slp.product.dao.mapper.attach.ProdSkuInfoSes;
import com.ai.slp.product.dao.mapper.attach.ProdSkuMapperExt;
import com.ai.slp.product.dao.mapper.bo.product.ProdSku;
import com.ai.slp.product.dao.mapper.bo.product.ProdSkuCriteria;
import com.ai.slp.product.dao.mapper.interfaces.product.ProdSkuMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;

/**
 * Created by jackieliu on 16/5/6.
 */
@Component
public class ProdSkuAtomSVImpl implements IProdSkuAtomSV {
	@Autowired
	ProdSkuMapper prodSkuMapper;
	@Autowired
	ProdSkuAttachMapper prodSkuAttachMapper;
	@Autowired
	ProdSkuMapperExt prodSkuMapperExt;
	/**
	 * 查询商品的SKU信息
	 *
	 * @param tenantId
	 * @param prodId
	 * @return
	 */
	@Override
	public List<ProdSku> querySkuOfProd(String tenantId, String prodId) {
		return querySkuOfProd(tenantId,prodId,false);
	}

	/**
	 * 查询商品的SKU信息
	 *
	 * @param tenantId
	 * @param prodId
	 * @param hasDiscard 是否包含废弃状态
	 * @return
	 */
	@Override
	public List<ProdSku> querySkuOfProd(String tenantId, String prodId, boolean hasDiscard) {
		ProdSkuCriteria example = new ProdSkuCriteria();
		ProdSkuCriteria.Criteria criteria = example.createCriteria().
				andTenantIdEqualTo(tenantId).andProdIdEqualTo(prodId);
		//如果不包含废弃状态
		if (!hasDiscard){
			criteria.andStateEqualTo(ProductConstants.ProdSku.State.ACTIVE);
		}
		return prodSkuMapper.selectByExample(example);
	}


	/**
	 * 废弃指定SKU单品
	 *
	 * @param prodSku
	 * @return
	 */
	@Override
	public int updateSkuById(ProdSku prodSku) {
		prodSku.setOperTime(DateUtils.currTimeStamp());
		return prodSkuMapper.updateByPrimaryKey(prodSku);
	}

	/**
	 * 添加商品SKU信息
	 *
	 * @param prodSku
	 * @return
	 */
	@Override
	public int createObj(ProdSku prodSku) {
		//prodSku.setSkuId(SequenceUtil.genProdSkuId());
		prodSku.setOperTime(DateUtils.currTimeStamp());
		return prodSkuMapper.insert(prodSku);
	}

	@Override
	public ProdSku querySkuById(String tenantId,String skuId) {
		ProdSkuCriteria example = new ProdSkuCriteria();
		example.createCriteria().andStateEqualTo(ProductConstants.ProdSku.State.ACTIVE)
				.andTenantIdEqualTo(tenantId)
				.andSkuIdEqualTo(skuId);
		List<ProdSku> prodSkuList = prodSkuMapper.selectByExample(example);
		return CollectionUtil.isEmpty(prodSkuList) ? null : prodSkuList.get(0);
	}

	/**
	 * 根据属性串查询SKU信息
	 *
	 * @param tenantId
	 * @param attrs
	 * @return
	 */
	@Override
	public ProdSku querySkuByAttrs(String tenantId, String attrs) {
		ProdSkuCriteria example = new ProdSkuCriteria();
		example.createCriteria().andStateEqualTo(ProductConstants.ProdSku.State.ACTIVE)
				.andTenantIdEqualTo(tenantId)
				.andSaleAttrsEqualTo(attrs);
		List<ProdSku> prodSkuList = prodSkuMapper.selectByExample(example);
		return CollectionUtil.isEmpty(prodSkuList)?null:prodSkuList.get(0);
	}


	/**
	 * 查询全国范围的快充产品
	 *
	 * @param tenantId
	 * @param productCatId
	 * @param basicOrgId
	 * @param userType
	 * @param userId
	 * @param attrId
	 * @return
	 */
	@Override
	public List<ProdFastSkuAttach> queryNationFastProd(String tenantId, String productCatId, String basicOrgId, String userType, String userId, Long attrId) {
		return prodSkuAttachMapper.queryNationwideFast(tenantId,productCatId,basicOrgId,userType,userId,attrId);
	}

	/**
	 * 查询指定地域的快充产品
	 *
	 * @param tenantId
	 * @param productCatId
	 * @param basicOrgId
	 * @param userType
	 * @param userId
	 * @param attrId
	 * @param provCode
	 * @return
	 */
	@Override
	public List<ProdFastSkuAttach> queryLocalFastProd(String tenantId, String productCatId, String basicOrgId, String userType, String userId, Long attrId, Integer provCode) {
		return prodSkuAttachMapper.queryLocalFast(tenantId,productCatId,basicOrgId,userType,userId,attrId,provCode);
	}

	@Override
	public List<ProdSku> queryProdSkuBySaleAttrs(String tenantId, String groupId, Set<String> skuSaleAttrs) {
		ProdSkuCriteria example = new ProdSkuCriteria();
		ProdSkuCriteria.Criteria criteria = example.createCriteria().
				andTenantIdEqualTo(tenantId);
		criteria.andStateNotEqualTo(ProductConstants.ProdSku.State.INACTIVE);
		List<String> saleAttrList = new LinkedList<String>();
		saleAttrList.addAll(skuSaleAttrs);
		criteria.andSaleAttrsIn(saleAttrList);
		return prodSkuMapper.selectByExample(example);
	}

	/**
	 * 通过SKU标识查询SKU信息
	 *
	 * @param tenantId
	 * @param skuId
	 * @param hasDiscard
	 * @return
	 * @author lipeng16
	 */
	@Override
	public ProdSku querySkuById(String tenantId, String skuId, boolean hasDiscard) {
		ProdSkuCriteria example = new ProdSkuCriteria();
		ProdSkuCriteria.Criteria criteria = example.createCriteria()
				.andTenantIdEqualTo(tenantId)
				.andSkuIdEqualTo(skuId);
		if (!hasDiscard){
			criteria.andStateEqualTo(ProductConstants.ProdSku.State.ACTIVE);
		}
		List<ProdSku> prodSkuList = prodSkuMapper.selectByExample(example);
		return CollectionUtil.isEmpty(prodSkuList) ? null : prodSkuList.get(0);
	}

	/**
	 * 查询指定商品的SKU信息,用于添加搜索信息
	 *
	 * @param productId
	 * @return
	 */
	@Override
	public List<ProdSkuInfoSes> queryOfProdForSearch(String productId) {
		return prodSkuMapperExt.selectByProdId(productId);
	}

	/**
	 * 为搜索信息,添加所有在售商品
	 *
	 * @param limitStart
	 * @param limitEnd
	 * @return
	 */
	@Override
	public List<ProdSkuInfoSes> queryInSaleForSearch(Integer limitStart, Integer limitEnd) {
		Map<String,Integer> params = new HashMap<>();
		params.put("limitStart",limitStart);
		params.put("limitEnd",limitEnd);
		return prodSkuMapperExt.selectActive(params);
	}


}
