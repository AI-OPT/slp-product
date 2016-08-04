package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import org.hibernate.validator.constraints.NotBlank;

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
    private static final long serialVersionUID = 1L;
    /**
     * 销售商（商户）标识，必填<br>
     */
    @NotBlank(message = "销售商（商户）标识不能为空",
            groups = { IStorageSV.ChargeStoragePriority.class})
    private String supplierId;
	/**
     * 库存组标识,必填
     */
    @NotBlank(message = "库存组标识不能小于0",
            groups = {IStorageSV.ChargeStoragePriority.class})
    private String storageGroupId;
    /**
     * 原优先级,必填<br>
     * 不能小于0,不能与目标优先级一致
     */
    @Min(value = 0,message = "将修改优先级不能小于0",
            groups = {IStorageSV.ChargeStoragePriority.class})
    private short oldLevel;
    /**
     * 目标优先级,必填<br>
     * 不能小于0,不能与原优先级一致
     */
    @Min(value = 0,message = "变更的优先级不能小于0",
            groups = {IStorageSV.ChargeStoragePriority.class})
    private short newLevel;

    /**
     * 操作人,必填
     */
    @NotNull(message = "操作人ID不能为空",
            groups = {IStorageSV.ChargeStoragePriority.class})
    private Long operId;

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getStorageGroupId() {
        return storageGroupId;
    }

    public void setStorageGroupId(String groupId) {
        this.storageGroupId = groupId;
    }

    public short getOldLevel() {
        return oldLevel;
    }

    public void setOldLevel(short oldLevel) {
        this.oldLevel = oldLevel;
    }

    public short getNewLevel() {
        return newLevel;
    }

    public void setNewLevel(short newLevel) {
        this.newLevel = newLevel;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

}
