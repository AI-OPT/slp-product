package com.ai.slp.product.service.atom.impl.storage;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.slp.product.constants.SkuStorageConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.attach.SkuStorageAttachMapper;
import com.ai.slp.product.dao.mapper.bo.storage.SkuStorage;
import com.ai.slp.product.dao.mapper.bo.storage.SkuStorageCriteria;
import com.ai.slp.product.dao.mapper.interfaces.storage.SkuStorageMapper;
import com.ai.slp.product.service.atom.interfaces.storage.ISkuStorageAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;

/**
 * SKU库存原子操作 Created by jackieliu on 16/5/12.
 */
@Component
public class SkuStorageAtomSVImpl implements ISkuStorageAtomSV {
	@Autowired
	SkuStorageMapper skuStorageMapper;
	@Autowired
	SkuStorageAttachMapper skuStorageAttachMapper;

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
//		skuStorage.setSkuStorageId(SequenceUtil.genskuStorageId());
		//skuStorage与Storage共享id,且一一对应
		skuStorage.setSkuStorageId(skuStorage.getStorageId());
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
	 * 根据库存ID查询SKU库存
	 *
	 * @param storageId
	 * @param hasDiscard 是否包含已废弃数据
	 * @return
	 */
	@Override
	public List<SkuStorage> queryByStorageId(String storageId, boolean hasDiscard) {
		if (StringUtils.isEmpty(storageId)) {
			return new ArrayList<SkuStorage>();
		}
		SkuStorageCriteria example = new SkuStorageCriteria();
		SkuStorageCriteria.Criteria criteria = example.createCriteria()
				.andStorageIdEqualTo(storageId);
		//如果不包括废弃的
		if (!hasDiscard){
			criteria.andStateNotEqualTo(SkuStorageConstants.SkuStorage.State.AUTO_DISCARD);
		}
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
		if (CollectionUtil.isEmpty(storageIdList)){
			return 0;
		}
		SkuStorageCriteria example = new SkuStorageCriteria();
		example.createCriteria().andStorageIdIn(storageIdList)
				.andSalePriceIsNull().andStateEqualTo(StorageConstants.SkuStorage.State.ACTIVE);
		return skuStorageMapper.countByExample(example);
	}

	/**
	 * 查询指定库存中没有销售价格的SKU库存数量
	 *
	 * @param storageId
	 * @return
	 */
	@Override
	public int queryNoPriceOfStorageById(String storageId) {
		SkuStorageCriteria example = new SkuStorageCriteria();
		example.createCriteria().andStorageIdEqualTo(storageId)
				.andSalePriceIsNull().andStateEqualTo(StorageConstants.SkuStorage.State.ACTIVE);
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
			&& StorageConstants.SkuStorage.State.AUTO_DISCARD.equals(skuStorage.getState())){
			skuStorage = null;
		}
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
		//return skuStorageMapper.updateByPrimaryKey(skuStorage);
		return skuStorageAttachMapper.updateSalePriceBySQL(skuStorage.getSalePrice(),
				skuStorage.getSkuStorageId(),skuStorage.getOperId(),skuStorage.getOperTime());
	}
	/**
	 * 根据标识符更新SKU库存
	 *
	 * @param skuStorage
	 * @return
	 */
	@Override
	public int updateById4Service(String groupId,Short priorityNum,Long price,Long operId) {
		Timestamp operTime = DateUtils.currTimeStamp();
		//return skuStorageMapper.updateByPrimaryKey(skuStorage);
		
		return skuStorageAttachMapper.updateSalePriceBySQL4Service(groupId, priorityNum,price,operId,operTime);
	}

	/**
	 * 查询指定库存集合下的SKU库存
	 *
	 * @param priorityNum
	 * @return
	 */
	@Override
	public List<SkuStorage> queryPriorityOfGroup(String groupId, String skuId, Short priorityNum) {
		Map<String,Object> params = new HashMap<>();
		params.put("groupId",groupId);
		params.put("priorityNum",priorityNum);
		params.put("skuId",skuId);
		//return skuStorageAttachMapper.queryOfPriority(params);
		return skuStorageAttachMapper.queryAllOfPriority(params);
	}

	/**
	 * 更新指定库存下指定SKU的价格
	 *
	 * @param storageId
	 * @param skuId
	 * @param price
	 * @return
	 */
	@Override
	public int updatePrice(String storageId, String skuId, Long price,Long operId) {
		SkuStorageCriteria example = new SkuStorageCriteria();
		SkuStorageCriteria.Criteria criteria = example.createCriteria();
		criteria.andStorageIdEqualTo(storageId).andSkuIdEqualTo(skuId);
		SkuStorage skuStorage = new SkuStorage();
		skuStorage.setSalePrice(price);
		skuStorage.setOperId(operId);
		skuStorage.setOperTime(DateUtil.getSysDate());
		return skuStorageMapper.updateByExampleSelective(skuStorage,example);
	}

	/**
	 * 查询库存组下已启用库存下未设置价格的SKU库存数量
	 *
	 * @param tenantId
	 * @param groupId
	 * @return
	 */
	@Override
	public int countOfNoPrice(String tenantId, String groupId) {
		return skuStorageAttachMapper.countOfNoPrice(groupId);
	}

	/**
	 * 查询sku价格
	 *
	 * @param tenantId
	 * @param prodId
	 * @param skuId
	 * @return
	 */
	@Override
	public Long queryPriceOfSku(String tenantId, String prodId, String skuId) {
		Map<String,Object> params = new HashMap<>();
		params.put("prodId",prodId);
		params.put("skuId",skuId);
		return skuStorageAttachMapper.selectPriceOfSku(params);
	}

    /**
     * 根据条件修改SKU库存
     * @param skuStorage
     * @return
     */
	@Override
	public int updateByCondtion(SkuStorage skuStorage, SkuStorage cond) {
		skuStorage.setOperTime(DateUtils.currTimeStamp());
		
		SkuStorageCriteria example=new SkuStorageCriteria();
		example.or().andSkuStorageIdEqualTo(cond.getSkuStorageId())
		.andUsableNumEqualTo(cond.getUsableNum())
		.andTotalNumEqualTo(cond.getTotalNum());
		
		return skuStorageMapper.updateByExampleSelective(skuStorage, example);
	}

	/**
	 * 根据条件修改SKU库存
	 * @param skuStorageId
	 * @param skuNum
	 * @author Gavin
	 * @UCUSER
	 */
	public int updateSkuStorageUsableNumBySQL(String skuStorageId, int skuNum) {
		return skuStorageAttachMapper.updateBySQL(skuStorageId,skuNum);
	}
	
}
