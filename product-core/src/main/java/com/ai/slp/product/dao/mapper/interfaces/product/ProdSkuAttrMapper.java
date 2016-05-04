package com.ai.slp.product.dao.mapper.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdSkuAttr;
import com.ai.slp.product.dao.mapper.bo.product.ProdSkuAttrCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProdSkuAttrMapper {
    int countByExample(ProdSkuAttrCriteria example);

    int deleteByExample(ProdSkuAttrCriteria example);

    int deleteByPrimaryKey(Long skuAttrId);

    int insert(ProdSkuAttr record);

    int insertSelective(ProdSkuAttr record);

    List<ProdSkuAttr> selectByExample(ProdSkuAttrCriteria example);

    ProdSkuAttr selectByPrimaryKey(Long skuAttrId);

    int updateByExampleSelective(@Param("record") ProdSkuAttr record, @Param("example") ProdSkuAttrCriteria example);

    int updateByExample(@Param("record") ProdSkuAttr record, @Param("example") ProdSkuAttrCriteria example);

    int updateByPrimaryKeySelective(ProdSkuAttr record);

    int updateByPrimaryKey(ProdSkuAttr record);
}