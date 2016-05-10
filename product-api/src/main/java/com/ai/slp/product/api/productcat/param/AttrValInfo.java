package com.ai.slp.product.api.productcat.param;

import java.sql.Timestamp;

/**
 * 属性值分页查询返回参数
 * 
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public class AttrValInfo {

    /**
     * 属性值ID
     */
    private String attrvalueDefId;
    
    /**
     * 属性值名称
     */
    private String attrValueName;
    
    /**
     * 属性值首字母
     */
    private String firstLetter;

    /**
     * 操作人ID
     */
    private long operId;
    
    /**
     *操作人
     */
    private String oper;
    
    /**
     * 操作时间
     */
    private Timestamp operTime;

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getAttrvalueDefId() {
        return attrvalueDefId;
    }

    public void setAttrvalueDefId(String attrvalueDefId) {
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

    public Timestamp getOperTime() {
        return operTime;
    }

    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
    }


}
