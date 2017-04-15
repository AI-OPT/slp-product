package com.ai.slp.product.dao.mapper.attach;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.ai.slp.product.dao.mapper.bo.storage.SkuStorage;

/**
 * Created by jackieliu on 16/8/8.
 */
public interface SkuStorageAttachMapper {

    /**
     * 查询指定优先级下有效的SKU库存
     *
     * @param params
     * @return
     */
    public List<SkuStorage> queryOfPriority(Map<String,Object> params);
 
    /**
     * 查询指定优先级下所有的SKU库存
     *
     * @param params
     * @return
     */
    public List<SkuStorage> queryAllOfPriority(Map<String,Object> params);

    /**
     * 查询库存组下启用库存的SKU库存下没有价格的数量
     * @param groupId
     * @return
     */
    public Integer countOfNoPrice(@Param("groupId")String groupId);

    /**
     * 查询sku价格
     * @param params
     * @return
     */
    public Long selectPriceOfSku(Map<String,Object> params);

    /**
     * 更新sku库存
     * @param skuStorageId
     * @param skuNum
     * @return
     * @author Gavin
     * @UCUSER
     */
    @Update("update sku_storage t set t.USABLE_NUM = (t.USABLE_NUM + (#{skuNum})) where t.SKU_STORAGE_ID = #{skuStorageId}")
	public int updateBySQL(@Param("skuStorageId") String skuStorageId,@Param("skuNum") int skuNum);
    
    
}
