package com.ai.slp.product.dao.mapper.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdAudiences;
import com.ai.slp.product.dao.mapper.bo.product.ProdAudiencesCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProdAudiencesMapper {
    int countByExample(ProdAudiencesCriteria example);

    int deleteByExample(ProdAudiencesCriteria example);

    int deleteByPrimaryKey(Long prodAudiencesId);

    int insert(ProdAudiences record);

    int insertSelective(ProdAudiences record);

    List<ProdAudiences> selectByExample(ProdAudiencesCriteria example);

    ProdAudiences selectByPrimaryKey(Long prodAudiencesId);

    int updateByExampleSelective(@Param("record") ProdAudiences record, @Param("example") ProdAudiencesCriteria example);

    int updateByExample(@Param("record") ProdAudiences record, @Param("example") ProdAudiencesCriteria example);

    int updateByPrimaryKeySelective(ProdAudiences record);

    int updateByPrimaryKey(ProdAudiences record);
}