package com.ai.slp.product.dao.mapper.bo.product;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProductStateLogCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public ProductStateLogCriteria() {
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

        public Criteria andLogIdIsNull() {
            addCriterion("LOG_ID is null");
            return (Criteria) this;
        }

        public Criteria andLogIdIsNotNull() {
            addCriterion("LOG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLogIdEqualTo(String value) {
            addCriterion("LOG_ID =", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotEqualTo(String value) {
            addCriterion("LOG_ID <>", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdGreaterThan(String value) {
            addCriterion("LOG_ID >", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdGreaterThanOrEqualTo(String value) {
            addCriterion("LOG_ID >=", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdLessThan(String value) {
            addCriterion("LOG_ID <", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdLessThanOrEqualTo(String value) {
            addCriterion("LOG_ID <=", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdLike(String value) {
            addCriterion("LOG_ID like", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotLike(String value) {
            addCriterion("LOG_ID not like", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdIn(List<String> values) {
            addCriterion("LOG_ID in", values, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotIn(List<String> values) {
            addCriterion("LOG_ID not in", values, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdBetween(String value1, String value2) {
            addCriterion("LOG_ID between", value1, value2, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotBetween(String value1, String value2) {
            addCriterion("LOG_ID not between", value1, value2, "logId");
            return (Criteria) this;
        }

        public Criteria andProdIdIsNull() {
            addCriterion("PROD_ID is null");
            return (Criteria) this;
        }

        public Criteria andProdIdIsNotNull() {
            addCriterion("PROD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProdIdEqualTo(String value) {
            addCriterion("PROD_ID =", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdNotEqualTo(String value) {
            addCriterion("PROD_ID <>", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdGreaterThan(String value) {
            addCriterion("PROD_ID >", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROD_ID >=", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdLessThan(String value) {
            addCriterion("PROD_ID <", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdLessThanOrEqualTo(String value) {
            addCriterion("PROD_ID <=", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdLike(String value) {
            addCriterion("PROD_ID like", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdNotLike(String value) {
            addCriterion("PROD_ID not like", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdIn(List<String> values) {
            addCriterion("PROD_ID in", values, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdNotIn(List<String> values) {
            addCriterion("PROD_ID not in", values, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdBetween(String value1, String value2) {
            addCriterion("PROD_ID between", value1, value2, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdNotBetween(String value1, String value2) {
            addCriterion("PROD_ID not between", value1, value2, "prodId");
            return (Criteria) this;
        }

        public Criteria andPriorityNumberIsNull() {
            addCriterion("PRIORITY_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andPriorityNumberIsNotNull() {
            addCriterion("PRIORITY_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andPriorityNumberEqualTo(Short value) {
            addCriterion("PRIORITY_NUMBER =", value, "priorityNumber");
            return (Criteria) this;
        }

        public Criteria andPriorityNumberNotEqualTo(Short value) {
            addCriterion("PRIORITY_NUMBER <>", value, "priorityNumber");
            return (Criteria) this;
        }

        public Criteria andPriorityNumberGreaterThan(Short value) {
            addCriterion("PRIORITY_NUMBER >", value, "priorityNumber");
            return (Criteria) this;
        }

        public Criteria andPriorityNumberGreaterThanOrEqualTo(Short value) {
            addCriterion("PRIORITY_NUMBER >=", value, "priorityNumber");
            return (Criteria) this;
        }

        public Criteria andPriorityNumberLessThan(Short value) {
            addCriterion("PRIORITY_NUMBER <", value, "priorityNumber");
            return (Criteria) this;
        }

        public Criteria andPriorityNumberLessThanOrEqualTo(Short value) {
            addCriterion("PRIORITY_NUMBER <=", value, "priorityNumber");
            return (Criteria) this;
        }

        public Criteria andPriorityNumberIn(List<Short> values) {
            addCriterion("PRIORITY_NUMBER in", values, "priorityNumber");
            return (Criteria) this;
        }

        public Criteria andPriorityNumberNotIn(List<Short> values) {
            addCriterion("PRIORITY_NUMBER not in", values, "priorityNumber");
            return (Criteria) this;
        }

        public Criteria andPriorityNumberBetween(Short value1, Short value2) {
            addCriterion("PRIORITY_NUMBER between", value1, value2, "priorityNumber");
            return (Criteria) this;
        }

        public Criteria andPriorityNumberNotBetween(Short value1, Short value2) {
            addCriterion("PRIORITY_NUMBER not between", value1, value2, "priorityNumber");
            return (Criteria) this;
        }

        public Criteria andPriorityReasonIsNull() {
            addCriterion("PRIORITY_REASON is null");
            return (Criteria) this;
        }

        public Criteria andPriorityReasonIsNotNull() {
            addCriterion("PRIORITY_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andPriorityReasonEqualTo(String value) {
            addCriterion("PRIORITY_REASON =", value, "priorityReason");
            return (Criteria) this;
        }

        public Criteria andPriorityReasonNotEqualTo(String value) {
            addCriterion("PRIORITY_REASON <>", value, "priorityReason");
            return (Criteria) this;
        }

        public Criteria andPriorityReasonGreaterThan(String value) {
            addCriterion("PRIORITY_REASON >", value, "priorityReason");
            return (Criteria) this;
        }

        public Criteria andPriorityReasonGreaterThanOrEqualTo(String value) {
            addCriterion("PRIORITY_REASON >=", value, "priorityReason");
            return (Criteria) this;
        }

        public Criteria andPriorityReasonLessThan(String value) {
            addCriterion("PRIORITY_REASON <", value, "priorityReason");
            return (Criteria) this;
        }

        public Criteria andPriorityReasonLessThanOrEqualTo(String value) {
            addCriterion("PRIORITY_REASON <=", value, "priorityReason");
            return (Criteria) this;
        }

        public Criteria andPriorityReasonLike(String value) {
            addCriterion("PRIORITY_REASON like", value, "priorityReason");
            return (Criteria) this;
        }

        public Criteria andPriorityReasonNotLike(String value) {
            addCriterion("PRIORITY_REASON not like", value, "priorityReason");
            return (Criteria) this;
        }

        public Criteria andPriorityReasonIn(List<String> values) {
            addCriterion("PRIORITY_REASON in", values, "priorityReason");
            return (Criteria) this;
        }

        public Criteria andPriorityReasonNotIn(List<String> values) {
            addCriterion("PRIORITY_REASON not in", values, "priorityReason");
            return (Criteria) this;
        }

        public Criteria andPriorityReasonBetween(String value1, String value2) {
            addCriterion("PRIORITY_REASON between", value1, value2, "priorityReason");
            return (Criteria) this;
        }

        public Criteria andPriorityReasonNotBetween(String value1, String value2) {
            addCriterion("PRIORITY_REASON not between", value1, value2, "priorityReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonIsNull() {
            addCriterion("REFUSE_REASON is null");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonIsNotNull() {
            addCriterion("REFUSE_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonEqualTo(String value) {
            addCriterion("REFUSE_REASON =", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonNotEqualTo(String value) {
            addCriterion("REFUSE_REASON <>", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonGreaterThan(String value) {
            addCriterion("REFUSE_REASON >", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonGreaterThanOrEqualTo(String value) {
            addCriterion("REFUSE_REASON >=", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonLessThan(String value) {
            addCriterion("REFUSE_REASON <", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonLessThanOrEqualTo(String value) {
            addCriterion("REFUSE_REASON <=", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonLike(String value) {
            addCriterion("REFUSE_REASON like", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonNotLike(String value) {
            addCriterion("REFUSE_REASON not like", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonIn(List<String> values) {
            addCriterion("REFUSE_REASON in", values, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonNotIn(List<String> values) {
            addCriterion("REFUSE_REASON not in", values, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonBetween(String value1, String value2) {
            addCriterion("REFUSE_REASON between", value1, value2, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonNotBetween(String value1, String value2) {
            addCriterion("REFUSE_REASON not between", value1, value2, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseDesIsNull() {
            addCriterion("REFUSE_DES is null");
            return (Criteria) this;
        }

        public Criteria andRefuseDesIsNotNull() {
            addCriterion("REFUSE_DES is not null");
            return (Criteria) this;
        }

        public Criteria andRefuseDesEqualTo(String value) {
            addCriterion("REFUSE_DES =", value, "refuseDes");
            return (Criteria) this;
        }

        public Criteria andRefuseDesNotEqualTo(String value) {
            addCriterion("REFUSE_DES <>", value, "refuseDes");
            return (Criteria) this;
        }

        public Criteria andRefuseDesGreaterThan(String value) {
            addCriterion("REFUSE_DES >", value, "refuseDes");
            return (Criteria) this;
        }

        public Criteria andRefuseDesGreaterThanOrEqualTo(String value) {
            addCriterion("REFUSE_DES >=", value, "refuseDes");
            return (Criteria) this;
        }

        public Criteria andRefuseDesLessThan(String value) {
            addCriterion("REFUSE_DES <", value, "refuseDes");
            return (Criteria) this;
        }

        public Criteria andRefuseDesLessThanOrEqualTo(String value) {
            addCriterion("REFUSE_DES <=", value, "refuseDes");
            return (Criteria) this;
        }

        public Criteria andRefuseDesLike(String value) {
            addCriterion("REFUSE_DES like", value, "refuseDes");
            return (Criteria) this;
        }

        public Criteria andRefuseDesNotLike(String value) {
            addCriterion("REFUSE_DES not like", value, "refuseDes");
            return (Criteria) this;
        }

        public Criteria andRefuseDesIn(List<String> values) {
            addCriterion("REFUSE_DES in", values, "refuseDes");
            return (Criteria) this;
        }

        public Criteria andRefuseDesNotIn(List<String> values) {
            addCriterion("REFUSE_DES not in", values, "refuseDes");
            return (Criteria) this;
        }

        public Criteria andRefuseDesBetween(String value1, String value2) {
            addCriterion("REFUSE_DES between", value1, value2, "refuseDes");
            return (Criteria) this;
        }

        public Criteria andRefuseDesNotBetween(String value1, String value2) {
            addCriterion("REFUSE_DES not between", value1, value2, "refuseDes");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("STATE is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("STATE is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("STATE =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("STATE <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("STATE >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("STATE >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("STATE <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("STATE <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("STATE like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("STATE not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("STATE in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("STATE not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("STATE between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("STATE not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andOperIdIsNull() {
            addCriterion("OPER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOperIdIsNotNull() {
            addCriterion("OPER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOperIdEqualTo(Long value) {
            addCriterion("OPER_ID =", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdNotEqualTo(Long value) {
            addCriterion("OPER_ID <>", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdGreaterThan(Long value) {
            addCriterion("OPER_ID >", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdGreaterThanOrEqualTo(Long value) {
            addCriterion("OPER_ID >=", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdLessThan(Long value) {
            addCriterion("OPER_ID <", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdLessThanOrEqualTo(Long value) {
            addCriterion("OPER_ID <=", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdIn(List<Long> values) {
            addCriterion("OPER_ID in", values, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdNotIn(List<Long> values) {
            addCriterion("OPER_ID not in", values, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdBetween(Long value1, Long value2) {
            addCriterion("OPER_ID between", value1, value2, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdNotBetween(Long value1, Long value2) {
            addCriterion("OPER_ID not between", value1, value2, "operId");
            return (Criteria) this;
        }

        public Criteria andOperTimeIsNull() {
            addCriterion("OPER_TIME is null");
            return (Criteria) this;
        }

        public Criteria andOperTimeIsNotNull() {
            addCriterion("OPER_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andOperTimeEqualTo(Timestamp value) {
            addCriterion("OPER_TIME =", value, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeNotEqualTo(Timestamp value) {
            addCriterion("OPER_TIME <>", value, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeGreaterThan(Timestamp value) {
            addCriterion("OPER_TIME >", value, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("OPER_TIME >=", value, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeLessThan(Timestamp value) {
            addCriterion("OPER_TIME <", value, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("OPER_TIME <=", value, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeIn(List<Timestamp> values) {
            addCriterion("OPER_TIME in", values, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeNotIn(List<Timestamp> values) {
            addCriterion("OPER_TIME not in", values, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("OPER_TIME between", value1, value2, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("OPER_TIME not between", value1, value2, "operTime");
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