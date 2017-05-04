package com.ai.slp.product.service.business.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.paas.ipaas.search.vo.Result;
import com.ai.paas.ipaas.search.vo.SearchCriteria;
import com.ai.paas.ipaas.search.vo.SearchOption;
//github.com/AI-OPT/slp-product.git
import com.ai.slp.product.api.normproduct.param.AttrValRequest;
import com.ai.slp.product.api.product.param.SkuAttrInfo;
import com.ai.slp.product.api.product.param.SkuAttrVal;
import com.ai.slp.product.api.product.param.SkuAttrValInfo;
import com.ai.slp.product.api.product.param.SkuInfo;
import com.ai.slp.product.api.product.param.SkuInfoMultSave;
import com.ai.slp.product.api.product.param.SkuSetForProduct;
import com.ai.slp.product.api.webfront.param.ProdAttrParam;
import com.ai.slp.product.api.webfront.param.ProdAttrValue;
import com.ai.slp.product.api.webfront.param.ProductImage;
import com.ai.slp.product.api.webfront.param.ProductSKUResponse;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.constants.ErrorCodeConstants;
import com.ai.slp.product.constants.ProductCatConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.constants.SearchFieldConfConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.attach.ProdCatAttrAttch;
import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDef;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttr;
import com.ai.slp.product.dao.mapper.bo.StandedProdAttr;
import com.ai.slp.product.dao.mapper.bo.product.ProdAttr;
import com.ai.slp.product.dao.mapper.bo.product.ProdPicture;
import com.ai.slp.product.dao.mapper.bo.product.ProdSku;
import com.ai.slp.product.dao.mapper.bo.product.ProdSkuAttr;
import com.ai.slp.product.dao.mapper.bo.product.ProdSkuLog;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.storage.SkuStorage;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.dao.mapper.bo.storage.StorageLog;
import com.ai.slp.product.search.bo.AttrInfo;
import com.ai.slp.product.search.bo.ImageInfo;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.service.atom.interfaces.IProdAttrValDefAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAttachAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProdAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.comment.IProdCommentAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdPictureAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSaleAllAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.ISkuStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageLogAtomSV;
import com.ai.slp.product.service.business.impl.search.ProductSearchImpl;
import com.ai.slp.product.service.business.interfaces.INormProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IProdSkuBusiSV;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageNumBusiSV;
import com.ai.slp.product.service.business.interfaces.search.IProductSearch;
import com.ai.slp.product.util.DataUtils;
import com.ai.slp.product.vo.ProdSkuAttrStr;
import com.ai.slp.product.vo.SkuStorageVo;

/**
 * 商品SKU操作 Created by jackieliu on 16/5/12.
 */
@Service
@Transactional
public class ProdSkuBusiSVImpl implements IProdSkuBusiSV {
	private static final Logger logger = LoggerFactory.getLogger(ProdSkuBusiSVImpl.class);
	@Autowired
	IProductAtomSV productAtomSV;
	@Autowired
	IProductBusiSV productBusiSV;
	@Autowired
	IProdCatAttrAtomSV prodCatAttrAtomSV;
	@Autowired
	IProdSkuAtomSV prodSkuAtomSV;
	@Autowired
	IProdSkuLogAtomSV prodSkuLogAtomSV;
	@Autowired
	IProdCatAttrAttachAtomSV catAttrAttachAtomSV;
	@Autowired
	IStandedProdAttrAtomSV standedProdAttrAtomSV;
	@Autowired
	IProdSkuAttrAtomSV prodSkuAttrAtomSV;
	@Autowired
	ISkuStorageAtomSV skuStorageAtomSV;
	@Autowired
	IStorageAtomSV storageAtomSV;
	@Autowired
	IStorageGroupAtomSV storageGroupAtomSV;
	@Autowired
	IStorageLogAtomSV storageLogAtomSV;
	@Autowired
	IStorageBusiSV storageBusiSV;
	@Autowired
	IProdPictureAtomSV pictureAtomSV;
	@Autowired
	IProdSaleAllAtomSV prodSaleAllAtomSV;
	@Autowired
	IStorageNumBusiSV storageNumBusiSV;
	@Autowired
	INormProductBusiSV normProductBusiSV;
	@Autowired
	IProdAttrValDefAtomSV attrValDefAtomSV;
	@Autowired
	IProdCommentAtomSV prodCommentAtomSV;
	@Autowired
	IProdAttrAtomSV prodAttrAtomSV;

	private static List<String> ACTIVE_STATUS_LIST = new ArrayList<>();

	static {
		ACTIVE_STATUS_LIST.add(ProductConstants.Product.State.IN_SALE);
		ACTIVE_STATUS_LIST.add(ProductConstants.Product.State.SALE_OUT);
	}


	/**
	 * 产生库存组对应商品的SKU 完全使用配置到标准品的销售属性
	 *
	 *
	 */
	@Override
	public int createSkuOfProduct(String tenantId, String groupId, List<AttrValRequest> attrValList, Long operId) {
		StorageGroup group = checkBefUpdateSkuByGroupId(tenantId, groupId);
		// 查询商品信息
		Product product = productAtomSV.queryProductByGroupId(tenantId, group.getStorageGroupId());
		// 查询类目下销售属性信息
		List<ProdCatAttr> catAttrList = prodCatAttrAtomSV.queryAttrOfCatByIdAndType(tenantId, product.getProductCatId(),
				ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_SALE,"ATTR_ID");
		// 创建所有sku组合
		if (catAttrList != null && catAttrList.size() > 0) {
			return createSkuProduct(attrValList, group, product, catAttrList, operId);
		} else {
			return 0;
		}
	}

