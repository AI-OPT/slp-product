package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 库存组的路由组/配货组关联设置<br>
 *
 * Date: 2016年9月2日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StoGroupRouteSet extends BaseInfo {
    private static final long serialVersionUID = 1L;

    /**
     * 库存组标识,必填
     */
    @NotBlank(message = "库存组标识不能为空")
    private String groupId;

    /**
     * 路由组/配货组标识,必填
     */
    @NotBlank(message = "路由组/配货组标识不能为空")
    private String routeGroupId;

    /**
     * 操作人标识
     */
    private Long operId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getRouteGroupId() {
        return routeGroupId;
    }

    public void setRouteGroupId(String routeGroupId) {
        this.routeGroupId = routeGroupId;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }
}
