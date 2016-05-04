package com.ai.slp.product.dao.mapper.interfaces.storage;

import com.ai.slp.product.dao.mapper.bo.storage.StorageLog;
import com.ai.slp.product.dao.mapper.bo.storage.StorageLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StorageLogMapper {
    int countByExample(StorageLogCriteria example);

    int deleteByExample(StorageLogCriteria example);

    int deleteByPrimaryKey(String logId);

    int insert(StorageLog record);

    int insertSelective(StorageLog record);

    List<StorageLog> selectByExample(StorageLogCriteria example);

    StorageLog selectByPrimaryKey(String logId);

    int updateByExampleSelective(@Param("record") StorageLog record, @Param("example") StorageLogCriteria example);

    int updateByExample(@Param("record") StorageLog record, @Param("example") StorageLogCriteria example);

    int updateByPrimaryKeySelective(StorageLog record);

    int updateByPrimaryKey(StorageLog record);
}