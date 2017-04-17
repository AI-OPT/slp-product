package com.ai.slp.product.service.atom.impl.storage;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.attach.StorageAttachMapper;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageCriteria;
import com.ai.slp.product.dao.mapper.interfaces.storage.StorageMapper;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;

/**
 * Created by jackieliu on 16/5/5.
 */
@Component
public class StorageAtomSVImpl implements IStorageAtomSV {
	@Autowired
	StorageMapper storageMapper;
	@Autowired
	StorageAttachMapper storageAttachMapper;
	/*
	 * 废弃状态标记集合
	 */
	static List<String> DISCARD_LIST = new ArrayList<>();
	static List<String> STOP_LIST = new ArrayList<>();
	static List<String> ACTIVE_LIST = new ArrayList<>();

	static {
		ACTIVE_LIST.add(StorageConstants.Storage.State.ACTIVE);
		ACTIVE_LIST.add(StorageConstants.Storage.State.AUTO_ACTIVE);
		DISCARD_LIST.add(StorageConstants.Storage.State.DISCARD);
		DISCARD_LIST.add(StorageConstants.Storage.State.AUTO_DISCARD);
		STOP_LIST.add(StorageConstants.Storage.State.STOP);
		STOP_LIST.add(StorageConstants.Storage.State.AUTO_STOP);
	}

	/**
	 * 查询指定库存组下的库存信息,按照优先级正序排序
	 *
	 * @param tenantId
	 * @param groupId
	 * @return
	 */
	@Override
	public List<Storage> queryOfGroup(String tenantId, String groupId) {
		StorageCriteria example = new StorageCriteria();
		
		//example.setOrderByClause("PRIORITY_NUMBER");
		
		example.createCriteria().andStorageGroupIdEqualTo(groupId);
		return storageMapper.selectByExample(example);
	}

	/**
	 * 查询指定库存组下的库存信息,按照优先级正序排序
	 *
	 * @param tenantId
	 * @param groupId
	 * @param hasDiscard 是否包括废弃库存
	 * @return
	 */
	@Override
	public List<Storage> queryOfGroup(String tenantId, String groupId, boolean hasDiscard) {
		StorageCriteria example = new StorageCriteria();
		
		//example.setOrderByClause("PRIORITY_NUMBER");
		
		StorageCriteria.Criteria criteria = example.createCriteria().andStorageGroupIdEqualTo(groupId);
		//若不包括废弃,则查询非废弃库存信息
		if (!hasDiscard){
			criteria.andStateNotIn(DISCARD_LIST);
		}
		return storageMapper.selectByExample(example);
	}

	/**
	 * 查询库存是否存在(通过预警对象标识)
	 */
	@Override
	public int findStorage(String storageId) {
		StorageCriteria example = new StorageCriteria();
		List<String> stateList = new ArrayList<>();
		stateList.add(StorageConstants.Storage.State.DISCARD);
		stateList.add(StorageConstants.Storage.State.AUTO_DISCARD);
		example.createCriteria().andStorageIdEqualTo(storageId).andStateNotIn(stateList);
		return storageMapper.countByExample(example);
	}

	/**
	 * 查询启用状态的库存信息
	 *
	 * @param tenantId
	 * @param groupId
	 * @param hasUsable
	 * @return
	 */
	@Override
	public List<Storage> queryActive(String tenantId, String groupId, boolean hasUsable) {
		StorageCriteria example = new StorageCriteria();
		
		//example.setOrderByClause("PRIORITY_NUMBER");
		
		List<String> activeList = new ArrayList<>();
		activeList.add(StorageConstants.Storage.State.ACTIVE);
		activeList.add(StorageConstants.Storage.State.AUTO_ACTIVE);
		StorageCriteria.Criteria criteria = example.createCriteria();
		criteria.andStorageGroupIdEqualTo(groupId).andStateIn(activeList);
		// 查询可用量大于0的
		if (hasUsable){
			criteria.andUsableNumGreaterThan(0l);
		}
		return storageMapper.selectByExample(example);
	}

	/**
	 * 更新库存信息
	 *
	 * @param storage
	 * @return
	 */
	@Override
	public int updateById(Storage storage) {
		storage.setOperTime(DateUtils.currTimeStamp());
		return storageMapper.updateByPrimaryKeySelective(storage);
	}

