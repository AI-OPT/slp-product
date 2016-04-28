package com.ai.slp.product.dao.mapper.interfaces;

import com.ai.slp.product.dao.mapper.bo.SysSequences;
import com.ai.slp.product.dao.mapper.bo.SysSequencesCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysSequencesMapper {
    int countByExample(SysSequencesCriteria example);

    int deleteByExample(SysSequencesCriteria example);

    int deleteByPrimaryKey(String sequenceName);

    int insert(SysSequences record);

    int insertSelective(SysSequences record);

    List<SysSequences> selectByExample(SysSequencesCriteria example);

    SysSequences selectByPrimaryKey(String sequenceName);

    int updateByExampleSelective(@Param("record") SysSequences record, @Param("example") SysSequencesCriteria example);

    int updateByExample(@Param("record") SysSequences record, @Param("example") SysSequencesCriteria example);

    int updateByPrimaryKeySelective(SysSequences record);

    int updateByPrimaryKey(SysSequences record);
}