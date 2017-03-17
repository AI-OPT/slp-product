package com.ai.slp.product.service.business.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.api.normproduct.param.AttrMap;
import com.ai.slp.product.api.normproduct.param.AttrValInfo;
import com.ai.slp.product.api.normproduct.param.AttrValRequest;
import com.ai.slp.product.api.normproduct.param.MarketPriceUpdate;
import com.ai.slp.product.api.normproduct.param.NormProdAndKeyAttrRes;
import com.ai.slp.product.api.normproduct.param.NormProdInfoResponse;
import com.ai.slp.product.api.normproduct.param.NormProdRequest;
import com.ai.slp.product.api.normproduct.param.NormProdResponse;
import com.ai.slp.product.api.normproduct.param.NormProdSaveRequest;
import com.ai.slp.product.api.normproduct.param.ProdCatAttrInfo;
import com.ai.slp.product.api.storage.param.STOStorageGroup;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.constants.ProductCatConstants;
import com.ai.slp.product.constants.StandedProductConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.attach.ProdCatAttrAttch;
import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDef;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttr;
import com.ai.slp.product.dao.mapper.bo.ProdPriceLog;
import com.ai.slp.product.dao.mapper.bo.StandedProdAttr;
import com.ai.slp.product.dao.mapper.bo.StandedProdAttrLog;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.bo.StandedProductLog;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.service.atom.interfaces.IProdAttrValDefAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAttachAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrValAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdCatDefAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdPriceLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProdAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProdAttrLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProductLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.business.interfaces.INormProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageGroupBusiSV;
import com.ai.slp.product.util.AttrValRequestComparator;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.OldAttrValListComparator;
import com.ai.slp.product.util.StorageComparator;
import com.ai.slp.product.util.StorageGroupComparator;
import com.ai.slp.product.vo.StandedProdPageQueryVo;

/**
 * Created by jackieliu on 16/4/27.
 */
@Service
@Transactional
public class NormProductBusiSVImpl implements INormProductBusiSV {
	private static final Logger logger = LoggerFactory.getLogger(NormProductBusiSVImpl.class);

	private static String ATTR_LINE_VAL = "||";

	@Autowired
	IStandedProductAtomSV standedProductAtomSV;
	@Autowired
	IStandedProductLogAtomSV standedProductLogAtomSV;
	@Autowired
	IStandedProdAttrAtomSV standedProdAttrAtomSV;
	@Autowired
	IStandedProdAttrLogAtomSV standedProdAttrLogAtomSV;
	@Autowired
	IStorageGroupAtomSV storageGroupAtomSV;
	@Autowired
	IStorageAtomSV storageAtomSV;
	@Autowired
	IProdCatAttrAttachAtomSV catAttrAttachAtomSV;
	@Autowired
	IProdCatAttrAtomSV prodCatAttrAtomSV;
	@Autowired
	IProdCatAttrValAtomSV prodCatAttrValAtomSV;
	@Autowired
	IProdAttrValDefAtomSV attrValDefAtomSV;
	@Autowired
	IProdCatDefAtomSV catDefAtomSV;
	@Autowired
	IProdPriceLogAtomSV prodPriceLogAtomSV;
	@Autowired
	IProductAtomSV productAtomSV;
	@Autowired
	IStorageGroupBusiSV storageGroupBusiSV;
	@Autowired
	IProdSkuBusiSV prodSkuBusiSV;
	@Autowired
	IStorageBusiSV storageBusiSV;

	@Autowired
	IProductBusiSV productBusiSV;
	
	/**
	 * 添加标准品
	 *
	 * @param normProduct
	 * @return 添加成功后的标准品的标识
	 */
	@Override
	public String installNormProd(NormProdSaveRequest normProduct) {
		StandedProduct standedProduct = saveNormProd(normProduct);
		if(standedProduct!=null){
			return standedProduct.getStandedProdId();
		}
		return null;
	}

	/**
	 * 保存标准品
	 * @param normProduct
	 * @return
	 * @author Gavin
	 * @UCUSER
	 */
	private StandedProduct saveNormProd(NormProdSaveRequest normProduct) {
		StandedProduct standedProduct = saveNormProdWithOutAttr(normProduct);
		// 添加标准品属性值
		List<AttrValRequest> attrValList = normProduct.getAttrValList();
		Timestamp nowTime = DateUtils.currTimeStamp();
		for (AttrValRequest attrValReq : attrValList) {
			StandedProdAttr prodAttr = new StandedProdAttr();
			BeanUtils.copyProperties(prodAttr, attrValReq);
			prodAttr.setTenantId(normProduct.getTenantId());
			prodAttr.setStandedProdId(standedProduct.getStandedProdId());
			prodAttr.setAttrvalueDefId(attrValReq.getAttrValId());
			prodAttr.setAttrValueName(attrValReq.getAttrVal());
			prodAttr.setAttrValueName2(attrValReq.getAttrVal2());
			prodAttr.setState(CommonConstants.STATE_ACTIVE);// 设置为有效
			prodAttr.setOperId(normProduct.getOperId());
			prodAttr.setOperTime(nowTime);
			prodAttr.setSerialNumber(getProductAttrSerialNo());
			// 添加成功
			standedProdAttrAtomSV.installObj(prodAttr);
		}
		return standedProduct;
	}

