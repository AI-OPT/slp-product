package com.ai.slp.product.dao.mapper.interfaces;

import com.ai.slp.product.dao.mapper.bo.SysSequenceCredit;
import com.ai.slp.product.dao.mapper.bo.SysSequenceCreditCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysSequenceCreditMapper {
    int countByExample(SysSequenceCreditCriteria example);

    int deleteByExample(SysSequenceCreditCriteria example);

    int deleteByPrimaryKey(String sequenceName);

    int insert(SysSequenceCredit record);

    int insertSelective(SysSequenceCredit record);

    List<SysSequenceCredit> selectByExample(SysSequenceCreditCriteria example);

    SysSequenceCredit selectByPrimaryKey(String sequenceName);

    int updateByExampleSelective(@Param("record") SysSequenceCredit record, @Param("example") SysSequenceCreditCriteria example);

    int updateByExample(@Param("record") SysSequenceCredit record, @Param("example") SysSequenceCreditCriteria example);

    int updateByPrimaryKeySelective(SysSequenceCredit record);

    int updateByPrimaryKey(SysSequenceCredit record);
}