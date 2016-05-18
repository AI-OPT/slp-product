package com.ai.slp.product.dao.mapper.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdTargetArea;
import com.ai.slp.product.dao.mapper.bo.product.ProdTargetAreaCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProdTargetAreaMapper {
    int countByExample(ProdTargetAreaCriteria example);

    int deleteByExample(ProdTargetAreaCriteria example);

    int deleteByPrimaryKey(Long targetAreaId);

    int insert(ProdTargetArea record);

    int insertSelective(ProdTargetArea record);

    List<ProdTargetArea> selectByExample(ProdTargetAreaCriteria example);

    ProdTargetArea selectByPrimaryKey(Long targetAreaId);

    int updateByExampleSelective(@Param("record") ProdTargetArea record, @Param("example") ProdTargetAreaCriteria example);

    int updateByExample(@Param("record") ProdTargetArea record, @Param("example") ProdTargetAreaCriteria example);

    int updateByPrimaryKeySelective(ProdTargetArea record);

    int updateByPrimaryKey(ProdTargetArea record);
}