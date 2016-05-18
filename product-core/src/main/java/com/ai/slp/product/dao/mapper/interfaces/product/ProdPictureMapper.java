package com.ai.slp.product.dao.mapper.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdPicture;
import com.ai.slp.product.dao.mapper.bo.product.ProdPictureCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProdPictureMapper {
    int countByExample(ProdPictureCriteria example);

    int deleteByExample(ProdPictureCriteria example);

    int deleteByPrimaryKey(Long proPictureId);

    int insert(ProdPicture record);

    int insertSelective(ProdPicture record);

    List<ProdPicture> selectByExample(ProdPictureCriteria example);

    ProdPicture selectByPrimaryKey(Long proPictureId);

    int updateByExampleSelective(@Param("record") ProdPicture record, @Param("example") ProdPictureCriteria example);

    int updateByExample(@Param("record") ProdPicture record, @Param("example") ProdPictureCriteria example);

    int updateByPrimaryKeySelective(ProdPicture record);

    int updateByPrimaryKey(ProdPicture record);
}