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
public class StorageSalePrice extends BaseInfo {
    private static final long serialVersionUID = 1L;

	/**
     * 库存id，必填
     */
    @NotNull(message = "库存标识不能为空",
            groups = { IStorageSV.UpdateMultiStorageSalePrice.class })
    private String storageId;

    /**
     * 销售价,必填不能为空且不能小于0
     */
    @NotNull(message = "销售价不能为空",
            groups = { IStorageSV.UpdateMultiStorageSalePrice.class })
    @Min(value = 0,message = "销售价不能小于0",
            groups = { IStorageSV.UpdateMultiStorageSalePrice.class })
    private long salePrice;
    /**
     * 操作人ID，必填
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

    public long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(long salePrice) {
        this.salePrice = salePrice;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }
}
