package com.ai.slp.product.dao.mapper.interfaces;

import com.ai.slp.product.dao.mapper.bo.ProdCatAttr;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttrCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProdCatAttrMapper {
    int countByExample(ProdCatAttrCriteria example);

    int deleteByExample(ProdCatAttrCriteria example);

    int deleteByPrimaryKey(String catAttrId);

    int insert(ProdCatAttr record);

    int insertSelective(ProdCatAttr record);

    List<ProdCatAttr> selectByExample(ProdCatAttrCriteria example);

    ProdCatAttr selectByPrimaryKey(String catAttrId);

    int updateByExampleSelective(@Param("record") ProdCatAttr record, @Param("example") ProdCatAttrCriteria example);

    int updateByExample(@Param("record") ProdCatAttr record, @Param("example") ProdCatAttrCriteria example);

    int updateByPrimaryKeySelective(ProdCatAttr record);

    int updateByPrimaryKey(ProdCatAttr record);
}