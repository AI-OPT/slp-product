package com.ai.slp.product.dao.mapper.interfaces;

import com.ai.slp.product.dao.mapper.bo.StandedProductLog;
import com.ai.slp.product.dao.mapper.bo.StandedProductLogCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StandedProductLogMapper {
    int countByExample(StandedProductLogCriteria example);

    int deleteByExample(StandedProductLogCriteria example);

    int deleteByPrimaryKey(String logId);

    int insert(StandedProductLog record);

    int insertSelective(StandedProductLog record);

    List<StandedProductLog> selectByExample(StandedProductLogCriteria example);

    StandedProductLog selectByPrimaryKey(String logId);

    int updateByExampleSelective(@Param("record") StandedProductLog record, @Param("example") StandedProductLogCriteria example);

    int updateByExample(@Param("record") StandedProductLog record, @Param("example") StandedProductLogCriteria example);

    int updateByPrimaryKeySelective(StandedProductLog record);

    int updateByPrimaryKey(StandedProductLog record);
}