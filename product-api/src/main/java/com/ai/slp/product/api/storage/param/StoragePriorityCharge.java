package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 变更库存组下库存的优先级<br>
 *
 *
 * Date: 2016年4月21日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StoragePriorityCharge extends BaseInfo {
    /**
     * 库存组标识,必填
     */
    @Min(value = 0,message = "库存组标识不能小于0",
            groups = {IStorageSV.ChargeStoragePriority.class})
    private String groupId;
    /**
     * 原优先级,必填<br>
     * 不能小于0,不能与目标优先级一致
     */
    @Min(value = 0,message = "将修改优先级不能小于0",
            groups = {IStorageSV.ChargeStoragePriority.class})
    private int oldLevel;
    /**
     * 目标优先级,必填<br>
     * 不能小于0,不能与原优先级一致
     */
    @Min(value = 0,message = "变更的优先级不能小于0",
            groups = {IStorageSV.ChargeStoragePriority.class})
    private int newLevel;
    /**
     * 操作人,必填
     */
    @NotNull(message = "操作人ID不能为空",
            groups = {IStorageSV.ChargeStoragePriority.class})
    private Long operId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getOldLevel() {
        return oldLevel;
    }

    public void setOldLevel(int oldLevel) {
        this.oldLevel = oldLevel;
    }

    public int getNewLevel() {
        return newLevel;
    }

    public void setNewLevel(int newLevel) {
        this.newLevel = newLevel;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }
}
