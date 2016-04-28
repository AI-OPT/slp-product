package com.ai.slp.product.api.normproduct.param;

import java.util.Date;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 属性值返回参数
 * 
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class AttrValInfo extends BaseResponse {

    /**
     * 属性值ID
     */
    private long attrvalueDefId;
    
    /**
     * 属性值名称
     */
    private String attrValueName;
    
    /**
     * 首字母
     */
    private String firstLetter;

    /**
     * 操作人ID
     */
    private long operId;
    
    /**
     * 操作时间
     */
    private Date operTime;

    public long getAttrvalueDefId() {
        return attrvalueDefId;
    }

    public void setAttrvalueDefId(long attrvalueDefId) {
        this.attrvalueDefId = attrvalueDefId;
    }

    public String getAttrValueName() {
        return attrValueName;
    }

    public void setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
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
