package com.ai.slp.product.service.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.api.storage.param.WarnReceStafForQuery;
import com.ai.slp.product.api.storage.param.WarnReceStaff;
import com.ai.slp.product.api.storage.param.WarnReceiveStaffOper;
import com.ai.slp.product.dao.mapper.bo.storage.WarnReceiveStaff;
import com.ai.slp.product.service.atom.interfaces.storage.IWarnReceiveStaffAtomSV;
import com.ai.slp.product.service.business.interfaces.IWarnReceiveStaffBusiSV;

public class WarnReceiveStaffBusiSVImpl implements IWarnReceiveStaffBusiSV{
    
    @Autowired
    IWarnReceiveStaffAtomSV warnReceiveStaffAtomSV;

    @Override
    public List<WarnReceStaff> queryByObjectId(WarnReceStafForQuery warnReceStafForQuery) {
        List<WarnReceiveStaff> warnReceiveStaffList = 
                warnReceiveStaffAtomSV.selectWarnRecList(warnReceStafForQuery.getTenantId(), 
                        warnReceStafForQuery.getStorageId());
        if(warnReceiveStaffList == null || warnReceiveStaffList.isEmpty())
            throw new BusinessException("", "找不到指定的租户ID="+warnReceStafForQuery.getTenantId()+
                    ",库存标识="+warnReceStafForQuery.getStorageId());
        
        List<WarnReceStaff> warnReceStaffList = new ArrayList<>();
        for(WarnReceiveStaff warnReceiveStaff : warnReceiveStaffList){
            WarnReceStaff warnReceStaff = new WarnReceStaff();
            warnReceStaff.setStaffId(warnReceiveStaff.getStaffId());
            //TODO只返回预警人ID，其余信息从用户端获取
            warnReceStaffList.add(warnReceStaff);
        }
        return warnReceStaffList;
    }

    @Override
    public int addWarnReceStafList(List<WarnReceiveStaffOper> operList) {
        if(operList == null || operList.isEmpty())
            return 0;
        List<WarnReceiveStaff> warnReceiveStaffList = new ArrayList<>();
        for(WarnReceiveStaffOper warnReceiveStaffOper : operList){
            if(warnReceiveStaffOper.getObjectId() == null || warnReceiveStaffOper.getOperId() == 0)
                throw new BusinessException("", "找不到指定的预警对象="+warnReceiveStaffOper.getObjectId()+
                        ",找不到操作人="+warnReceiveStaffOper.getOperId());
            WarnReceiveStaff warnReceiveStaff = new WarnReceiveStaff();
            BeanUtils.copyProperties(warnReceiveStaff, warnReceiveStaffOper);
            warnReceiveStaffList.add(warnReceiveStaff);
        }
        return warnReceiveStaffAtomSV.insertWarnReceiveStaff(warnReceiveStaffList);
    }

    @Override
    public int deleteWarnReceStaff(List<WarnReceiveStaffOper> operList) {
        if(operList == null || operList.isEmpty())
            return 0;
        int count = 0;
        for(WarnReceiveStaffOper warnReceiveStaffOper : operList){
            if(warnReceiveStaffOper.getOperId() == 0)
                throw new BusinessException("", "找不到操作人="+warnReceiveStaffOper.getOperId());
            warnReceiveStaffAtomSV.deleteWarnReceiveStaff(warnReceiveStaffOper.getTenantId(), 
                    warnReceiveStaffOper.getId(), warnReceiveStaffOper.getOperId(), warnReceiveStaffOper.getOperTime());
            count++;
        }
        return count;
    }

}
