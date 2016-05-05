package com.ai.slp.product.api.storage.impl;

import java.util.List;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.slp.product.api.storage.interfaces.IWarnReceiveStaffSV;
import com.ai.slp.product.api.storage.param.WarnReceStafForQuery;
import com.ai.slp.product.api.storage.param.WarnReceiveStaff;
import com.ai.slp.product.api.storage.param.WarnReceiveStaffOper;

public class IWarnReceiveStaffSVImpl implements IWarnReceiveStaffSV {

    @Override
    public List<WarnReceiveStaff> queryByObjectIdOfStorage(
            WarnReceStafForQuery warnReceStafForQuery) throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse installWarnReceiveStaff(List<WarnReceiveStaffOper> operList)
            throws BusinessException, SystemException {
        return null;
    }

    @Override
    public BaseResponse deleteWarnReceiveStaff(List<WarnReceiveStaffOper> operList)
            throws BusinessException, SystemException {
        return null;
    }


}
