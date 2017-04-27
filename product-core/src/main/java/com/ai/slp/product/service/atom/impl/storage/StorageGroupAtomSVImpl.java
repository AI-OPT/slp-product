package com.ai.slp.product.service.atom.impl.storage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.constants.CommonConstants;
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

/**
 * 库存组原子操作 Created by jackieliu on 16/4/29.
 */
@Component
public class StorageGroupAtomSVImpl implements IStorageGroupAtomSV {
	@Autowired
	StorageGroupMapper storageGroupMapper;
	@Autowired
	StorageGroupAttachMapper attachMapper;
	@Autowired
	IStandedProductAtomSV standedProductAtomSV;

	private static List<String> DISCARD_LIST = new ArrayList<>();

	static {
		DISCARD_LIST.add(StorageConstants.StorageGroup.State.DISCARD);
		DISCARD_LIST.add(StorageConstants.StorageGroup.State.AUTO_DISCARD);
	}
	/**
	 * 查询标准品下没有废弃的库存组数量
	 * 
	 * @param tenantId
	 * @param standedProdId
	 * @return
	 */
	@Override
	public int countNoDiscard(String tenantId, String standedProdId) {
		StorageGroupCriteria example = new StorageGroupCriteria();
		List<String> discard = new ArrayList<>();
		discard.add(StorageConstants.StorageGroup.State.DISCARD);
		discard.add(StorageConstants.StorageGroup.State.AUTO_DISCARD);
		example.createCriteria().andTenantIdEqualTo(tenantId).andStandedProdIdEqualTo(standedProdId)
				.andStateNotIn(discard);
		return storageGroupMapper.countByExample(example);
	}

	/**
	 * 查询标准品下状态为"启用"的库存组数量
	 * 
	 * @param tenantId
	 * @param standedProdId
	 * @return
	 */
	@Override
	public int countActive(String tenantId, String standedProdId) {
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
		if (group.getCreateTime() == null){
			group.setCreateTime(DateUtils.currTimeStamp());
		}
		group.setOperTime(group.getCreateTime());
		group.setOperId(group.getCreateId());
		return storageGroupMapper.insert(group);
	}

