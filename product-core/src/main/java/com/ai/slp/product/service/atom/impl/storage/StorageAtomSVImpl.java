package com.ai.slp.product.service.atom.impl.storage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.slp.product.constants.CommonSatesConstants;
import com.ai.slp.product.constants.StorageConstants;
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
		example.setOrderByClause("PRIORITY_NUMBER");
		example.createCriteria().andStorageGroupIdEqualTo(groupId);
		return storageMapper.selectByExample(example);
	}

	/**
	 * 查询库存是否存在(通过预警对象标识)
	 */
	@Override
	public int findStorage(String storageId) {
		StorageCriteria example = new StorageCriteria();
		example.createCriteria().andStorageIdEqualTo(storageId).andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
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
		example.setOrderByClause("PRIORITY_NUMBER");
		List<String> activeList = new ArrayList<>();
		activeList.add(StorageConstants.Storage.State.ACTIVE);
		activeList.add(StorageConstants.Storage.State.AUTO_ACTIVE);
		StorageCriteria.Criteria criteria = example.createCriteria();
		criteria.andStorageGroupIdEqualTo(groupId).andStateIn(activeList);
		// 查询可用量大于0的
		if (hasUsable)
			criteria.andUsableNumGreaterThan(0l);
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
	 * 查询指定标识的库存
	 */
	@Override
	public Storage queryById(String storageId) {
		Storage storage = storageMapper.selectByPrimaryKey(storageId);
		if (StorageConstants.Storage.State.DISCARD.equals(storage.getState())
				|| StorageConstants.Storage.State.AUTO_DISCARD.equals(storage.getState()))
			return null;
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
		if (storage == null)
			return 0;
		storage.setOperTime(DateUtils.currTimeStamp());
		storage.setStorageId(SequenceUtil.genStorageId());
		//新增库存的状态为停用
		storage.setState(StorageConstants.Storage.State.STOP);
		return storageMapper.insert(storage);
	}
}
