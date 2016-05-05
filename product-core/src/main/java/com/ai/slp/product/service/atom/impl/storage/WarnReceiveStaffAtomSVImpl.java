package com.ai.slp.product.service.atom.impl.storage;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.slp.product.constants.CommonSatesConstants;
import com.ai.slp.product.dao.mapper.bo.storage.WarnReceiveStaff;
import com.ai.slp.product.dao.mapper.bo.storage.WarnReceiveStaffCriteria;
import com.ai.slp.product.dao.mapper.interfaces.storage.WarnReceiveStaffMapper;
import com.ai.slp.product.service.atom.interfaces.ISysSequenceCreditAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IWarnReceiveStaffAtomSV;
import com.ai.slp.product.util.DateUtils;

public class WarnReceiveStaffAtomSVImpl implements IWarnReceiveStaffAtomSV{

    @Autowired
    WarnReceiveStaffMapper warnReceiveStaffMapper;
    @Autowired
    ISysSequenceCreditAtomSV sysSequenceCreditAtomSV;
    
    @Override
    public WarnReceiveStaff selectWarnReceiveStaff(String tenantId, String warnReceiveStaffId) {
        WarnReceiveStaffCriteria example = new WarnReceiveStaffCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).
        andWarnReceiveStaffIdEqualTo(warnReceiveStaffId).andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
        List<WarnReceiveStaff> warnReceiveStaffList = warnReceiveStaffMapper.selectByExample(example);
        if(warnReceiveStaffList == null || warnReceiveStaffList.isEmpty())
            return null;
        return warnReceiveStaffList.get(0);
    }

    @Override
    public List<WarnReceiveStaff> selectWarnRecList(String tenantId, String objectId) {
        WarnReceiveStaffCriteria example = new WarnReceiveStaffCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId)
        .andObjectIdEqualTo(objectId).andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
        return warnReceiveStaffMapper.selectByExample(example);
    }

    @Override
    public int insertWarnReceiveStaff(WarnReceiveStaff warnReceiveStaff) {
        if(warnReceiveStaff.getOperTime()==null)
            warnReceiveStaff.setOperTime(DateUtils.currTimeStamp());
        return warnReceiveStaffMapper.insert(warnReceiveStaff);
    }

    @Override
    public int insertWarnReceiveStaff(List<WarnReceiveStaff> warnReceiveStaffList) {
        if(warnReceiveStaffList == null || warnReceiveStaffList.isEmpty())
            return 0;
        int count = 0;
        for(WarnReceiveStaff warnReceiveStaff : warnReceiveStaffList){
            if(warnReceiveStaff.getOperTime()==null)
                warnReceiveStaff.setOperTime(DateUtils.currTimeStamp());
            //设置预警类型为库存预警11
            warnReceiveStaff.setObiectType("11");
            warnReceiveStaff.setWarnReceiveStaffId(sysSequenceCreditAtomSV.getSeqByName()+"");
            warnReceiveStaffMapper.insert(warnReceiveStaff);
            count++;
        }
        return count;
    }

    @Override
    public int deleteWarnReceiveStaff(String tenantId, String warnReceiveStaffId, long operId,Timestamp operTime) {
        WarnReceiveStaff warnReceiveStaff = new WarnReceiveStaff();
        warnReceiveStaff.setState(CommonSatesConstants.STATE_INACTIVE);
        warnReceiveStaff.setOperId(operId);
        warnReceiveStaff.setOperTime(operTime == null ? DateUtils.currTimeStamp() : operTime);
        WarnReceiveStaffCriteria example = new WarnReceiveStaffCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andWarnReceiveStaffIdEqualTo(warnReceiveStaffId);
        return warnReceiveStaffMapper.updateByExampleSelective(warnReceiveStaff, example);
    }

}
