package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 库存售价信息<br>
 * 用于更新库存销售价
 *
 * Date: 2016年4月24日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class STOStorageSalePrice extends BaseInfo {
    /**
     * 库存id
     */
    @NotNull(message = "库存标识不能为空",
            groups = { IStorageSV.UpdateMultiStorageSalePrice.class })
    private String storageId;

    /**
     * 销售价
     */
    @NotNull(message = "销售价不能为空",
            groups = { IStorageSV.UpdateMultiStorageSalePrice.class })
    @Min(value = 0,message = "销售价不能小于0",
            groups = { IStorageSV.UpdateMultiStorageSalePrice.class })
    private Long salePrice;
    /**
     * 操作人ID
     */
    @NotNull(message = "操作人不能为空",
            groups = { IStorageSV.UpdateMultiStorageSalePrice.class })
    private Long operId;

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
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
}
