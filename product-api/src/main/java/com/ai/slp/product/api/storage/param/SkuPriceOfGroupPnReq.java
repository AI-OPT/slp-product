package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 *	库存组下某个优先级中SKU价格查询请求
 *
 * Date: 2016年5月21日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author lipeng16
 */
public class SkuPriceOfGroupPnReq extends BaseInfo {

    private static final long serialVersionUID = 1L;
    /**
     * 销售商（商户）标识，必填<br>
     */
    @NotBlank(message = "销售商（商户）标识不能为空")
    private String supplierId;
    /**
     * 库存组标识
     */
    @NotBlank(message = "库存组标识不能为空")
    private String groupId;
    /**
     * 库存组优先级
     */
    @NotNull(message = "优先级不能为空")
    private Short priorityNum;

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

    public Short getPriorityNum() {
        return priorityNum;
    }

    public void setPriorityNum(Short priorityNum) {
        this.priorityNum = priorityNum;
    }
}
