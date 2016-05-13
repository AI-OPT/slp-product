package com.ai.slp.product.api.storage.param;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.storage.interfaces.IWarnReceiveStaffSV;

/**
 * 预警接收人操作时涉及的信息<br>
 *
 * Date: 2016年4月21日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class WarnReceiveStaffOper extends BaseInfo {
    private static final long serialVersionUID = 1L;

	/**
     * 进行删除时,等同与预警标识
     * 进行添加时,等同于预警接收人标识
     * 作为查询结果时,等同于预警标识
     */
    private String id;

    /**
     * 预警对象标识,不能为空
     */
    @NotBlank(message = "预警对象标识不能为空",
            groups = {IWarnReceiveStaffSV.InstallWarnReceiveStaff.class})
    private String objectId;
    /**
     * 预警对象类型
     * 11:库存预警
     */
    @NotBlank(message = "预警对象类型不能为空",
            groups = {IWarnReceiveStaffSV.DeleteWarnReceiveStaff.class,
                    IWarnReceiveStaffSV.InstallWarnReceiveStaff.class
            })
    private String objectType;
    /**
     * 操作人id
     */
    private Long operId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public long getOperId() {
        return operId;
    }

    public void setOperId(long operId) {
        this.operId = operId;
    }
}
