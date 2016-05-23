package com.ai.slp.product.dao.mapper.bo.product;

import java.sql.Timestamp;

public class ProdPicture {
    private Long proPictureId;

    private String prodId;

    private String attrvalueDefId;

    private String picUses;

    private String picType;

    private String vfsId;

    private String isMainPic;

    private Short serialNumber;

    private String state;

    private Long operId;

    private Timestamp operTime;

    public Long getProPictureId() {
        return proPictureId;
    }

    public void setProPictureId(Long proPictureId) {
        this.proPictureId = proPictureId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public String getAttrvalueDefId() {
        return attrvalueDefId;
    }

    public void setAttrvalueDefId(String attrvalueDefId) {
        this.attrvalueDefId = attrvalueDefId == null ? null : attrvalueDefId.trim();
    }

    public String getPicUses() {
        return picUses;
    }

    public void setPicUses(String picUses) {
        this.picUses = picUses == null ? null : picUses.trim();
    }

    public String getPicType() {
        return picType;
    }

    public void setPicType(String picType) {
        this.picType = picType == null ? null : picType.trim();
    }

    public String getVfsId() {
        return vfsId;
    }

    public void setVfsId(String vfsId) {
        this.vfsId = vfsId == null ? null : vfsId.trim();
    }

    public String getIsMainPic() {
        return isMainPic;
    }

    public void setIsMainPic(String isMainPic) {
        this.isMainPic = isMainPic == null ? null : isMainPic.trim();
    }

    public Short getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Short serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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