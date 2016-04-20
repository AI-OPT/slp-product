package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.storage.interfaces.INormProductStorageSV;

import javax.validation.constraints.NotNull;
import java.util.Date;

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
            groups = { INormProductStorageSV.ChargeStorageStatus.class })
    private String storageId;

    /**
     * 库存状态<br>
     * 添加库存时,必须为停用状态
     */
    @NotNull(message = "库存状态不能为空",
            groups = { INormProductStorageSV.ChargeStorageStatus.class })
    private String status;
    /**
     * 操作人ID
     */
    @NotNull(message = "操作人不能为空",
            groups = { INormProductStorageSV.ChargeStorageStatus.class })
    private Long operId;
    /**
     * 操作时间<br>
     * 若不填写,则设置接收时间
     */
    private Date operTime;

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }
}
