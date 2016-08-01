package com.ai.slp.product.dao.mapper.attach;

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
}
