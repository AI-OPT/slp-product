package com.ai.slp.product.dao.mapper.bo.storage;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class WarnReceiveStaffCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public WarnReceiveStaffCriteria() {
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

        public Criteria andWarnReceiveStaffIdIsNull() {
            addCriterion("WARN_RECEIVE_STAFF_ID is null");
            return (Criteria) this;
        }

        public Criteria andWarnReceiveStaffIdIsNotNull() {
            addCriterion("WARN_RECEIVE_STAFF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andWarnReceiveStaffIdEqualTo(String value) {
            addCriterion("WARN_RECEIVE_STAFF_ID =", value, "warnReceiveStaffId");
            return (Criteria) this;
        }

        public Criteria andWarnReceiveStaffIdNotEqualTo(String value) {
            addCriterion("WARN_RECEIVE_STAFF_ID <>", value, "warnReceiveStaffId");
            return (Criteria) this;
        }

        public Criteria andWarnReceiveStaffIdGreaterThan(String value) {
            addCriterion("WARN_RECEIVE_STAFF_ID >", value, "warnReceiveStaffId");
            return (Criteria) this;
        }

        public Criteria andWarnReceiveStaffIdGreaterThanOrEqualTo(String value) {
            addCriterion("WARN_RECEIVE_STAFF_ID >=", value, "warnReceiveStaffId");
            return (Criteria) this;
        }

        public Criteria andWarnReceiveStaffIdLessThan(String value) {
            addCriterion("WARN_RECEIVE_STAFF_ID <", value, "warnReceiveStaffId");
            return (Criteria) this;
        }

        public Criteria andWarnReceiveStaffIdLessThanOrEqualTo(String value) {
            addCriterion("WARN_RECEIVE_STAFF_ID <=", value, "warnReceiveStaffId");
            return (Criteria) this;
        }

        public Criteria andWarnReceiveStaffIdLike(String value) {
            addCriterion("WARN_RECEIVE_STAFF_ID like", value, "warnReceiveStaffId");
            return (Criteria) this;
        }

        public Criteria andWarnReceiveStaffIdNotLike(String value) {
            addCriterion("WARN_RECEIVE_STAFF_ID not like", value, "warnReceiveStaffId");
            return (Criteria) this;
        }

        public Criteria andWarnReceiveStaffIdIn(List<String> values) {
            addCriterion("WARN_RECEIVE_STAFF_ID in", values, "warnReceiveStaffId");
            return (Criteria) this;
        }

        public Criteria andWarnReceiveStaffIdNotIn(List<String> values) {
            addCriterion("WARN_RECEIVE_STAFF_ID not in", values, "warnReceiveStaffId");
            return (Criteria) this;
        }

        public Criteria andWarnReceiveStaffIdBetween(String value1, String value2) {
            addCriterion("WARN_RECEIVE_STAFF_ID between", value1, value2, "warnReceiveStaffId");
            return (Criteria) this;
        }

        public Criteria andWarnReceiveStaffIdNotBetween(String value1, String value2) {
            addCriterion("WARN_RECEIVE_STAFF_ID not between", value1, value2, "warnReceiveStaffId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNull() {
            addCriterion("TENANT_ID is null");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNotNull() {
            addCriterion("TENANT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTenantIdEqualTo(String value) {
            addCriterion("TENANT_ID =", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotEqualTo(String value) {
            addCriterion("TENANT_ID <>", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThan(String value) {
            addCriterion("TENANT_ID >", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThanOrEqualTo(String value) {
            addCriterion("TENANT_ID >=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThan(String value) {
            addCriterion("TENANT_ID <", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThanOrEqualTo(String value) {
            addCriterion("TENANT_ID <=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLike(String value) {
            addCriterion("TENANT_ID like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotLike(String value) {
            addCriterion("TENANT_ID not like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIn(List<String> values) {
            addCriterion("TENANT_ID in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotIn(List<String> values) {
            addCriterion("TENANT_ID not in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdBetween(String value1, String value2) {
            addCriterion("TENANT_ID between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotBetween(String value1, String value2) {
            addCriterion("TENANT_ID not between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andObiectTypeIsNull() {
            addCriterion("OBIECT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andObiectTypeIsNotNull() {
            addCriterion("OBIECT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andObiectTypeEqualTo(String value) {
            addCriterion("OBIECT_TYPE =", value, "obiectType");
            return (Criteria) this;
        }

        public Criteria andObiectTypeNotEqualTo(String value) {
            addCriterion("OBIECT_TYPE <>", value, "obiectType");
            return (Criteria) this;
        }

        public Criteria andObiectTypeGreaterThan(String value) {
            addCriterion("OBIECT_TYPE >", value, "obiectType");
            return (Criteria) this;
        }

        public Criteria andObiectTypeGreaterThanOrEqualTo(String value) {
            addCriterion("OBIECT_TYPE >=", value, "obiectType");
            return (Criteria) this;
        }

        public Criteria andObiectTypeLessThan(String value) {
            addCriterion("OBIECT_TYPE <", value, "obiectType");
            return (Criteria) this;
        }

        public Criteria andObiectTypeLessThanOrEqualTo(String value) {
            addCriterion("OBIECT_TYPE <=", value, "obiectType");
            return (Criteria) this;
        }

        public Criteria andObiectTypeLike(String value) {
            addCriterion("OBIECT_TYPE like", value, "obiectType");
            return (Criteria) this;
        }

        public Criteria andObiectTypeNotLike(String value) {
            addCriterion("OBIECT_TYPE not like", value, "obiectType");
            return (Criteria) this;
        }

        public Criteria andObiectTypeIn(List<String> values) {
            addCriterion("OBIECT_TYPE in", values, "obiectType");
            return (Criteria) this;
        }

        public Criteria andObiectTypeNotIn(List<String> values) {
            addCriterion("OBIECT_TYPE not in", values, "obiectType");
            return (Criteria) this;
        }

        public Criteria andObiectTypeBetween(String value1, String value2) {
            addCriterion("OBIECT_TYPE between", value1, value2, "obiectType");
            return (Criteria) this;
        }

        public Criteria andObiectTypeNotBetween(String value1, String value2) {
            addCriterion("OBIECT_TYPE not between", value1, value2, "obiectType");
            return (Criteria) this;
        }

        public Criteria andObjectIdIsNull() {
            addCriterion("OBJECT_ID is null");
            return (Criteria) this;
        }

        public Criteria andObjectIdIsNotNull() {
            addCriterion("OBJECT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andObjectIdEqualTo(String value) {
            addCriterion("OBJECT_ID =", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdNotEqualTo(String value) {
            addCriterion("OBJECT_ID <>", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdGreaterThan(String value) {
            addCriterion("OBJECT_ID >", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT_ID >=", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdLessThan(String value) {
            addCriterion("OBJECT_ID <", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdLessThanOrEqualTo(String value) {
            addCriterion("OBJECT_ID <=", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdLike(String value) {
            addCriterion("OBJECT_ID like", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdNotLike(String value) {
            addCriterion("OBJECT_ID not like", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdIn(List<String> values) {
            addCriterion("OBJECT_ID in", values, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdNotIn(List<String> values) {
            addCriterion("OBJECT_ID not in", values, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdBetween(String value1, String value2) {
            addCriterion("OBJECT_ID between", value1, value2, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdNotBetween(String value1, String value2) {
            addCriterion("OBJECT_ID not between", value1, value2, "objectId");
            return (Criteria) this;
        }

        public Criteria andStaffIdIsNull() {
            addCriterion("STAFF_ID is null");
            return (Criteria) this;
        }

        public Criteria andStaffIdIsNotNull() {
            addCriterion("STAFF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStaffIdEqualTo(Long value) {
            addCriterion("STAFF_ID =", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotEqualTo(Long value) {
            addCriterion("STAFF_ID <>", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdGreaterThan(Long value) {
            addCriterion("STAFF_ID >", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdGreaterThanOrEqualTo(Long value) {
            addCriterion("STAFF_ID >=", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLessThan(Long value) {
            addCriterion("STAFF_ID <", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLessThanOrEqualTo(Long value) {
            addCriterion("STAFF_ID <=", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdIn(List<Long> values) {
            addCriterion("STAFF_ID in", values, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotIn(List<Long> values) {
            addCriterion("STAFF_ID not in", values, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdBetween(Long value1, Long value2) {
            addCriterion("STAFF_ID between", value1, value2, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotBetween(Long value1, Long value2) {
            addCriterion("STAFF_ID not between", value1, value2, "staffId");
            return (Criteria) this;
        }

        public Criteria andWarningTypeIsNull() {
            addCriterion("WARNING_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andWarningTypeIsNotNull() {
            addCriterion("WARNING_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andWarningTypeEqualTo(String value) {
            addCriterion("WARNING_TYPE =", value, "warningType");
            return (Criteria) this;
        }

        public Criteria andWarningTypeNotEqualTo(String value) {
            addCriterion("WARNING_TYPE <>", value, "warningType");
            return (Criteria) this;
        }

        public Criteria andWarningTypeGreaterThan(String value) {
            addCriterion("WARNING_TYPE >", value, "warningType");
            return (Criteria) this;
        }

        public Criteria andWarningTypeGreaterThanOrEqualTo(String value) {
            addCriterion("WARNING_TYPE >=", value, "warningType");
            return (Criteria) this;
        }

        public Criteria andWarningTypeLessThan(String value) {
            addCriterion("WARNING_TYPE <", value, "warningType");
            return (Criteria) this;
        }

        public Criteria andWarningTypeLessThanOrEqualTo(String value) {
            addCriterion("WARNING_TYPE <=", value, "warningType");
            return (Criteria) this;
        }

        public Criteria andWarningTypeLike(String value) {
            addCriterion("WARNING_TYPE like", value, "warningType");
            return (Criteria) this;
        }

        public Criteria andWarningTypeNotLike(String value) {
            addCriterion("WARNING_TYPE not like", value, "warningType");
            return (Criteria) this;
        }

        public Criteria andWarningTypeIn(List<String> values) {
            addCriterion("WARNING_TYPE in", values, "warningType");
            return (Criteria) this;
        }

        public Criteria andWarningTypeNotIn(List<String> values) {
            addCriterion("WARNING_TYPE not in", values, "warningType");
            return (Criteria) this;
        }

        public Criteria andWarningTypeBetween(String value1, String value2) {
            addCriterion("WARNING_TYPE between", value1, value2, "warningType");
            return (Criteria) this;
        }

        public Criteria andWarningTypeNotBetween(String value1, String value2) {
            addCriterion("WARNING_TYPE not between", value1, value2, "warningType");
            return (Criteria) this;
        }

        public Criteria andWarningContentIsNull() {
            addCriterion("WARNING_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andWarningContentIsNotNull() {
            addCriterion("WARNING_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andWarningContentEqualTo(String value) {
            addCriterion("WARNING_CONTENT =", value, "warningContent");
            return (Criteria) this;
        }

        public Criteria andWarningContentNotEqualTo(String value) {
            addCriterion("WARNING_CONTENT <>", value, "warningContent");
            return (Criteria) this;
        }

        public Criteria andWarningContentGreaterThan(String value) {
            addCriterion("WARNING_CONTENT >", value, "warningContent");
            return (Criteria) this;
        }

        public Criteria andWarningContentGreaterThanOrEqualTo(String value) {
            addCriterion("WARNING_CONTENT >=", value, "warningContent");
            return (Criteria) this;
        }

        public Criteria andWarningContentLessThan(String value) {
            addCriterion("WARNING_CONTENT <", value, "warningContent");
            return (Criteria) this;
        }

        public Criteria andWarningContentLessThanOrEqualTo(String value) {
            addCriterion("WARNING_CONTENT <=", value, "warningContent");
            return (Criteria) this;
        }

        public Criteria andWarningContentLike(String value) {
            addCriterion("WARNING_CONTENT like", value, "warningContent");
            return (Criteria) this;
        }

        public Criteria andWarningContentNotLike(String value) {
            addCriterion("WARNING_CONTENT not like", value, "warningContent");
            return (Criteria) this;
        }

        public Criteria andWarningContentIn(List<String> values) {
            addCriterion("WARNING_CONTENT in", values, "warningContent");
            return (Criteria) this;
        }

        public Criteria andWarningContentNotIn(List<String> values) {
            addCriterion("WARNING_CONTENT not in", values, "warningContent");
            return (Criteria) this;
        }

        public Criteria andWarningContentBetween(String value1, String value2) {
            addCriterion("WARNING_CONTENT between", value1, value2, "warningContent");
            return (Criteria) this;
        }

        public Criteria andWarningContentNotBetween(String value1, String value2) {
            addCriterion("WARNING_CONTENT not between", value1, value2, "warningContent");
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