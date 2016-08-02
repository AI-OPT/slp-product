package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 库存组售价信息<br>
 * 用于更新库存组销售价
 *
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StorageGroupSalePrice extends BaseInfo {
    private static final long serialVersionUID = 1L;
    /**
     * 销售商（商户）标识，必填<br>
     */
    @NotBlank(message = "销售商（商户）标识不能为空",groups = { IStorageSV.UpdateStorageGroupSalePrice.class})
    private String supplierId;

	/**
     * 库存组标识,必填
     */
    @NotBlank(message = "库存组标识不能为空",
            groups = { IStorageSV.UpdateStorageGroupSalePrice.class})
    private String storageGroupId;

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

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getStorageGroupId() {
        return storageGroupId;
    }

    public void setStorageGroupId(String storageGroupId) {
        this.storageGroupId = storageGroupId;
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

}
