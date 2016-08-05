package com.ai.slp.product.service.business.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.api.storage.param.*;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.constants.SkuStorageConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.bo.ProdPriceLog;
import com.ai.slp.product.dao.mapper.bo.product.ProdSku;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.storage.SkuStorage;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.dao.mapper.bo.storage.StorageLog;
import com.ai.slp.product.dao.mapper.interfaces.storage.StorageMapper;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdPriceLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProdAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.*;
import com.ai.slp.product.service.atom.interfaces.storage.ISkuStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageLogAtomSV;
import com.ai.slp.product.service.business.interfaces.IStorageBusiSV;
import com.ai.slp.product.service.business.interfaces.search.ISKUIndexManage;
import com.ai.slp.product.vo.StorageGroupPageQueryVo;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	@Autowired
    IProductStateLogAtomSV productStateLogAtomSV;
	@Autowired
	ISKUIndexManage iskuIndexManage;
	@Autowired
	StorageNumDbBusiSVImpl storageNumDbBusiSV;

	/**
	 * 废弃库存
	 *
	 * @param storage 要废弃的库存信息
	 */
	@Override
	public void discardStorage(String tenantId,Storage storage, Long operId,boolean isAuto) {
		Product product = productAtomSV.queryProductByGroupId(tenantId,storage.getStorageGroupId());
		//库存为启用,且商品为在售,则不允许进行废弃
		if (StorageConstants.Storage.State.ACTIVE.equals(storage.getState())
				&& product!=null && ProductConstants.Product.State.IN_SALE.equals(product.getState()))
			throw new BusinessException("","对应商品在销售中,不允许停用");
		//如果库存为启用,需要从缓存中删除库存对应缓存信息
		if (StorageConstants.Storage.State.ACTIVE.equals(storage.getState()))
			storageNumDbBusiSV.subStorageCache(tenantId,storage);
		//判断是否为自动废弃
		if (isAuto)
			storage.setState(StorageConstants.Storage.State.AUTO_DISCARD);
		else
			storage.setState(StorageConstants.Storage.State.DISCARD);
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
	public Storage changeStorageStats(String tenantId,String supplierId, String storageId, String state, Long operId) {
		// 查询库存是否存在
		Storage storage = storageAtomSV.queryNoDiscardById(storageId);
		if (storage == null) {
			throw new BusinessException("", "库存不存在或已废弃,库存标识:" + storageId);
		}
		// 查询状态是否变更
		if (storage.getState().equals(state)) {
			throw new BusinessException("", "状态已变更,不需重复变更");
		}

		switch (state) {
		case StorageConstants.Storage.State.ACTIVE:// 转启用
			// 调用启用库存方法
			activeStorage(tenantId,supplierId,storage,operId);
			break;
		case StorageConstants.Storage.State.STOP:// 转停用
			// 调用停用库存方法
			stopStorage(tenantId,storage,operId);
			break;
		case StorageConstants.Storage.State.DISCARD:// 转废弃
			discardStorage(tenantId,storage,operId,false);
			break;
		default:
			throw new BusinessException("", "无法识别的状态:" + state);
		}
		return storage;
	}

	/**
	 * 停用库存
	 * 商品必须为为非在售状态
	 *
	 * @param storage
	 * @author lipeng16
	 */
	private void stopStorage(String tenantId,Storage storage, Long operId) {
		//判断商品是否在售
		Product product = productAtomSV.queryProductByGroupId(tenantId,storage.getStorageGroupId());
		if (product!=null && ProductConstants.Product.State.IN_SALE.equals(product.getState()))
			throw new BusinessException("","对应商品在销售中,不允许停用");
		//更新库存状态
		storage.setOperId(operId);
		storage.setState(StorageConstants.Storage.State.STOP);
		if (storageAtomSV.updateById(storage)>0){
			StorageLog storageLog = new StorageLog();
			BeanUtils.copyProperties(storageLog, storage);
			storageLogAtomSV.installLog(storageLog);
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
			StorageGroup storageGroup = storageGroupAtomSV.queryByGroupIdAndSupplierId(
					tenantId, storageSalePrice.getSupplierId(),storage0.getStorageGroupId());
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
	private void activeStorage(String tenantId, String supplierId, Storage storage, Long operId) {
		//检查是否有启用库存
		int count = storageAtomSV.countOfActiveNoPrioritySelf(
				storage.getStorageGroupId(), storage.getPriorityNumber());
		if (count > 0) {
			throw new BusinessException("", "其他优先级存在启用库存,无法启用当前库存");
		}
		//判断库存下SKU库存是否已设置价格
		if (skuStorageAtomSV.queryNoPriceOfStorageById(storage.getStorageId())>0){
			throw new BusinessException("", "该库存价格设置不完整,无法启用");
		}
		//更新库存状态
		storage.setOperId(operId);
		storage.setState(StorageConstants.Storage.State.ACTIVE);
		if (storageAtomSV.updateById(storage)>0){
			StorageLog storageLog = new StorageLog();
			BeanUtils.copyProperties(storageLog, storage);
			storageLogAtomSV.installLog(storageLog);
		}
	}

	/**
	 * 查询标准品列表,包含标准品的库存组,适用于库存组定最低最高销售价初始页面信息查询<br>
	 * 库存组不包括废弃状态的
	 */
	@Override
	public PageInfoResponse<StorageGroup4SaleList> queryGroupsForSalePrice(StorageGroupQueryPage groupQuery) {
		StorageGroupPageQueryVo queryVo = new StorageGroupPageQueryVo();
		BeanUtils.copyProperties(queryVo, groupQuery);
		PageInfo<StorageGroup> StorageGroupPage = storageGroupAtomSV.queryPageOfSearch(queryVo);
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
	 * @return 新增库存ID
	 */
	@Override
	public String saveStorage(STOStorage stoStorage) {
		String tenantId = stoStorage.getTenantId();
		Long operId = stoStorage.getOperId();
		String storageGroupId = stoStorage.getStorageGroupId();
//		查询指定库存组下的销售商品
		Product product = productAtomSV.selectByGroupId(tenantId, storageGroupId);
		if(product == null){
			logger.warn("未找到对应的商品信息,租户ID:{},销售商ID:{},库存组ID:{},库存ID:{}",
					stoStorage.getTenantId());
			throw new BusinessException("", "找不到指定标示的商品:" + storageGroupId);
		}
		String isSaleAttr = product.getIsSaleAttr();
		// 新增库存信息
		Storage storage = new Storage();
		BeanUtils.copyProperties(storage, stoStorage);
		storage.setProdId(product.getProdId());
		storage.setIsSaleAttr(isSaleAttr);
		// 设置优先级
		storage.setPriorityNumber(stoStorage.getPriorityNumber());
		storage.setCreateId(stoStorage.getOperId());
		//新增可用量为库存量
		storage.setUsableNum(stoStorage.getTotalNum());
		int saveNum = storageAtomSV.insertStorage(storage);
		if (saveNum <= 0) {
			logger.error("Install storage num is 0.\r\n{}", new Gson().toJson(storage));
			throw new BusinessException("","添加库存失败");
		}
		// 添加库存日志
		StorageLog storageLog = new StorageLog();
		BeanUtils.copyProperties(storageLog, storage);
		storageLogAtomSV.installLog(storageLog);
		// 如果有销售属性,则添加SKU对应的库存信息
		if (isSaleAttr.equals(ProductConstants.Product.IsSaleAttr.YES)) {
			for (Map.Entry<String,Long> entry:stoStorage.getSkuStorageNum().entrySet()){
				installSkuStorage(entry.getKey(),storage.getStorageId(),entry.getValue(),operId);
			}
		} else if (isSaleAttr.equals(ProductConstants.Product.IsSaleAttr.NO)) {
			//通过商品id查出商品SKU信息,更新SKU库存信息
			String skuId = prodSkuAtomSV.querySkuOfProd(tenantId, product.getProdId()).get(0).getSkuId();
			installSkuStorage(skuId,storage.getStorageId(),storage.getTotalNum(),operId);
		} else {
			throw new BusinessException("", "不是有效的是否有销售属性状态" + isSaleAttr);
		}
		return storage.getStorageId();
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
	public StorageRes queryStorageById(String tenantId,String supplierId,String storageId) {
		Storage storage = storageAtomSV.queryAllStateStorage(storageId);
		if (storage == null) {
			logger.warn("tenantId:{},supplierId:{},storageId:{}",tenantId,supplierId,storageId);
			throw new BusinessException("", "找不到指定标识的库存,库存ID" + storageId);
		}
		StorageGroup group = storageGroupAtomSV.queryByGroupIdAndSupplierId(
				tenantId,supplierId,storage.getStorageGroupId());
		if (group==null){
			logger.warn("tenantId:{},supplierId:{},storageId:{}",tenantId,supplierId,storageId);
			throw new BusinessException("", "未找到对应库存信息,库存ID:" + storageId);
		}
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
				|| storageGroupAtomSV.queryByGroupIdAndSupplierId(tenantId,stoPriorityCharge.getSupplierId(), groupId) == null) {
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

	/**
	 * 添加SKU库存
	 * @return sku库存标识
	 */
	private String installSkuStorage(String skuId,String storageId,Long totalNum,Long operId){
		// 新增SKU虚拟库存,数据来自虚拟库存和单品SKU
		SkuStorage skuStorage = new SkuStorage();
		skuStorage.setSkuId(skuId);
		skuStorage.setStorageId(storageId);
		skuStorage.setTotalNum(totalNum);
		skuStorage.setState(SkuStorageConstants.SkuStorage.State.ACTIVE);
		skuStorage.setOperId(operId);
		skuStorage.setUsableNum(totalNum);
		skuStorageAtomSV.install(skuStorage);
		return skuStorage.getSkuStorageId();
	}
}
