package com.ai.slp.product.dao.mapper.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdPictureLog;
import com.ai.slp.product.dao.mapper.bo.product.ProdPictureLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProdPictureLogMapper {
    int countByExample(ProdPictureLogCriteria example);

    int deleteByExample(ProdPictureLogCriteria example);

    int deleteByPrimaryKey(String logId);

    int insert(ProdPictureLog record);

    int insertSelective(ProdPictureLog record);

    List<ProdPictureLog> selectByExample(ProdPictureLogCriteria example);

    ProdPictureLog selectByPrimaryKey(String logId);

    int updateByExampleSelective(@Param("record") ProdPictureLog record, @Param("example") ProdPictureLogCriteria example);

    int updateByExample(@Param("record") ProdPictureLog record, @Param("example") ProdPictureLogCriteria example);

    int updateByPrimaryKeySelective(ProdPictureLog record);

    int updateByPrimaryKey(ProdPictureLog record);
}