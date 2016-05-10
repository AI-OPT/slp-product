package com.ai.slp.product.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProductCatCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public ProductCatCriteria() {
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

        public Criteria andProductCatIdIsNull() {
            addCriterion("PRODUCT_CAT_ID is null");
            return (Criteria) this;
        }

        public Criteria andProductCatIdIsNotNull() {
            addCriterion("PRODUCT_CAT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProductCatIdEqualTo(String value) {
            addCriterion("PRODUCT_CAT_ID =", value, "productCatId");
            return (Criteria) this;
        }

        public Criteria andProductCatIdNotEqualTo(String value) {
            addCriterion("PRODUCT_CAT_ID <>", value, "productCatId");
            return (Criteria) this;
        }

        public Criteria andProductCatIdGreaterThan(String value) {
            addCriterion("PRODUCT_CAT_ID >", value, "productCatId");
            return (Criteria) this;
        }

        public Criteria andProductCatIdGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_CAT_ID >=", value, "productCatId");
            return (Criteria) this;
        }

        public Criteria andProductCatIdLessThan(String value) {
            addCriterion("PRODUCT_CAT_ID <", value, "productCatId");
            return (Criteria) this;
        }

        public Criteria andProductCatIdLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_CAT_ID <=", value, "productCatId");
            return (Criteria) this;
        }

        public Criteria andProductCatIdLike(String value) {
            addCriterion("PRODUCT_CAT_ID like", value, "productCatId");
            return (Criteria) this;
        }

        public Criteria andProductCatIdNotLike(String value) {
            addCriterion("PRODUCT_CAT_ID not like", value, "productCatId");
            return (Criteria) this;
        }

        public Criteria andProductCatIdIn(List<String> values) {
            addCriterion("PRODUCT_CAT_ID in", values, "productCatId");
            return (Criteria) this;
        }

        public Criteria andProductCatIdNotIn(List<String> values) {
            addCriterion("PRODUCT_CAT_ID not in", values, "productCatId");
            return (Criteria) this;
        }

        public Criteria andProductCatIdBetween(String value1, String value2) {
            addCriterion("PRODUCT_CAT_ID between", value1, value2, "productCatId");
            return (Criteria) this;
        }

        public Criteria andProductCatIdNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_CAT_ID not between", value1, value2, "productCatId");
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

        public Criteria andProductCatNameIsNull() {
            addCriterion("PRODUCT_CAT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProductCatNameIsNotNull() {
            addCriterion("PRODUCT_CAT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProductCatNameEqualTo(String value) {
            addCriterion("PRODUCT_CAT_NAME =", value, "productCatName");
            return (Criteria) this;
        }

        public Criteria andProductCatNameNotEqualTo(String value) {
            addCriterion("PRODUCT_CAT_NAME <>", value, "productCatName");
            return (Criteria) this;
        }

        public Criteria andProductCatNameGreaterThan(String value) {
            addCriterion("PRODUCT_CAT_NAME >", value, "productCatName");
            return (Criteria) this;
        }

        public Criteria andProductCatNameGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_CAT_NAME >=", value, "productCatName");
            return (Criteria) this;
        }

        public Criteria andProductCatNameLessThan(String value) {
            addCriterion("PRODUCT_CAT_NAME <", value, "productCatName");
            return (Criteria) this;
        }

        public Criteria andProductCatNameLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_CAT_NAME <=", value, "productCatName");
            return (Criteria) this;
        }

        public Criteria andProductCatNameLike(String value) {
            addCriterion("PRODUCT_CAT_NAME like", value, "productCatName");
            return (Criteria) this;
        }

        public Criteria andProductCatNameNotLike(String value) {
            addCriterion("PRODUCT_CAT_NAME not like", value, "productCatName");
            return (Criteria) this;
        }

        public Criteria andProductCatNameIn(List<String> values) {
            addCriterion("PRODUCT_CAT_NAME in", values, "productCatName");
            return (Criteria) this;
        }

        public Criteria andProductCatNameNotIn(List<String> values) {
            addCriterion("PRODUCT_CAT_NAME not in", values, "productCatName");
            return (Criteria) this;
        }

        public Criteria andProductCatNameBetween(String value1, String value2) {
            addCriterion("PRODUCT_CAT_NAME between", value1, value2, "productCatName");
            return (Criteria) this;
        }

        public Criteria andProductCatNameNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_CAT_NAME not between", value1, value2, "productCatName");
            return (Criteria) this;
        }

        public Criteria andParentProductCatIdIsNull() {
            addCriterion("PARENT_PRODUCT_CAT_ID is null");
            return (Criteria) this;
        }

        public Criteria andParentProductCatIdIsNotNull() {
            addCriterion("PARENT_PRODUCT_CAT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParentProductCatIdEqualTo(String value) {
            addCriterion("PARENT_PRODUCT_CAT_ID =", value, "parentProductCatId");
            return (Criteria) this;
        }

        public Criteria andParentProductCatIdNotEqualTo(String value) {
            addCriterion("PARENT_PRODUCT_CAT_ID <>", value, "parentProductCatId");
            return (Criteria) this;
        }

        public Criteria andParentProductCatIdGreaterThan(String value) {
            addCriterion("PARENT_PRODUCT_CAT_ID >", value, "parentProductCatId");
            return (Criteria) this;
        }

        public Criteria andParentProductCatIdGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_PRODUCT_CAT_ID >=", value, "parentProductCatId");
            return (Criteria) this;
        }

        public Criteria andParentProductCatIdLessThan(String value) {
            addCriterion("PARENT_PRODUCT_CAT_ID <", value, "parentProductCatId");
            return (Criteria) this;
        }

        public Criteria andParentProductCatIdLessThanOrEqualTo(String value) {
            addCriterion("PARENT_PRODUCT_CAT_ID <=", value, "parentProductCatId");
            return (Criteria) this;
        }

        public Criteria andParentProductCatIdLike(String value) {
            addCriterion("PARENT_PRODUCT_CAT_ID like", value, "parentProductCatId");
            return (Criteria) this;
        }

        public Criteria andParentProductCatIdNotLike(String value) {
            addCriterion("PARENT_PRODUCT_CAT_ID not like", value, "parentProductCatId");
            return (Criteria) this;
        }

        public Criteria andParentProductCatIdIn(List<String> values) {
            addCriterion("PARENT_PRODUCT_CAT_ID in", values, "parentProductCatId");
            return (Criteria) this;
        }

        public Criteria andParentProductCatIdNotIn(List<String> values) {
            addCriterion("PARENT_PRODUCT_CAT_ID not in", values, "parentProductCatId");
            return (Criteria) this;
        }

        public Criteria andParentProductCatIdBetween(String value1, String value2) {
            addCriterion("PARENT_PRODUCT_CAT_ID between", value1, value2, "parentProductCatId");
            return (Criteria) this;
        }

        public Criteria andParentProductCatIdNotBetween(String value1, String value2) {
            addCriterion("PARENT_PRODUCT_CAT_ID not between", value1, value2, "parentProductCatId");
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

        public Criteria andCatLevelIsNull() {
            addCriterion("CAT_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andCatLevelIsNotNull() {
            addCriterion("CAT_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andCatLevelEqualTo(Short value) {
            addCriterion("CAT_LEVEL =", value, "catLevel");
            return (Criteria) this;
        }

        public Criteria andCatLevelNotEqualTo(Short value) {
            addCriterion("CAT_LEVEL <>", value, "catLevel");
            return (Criteria) this;
        }

        public Criteria andCatLevelGreaterThan(Short value) {
            addCriterion("CAT_LEVEL >", value, "catLevel");
            return (Criteria) this;
        }

        public Criteria andCatLevelGreaterThanOrEqualTo(Short value) {
            addCriterion("CAT_LEVEL >=", value, "catLevel");
            return (Criteria) this;
        }

        public Criteria andCatLevelLessThan(Short value) {
            addCriterion("CAT_LEVEL <", value, "catLevel");
            return (Criteria) this;
        }

        public Criteria andCatLevelLessThanOrEqualTo(Short value) {
            addCriterion("CAT_LEVEL <=", value, "catLevel");
            return (Criteria) this;
        }

        public Criteria andCatLevelIn(List<Short> values) {
            addCriterion("CAT_LEVEL in", values, "catLevel");
            return (Criteria) this;
        }

        public Criteria andCatLevelNotIn(List<Short> values) {
            addCriterion("CAT_LEVEL not in", values, "catLevel");
            return (Criteria) this;
        }

        public Criteria andCatLevelBetween(Short value1, Short value2) {
            addCriterion("CAT_LEVEL between", value1, value2, "catLevel");
            return (Criteria) this;
        }

        public Criteria andCatLevelNotBetween(Short value1, Short value2) {
            addCriterion("CAT_LEVEL not between", value1, value2, "catLevel");
            return (Criteria) this;
        }

        public Criteria andIsChildIsNull() {
            addCriterion("IS_CHILD is null");
            return (Criteria) this;
        }

        public Criteria andIsChildIsNotNull() {
            addCriterion("IS_CHILD is not null");
            return (Criteria) this;
        }

        public Criteria andIsChildEqualTo(String value) {
            addCriterion("IS_CHILD =", value, "isChild");
            return (Criteria) this;
        }

        public Criteria andIsChildNotEqualTo(String value) {
            addCriterion("IS_CHILD <>", value, "isChild");
            return (Criteria) this;
        }

        public Criteria andIsChildGreaterThan(String value) {
            addCriterion("IS_CHILD >", value, "isChild");
            return (Criteria) this;
        }

        public Criteria andIsChildGreaterThanOrEqualTo(String value) {
            addCriterion("IS_CHILD >=", value, "isChild");
            return (Criteria) this;
        }

        public Criteria andIsChildLessThan(String value) {
            addCriterion("IS_CHILD <", value, "isChild");
            return (Criteria) this;
        }

        public Criteria andIsChildLessThanOrEqualTo(String value) {
            addCriterion("IS_CHILD <=", value, "isChild");
            return (Criteria) this;
        }

        public Criteria andIsChildLike(String value) {
            addCriterion("IS_CHILD like", value, "isChild");
            return (Criteria) this;
        }

        public Criteria andIsChildNotLike(String value) {
            addCriterion("IS_CHILD not like", value, "isChild");
            return (Criteria) this;
        }

        public Criteria andIsChildIn(List<String> values) {
            addCriterion("IS_CHILD in", values, "isChild");
            return (Criteria) this;
        }

        public Criteria andIsChildNotIn(List<String> values) {
            addCriterion("IS_CHILD not in", values, "isChild");
            return (Criteria) this;
        }

        public Criteria andIsChildBetween(String value1, String value2) {
            addCriterion("IS_CHILD between", value1, value2, "isChild");
            return (Criteria) this;
        }

        public Criteria andIsChildNotBetween(String value1, String value2) {
            addCriterion("IS_CHILD not between", value1, value2, "isChild");
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