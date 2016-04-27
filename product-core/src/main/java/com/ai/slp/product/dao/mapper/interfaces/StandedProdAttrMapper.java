package com.ai.slp.product.dao.mapper.interfaces;

import com.ai.slp.product.dao.mapper.bo.StandedProdAttr;
import com.ai.slp.product.dao.mapper.bo.StandedProdAttrCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StandedProdAttrMapper {
    int countByExample(StandedProdAttrCriteria example);

    int deleteByExample(StandedProdAttrCriteria example);

    int deleteByPrimaryKey(Long standedProdAttrId);

    int insert(StandedProdAttr record);

    int insertSelective(StandedProdAttr record);

    List<StandedProdAttr> selectByExample(StandedProdAttrCriteria example);

    StandedProdAttr selectByPrimaryKey(Long standedProdAttrId);

    int updateByExampleSelective(@Param("record") StandedProdAttr record, @Param("example") StandedProdAttrCriteria example);

    int updateByExample(@Param("record") StandedProdAttr record, @Param("example") StandedProdAttrCriteria example);

    int updateByPrimaryKeySelective(StandedProdAttr record);

    int updateByPrimaryKey(StandedProdAttr record);
}