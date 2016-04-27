package com.ai.slp.product.dao.mapper.interfaces;

import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDef;
import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDefCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProdAttrvalueDefMapper {
    int countByExample(ProdAttrvalueDefCriteria example);

    int deleteByExample(ProdAttrvalueDefCriteria example);

    int deleteByPrimaryKey(String attrvalueDefId);

    int insert(ProdAttrvalueDef record);

    int insertSelective(ProdAttrvalueDef record);

    List<ProdAttrvalueDef> selectByExample(ProdAttrvalueDefCriteria example);

    ProdAttrvalueDef selectByPrimaryKey(String attrvalueDefId);

    int updateByExampleSelective(@Param("record") ProdAttrvalueDef record, @Param("example") ProdAttrvalueDefCriteria example);

    int updateByExample(@Param("record") ProdAttrvalueDef record, @Param("example") ProdAttrvalueDefCriteria example);

    int updateByPrimaryKeySelective(ProdAttrvalueDef record);

    int updateByPrimaryKey(ProdAttrvalueDef record);
}