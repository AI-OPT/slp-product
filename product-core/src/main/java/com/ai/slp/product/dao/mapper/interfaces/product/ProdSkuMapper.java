package com.ai.slp.product.dao.mapper.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdSku;
import com.ai.slp.product.dao.mapper.bo.product.ProdSkuCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProdSkuMapper {
    int countByExample(ProdSkuCriteria example);

    int deleteByExample(ProdSkuCriteria example);

    int deleteByPrimaryKey(String skuId);

    int insert(ProdSku record);

    int insertSelective(ProdSku record);

    List<ProdSku> selectByExample(ProdSkuCriteria example);

    ProdSku selectByPrimaryKey(String skuId);

    int updateByExampleSelective(@Param("record") ProdSku record, @Param("example") ProdSkuCriteria example);

    int updateByExample(@Param("record") ProdSku record, @Param("example") ProdSkuCriteria example);

    int updateByPrimaryKeySelective(ProdSku record);

    int updateByPrimaryKey(ProdSku record);
}