package com.ai.slp.product.api.storage.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseListResponse;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.slp.product.api.storage.interfaces.IWarnReceiveStaffSV;
import com.ai.slp.product.api.storage.param.WarnReceStafForQuery;
import com.ai.slp.product.api.storage.param.WarnReceStaff;
import com.ai.slp.product.api.storage.param.WarnReceiveStaffOper;
import com.ai.slp.product.constants.ErrorCodeConstants;
import com.ai.slp.product.service.business.interfaces.IWarnReceiveStaffBusiSV;
import com.ai.slp.product.util.CommonUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service(validation = "true")
@Component
public class IWarnReceiveStaffSVImpl implements IWarnReceiveStaffSV {
    
    @Autowired
    IWarnReceiveStaffBusiSV warnReceiveStaffBusiSV;

    /**
     * 查询指定库存的预警人集合
     *
     * @param warnReceStafForQuery 库存标识
     * @return 预警人集合
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     */
    @Override
    public BaseListResponse<WarnReceStaff> queryByObjectIdOfStorage(
            WarnReceStafForQuery warnReceStafForQuery) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(warnReceStafForQuery.getTenantId(), ErrorCodeConstants.TENANT_ID_NULL);
        List<WarnReceStaff> resList = warnReceiveStaffBusiSV.queryByObjectId(warnReceStafForQuery);
        BaseListResponse<WarnReceStaff> prodRes = new BaseListResponse<>();
        prodRes.setResult(resList);
        prodRes.setResponseHeader(new ResponseHeader(true, ExceptCodeConstants.Special.SUCCESS,"OK"));
        return prodRes;
    }

    /**
     * 添加库存的预警人
     *
     * @param operList 预警人集合
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     */
    @Override
    public BaseResponse installWarnReceiveStaff(List<WarnReceiveStaffOper> operList)
            throws BusinessException, SystemException {
        for(WarnReceiveStaffOper warnReceiveStaffOper : operList){
            CommonUtils.checkTenantId(warnReceiveStaffOper.getTenantId(), ErrorCodeConstants.TENANT_ID_NULL);
        }
        warnReceiveStaffBusiSV.addWarnReceStafList(operList);
        
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResponseHeader(new ResponseHeader(true, ExceptCodeConstants.Special.SUCCESS,"OK"));
        return baseResponse;
    }

    /**
     * 删除库存的预警人
     *
     * @param operList 预警人集合
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     */
    @Override
    public BaseResponse deleteWarnReceiveStaff(List<WarnReceiveStaffOper> operList)
            throws BusinessException, SystemException {
        for(WarnReceiveStaffOper warnReceiveStaffOper : operList){
            CommonUtils.checkTenantId(warnReceiveStaffOper.getTenantId(), "");
        }
        warnReceiveStaffBusiSV.deleteWarnReceStaff(operList);
        
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResponseHeader(new ResponseHeader(true, ExceptCodeConstants.Special.SUCCESS,"OK"));
        return baseResponse;
    }


}
