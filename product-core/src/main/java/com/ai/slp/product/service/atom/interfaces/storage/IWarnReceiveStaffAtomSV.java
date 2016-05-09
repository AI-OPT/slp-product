package com.ai.slp.product.service.atom.interfaces.storage;

import java.util.List;

import com.ai.slp.product.dao.mapper.bo.storage.WarnReceiveStaff;

/**
 * 预警原子操作
 * 
 * Date: 2016年5月5日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public interface IWarnReceiveStaffAtomSV {
    /**
     * 查询单个预警人信息
     * 
     * @param tenantId
     * @param warnReceiveStaffId
     * @return
     * @author lipeng
     */
    public WarnReceiveStaff selectWarnReceiveStaff(String tenantId,String warnReceiveStaffId);
    
    /**
     * 批量查询预警人信息
     * 
     * @param tenantId 租户ID
     * @param storageId 预警对象标识(现指库存标识)
     * @return
     * @author lipeng
     */
    public List<WarnReceiveStaff> selectWarnRecList(String tenantId,String objectId);
    
    /**
     * 新增预警接受人(单个)
     * 
     * @param warnReceiveStaffList
     * @return
     * @author lipeng
     */
    public int insertWarnReceiveStaff(WarnReceiveStaff warnReceiveStaff);
    
    /**
     * 新增预警接受人(批量)
     * 
     * @param warnReceiveStaffList
     * @return
     * @author lipeng
     */
    public int insertWarnReceiveStaff(List<WarnReceiveStaff> warnReceiveStaffList);
    
    /**
     * 删除预警接收人
     * 
     * @param tenantId
     * @param warnReceiveStaffId
     * @param operId
     * @return
     * @author lipeng
     */
    public int deleteWarnReceiveStaff(String tenantId,String warnReceiveStaffId,Long operId);
    
}
