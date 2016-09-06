package com.ai.slp.product.dao.mapper.interfaces;

import com.ai.slp.product.dao.mapper.bo.ProdComment;
import com.ai.slp.product.dao.mapper.bo.ProdCommentCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProdCommentMapper {
    int countByExample(ProdCommentCriteria example);

    int deleteByExample(ProdCommentCriteria example);

    int deleteByPrimaryKey(String commentId);

    int insert(ProdComment record);

    int insertSelective(ProdComment record);

    List<ProdComment> selectByExample(ProdCommentCriteria example);

    ProdComment selectByPrimaryKey(String commentId);

    int updateByExampleSelective(@Param("record") ProdComment record, @Param("example") ProdCommentCriteria example);

    int updateByExample(@Param("record") ProdComment record, @Param("example") ProdCommentCriteria example);

    int updateByPrimaryKeySelective(ProdComment record);

    int updateByPrimaryKey(ProdComment record);
}