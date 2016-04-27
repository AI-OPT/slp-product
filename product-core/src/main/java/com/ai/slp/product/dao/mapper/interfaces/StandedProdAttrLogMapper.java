package com.ai.slp.product.dao.mapper.interfaces;

import com.ai.slp.product.dao.mapper.bo.StandedProdAttrLog;
import com.ai.slp.product.dao.mapper.bo.StandedProdAttrLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StandedProdAttrLogMapper {
    int countByExample(StandedProdAttrLogCriteria example);

    int deleteByExample(StandedProdAttrLogCriteria example);

    int deleteByPrimaryKey(String logId);

    int insert(StandedProdAttrLog record);

    int insertSelective(StandedProdAttrLog record);

    List<StandedProdAttrLog> selectByExample(StandedProdAttrLogCriteria example);

    StandedProdAttrLog selectByPrimaryKey(String logId);

    int updateByExampleSelective(@Param("record") StandedProdAttrLog record, @Param("example") StandedProdAttrLogCriteria example);

    int updateByExample(@Param("record") StandedProdAttrLog record, @Param("example") StandedProdAttrLogCriteria example);

    int updateByPrimaryKeySelective(StandedProdAttrLog record);

    int updateByPrimaryKey(StandedProdAttrLog record);
}