	/**
	 * 添加库存组信息
	 *
	 * @param group
	 * @return
	 */
	@Override
	public int installGroup(StorageGroup group,String storageGroupId) {
		group.setStorageGroupId(storageGroupId);
		if (group.getCreateTime() == null){
			group.setCreateTime(DateUtils.currTimeStamp());
		}
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
	public StorageGroup queryByGroupIdAndSupplierId(String tenantId, String supplierId, String groupId) {
		StorageGroup group = storageGroupMapper.selectByPrimaryKey(groupId);
		/*if (group==null
				|| !group.getTenantId().equals(tenantId)
				|| !group.getSupplierId().equals(supplierId)){
			group = null;
		}*/
		return group;
	}

	/**
	 * 查询指定租户下指定标识的库存组
	 *
	 * @param tenantId
	 * @param groupId
	 * @return
	 */
	@Override
	public StorageGroup queryByGroupId(String tenantId, String groupId) {
		StorageGroup group = storageGroupMapper.selectByPrimaryKey(groupId);
		if (group==null
				|| !group.getTenantId().equals(tenantId)){
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
	public List<StorageGroup> queryOfStandedProd(String tenantId,String supplierId, String standedProdId) {
		StorageGroupCriteria example = new StorageGroupCriteria();
		
		example.setOrderByClause(" CREATE_TIME desc");
		
		StorageGroupCriteria.Criteria criteria = example.createCriteria()
				.andTenantIdEqualTo(tenantId).andStandedProdIdEqualTo(standedProdId);
		if (!CommonConstants.ALL_SUPPLIER.equals(supplierId)){
			criteria.andSupplierIdEqualTo(supplierId);
		}
		return storageGroupMapper.selectByExample(example);
	}

	/**
	 * 查询某个标准品下的非废弃库存组列表
	 *
	 * @param tenantId
	 * @param supplierId
	 * @param standedProdId
	 * @return
	 */
	@Override
	public List<StorageGroup> queryNoDiscardOfStandProd(String tenantId, String supplierId, String standedProdId) {
		StorageGroupCriteria example = new StorageGroupCriteria();
		
		//example.setOrderByClause(" CREATE_TIME desc");
		
		StorageGroupCriteria.Criteria criteria = example.createCriteria()
				.andTenantIdEqualTo(tenantId).andStandedProdIdEqualTo(standedProdId);
		if (!CommonConstants.ALL_SUPPLIER.equals(supplierId)){
			criteria.andSupplierIdEqualTo(supplierId);
		}
		criteria.andStateNotIn(DISCARD_LIST);
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

	/**
	 * 通过标准品ID查询标准品下的非废弃库存组数量
	 * @param tenantId
	 * @param supplierId
	 * @param standedProdId
     * @return
     */
	@Override
	public int countStorGroupByProdID(String tenantId,String supplierId, String standedProdId) {
		StorageGroupCriteria example = new StorageGroupCriteria();
		StorageGroupCriteria.Criteria criteria = example.createCriteria().andTenantIdEqualTo(tenantId)
				.andStandedProdIdEqualTo(standedProdId).andStateNotIn(DISCARD_LIST);
		//若不是查询全部,则指定查询的销售商(商户)标识
		if (!CommonConstants.ALL_SUPPLIER.equals(supplierId)){
			criteria.andSupplierIdEqualTo(supplierId);
		}
		return storageGroupMapper.countByExample(example);
	}

	/**
	 * 分页查询某个标准品下的库存组列表
	 */
	@Override
	public PageInfoResponse<StorageGroup> queryPageOfStandedProd(
			String tenantId, String supplierId,String standedProdId, Integer pageNo,Integer pageSize)
	{
		StorageGroupCriteria example = new StorageGroupCriteria();
		example.setLimitStart((pageNo - 1) * pageSize);
		example.setLimitEnd(pageSize);
		StorageGroupCriteria.Criteria criteria = example.createCriteria()
				.andTenantIdEqualTo(tenantId).andStandedProdIdEqualTo(standedProdId).andStateNotIn(DISCARD_LIST);
		//若不是查询全部,则指定查询的销售商(商户)标识
		if (!CommonConstants.ALL_SUPPLIER.equals(supplierId)){
			criteria.andSupplierIdEqualTo(supplierId);
		}
		PageInfoResponse<StorageGroup> storageGroupPage = new PageInfoResponse<>();
		storageGroupPage.setResult(storageGroupMapper.selectByExample(example));
		storageGroupPage.setPageNo(pageNo);
		storageGroupPage.setPageSize(pageSize);
		return storageGroupPage;
	}
	/**
	 * 根据搜索条件分页查询库存组列表
	 *
	 * @param queryVo
	 * @return
	 * @author lipeng16
	 */
	@Override
	public PageInfo<StorageGroup> queryPageOfSearch(StorageGroupPageQueryVo queryVo) {
		StorageGroupCriteria example = new StorageGroupCriteria();
		StorageGroupCriteria.Criteria request = example.createCriteria();
		// 设置状态为除废弃外的所有状态
		request.andTenantIdEqualTo(queryVo.getTenantId()).andStateNotIn(DISCARD_LIST);
		if (queryVo.getStorageGroupName() != null){
			request.andStorageGroupNameLike("%" + queryVo.getStorageGroupName() + "%");
		}
		if (queryVo.getStorageGroupId() != null){
			request.andStorageGroupIdEqualTo(queryVo.getStorageGroupId());
		}
		if (queryVo.getStandedProdId() != null){
			request.andStandedProdIdEqualTo(queryVo.getStandedProdId());
		}
		//若不是查询全部,则指定查询的销售商(商户)标识
		if (!CommonConstants.ALL_SUPPLIER.equals(queryVo.getSupplierId())){
			request.andSupplierIdEqualTo(queryVo.getSupplierId());
		}
		if(queryVo.getCreateTimeStart() != null && queryVo.getCreateTimeEnd() != null){
			request.andCreateTimeBetween(queryVo.getCreateTimeStart(), queryVo.getCreateTimeEnd());
		}
		//设置页码相关参数
		example.setLimitStart((queryVo.getPageNo() - 1) * queryVo.getPageSize());
		example.setLimitEnd(queryVo.getPageSize());
		int count = 0;
		List<StorageGroup> result = new ArrayList<>();
		// 标准品名称
		if (StringUtils.isNotBlank(queryVo.getStandedProductName())) {
			List<String> standedProductIdList = standedProductAtomSV
					.queryIdByName("%" + queryVo.getStandedProductName() + "%");
			//若为空,则直接返回空
			if(!CollectionUtil.isEmpty(standedProductIdList)){
				//获取结果总数
				count = storageGroupMapper.countByExample(example);
				result = storageGroupMapper.selectByExample(example);
			}
		}

		//新建返回对象
		PageInfo<StorageGroup> StorageGroupPage = new PageInfo<>();
		StorageGroupPage.setCount(count);
		StorageGroupPage.setPageNo(queryVo.getPageNo());
		StorageGroupPage.setPageSize(queryVo.getPageSize());
		StorageGroupPage.setResult(result);
		//设置总页数
		int pageCount = count/queryVo.getPageSize();
		if (count % queryVo.getPageSize() == 0){
			pageCount++;
		}
		StorageGroupPage.setPageCount(pageCount);
			
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
		example.createCriteria().andStateNotIn(DISCARD_LIST);
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
			example.createCriteria().andStateNotIn(DISCARD_LIST);
		}
		return storageGroupMapper.selectByExample(example);
	}

	@Override
	public PageInfo<StorageGroupAttach4List> queryForGroupList(StoGroupPageQueryVo queryVo){
		//获取结果总数
		int count = attachMapper.count(queryVo);
		int pageSize = queryVo.getPageSize(),
				pageNo = queryVo.getPageNo();
		if (pageNo<1){
			pageNo = 1;
		}
		
		//queryVo.setOrderByClause("OPER_TIME desc");
		
		//设置页码相关参数
		int start = (pageNo - 1) * pageSize;
		String limitStr = "limit "+start+","+pageSize;
		genLikeStr(queryVo);
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

	/**
	 * 判断库存组是否为废弃状态
	 *
	 * @param group
	 * @return
	 */
	@Override
	public boolean isDiscard(StorageGroup group) {
		return group!=null&&DISCARD_LIST.contains(group.getState());
	}

	/**
	 * 完善like检查信息
	 */
	private void genLikeStr(StoGroupPageQueryVo queryVo){
		//库存组ID
		if (StringUtils.isNotBlank(queryVo.getStorageGroupId())){
			queryVo.setStorageGroupId("%"+queryVo.getStorageGroupId()+"%");
		}
		//库存组名称
		if (StringUtils.isNotBlank(queryVo.getStorageGroupName())){
			queryVo.setStorageGroupId("%"+queryVo.getStorageGroupName()+"%");
		}
		//标准品id
		if (StringUtils.isNotBlank(queryVo.getStandedProdId())){
			queryVo.setStorageGroupId("%"+queryVo.getStandedProdId()+"%");
		}
		//标准品名称
		if (StringUtils.isNotBlank(queryVo.getStandedProductName())){
			queryVo.setStorageGroupId("%"+queryVo.getStandedProductName()+"%");
		}
	}
}
