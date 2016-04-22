package com.ai.slp.product.api.normproduct.param;


import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.normproduct.interfaces.INormProductSV;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 标准品市场价更新请求<br>
 *
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class MarketPrice4Update extends BaseInfo {
    /**
     * 标准品ID
     */
    @NotNull(message = "标准品ID不能为空",
            groups = { INormProductSV.UpdateMarketPrice.class })
    private String productId;
    /**
     * 市场价
     */
    @Min(value = 0,message = "市场价格不能小于0",
            groups = { INormProductSV.UpdateMarketPrice.class })
    private long marketPrice;

    /**
     * 操作人ID<br>
     * 更新时不能为空
     */
    @NotNull(message = "创建人ID不能为空",
            groups = { INormProductSV.UpdateMarketPrice.class})
    private Long operId;
    /**
     * 操作时间<br>
     * 若为空,更新时则填充服务接收时间
     */
    private Date operTime;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public long getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(long marketPrice) {
        this.marketPrice = marketPrice;
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
