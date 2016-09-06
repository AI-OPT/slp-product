package com.ai.slp.product.dao.mapper.bo;

import java.sql.Timestamp;

public class ProdCommentPicture {
    private String prodCommentPicId;

    private String commentId;

    private String vfsId;

    private String picName;

    private String picAddr;

    private Long serialNumber;

    private String state;

    private Timestamp createTime;

    public String getProdCommentPicId() {
        return prodCommentPicId;
    }

    public void setProdCommentPicId(String prodCommentPicId) {
        this.prodCommentPicId = prodCommentPicId == null ? null : prodCommentPicId.trim();
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId == null ? null : commentId.trim();
    }

    public String getVfsId() {
        return vfsId;
    }

    public void setVfsId(String vfsId) {
        this.vfsId = vfsId == null ? null : vfsId.trim();
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName == null ? null : picName.trim();
    }

    public String getPicAddr() {
        return picAddr;
    }

    public void setPicAddr(String picAddr) {
        this.picAddr = picAddr == null ? null : picAddr.trim();
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}