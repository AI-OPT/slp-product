package com.ai.slp.product.service.atom.interfaces.storage;

import com.ai.slp.product.dao.mapper.bo.storage.Storage;

import java.util.List;

/**
 * Created by jackieliu on 16/5/5.
 */
public interface IStorageAtomSV {

    /**
     * 查询指定库存组下的库存信息,按照优先级正序排序
     * @param tenantId
     * @param groupId
     * @return
     */
    public List<Storage> queryOfGroup(String tenantId,Long groupId);
}
