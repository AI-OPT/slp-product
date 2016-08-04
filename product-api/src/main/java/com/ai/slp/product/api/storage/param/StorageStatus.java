package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 虚拟库存状态变更<br>
 *
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StorageStatus extends BaseInfo {
    private static final long serialVersionUID = 1L;

    /**
     * 销售商（商户）标识，必填<br>
     */
    @NotBlank(message = "销售商（商户）标识不能为空",
            groups = { IStorageSV.ChargeStorageStatus.class})
    private String supplierId;
	/**
     * 库存标识,必填
     */
    @NotBlank(message = "库存标识不能为空",
            groups = { IStorageSV.ChargeStorageStatus.class })
    private String storageId;

    /**
     * 库存状态 必填 1:启用;2:停用;3:废弃
     */
    @NotBlank(message = "库存状态不能为空",
            groups = { IStorageSV.ChargeStorageStatus.class })
    private String state;
    /**
     * 操作人ID
     */
    @NotNull(message = "操作人不能为空",
            groups = { IStorageSV.ChargeStorageStatus.class })
    private Long operId;

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

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
