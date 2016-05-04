package com.ai.slp.product.dao.mapper.interfaces;

import com.ai.slp.product.dao.mapper.bo.ProdPriceLog;
import com.ai.slp.product.dao.mapper.bo.ProdPriceLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProdPriceLogMapper {
    int countByExample(ProdPriceLogCriteria example);

    int deleteByExample(ProdPriceLogCriteria example);

    int deleteByPrimaryKey(String logId);

    int insert(ProdPriceLog record);

    int insertSelective(ProdPriceLog record);

    List<ProdPriceLog> selectByExample(ProdPriceLogCriteria example);

    ProdPriceLog selectByPrimaryKey(String logId);

    int updateByExampleSelective(@Param("record") ProdPriceLog record, @Param("example") ProdPriceLogCriteria example);

    int updateByExample(@Param("record") ProdPriceLog record, @Param("example") ProdPriceLogCriteria example);

    int updateByPrimaryKeySelective(ProdPriceLog record);

    int updateByPrimaryKey(ProdPriceLog record);
}