package com.ai.slp.product.dao.mapper.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProductLog;
import com.ai.slp.product.dao.mapper.bo.product.ProductLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductLogMapper {
    int countByExample(ProductLogCriteria example);

    int deleteByExample(ProductLogCriteria example);

    int deleteByPrimaryKey(String logId);

    int insert(ProductLog record);

    int insertSelective(ProductLog record);

    List<ProductLog> selectByExample(ProductLogCriteria example);

    ProductLog selectByPrimaryKey(String logId);

    int updateByExampleSelective(@Param("record") ProductLog record, @Param("example") ProductLogCriteria example);

    int updateByExample(@Param("record") ProductLog record, @Param("example") ProductLogCriteria example);

    int updateByPrimaryKeySelective(ProductLog record);

    int updateByPrimaryKey(ProductLog record);
}