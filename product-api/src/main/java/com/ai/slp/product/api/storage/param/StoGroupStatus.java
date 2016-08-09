package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 虚拟库存组状态变更<br>
 *
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StoGroupStatus extends BaseInfo {
    private static final long serialVersionUID = 1L;
    /**
     * 销售商（商户）标识，必填<br>
     */
    @NotBlank(message = "销售商（商户）标识不能为空",
            groups = { IStorageSV.ChargeStorageGroupStatus.class})
    private String supplierId;
	/**
     * 库存组标识
     */
    @NotBlank(message = "库存组标识不能为空")
    private String groupId;
    /**
     * 状态 1:启用;2:停用;3:废弃<br>
     *     状态变更时不能为空
     */
    @NotBlank(message = "库存组状态不能为空",
            groups = { IStorageSV.ChargeStorageGroupStatus.class})
    private String state;
    /**
     * 操作者ID<br>
     * 进行更新时,不能为空
     */
    @NotNull(message = "操作者不能为空",
            groups = { IStorageSV.ChargeStorageGroupStatus.class})
    private Long operId;

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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
