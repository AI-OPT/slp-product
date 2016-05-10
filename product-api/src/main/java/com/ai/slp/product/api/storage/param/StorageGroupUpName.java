package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 虚拟库存组更新信息<br>
 *
 * Date: 2016年5月10日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StorageGroupUpName extends BaseInfo {
    /**
     * 库存组标识
     */
    @NotBlank(message = "库存组标识不能为空",
            groups = { IStorageSV.UpdateStorageGroupName.class})
    private String storageGroupId;
    /**
     * 库存组名称,必填
     * 设置为null,则不进行更新操作
     */
    @NotBlank(message = "库存组名称不能为空",
            groups = { IStorageSV.UpdateStorageGroupName.class})
    private String storageGroupName;

    /**
     * 操作者ID<br>
     * 不能为空
     */
    @NotNull(message = "操作者不能为空",
            groups = {IStorageSV.UpdateStorageGroupName.class})
    private Long operId;

    public String getStorageGroupId() {
        return storageGroupId;
    }

    public void setStorageGroupId(String storageGroupId) {
        this.storageGroupId = storageGroupId;
    }

    public String getStorageGroupName() {
        return storageGroupName;
    }

    public void setStorageGroupName(String storageGroupName) {
        this.storageGroupName = storageGroupName;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }
}