	/**
	 * 保存标准品,不同时保存属性
	 * @param normProduct
	 * @return
	 * @author Gavin
	 * @UCUSER
	 */
	private StandedProduct saveNormProdWithOutAttr(NormProdSaveRequest normProduct) {
		// 添加标准品
		StandedProduct standedProduct = new StandedProduct();
		BeanUtils.copyProperties(standedProduct, normProduct);
		standedProduct.setStandedProductName(normProduct.getProductName());
		// 添加标准品
		standedProductAtomSV.installObj(standedProduct);
		return standedProduct;
	}

	@Override
	public String installNormProdAndPtoGroup(NormProdSaveRequest normProduct) {
		// 添加标准品,不需保存标准品属性
		StandedProduct standedProduct = saveNormProdWithOutAttr(normProduct);

		// 添加一个库存组
		STOStorageGroup storageGroup = createSTOStorageGroup(normProduct, standedProduct);
		StorageGroup group = storageGroupBusiSV.addGroupObj(storageGroup);
		
		// 添加商品,在商品属性表保存商品属性值
		 List<AttrValRequest> attrValList = normProduct.getAttrValList();
		Product product = productBusiSV.addProductWithStorageGroup(standedProduct, group,attrValList);
		
		// 添加SKU
		prodSkuBusiSV.createSkuProduct(product);
		
		return product.getProdId();
		
	}

	private STOStorageGroup createSTOStorageGroup(NormProdSaveRequest normProduct, StandedProduct standedProduct) {
		STOStorageGroup storageGroup = new STOStorageGroup();
		storageGroup.setTenantId(normProduct.getTenantId());
		storageGroup.setCreateId(normProduct.getOperId());
		storageGroup.setStandedProdId(standedProduct.getStandedProdId());
		storageGroup.setSupplierId(normProduct.getSupplierId());
		storageGroup.setStorageGroupName(StorageConstants.StorageGroup.DEFAULT_NAME);
//		String groupId = storageGroupBusiSV.addGroup(storageGroup);
		return storageGroup;
	}

	/**
	 * 更新标准品
	 *
	 * @param normProdct
	 */
	@Override
	public int updateNormProd(NormProdSaveRequest normProdct) {
		String tenantId = normProdct.getTenantId(), productId = normProdct.getProductId();
		// 查询是否存在
		StandedProduct standedProduct = standedProductAtomSV.selectById(tenantId, productId);
		if (standedProduct == null){
			throw new BusinessException("", "不存在指定标准品,租户ID:" + tenantId + ",标准品标识:" + productId);
		}
		// 判断商户ID是否传入的商户ID
		if (!normProdct.getSupplierId().equals(standedProduct.getSupplierId())){
			throw new BusinessException("",
					"标准品所属商户ID:" + standedProduct.getSupplierId() + "与当前商户ID:" + normProdct.getSupplierId() + "不一致!");
		}

		/*
		 * 总共有6种状态变化, 1.不允许变更为废弃状态;2.已废弃标准品不允许变更状态;3.可用变为不可用,需要检查.
		 */
		// 状态变更
		if (!standedProduct.getState().equals(normProdct.getState())) {
			// 变更为废弃
			if (StandedProductConstants.STATUS_DISCARD.equals(normProdct.getState())){
				throw new BusinessException("", "不允许变更为[废弃]状态,请使用废弃接口");
			}
			// 废弃状态下不允许变更为其他状态
			else if (StandedProductConstants.STATUS_DISCARD.equals(standedProduct.getState())){
				throw new BusinessException("", "不允许将[废弃]状态变更为其他状态");
			}
			// 将可用变为不可用
			else if (StandedProductConstants.STATUS_ACTIVE.equals(standedProduct.getState())
					&& StandedProductConstants.STATUS_INACTIVE.equals(normProdct.getState())) {
				// 检查是否有启用状态库存组
				int groupNum = storageGroupAtomSV.countActive(tenantId, productId);
				if (groupNum > 0){
					throw new BusinessException("", "该标准品下存在[" + groupNum + "]个启用的库存组,不允许变更为不可用");
				}
			}
		}
		// 变更信息
		standedProduct.setStandedProductName(normProdct.getProductName());
		standedProduct.setProductType(normProdct.getProductType());
		standedProduct.setState(normProdct.getState());
		StandedProductLog productLog = new StandedProductLog();
		BeanUtils.copyProperties(productLog, standedProduct);
		int updateCount = standedProductAtomSV.updateObj(standedProduct);
		if (updateCount > 0) {
			productLog.setOperTime(standedProduct.getOperTime());
			standedProductLogAtomSV.insert(productLog);
		}
		// 变更属性值. 1.将原来属性值设置为不可用;2,启用新的属性值.
		updateStandedProdAttr(normProdct);
		return updateCount;
	}

	@Override
	public int updateNormProdAndStoGroup(NormProdSaveRequest productInfoRequest) {
		// 更新库存组相关信息
		updateStoGroupInfo(productInfoRequest);
		// 更新标准品及属性
		int updateCount = updateNormProd(productInfoRequest);
		// 更新销售品
		if(updateCount>0){
			Product product = new Product();
			product.setStandedProdId(productInfoRequest.getProductId());
			product.setProdName(productInfoRequest.getProductName());
			product.setProductType(productInfoRequest.getProductType());
			productAtomSV.updateByStandedProdId(product);
		}
		return updateCount;
	}

