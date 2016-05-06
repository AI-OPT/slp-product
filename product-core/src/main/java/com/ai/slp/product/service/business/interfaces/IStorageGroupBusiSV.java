package com.ai.slp.product.service.business.interfaces;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.slp.product.api.storage.param.STOStorageGroup;
import com.ai.slp.product.api.storage.param.StorageGroupInfo;
import com.ai.slp.product.api.storage.param.StorageGroupSalePrice;

import java.util.List;

/**
 * 库存组业务操作
 * Created by jackieliu on 16/5/4.
 */
public interface IStorageGroupBusiSV {

    /**
     * 添加库存组
     *
     * @param storageGroup
     * @return
     */
    public int addGroup(STOStorageGroup storageGroup);

    /**
     * 更新库存组
     *
     * @param storageGroup
     * @return
     */
    public int updateGroup(STOStorageGroup storageGroup);

    /**
     * 查询指定标准品下的库存组信息,包括库存信息
     *
     * @param tenantId
     * @param productId
     * @return
     */
    public List<StorageGroupInfo> queryGroupInfoByNormProId(String tenantId,String productId);

    /**
     * 查询单个库存组的信息
     *
     * @param tenantId
     * @param groupId
     * @return
     */
    public StorageGroupInfo queryGroupInfoByGroupId(String tenantId,String groupId);
    
    /**
     * 更新库存组价格信息
     * @param salePrice
     * @return
     * @author lipeng
    *  @ApiCode
     */
    public int updateStorageGroupPrice(StorageGroupSalePrice salePrice);
}
