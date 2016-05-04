package com.ai.slp.product.dao.mapper.interfaces.storage;

import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StorageMapper {
    int countByExample(StorageCriteria example);

    int deleteByExample(StorageCriteria example);

    int deleteByPrimaryKey(String storageId);

    int insert(Storage record);

    int insertSelective(Storage record);

    List<Storage> selectByExample(StorageCriteria example);

    Storage selectByPrimaryKey(String storageId);

    int updateByExampleSelective(@Param("record") Storage record, @Param("example") StorageCriteria example);

    int updateByExample(@Param("record") Storage record, @Param("example") StorageCriteria example);

    int updateByPrimaryKeySelective(Storage record);

    int updateByPrimaryKey(Storage record);
}