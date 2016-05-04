package com.ai.slp.product.dao.mapper.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.product.ProductCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    int countByExample(ProductCriteria example);

    int deleteByExample(ProductCriteria example);

    int deleteByPrimaryKey(String prodId);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductCriteria example);

    Product selectByPrimaryKey(String prodId);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductCriteria example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductCriteria example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}