	/**
	 * 更新库存组相关信息
	 * 
	 * @param productInfoRequest
	 * @author jiaxs
	 */
	private void updateStoGroupInfo(NormProdSaveRequest productInfoRequest) {
		Map<Long, List<String>> addAttrAndValueMap = new HashMap<Long, List<String>>();// 新增数据
		Map<Long, List<String>> delAttrAndValueMap = new HashMap<Long, List<String>>();// 删除数据
		setAddAndDelAttrValueData(productInfoRequest, addAttrAndValueMap, delAttrAndValueMap);
		if (addAttrAndValueMap.size() > 0 || delAttrAndValueMap.size() > 0) {
			// 废除标准品关联库存组下的所有库存
			String tenantId = productInfoRequest.getTenantId();
			String supplierId = productInfoRequest.getSupplierId();
			String productId = productInfoRequest.getProductId();
			Long operId = productInfoRequest.getOperId();
			List<StorageGroup> groupList = storageGroupAtomSV.queryOfStandedProd(tenantId, supplierId, productId);
			if (groupList == null || groupList.size() == 0) {
				return;
			}
			for (StorageGroup group : groupList) {
				String storageGroupId = group.getStorageGroupId();
				List<Storage> storageList = storageAtomSV.queryOfGroup(tenantId, storageGroupId,false);
				if (CollectionUtil.isEmpty(storageList)) {
					continue;
				}
				
				//list升序排序
				Collections.sort(storageList, new StorageComparator());
				
				for (Storage storage : storageList) {
					storageBusiSV.discardStorage(tenantId, storage, productInfoRequest.getOperId(), true);
				}
				// 新增数据
				if (addAttrAndValueMap.size() > 0) {
					prodSkuBusiSV.createSkuOfAttrValue(tenantId, storageGroupId, addAttrAndValueMap, operId);
				}
				// 删除数据
				if (delAttrAndValueMap.size() > 0) {
					prodSkuBusiSV.discardSkuOfAttrValue(tenantId, storageGroupId, delAttrAndValueMap, operId);
				}
			}
		}
	}

	/**
	 * 设置新增 删除 数据 销售属性值
	 * 
	 * @param productInfoRequest
	 * @param addAttrAndValueMap
	 * @param delAttrAndValueMap
	 * @author jiaxs
	 */
	private void setAddAndDelAttrValueData(NormProdSaveRequest productInfoRequest,
			Map<Long, List<String>> addAttrAndValueMap, Map<Long, List<String>> delAttrAndValueMap) {
		// 修改后的属性信息
		List<AttrValRequest> attrValList = productInfoRequest.getAttrValList();
		Map<Long, List<String>> attrAndValMap = getAttrAndValMap(attrValList);
		// 查询原属性信息
		String tenantId = productInfoRequest.getTenantId();
		List<StandedProdAttr> attrList = standedProdAttrAtomSV.queryByNormProduct(tenantId,
				productInfoRequest.getProductId());
		Map<Long, List<String>> oldAttrAndValueMap = getAttrAndValueMap(attrList);
		// 查询销售属性
		List<ProdCatAttr> catAttrList = prodCatAttrAtomSV.queryAttrOfCatByIdAndType(tenantId,
				productInfoRequest.getProductCatId(), ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_SALE);
		for (ProdCatAttr prodCatAttr : catAttrList) {
			Long attrId = prodCatAttr.getAttrId();
			List<String> attrAndValSet = attrAndValMap.get(attrId);
			List<String> oldAttrAndValSet = oldAttrAndValueMap.get(attrId);
			if (attrAndValSet == null && oldAttrAndValSet == null) {
				continue;
			}
			if (attrAndValSet == null) {
				delAttrAndValueMap.put(attrId, oldAttrAndValSet);
				continue;
			}
			if (oldAttrAndValSet == null) {
				addAttrAndValueMap.put(attrId, attrAndValSet);
				continue;
			}
			if (attrAndValSet.containsAll(oldAttrAndValSet) && oldAttrAndValSet.containsAll(attrAndValSet)) {
				continue;
			}
			List<String> addValueSet = new LinkedList<String>();// 新增的属性值
			for (String attrValue : attrAndValSet) {
				// 原属性值不包含 说明为新增数据
				if (!oldAttrAndValSet.contains(attrValue)) {
					addValueSet.add(attrValue);
				}
			}
			if (addValueSet.size() > 0) {
				addAttrAndValueMap.put(attrId, addValueSet);
			}
			List<String> delValueSet = new LinkedList<String>();// 删除的属性值
			for (String attrValue : oldAttrAndValSet) {
				// 新属性值不包含 说明为删除数据
				if (!attrAndValSet.contains(attrValue)) {
					delValueSet.add(attrValue);
				}
			}
			if (delValueSet.size() > 0) {
				delAttrAndValueMap.put(attrId, delValueSet);
			}
		}
		if (addAttrAndValueMap.size() > 0 || delAttrAndValueMap.size() > 0) {
			for (ProdCatAttr prodCatAttr : catAttrList) {
				Long attrId = prodCatAttr.getAttrId();
				// 新增数据
				if (addAttrAndValueMap.size() > 0) {
					Set<Long> addkeySet = addAttrAndValueMap.keySet();
					if (!addkeySet.contains(attrId)) {
						addAttrAndValueMap.put(attrId, attrAndValMap.get(attrId));
					}
				}
				// 删除数据
				if (delAttrAndValueMap.size() > 0) {
					Set<Long> delkeySet = delAttrAndValueMap.keySet();
					if (!delkeySet.contains(attrId)) {
						delAttrAndValueMap.put(attrId, attrAndValMap.get(attrId));
					}
				}
			}
		}
	}

