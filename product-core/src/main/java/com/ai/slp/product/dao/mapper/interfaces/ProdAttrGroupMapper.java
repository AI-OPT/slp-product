package com.ai.slp.product.dao.mapper.interfaces;

import com.ai.slp.product.dao.mapper.bo.ProdAttrGroup;
import com.ai.slp.product.dao.mapper.bo.ProdAttrGroupCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProdAttrGroupMapper {
    int countByExample(ProdAttrGroupCriteria example);

    int deleteByExample(ProdAttrGroupCriteria example);

    int deleteByPrimaryKey(Integer attrGroupId);

    int insert(ProdAttrGroup record);

    int insertSelective(ProdAttrGroup record);

    List<ProdAttrGroup> selectByExample(ProdAttrGroupCriteria example);

    ProdAttrGroup selectByPrimaryKey(Integer attrGroupId);

    int updateByExampleSelective(@Param("record") ProdAttrGroup record, @Param("example") ProdAttrGroupCriteria example);

    int updateByExample(@Param("record") ProdAttrGroup record, @Param("example") ProdAttrGroupCriteria example);

    int updateByPrimaryKeySelective(ProdAttrGroup record);

    int updateByPrimaryKey(ProdAttrGroup record);
}