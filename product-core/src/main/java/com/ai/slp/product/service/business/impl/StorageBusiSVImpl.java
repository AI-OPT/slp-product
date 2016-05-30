package com.ai.slp.product.service.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.api.storage.param.STOStorage;
import com.ai.slp.product.api.storage.param.SkuStorageAdd;
import com.ai.slp.product.api.storage.param.SkuStorageAndProd;
import com.ai.slp.product.api.storage.param.StorageGroup4SaleList;
import com.ai.slp.product.api.storage.param.StorageGroupQueryPage;
import com.ai.slp.product.api.storage.param.StoragePriorityCharge;
import com.ai.slp.product.api.storage.param.StorageRes;
import com.ai.slp.product.api.storage.param.StorageSalePrice;
import com.ai.slp.product.constants.CommonSatesConstants;
import com.ai.slp.product.constants.ProductCatConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.constants.SkuStorageConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttr;
import com.ai.slp.product.dao.mapper.bo.ProdPriceLog;
import com.ai.slp.product.dao.mapper.bo.StandedProdAttr;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.bo.product.ProdSku;
import com.ai.slp.product.dao.mapper.bo.product.ProdSkuLog;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.product.ProductLog;
import com.ai.slp.product.dao.mapper.bo.storage.SkuStorage;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.dao.mapper.bo.storage.StorageLog;
import com.ai.slp.product.dao.mapper.interfaces.storage.StorageMapper;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdPriceLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProdAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.ISkuStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageLogAtomSV;
import com.ai.slp.product.service.business.interfaces.IStorageBusiSV;
import com.ai.slp.product.vo.StorageGroupPageQueryVo;

/**
 * 库存业务操作
 *
 * Created by jackieliu on 16/5/5.
 */
@Service
@Transactional
public class StorageBusiSVImpl implements IStorageBusiSV {
	private static Logger logger = LoggerFactory.getLogger(StorageBusiSVImpl.class);
	@Autowired
	IStorageGroupAtomSV storageGroupAtomSV;
	@Autowired
	IStorageAtomSV storageAtomSV;
	@Autowired
	IStorageLogAtomSV storageLogAtomSV;
	@Autowired
	ISkuStorageAtomSV skuStorageAtomSV;
	@Autowired
	private transient StorageMapper storageMapper;
	@Autowired
	IProdPriceLogAtomSV prodPriceLogAtomSV;
	@Autowired
	IStandedProductAtomSV standedProductAtomSV;
	@Autowired
	IProductAtomSV productAtomSV;
	@Autowired
	IProdSkuAtomSV prodSkuAtomSV;
	@Autowired
	IProdSkuLogAtomSV prodSkuLogAtomSV;
	@Autowired
	IStandedProdAttrAtomSV standedProdAttrAtomSV;
	@Autowired
	IProdCatAttrAtomSV prodCatAttrAtomSV;
	@Autowired
	IProductLogAtomSV productLogAtomSV;

	/**
	 * 废弃库存
	 *
	 * @param storage 要废弃的库存信息
	 */
	@Override
	public void discardStorage(Storage storage, Long operId) {
		// 废弃库存
		storage.setState(StorageConstants.Storage.State.AUTO_DISCARD);
		storage.setOperId(operId);
		if (storageAtomSV.updateById(storage) > 0) {
			StorageLog storageLog = new StorageLog();
			BeanUtils.copyProperties(storageLog, storage);
			storageLogAtomSV.installLog(storageLog);
		}
		// 废弃SKU库存
		skuStorageAtomSV.discardSkuStorageOfStorage(storage.getStorageId(), storage.getOperId());
	}

	/**
	 * 自动停用库存
	 *
	 * @param storage
	 */
	@Override
	public void autoStopStorage(Storage storage) {
		//售罄后自动停用,上级库存组停用自动停用
		// TODO...
	}

