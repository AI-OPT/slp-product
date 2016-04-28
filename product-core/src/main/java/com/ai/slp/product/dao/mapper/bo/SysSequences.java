package com.ai.slp.product.dao.mapper.bo;

public class SysSequences {
    private String sequenceName;

    private Long startBy;

    private Long incrementBy;

    private Long lastNumber;

    private Long jvmStepBy;

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName == null ? null : sequenceName.trim();
    }

    public Long getStartBy() {
        return startBy;
    }

    public void setStartBy(Long startBy) {
        this.startBy = startBy;
    }

    public Long getIncrementBy() {
        return incrementBy;
    }

    public void setIncrementBy(Long incrementBy) {
        this.incrementBy = incrementBy;
    }

    public Long getLastNumber() {
        return lastNumber;
    }

    public void setLastNumber(Long lastNumber) {
        this.lastNumber = lastNumber;
    }

    public Long getJvmStepBy() {
        return jvmStepBy;
    }

    public void setJvmStepBy(Long jvmStepBy) {
        this.jvmStepBy = jvmStepBy;
    }
}