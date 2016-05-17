package com.ai.slp.product.api.storage.impl;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.slp.product.api.storage.interfaces.IWarnReceiveStaffSV;
import com.ai.slp.product.api.storage.param.WarnReceStafForQuery;
import com.ai.slp.product.api.storage.param.WarnReceStaff;
import com.ai.slp.product.api.storage.param.WarnReceiveStaffOper;
import com.ai.slp.product.service.business.interfaces.IWarnReceiveStaffBusiSV;
import com.ai.slp.product.util.CommonCheckUtils;
import org.springframework.stereotype.Component;

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
     * @ApiCode WARN_RECE_0100
     */
    @Override
    public List<WarnReceStaff> queryByObjectIdOfStorage(
            WarnReceStafForQuery warnReceStafForQuery) throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(warnReceStafForQuery.getTenantId(), "");
        return warnReceiveStaffBusiSV.queryByObjectId(warnReceStafForQuery);
    }

    /**
     * 添加库存的预警人
     *
     * @param operList 预警人集合
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode WARN_RECE_0101
     */
    @Override
    public BaseResponse installWarnReceiveStaff(List<WarnReceiveStaffOper> operList)
            throws BusinessException, SystemException {
        for(WarnReceiveStaffOper warnReceiveStaffOper : operList){
            CommonCheckUtils.checkTenantId(warnReceiveStaffOper.getTenantId(), "");
        }
        warnReceiveStaffBusiSV.addWarnReceStafList(operList);
        
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
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
     * @ApiCode WARN_RECE_0102
     */
    @Override
    public BaseResponse deleteWarnReceiveStaff(List<WarnReceiveStaffOper> operList)
            throws BusinessException, SystemException {
        for(WarnReceiveStaffOper warnReceiveStaffOper : operList){
            CommonCheckUtils.checkTenantId(warnReceiveStaffOper.getTenantId(), "");
        }
        warnReceiveStaffBusiSV.deleteWarnReceStaff(operList);
        
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }


}
