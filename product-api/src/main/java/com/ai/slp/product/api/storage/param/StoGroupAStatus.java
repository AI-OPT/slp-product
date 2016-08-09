package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 虚拟库存组状态变更<br>
 *
 * Date: 2016年8月9日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StoGroupAStatus extends BaseInfo{

    /**
     * 库存组标识
     */
    @NotBlank(message = "库存组标识不能为空")
    private String groupId;
    /**
     * 状态 11:自动启用;21:自动停用<br>
     *     状态变更时不能为空
     */
    @NotBlank(message = "库存组状态不能为空")
    private String state;

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
}