	/**
	 * 更改库存状态
	 *
	 * @param tenantId
	 * @param storageId
	 * @param state
	 * @param operId
	 */
	@Override
	public void changeStorageStats(String tenantId, String storageId, String state, Long operId) {
		// 查询库存是否存在
		Storage storage = storageAtomSV.queryById(storageId);
		if (storage == null) {
			throw new BusinessException("", "要查询库存不存在,库存标识:" + storageId);
		}
		// 查询状态是否变更
		if (storage.getState().equals(state)) {
			throw new BusinessException("", "库存状态已变更,不需重复变更");
		}
		// 废弃状态不允许变更
		if (StorageConstants.Storage.State.DISCARD.equals(storage.getState())
				|| StorageConstants.Storage.State.AUTO_DISCARD.equals(storage.getState()))
		{
			throw new BusinessException("", "库存已废弃,不允许变更状态");
		}

		switch (state) {
		case StorageConstants.Storage.State.ACTIVE:// 转启用
			// 调用启用库存方法
			activeStorage(tenantId,storage,operId);
			break;
		case StorageConstants.Storage.State.STOP:// 转停用
			// 调用停用库存方法
			stopStorage(tenantId,storage,operId);
			break;
		case StorageConstants.Storage.State.DISCARD:// 转废弃
			discardStorage(storage,operId);
			break;
		default:
			throw new BusinessException("", "无法识别的状态:" + state);
		}
	}

	/**
	 * 停用库存
	 *
	 * @param storage
	 * @author lipeng16
	 */
	private void stopStorage(String tenantId,Storage storage, Long operId) {
		//库存对应的库存组下是否存在启用或自动启用的库存,若存在则直接停用
		List<Storage> storageList = storageAtomSV.queryActive(tenantId,storage.getStorageGroupId(),false);
		if(!CollectionUtil.isEmpty(storageList)){
			//库存状态变为停用并更新日志
			storage.setState(StorageConstants.Storage.State.STOP);
			storage.setOperId(operId);
			if(storageAtomSV.updateById(storage)>0){
				StorageLog storageLog = new StorageLog();
				BeanUtils.copyProperties(storageLog, storage);
				storageLogAtomSV.installLog(storageLog);
			}
			//TODO 调用自动切换库存方法
			return;
		}
		//若不存在,则该库存组对应商品状态若为在售则变为停用下架
		Product product = productAtomSV.queryProductByGroupId(tenantId, storage.getStorageGroupId());
		if(!ProductConstants.Product.State.IN_SALE.equals(product.getState())){
			return;
		}
		product.setState(ProductConstants.Product.State.STOP);
		product.setOperId(operId);
		if(productAtomSV.updateById(product)>0){
			ProductLog productLog = new ProductLog();
			BeanUtils.copyProperties(productLog, product);
			productLogAtomSV.install(productLog);
		}
	}

	/**
	 * 批量更新库存销售价
	 *
	 * @param storageSalePrice
	 * @return
	 * @author lipeng16
	 */
	@Override
	public int updateMultiStorageSalePrice(StorageSalePrice storageSalePrice) {
		Map<String, Long> priceMap = storageSalePrice.getStorageSalePrice();
		if (priceMap == null || priceMap.isEmpty())
			return 0;
		String tenantId = storageSalePrice.getTenantId();
		Long operId = storageSalePrice.getOperId();
		int count = 0;
		for (String storageId : priceMap.keySet()) {
			// 库存标识为空,库存对应价格为空,库存销售价小于等于0,均不处理
			if (StringUtils.isBlank(storageId) || priceMap.get(storageId) == null || priceMap.get(storageId) <= 0) {
				logger.warn("库存标识为空或销售价不大于零,库存标识[{}],销售价[{}]", storageId, priceMap.get(storageId));
				continue;
			}

			// 查看对应的标识下是否存在库存信息
			Storage storage0 = storageMapper.selectByPrimaryKey(storageId);
			if (storage0 == null) {
				logger.warn("未查询到指定库存,库存标识[{}]", storageId);
				continue;
			}
			// 获取当前库存对应库存组
			StorageGroup storageGroup = storageGroupAtomSV.queryByGroupId(tenantId, storage0.getStorageGroupId());
			if (storageGroup == null || storageGroup.getHighSalePrice() == null
					|| storageGroup.getLowSalePrice() == null)
			{
				logger.warn("未找到对应库存组或库存组为设置最低最高销售价,租户ID:{},库存组标识:{}", tenantId, storage0.getStorageGroupId());
				throw new BusinessException("",
						"未找到对应库存组或库存组未设置最低最高销售价,租户ID:" + tenantId + ",库存组标识:" + storage0.getStorageGroupId());
			}
			// 判断销售价是否在库存组价格区间
			Long salePrice = priceMap.get(storageId);
			if (salePrice > storageGroup.getHighSalePrice() || salePrice < storageGroup.getLowSalePrice()) {
				throw new BusinessException("", "库存[" + storage0.getStorageName() + "]的价格必须在库存组的最低最高销售价范围内");
			}

			// 更新库存价格信息
			Storage storage = new Storage();
			storage.setStorageId(tenantId);
			storage.setSalePrice(salePrice);
			storage.setOperId(operId);
			int installNum = storageAtomSV.updateSaleById(storage);
			if (installNum > 0) {
				ProdPriceLog prodPriceLog = new ProdPriceLog();
				prodPriceLog.setObjId(storageId);
				prodPriceLog.setObjType("SO");
				prodPriceLog.setOperId(operId);
				prodPriceLog.setUpdatePrice(salePrice);
				prodPriceLogAtomSV.insert(prodPriceLog);
			}
			count++;
		}
		return count;
	}

