package com.ai.slp.product.service.atom.impl.storage;

import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.attach.StorageGroupAttach4List;
import com.ai.slp.product.dao.mapper.attach.StorageGroupAttachMapper;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroupCriteria;
import com.ai.slp.product.dao.mapper.interfaces.storage.StorageGroupMapper;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;
import com.ai.slp.product.vo.StoGroupPageQueryVo;
import com.ai.slp.product.vo.StorageGroupPageQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 库存组原子操作 Created by jackieliu on 16/4/29.
 */
@Component
public class StorageGroupAtomSVImpl implements IStorageGroupAtomSV {
	@Autowired
	StorageGroupMapper storageGroupMapper;
	@Autowired
	IStandedProductAtomSV standedProductAtomSV;
	@Autowired
	StorageGroupAttachMapper attachMapper;

	private static List<String> discardList = new ArrayList<>();

	static {
		discardList.add(StorageConstants.StorageGroup.State.DISCARD);
		discardList.add(StorageConstants.StorageGroup.State.AUTO_DISCARD);
	}
	/**
	 * 没有废弃的库存组数量
	 * 
	 * @param tenantId
	 * @param standedProdId
	 * @return
	 */
	@Override
	public int queryCountNoDiscard(String tenantId, String standedProdId) {
		StorageGroupCriteria example = new StorageGroupCriteria();
		List<String> discard = new ArrayList<>();
		discard.add(StorageConstants.StorageGroup.State.DISCARD);
		discard.add(StorageConstants.StorageGroup.State.AUTO_DISCARD);
		example.createCriteria().andTenantIdEqualTo(tenantId).andStandedProdIdEqualTo(standedProdId)
				.andStateNotIn(discard);
		return storageGroupMapper.countByExample(example);
	}

	/**
	 * 启用状态的库存组数量
	 * 
	 * @param tenantId
	 * @param standedProdId
	 * @return
	 */
	@Override
	public int queryCountActive(String tenantId, String standedProdId) {
		StorageGroupCriteria example = new StorageGroupCriteria();
		List<String> stateList = new ArrayList<>();
		stateList.add(StorageConstants.StorageGroup.State.ACTIVE);
		stateList.add(StorageConstants.StorageGroup.State.AUTO_ACTIVE);
		example.createCriteria().andTenantIdEqualTo(tenantId).andStandedProdIdEqualTo(standedProdId)
				.andStateIn(stateList);
		return storageGroupMapper.countByExample(example);
	}

	/**
	 * 添加库存组信息
	 *
	 * @param group
	 * @return
	 */
	@Override
	public int installGroup(StorageGroup group) {
		group.setStorageGroupId(SequenceUtil.genStorageGroupId());
		if (group.getCreateTime() == null)
			group.setCreateTime(DateUtils.currTimeStamp());
		group.setOperTime(group.getCreateTime());
		group.setOperId(group.getCreateId());
		return storageGroupMapper.insert(group);
	}

	/**
	 * 查询指定标识的库存组
	 *
	 * @param tenantId
	 * @param groupId
	 * @return
	 */
	@Override
	public StorageGroup queryByGroupId(String tenantId, String groupId) {
		StorageGroup group = storageGroupMapper.selectByPrimaryKey(groupId);
		if (group!=null && !group.getTenantId().equals(tenantId)){
			group = null;
		}
		return group;
	}

	/**
	 * 更新指定库存组标识的库存组信息
	 *
	 * @param group
	 * @return
	 */
	@Override
	public int updateById(StorageGroup group) {
		group.setOperTime(DateUtils.currTimeStamp());
		return storageGroupMapper.updateByPrimaryKey(group);
	}

	/**
	 * 查询某个标准品下的库存组列表,创建时间倒序显示
	 *
	 * @param tenantId
	 * @param standedProdId
	 * @return
	 */
	@Override
	public List<StorageGroup> queryOfStandedProd(String tenantId, String standedProdId) {
		StorageGroupCriteria example = new StorageGroupCriteria();
		example.setOrderByClause(" CREATE_TIME desc");
		example.createCriteria().andTenantIdEqualTo(tenantId).andStandedProdIdEqualTo(standedProdId);
		return storageGroupMapper.selectByExample(example);
	}

	/**
	 * 修改库存组价格信息
	 */
	@Override
	public int updateStorGroupPrice(StorageGroup storageGroup) {
		storageGroup.setOperTime(DateUtils.currTimeStamp());
		return storageGroupMapper.updateByPrimaryKeySelective(storageGroup);
	}

	@Override
	public int countStorGroupByProdID(String tenantId, String standedProdId) {
		StorageGroupCriteria example = new StorageGroupCriteria();
		example.createCriteria().andTenantIdEqualTo(tenantId).andStandedProdIdEqualTo(tenantId).andStateNotEqualTo("3")
				.andStateNotEqualTo("31");
		return storageGroupMapper.countByExample(example);
	}

	/**
	 * 分页查询某个标准品下的库存组列表
	 */
	@Override
	public PageInfoResponse<StorageGroup> queryPageOfStandedProd(String tenantId, String standedProdId, Integer pageNo,
			Integer pageSize)
	{
		StorageGroupCriteria example = new StorageGroupCriteria();
		example.setLimitStart((pageNo - 1) * pageSize);
		example.setLimitEnd(pageSize);
		List<String> stateList = new ArrayList<String>();
		stateList.add("3");
		stateList.add("31");
		example.createCriteria().andTenantIdEqualTo(tenantId).andStandedProdIdEqualTo(standedProdId)
				.andStateNotIn(stateList);
		PageInfoResponse<StorageGroup> storageGroupPage = new PageInfoResponse<>();
		storageGroupPage.setResult(storageGroupMapper.selectByExample(example));
		storageGroupPage.setPageNo(pageNo);
		storageGroupPage.setPageSize(pageSize);
		return storageGroupPage;
	}

