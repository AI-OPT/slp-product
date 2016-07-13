package com.ai.slp.product.service.atom.impl.storage;

import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.constants.SkuStorageConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.bo.storage.SkuStorage;
import com.ai.slp.product.dao.mapper.bo.storage.SkuStorageCriteria;
import com.ai.slp.product.dao.mapper.interfaces.storage.SkuStorageMapper;
import com.ai.slp.product.service.atom.interfaces.storage.ISkuStorageAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * SKU库存原子操作 Created by jackieliu on 16/5/12.
 */
@Component
public class SkuStorageAtomSVImpl implements ISkuStorageAtomSV {
	@Autowired
	SkuStorageMapper skuStorageMapper;

	/**
	 * 废弃指定库存的SKU库存
	 *
	 * @param storageId
	 * @param operId
	 * @return
	 */
	@Override
	public int discardSkuStorageOfStorage(String storageId, Long operId) {
		SkuStorage skuStorage = new SkuStorage();
		skuStorage.setState(StorageConstants.SkuStorage.State.AUTO_DISCARD);
		skuStorage.setOperTime(DateUtils.currTimeStamp());
		skuStorage.setOperId(operId);
		// 废弃状态
		SkuStorageCriteria example = new SkuStorageCriteria();
		example.createCriteria().andStorageIdEqualTo(storageId)
				.andStateNotEqualTo(StorageConstants.SkuStorage.State.AUTO_DISCARD);
		return skuStorageMapper.updateByExampleSelective(skuStorage, example);
	}

	/**
	 * 查询某个SKU单品的所有非废弃的库存
	 *
	 * @param skuId
	 * @return
	 */
	@Override
	public List<SkuStorage> queryOfSku(String skuId) {
		SkuStorageCriteria example = new SkuStorageCriteria();
		example.createCriteria().andSkuIdEqualTo(skuId)
				.andStateNotEqualTo(StorageConstants.SkuStorage.State.AUTO_DISCARD);
		return skuStorageMapper.selectByExample(example);
	}

	@Override
	public int discardById(String skuStorageId, Long operId) {
		SkuStorage skuStorage = new SkuStorage();
		skuStorage.setSkuStorageId(skuStorageId);
		skuStorage.setState(StorageConstants.SkuStorage.State.AUTO_DISCARD);
		skuStorage.setOperId(operId);
		skuStorage.setOperTime(DateUtils.currTimeStamp());
		return skuStorageMapper.updateByPrimaryKeySelective(skuStorage);
	}

	/**
	 * 添加指定SKU库存
	 *
	 * @param skuStorage
	 * @return
	 */
	@Override
	public int install(SkuStorage skuStorage) {
		skuStorage.setSkuStorageId(SequenceUtil.genskuStorageId());
		skuStorage.setOperTime(DateUtils.currTimeStamp());
		return skuStorageMapper.insert(skuStorage);
	}

	/**
	 * 根据库存ID查询SKU库存
	 *
	 * @param storageId
	 * @return
	 * @author lipeng16
	 */
	@Override
	public List<SkuStorage> queryByStorageId(String storageId) {
		if (StringUtils.isEmpty(storageId)) {
			return new ArrayList<SkuStorage>();
		}
		SkuStorageCriteria example = new SkuStorageCriteria();
		example.createCriteria().andStorageIdEqualTo(storageId)
				.andStateNotEqualTo(SkuStorageConstants.SkuStorage.State.AUTO_DISCARD);
		return skuStorageMapper.selectByExample(example);
	}

	/**
	 * 查询指定库存集合中没有销售价格的SKU库存数量
	 *
	 * @param storageIdList
	 * @return
	 */
	@Override
	public int queryNoPriceOfStorageByIdList(List<String> storageIdList) {
		if (CollectionUtil.isEmpty(storageIdList))
			return 0;
		SkuStorageCriteria example = new SkuStorageCriteria();
		example.createCriteria().andStorageIdIn(storageIdList)
				.andSalePriceIsNull();
		return skuStorageMapper.countByExample(example);
	}

	/**
	 * 查询指定标识的SKU库存
	 *
	 * @param skuStorageId
	 * @return
	 */
	@Override
	public SkuStorage queryById(String skuStorageId,boolean hasDiscard) {
		SkuStorage skuStorage = skuStorageMapper.selectByPrimaryKey(skuStorageId);
		if (skuStorage!=null && !hasDiscard
			&& StorageConstants.SkuStorage.State.AUTO_DISCARD.equals(skuStorage.getState()))
			skuStorage = null;
		return skuStorage;
	}

	/**
	 * 根据标识符更新SKU库存
	 *
	 * @param skuStorage
	 * @return
	 */
	@Override
	public int updateById(SkuStorage skuStorage) {
		skuStorage.setOperTime(DateUtils.currTimeStamp());
		return skuStorageMapper.updateByPrimaryKey(skuStorage);
	}
}
