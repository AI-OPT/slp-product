package com.ai.slp.product.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProdAttrGroupCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public ProdAttrGroupCriteria() {
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

        public Criteria andAttrGroupIdIsNull() {
            addCriterion("ATTR_GROUP_ID is null");
            return (Criteria) this;
        }

        public Criteria andAttrGroupIdIsNotNull() {
            addCriterion("ATTR_GROUP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAttrGroupIdEqualTo(Integer value) {
            addCriterion("ATTR_GROUP_ID =", value, "attrGroupId");
            return (Criteria) this;
        }

        public Criteria andAttrGroupIdNotEqualTo(Integer value) {
            addCriterion("ATTR_GROUP_ID <>", value, "attrGroupId");
            return (Criteria) this;
        }

        public Criteria andAttrGroupIdGreaterThan(Integer value) {
            addCriterion("ATTR_GROUP_ID >", value, "attrGroupId");
            return (Criteria) this;
        }

        public Criteria andAttrGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ATTR_GROUP_ID >=", value, "attrGroupId");
            return (Criteria) this;
        }

        public Criteria andAttrGroupIdLessThan(Integer value) {
            addCriterion("ATTR_GROUP_ID <", value, "attrGroupId");
            return (Criteria) this;
        }

        public Criteria andAttrGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("ATTR_GROUP_ID <=", value, "attrGroupId");
            return (Criteria) this;
        }

        public Criteria andAttrGroupIdIn(List<Integer> values) {
            addCriterion("ATTR_GROUP_ID in", values, "attrGroupId");
            return (Criteria) this;
        }

        public Criteria andAttrGroupIdNotIn(List<Integer> values) {
            addCriterion("ATTR_GROUP_ID not in", values, "attrGroupId");
            return (Criteria) this;
        }

        public Criteria andAttrGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("ATTR_GROUP_ID between", value1, value2, "attrGroupId");
            return (Criteria) this;
        }

        public Criteria andAttrGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ATTR_GROUP_ID not between", value1, value2, "attrGroupId");
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

        public Criteria andAttrGroupNameIsNull() {
            addCriterion("ATTR_GROUP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAttrGroupNameIsNotNull() {
            addCriterion("ATTR_GROUP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAttrGroupNameEqualTo(String value) {
            addCriterion("ATTR_GROUP_NAME =", value, "attrGroupName");
            return (Criteria) this;
        }

        public Criteria andAttrGroupNameNotEqualTo(String value) {
            addCriterion("ATTR_GROUP_NAME <>", value, "attrGroupName");
            return (Criteria) this;
        }

        public Criteria andAttrGroupNameGreaterThan(String value) {
            addCriterion("ATTR_GROUP_NAME >", value, "attrGroupName");
            return (Criteria) this;
        }

        public Criteria andAttrGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_GROUP_NAME >=", value, "attrGroupName");
            return (Criteria) this;
        }

        public Criteria andAttrGroupNameLessThan(String value) {
            addCriterion("ATTR_GROUP_NAME <", value, "attrGroupName");
            return (Criteria) this;
        }

        public Criteria andAttrGroupNameLessThanOrEqualTo(String value) {
            addCriterion("ATTR_GROUP_NAME <=", value, "attrGroupName");
            return (Criteria) this;
        }

        public Criteria andAttrGroupNameLike(String value) {
            addCriterion("ATTR_GROUP_NAME like", value, "attrGroupName");
            return (Criteria) this;
        }

        public Criteria andAttrGroupNameNotLike(String value) {
            addCriterion("ATTR_GROUP_NAME not like", value, "attrGroupName");
            return (Criteria) this;
        }

        public Criteria andAttrGroupNameIn(List<String> values) {
            addCriterion("ATTR_GROUP_NAME in", values, "attrGroupName");
            return (Criteria) this;
        }

        public Criteria andAttrGroupNameNotIn(List<String> values) {
            addCriterion("ATTR_GROUP_NAME not in", values, "attrGroupName");
            return (Criteria) this;
        }

        public Criteria andAttrGroupNameBetween(String value1, String value2) {
            addCriterion("ATTR_GROUP_NAME between", value1, value2, "attrGroupName");
            return (Criteria) this;
        }

        public Criteria andAttrGroupNameNotBetween(String value1, String value2) {
            addCriterion("ATTR_GROUP_NAME not between", value1, value2, "attrGroupName");
            return (Criteria) this;
        }

        public Criteria andLogoUrlIsNull() {
            addCriterion("LOGO_URL is null");
            return (Criteria) this;
        }

        public Criteria andLogoUrlIsNotNull() {
            addCriterion("LOGO_URL is not null");
            return (Criteria) this;
        }

        public Criteria andLogoUrlEqualTo(String value) {
            addCriterion("LOGO_URL =", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlNotEqualTo(String value) {
            addCriterion("LOGO_URL <>", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlGreaterThan(String value) {
            addCriterion("LOGO_URL >", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlGreaterThanOrEqualTo(String value) {
            addCriterion("LOGO_URL >=", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlLessThan(String value) {
            addCriterion("LOGO_URL <", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlLessThanOrEqualTo(String value) {
            addCriterion("LOGO_URL <=", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlLike(String value) {
            addCriterion("LOGO_URL like", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlNotLike(String value) {
            addCriterion("LOGO_URL not like", value, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlIn(List<String> values) {
            addCriterion("LOGO_URL in", values, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlNotIn(List<String> values) {
            addCriterion("LOGO_URL not in", values, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlBetween(String value1, String value2) {
            addCriterion("LOGO_URL between", value1, value2, "logoUrl");
            return (Criteria) this;
        }

        public Criteria andLogoUrlNotBetween(String value1, String value2) {
            addCriterion("LOGO_URL not between", value1, value2, "logoUrl");
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