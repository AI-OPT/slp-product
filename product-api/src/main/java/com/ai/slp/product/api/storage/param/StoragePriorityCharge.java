package com.ai.slp.product.api.storage.param;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;

/**
 * 变更库存组下库存的优先级<br>
 *
 *
 * Date: 2016年4月21日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StoragePriorityCharge extends BaseInfo {
    private static final long serialVersionUID = 1L;
	/**
     * 库存组标识,必填
     */
    @NotBlank(message = "库存组标识不能小于0",
            groups = {IStorageSV.ChargeStoragePriority.class})
    private String storageGroupId;
//    /**
//     * 原优先级,必填<br>
//     * 不能小于0,不能与目标优先级一致
//     */
//    @Min(value = 0,message = "将修改优先级不能小于0",
//            groups = {IStorageSV.ChargeStoragePriority.class})
//    private int oldLevel;
//    /**
//     * 目标优先级,必填<br>
//     * 不能小于0,不能与原优先级一致
//     */
//    @Min(value = 0,message = "变更的优先级不能小于0",
//            groups = {IStorageSV.ChargeStoragePriority.class})
//    private int newLevel;
    
    /**
     * 优先级对应的库存ID集合
     */
    private Map<Short,List<String>> priorityMap;
    /**
     * 操作人,必填
     */
    @NotNull(message = "操作人ID不能为空",
            groups = {IStorageSV.ChargeStoragePriority.class})
    private Long operId;

    public String getStorageGroupId() {
        return storageGroupId;
    }

    public void setStorageGroupId(String groupId) {
        this.storageGroupId = groupId;
    }

//    public int getOldLevel() {
//        return oldLevel;
//    }
//
//    public void setOldLevel(int oldLevel) {
//        this.oldLevel = oldLevel;
//    }
//
//    public int getNewLevel() {
//        return newLevel;
//    }
//
//    public void setNewLevel(int newLevel) {
//        this.newLevel = newLevel;
//    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

	public Map<Short,List<String>> getPriorityMap() {
		return priorityMap;
	}

	public void setPriorityMap(Map<Short,List<String>> priorityMap) {
		this.priorityMap = priorityMap;
	}
}
