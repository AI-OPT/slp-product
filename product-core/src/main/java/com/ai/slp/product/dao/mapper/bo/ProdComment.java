package com.ai.slp.product.dao.mapper.bo;

import java.sql.Timestamp;

public class ProdComment {
    private String commentId;

    private String orderId;

    private String subOrderId;

    private String standedProdId;

    private String prodId;

    private String skuId;

    private String supplierId;

    private String userId;

    private String commentBody;

    private Long shopScoreMs;

    private Long shopScoreFw;

    private Long shopScoreWl;

    private Timestamp commentTime;

    private String state;

    private String replyState;

    private String isPicture;

    private String operId;

    private Timestamp operTime;

    private String tenantId;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId == null ? null : commentId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(String subOrderId) {
        this.subOrderId = subOrderId == null ? null : subOrderId.trim();
    }

    public String getStandedProdId() {
        return standedProdId;
    }

    public void setStandedProdId(String standedProdId) {
        this.standedProdId = standedProdId == null ? null : standedProdId.trim();
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId == null ? null : skuId.trim();
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody == null ? null : commentBody.trim();
    }

    public Long getShopScoreMs() {
        return shopScoreMs;
    }

    public void setShopScoreMs(Long shopScoreMs) {
        this.shopScoreMs = shopScoreMs;
    }

    public Long getShopScoreFw() {
        return shopScoreFw;
    }

    public void setShopScoreFw(Long shopScoreFw) {
        this.shopScoreFw = shopScoreFw;
    }

    public Long getShopScoreWl() {
        return shopScoreWl;
    }

    public void setShopScoreWl(Long shopScoreWl) {
        this.shopScoreWl = shopScoreWl;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getReplyState() {
        return replyState;
    }

    public void setReplyState(String replyState) {
        this.replyState = replyState == null ? null : replyState.trim();
    }

    public String getIsPicture() {
        return isPicture;
    }

    public void setIsPicture(String isPicture) {
        this.isPicture = isPicture == null ? null : isPicture.trim();
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId == null ? null : operId.trim();
    }

    public Timestamp getOperTime() {
        return operTime;
    }

    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }
}