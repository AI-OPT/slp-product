package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.storage.interfaces.INormProductStorageSV;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 虚拟库存组状态变更<br>
 *
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StorageGroupStatus extends BaseInfo {
    /**
     * 库存组标识
     */
    @NotNull(message = "库存组标识不能为空",
            groups = { INormProductStorageSV.UpdateStorageGroup.class})
    private Long groupId;
    /**
     * 状态<br>
     * 添加时使用停用状态,更新时直接忽略
     * 0:全部,
     */
    @NotNull(message = "库存组状态不能为空",
            groups = { INormProductStorageSV.ChargeStorageGroupStatus.class
                    ,INormProductStorageSV.QueryGroupList.class
            })
    private String state;
    /**
     * 操作者ID<br>
     * 进行变更时,不能为空
     */
    @NotNull(message = "操作者不能为空",
            groups = { INormProductStorageSV.ChargeStorageGroupStatus.class,
                    INormProductStorageSV.UpdateStorageGroup.class})
    private Long operId;
    /**
     * 操作时间<br>
     * 若不填写,则设置接收时间
     * 查询时,使用操作时间范围的属性
     */
    private Date operTime;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
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

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }
}
