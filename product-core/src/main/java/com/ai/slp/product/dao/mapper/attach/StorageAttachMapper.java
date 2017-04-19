package com.ai.slp.product.dao.mapper.attach;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.storage.StorageCriteria;

/**
 * Created by jackieliu on 16/8/1.
 */
public interface StorageAttachMapper {

    /**
     * 统计符合条件的库存的总量的和
     * @param example
     * @return
     */
    public Long sumTotalByExample(StorageCriteria example);

    @Select("select sum(s.USABLE_NUM) from `storage` s where s.PROD_ID = #{prodId} and s.PRIORITY_NUMBER = #{priority} and s.STATE = #{state}")
	public int getProdUsableNumSum(@Param("prodId") String prodId,
			@Param("priority") String priority,
			@Param("state") String state);

    /**
     * 根据skuStorageId更新storage可用库存
     * @param skuStorageId
     * @param skuNum
     * @author Gavin
     * @UCUSER
     */
    //@Update("update `storage` t set t.USABLE_NUM = (t.USABLE_NUM + (#{skuNum})) where t.STORAGE_ID = (select k.STORAGE_ID from sku_storage k where k.SKU_STORAGE_ID = #{skuStorageId})")
    @Update("update `storage` t set t.USABLE_NUM = (t.USABLE_NUM + (#{skuNum})) where t.STORAGE_ID = #{skuStorageId}")
    public void updateStorageUsableNumSQL(@Param("skuStorageId") String skuStorageId, @Param("skuNum")  int skuNum);

}
