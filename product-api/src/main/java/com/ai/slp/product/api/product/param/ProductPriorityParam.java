package com.ai.slp.product.api.product.param;

/**
 * 申请优先处理参数
 * 
 * Date: 2016年4月27日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class ProductPriorityParam extends ProductBase{

    /**
     * 商品ID
     */
    private String prodId;

    /**
     * 申请优先级:1优先0普通
     */
    private int priorityNumber;
    
    /**
     * 优先理由
     */
    private String priorityReason;

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public int getPriorityNumber() {
        return priorityNumber;
    }

    public void setPriorityNumber(int priorityNumber) {
        this.priorityNumber = priorityNumber;
    }

    public String getPriorityReason() {
        return priorityReason;
    }

    public void setPriorityReason(String priorityReason) {
        this.priorityReason = priorityReason;
    }
    
    
}
