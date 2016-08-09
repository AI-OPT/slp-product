package com.ai.slp.product.dao.mapper.attach;

import com.ai.slp.product.dao.mapper.bo.storage.SkuStorage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
     * 查询库存组下启用库存的SKU库存下没有价格的数量
     * @param groupId
     * @return
     */
    public Integer countOfNoPrice(@Param("groupId")String groupId);
}
