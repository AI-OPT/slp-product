package com.ai.slp.product.dao.mapper.interfaces;

import com.ai.slp.product.dao.mapper.bo.ProdAttrDef;
import com.ai.slp.product.dao.mapper.bo.ProdAttrDefCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProdAttrDefMapper {
    int countByExample(ProdAttrDefCriteria example);

    int deleteByExample(ProdAttrDefCriteria example);

    int deleteByPrimaryKey(Long attrId);

    int insert(ProdAttrDef record);

    int insertSelective(ProdAttrDef record);

    List<ProdAttrDef> selectByExample(ProdAttrDefCriteria example);

    ProdAttrDef selectByPrimaryKey(Long attrId);

    int updateByExampleSelective(@Param("record") ProdAttrDef record, @Param("example") ProdAttrDefCriteria example);

    int updateByExample(@Param("record") ProdAttrDef record, @Param("example") ProdAttrDefCriteria example);

    int updateByPrimaryKeySelective(ProdAttrDef record);

    int updateByPrimaryKey(ProdAttrDef record);
}