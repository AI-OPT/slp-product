package com.ai.slp.product.service.business.interfaces;

import java.util.List;

import com.ai.slp.product.api.storage.param.WarnReceStafForQuery;
import com.ai.slp.product.api.storage.param.WarnReceStaff;
import com.ai.slp.product.api.storage.param.WarnReceiveStaffOper;

/**
 * 预警人相关操作
 * 
 * Date: 2016年5月5日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public interface IWarnReceiveStaffBusiSV {
    /**
     * 通过预警对象标识查询预警人
     * 
     * @param warnReceStafForQuery
     * @return
     * @author lipeng
     */
    public List<WarnReceStaff> queryByObjectId(WarnReceStafForQuery warnReceStafForQuery);
    
    /**
     * 添加预警人信息
     * 
     * @param operList
     * @return
     * @author lipeng
     */
    public int addWarnReceStafList(List<WarnReceiveStaffOper> operList);
    
    /**
     * 删除预警人信息
     * 
     * @param operList
     * @return
     * @author lipeng
     */
    public int deleteWarnReceStaff(List<WarnReceiveStaffOper> operList);

}
