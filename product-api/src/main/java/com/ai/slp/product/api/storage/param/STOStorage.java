package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Map;

/**
 * 虚拟库存信息<br>
 *
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class STOStorage extends BaseInfo{
    private static final long serialVersionUID = 1L;
    /**
     * 销售商(商户)标识,必填
     */
    @NotBlank(message = "销售商(商户)标识不能为空",
            groups = { IStorageSV.SaveStorage.class })
    private String supplierId;
	/**
     * 库存标识<br>
     * 若包含标识则进行更新操作,否则进行添加操作
     */
    private String storageId;
    /**
     * 库存名称,不能为空,且长度不能超过300
     */
    @NotBlank(message = "库存名称不能为空",
            groups = { IStorageSV.SaveStorage.class })
    @Length(max = 300,message = "库存名称长度不允许超过300",
            groups = {IStorageSV.SaveStorage.class})
    private String storageName;

    /**
     * 库存组id,必填
     */
    @NotBlank(message = "库存组标识不能为空",
            groups = { IStorageSV.SaveStorage.class })
    private String storageGroupId;

    /**
     * 虚拟库存量,必填,不能小于0
     */
    @Min(value = 0,message = "虚拟库存量不能小于0",
            groups = {IStorageSV.SaveStorage.class})
    private Long totalNum;
    /**
     * 预警库存量,不能小于0
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
     * 销售价,为空则不更新
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

    /**
     * 操作人ID,必填
     */
    @NotNull(message = "操作人不能为空",
            groups = { IStorageSV.SaveStorage.class })
    private Long operId;

    /**
     * SKU对应的库存量<br>
     * 没有销售属性时,为空
     * K:skuId,V:sku库存的数量
     */
    private Map<String,Long> skuStorageNum;

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public String getStorageGroupId() {
        return storageGroupId;
    }

    public void setStorageGroupId(String groupId) {
        this.storageGroupId = groupId;
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

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public Map<String, Long> getSkuStorageNum() {
        return skuStorageNum;
    }

    public void setSkuStorageNum(Map<String, Long> skuStorageNum) {
        this.skuStorageNum = skuStorageNum;
    }
}
