package com.ai.slp.product.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProdAttrvalueDefCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public ProdAttrvalueDefCriteria() {
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

        public Criteria andAttrvalueDefIdIsNull() {
            addCriterion("ATTRVALUE_DEF_ID is null");
            return (Criteria) this;
        }

        public Criteria andAttrvalueDefIdIsNotNull() {
            addCriterion("ATTRVALUE_DEF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAttrvalueDefIdEqualTo(String value) {
            addCriterion("ATTRVALUE_DEF_ID =", value, "attrvalueDefId");
            return (Criteria) this;
        }

        public Criteria andAttrvalueDefIdNotEqualTo(String value) {
            addCriterion("ATTRVALUE_DEF_ID <>", value, "attrvalueDefId");
            return (Criteria) this;
        }

        public Criteria andAttrvalueDefIdGreaterThan(String value) {
            addCriterion("ATTRVALUE_DEF_ID >", value, "attrvalueDefId");
            return (Criteria) this;
        }

        public Criteria andAttrvalueDefIdGreaterThanOrEqualTo(String value) {
            addCriterion("ATTRVALUE_DEF_ID >=", value, "attrvalueDefId");
            return (Criteria) this;
        }

        public Criteria andAttrvalueDefIdLessThan(String value) {
            addCriterion("ATTRVALUE_DEF_ID <", value, "attrvalueDefId");
            return (Criteria) this;
        }

        public Criteria andAttrvalueDefIdLessThanOrEqualTo(String value) {
            addCriterion("ATTRVALUE_DEF_ID <=", value, "attrvalueDefId");
            return (Criteria) this;
        }

        public Criteria andAttrvalueDefIdLike(String value) {
            addCriterion("ATTRVALUE_DEF_ID like", value, "attrvalueDefId");
            return (Criteria) this;
        }

        public Criteria andAttrvalueDefIdNotLike(String value) {
            addCriterion("ATTRVALUE_DEF_ID not like", value, "attrvalueDefId");
            return (Criteria) this;
        }

        public Criteria andAttrvalueDefIdIn(List<String> values) {
            addCriterion("ATTRVALUE_DEF_ID in", values, "attrvalueDefId");
            return (Criteria) this;
        }

        public Criteria andAttrvalueDefIdNotIn(List<String> values) {
            addCriterion("ATTRVALUE_DEF_ID not in", values, "attrvalueDefId");
            return (Criteria) this;
        }

        public Criteria andAttrvalueDefIdBetween(String value1, String value2) {
            addCriterion("ATTRVALUE_DEF_ID between", value1, value2, "attrvalueDefId");
            return (Criteria) this;
        }

        public Criteria andAttrvalueDefIdNotBetween(String value1, String value2) {
            addCriterion("ATTRVALUE_DEF_ID not between", value1, value2, "attrvalueDefId");
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

        public Criteria andAttrValueIdIsNull() {
            addCriterion("ATTR_VALUE_ID is null");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdIsNotNull() {
            addCriterion("ATTR_VALUE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdEqualTo(String value) {
            addCriterion("ATTR_VALUE_ID =", value, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdNotEqualTo(String value) {
            addCriterion("ATTR_VALUE_ID <>", value, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdGreaterThan(String value) {
            addCriterion("ATTR_VALUE_ID >", value, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdGreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_VALUE_ID >=", value, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdLessThan(String value) {
            addCriterion("ATTR_VALUE_ID <", value, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdLessThanOrEqualTo(String value) {
            addCriterion("ATTR_VALUE_ID <=", value, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdLike(String value) {
            addCriterion("ATTR_VALUE_ID like", value, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdNotLike(String value) {
            addCriterion("ATTR_VALUE_ID not like", value, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdIn(List<String> values) {
            addCriterion("ATTR_VALUE_ID in", values, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdNotIn(List<String> values) {
            addCriterion("ATTR_VALUE_ID not in", values, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdBetween(String value1, String value2) {
            addCriterion("ATTR_VALUE_ID between", value1, value2, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueIdNotBetween(String value1, String value2) {
            addCriterion("ATTR_VALUE_ID not between", value1, value2, "attrValueId");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameIsNull() {
            addCriterion("ATTR_VALUE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameIsNotNull() {
            addCriterion("ATTR_VALUE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameEqualTo(String value) {
            addCriterion("ATTR_VALUE_NAME =", value, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameNotEqualTo(String value) {
            addCriterion("ATTR_VALUE_NAME <>", value, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameGreaterThan(String value) {
            addCriterion("ATTR_VALUE_NAME >", value, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameGreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_VALUE_NAME >=", value, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameLessThan(String value) {
            addCriterion("ATTR_VALUE_NAME <", value, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameLessThanOrEqualTo(String value) {
            addCriterion("ATTR_VALUE_NAME <=", value, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameLike(String value) {
            addCriterion("ATTR_VALUE_NAME like", value, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameNotLike(String value) {
            addCriterion("ATTR_VALUE_NAME not like", value, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameIn(List<String> values) {
            addCriterion("ATTR_VALUE_NAME in", values, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameNotIn(List<String> values) {
            addCriterion("ATTR_VALUE_NAME not in", values, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameBetween(String value1, String value2) {
            addCriterion("ATTR_VALUE_NAME between", value1, value2, "attrValueName");
            return (Criteria) this;
        }

        public Criteria andAttrValueNameNotBetween(String value1, String value2) {
            addCriterion("ATTR_VALUE_NAME not between", value1, value2, "attrValueName");
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

        public Criteria andSerialNumberIsNull() {
            addCriterion("SERIAL_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andSerialNumberIsNotNull() {
            addCriterion("SERIAL_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andSerialNumberEqualTo(Short value) {
            addCriterion("SERIAL_NUMBER =", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotEqualTo(Short value) {
            addCriterion("SERIAL_NUMBER <>", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberGreaterThan(Short value) {
            addCriterion("SERIAL_NUMBER >", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberGreaterThanOrEqualTo(Short value) {
            addCriterion("SERIAL_NUMBER >=", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberLessThan(Short value) {
            addCriterion("SERIAL_NUMBER <", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberLessThanOrEqualTo(Short value) {
            addCriterion("SERIAL_NUMBER <=", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberIn(List<Short> values) {
            addCriterion("SERIAL_NUMBER in", values, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotIn(List<Short> values) {
            addCriterion("SERIAL_NUMBER not in", values, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberBetween(Short value1, Short value2) {
            addCriterion("SERIAL_NUMBER between", value1, value2, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotBetween(Short value1, Short value2) {
            addCriterion("SERIAL_NUMBER not between", value1, value2, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
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