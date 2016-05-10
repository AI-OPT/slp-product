package com.ai.slp.product.dao.mapper.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdSkuLog;
import com.ai.slp.product.dao.mapper.bo.product.ProdSkuLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProdSkuLogMapper {
    int countByExample(ProdSkuLogCriteria example);

    int deleteByExample(ProdSkuLogCriteria example);

    int deleteByPrimaryKey(String logId);

    int insert(ProdSkuLog record);

    int insertSelective(ProdSkuLog record);

    List<ProdSkuLog> selectByExample(ProdSkuLogCriteria example);

    ProdSkuLog selectByPrimaryKey(String logId);

    int updateByExampleSelective(@Param("record") ProdSkuLog record, @Param("example") ProdSkuLogCriteria example);

    int updateByExample(@Param("record") ProdSkuLog record, @Param("example") ProdSkuLogCriteria example);

    int updateByPrimaryKeySelective(ProdSkuLog record);

    int updateByPrimaryKey(ProdSkuLog record);
}