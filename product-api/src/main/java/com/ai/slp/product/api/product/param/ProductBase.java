package com.ai.slp.product.api.product.param;

import java.sql.Date;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 商品基础类
 * 
 * Date: 2016年4月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class ProductBase extends BaseInfo{
    
    /**
     * 状态
     * 0
     * 1未编辑2已编辑
     * 3审核中4审核未通过
     * 5在售
     * 6仓库中（审核通过放入） 61售罄下架62废弃下架63自动下架
     * 7停用8废弃
     */
    private String state;
    
    /**
     * 操作人ID
     */
    private long operId;
    
    /**
     * 操作时间
     */
    private Date operTime;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getOperId() {
        return operId;
    }

    public void setOperId(long operId) {
        this.operId = operId;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }
    
    
}
