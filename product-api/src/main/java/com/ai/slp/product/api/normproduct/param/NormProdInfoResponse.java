package com.ai.slp.product.api.normproduct.param;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * 标准品详情信息<br>
 *
 * Date: 2016年4月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class NormProdInfoResponse extends NormProdBaseResponse {

    /**
     * 创建者名称
     */
    private String createName;

    /**
     * 操作人
     */
    private Long operId;
    /**
     * 市场价
     */
    private Long marketPrice;

    /**
     * 操作人名称
     */
    private String operName;

    /**
     * 操作时间
     */
    private Timestamp operTime;

    /**
     * 属性与属性值对应关系
     */
    private Map<Long,Set<String>> attrAndValueIds;

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public Long getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Long marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Timestamp getOperTime() {
        return operTime;
    }

    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
    }

    public Map<Long, Set<String>> getAttrAndValueIds() {
        return attrAndValueIds;
    }

    public void setAttrAndValueIds(Map<Long, Set<String>> attrAndValueIds) {
        this.attrAndValueIds = attrAndValueIds;
    }
}