	/**
	 * 启用库存
	 * 
	 * @param storage
	 */
	private void activeStorage(String tenantId,Storage storage, Long operId) {
		// 检查库存可用量,若有库存,则检查库存组是否自动停用,若是,则自动启用
		if (storage.getUsableNum() > 0) {
			// 通过库存组ID查询启用或自动启用的库存
			List<Storage> storageList = storageAtomSV.queryActive(tenantId,storage.getStorageGroupId(),false);
			if (CollectionUtil.isEmpty(storageList)) {
				// 如果不存在启用的库存则更新当前库存状态为启用
				storage.setState(StorageConstants.Storage.State.ACTIVE);
				storage.setOperId(operId);
				// 更新库存和日志
				if (storageAtomSV.updateById(storage) > 0) {
					StorageLog storageLog = new StorageLog();
					BeanUtils.copyProperties(storageLog, storage);
					storageLogAtomSV.installLog(storageLog);
				}
				// 检查库存组状态
				String state = storageGroupAtomSV
						.queryByGroupId(tenantId, storage.getStorageGroupId()).getState();
				// 库存组状态不为启用或不启用则不进行下一步操作
				if (!StorageConstants.StorageGroup.State.ACTIVE.equals(state)
						|| !StorageConstants.StorageGroup.State.AUTO_ACTIVE.equals(state))
				{
					return;
				}
				// 查询销售商品的状态是否为售罄下架或停用下架
				Product product = productAtomSV.selectByGroupId(tenantId, storage.getStorageGroupId());
				if(ProductConstants.Product.State.SALE_OUT.equals(product.getState()) || ProductConstants.Product.State.STOP.equals(product.getState())){
					//是则改为在售
					product.setState(ProductConstants.Product.State.IN_SALE);
					storage.setOperId(operId);
					//更新商品和日志
					if(productAtomSV.updateById(product) >0 ){
						ProductLog productLog = new ProductLog();
						BeanUtils.copyProperties(productLog, product);
						productLogAtomSV.install(productLog);
					}
				}
			} else {
				if (storageList.get(0).getPriorityNumber().equals(storage.getPriorityNumber())) {
					// 判断当前启用状态的优先级是否与当前库存的优先级相同,相同则改为启用状态
					storage.setState(StorageConstants.Storage.State.ACTIVE);
					storage.setOperId(operId);
					// 更新库存和日志
					if (storageAtomSV.updateById(storage) > 0) {
						StorageLog storageLog = new StorageLog();
						BeanUtils.copyProperties(storageLog, storage);
						storageLogAtomSV.installLog(storageLog);
					}
				} else {
					// 若不同则不进行启用
					return;
				}
			}
		}
		// 若没有则设置为自动停用.
		if (storage.getUsableNum() <= 0) {
			storage.setState(StorageConstants.Storage.State.AUTO_STOP);
			// 更新日志
			if (storageAtomSV.updateById(storage) > 0) {
				StorageLog storageLog = new StorageLog();
				BeanUtils.copyProperties(storageLog, storage);
				storageLogAtomSV.installLog(storageLog);
			}
		}

	}

