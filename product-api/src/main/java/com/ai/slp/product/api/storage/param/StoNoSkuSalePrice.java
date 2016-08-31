package com.ai.slp.product.api.storage.param;

import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 库存售价信息<br>
 * 用于更新没有销售属性库存销售价
 *
 * Date: 2016年8月31日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StoNoSkuSalePrice implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 库存组标识,必填
     */
    @NotBlank(message = "库存组标识不能为空",
            groups = { IStorageSV.UpdateMultiStorageSalePrice.class})
    private String groupId;

    /**
     * 优先级,必填
     */
    @NotNull(message = "优先级不能为空")
    private Short PriorityNumber;
    /**
     * 销售价格(单位:厘),必填
     */
    @NotNull(message = "销售价格不能为空")
    private Long salePrice;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Short getPriorityNumber() {
        return PriorityNumber;
    }

    public void setPriorityNumber(Short priorityNumber) {
        PriorityNumber = priorityNumber;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }
}