	@Override
	public PageInfoResponse<StorageGroup> queryPageOfSearch(StorageGroupPageQueryVo storageGroupPageQueryVo) {
		StorageGroupCriteria example = new StorageGroupCriteria();
		StorageGroupCriteria.Criteria request = example.createCriteria();
		// 设置状态为除废弃外的所有状态
		List<String> stateList = new ArrayList<String>();
		stateList.add(StorageConstants.StorageGroup.State.DISCARD);
		stateList.add(StorageConstants.StorageGroup.State.AUTO_DISCARD);
		// 设置条件参数
		request.andTenantIdEqualTo(storageGroupPageQueryVo.getTenantId()).andStateNotIn(stateList);
		if (storageGroupPageQueryVo.getStorageGroupName() != null)
			request.andStorageGroupNameLike("%" + storageGroupPageQueryVo.getStorageGroupName() + "%");
		if (storageGroupPageQueryVo.getStorageGroupId() != null)
			request.andStorageGroupIdEqualTo(storageGroupPageQueryVo.getStorageGroupId());
		if (storageGroupPageQueryVo.getStandedProdId() != null)
			request.andStandedProdIdEqualTo(storageGroupPageQueryVo.getStandedProdId());
		// 标准品名称
		if (storageGroupPageQueryVo.getStandedProductName() != null) {
			List<String> standedProductIdList = standedProductAtomSV
					.queryIdByName("%" + storageGroupPageQueryVo.getStandedProductName() + "%");
			if(!CollectionUtil.isEmpty(standedProductIdList)){
				request.andStandedProdIdIn(standedProductIdList);
			}
		}
		if(storageGroupPageQueryVo.getCreateTimeStart() != null && storageGroupPageQueryVo.getCreateTimeEnd() != null)
			request.andCreateTimeBetween(storageGroupPageQueryVo.getCreateTimeStart(), storageGroupPageQueryVo.getCreateTimeEnd());
		//获取结果总数
		int count = storageGroupMapper.countByExample(example);
		//设置页码相关参数
		example.setLimitStart((storageGroupPageQueryVo.getPageNo() - 1) * storageGroupPageQueryVo.getPageSize());
		example.setLimitEnd(storageGroupPageQueryVo.getPageSize());
		//新建返回对象
		PageInfoResponse<StorageGroup> StorageGroupPage = new PageInfoResponse<>();
		StorageGroupPage.setCount(count);
		StorageGroupPage.setPageNo(storageGroupPageQueryVo.getPageNo());
		StorageGroupPage.setPageSize(storageGroupPageQueryVo.getPageSize());
		StorageGroupPage.setResult(storageGroupMapper.selectByExample(example));
		//设置总页数
		boolean isDivisible = count%storageGroupPageQueryVo.getPageSize() == 0;
		if(isDivisible)
			StorageGroupPage.setPageCount(count%storageGroupPageQueryVo.getPageSize());
		if(!isDivisible)
			StorageGroupPage.setPageCount(count%storageGroupPageQueryVo.getPageSize() + 1);
			
		return StorageGroupPage;
	}

	/**
	 * 统计所有非废弃库存组
	 *
	 * @return
	 */
	@Override
	public int countOfNoDiscard() {
		StorageGroupCriteria example = new StorageGroupCriteria();
		example.createCriteria().andStateNotIn(discardList);
		return storageGroupMapper.countByExample(example);
	}

	/**
	 * 查询指定分页的集合
	 *
	 * @param pageNum 要查询页码
	 * @param pageSize 每页的条目数
	 * @param hasDiscard 是否包含废弃库存组
	 * @return
	 */
	@Override
	public List<StorageGroup> queryOfPage(int pageNum, int pageSize, boolean hasDiscard) {
		StorageGroupCriteria example = new StorageGroupCriteria();
		//设置页码相关参数
		example.setLimitStart((pageNum - 1) * pageSize);
		example.setLimitEnd(pageSize);
		if (!hasDiscard){
			example.createCriteria().andStateNotIn(discardList);
		}
		return storageGroupMapper.selectByExample(example);
	}

	@Override
	public PageInfo<StorageGroupAttach4List> queryForGroupList(StoGroupPageQueryVo queryVo){
		//获取结果总数
		int count = attachMapper.count(queryVo);
		int pageSize = queryVo.getPageSize(),
				pageNo = queryVo.getPageNo();
		if (pageNo<1)
			pageNo = 1;
		queryVo.setOrderByClause("OPER_TIME desc");
		//设置页码相关参数
		int start = (pageNo - 1) * pageSize;
		String limitStr = "limit "+start+","+pageSize;
		//新建返回对象
		PageInfo<StorageGroupAttach4List> StorageGroupPage = new PageInfo<>();
		StorageGroupPage.setCount(count);
		StorageGroupPage.setPageNo(pageNo);
		StorageGroupPage.setPageSize(pageSize);
		StorageGroupPage.setResult(attachMapper.query(queryVo,limitStr));
		//设置总页数
		boolean isDivisible = count%pageSize == 0;
		int pageCount = count/pageSize;
		StorageGroupPage.setPageCount(isDivisible?pageCount:pageCount+1);
		return StorageGroupPage;
	}
}
