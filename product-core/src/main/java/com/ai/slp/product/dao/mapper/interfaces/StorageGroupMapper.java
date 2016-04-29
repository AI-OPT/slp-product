package com.ai.slp.product.dao.mapper.interfaces;

import com.ai.slp.product.dao.mapper.bo.StorageGroup;
import com.ai.slp.product.dao.mapper.bo.StorageGroupCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StorageGroupMapper {
    int countByExample(StorageGroupCriteria example);

    int deleteByExample(StorageGroupCriteria example);

    int deleteByPrimaryKey(Long storageGroupId);

    int insert(StorageGroup record);

    int insertSelective(StorageGroup record);

    List<StorageGroup> selectByExample(StorageGroupCriteria example);

    StorageGroup selectByPrimaryKey(Long storageGroupId);

    int updateByExampleSelective(@Param("record") StorageGroup record, @Param("example") StorageGroupCriteria example);

    int updateByExample(@Param("record") StorageGroup record, @Param("example") StorageGroupCriteria example);

    int updateByPrimaryKeySelective(StorageGroup record);

    int updateByPrimaryKey(StorageGroup record);
}