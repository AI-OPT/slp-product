package com.ai.slp.product.dao.mapper.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProductStateLog;
import com.ai.slp.product.dao.mapper.bo.product.ProductStateLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductStateLogMapper {
    int countByExample(ProductStateLogCriteria example);

    int deleteByExample(ProductStateLogCriteria example);

    int deleteByPrimaryKey(String logId);

    int insert(ProductStateLog record);

    int insertSelective(ProductStateLog record);

    List<ProductStateLog> selectByExample(ProductStateLogCriteria example);

    ProductStateLog selectByPrimaryKey(String logId);

    int updateByExampleSelective(@Param("record") ProductStateLog record, @Param("example") ProductStateLogCriteria example);

    int updateByExample(@Param("record") ProductStateLog record, @Param("example") ProductStateLogCriteria example);

    int updateByPrimaryKeySelective(ProductStateLog record);

    int updateByPrimaryKey(ProductStateLog record);
}