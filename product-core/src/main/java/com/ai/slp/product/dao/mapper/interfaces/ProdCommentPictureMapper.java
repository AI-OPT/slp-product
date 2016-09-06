package com.ai.slp.product.dao.mapper.interfaces;

import com.ai.slp.product.dao.mapper.bo.ProdCommentPicture;
import com.ai.slp.product.dao.mapper.bo.ProdCommentPictureCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProdCommentPictureMapper {
    int countByExample(ProdCommentPictureCriteria example);

    int deleteByExample(ProdCommentPictureCriteria example);

    int deleteByPrimaryKey(String prodCommentPicId);

    int insert(ProdCommentPicture record);

    int insertSelective(ProdCommentPicture record);

    List<ProdCommentPicture> selectByExample(ProdCommentPictureCriteria example);

    ProdCommentPicture selectByPrimaryKey(String prodCommentPicId);

    int updateByExampleSelective(@Param("record") ProdCommentPicture record, @Param("example") ProdCommentPictureCriteria example);

    int updateByExample(@Param("record") ProdCommentPicture record, @Param("example") ProdCommentPictureCriteria example);

    int updateByPrimaryKeySelective(ProdCommentPicture record);

    int updateByPrimaryKey(ProdCommentPicture record);
}