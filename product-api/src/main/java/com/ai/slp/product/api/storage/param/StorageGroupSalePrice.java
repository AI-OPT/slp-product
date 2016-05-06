package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 库存组售价信息<br>
 * 用于更新库存组销售价
 *
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StorageGroupSalePrice extends BaseInfo {
    /**
     * 库存组标识
     */
    @NotNull(message = "库存组标识不能为空",
            groups = { IStorageSV.UpdateStorageGroupSalePrice.class})
    private String groupId;

    /**
     * 最低销售价
     */
    @NotNull(message = "最低销售价不能为空",
            groups = { IStorageSV.UpdateStorageGroupSalePrice.class})
    @Min(value = 0,message = "最低销售价不能小于0",
            groups = { IStorageSV.UpdateStorageGroupSalePrice.class})
    private Long lowSalePrice;
    /**
     * 最高销售价
     */
    private Long highSalePrice;

    /**
     * 操作者ID<br>
     * 进行变更时,不能为空
     */
    @NotNull(message = "操作者不能为空",
            groups = { IStorageSV.UpdateStorageGroupSalePrice.class})
    private Long operId;
    /**
     * 操作时间<br>
     * 若不填写,则设置接收时间
     * 查询时,使用操作时间范围的属性
     */
    private Date operTime;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Long getLowSalePrice() {
        return lowSalePrice;
    }

    public void setLowSalePrice(Long lowSalePrice) {
        this.lowSalePrice = lowSalePrice;
    }

    public Long getHighSalePrice() {
        return highSalePrice;
    }

    public void setHighSalePrice(Long highSalePrice) {
        this.highSalePrice = highSalePrice;
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