	/**
	 * 查询标准品列表,包含标准品的库存组,适用于库存组定最低最高销售价初始页面信息查询<br>
	 * 库存组不包括废弃状态的
	 */
	@Override
	public PageInfoResponse<StorageGroup4SaleList> queryGroupsForSalePrice(StorageGroupQueryPage groupQuery) {
		StorageGroupPageQueryVo storageGroupPageQueryVo = new StorageGroupPageQueryVo();
		BeanUtils.copyProperties(storageGroupPageQueryVo, groupQuery);
		PageInfoResponse<StorageGroup> StorageGroupPage = storageGroupAtomSV.queryPageOfSearch(storageGroupPageQueryVo);
		// 设置返回对象
		PageInfoResponse<StorageGroup4SaleList> StorageGroup4SaleListPage = new PageInfoResponse<>();
		StorageGroup4SaleListPage.setPageNo(groupQuery.getPageNo());
		StorageGroup4SaleListPage.setPageSize(groupQuery.getPageSize());
		StorageGroup4SaleListPage.setPageCount(StorageGroupPage.getPageCount());

		List<StorageGroup> storageGroupList = StorageGroupPage.getResult();
		// 新建结果集
		List<StorageGroup4SaleList> storGroup4SaleList = new ArrayList<>();
		for (StorageGroup storageGroup : storageGroupList) {
			StorageGroup4SaleList storageGroup4SaleList = new StorageGroup4SaleList();
			BeanUtils.copyProperties(storageGroup4SaleList, storageGroup);
			// 通过ID查标准品名称
			storageGroup4SaleList.setStandedProductName(standedProductAtomSV
					.selectById(groupQuery.getTenantId(), storageGroup.getStandedProdId()).getStandedProductName());
			// 填充结果集
			storGroup4SaleList.add(storageGroup4SaleList);
		}
		// 设置结果集
		StorageGroup4SaleListPage.setResult(storGroup4SaleList);
		return StorageGroup4SaleListPage;
	}

	/**
	 * 保存库存信息
	 */
	@Override
	public int saveStorage(STOStorage stoStorage) {
		String tenantId = stoStorage.getTenantId();
		Long operId = stoStorage.getOperId();
		// 通过库存组查询库存相关标准品信息及是否有销售属性1.库存组标识查库存组,2.库存组的标准品标识查询标准品信息,3.获取是否有销售属性
		StorageGroup storageGroup = storageGroupAtomSV.queryByGroupId(tenantId, stoStorage.getStorageGroupId());
		StandedProduct standedProduct = standedProductAtomSV.selectById(tenantId, storageGroup.getStandedProdId());
		// 商品是否有销售属性
		String isSaleAttr = storageGroup.getIsSaleAttr();
		// 增加销售商品信息
		Product product = new Product();
		product.setTenantId(tenantId);
		product.setProductCatId(stoStorage.getProductCatId());
		product.setStandedProdId(standedProduct.getStandedProdId());
		product.setStorageGroupId(stoStorage.getStorageGroupId());
		// 设置商品名称为标准品名称,设置商品类型为标准品类型
		product.setProdName(standedProduct.getStandedProductName());
		product.setProductType(standedProduct.getProductType());
		product.setIsSaleAttr(isSaleAttr);
		product.setOperId(operId);
		// 设置商品状态为新增状态
		product.setState(ProductConstants.Product.State.ADD);
		// 调用方法保存商品
		int saveProductNum = productAtomSV.installProduct(product);
		if (saveProductNum <= 0) {
			throw new BusinessException("", "新增商城商品失败,商品ID=" + product.getProdId());
		}
		// 新增库存信息
		Storage storage = new Storage();
		BeanUtils.copyProperties(storage, stoStorage);
		storage.setProdId(product.getProdId());
		storage.setIsSaleAttr(isSaleAttr);
		// 设置优先级
		storage.setPriorityNumber(stoStorage.getPriorityNumber());
		int saveNum = storageAtomSV.insertStorage(storage);
		if (saveNum <= 0) {
			// 没有插入成功返回0
			return 0;
		}
		// 添加库存日志
		StorageLog storageLog = new StorageLog();
		BeanUtils.copyProperties(storageLog, storage);
		storageLogAtomSV.installLog(storageLog);
		// 通过是否有销售属性判断下一步操作
		if (isSaleAttr.equals(ProductConstants.Product.IsSaleAttr.YES)) {
			// 如果有销售属性则返回
			return saveNum;
		} else if (isSaleAttr.equals(ProductConstants.Product.IsSaleAttr.NO)) {
			// 如果没有销售属性则增加一个SKU对象
			com.ai.slp.product.dao.mapper.bo.product.ProdSku prodSku = new com.ai.slp.product.dao.mapper.bo.product.ProdSku();
			prodSku.setTenantId(tenantId);
			prodSku.setProdId(product.getProdId());
			prodSku.setStorageGroupId(product.getStorageGroupId());
			// 名称为商品名称
			prodSku.setSkuName(product.getProdName());
			prodSku.setIsSaleAttr(ProductConstants.Product.IsSaleAttr.NO);
			// 设置SKU属性串为空
			prodSku.setSaleAttrs(null);
			// 状态为有效
			prodSku.setState(CommonSatesConstants.STATE_ACTIVE);
			prodSku.setOperId(operId);
			int saveProdSku = prodSkuAtomSV.createObj(prodSku);
			if (saveProdSku <= 0) {
				throw new BusinessException("", "添加单品SKU失败,单品SKU_ID=" + prodSku.getSkuId());
			}
			// 添加SKU日志信息
			ProdSkuLog prodSkuLog = new ProdSkuLog();
			BeanUtils.copyProperties(prodSkuLog, prodSku);
			prodSkuLogAtomSV.install(prodSkuLog);
			// 新增SKU虚拟库存,数据来自虚拟库存和单品SKU
			SkuStorage skuStorage = new SkuStorage();
			skuStorage.setSkuId(prodSku.getSkuId());
			skuStorage.setStorageId(storage.getStorageId());
			skuStorage.setTotalNum(storage.getTotalNum());
			skuStorage.setState(SkuStorageConstants.SkuStorage.State.ACTIVE);
			skuStorage.setOperId(operId);
			int saveSkuStorage = skuStorageAtomSV.install(skuStorage);
			if (saveSkuStorage <= 0) {
				throw new BusinessException("", "添加SKU虚拟库存失败,虚拟库存ID=" + skuStorage.getSkuStorageId());
			}
			return saveNum;
		} else {
			throw new BusinessException("", "不是有效的是否有销售属性状态" + isSaleAttr);
		}
	}

