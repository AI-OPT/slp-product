package com.ai.slp.product.dao.mapper.interfaces;

import com.ai.slp.product.dao.mapper.bo.ProdCatAttrValue;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttrValueCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProdCatAttrValueMapper {
    int countByExample(ProdCatAttrValueCriteria example);

    int deleteByExample(ProdCatAttrValueCriteria example);

    int deleteByPrimaryKey(String catAttrValueId);

    int insert(ProdCatAttrValue record);

    int insertSelective(ProdCatAttrValue record);

    List<ProdCatAttrValue> selectByExample(ProdCatAttrValueCriteria example);

    ProdCatAttrValue selectByPrimaryKey(String catAttrValueId);

    int updateByExampleSelective(@Param("record") ProdCatAttrValue record, @Param("example") ProdCatAttrValueCriteria example);

    int updateByExample(@Param("record") ProdCatAttrValue record, @Param("example") ProdCatAttrValueCriteria example);

    int updateByPrimaryKeySelective(ProdCatAttrValue record);

    int updateByPrimaryKey(ProdCatAttrValue record);
}