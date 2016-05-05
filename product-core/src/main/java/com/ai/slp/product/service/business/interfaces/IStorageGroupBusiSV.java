package com.ai.slp.product.service.business.interfaces;

import com.ai.slp.product.api.storage.param.STOStorageGroup;

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
    public int installGroup(STOStorageGroup storageGroup);
}
