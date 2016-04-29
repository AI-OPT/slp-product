package com.ai.slp.product.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProdAttrDefCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public ProdAttrDefCriteria() {
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

        public Criteria andAttrIdIsNull() {
            addCriterion("ATTR_ID is null");
            return (Criteria) this;
        }

        public Criteria andAttrIdIsNotNull() {
            addCriterion("ATTR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAttrIdEqualTo(Long value) {
            addCriterion("ATTR_ID =", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdNotEqualTo(Long value) {
            addCriterion("ATTR_ID <>", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdGreaterThan(Long value) {
            addCriterion("ATTR_ID >", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ATTR_ID >=", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdLessThan(Long value) {
            addCriterion("ATTR_ID <", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdLessThanOrEqualTo(Long value) {
            addCriterion("ATTR_ID <=", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdIn(List<Long> values) {
            addCriterion("ATTR_ID in", values, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdNotIn(List<Long> values) {
            addCriterion("ATTR_ID not in", values, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdBetween(Long value1, Long value2) {
            addCriterion("ATTR_ID between", value1, value2, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdNotBetween(Long value1, Long value2) {
            addCriterion("ATTR_ID not between", value1, value2, "attrId");
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

        public Criteria andAttrNameIsNull() {
            addCriterion("ATTR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAttrNameIsNotNull() {
            addCriterion("ATTR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAttrNameEqualTo(String value) {
            addCriterion("ATTR_NAME =", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotEqualTo(String value) {
            addCriterion("ATTR_NAME <>", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameGreaterThan(String value) {
            addCriterion("ATTR_NAME >", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameGreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_NAME >=", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameLessThan(String value) {
            addCriterion("ATTR_NAME <", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameLessThanOrEqualTo(String value) {
            addCriterion("ATTR_NAME <=", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameLike(String value) {
            addCriterion("ATTR_NAME like", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotLike(String value) {
            addCriterion("ATTR_NAME not like", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameIn(List<String> values) {
            addCriterion("ATTR_NAME in", values, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotIn(List<String> values) {
            addCriterion("ATTR_NAME not in", values, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameBetween(String value1, String value2) {
            addCriterion("ATTR_NAME between", value1, value2, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotBetween(String value1, String value2) {
            addCriterion("ATTR_NAME not between", value1, value2, "attrName");
            return (Criteria) this;
        }

        public Criteria andFirstLetterIsNull() {
            addCriterion("FIRST_LETTER is null");
            return (Criteria) this;
        }

        public Criteria andFirstLetterIsNotNull() {
            addCriterion("FIRST_LETTER is not null");
            return (Criteria) this;
        }

        public Criteria andFirstLetterEqualTo(String value) {
            addCriterion("FIRST_LETTER =", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterNotEqualTo(String value) {
            addCriterion("FIRST_LETTER <>", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterGreaterThan(String value) {
            addCriterion("FIRST_LETTER >", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_LETTER >=", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterLessThan(String value) {
            addCriterion("FIRST_LETTER <", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterLessThanOrEqualTo(String value) {
            addCriterion("FIRST_LETTER <=", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterLike(String value) {
            addCriterion("FIRST_LETTER like", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterNotLike(String value) {
            addCriterion("FIRST_LETTER not like", value, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterIn(List<String> values) {
            addCriterion("FIRST_LETTER in", values, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterNotIn(List<String> values) {
            addCriterion("FIRST_LETTER not in", values, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterBetween(String value1, String value2) {
            addCriterion("FIRST_LETTER between", value1, value2, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andFirstLetterNotBetween(String value1, String value2) {
            addCriterion("FIRST_LETTER not between", value1, value2, "firstLetter");
            return (Criteria) this;
        }

        public Criteria andValueWayIsNull() {
            addCriterion("VALUE_WAY is null");
            return (Criteria) this;
        }

        public Criteria andValueWayIsNotNull() {
            addCriterion("VALUE_WAY is not null");
            return (Criteria) this;
        }

        public Criteria andValueWayEqualTo(String value) {
            addCriterion("VALUE_WAY =", value, "valueWay");
            return (Criteria) this;
        }

        public Criteria andValueWayNotEqualTo(String value) {
            addCriterion("VALUE_WAY <>", value, "valueWay");
            return (Criteria) this;
        }

        public Criteria andValueWayGreaterThan(String value) {
            addCriterion("VALUE_WAY >", value, "valueWay");
            return (Criteria) this;
        }

        public Criteria andValueWayGreaterThanOrEqualTo(String value) {
            addCriterion("VALUE_WAY >=", value, "valueWay");
            return (Criteria) this;
        }

        public Criteria andValueWayLessThan(String value) {
            addCriterion("VALUE_WAY <", value, "valueWay");
            return (Criteria) this;
        }

        public Criteria andValueWayLessThanOrEqualTo(String value) {
            addCriterion("VALUE_WAY <=", value, "valueWay");
            return (Criteria) this;
        }

        public Criteria andValueWayLike(String value) {
            addCriterion("VALUE_WAY like", value, "valueWay");
            return (Criteria) this;
        }

        public Criteria andValueWayNotLike(String value) {
            addCriterion("VALUE_WAY not like", value, "valueWay");
            return (Criteria) this;
        }

        public Criteria andValueWayIn(List<String> values) {
            addCriterion("VALUE_WAY in", values, "valueWay");
            return (Criteria) this;
        }

        public Criteria andValueWayNotIn(List<String> values) {
            addCriterion("VALUE_WAY not in", values, "valueWay");
            return (Criteria) this;
        }

        public Criteria andValueWayBetween(String value1, String value2) {
            addCriterion("VALUE_WAY between", value1, value2, "valueWay");
            return (Criteria) this;
        }

        public Criteria andValueWayNotBetween(String value1, String value2) {
            addCriterion("VALUE_WAY not between", value1, value2, "valueWay");
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

        public Criteria andIsAllowCustomIsNull() {
            addCriterion("IS_ALLOW_CUSTOM is null");
            return (Criteria) this;
        }

        public Criteria andIsAllowCustomIsNotNull() {
            addCriterion("IS_ALLOW_CUSTOM is not null");
            return (Criteria) this;
        }

        public Criteria andIsAllowCustomEqualTo(String value) {
            addCriterion("IS_ALLOW_CUSTOM =", value, "isAllowCustom");
            return (Criteria) this;
        }

        public Criteria andIsAllowCustomNotEqualTo(String value) {
            addCriterion("IS_ALLOW_CUSTOM <>", value, "isAllowCustom");
            return (Criteria) this;
        }

        public Criteria andIsAllowCustomGreaterThan(String value) {
            addCriterion("IS_ALLOW_CUSTOM >", value, "isAllowCustom");
            return (Criteria) this;
        }

        public Criteria andIsAllowCustomGreaterThanOrEqualTo(String value) {
            addCriterion("IS_ALLOW_CUSTOM >=", value, "isAllowCustom");
            return (Criteria) this;
        }

        public Criteria andIsAllowCustomLessThan(String value) {
            addCriterion("IS_ALLOW_CUSTOM <", value, "isAllowCustom");
            return (Criteria) this;
        }

        public Criteria andIsAllowCustomLessThanOrEqualTo(String value) {
            addCriterion("IS_ALLOW_CUSTOM <=", value, "isAllowCustom");
            return (Criteria) this;
        }

        public Criteria andIsAllowCustomLike(String value) {
            addCriterion("IS_ALLOW_CUSTOM like", value, "isAllowCustom");
            return (Criteria) this;
        }

        public Criteria andIsAllowCustomNotLike(String value) {
            addCriterion("IS_ALLOW_CUSTOM not like", value, "isAllowCustom");
            return (Criteria) this;
        }

        public Criteria andIsAllowCustomIn(List<String> values) {
            addCriterion("IS_ALLOW_CUSTOM in", values, "isAllowCustom");
            return (Criteria) this;
        }

        public Criteria andIsAllowCustomNotIn(List<String> values) {
            addCriterion("IS_ALLOW_CUSTOM not in", values, "isAllowCustom");
            return (Criteria) this;
        }

        public Criteria andIsAllowCustomBetween(String value1, String value2) {
            addCriterion("IS_ALLOW_CUSTOM between", value1, value2, "isAllowCustom");
            return (Criteria) this;
        }

        public Criteria andIsAllowCustomNotBetween(String value1, String value2) {
            addCriterion("IS_ALLOW_CUSTOM not between", value1, value2, "isAllowCustom");
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