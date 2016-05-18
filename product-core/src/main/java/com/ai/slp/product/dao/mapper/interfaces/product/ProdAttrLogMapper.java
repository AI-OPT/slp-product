package com.ai.slp.product.dao.mapper.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdAttrLog;
import com.ai.slp.product.dao.mapper.bo.product.ProdAttrLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProdAttrLogMapper {
    int countByExample(ProdAttrLogCriteria example);

    int deleteByExample(ProdAttrLogCriteria example);

    int deleteByPrimaryKey(String logId);

    int insert(ProdAttrLog record);

    int insertSelective(ProdAttrLog record);

    List<ProdAttrLog> selectByExample(ProdAttrLogCriteria example);

    ProdAttrLog selectByPrimaryKey(String logId);

    int updateByExampleSelective(@Param("record") ProdAttrLog record, @Param("example") ProdAttrLogCriteria example);

    int updateByExample(@Param("record") ProdAttrLog record, @Param("example") ProdAttrLogCriteria example);

    int updateByPrimaryKeySelective(ProdAttrLog record);

    int updateByPrimaryKey(ProdAttrLog record);
}