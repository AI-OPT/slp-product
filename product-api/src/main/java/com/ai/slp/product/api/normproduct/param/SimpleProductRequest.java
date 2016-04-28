package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.normproduct.interfaces.INormProductSV;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 标准品废弃请求信息<br>
 *
 * Date: 2016年4月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class SimpleProductRequest extends BaseInfo {
    /**
     * 标准品ID
     * 执行废弃操作和按照id查询时,均不能为空
     */
    @NotNull(message = "标准品ID不能为空",
            groups = { INormProductSV.DiscardProduct.class,INormProductSV.QueryProducById.class })
    private String productId;
    /**
     * 操作人<br>
     * 执行废弃操作时,不能为空
     */
    @NotNull(message = "操作人ID不能为空",
            groups = { INormProductSV.DiscardProduct.class })
    private Long operId;

    /**
     * 操作时间
     */
    private Date operTime;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }
}
