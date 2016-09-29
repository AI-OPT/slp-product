package com.ai.slp.product.service.atom.impl.storage;

import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.dao.mapper.bo.storage.WarnReceiveStaff;
import com.ai.slp.product.dao.mapper.bo.storage.WarnReceiveStaffCriteria;
import com.ai.slp.product.dao.mapper.interfaces.storage.WarnReceiveStaffMapper;
import com.ai.slp.product.service.atom.interfaces.storage.IWarnReceiveStaffAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WarnReceiveStaffAtomSVImpl implements IWarnReceiveStaffAtomSV{

    @Autowired
    WarnReceiveStaffMapper warnReceiveStaffMapper;
    
    @Override
    public WarnReceiveStaff selectWarnReceiveStaff(String tenantId, String warnReceiveStaffId) {
        WarnReceiveStaffCriteria example = new WarnReceiveStaffCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).
        andWarnReceiveStaffIdEqualTo(warnReceiveStaffId).andStateEqualTo(CommonConstants.STATE_ACTIVE);
        List<WarnReceiveStaff> warnReceiveStaffList = warnReceiveStaffMapper.selectByExample(example);
        if(warnReceiveStaffList == null || warnReceiveStaffList.isEmpty()){
        	return null;
        }
        return warnReceiveStaffList.get(0);
    }

    @Override
    public List<WarnReceiveStaff> selectWarnRecList(String tenantId, String objectId) {
        WarnReceiveStaffCriteria example = new WarnReceiveStaffCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId)
        .andObjectIdEqualTo(objectId).andStateEqualTo(CommonConstants.STATE_ACTIVE);
        return warnReceiveStaffMapper.selectByExample(example);
    }

    @Override
    public int insertWarnReceiveStaff(WarnReceiveStaff warnReceiveStaff) {
        if(warnReceiveStaff.getOperTime()==null){
        	warnReceiveStaff.setOperTime(DateUtils.currTimeStamp());
        }
        warnReceiveStaff.setWarnReceiveStaffId(SequenceUtil.genWarnReceiveStaffId());
        return warnReceiveStaffMapper.insert(warnReceiveStaff);
    }

    @Override
    public int insertWarnReceiveStaff(List<WarnReceiveStaff> warnReceiveStaffList) {
        if(warnReceiveStaffList == null || warnReceiveStaffList.isEmpty()){
        	return 0;
        }
        int count = 0;
        for(WarnReceiveStaff warnReceiveStaff : warnReceiveStaffList){
            if(warnReceiveStaff.getOperTime()==null){
            	warnReceiveStaff.setOperTime(DateUtils.currTimeStamp());
            }
            //设置预警类型为库存预警11
            warnReceiveStaff.setObiectType("11");
            warnReceiveStaff.setWarnReceiveStaffId(SequenceUtil.genWarnReceiveStaffId());
            warnReceiveStaffMapper.insert(warnReceiveStaff);
            count++;
        }
        return count;
    }

    @Override
    public int deleteWarnReceiveStaff(String tenantId, String warnReceiveStaffId, Long operId) {
        WarnReceiveStaff warnReceiveStaff = new WarnReceiveStaff();
        warnReceiveStaff.setState(CommonConstants.STATE_INACTIVE);
        warnReceiveStaff.setOperId(operId);
        warnReceiveStaff.setOperTime(DateUtils.currTimeStamp());
        WarnReceiveStaffCriteria example = new WarnReceiveStaffCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andWarnReceiveStaffIdEqualTo(warnReceiveStaffId);
        return warnReceiveStaffMapper.updateByExampleSelective(warnReceiveStaff, example);
    }

}
