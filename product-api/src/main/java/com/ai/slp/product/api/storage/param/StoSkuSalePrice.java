package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 库存售价信息<br>
 * 用于更新具有销售属性库存销售价
 *
 * Date: 2016年8月8日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StoSkuSalePrice extends BaseInfo {
    private static final long serialVersionUID = 1L;

    /**
     * 销售商（商户）标识，必填<br>
     */
    @NotBlank(message = "销售商（商户）标识不能为空")
    private String supplierId;
    /**
     * 库存组标识,必填
     */
    @NotBlank(message = "库存组标识不能为空")
    private String groupId;
    /**
     * 操作人ID，必填
     */
    @NotNull(message = "操作人不能为空")
    private Long operId;
    /**
     * 库存组优先级
     */
    @NotNull(message = "优先级不能为空")
    private Short priorityNum;
    /**
     * 库存与待更新价格<br>
     * K:SKU标识;V:sku销售价,不能为空,不能小于0
     */
    private Map<String,Long> storageSalePrice;

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

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public Short getPriorityNum() {
        return priorityNum;
    }

    public void setPriorityNum(Short priorityNum) {
        this.priorityNum = priorityNum;
    }

    public Map<String, Long> getStorageSalePrice() {
        return storageSalePrice;
    }

    public void setStorageSalePrice(Map<String, Long> storageSalePrice) {
        this.storageSalePrice = storageSalePrice;
    }
}
