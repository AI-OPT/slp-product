package com.ai.slp.product.service.business.interfaces;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.storage.param.*;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;

import java.util.List;
import java.util.Map;

/**
 * 库存业务操作
 * Created by jackieliu on 16/5/4.
 */

public interface IStorageBusiSV {

    /**
     * 废弃库存
     * @param storage
     */
    public void discardStorage(String tenantId,Storage storage, Long operId,boolean isAuto);

    /**
     * 自动停用库存
     * @param storage
     */
    public void autoStopStorage(Storage storage);

    /**
     * 更改库存状态
     * @param tenantId
     * @param storageId
     * @param state
     * @param operId
     * @return 返回更改成功的库存信息
     */
    public Storage changeStorageStats(String tenantId,String supplierId,String storageId,String state,Long operId);

    /**
	  * 批量更新库存销售价
	  *
	 * @param salePrice
	 * @return
	 * @author lipeng16
	 */
	public int updateNoSkuStoSalePrice(StoNoSkuSalePriceReq salePrice);

    /**
     * 批量更新有销售属性库存销售价
     *
     * @param salePrice
     * @return
     * @author lipeng16
     */
    public int updateSkuStoSalePrice(StoSkuSalePrice salePrice);
	
	/**
	 * 查询标准品列表,包含标准品的库存组,适用于库存组定最低最高销售价<br>
     * 库存组不包括废弃状态的
	 *
	 * @param groupQuery
	 * @return
	 * @author lipeng16
	 */
	public PageInfoResponse<StorageGroup4SaleList> queryGroupsForSalePrice(StorageGroupQueryPage groupQuery);
	
	 /**
     * 保存库存信息
     *
     * @param stoStorage
     * @return
     * @author lipeng16
     */
    public String saveStorage(STOStorage stoStorage);
    
    /**
     * 修改库存信息
     *
     * @param stoStorage
     * @return
     * @author lipeng16
     */
    public int updateStorageNameWarn(STOStorage stoStorage);
    
    /**
     * 查看库存信息
     *
     * @param tenantId
     * @param supplierId
     * @param storageId
     * @return
     * @author lipeng16
     */
    public StorageRes queryStorageById(String tenantId,String supplierId,String storageId);
    
    /**
     * 查询SKU库存信息,包括废弃
     *
     * @param tenantId
     * @param supplierId
     * @param storageId
     * @return
     * @author lipeng16
     */
    public Map<String,SkuStorageInfo> querySkuStorageById(String tenantId, String supplierId, String storageId);
    
    /**
     * 新增SKU库存(有销售属性)
     *
     * @param skuStorageAddList
     * @return
     * @author lipeng16
     */
    public int insertSkuStorage(List<SkuStorageAdd> skuStorageAddList);
    
    /**
     * 变更库存优先级
     *
     * @param StoPriorityCharge
     * @return
     * @author lipeng16
     */
    public void updateStoragePriority(StoragePriorityCharge StoPriorityCharge);
}
