package com.ai.slp.product.dao.mapper.interfaces.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ai.slp.product.dao.mapper.bo.product.ProdAttr;
import com.ai.slp.product.dao.mapper.bo.product.ProdAttrCriteria;

public interface ProdAttrMapper {
    int countByExample(ProdAttrCriteria example);

    int deleteByExample(ProdAttrCriteria example);

    int deleteByPrimaryKey(Long prodAttrId);

    int insert(ProdAttr record);

    int insertSelective(ProdAttr record);

    List<ProdAttr> selectByExample(ProdAttrCriteria example);

    ProdAttr selectByPrimaryKey(Long prodAttrId);

    int updateByExampleSelective(@Param("record") ProdAttr record, @Param("example") ProdAttrCriteria example);

    int updateByExample(@Param("record") ProdAttr record, @Param("example") ProdAttrCriteria example);

    int updateByPrimaryKeySelective(ProdAttr record);

    int updateByPrimaryKey(ProdAttr record);
}