	/**
	 * 查询指定标识的非废弃的库存
	 */
	@Override
	public Storage queryNoDiscardById(String storageId) {
		Storage storage = storageMapper.selectByPrimaryKey(storageId);
		if (StorageConstants.Storage.State.DISCARD.equals(storage.getState())
				|| StorageConstants.Storage.State.AUTO_DISCARD.equals(storage.getState())){
			storage = null;
		}
		return storage;
	}

	/**
	 * 更新库存销售价
	 */
	@Override
	public int updateSaleById(Storage storage) {
		storage.setOperTime(DateUtils.currTimeStamp());
		return storageMapper.updateByPrimaryKeySelective(storage);
	}

	/**
	 * 新增库存信息
	 */
	@Override
	public int insertStorage(Storage storage) {
		if (storage == null){
			return 0;
		}
		storage.setCreateTime(DateUtils.currTimeStamp());
		storage.setOperTime(storage.getCreateTime());
		storage.setStorageId(SequenceUtil.genStorageId());
		//新增库存的状态为停用
		storage.setState(StorageConstants.Storage.State.STOP);
		return storageMapper.insert(storage);
	}

	@Override
	public Storage queryAllStateStorage(String storageId) {
		if(StringUtils.isEmpty(storageId)){
			throw new BusinessException("", "库存ID不存在,库存ID"+storageId);
		}
		return storageMapper.selectByPrimaryKey(storageId);
	}

	@Override
	public Storage queryStorageByGroupAndId(String storageGroupId, String storageId) {
		StorageCriteria example = new StorageCriteria();
		example.createCriteria().andStorageIdEqualTo(storageId).andStorageGroupIdEqualTo(storageGroupId);
		List<Storage> storageList = storageMapper.selectByExample(example);
		return CollectionUtil.isEmpty(storageList) ? null : storageList.get(0);
	}

	/**
	 * 查询指定库存组下指定优先级库存列表
	 *
	 * @param groupId
	 * @param priorityNum
	 * @return
	 */
	@Override
	public List<Storage> queryStorageByGroupIdAndPriority(String groupId, short priorityNum,boolean hasDestory) {
		StorageCriteria example = new StorageCriteria();
		StorageCriteria.Criteria criteria = example.createCriteria();
		criteria.andStorageGroupIdEqualTo(groupId)
				.andPriorityNumberEqualTo(priorityNum);
		//排除已废弃状态
		if (!hasDestory){
			criteria.andStateNotIn(DISCARD_LIST);
		}
		return storageMapper.selectByExample(example);
	}

	/**
	 * 查询指定优先级后非促销的库存,按照优先级正序排列
	 *
	 * @param groupId    库存组表示
	 * @param priority   指定优先级
	 * @param hasDestory 是否查询废弃库存
	 * @return
	 */
	@Override
	public List<Storage> queryNoTimeNoPrioritySelf(String groupId, Short priority,Boolean after, boolean hasDestory) {
		StorageCriteria example = new StorageCriteria();
		
		//example.setOrderByClause("PRIORITY_NUMBER");
		
		StorageCriteria.Criteria criteria = example.createCriteria();
		criteria.andStorageGroupIdEqualTo(groupId)
				.andUsableNumGreaterThan(0l)
				.andActiveTimeIsNull()
				.andInactiveTimeIsNull();
		//查询非当前优先级
		if (after == null){
			criteria.andPriorityNumberNotEqualTo(priority);
		}
		//查询当前优先级之后
		else if (Boolean.TRUE.equals(after)){
			criteria.andPriorityNumberGreaterThan(priority);
		}
		//查询当前优先级之前
		else {
			criteria.andPriorityNumberLessThan(priority);
		}

		if (!hasDestory){
			criteria.andStateNotIn(DISCARD_LIST);
		}
		return storageMapper.selectByExample(example);
	}

	/**
	 * 查询现在正促销优先级的库存,可用量大于零
	 *
	 * @param groupId
	 * @param hasDiscard 是否包括废弃的库存
	 * @return
	 */
	@Override
	public List<Storage> queryTimeActiveOfNow(String groupId, boolean hasDiscard) {
		StorageCriteria example = new StorageCriteria();
		
		//example.setOrderByClause("PRIORITY_NUMBER");
		
		StorageCriteria.Criteria criteria = example.createCriteria();
		Timestamp nowTime = DateUtils.currTimeStamp();
		criteria.andStorageGroupIdEqualTo(groupId)
				.andUsableNumGreaterThan(0l)
				.andActiveTimeLessThanOrEqualTo(nowTime)
				.andInactiveTimeGreaterThan(nowTime);
		if (!hasDiscard){
			criteria.andStateNotIn(DISCARD_LIST);
		}

		return storageMapper.selectByExample(example);
	}

