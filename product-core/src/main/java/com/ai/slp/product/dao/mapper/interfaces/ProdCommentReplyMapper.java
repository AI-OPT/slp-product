package com.ai.slp.product.dao.mapper.interfaces;

import com.ai.slp.product.dao.mapper.bo.ProdCommentReply;
import com.ai.slp.product.dao.mapper.bo.ProdCommentReplyCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProdCommentReplyMapper {
    int countByExample(ProdCommentReplyCriteria example);

    int deleteByExample(ProdCommentReplyCriteria example);

    int deleteByPrimaryKey(String replyId);

    int insert(ProdCommentReply record);

    int insertSelective(ProdCommentReply record);

    List<ProdCommentReply> selectByExample(ProdCommentReplyCriteria example);

    ProdCommentReply selectByPrimaryKey(String replyId);

    int updateByExampleSelective(@Param("record") ProdCommentReply record, @Param("example") ProdCommentReplyCriteria example);

    int updateByExample(@Param("record") ProdCommentReply record, @Param("example") ProdCommentReplyCriteria example);

    int updateByPrimaryKeySelective(ProdCommentReply record);

    int updateByPrimaryKey(ProdCommentReply record);
}