package com.ai.slp.product.dao.mapper.interfaces;

import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.bo.StandedProductCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StandedProductMapper {
    int countByExample(StandedProductCriteria example);

    int deleteByExample(StandedProductCriteria example);

    int deleteByPrimaryKey(String standedProdId);

    int insert(StandedProduct record);

    int insertSelective(StandedProduct record);

    List<StandedProduct> selectByExample(StandedProductCriteria example);

    StandedProduct selectByPrimaryKey(String standedProdId);

    int updateByExampleSelective(@Param("record") StandedProduct record, @Param("example") StandedProductCriteria example);

    int updateByExample(@Param("record") StandedProduct record, @Param("example") StandedProductCriteria example);

    int updateByPrimaryKeySelective(StandedProduct record);

    int updateByPrimaryKey(StandedProduct record);
}