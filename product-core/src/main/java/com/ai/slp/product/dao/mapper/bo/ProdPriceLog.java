package com.ai.slp.product.dao.mapper.bo;

import java.sql.Timestamp;

public class ProdPriceLog {
    private String logId;

    private String objType;

    private String objId;

    private Long updatePrice;

    private Long updatePeice2;

    private Long operId;

    private Timestamp operTime;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType == null ? null : objType.trim();
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId == null ? null : objId.trim();
    }

    public Long getUpdatePrice() {
        return updatePrice;
    }

    public void setUpdatePrice(Long updatePrice) {
        this.updatePrice = updatePrice;
    }

    public Long getUpdatePeice2() {
        return updatePeice2;
    }

    public void setUpdatePeice2(Long updatePeice2) {
        this.updatePeice2 = updatePeice2;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public Timestamp getOperTime() {
        return operTime;
    }

    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
    }
}