	private Map<Long, List<String>> getAttrAndValueMap(List<StandedProdAttr> attrList) {
		Map<Long, List<String>> attrAndValueIds = new HashMap<>();
		for (StandedProdAttr prodAttr : attrList) {
			List<String> attrVal = attrAndValueIds.get(prodAttr.getAttrId());
			if (attrVal == null) {
				attrVal = new LinkedList<String>();
			}
			attrVal.add(prodAttr.getAttrvalueDefId());
			if (!attrAndValueIds.containsKey(prodAttr.getAttrId())){
				attrAndValueIds.put(prodAttr.getAttrId(), attrVal);
			}
		}
		return attrAndValueIds;
	}

	/**
	 * 转换格式 获得属性key 属性值集合的map
	 * 
	 * @param attrValList
	 * @return
	 * @author jiaxs
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL
	 */
	private Map<Long, List<String>> getAttrAndValMap(List<AttrValRequest> attrValList) {
		Map<Long, List<String>> attrAndValMap = new HashMap<Long, List<String>>();
		if (attrValList != null && attrValList.size() > 0) {
			for (AttrValRequest attrVal : attrValList) {
				Long attrId = attrVal.getAttrId();
				List<String> valueSet = attrAndValMap.get(attrId);
				if (valueSet == null) {
					valueSet = new LinkedList<String>();
				}
				valueSet.add(attrVal.getAttrValId());
				if (!attrAndValMap.containsKey(attrVal.getAttrId())){
					attrAndValMap.put(attrVal.getAttrId(), valueSet);
				}
			}
		}
		return attrAndValMap;
	}

	/**
	 * 查询指定标准品信息
	 *
	 * @param tenantId
	 *            租户id
	 * @param productId
	 *            标准品标识
	 * @return
	 */
	@Override
	public NormProdInfoResponse queryById(String tenantId, String productId) {
		StandedProduct product = standedProductAtomSV.selectById(tenantId, productId);
		if (product == null) {
			logger.warn("租户[{}]下不存在标识[{}]的标准品", tenantId, productId);
			throw new BusinessException("", "未找到对应标准品信息,租户id=" + tenantId + ",标准品标识=" + productId);
		}
		NormProdInfoResponse response = new NormProdInfoResponse();
		// 标准品信息填充返回值
		BeanUtils.copyProperties(response, product);
		response.setProductId(product.getStandedProdId());
		response.setProductName(product.getStandedProductName());
		Map<Long, Set<String>> attrAndValueIds = new HashMap<>();
		Map<Long, String> attrAndValueMap = new HashMap<>();
		// 查询属性信息
		List<StandedProdAttr> attrList = standedProdAttrAtomSV.queryByNormProduct(tenantId, productId);
		for (StandedProdAttr prodAttr : attrList) {
			Set<String> attrVal = attrAndValueIds.get(prodAttr.getAttrId());
			if (attrVal == null) {
				attrVal = new HashSet<>();
			}
			attrVal.add(prodAttr.getAttrvalueDefId());
			if (!attrAndValueIds.containsKey(prodAttr.getAttrId())){
				attrAndValueIds.put(prodAttr.getAttrId(), attrVal);
			}
			if(StringUtils.isBlank(prodAttr.getAttrvalueDefId())){
				attrAndValueMap.put(prodAttr.getAttrId(), prodAttr.getAttrValueName());
			}
		}
		response.setAttrAndValueIds(attrAndValueIds);
		response.setAttrAndValueMap(attrAndValueMap);
		return response;
	}

	@Override
	public PageInfo<StandedProduct> queryForPage(NormProdRequest productRequest) {
		long startTime = System.currentTimeMillis();
		logger.info("=====开始商品查询 NormProductBusiSVImpl.queryNormForPage, 时间戳:{}",startTime);
		
		StandedProdPageQueryVo pageQueryVo = new StandedProdPageQueryVo();
		BeanUtils.copyProperties(pageQueryVo, productRequest);
		pageQueryVo.setProductId(productRequest.getStandedProdId());
		pageQueryVo.setProductName(productRequest.getStandedProductName());
		long atomStart = System.currentTimeMillis();
		
		logger.info("开始商品查询原子服务:standedProductAtomSV.queryForPage, 时间戳:{}",atomStart);
		
		// 查询结果
		PageInfo<StandedProduct> productPageInfo = standedProductAtomSV.queryForPage(pageQueryVo);
		
		long atomEnd = System.currentTimeMillis();
		logger.info("结束商品查询原子服务:standedProductAtomSV.queryForPage, 当前时间戳:{},用时:{}",atomEnd,(atomEnd-atomStart));
		
/*		// 接口输出接口
		PageInfoResponse<NormProdResponse> normProdPageInfo = new PageInfoResponse<NormProdResponse>();
		BeanUtils.copyProperties(normProdPageInfo, productPageInfo);
		// 添加结果集
		List<StandedProduct> productList = productPageInfo.getResult();
		List<NormProdResponse> normProductList = new ArrayList<NormProdResponse>();
		normProdPageInfo.setResult(normProductList);

		for (StandedProduct standedProduct : productList) {
			NormProdResponse normProduct = new NormProdResponse();
			BeanUtils.copyProperties(normProduct, standedProduct);
			atomStart = System.currentTimeMillis();
			logger.info("开始商品类目查询原子服务:catDefAtomSV.selectAllStateById, 类目ID:{},时间戳:{}",standedProduct.getProductCatId(),atomStart);
			ProductCat productCat = catDefAtomSV.selectAllStateById(standedProduct.getTenantId(),
					standedProduct.getProductCatId());
			atomEnd = System.currentTimeMillis();
			logger.info("结束商品类目查询原子服务:catDefAtomSV.selectAllStateById, 当前时间戳:{},用时:{}",atomEnd,(atomEnd-atomStart));
			if (productCat != null){
				normProduct.setCatName(productCat.getProductCatName());
			}
			normProduct.setCatId(standedProduct.getProductCatId());
			normProduct.setProductId(standedProduct.getStandedProdId());
			normProduct.setProductName(standedProduct.getStandedProductName());
			normProductList.add(normProduct);
		}*/
		long endTime = System.currentTimeMillis();
		logger.info("=====结束商品查询 NormProductBusiSVImpl.queryNormForPage, 当前时间戳:{},用时:{}",endTime,(endTime-startTime));
		return productPageInfo;
	}

