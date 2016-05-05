package com.ai.slp.product.api.storage.param;

import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 虚拟库存信息<br>
 *
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class STOStorage extends StorageStatus{
    /**
     * 库存名称
     */
    @NotNull(message = "库存名称不能为空",
            groups = { IStorageSV.SaveStorage.class })
    @Length(max = 300,message = "库存名称长度不允许超过300",
            groups = {IStorageSV.SaveStorage.class})
    private String storageName;

    /**
     * 库存组id
     */
    @NotNull(message = "库存组标识不能为空",
            groups = { IStorageSV.SaveStorage.class })
    private Long groupId;

    /**
     * 虚拟库存量
     */
    @Min(value = 0,message = "虚拟库存量不能小于0",
            groups = {IStorageSV.SaveStorage.class})
    private Long totalNum;
    /**
     * 预警库存量
     */
    @Min(value = 0,message = "预警库存量不能小于0",
            groups = {IStorageSV.SaveStorage.class})
    private Long warnNum;
    /**
     * 优先级
     */
    @Min(value = 0,message = "优先级不能小于0",
            groups = {IStorageSV.SaveStorage.class})
    private Short priorityNumber;
    /**
     * 销售价
     * 在查询时,忽略此字段
     */
    private Long salePrice;

    /**
     * 生效时间
     */
    private Timestamp activeTime;
    /**
     * 失效时间
     */
    private Timestamp inactiveTime;

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public Short getPriorityNumber() {
        return priorityNumber;
    }

    public void setPriorityNumber(Short priorityNumber) {
        this.priorityNumber = priorityNumber;
    }

    public Timestamp getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Timestamp activeTime) {
        this.activeTime = activeTime;
    }

    public Timestamp getInactiveTime() {
        return inactiveTime;
    }

    public void setInactiveTime(Timestamp inactiveTime) {
        this.inactiveTime = inactiveTime;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Long getWarnNum() {
        return warnNum;
    }

    public void setWarnNum(Long warnNum) {
        this.warnNum = warnNum;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }
}
