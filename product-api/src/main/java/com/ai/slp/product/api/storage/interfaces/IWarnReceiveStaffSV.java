package com.ai.slp.product.api.storage.interfaces;

import java.util.List;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.slp.product.api.storage.param.WarnReceStafForQuery;
import com.ai.slp.product.api.storage.param.WarnReceiveStaff;
import com.ai.slp.product.api.storage.param.WarnReceiveStaffOper;

/**
 * 预警接收人操作<br>
 *
 * Date: 2016年4月21日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public interface IWarnReceiveStaffSV {

    /**
     * 查询指定库存的预警人集合
     *
     * @param objectId 库存标识
     * @return 预警人集合
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode WARN_RECE_0100
     */
    public List<WarnReceiveStaff> queryByObjectIdOfStorage(WarnReceStafForQuery warnReceStafForQuery)
            throws BusinessException,SystemException;
    @interface QueryByObjectIdOfStorage {}

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
    public BaseResponse installWarnReceiveStaff(List<WarnReceiveStaffOper> operList)
            throws BusinessException,SystemException;
    @interface InstallWarnReceiveStaff{}


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
    public BaseResponse deleteWarnReceiveStaff(List<WarnReceiveStaffOper> operList)
            throws BusinessException,SystemException;
    @interface DeleteWarnReceiveStaff{}
}
