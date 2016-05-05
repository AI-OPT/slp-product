package com.ai.slp.product.service.atom.impl.storage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.slp.product.dao.mapper.bo.storage.WarnReceiveStaff;
import com.ai.slp.product.dao.mapper.bo.storage.WarnReceiveStaffCriteria;
import com.ai.slp.product.dao.mapper.interfaces.storage.WarnReceiveStaffMapper;
import com.ai.slp.product.service.atom.interfaces.storage.IWarnReceiveStaffAtomSV;

public class WarnReceiveStaffAtomSVImpl implements IWarnReceiveStaffAtomSV{

    @Autowired
    WarnReceiveStaffMapper warnReceiveStaffMapper;
    
    @Override
    public WarnReceiveStaff selectWarnReceiveStaff(String tenantId, String warnReceiveStaffId) {
        WarnReceiveStaffCriteria example = new WarnReceiveStaffCriteria();
        example.createCriteria();
        return null;
    }

    @Override
    public List<WarnReceiveStaff> selectWarnRecList(String tenantId, String storageId) {
        return null;
    }

    @Override
    public int insertWarnReceiveStaff(WarnReceiveStaff warnReceiveStaff) {
        return 0;
    }

    @Override
    public int insertWarnReceiveStaff(List<WarnReceiveStaff> warnReceiveStaffList) {
        return 0;
    }

    @Override
    public int deleteWarnReceiveStaff(String tenantId, String warnReceiveStaffId) {
        return 0;
    }

}
