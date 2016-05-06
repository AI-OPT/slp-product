package com.ai.slp.product.dao.mapper.interfaces.storage;

import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroupCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StorageGroupMapper {
    int countByExample(StorageGroupCriteria example);

    int deleteByExample(StorageGroupCriteria example);

    int deleteByPrimaryKey(String storageGroupId);

    int insert(StorageGroup record);

    int insertSelective(StorageGroup record);

    List<StorageGroup> selectByExample(StorageGroupCriteria example);

    StorageGroup selectByPrimaryKey(String storageGroupId);

    int updateByExampleSelective(@Param("record") StorageGroup record, @Param("example") StorageGroupCriteria example);

    int updateByExample(@Param("record") StorageGroup record, @Param("example") StorageGroupCriteria example);

    int updateByPrimaryKeySelective(StorageGroup record);

    int updateByPrimaryKey(StorageGroup record);
}