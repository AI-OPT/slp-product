package com.ai.slp.product.api.prodsupply.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.prodsupply.interfaces.IProdSupplySV;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 供应商品批量保存成本价对象<br>
 *
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class ProdSupplySaveCostPrice extends BaseInfo {
    /**
     * 供应商品标识
     */
    @NotNull(message = "供应商品标识不能为空",groups = {IProdSupplySV.UpdateMultCostPrice.class})
    private String supplyId;
    /**
     * 成本价
     */
    @NotNull(message = "成本价不能为空",groups = {IProdSupplySV.UpdateMultCostPrice.class})
    @Min(value = 0,message = "成本价不能小于0",groups = {IProdSupplySV.UpdateMultCostPrice.class})
    private Long costPrice;
    /**
     * 操作时间
     */
    private Date operTime;
    /**
     * 操作人id
     */
    @NotNull(message = "操作人标识不能为空",groups = {IProdSupplySV.UpdateMultCostPrice.class})
    private Date operId;

    public String getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(String supplyId) {
        this.supplyId = supplyId;
    }

    public Long getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Long costPrice) {
        this.costPrice = costPrice;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public Date getOperId() {
        return operId;
    }

    public void setOperId(Date operId) {
        this.operId = operId;
    }
}
