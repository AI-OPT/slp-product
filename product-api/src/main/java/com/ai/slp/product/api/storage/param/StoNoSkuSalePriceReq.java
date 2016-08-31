package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 库存售价信息<br>
 * 用于更新没有销售属性库存销售价
 *
 * Date: 2016年4月24日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StoNoSkuSalePriceReq extends BaseInfo {
    private static final long serialVersionUID = 1L;

    /**
     * 销售商（商户）标识，必填<br>
     */
    @NotBlank(message = "销售商（商户）标识不能为空",
            groups = { IStorageSV.UpdateMultiStorageSalePrice.class})
    private String supplierId;
    /**
     * 操作人ID，必填
     */
    @NotNull(message = "操作人不能为空",
            groups = { IStorageSV.UpdateMultiStorageSalePrice.class })
    private Long operId;
    /**
     * 库存与待更新价格<br>
     * K:库存优先级;V:库存对应销售价,不能为空,不能小于0
     */
    private List<StoNoSkuSalePrice> storageSalePrice;

    public Long getOperId() {
        return operId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public List<StoNoSkuSalePrice> getStorageSalePrice() {
        return storageSalePrice;
    }

    public void setStorageSalePrice(List<StoNoSkuSalePrice> storageSalePrice) {
        this.storageSalePrice = storageSalePrice;
    }
}

