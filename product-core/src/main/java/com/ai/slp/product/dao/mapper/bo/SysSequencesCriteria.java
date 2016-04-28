package com.ai.slp.product.dao.mapper.bo;

import java.util.ArrayList;
import java.util.List;

public class SysSequencesCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public SysSequencesCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSequenceNameIsNull() {
            addCriterion("SEQUENCE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSequenceNameIsNotNull() {
            addCriterion("SEQUENCE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSequenceNameEqualTo(String value) {
            addCriterion("SEQUENCE_NAME =", value, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andSequenceNameNotEqualTo(String value) {
            addCriterion("SEQUENCE_NAME <>", value, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andSequenceNameGreaterThan(String value) {
            addCriterion("SEQUENCE_NAME >", value, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andSequenceNameGreaterThanOrEqualTo(String value) {
            addCriterion("SEQUENCE_NAME >=", value, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andSequenceNameLessThan(String value) {
            addCriterion("SEQUENCE_NAME <", value, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andSequenceNameLessThanOrEqualTo(String value) {
            addCriterion("SEQUENCE_NAME <=", value, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andSequenceNameLike(String value) {
            addCriterion("SEQUENCE_NAME like", value, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andSequenceNameNotLike(String value) {
            addCriterion("SEQUENCE_NAME not like", value, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andSequenceNameIn(List<String> values) {
            addCriterion("SEQUENCE_NAME in", values, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andSequenceNameNotIn(List<String> values) {
            addCriterion("SEQUENCE_NAME not in", values, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andSequenceNameBetween(String value1, String value2) {
            addCriterion("SEQUENCE_NAME between", value1, value2, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andSequenceNameNotBetween(String value1, String value2) {
            addCriterion("SEQUENCE_NAME not between", value1, value2, "sequenceName");
            return (Criteria) this;
        }

        public Criteria andStartByIsNull() {
            addCriterion("START_BY is null");
            return (Criteria) this;
        }

        public Criteria andStartByIsNotNull() {
            addCriterion("START_BY is not null");
            return (Criteria) this;
        }

        public Criteria andStartByEqualTo(Long value) {
            addCriterion("START_BY =", value, "startBy");
            return (Criteria) this;
        }

        public Criteria andStartByNotEqualTo(Long value) {
            addCriterion("START_BY <>", value, "startBy");
            return (Criteria) this;
        }

        public Criteria andStartByGreaterThan(Long value) {
            addCriterion("START_BY >", value, "startBy");
            return (Criteria) this;
        }

        public Criteria andStartByGreaterThanOrEqualTo(Long value) {
            addCriterion("START_BY >=", value, "startBy");
            return (Criteria) this;
        }

        public Criteria andStartByLessThan(Long value) {
            addCriterion("START_BY <", value, "startBy");
            return (Criteria) this;
        }

        public Criteria andStartByLessThanOrEqualTo(Long value) {
            addCriterion("START_BY <=", value, "startBy");
            return (Criteria) this;
        }

        public Criteria andStartByIn(List<Long> values) {
            addCriterion("START_BY in", values, "startBy");
            return (Criteria) this;
        }

        public Criteria andStartByNotIn(List<Long> values) {
            addCriterion("START_BY not in", values, "startBy");
            return (Criteria) this;
        }

        public Criteria andStartByBetween(Long value1, Long value2) {
            addCriterion("START_BY between", value1, value2, "startBy");
            return (Criteria) this;
        }

        public Criteria andStartByNotBetween(Long value1, Long value2) {
            addCriterion("START_BY not between", value1, value2, "startBy");
            return (Criteria) this;
        }

        public Criteria andIncrementByIsNull() {
            addCriterion("INCREMENT_BY is null");
            return (Criteria) this;
        }

        public Criteria andIncrementByIsNotNull() {
            addCriterion("INCREMENT_BY is not null");
            return (Criteria) this;
        }

        public Criteria andIncrementByEqualTo(Long value) {
            addCriterion("INCREMENT_BY =", value, "incrementBy");
            return (Criteria) this;
        }

        public Criteria andIncrementByNotEqualTo(Long value) {
            addCriterion("INCREMENT_BY <>", value, "incrementBy");
            return (Criteria) this;
        }

        public Criteria andIncrementByGreaterThan(Long value) {
            addCriterion("INCREMENT_BY >", value, "incrementBy");
            return (Criteria) this;
        }

        public Criteria andIncrementByGreaterThanOrEqualTo(Long value) {
            addCriterion("INCREMENT_BY >=", value, "incrementBy");
            return (Criteria) this;
        }

        public Criteria andIncrementByLessThan(Long value) {
            addCriterion("INCREMENT_BY <", value, "incrementBy");
            return (Criteria) this;
        }

        public Criteria andIncrementByLessThanOrEqualTo(Long value) {
            addCriterion("INCREMENT_BY <=", value, "incrementBy");
            return (Criteria) this;
        }

        public Criteria andIncrementByIn(List<Long> values) {
            addCriterion("INCREMENT_BY in", values, "incrementBy");
            return (Criteria) this;
        }

        public Criteria andIncrementByNotIn(List<Long> values) {
            addCriterion("INCREMENT_BY not in", values, "incrementBy");
            return (Criteria) this;
        }

        public Criteria andIncrementByBetween(Long value1, Long value2) {
            addCriterion("INCREMENT_BY between", value1, value2, "incrementBy");
            return (Criteria) this;
        }

        public Criteria andIncrementByNotBetween(Long value1, Long value2) {
            addCriterion("INCREMENT_BY not between", value1, value2, "incrementBy");
            return (Criteria) this;
        }

        public Criteria andLastNumberIsNull() {
            addCriterion("LAST_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andLastNumberIsNotNull() {
            addCriterion("LAST_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andLastNumberEqualTo(Long value) {
            addCriterion("LAST_NUMBER =", value, "lastNumber");
            return (Criteria) this;
        }

        public Criteria andLastNumberNotEqualTo(Long value) {
            addCriterion("LAST_NUMBER <>", value, "lastNumber");
            return (Criteria) this;
        }

        public Criteria andLastNumberGreaterThan(Long value) {
            addCriterion("LAST_NUMBER >", value, "lastNumber");
            return (Criteria) this;
        }

        public Criteria andLastNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("LAST_NUMBER >=", value, "lastNumber");
            return (Criteria) this;
        }

        public Criteria andLastNumberLessThan(Long value) {
            addCriterion("LAST_NUMBER <", value, "lastNumber");
            return (Criteria) this;
        }

        public Criteria andLastNumberLessThanOrEqualTo(Long value) {
            addCriterion("LAST_NUMBER <=", value, "lastNumber");
            return (Criteria) this;
        }

        public Criteria andLastNumberIn(List<Long> values) {
            addCriterion("LAST_NUMBER in", values, "lastNumber");
            return (Criteria) this;
        }

        public Criteria andLastNumberNotIn(List<Long> values) {
            addCriterion("LAST_NUMBER not in", values, "lastNumber");
            return (Criteria) this;
        }

        public Criteria andLastNumberBetween(Long value1, Long value2) {
            addCriterion("LAST_NUMBER between", value1, value2, "lastNumber");
            return (Criteria) this;
        }

        public Criteria andLastNumberNotBetween(Long value1, Long value2) {
            addCriterion("LAST_NUMBER not between", value1, value2, "lastNumber");
            return (Criteria) this;
        }

        public Criteria andJvmStepByIsNull() {
            addCriterion("JVM_STEP_BY is null");
            return (Criteria) this;
        }

        public Criteria andJvmStepByIsNotNull() {
            addCriterion("JVM_STEP_BY is not null");
            return (Criteria) this;
        }

        public Criteria andJvmStepByEqualTo(Long value) {
            addCriterion("JVM_STEP_BY =", value, "jvmStepBy");
            return (Criteria) this;
        }

        public Criteria andJvmStepByNotEqualTo(Long value) {
            addCriterion("JVM_STEP_BY <>", value, "jvmStepBy");
            return (Criteria) this;
        }

        public Criteria andJvmStepByGreaterThan(Long value) {
            addCriterion("JVM_STEP_BY >", value, "jvmStepBy");
            return (Criteria) this;
        }

        public Criteria andJvmStepByGreaterThanOrEqualTo(Long value) {
            addCriterion("JVM_STEP_BY >=", value, "jvmStepBy");
            return (Criteria) this;
        }

        public Criteria andJvmStepByLessThan(Long value) {
            addCriterion("JVM_STEP_BY <", value, "jvmStepBy");
            return (Criteria) this;
        }

        public Criteria andJvmStepByLessThanOrEqualTo(Long value) {
            addCriterion("JVM_STEP_BY <=", value, "jvmStepBy");
            return (Criteria) this;
        }

        public Criteria andJvmStepByIn(List<Long> values) {
            addCriterion("JVM_STEP_BY in", values, "jvmStepBy");
            return (Criteria) this;
        }

        public Criteria andJvmStepByNotIn(List<Long> values) {
            addCriterion("JVM_STEP_BY not in", values, "jvmStepBy");
            return (Criteria) this;
        }

        public Criteria andJvmStepByBetween(Long value1, Long value2) {
            addCriterion("JVM_STEP_BY between", value1, value2, "jvmStepBy");
            return (Criteria) this;
        }

        public Criteria andJvmStepByNotBetween(Long value1, Long value2) {
            addCriterion("JVM_STEP_BY not between", value1, value2, "jvmStepBy");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}