	@Override
	public void discardProduct(String tenantId, String productId, Long operId, String supplierId) {
		StandedProduct standedProduct = standedProductAtomSV.selectById(tenantId, productId);
		if (standedProduct == null){
			throw new BusinessException("", "不存在指定商品,租户ID:" + tenantId + ",商品标识:" + productId);
		}

		if (!standedProduct.getSupplierId().equals(supplierId)){
			throw new BusinessException("",
					"商品所属商户ID:" + standedProduct.getSupplierId() + "与当前商户ID:" + supplierId + "不一致!");
		}

		// 查询没有废弃的库存组
		int noDiscardNum = storageGroupAtomSV.countNoDiscard(tenantId, productId);
		if (noDiscardNum > 0){
			throw new BusinessException("", "该商品下存在[" + noDiscardNum + "]个未废弃库存组");
		}
		discardProduct(standedProduct,operId);
	}

	private void discardProduct(StandedProduct standedProduct,Long operId){
		standedProduct.setOperId(operId);
		standedProduct.setState(StandedProductConstants.STATUS_DISCARD);// 设置废弃
		StandedProductLog productLog = new StandedProductLog();
		BeanUtils.copyProperties(productLog, standedProduct);
		// 添加日志
		if (standedProductAtomSV.updateObj(standedProduct) > 0) {
			productLog.setOperTime(standedProduct.getOperTime());
			standedProductLogAtomSV.insert(productLog);
		}
	}

	/**
	 * 废弃标准品
	 *
	 * @param tenantId   租户id
	 * @param standedProdId  标准品标识
	 * @param operId     操作者id
	 * @param supplierId
	 */
	@Override
	public void discardProductWithProduct(String tenantId, String standedProdId, Long operId, String supplierId) {
		StandedProduct standedProduct = standedProductAtomSV.selectById(tenantId, standedProdId);
		if (standedProduct == null) {
			logger.warn("not find the product. tenantId:{},standedProdId:{}",tenantId,standedProdId);
			throw new BusinessException("", "未查询到指定商品");
		}
		if (!standedProduct.getSupplierId().equals(supplierId)) {
			logger.warn("the supplierId of query and product is not accord.querySupplierId:{},prodSupplierId:{}"
				,supplierId,standedProduct.getSupplierId());
			throw new BusinessException("","未查询到商户下指定的商品");
		}
		//查询启用状态的库组
		int activeNum = storageGroupAtomSV.countActive(tenantId,standedProdId);
		if (activeNum>0){
			throw new BusinessException("", "该商品下存在[" + activeNum + "]个启用库存组");
		}

		//查询标准品下所有非废弃库存组
		List<StorageGroup> groupList = storageGroupAtomSV.queryNoDiscardOfStandProd(tenantId,supplierId,standedProdId);
		
		//对groupList排序,
		Collections.sort(groupList, new StorageGroupComparator());
		
		for (StorageGroup group:groupList){
			storageGroupBusiSV.updateGroupState(tenantId,supplierId,group.getStorageGroupId()
					,StorageConstants.StorageGroup.State.AUTO_DISCARD,operId);
		}
		//废弃标准品
		discardProduct(standedProduct,operId);
	}