	/**
	 * 通过标准品ID判断是否有销售属性
	 *
	 * @param tenantId
	 * @param standedProdId
	 * @return
	 * @author lipeng16
	 */
	public boolean isSaleAttr(String tenantId, String standedProdId) {
		// 通过标准品标识查属性(standedProdAttr)
		List<StandedProdAttr> standedProdAttrList = standedProdAttrAtomSV.queryByNormProduct(tenantId, standedProdId);
		if (CollectionUtil.isEmpty(standedProdAttrList)) {
			return false;
		}
		// 通过属性标识查看类目下属性(ProdCatAttr)
		for (StandedProdAttr standedProdAttr : standedProdAttrList) {
			List<ProdCatAttr> prodCatAttrList = prodCatAttrAtomSV.queryCatAttrByAttrId(tenantId,
					standedProdAttr.getAttrId());
			if (CollectionUtil.isEmpty(prodCatAttrList)) {
				return false;
			}
			for (ProdCatAttr prodCatAttr : prodCatAttrList) {
				if (ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_SALE.equals(prodCatAttr.getAttrType())) {
					// 有一个是销售属性则返回true
					return true;
				}
			}
		}
		// 3.没有一个则false
		return false;
	}

	/**
	 * 修改库存信息,只能修改名称和最低预警库存量-适用有和无销售属性两种状态
	 */
	@Override
	public int updateStorageNameWarn(STOStorage stoStorage) {
		Long operId = stoStorage.getOperId();
		// 获取库存ID
		String storageId = stoStorage.getStorageId();
		// 判断库存标识是否存在库存信息
		if (storageAtomSV.findStorage(storageId) <= 0) {
			throw new BusinessException("", "找不到指定的标识的库存,库存标识=" + storageId);
		}
		// 如果存在则修改库存信息,只能修改名称和最低预警库存量
		Storage storage = new Storage();
		storage.setStorageId(storageId);
		storage.setStorageName(stoStorage.getStorageName());
		storage.setWarnNum(storage.getWarnNum());
		storage.setOperId(operId);
		int updateNum = storageAtomSV.updateById(storage);
		if (updateNum <= 0) {
			throw new BusinessException("", "修改库存信息失败,库存ID=" + storageId);
		}
		// 更新库存日志信息
		StorageLog storageLog = new StorageLog();
		BeanUtils.copyProperties(storageLog, storage);
		storageLogAtomSV.installLog(storageLog);
		return updateNum;
	}

