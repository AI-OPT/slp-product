package com.ai.slp.product.dao.mapper.interfaces.storage;

import com.ai.slp.product.dao.mapper.bo.storage.WarnReceiveStaff;
import com.ai.slp.product.dao.mapper.bo.storage.WarnReceiveStaffCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WarnReceiveStaffMapper {
    int countByExample(WarnReceiveStaffCriteria example);

    int deleteByExample(WarnReceiveStaffCriteria example);

    int deleteByPrimaryKey(String warnReceiveStaffId);

    int insert(WarnReceiveStaff record);

    int insertSelective(WarnReceiveStaff record);

    List<WarnReceiveStaff> selectByExample(WarnReceiveStaffCriteria example);

    WarnReceiveStaff selectByPrimaryKey(String warnReceiveStaffId);

    int updateByExampleSelective(@Param("record") WarnReceiveStaff record, @Param("example") WarnReceiveStaffCriteria example);

    int updateByExample(@Param("record") WarnReceiveStaff record, @Param("example") WarnReceiveStaffCriteria example);

    int updateByPrimaryKeySelective(WarnReceiveStaff record);

    int updateByPrimaryKey(WarnReceiveStaff record);
}