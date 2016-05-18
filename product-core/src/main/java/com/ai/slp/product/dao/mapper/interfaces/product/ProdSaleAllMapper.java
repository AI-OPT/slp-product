package com.ai.slp.product.dao.mapper.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdSaleAll;
import com.ai.slp.product.dao.mapper.bo.product.ProdSaleAllCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProdSaleAllMapper {
    int countByExample(ProdSaleAllCriteria example);

    int deleteByExample(ProdSaleAllCriteria example);

    int deleteByPrimaryKey(Long proSaleId);

    int insert(ProdSaleAll record);

    int insertSelective(ProdSaleAll record);

    List<ProdSaleAll> selectByExample(ProdSaleAllCriteria example);

    ProdSaleAll selectByPrimaryKey(Long proSaleId);

    int updateByExampleSelective(@Param("record") ProdSaleAll record, @Param("example") ProdSaleAllCriteria example);

    int updateByExample(@Param("record") ProdSaleAll record, @Param("example") ProdSaleAllCriteria example);

    int updateByPrimaryKeySelective(ProdSaleAll record);

    int updateByPrimaryKey(ProdSaleAll record);
}