	/**
	 * 查询标准品下指定类型的属性及属性值信息
	 *
	 * @param tenantId
	 * @param standedProdId
	 * @param attrType
	 * @return
	 */
	@Override
	public AttrMap queryAttrOfProduct(String tenantId, String standedProdId, String attrType) {
		// 查询标准品信息
		StandedProduct standedProduct = standedProductAtomSV.selectById(tenantId, standedProdId);
		if (standedProduct == null){
			throw new BusinessException("", "未找到对应标准品信息,租户ID:" + tenantId + ",标准品标识:" + standedProdId);
		}
		AttrMap attrMapOfNormProd = new AttrMap();
		Map<Long, List<Long>> attrAndValMap = new LinkedMap();
		Map<Long, ProdCatAttrInfo> attrDefMap = new HashMap<>();
		Map<Long, AttrValInfo> attrValDefMap = new HashMap<>();
		// 查询对应类目属性
		List<ProdCatAttrAttch> catAttrAttches = catAttrAttachAtomSV.queryAttrOfByIdAndType(tenantId,
				standedProduct.getProductCatId(), attrType);
		// 查询标准品对应属性的属性值
		for (ProdCatAttrAttch catAttrAttch : catAttrAttches) {
			ProdCatAttrInfo catAttrDef = new ProdCatAttrInfo();
			BeanUtils.copyProperties(catAttrDef, catAttrAttch);
			List<Long> attrValDefList = new ArrayList<>();
			attrAndValMap.put(catAttrDef.getAttrId(), attrValDefList);
			attrDefMap.put(catAttrDef.getAttrId(), catAttrDef);
			// 查询属性值
			List<StandedProdAttr> prodAttrs = standedProdAttrAtomSV.queryAttrVal(tenantId, standedProdId,
					catAttrAttch.getAttrId());
			for (StandedProdAttr prodAttr : prodAttrs) {
				AttrValInfo valDef = new AttrValInfo();
				BeanUtils.copyProperties(valDef, prodAttr);
				valDef.setProductAttrValId(prodAttr.getStandedProdAttrId());
				valDef.setProductId(prodAttr.getStandedProdId());
				valDef.setAttrValId(prodAttr.getAttrvalueDefId());
				valDef.setAttrVal(prodAttr.getAttrValueName());
				valDef.setAttrVal2(prodAttr.getAttrValueName2());
				if (prodAttr.getAttrvalueDefId() != null) {
					ProdAttrvalueDef attrvalueDef = attrValDefAtomSV.selectById(tenantId, prodAttr.getAttrvalueDefId());
					if (attrvalueDef != null){
						valDef.setAttrVal(attrvalueDef.getAttrValueName());
					}
				}
				attrValDefMap.put(valDef.getProductAttrValId(), valDef);
				attrValDefList.add(valDef.getProductAttrValId());
			}
		}
		attrMapOfNormProd.setAttrAndVal(attrAndValMap);
		attrMapOfNormProd.setAttrDefMap(attrDefMap);
		attrMapOfNormProd.setAttrValDefMap(attrValDefMap);
		return attrMapOfNormProd;
	}

	/**
	 * 更新标准品的属性值
	 */
	private void updateStandedProdAttr(NormProdSaveRequest normProdct) {
		//
		List<AttrValRequest> attrValList = normProdct.getAttrValList();
		// 查询原来的属性值
		Map<String, StandedProdAttr> oldAttrValMap = queryOldProdAttr(normProdct.getTenantId(),
				normProdct.getProductId());
		String tenantId = normProdct.getTenantId();
		Long operId = normProdct.getOperId();
		
		//把attrValList排序  -- 然后进行更新
		Collections.sort(attrValList, new AttrValRequestComparator());
		
		for (AttrValRequest attrValReq : attrValList) {
			// 查询属性值是否已存在
			String attrKey = attrValReq.getAttrId() + ATTR_LINE_VAL + attrValReq.getAttrValId();
			StandedProdAttr prodAttr = oldAttrValMap.get(attrKey);
			int upNum = 0;
			// 如果已经存在,则进行更新
			if (prodAttr != null) {
				prodAttr.setAttrValueName(attrValReq.getAttrVal());
				prodAttr.setAttrValueName2(attrValReq.getAttrVal2());
				// prodAttr.setSerialNumber(attrValReq.getSerialNumber());
				prodAttr.setSerialNumber(getProductAttrSerialNo());
				prodAttr.setOperId(operId);
				upNum = standedProdAttrAtomSV.updateObj(prodAttr);
			} else {
				prodAttr = new StandedProdAttr();
				BeanUtils.copyProperties(prodAttr, attrValReq);
				prodAttr.setAttrvalueDefId(attrValReq.getAttrValId());
				prodAttr.setAttrValueName(attrValReq.getAttrVal());
				prodAttr.setAttrValueName2(attrValReq.getAttrVal2());
				prodAttr.setOperId(operId);
				prodAttr.setTenantId(tenantId);
				prodAttr.setStandedProdId(normProdct.getProductId());
				// prodAttr.setSerialNumber(
				// queryValInfoSerialNum(tenantId, catId,
				// attrValReq.getAttrId(), attrValReq.getAttrValId()));
				prodAttr.setSerialNumber(getProductAttrSerialNo());
				prodAttr.setState(CommonConstants.STATE_ACTIVE);// 设置为有效
				upNum = standedProdAttrAtomSV.installObj(prodAttr);
			}
			oldAttrValMap.remove(attrKey);
			// 添加日志
			if (upNum > 0) {
				StandedProdAttrLog prodAttrLog = new StandedProdAttrLog();
				BeanUtils.copyProperties(prodAttrLog, prodAttr);
				standedProdAttrLogAtomSV.installObj(prodAttrLog);
			}
		}
		// 将未更新属性值设置为无效.
		loseActiveAttr(oldAttrValMap.values(), normProdct.getOperId());
	}

	/**
	 * 获得商品属性序列号
	 * 
	 * @return
	 * @author jiaxs
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL
	 */
	private Short getProductAttrSerialNo() {
		return Short.valueOf("1");
	}

	/**
	 * 获取标准品已关联属性值
	 *
	 * @param tenantId
	 * @param productId
	 * @return
	 */
	private Map<String, StandedProdAttr> queryOldProdAttr(String tenantId, String productId) {
		List<StandedProdAttr> prodAttrList = standedProdAttrAtomSV.queryByNormProduct(tenantId, productId);
		Map<String, StandedProdAttr> oldAttrMap = new HashMap<>();
		for (StandedProdAttr prodAttr : prodAttrList) {
			oldAttrMap.put(prodAttr.getAttrId() + ATTR_LINE_VAL + prodAttr.getAttrvalueDefId(), prodAttr);
		}
		return oldAttrMap;
	}

