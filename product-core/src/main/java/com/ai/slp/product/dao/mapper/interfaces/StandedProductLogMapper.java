package com.ai.slp.product.dao.mapper.interfaces;

import com.ai.slp.product.dao.mapper.bo.StandedProductLog;
import com.ai.slp.product.dao.mapper.bo.StandedProductLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StandedProductLogMapper {
    int countByExample(StandedProductLogCriteria example);

    int deleteByExample(StandedProductLogCriteria example);

    int deleteByPrimaryKey(String standedProdId);

    int insert(StandedProductLog record);

    int insertSelective(StandedProductLog record);

    List<StandedProductLog> selectByExample(StandedProductLogCriteria example);

    StandedProductLog selectByPrimaryKey(String standedProdId);

    int updateByExampleSelective(@Param("record") StandedProductLog record, @Param("example") StandedProductLogCriteria example);

    int updateByExample(@Param("record") StandedProductLog record, @Param("example") StandedProductLogCriteria example);

    int updateByPrimaryKeySelective(StandedProductLog record);

    int updateByPrimaryKey(StandedProductLog record);
}