	/**
	 * 查看库存信息
	 */
	@Override
	public StorageRes queryStorageById(String storageId) {
		if (storageAtomSV.queryAllStateStorage(storageId) == null) {
			throw new BusinessException("", "找不到指定标识的库存,库存ID" + storageId);
		}
		Storage storage = storageAtomSV.queryAllStateStorage(storageId);
		StorageRes storageRes = new StorageRes();
		BeanUtils.copyProperties(storageRes, storage);
		return storageRes;
	}

	/**
	 * 查看SKU库存
	 */
	@Override
	public List<SkuStorageAndProd> querySkuStorageById(String tenantId,String storageId) {
		// 通过库存标识查询SKU库存集合
		List<SkuStorage> skuStorageList = skuStorageAtomSV.queryByStorageId(storageId);
		if (CollectionUtil.isEmpty(skuStorageList)) {
			throw new BusinessException("", "找不到指定库存标识的SKU库存,库存标识=" + storageId);
		}
		List<SkuStorageAndProd> skuStorageAndProdList = new ArrayList<>();
		SkuStorageAndProd skuStorageAndProd = null;
		// 通过SKU库存的SKU标识查对应的商品SKU
		for (SkuStorage skuStorage : skuStorageList) {
			String skuId = skuStorage.getSkuId();
			ProdSku prodSku = prodSkuAtomSV.querySkuById(tenantId,skuId);
			if (prodSku == null) {
				continue;
			}
			skuStorageAndProd = new SkuStorageAndProd();
			skuStorageAndProd.setSkuId(skuId);
			skuStorageAndProd.setSkuStorageId(storageId);
			skuStorageAndProd.setTotalNum(skuStorage.getTotalNum());
			skuStorageAndProd.setSaleAttrs(prodSku.getSaleAttrs());
			// 填充返回值
			skuStorageAndProdList.add(skuStorageAndProd);
		}
		return CollectionUtil.isEmpty(skuStorageAndProdList) ? null : skuStorageAndProdList;
	}

	/**
	 * 新增SKU库存
	 */
	@Override
	public int insertSkuStorage(List<SkuStorageAdd> skuStorageAddList) {
		SkuStorage skuStorage = null;
		int count = 0;
		for (SkuStorageAdd skuStorageAdd : skuStorageAddList) {
			skuStorage = new SkuStorage();
			BeanUtils.copyProperties(skuStorage, skuStorageAdd);
			skuStorage.setState(SkuStorageConstants.SkuStorage.State.ACTIVE);
			int addNum = skuStorageAtomSV.install(skuStorage);
			count += addNum;
		}
		return count;
	}

	@Override
	public void updateStoragePriority(StoragePriorityCharge stoPriorityCharge) {
		// 获取库存组标识
		String groupId = stoPriorityCharge.getStorageGroupId();
		String tenantId = stoPriorityCharge.getTenantId();
		// 检查将移动优先级是否一致
		if (stoPriorityCharge.getNewLevel() == stoPriorityCharge.getOldLevel()){
			throw new BusinessException("","优先级为发生变更");
		}
		// 检查库存组是否存在
		if (stoPriorityCharge == null
				|| storageGroupAtomSV.queryByGroupId(tenantId, groupId) == null) {
			logger.warn("找不到指定的库存组,租户表{},库存组标识{}",tenantId,groupId );
			throw new BusinessException("","指定库存组不存在,租户ID:"+tenantId+",库存组ID:"+groupId);
		}
		// 获取要优先级库存
		List<Storage> oldStorageList = storageAtomSV.queryStorageByGroupIdAndPriority(groupId,stoPriorityCharge.getOldLevel(),true);
		List<Storage> newStorageList = storageAtomSV.queryStorageByGroupIdAndPriority(groupId,stoPriorityCharge.getNewLevel(),true);
		changeStoragePriority(oldStorageList,stoPriorityCharge.getNewLevel());
		changeStoragePriority(newStorageList,stoPriorityCharge.getOldLevel());
	}

	/**
	 * 变更库存优先级
	 * @param storageList
	 * @param newLevel
     */
	private void changeStoragePriority(List<Storage> storageList,short newLevel){
		if (CollectionUtil.isEmpty(storageList))
			return;
		for (Storage storage:storageList){
			storage.setPriorityNumber(newLevel);
			if (storageAtomSV.updateById(storage)>0){
				StorageLog storageLog = new StorageLog();
				BeanUtils.copyProperties(storageLog, storage);
				storageLogAtomSV.installLog(storageLog);
			}
		}
	}
}