	/**
	 * 将为涉及的属性值设置为失效
	 *
	 * @param oldAttrValList
	 */
	private void loseActiveAttr(Collection<StandedProdAttr> oldValList, Long operId) {
		if (oldValList == null || oldValList.isEmpty()){
			return;
		}
		
		ArrayList<StandedProdAttr> oldAttrValList = new ArrayList<>();
		oldAttrValList.addAll(oldValList);
		
		//排序
		Collections.sort(oldAttrValList, new OldAttrValListComparator());
		
		for (StandedProdAttr prodAttr : oldAttrValList) {
			prodAttr.setOperId(operId);
			prodAttr.setOperTime(DateUtils.currTimeStamp());
			prodAttr.setState(CommonConstants.STATE_INACTIVE);
			if (standedProdAttrAtomSV.updateObj(prodAttr) > 0) {
				StandedProdAttrLog prodAttrLog = new StandedProdAttrLog();
				BeanUtils.copyProperties(prodAttrLog, prodAttr);
				standedProdAttrLogAtomSV.installObj(prodAttrLog);
			}
		}
	}

	@Override
	public int updateMarketPrice(MarketPriceUpdate marketPrice) {
		
		long querystandedProdStart = System.currentTimeMillis();
		logger.info("####loadtest####开始执行standedProductAtomSV.selectById，查询租户下的某个标准品,当前时间戳：" + querystandedProdStart);
		
		StandedProduct standedProduct = standedProductAtomSV.selectById(marketPrice.getTenantId(),
				marketPrice.getProductId());
		
		long querystandedProdEnd = System.currentTimeMillis();
		logger.info("####loadtest####结束调用standedProductAtomSV.selectById，查询租户下的某个标准品,当前时间戳：" + querystandedProdEnd + ",用时:"
				+ (querystandedProdEnd - querystandedProdStart) + "毫秒");
		
		
		// 判断此租户下是否存在次标准品
		if (standedProduct == null){
			throw new BusinessException("",
					"找不到指定的租户=" + marketPrice.getTenantId() + "下的标准品=" + marketPrice.getProductId() + "信息");
		}
		// 判断商户ID是否为传入的商户ID
		if (!marketPrice.getSupplierId().equals(standedProduct.getSupplierId())){
			throw new BusinessException("",
					"标准品所属商户ID:" + standedProduct.getSupplierId() + "与当前商户ID:" + marketPrice.getSupplierId() + "不一致!");
		}

		long updateMarketPriceStart = System.currentTimeMillis();
		logger.info("####loadtest####开始执行standedProductAtomSV.updateMarketPrice，更新市场价格信息,当前时间戳：" + updateMarketPriceStart);
		
		// 更新市场价格信息
		int count = standedProductAtomSV.updateMarketPrice(marketPrice.getTenantId(), marketPrice.getProductId(),
				marketPrice.getMarketPrice(), marketPrice.getOperId());
		
		long updateMarketPriceEnd = System.currentTimeMillis();
		logger.info("####loadtest####结束调用standedProductAtomSV.updateMarketPrice，更新市场价格信息,当前时间戳：" + updateMarketPriceEnd + ",用时:"
				+ (updateMarketPriceEnd - updateMarketPriceStart) + "毫秒");
		
		if (count > 0) {
			standedProduct.setMarketPrice(marketPrice.getMarketPrice());
			// 更行标准品日志
			StandedProductLog standedProductLog = new StandedProductLog();
			BeanUtils.copyProperties(standedProductLog, standedProduct);
			//standedProductLogAtomSV.insert(standedProductLog);
			// 更新价格类日志
			ProdPriceLog prodPriceLog = new ProdPriceLog();
			BeanUtils.copyProperties(prodPriceLog, standedProduct);
			// 设置对象类型为标准品-SA
			prodPriceLog.setObjType("SA");
			prodPriceLog.setObjId(standedProduct.getStandedProdId());
			prodPriceLog.setUpdatePrice(standedProduct.getMarketPrice());
			
			long insertPriceStart = System.currentTimeMillis();
			logger.info("####loadtest####开始执行prodPriceLogAtomSV.insert，添加价格类日志,当前时间戳：" + insertPriceStart);
			
			prodPriceLogAtomSV.insert(prodPriceLog);
			
			long insertPriceEnd = System.currentTimeMillis();
			logger.info("####loadtest####结束调用prodPriceLogAtomSV.insert，添加价格类日志,当前时间戳：" + insertPriceEnd + ",用时:"
					+ (insertPriceEnd - insertPriceStart) + "毫秒");
			
		}

		return count;
	}