	/**
	 * 查询库组组下促销的库存,促销截止时间在当前时间之后
	 *
	 * @param groupId
	 * @param hasDiscard
	 * @return
	 */
	@Override
	public List<Storage> queryTimeStorageOfGroup(String groupId, boolean hasDiscard) {
		StorageCriteria example = new StorageCriteria();
		
		//example.setOrderByClause("PRIORITY_NUMBER");
		
		StorageCriteria.Criteria criteria = example.createCriteria();
		Timestamp nowTime = DateUtils.currTimeStamp();
		criteria.andStorageGroupIdEqualTo(groupId)
				.andInactiveTimeGreaterThan(nowTime);
		if (!hasDiscard){
			criteria.andStateNotIn(DISCARD_LIST);
		}

		return storageMapper.selectByExample(example);
	}

	/**
	 * 统计库存组下库存总量的和
	 *
	 * @param groupId
	 * @param hasDiscard
	 * @return
	 */
	@Override
	public Long sumTotalOfGroup(String groupId, boolean hasDiscard) {
		StorageCriteria example = new StorageCriteria();
		StorageCriteria.Criteria criteria = example.createCriteria();
		criteria.andStorageGroupIdEqualTo(groupId);
		//不包含废弃的
		if (!hasDiscard){
			criteria.andStateNotIn(DISCARD_LIST);
		}
		return storageAttachMapper.sumTotalByExample(example);
	}

	/**
	 * 统计库存组下库存的数量
	 *
	 * @param groupId
	 * @param hasDiscard
	 * @return
	 */
	@Override
	public Integer countOfGroup(String groupId, boolean hasDiscard) {
		StorageCriteria example = new StorageCriteria();
		StorageCriteria.Criteria criteria = example.createCriteria();
		criteria.andStorageGroupIdEqualTo(groupId);
		//不包含废弃的
		if (!hasDiscard){
			criteria.andStateNotIn(DISCARD_LIST);
		}
		return storageMapper.countByExample(example);
	}

	/**
	 * 统计除当前优先级外,状态为启用的库存数量
	 *
	 * @param groupId
	 * @param priorityNum
	 * @return
	 */
	@Override
	public int countOfActiveNoPrioritySelf(String groupId, Short priorityNum) {
		StorageCriteria example = new StorageCriteria();
		example.createCriteria().andStorageGroupIdEqualTo(groupId).andStateIn(ACTIVE_LIST)
				.andPriorityNumberNotEqualTo(priorityNum);

		return storageMapper.countByExample(example);
	}

	/**
	 * 查询库存组中指定优先级下启用库存
	 *
	 * @param groupId
	 * @param priorityNum
	 * @return
	 */
	@Override
	public List<Storage> queryActiveByGroupIdAndPriorityNum(String groupId, Short priorityNum) {
		StorageCriteria example = new StorageCriteria();
		example.createCriteria().andStorageGroupIdEqualTo(groupId).andStateIn(ACTIVE_LIST)
				.andPriorityNumberEqualTo(priorityNum);
		return storageMapper.selectByExample(example);
	}

	@Override
	public int updateByCondtion(Storage storage, Storage cond) {
		storage.setOperTime(DateUtils.currTimeStamp());
		
		StorageCriteria storageCriteria = new StorageCriteria();
		storageCriteria.or().andStorageIdEqualTo(cond.getStorageId())
		.andUsableNumEqualTo(cond.getUsableNum())
		.andTotalNumEqualTo(cond.getTotalNum());
		
		return storageMapper.updateByExampleSelective(storage,storageCriteria);
	}
	
	/**
	 * 获取商品可用库存
	 * @param prodId
	 * @return
	 * @author Gavin
	 * @UCUSER
	 */
	public int getProdUsableNumSum(String prodId,String priority,String state){
		return storageAttachMapper.getProdUsableNumSum(prodId,priority,state);
	}

	
	/**
	 * 根据skuSorageId更新storage库存
	 * @param skuStorageId
	 * @param skuNum
	 * @author Gavin
	 * @UCUSER
	 */
	public void updateStorageUsableNumBySQL(String skuStorageId, int skuNum) {
		storageAttachMapper.updateStorageUsableNumSQL(skuStorageId,skuNum);
	}

}
