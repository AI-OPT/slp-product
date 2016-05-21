package com.ai.slp.product.service.business.interfaces;

import java.util.List;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.storage.param.STOStorage;
import com.ai.slp.product.api.storage.param.SkuStorageAndProd;
import com.ai.slp.product.api.storage.param.StorageGroup4SaleList;
import com.ai.slp.product.api.storage.param.StorageGroupQueryPage;
import com.ai.slp.product.api.storage.param.StorageRes;
import com.ai.slp.product.api.storage.param.StorageSalePrice;
import com.ai.slp.product.dao.mapper.bo.storage.SkuStorage;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;

/**
 * 库存业务操作
 * Created by jackieliu on 16/5/4.
 */

public interface IStorageBusiSV {

    /**
     * 废弃库存组
     * @param storage
     */
    public void discardStorage(Storage storage);

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
     */
    public void changeStorageStats(String tenantId,String storageId,String state,Long operId);
	 /**
	  * 批量更新库存销售价
	  *
	 * @param salePrice
	 * @return
	 * @author lipeng16
	 */
	public int updateMultiStorageSalePrice(StorageSalePrice salePrice);
	
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
    public int saveStorage(STOStorage stoStorage);
    
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
     * @param storageId
     * @return
     * @author lipeng16
     */
    public StorageRes queryStorageById(String storageId);
    
    /**
     * 查询SKU库存信息
     *
     * @param storageId
     * @return
     * @author lipeng16
     */
    public List<SkuStorageAndProd> querySkuStorageById(String storageId);
}