	/**
	 * 分页查询标准品及数据信息-添加商场商品销售价页面
	 */
	@Override
	public PageInfoResponse<NormProdResponse> queryNormProductForSalePrice(NormProdRequest productRequest) {
		StandedProdPageQueryVo standedProdPageQueryVo = new StandedProdPageQueryVo();
		BeanUtils.copyProperties(standedProdPageQueryVo, productRequest);
		standedProdPageQueryVo.setProductId(productRequest.getStandedProdId());
		standedProdPageQueryVo.setProductName(productRequest.getStandedProductName());
		// 获取分页查询的标准品信息
		PageInfo<StandedProduct> standedProductPage = standedProductAtomSV.queryForPage(standedProdPageQueryVo);
		List<StandedProduct> standedProductList = standedProductPage.getResult();
		// 获取查询到的条目数
		int count = standedProductPage.getCount();
		// 返回类型的Result属性
		List<NormProdResponse> NormProdResponseList = new ArrayList<>();
		for (StandedProduct standedProduct : standedProductList) {
			NormProdResponse normProdResponse = new NormProdResponse();
			BeanUtils.copyProperties(normProdResponse, standedProduct);
			// 获取类目名称
			String catName = catDefAtomSV.selectById(standedProduct.getTenantId(), standedProduct.getProductCatId())
					.getProductCatName();
			normProdResponse.setCatName(catName);
			// 获取库存组数量
			normProdResponse.setStorageGroupNum(storageGroupAtomSV.countStorGroupByProdID(standedProduct.getTenantId(),
					productRequest.getSupplierId(), standedProduct.getStandedProdId()));
			NormProdResponseList.add(normProdResponse);
		}
		// 新建返回对象
		PageInfoResponse<NormProdResponse> normProdResponseList = new PageInfoResponse<>();
		// 结果集赋值
		normProdResponseList.setResult(NormProdResponseList);
		normProdResponseList.setPageNo(productRequest.getPageNo());
		normProdResponseList.setPageSize(productRequest.getPageSize());
		normProdResponseList.setCount(count);
		return normProdResponseList;
	}

	/**
	 * 查询标准品信息,包括关键属性
	 *
	 * @param productRequest
	 * @return
	 */
	@Override
	public PageInfoResponse<NormProdAndKeyAttrRes> queryProdAndKeyAttr(NormProdRequest productRequest) {
		// 查询出所有符合条件的标准品信息
		StandedProdPageQueryVo pageQueryVo = new StandedProdPageQueryVo();
		BeanUtils.copyProperties(pageQueryVo, productRequest);
		pageQueryVo.setProductId(productRequest.getStandedProdId());
		pageQueryVo.setProductName(productRequest.getStandedProductName());
		// 查询结果
		PageInfo<StandedProduct> productPageInfo = standedProductAtomSV.queryForPage(pageQueryVo);
		// 接口输出接口
		PageInfoResponse<NormProdAndKeyAttrRes> normProdPageInfo = new PageInfoResponse<NormProdAndKeyAttrRes>();
		BeanUtils.copyProperties(normProdPageInfo, productPageInfo);
		// 添加结果集
		List<StandedProduct> productList = productPageInfo.getResult();
		List<NormProdAndKeyAttrRes> normProductList = new ArrayList<NormProdAndKeyAttrRes>();
		normProdPageInfo.setResult(normProductList);
		for (StandedProduct standedProduct : productList) {
			NormProdAndKeyAttrRes normProduct = new NormProdAndKeyAttrRes();
			BeanUtils.copyProperties(normProduct, standedProduct);
			normProduct.setProductId(standedProduct.getStandedProdId());
			normProduct.setProductName(standedProduct.getStandedProductName());
			// 填充关键属性信息
			normProduct.setKeyAttrMap(getAttrVal(productRequest.getTenantId(), standedProduct.getProductCatId(),
					standedProduct.getStandedProdId(), ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_KEY));
			normProductList.add(normProduct);
		}
		return normProdPageInfo;

	}

	/**
	 * 获取属性对应的属性值信息
	 *
	 * @return K:属性标识;V:属性值集合
	 */
	private Map<Long, List<AttrValInfo>> getAttrVal(String tenantId, String catId, String standedProdId,
			String attrType) {
		// 查询对应类目属性
		List<ProdCatAttrAttch> catAttrAttches = catAttrAttachAtomSV.queryAttrOfByIdAndType(tenantId, catId, attrType);
		Map<Long, List<AttrValInfo>> attAndValMap = new HashMap<>();
		// 查询标准品对应属性的属性值
		for (ProdCatAttrAttch catAttrAttch : catAttrAttches) {
			List<AttrValInfo> valInfoList = new ArrayList<>();
			// 查询属性值
			List<StandedProdAttr> prodAttrs = standedProdAttrAtomSV.queryAttrVal(tenantId, standedProdId,
					catAttrAttch.getAttrId());
			for (StandedProdAttr prodAttr : prodAttrs) {
				AttrValInfo valDef = new AttrValInfo();
				BeanUtils.copyProperties(valDef, prodAttr);
				valDef.setProductAttrValId(prodAttr.getStandedProdAttrId());
				valDef.setProductId(prodAttr.getStandedProdId());
				valDef.setAttrValId(prodAttr.getAttrvalueDefId());
				valDef.setAttrVal(prodAttr.getAttrValueName());
				valDef.setAttrVal2(prodAttr.getAttrValueName2());
				if (prodAttr.getAttrvalueDefId() != null) {
					ProdAttrvalueDef attrvalueDef = attrValDefAtomSV.selectById(tenantId, prodAttr.getAttrvalueDefId());
					if (attrvalueDef != null){
						valDef.setAttrVal(attrvalueDef.getAttrValueName());
					}
				}
				valInfoList.add(valDef);
			}
			attAndValMap.put(catAttrAttch.getAttrId(), valInfoList);
		}
		return attAndValMap;
	}

}
