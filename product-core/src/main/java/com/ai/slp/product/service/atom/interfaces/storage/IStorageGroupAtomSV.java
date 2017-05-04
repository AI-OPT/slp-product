package com.ai.slp.product.service.atom.interfaces.storage;

import java.util.List;

import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.dao.mapper.attach.StorageGroupAttach4List;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.vo.StoGroupPageQueryVo;
import com.ai.slp.product.vo.StorageGroupPageQueryVo;

/**
 * 库存组原子操作
 *
 * Created by jackieliu on 16/4/29.
 */
public interface IStorageGroupAtomSV {
    /**
     * 查询标准品下没有废弃的库存组数量
     *
     * @param tenantId
     * @param standedProdId
     * @return
     */
    public int countNoDiscard(String tenantId, String standedProdId);

    /**
     * 查询标准品下启用状态的库存组
     *
     * @param tenantId
     * @param standedProdId
     * @return
     */
    public int countActive(String tenantId, String standedProdId);

    /**
     * 添加库存组信息
     *
     * @param group
     * @return
     */
    public int installGroup(StorageGroup group);

    /**
     * 查询指定标识和销售商的库存组
     *
     * @param tenantId
     * @param supplierId
     * @param groupId
     * @return
     */
    public StorageGroup queryByGroupIdAndSupplierId(String tenantId, String supplierId, String groupId);

    /**
     * 查询指定租户下指定标识的库存组
     * @param tenantId
     * @param groupId
     * @return
     */
    public StorageGroup queryByGroupId(String tenantId, String groupId);

    /**
     * 更新指定库存组标识的库存组信息
     *
     * @param group
     * @return
     */
    public int updateById(StorageGroup group);

    /**
     * 查询某个标准品下的库存组列表,创建时间倒序显示
     *
     * @param tenantId
     * @param standedProdId
     * @return
     */
    public List<StorageGroup> queryOfStandedProd(String tenantId,String supplierId,String standedProdId);

    /**
     * 查询某个标准品下的非废弃库存组列表
     * @return
     */
    public List<StorageGroup> queryNoDiscardOfStandProd(String tenantId, String supplierId, String standedProdId);
    
    /**
     * 更新库存组的最低最高销售价
     * 
     * @param storageGroup
     * @return
     * @author lipeng16
     */
    public int updateStorGroupPrice(StorageGroup storageGroup);
    
    /**
     * 通过标准品ID查询标准品下的非废弃库存组数量
     * 
     * @param tenantId
     * @param standedProdId
     * @return 标准品下库存组数量
     * @author lipeng16
     */
    public int countStorGroupByProdID(String tenantId,String supplierId,String standedProdId);
    
    /**
     * 分页查询某个标准品下的库存组列表
     * 
     * @param tenantId
     * @param standedProdId
     * @param pageNo
     * @param pageSize
     * @return
     * @author lipeng16
     */
    public PageInfoResponse<StorageGroup> queryPageOfStandedProd(String tenantId, String supplierId,String standedProdId, Integer pageNo, Integer pageSize);
    
    /**
     * 根据搜索条件分页查询库存组列表
     *
     * @param storageGroupPageQueryVo
     * @return
     * @author lipeng16
     */
    public PageInfo<StorageGroup> queryPageOfSearch(StorageGroupPageQueryVo storageGroupPageQueryVo);

    /**
     * 统计所有非废弃库存组
     * @return
     */
    public int countOfNoDiscard();

    /**
     * 查询指定分页的集合
     *
     * @param pageNum 要查询页码
     * @param pageSize 每页的条目数
     * @param hasDiscard 是否包含废弃库存组
     * @return
     */
    public List<StorageGroup> queryOfPage(int pageNum,int pageSize,boolean hasDiscard);

    /**
     * 根据查询条件进行分页查询库存组信息
     *
     * @param queryVo
     * @return
     */
    public PageInfo<StorageGroupAttach4List> queryForGroupList(StoGroupPageQueryVo queryVo);

    /**
     * 判断库存组是否为废弃状态
     * @param group
     * @return
     */
    public boolean isDiscard(StorageGroup group);

    /**
     * 保存库存组
     * @param group
     * @param storageGroupId
     * @return
     * @author
     */
	int installGroup(StorageGroup group, String storageGroupId);
}
