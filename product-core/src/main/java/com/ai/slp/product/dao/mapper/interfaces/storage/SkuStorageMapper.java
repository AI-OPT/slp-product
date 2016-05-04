package com.ai.slp.product.dao.mapper.interfaces.storage;

import com.ai.slp.product.dao.mapper.bo.storage.SkuStorage;
import com.ai.slp.product.dao.mapper.bo.storage.SkuStorageCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SkuStorageMapper {
    int countByExample(SkuStorageCriteria example);

    int deleteByExample(SkuStorageCriteria example);

    int deleteByPrimaryKey(String skuStorageId);

    int insert(SkuStorage record);

    int insertSelective(SkuStorage record);

    List<SkuStorage> selectByExample(SkuStorageCriteria example);

    SkuStorage selectByPrimaryKey(String skuStorageId);

    int updateByExampleSelective(@Param("record") SkuStorage record, @Param("example") SkuStorageCriteria example);

    int updateByExample(@Param("record") SkuStorage record, @Param("example") SkuStorageCriteria example);

    int updateByPrimaryKeySelective(SkuStorage record);

    int updateByPrimaryKey(SkuStorage record);
}