package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 虚拟库存状态变更<br>
 *
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StorageStatus extends BaseInfo {
    /**
     * 库存标识<br>
     * 在保存时,若包含标识则进行更新操作,否则进行添加操作
     */
    @NotNull(message = "库存标识不能为空",
            groups = { IStorageSV.ChargeStorageStatus.class })
    private String storageId;

    /**
     * 库存状态 1:启用;2:停用;3:废弃<br>
     *     状态变更时不能为空
     */
    @NotNull(message = "库存状态不能为空",
            groups = { IStorageSV.ChargeStorageStatus.class })
    private String state;
    /**
     * 操作人ID
     */
    @NotNull(message = "操作人不能为空",
            groups = { IStorageSV.ChargeStorageStatus.class })
    private Long operId;

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }
}
