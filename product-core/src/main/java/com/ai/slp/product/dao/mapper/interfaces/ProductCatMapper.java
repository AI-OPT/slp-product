package com.ai.slp.product.dao.mapper.interfaces;

import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.dao.mapper.bo.ProductCatCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductCatMapper {
    int countByExample(ProductCatCriteria example);

    int deleteByExample(ProductCatCriteria example);

    int deleteByPrimaryKey(String productCatId);

    int insert(ProductCat record);

    int insertSelective(ProductCat record);

    List<ProductCat> selectByExample(ProductCatCriteria example);

    ProductCat selectByPrimaryKey(String productCatId);

    int updateByExampleSelective(@Param("record") ProductCat record, @Param("example") ProductCatCriteria example);

    int updateByExample(@Param("record") ProductCat record, @Param("example") ProductCatCriteria example);

    int updateByPrimaryKeySelective(ProductCat record);

    int updateByPrimaryKey(ProductCat record);
}