	/**
	 * 更改sku前检查
	 * 
	 * @param tenantId
	 * @param groupId
	 * @return
	 * @author jiaxs
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL
	 */
	private StorageGroup checkBefUpdateSkuByGroupId(String tenantId, String groupId) {
		if (StringUtil.isBlank(groupId) || StringUtil.isBlank(tenantId)) {
			logger.warn("tenantId、groupId不能为空");
			throw new BusinessException("", "tenantId、groupId不能为空");
		}
		StorageGroup group = storageGroupAtomSV.queryByGroupId(tenantId, groupId);
		if (group == null) {
			logger.warn("库存组不能为空");
			throw new BusinessException("", "库存组不能为空");
		}
		// 只有在库存组停用时,才允许变更SKU.
		if (!StorageConstants.StorageGroup.State.STOP.equals(group.getState())) {
			logger.warn("库存组不是停用状态,不允许更新SKU信息,租户ID:{},库存组标识:{}.", tenantId, group.getStorageGroupId());
			throw new BusinessException("", "库存组不是停用状态,不允许更新SKU信息");
		}
		return group;
	}

	@Override
	public int createSkuOfAttrValue(String tenantId, String groupId, Map<Long, List<String>> attrValMap, Long operId) {
		if(attrValMap==null || attrValMap.size()==0){
			return 0;
		}
		StorageGroup group = checkBefUpdateSkuByGroupId(tenantId, groupId);
		// 查询商品信息
		Product product = productAtomSV.queryProductByGroupId(tenantId, group.getStorageGroupId());
		// 查询类目下销售属性信息
		List<ProdCatAttr> catAttrList = prodCatAttrAtomSV.queryAttrOfCatByIdAndType(tenantId, product.getProductCatId(),
				ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_SALE,"ATTR_ID");
		// 创建所有sku组合
		if (catAttrList != null && catAttrList.size() > 0) {
			return createSkuProduct(group, product, catAttrList, attrValMap, operId);
		} else {
			return 0;
		}
	}

	/**
	 * 创建sku
	 * 
	 * @param attrValList
	 * @param group
	 * @param product
	 * @param catAttrList
	 * @return 添加的条数
	 * @author jiaxs
	 */
	private int createSkuProduct(List<AttrValRequest> attrValList, StorageGroup group, Product product,
			List<ProdCatAttr> catAttrList, Long operId) {
		Map<Long, List<String>> attrAndValMap = getAttrAndValMap(attrValList);
		return createSkuProduct(group, product, catAttrList, attrAndValMap, operId);
	}

	/**
	 * 保存SKU
	 * @param product
	 * @return
	 * @author Gavin
	 * @UCUSER
	 */
	public ProdSku createSkuProduct(Product product){
		ProdSku prodSku = new ProdSku();
		prodSku.setTenantId(product.getTenantId());
		prodSku.setProdId(product.getProdId());
		prodSku.setStorageGroupId(product.getStorageGroupId());
		prodSku.setSkuName(product.getProdName());
		prodSku.setSerialNumber((short) 0);
		prodSku.setState(ProductConstants.ProdSku.State.ACTIVE);
		prodSku.setOperId(product.getOperId());
		prodSku.setOperTime(product.getOperTime());
		//标准品/商品/sku共用主键
		prodSku.setSkuId(product.getProdId());
		prodSkuAtomSV.createObj(prodSku);
		return prodSku;
	}
	
