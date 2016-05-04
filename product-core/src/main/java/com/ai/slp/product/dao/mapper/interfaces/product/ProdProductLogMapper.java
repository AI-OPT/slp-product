package com.ai.slp.product.dao.mapper.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdProductLog;
import com.ai.slp.product.dao.mapper.bo.product.ProdProductLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProdProductLogMapper {
    int countByExample(ProdProductLogCriteria example);

    int deleteByExample(ProdProductLogCriteria example);

    int deleteByPrimaryKey(String logId);

    int insert(ProdProductLog record);

    int insertSelective(ProdProductLog record);

    List<ProdProductLog> selectByExample(ProdProductLogCriteria example);

    ProdProductLog selectByPrimaryKey(String logId);

    int updateByExampleSelective(@Param("record") ProdProductLog record, @Param("example") ProdProductLogCriteria example);

    int updateByExample(@Param("record") ProdProductLog record, @Param("example") ProdProductLogCriteria example);

    int updateByPrimaryKeySelective(ProdProductLog record);

    int updateByPrimaryKey(ProdProductLog record);
}