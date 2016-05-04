package com.ai.slp.product.dao.mapper.interfaces.storage;

import com.ai.slp.product.dao.mapper.bo.storage.StorageGroupLog;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroupLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StorageGroupLogMapper {
    int countByExample(StorageGroupLogCriteria example);

    int deleteByExample(StorageGroupLogCriteria example);

    int deleteByPrimaryKey(String logId);

    int insert(StorageGroupLog record);

    int insertSelective(StorageGroupLog record);

    List<StorageGroupLog> selectByExample(StorageGroupLogCriteria example);

    StorageGroupLog selectByPrimaryKey(String logId);

    int updateByExampleSelective(@Param("record") StorageGroupLog record, @Param("example") StorageGroupLogCriteria example);

    int updateByExample(@Param("record") StorageGroupLog record, @Param("example") StorageGroupLogCriteria example);

    int updateByPrimaryKeySelective(StorageGroupLog record);

    int updateByPrimaryKey(StorageGroupLog record);
}