	/**
	 * 保存SKU
	 * @param product
	 * @param attrValList
	 * @return
	 * @author Gavin
	 * @UCUSER
	 */
	public ProdSku createSkuProduct(Product product, List<AttrValRequest> attrValList ){
		ProdSku prodSku = createSkuProduct(product);
		//创建属性
		for (AttrValRequest attrValReq : attrValList) {
			ProdSkuAttr prodAttr = new ProdSkuAttr();
			BeanUtils.copyProperties(prodAttr, attrValReq);
			prodAttr.setTenantId(product.getTenantId());
			prodAttr.setProdId(product.getProdId());
			prodAttr.setAttrvalueDefId(attrValReq.getAttrValId());
			prodAttr.setState(CommonConstants.STATE_ACTIVE);// 设置为有效
			prodAttr.setOperId(product.getOperId());
			prodAttr.setOperTime(product.getOperTime());
			// 添加成功
			prodSkuAttrAtomSV.createAttr(prodAttr);
		}
		return prodSku;
	}
	
	
	/**
	 * 根据属性集合创建sku
	 * 
	 * @param group
	 * @param product
	 * @param catAttrList
	 * @param attrAndValMap
	 * @return
	 */
	private int createSkuProduct(StorageGroup group, Product product, List<ProdCatAttr> catAttrList,
			Map<Long, List<String>> attrAndValMap, Long operId) {
		int count = 0;
		Set<String> skuSaleAttrs = new HashSet<>();// 新SKU属性串集合
		// 参数属性值的所有SKU组合
		genSkuSalAttr(attrAndValMap, "", 0, skuSaleAttrs, catAttrList);
		if (skuSaleAttrs.size() > 0) {
			for (String saleAttrs : skuSaleAttrs) {
				ProdSku prodSku = new ProdSku();
				prodSku.setTenantId(product.getTenantId());
				prodSku.setProdId(product.getProdId());
				prodSku.setStorageGroupId(group.getStorageGroupId());
				prodSku.setSkuName(product.getProdName());
				prodSku.setSaleAttrs(saleAttrs);
				prodSku.setIsSaleAttr(ProductConstants.ProdSku.IsSaleAttr.YES);
				prodSku.setSerialNumber((short) 0);
				prodSku.setState(ProductConstants.ProdSku.State.ACTIVE);
				prodSku.setOperId(operId);
				prodSku.setOperTime(DateUtil.getSysDate());
				if (prodSkuAtomSV.createObj(prodSku) > 0) {
					count++;
					if (!StringUtil.isBlank(saleAttrs)) {
						String[] salAttrValueArray = saleAttrs.split(ProductConstants.ProdSku.SaleAttrs.ATTR_SPLIT);
						for (String salAttrValue : salAttrValueArray) {
							String[] attrValueArray = salAttrValue
									.split(ProductConstants.ProdSku.SaleAttrs.ATTRVAL_SPLIT);
							ProdSkuAttr prodSkuAttr = new ProdSkuAttr();
							prodSkuAttr.setAttrId(DataUtils.getLongVal(attrValueArray[0]));
							prodSkuAttr.setAttrvalueDefId(attrValueArray[1]);
							prodSkuAttr.setState(ProductConstants.ProdSkuAttr.State.ACTIVE);
							prodSkuAttr.setOperId(prodSku.getOperId());
							prodSkuAttr.setProdId(prodSku.getProdId());
							prodSkuAttr.setSkuId(prodSku.getSkuId());
							prodSkuAttr.setTenantId(prodSku.getTenantId());
							int createAttr = prodSkuAttrAtomSV.createAttr(prodSkuAttr);
							if (createAttr == 0) {
								return 0;
							}
						}
					}
				}
			}
		}
		return count;
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
				List<String> valuelist = attrAndValMap.get(attrId);
				if (valuelist == null) {
					valuelist = new LinkedList<String>();
				}
				valuelist.add(attrVal.getAttrValId());
				if (!attrAndValMap.containsKey(attrId)) {
					attrAndValMap.put(attrId, valuelist);
				}
			}
		}
		return attrAndValMap;
	}

	/**
	 * 更新商品SKU信息
	 *
	 * @param saveInfo
	 */
	@Override
	public void updateSkuOfProduct(SkuInfoMultSave saveInfo) {
		String tenantId = saveInfo.getTenantId(), productId = saveInfo.getProdId();
		Product product = productAtomSV.selectByProductId(productId);
		if (product == null) {
			logger.warn("未找到指定商品,租户ID{},商品标识{}:" + tenantId + "," + productId);
			throw new BusinessException("", "未找到指定商品,租户ID:" + tenantId + ",商品标识:" + productId);
		}
		StorageGroup group = storageGroupAtomSV.queryByGroupIdAndSupplierId(tenantId, saveInfo.getSupplierId(),
				product.getStorageGroupId());
		if (group == null) {
			logger.warn("未找到指定商品,租户ID{},库存组标识{}:" + tenantId + "," + product.getStorageGroupId());
			throw new BusinessException("", "未找到指定库存组,租户ID:" + tenantId + ",库存组标识:" + product.getStorageGroupId());
		}
		// 只有在库存组停用时,才允许变更SKU.
		if (!StorageConstants.StorageGroup.State.STOP.equals(group.getState())) {
			logger.warn("库存组不是停用状态,不允许更新SKU信息,租户ID:{},库存组标识:{}.", tenantId, product.getStorageGroupId());
			throw new BusinessException("", "库存组不是停用状态,不允许更新SKU信息");
		}
		// 查询商品的销售属性集合,序号正序
		Map<Long, List<String>> attrAndValMap = saveInfo.getAttrAndValIdMap();
		// 查询类目下指定类型的属性信息
		List<ProdCatAttr> catAttrList = prodCatAttrAtomSV.queryAttrOfCatByIdAndType(tenantId, product.getProductCatId(),
				ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_SALE,"ATTR_ID");
		if (attrAndValMap.size() != catAttrList.size()){
			throw new BusinessException("",
					"已选择销售属性数量与实际数量不符,已选择属性数量:" + attrAndValMap.size() + ",实际属性数量:" + catAttrList.size());
		}
		// 参数属性值的所有SKU组合
		Set<String> skuSaleAttrs = new HashSet<>();// 新SKU属性串集合
		genSkuSalAttr(attrAndValMap, "", 0, skuSaleAttrs, catAttrList);
		// 查询商品的所有SKU信息
		List<ProdSku> prodSkuList = prodSkuAtomSV.querySkuOfProd(tenantId, productId);
		// 遍历商品已有SKU信息,确认是否在新的SKU组合中
		for (ProdSku prodSku : prodSkuList) {
			// 若不包含,则进行废弃
			if (!skuSaleAttrs.contains(prodSku.getSaleAttrs())) {
				discardSku(prodSku, saveInfo.getOperId());
				continue;
			}
			skuSaleAttrs.remove(prodSku.getSaleAttrs());
		}
		// 若新添加SKU,则需要废除之前所有库存.
		if (!CollectionUtil.isEmpty(skuSaleAttrs)) {
			// 查询库存组下库存
			List<Storage> storageList = storageAtomSV.queryOfGroup(tenantId, group.getStorageGroupId(),false);
			for (Storage storage : storageList) {
				storageBusiSV.discardStorage(tenantId, storage, saveInfo.getOperId(), true);
			}
		}
	}

	/**
	 * 查询指定商品下的SKU信息
	 *
	 * @param tenantId
	 * @param productId
	 * @return
	 */
	@Override
	public SkuSetForProduct querySkuByProdId(String tenantId, String supplierId, String productId) {
		// 查询商品信息
		Product product = productAtomSV.selectByProductId(tenantId, supplierId, productId);
		if (product == null) {
			logger.warn("未找到指定商品,租户ID{},商品标识{}:" + tenantId + "," + productId);
			throw new BusinessException("", "查询商品信息不存在,租户ID:" + tenantId + ",商品标识:" + productId);
		}
		return querySkuByProdId(tenantId, product);
	}

	public List<ProdCatAttrAttch> querySkuDetail(String tenantId, Product product, String skuAttrs) {
		ProductSKUResponse skuResponse = new ProductSKUResponse();
		BeanUtils.copyProperties(skuResponse, product);
		// 查询商品对应标准品的销售属性,已按照属性属性排序
		List<ProdCatAttrAttch> catAttrAttches = catAttrAttachAtomSV.queryAttrOfByIdAndType(tenantId,product.getProductCatId(), ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_SALE);
		return catAttrAttches;
	}

	/**
	 * 根据SKU标识或SKU属性串查询SKU的所有属性信息
	 *
	 * @param tenantId
	 * @param skuId
	 * @param skuAttrs
	 * @return
	 */
	@Override
	public List<AttrInfo> querySkuAttr(String tenantId, String skuId, String skuAttrs) {
		return prodAttrAtomSV.queryAttrOfProdId(skuId);
	}

	/**
	 * 查询库组下SKU的信息
	 *
	 * @param tenantId
	 * @param supplierId
	 * @param groupId
	 * @return
	 */
	@Override
	public SkuSetForProduct querySkuByStoGroupId(String tenantId, String supplierId, String groupId) {
		// 查看库组对应的销售商品
		Product product = productAtomSV.selectByGroupId(tenantId, groupId);
		if (product == null || !supplierId.equals(product.getSupplierId())) {
			throw new BusinessException("", "未查到对应的商品信息");
		}
		return querySkuByProdId(tenantId, product);
	}

	private SkuSetForProduct querySkuByProdId(String tenantId, Product product) {
		Map<Long, Short> attrSn = new HashMap<>();
		SkuSetForProduct skuSetForProduct = querySalAttr(tenantId,product.getProductCatId(),attrSn);
		skuSetForProduct.setProdId(product.getProdId());
		skuSetForProduct.setStorageGroupId(product.getStorageGroupId());
		// 属性值 k:属性值标识; v:属性值名称
		Map<String, String> valInfoMap = new HashMap<>();
		List<SkuInfo> skuInfoList = new ArrayList<>();
		// 查询所有单品信息
		List<ProdSku> skuList = prodSkuAtomSV.querySkuOfProd(tenantId, product.getProdId());
		if (!CollectionUtil.isEmpty(skuSetForProduct.getAttrInfoList()) && CollectionUtil.isEmpty(skuList)){
			throw new BusinessException("", "该商品未设置SKU单品信息");
		}
		// 设置SKU单品信息集合
		for (ProdSku sku : skuList) {
			// 设置属性串和SKU标识
			SkuInfo skuInfo = new SkuInfo();
			BeanUtils.copyProperties(skuInfo, sku);
			skuInfoList.add(skuInfo);
			skuInfo.setValForSkuList(genSkuAttrVal(product, sku.getSkuId(), valInfoMap, attrSn));
		}
		skuSetForProduct.setSkuInfoList(skuInfoList);
		return skuSetForProduct;
	}

	private SkuSetForProduct querySalAttr(String tenantId,String catId,Map<Long, Short> attrSn){
		List<String> attrIdSnList = new ArrayList<>();
		// 查询商品对应标准品的销售属性,已按照属性排序
		List<ProdCatAttrAttch> catAttrAttches = catAttrAttachAtomSV.queryAttrOfByIdAndType(tenantId,
				catId, ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_SALE);
		// 查询商品的SKU信息
		SkuSetForProduct skuSetForProduct = new SkuSetForProduct();
		List<SkuAttrInfo> attrInfoList = new ArrayList<>();
		// 查询销售属性信息
		for (ProdCatAttrAttch attrAttch : catAttrAttches) {
			attrIdSnList.add(Long.toString(attrAttch.getAttrId()));
			attrSn.put(attrAttch.getAttrId(), attrAttch.getSerialNumber());
			// 设置属性对象集合
			SkuAttrInfo skuAttrInfo = new SkuAttrInfo();
			BeanUtils.copyProperties(skuAttrInfo, attrAttch);
			attrInfoList.add(skuAttrInfo);
		}
		skuSetForProduct.setAttrInfoList(attrInfoList);
		return skuSetForProduct;
	}

	/**
	 * 返回下级属性的属性值的跨行数
	 *
	 * @param entryIterator
	 * @return
	 */
	private int getAttrRowspan(Iterator<Map.Entry<SkuAttrInfo, List<SkuAttrValInfo>>> entryIterator) {
		Map.Entry<SkuAttrInfo, List<SkuAttrValInfo>> entry = entryIterator.next();
		SkuAttrInfo skuAttrInfo = entry.getKey();
		int valNum = entry.getValue().size();
		int rowspan = 1;
		if (entryIterator.hasNext()){
			rowspan = getAttrRowspan(entryIterator);
		}
		skuAttrInfo.setRowspan(rowspan);
		return rowspan * valNum;
	}

	/**
	 * 根据属性值,完善SKU字符串
	 *
	 * @param attrAndValMap
	 * @param skuInfo
	 * @param attrIndex
	 * @param skuSalInfo
	 * @param catAttrList
	 */
	private void genSkuSalAttr(Map<Long, List<String>> attrAndValMap, String skuInfo, int attrIndex,
			Set<String> skuSalInfo, List<ProdCatAttr> catAttrList) {
		if (attrIndex == catAttrList.size()) {
			skuSalInfo.add(skuInfo);
			return;
		}
		ProdCatAttr catAttr = catAttrList.get(attrIndex);
		Long attrId = catAttr.getAttrId();
		List<String> valList = attrAndValMap.get(attrId);
		if(valList == null || valList.size()==0){
			return;
		}
		// 拼装sku属性串
		String newSkuAttr = null;
		if (StringUtil.isBlank(skuInfo)) {
			newSkuAttr = attrId	+ ProductConstants.ProdSku.SaleAttrs.ATTRVAL_SPLIT;
		} else {
			newSkuAttr = skuInfo + ProductConstants.ProdSku.SaleAttrs.ATTR_SPLIT + attrId
					+ ProductConstants.ProdSku.SaleAttrs.ATTRVAL_SPLIT;
		}
		for (String val : valList) {
			String skuAttrVal = newSkuAttr + val;
			genSkuSalAttr(attrAndValMap, skuAttrVal, attrIndex + 1, skuSalInfo, catAttrList);
		}
	}

	/**
	 * 删除指定SKU
	 *
	 * @param prodSku
	 */
	private void discardSku(ProdSku prodSku, Long operId) {
		// 将SKU单品进行废弃
		prodSku.setOperId(operId);
		prodSku.setState(ProductConstants.ProdSku.State.INACTIVE);
		if (prodSkuAtomSV.updateSkuById(prodSku) > 0) {
			ProdSkuLog prodSkuLog = new ProdSkuLog();
			BeanUtils.copyProperties(prodSkuLog, prodSku);
			prodSkuLogAtomSV.install(prodSkuLog);
		}
		// 将SKU单品对应属性值进行废弃
		prodSkuAttrAtomSV.discardAttrOfSku(prodSku.getTenantId(), prodSku.getSkuId(), operId);
		// 查询SKU对应的SKU库存信息
		List<SkuStorage> storageList = skuStorageAtomSV.queryOfSku(prodSku.getSkuId());
		// 对SKU库存进行废弃,并减少对应库存的库存量
		for (SkuStorage skuStorage : storageList) {
			Storage storage = storageAtomSV.queryNoDiscardById(skuStorage.getStorageId());
			if (storage == null) {
				continue;
			}
			long totalNum = storage.getTotalNum();
			long usableNum = storage.getUsableNum();
			if (totalNum > 0){
				storage.setTotalNum(totalNum - skuStorage.getTotalNum());
			}
			if (usableNum > 0){
				storage.setUsableNum(usableNum - skuStorage.getUsableNum());
			}
			storage.setOperId(operId);
			if (storageAtomSV.updateById(storage) > 0) {
				StorageLog storageLog = new StorageLog();
				BeanUtils.copyProperties(storageLog, storage);
				storageLogAtomSV.installLog(storageLog);
			}
			// 可用量为零,则需要自动停用
			if (storage.getUsableNum() <= 0){
				storageBusiSV.autoStopStorage(storage);
			}
			skuStorageAtomSV.discardById(skuStorage.getSkuStorageId(), operId);
		}

	}

	/**
	 * 根据SkuId或属性串查询SKU信息
	 * 
	 * @param tenantId
	 * @param skuId
	 * @param skuAttrs
	 * @return
	 */
	private ProdSku selectSkuBySkuIdOrAttrs(String tenantId, String skuId, String skuAttrs) {
		ProdSku prodSku = null;
		// 通过SKU标识查询
		if (StringUtils.isNotBlank(skuId)) {
			prodSku = prodSkuAtomSV.querySkuById(tenantId, skuId);
		}
		// 通过属性串查询
		/*else if (StringUtils.isNotBlank(skuAttrs)) {
			String sortSkuAttrs = skuAttrStrSort(skuAttrs);
			prodSku = prodSkuAtomSV.querySkuByAttrs(tenantId, sortSkuAttrs);
		}*/
		if (prodSku == null) {
			logger.warn("未查询到指定的SKU信息,租户ID:{},SKU标识:{},SKU属性串:{}", tenantId, skuId, skuAttrs);
			throw new BusinessException(ErrorCodeConstants.Product.SKU_NO_EXIST,"未查询到指定的SKU信息,租户ID:" + tenantId + ",SKU标识:" + skuId + "SKU属性串:" + skuAttrs);
		}
		return prodSku;
	}

	private ProductSKUResponse genSkuResponse(String tenantId, Product product) {
		ProductSKUResponse skuResponse = new ProductSKUResponse();
		BeanUtils.copyProperties(skuResponse, product);
		// 查询商品对应标准品的销售属性,已按照属性属性排序
		List<ProdCatAttrAttch> catAttrAttches = catAttrAttachAtomSV.queryAttrOfByIdAndType(tenantId,product.getProductCatId(), ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_SALE);
		// SKU图片
		String attrPic = null;
		// 查询已设置SKU的属性和属性值信息
		for (ProdCatAttrAttch prodCatAttrAttch : catAttrAttches) {
			// 查询属性对应属性值集合
			List<ProdAttr> prodAttrs = prodAttrAtomSV.queryOfProdAndAttr(tenantId, product.getProdId(), prodCatAttrAttch.getAttrId());
			
			List<ProdAttrValue> attrValueList = new ArrayList<>();
			ProdAttrParam prodAttrParam = new ProdAttrParam();
			for (ProdAttr prodAttr : prodAttrs) {
			ProdAttrValue prodAttrValue = new ProdAttrValue();
			prodAttrValue.setAttrvalueDefId(prodAttr.getAttrvalueDefId());
			prodAttrValue.setAttrValueName(prodAttr.getAttrValueName());
			attrValueList.add(prodAttrValue);
			// 若此属性是否包含图片
			if (ProductCatConstants.ProductCatAttr.IsPicture.YES.equals(prodCatAttrAttch.getIsPicture())) {
				// 查询主图
				ProdPicture prodPicture = pictureAtomSV.queryMainOfProdIdAndAttrVal(product.getProdId(),prodAttr.getAttrvalueDefId());
				
				if (prodPicture != null) {
					ProductImage productImage = new ProductImage();
					BeanUtils.copyProperties(productImage, prodPicture);
					prodAttrValue.setImage(productImage);
				}
			}
			attrValueList.add(prodAttrValue);
			}
			prodAttrParam.setAttrValueList(attrValueList);
			skuResponse.setProductAttrList(attrValueList);
		}
		// 设置主图
		skuResponse.setProductImageList(getProductSkuPic(attrPic, product));
		// 设置评论数
		skuResponse.setCommentNum((long)prodCommentAtomSV.countBySkuId(product.getProdId(),false));
		// 设置商品销量
		skuResponse.setSaleNum(prodSaleAllAtomSV.queryNumOfProduc(tenantId, product.getProdId()));
		
		// 获取当前库存和价格
		//目前查询不到
		SkuStorageVo skuStorageVo = storageNumBusiSV.queryStorageOfSku(tenantId, product.getProdId());
		skuResponse.setUsableNum(skuStorageVo.getUsableNum());
		skuResponse.setSalePrice(skuStorageVo.getSalePrice());
		return skuResponse;
	}

	/**
	 * 获取SKU商品的展示图片
	 */
	private List<ProductImage> getProductSkuPic(String attrPic, Product product) {
		List<ProdPicture> pictureList = null;
		List<ProductImage> productImageList = new ArrayList<>();
		if (StringUtils.isNotBlank(attrPic)) {
			pictureList = pictureAtomSV.queryProdIdAndAttrVal(product.getProdId(), attrPic);
		} else{
			pictureList = pictureAtomSV.queryPicOfProd(product.getProdId());
		}
		for (ProdPicture picture : pictureList) {
			ProductImage productImage = new ProductImage();
			BeanUtils.copyProperties(productImage, picture);
			// 将主图放在第一个位置
			if (ProductConstants.ProdPicture.IsMainPic.YES.equals(picture.getIsMainPic())) {
				productImageList.add(0, productImage);
			} else{
				productImageList.add(productImage);
			}
		}
		return productImageList;
	}

	private List<SkuAttrVal> genSkuAttrVal(Product product, String skuId,
			Map<String, String> valInfoMap, Map<Long, Short> attrSn) {
		String tenantId = product.getTenantId();
		// 获取所有的属性信息
		List<ProdSkuAttr> skuAttrList = prodSkuAttrAtomSV.queryBySkuId(tenantId, skuId,true);
		List<SkuAttrVal> valForSkus = new ArrayList<>();
		for (ProdSkuAttr skuAttr : skuAttrList) {
			String valName = valInfoMap.get(skuAttr.getAttrvalueDefId());
			if (StringUtils.isBlank(valName)) {
				StandedProdAttr prodAttr = standedProdAttrAtomSV.queryByProdIdAndAttrValId(tenantId,
						product.getStandedProdId(), skuAttr.getAttrvalueDefId());
				if (prodAttr != null) {
					valName = prodAttr.getAttrValueName();
				}
				//若标准品属性值不存在或无效,则查询属性值定义信息
				if (StringUtils.isBlank(valName)){
					ProdAttrvalueDef attrvalueDef = attrValDefAtomSV.selectById(tenantId,skuAttr.getAttrvalueDefId());
					valName = attrvalueDef==null?"":attrvalueDef.getAttrValueName();
				}
				valInfoMap.put(skuAttr.getAttrvalueDefId(), valName);
			}
			SkuAttrVal valForSku = new SkuAttrVal();
			valForSku.setAttrId(skuAttr.getAttrId());
			valForSku.setSerialNumber(attrSn.get(skuAttr.getAttrId()));
			valForSku.setValId(skuAttr.getAttrvalueDefId());
			valForSku.setValName(valName);
			valForSkus.add(valForSku);
		}
		Collections.sort(valForSkus, new Comparator<SkuAttrVal>() {
			@Override
			public int compare(SkuAttrVal o1, SkuAttrVal o2) {
				if (o1.getSerialNumber() > o2.getSerialNumber()){
					return 1;
				}
				else if (o1.getSerialNumber() < o2.getSerialNumber()){
					return -1;
				}
				return 0;
			}
		});
		return valForSkus;
	}

	@Override
	public int discardSkuOfAttrValue(String tenantId, String groupId, Map<Long, List<String>> attrValMap,Long operId) {
		if (attrValMap != null && attrValMap.size() > 0) {
			StorageGroup group = checkBefUpdateSkuByGroupId(tenantId, groupId);
			// 查询商品信息
			Product product = productAtomSV.queryProductByGroupId(tenantId, group.getStorageGroupId());
			// 查询类目下销售属性信息
			List<ProdCatAttr> catAttrList = prodCatAttrAtomSV.queryAttrOfCatByIdAndType(tenantId, product.getProductCatId(),
					ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_SALE,"ATTR_ID");
			Set<String> skuSaleAttrs = new HashSet<>();// 删除的SKU属性串集合
			// 参数属性值的所有SKU组合
			genSkuSalAttr(attrValMap, "", 0, skuSaleAttrs, catAttrList);
			
			List<ProdSku> prodSkuList = prodSkuAtomSV.queryProdSkuBySaleAttrs(tenantId, groupId, skuSaleAttrs);
			if(prodSkuList != null && prodSkuList.size()>0){
				for(ProdSku prodSku : prodSkuList){
					discardSku(prodSku, operId);
				}
				return prodSkuList.size();
			}
			return 0;
		} else {
			return 0;
		}
	}

	/**
	 * 查询某个库存下的SKU信息
	 *
	 * @param tenantId
	 * @param supplierId
	 * @param storageId
	 * @return
	 */
	@Override
	public SkuSetForProduct querySkuByStorageId(String tenantId, String supplierId, String storageId) {
		//查询库存信息
		Storage storage = storageAtomSV.queryAllStateStorage(storageId);
		if (storage == null) {
			logger.warn("未查询到指定的库存信息,租户ID:{},商户标识:{},库存ID:{}", tenantId, supplierId, storageId);
			throw new BusinessException("","未查询到指定的库存信息");
		}
		Product product = productAtomSV.selectByProductId(tenantId, supplierId, storage.getProdId());
		if (product == null) {
			logger.warn("未找到指定商品,租户ID{},商品标识{}:" + tenantId + "," + storage.getProdId());
			throw new BusinessException("", "查询商品信息不存在");
		}
		//若库存没有废弃,则按照商品的SKU返回
		if (!StorageConstants.Storage.State.DISCARD.equals(storage.getState())
				&& !StorageConstants.Storage.State.AUTO_DISCARD.equals(storage.getState())){
			return querySkuByProdId(tenantId,product);
		}

		//以下为库存废弃的情况
		Map<Long, Short> attrSn = new HashMap<>();
		SkuSetForProduct skuSetForProduct = querySalAttr(tenantId,product.getProductCatId(),attrSn);
		skuSetForProduct.setProdId(product.getProdId());
		skuSetForProduct.setStorageGroupId(product.getStorageGroupId());
		// 属性值 k:属性值标识; v:属性值名称
		Map<String, String> valInfoMap = new HashMap<>();
		List<SkuInfo> skuInfoList = new ArrayList<>();
		// 查询库存下SKU单品信息
		List<SkuStorage> skuStoList = skuStorageAtomSV.queryByStorageId(storageId,true);
		if (!CollectionUtil.isEmpty(skuSetForProduct.getAttrInfoList()) && CollectionUtil.isEmpty(skuStoList)){
			throw new BusinessException("", "该商品未设置SKU单品信息");
		}
		// 设置SKU单品信息集合
		for (SkuStorage skuSto : skuStoList) {
			// 设置属性串和SKU标识
			SkuInfo skuInfo = new SkuInfo();
			ProdSku sku = prodSkuAtomSV.querySkuById(tenantId,skuSto.getSkuId(),true);
			BeanUtils.copyProperties(skuInfo, sku);
			skuInfoList.add(skuInfo);
			skuInfo.setValForSkuList(genSkuAttrVal(product, sku.getSkuId(), valInfoMap, attrSn));
		}
		skuSetForProduct.setSkuInfoList(skuInfoList);
		//查询已废弃的SKU库存.
		return skuSetForProduct;
	}

	/**
	 * 将属性串按照属性ID进行重新组合
	 * @param skuAttrStr
	 * @return
     */
	private String skuAttrStrSort(String skuAttrStr){
		if (StringUtils.isBlank(skuAttrStr)){
			return "";
		}
		//进行拆解
		String[] skuAttrArry = skuAttrStr.split(ProductConstants.ProdSku.SaleAttrs.ATTR_SPLIT);
		List<ProdSkuAttrStr> skuAttrStrList = new ArrayList<>();
		for (String skuAttr:skuAttrArry){
			String[] attrAndVal = skuAttr.split(ProductConstants.ProdSku.SaleAttrs.ATTRVAL_SPLIT);
			if (attrAndVal==null || attrAndVal.length<2){
				continue;
			}
			ProdSkuAttrStr attrStr = new ProdSkuAttrStr();
			attrStr.setAttrId(attrAndVal[0]);
			attrStr.setAttrValId(attrAndVal[1]);
			skuAttrStrList.add(attrStr);
		}
		Collections.sort(skuAttrStrList, new Comparator<ProdSkuAttrStr>() {
			@Override
			public int compare(ProdSkuAttrStr o1, ProdSkuAttrStr o2) {
				return o1.getAttrId().compareToIgnoreCase(o2.getAttrId());
			}
		});
		return StringUtils.join(skuAttrStrList,ProductConstants.ProdSku.SaleAttrs.ATTR_SPLIT);
	}

	/**
	 * 根据SKU标识或SKU属性串查询SKU的信息(订单专用)
	 *
	 * @param tenantId
	 * @param skuId
	 * @param skuAttrs
	 * @return
	 */
	@Override
	public ProductSKUResponse querySkuDetail4ShopCart(String tenantId, String skuId, String skuAttrs) {
		logger.info("--== querySkuDetail start time:" + System.currentTimeMillis());
		ProdSku prodSku = selectSkuBySkuIdOrAttrs(tenantId, skuId, skuAttrs);

		// 查询商品
		Product product = productAtomSV.selectByProductId(prodSku.getProdId());
		if (product == null) {
			logger.warn("未查询到指定的销售商品,租户ID:{},SKU标识:{},商品ID:{}",
					tenantId, prodSku.getSkuId(), prodSku.getProdId());
			throw new BusinessException(ErrorCodeConstants.Product.PRODUCT_NO_EXIST,"未查询到指定的SKU信息");
		}
		//若不是有效状态,则不处理
		if (!ACTIVE_STATUS_LIST.contains(product.getState())){
			logger.warn("销售商品为无效状态,租户ID:{},SKU标识:{},商品ID:{},状态:{}",
					tenantId, prodSku.getSkuId(), prodSku.getProdId(),product.getState());
			throw new BusinessException(ErrorCodeConstants.Product.PRODUCT_NO_EXIST,"未查询到指定的SKU信息");
		}
		return genSkuResponse4ShopCart(tenantId, product, prodSku);
	}
	private ProductSKUResponse genSkuResponse4ShopCart(String tenantId, Product product, ProdSku prodSku) {
		ProductSKUResponse skuResponse = new ProductSKUResponse();
		BeanUtils.copyProperties(skuResponse, prodSku);
		BeanUtils.copyProperties(skuResponse, product);
		
		// SKU图片
		String attrPic = null;
		
		// 设置主图
		skuResponse.setProductImageList(getProductSkuPic(attrPic, product));
		
		// 设置商品销量
		skuResponse.setSaleNum(prodSaleAllAtomSV.queryNumOfProduc(tenantId, product.getProdId()));
		// 获取当前库存和价格
		SkuStorageVo skuStorageVo = storageNumBusiSV.queryStorageOfSku(tenantId, prodSku.getSkuId());
		skuResponse.setUsableNum(skuStorageVo.getUsableNum());
		skuResponse.setSalePrice(skuStorageVo.getSalePrice());
		
		return skuResponse;
	}

	@Override
	public ProductSKUResponse querySkuDetail(String tenantId, String skuId, String skuAttrs) {
		// 查询商品
		//Product product = productAtomSV.selectByProductId(tenantId, skuId);
		//查询es
		IProductSearch productSearch = new ProductSearchImpl();
		
		List<SearchCriteria> criteria = new ArrayList<SearchCriteria>();
		
		// 商品标识
		if (StringUtils.isNotBlank(skuId)){
			//精确查询
			criteria.add(new SearchCriteria(SearchFieldConfConstants.PRODUCT_ID,
					skuId,
					new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		}
		// 商品类型
		if (StringUtils.isNotBlank(tenantId)){
			criteria.add(new SearchCriteria("tenantid",
					tenantId,
					new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		}
		
		
		int startSize = 1;
		int maxSize = 1;
		// 最大条数设置
		int pageNo = 1;
		int size = 20;
		if (pageNo == 1) {
			startSize = 0;
		} else {
			startSize = (pageNo - 1) * size;
		}
		maxSize = size;
		Result<SKUInfo> infoResult = productSearch.search(criteria, startSize, maxSize, null);
		if (CollectionUtil.isEmpty(infoResult.getContents())) {
    		logger.error("查询商品失败");
    		throw new BusinessException("查询es中的商品信息失败");
		}
    	SKUInfo skuInfo = infoResult.getContents().get(0);

		ProductSKUResponse response = new ProductSKUResponse();
		response.setUsableNum(skuInfo.getUsablenum());
		response.setUnit(skuInfo.getUnit());
		response.setSupplierId(skuInfo.getSupplierid());
		response.setState(skuInfo.getState());
		response.setSkuName(skuInfo.getSkuname());
		response.setSkuId(skuInfo.getSkuid());
		response.setSalePrice(skuInfo.getPrice());
		response.setSaleNum(skuInfo.getSalenum());
		response.setRechargeType(skuInfo.getRechagetype());
		response.setProductSellPoint(skuInfo.getProductsellpoint());
		response.setProdName(skuInfo.getProductname());
		response.setProductCatId(skuInfo.getProductcategoryid());
		response.setProdId(skuInfo.getProductid());
		response.setCommentNum(skuInfo.getCommentnum());
		response.setProDetailContent(skuInfo.getProdetailcontent());
		/**
		 * 商品属性
		 */
		List<ProdAttrValue> prodAttrValues = new ArrayList<>();
		for (AttrInfo attrInfo : skuInfo.getAttrinfos()) {
			ProdAttrValue prodAttrValue = new ProdAttrValue();
			prodAttrValue.setAttrvalueDefId(attrInfo.getAttrvaluedefid());
			prodAttrValue.setAttrValueName(attrInfo.getAttrvalue());
			/**
			 * 商品主图
			 */
			ProductImage productImage = new ProductImage();
			if(null!=skuInfo.getImageinfo()){
				productImage.setPicType(skuInfo.getImageinfo().getImagetype());
				productImage.setVfsId(skuInfo.getImageinfo().getVfsid());
			}
			prodAttrValue.setImage(productImage);
			prodAttrValues.add(prodAttrValue);
		}
		response.setProductAttrList(prodAttrValues);
		/**
		 * 商品图片
		 */
		List<ProductImage> productImages = new ArrayList<>();
		if(!CollectionUtils.isEmpty(skuInfo.getThumbnail())){
		for (ImageInfo imageInfo : skuInfo.getThumbnail()) {
			ProductImage productImage = new ProductImage();
			productImage.setPicType(imageInfo.getImagetype());
			productImage.setVfsId(imageInfo.getVfsid());
			productImages.add(productImage);
			}
		}
		response.setProductImageList(productImages);
		return response;
	
	}
}
