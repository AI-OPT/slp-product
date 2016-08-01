package com.ai.slp.product.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class StandedProductLogCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public StandedProductLogCriteria() {
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

        public Criteria andStandedProdIdIsNull() {
            addCriterion("STANDED_PROD_ID is null");
            return (Criteria) this;
        }

        public Criteria andStandedProdIdIsNotNull() {
            addCriterion("STANDED_PROD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStandedProdIdEqualTo(String value) {
            addCriterion("STANDED_PROD_ID =", value, "standedProdId");
            return (Criteria) this;
        }

        public Criteria andStandedProdIdNotEqualTo(String value) {
            addCriterion("STANDED_PROD_ID <>", value, "standedProdId");
            return (Criteria) this;
        }

        public Criteria andStandedProdIdGreaterThan(String value) {
            addCriterion("STANDED_PROD_ID >", value, "standedProdId");
            return (Criteria) this;
        }

        public Criteria andStandedProdIdGreaterThanOrEqualTo(String value) {
            addCriterion("STANDED_PROD_ID >=", value, "standedProdId");
            return (Criteria) this;
        }

        public Criteria andStandedProdIdLessThan(String value) {
            addCriterion("STANDED_PROD_ID <", value, "standedProdId");
            return (Criteria) this;
        }

        public Criteria andStandedProdIdLessThanOrEqualTo(String value) {
            addCriterion("STANDED_PROD_ID <=", value, "standedProdId");
            return (Criteria) this;
        }

        public Criteria andStandedProdIdLike(String value) {
            addCriterion("STANDED_PROD_ID like", value, "standedProdId");
            return (Criteria) this;
        }

        public Criteria andStandedProdIdNotLike(String value) {
            addCriterion("STANDED_PROD_ID not like", value, "standedProdId");
            return (Criteria) this;
        }

        public Criteria andStandedProdIdIn(List<String> values) {
            addCriterion("STANDED_PROD_ID in", values, "standedProdId");
            return (Criteria) this;
        }

        public Criteria andStandedProdIdNotIn(List<String> values) {
            addCriterion("STANDED_PROD_ID not in", values, "standedProdId");
            return (Criteria) this;
        }

        public Criteria andStandedProdIdBetween(String value1, String value2) {
            addCriterion("STANDED_PROD_ID between", value1, value2, "standedProdId");
            return (Criteria) this;
        }

        public Criteria andStandedProdIdNotBetween(String value1, String value2) {
            addCriterion("STANDED_PROD_ID not between", value1, value2, "standedProdId");
            return (Criteria) this;
        }

        public Criteria andStandedProductNameIsNull() {
            addCriterion("STANDED_PRODUCT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStandedProductNameIsNotNull() {
            addCriterion("STANDED_PRODUCT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStandedProductNameEqualTo(String value) {
            addCriterion("STANDED_PRODUCT_NAME =", value, "standedProductName");
            return (Criteria) this;
        }

        public Criteria andStandedProductNameNotEqualTo(String value) {
            addCriterion("STANDED_PRODUCT_NAME <>", value, "standedProductName");
            return (Criteria) this;
        }

        public Criteria andStandedProductNameGreaterThan(String value) {
            addCriterion("STANDED_PRODUCT_NAME >", value, "standedProductName");
            return (Criteria) this;
        }

        public Criteria andStandedProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("STANDED_PRODUCT_NAME >=", value, "standedProductName");
            return (Criteria) this;
        }

        public Criteria andStandedProductNameLessThan(String value) {
            addCriterion("STANDED_PRODUCT_NAME <", value, "standedProductName");
            return (Criteria) this;
        }

        public Criteria andStandedProductNameLessThanOrEqualTo(String value) {
            addCriterion("STANDED_PRODUCT_NAME <=", value, "standedProductName");
            return (Criteria) this;
        }

        public Criteria andStandedProductNameLike(String value) {
            addCriterion("STANDED_PRODUCT_NAME like", value, "standedProductName");
            return (Criteria) this;
        }

        public Criteria andStandedProductNameNotLike(String value) {
            addCriterion("STANDED_PRODUCT_NAME not like", value, "standedProductName");
            return (Criteria) this;
        }

        public Criteria andStandedProductNameIn(List<String> values) {
            addCriterion("STANDED_PRODUCT_NAME in", values, "standedProductName");
            return (Criteria) this;
        }

        public Criteria andStandedProductNameNotIn(List<String> values) {
            addCriterion("STANDED_PRODUCT_NAME not in", values, "standedProductName");
            return (Criteria) this;
        }

        public Criteria andStandedProductNameBetween(String value1, String value2) {
            addCriterion("STANDED_PRODUCT_NAME between", value1, value2, "standedProductName");
            return (Criteria) this;
        }

        public Criteria andStandedProductNameNotBetween(String value1, String value2) {
            addCriterion("STANDED_PRODUCT_NAME not between", value1, value2, "standedProductName");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNull() {
            addCriterion("PRODUCT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNotNull() {
            addCriterion("PRODUCT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andProductTypeEqualTo(String value) {
            addCriterion("PRODUCT_TYPE =", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotEqualTo(String value) {
            addCriterion("PRODUCT_TYPE <>", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThan(String value) {
            addCriterion("PRODUCT_TYPE >", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_TYPE >=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThan(String value) {
            addCriterion("PRODUCT_TYPE <", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_TYPE <=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLike(String value) {
            addCriterion("PRODUCT_TYPE like", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotLike(String value) {
            addCriterion("PRODUCT_TYPE not like", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeIn(List<String> values) {
            addCriterion("PRODUCT_TYPE in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotIn(List<String> values) {
            addCriterion("PRODUCT_TYPE not in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeBetween(String value1, String value2) {
            addCriterion("PRODUCT_TYPE between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_TYPE not between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andMarketPriceIsNull() {
            addCriterion("MARKET_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andMarketPriceIsNotNull() {
            addCriterion("MARKET_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andMarketPriceEqualTo(Long value) {
            addCriterion("MARKET_PRICE =", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotEqualTo(Long value) {
            addCriterion("MARKET_PRICE <>", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceGreaterThan(Long value) {
            addCriterion("MARKET_PRICE >", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("MARKET_PRICE >=", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceLessThan(Long value) {
            addCriterion("MARKET_PRICE <", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceLessThanOrEqualTo(Long value) {
            addCriterion("MARKET_PRICE <=", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceIn(List<Long> values) {
            addCriterion("MARKET_PRICE in", values, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotIn(List<Long> values) {
            addCriterion("MARKET_PRICE not in", values, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceBetween(Long value1, Long value2) {
            addCriterion("MARKET_PRICE between", value1, value2, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotBetween(Long value1, Long value2) {
            addCriterion("MARKET_PRICE not between", value1, value2, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andActiveTypeIsNull() {
            addCriterion("ACTIVE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andActiveTypeIsNotNull() {
            addCriterion("ACTIVE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andActiveTypeEqualTo(String value) {
            addCriterion("ACTIVE_TYPE =", value, "activeType");
            return (Criteria) this;
        }

        public Criteria andActiveTypeNotEqualTo(String value) {
            addCriterion("ACTIVE_TYPE <>", value, "activeType");
            return (Criteria) this;
        }

        public Criteria andActiveTypeGreaterThan(String value) {
            addCriterion("ACTIVE_TYPE >", value, "activeType");
            return (Criteria) this;
        }

        public Criteria andActiveTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ACTIVE_TYPE >=", value, "activeType");
            return (Criteria) this;
        }

        public Criteria andActiveTypeLessThan(String value) {
            addCriterion("ACTIVE_TYPE <", value, "activeType");
            return (Criteria) this;
        }

        public Criteria andActiveTypeLessThanOrEqualTo(String value) {
            addCriterion("ACTIVE_TYPE <=", value, "activeType");
            return (Criteria) this;
        }

        public Criteria andActiveTypeLike(String value) {
            addCriterion("ACTIVE_TYPE like", value, "activeType");
            return (Criteria) this;
        }

        public Criteria andActiveTypeNotLike(String value) {
            addCriterion("ACTIVE_TYPE not like", value, "activeType");
            return (Criteria) this;
        }

        public Criteria andActiveTypeIn(List<String> values) {
            addCriterion("ACTIVE_TYPE in", values, "activeType");
            return (Criteria) this;
        }

        public Criteria andActiveTypeNotIn(List<String> values) {
            addCriterion("ACTIVE_TYPE not in", values, "activeType");
            return (Criteria) this;
        }

        public Criteria andActiveTypeBetween(String value1, String value2) {
            addCriterion("ACTIVE_TYPE between", value1, value2, "activeType");
            return (Criteria) this;
        }

        public Criteria andActiveTypeNotBetween(String value1, String value2) {
            addCriterion("ACTIVE_TYPE not between", value1, value2, "activeType");
            return (Criteria) this;
        }

        public Criteria andActiveTimeIsNull() {
            addCriterion("ACTIVE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andActiveTimeIsNotNull() {
            addCriterion("ACTIVE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andActiveTimeEqualTo(Timestamp value) {
            addCriterion("ACTIVE_TIME =", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeNotEqualTo(Timestamp value) {
            addCriterion("ACTIVE_TIME <>", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeGreaterThan(Timestamp value) {
            addCriterion("ACTIVE_TIME >", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("ACTIVE_TIME >=", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeLessThan(Timestamp value) {
            addCriterion("ACTIVE_TIME <", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("ACTIVE_TIME <=", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeIn(List<Timestamp> values) {
            addCriterion("ACTIVE_TIME in", values, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeNotIn(List<Timestamp> values) {
            addCriterion("ACTIVE_TIME not in", values, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("ACTIVE_TIME between", value1, value2, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("ACTIVE_TIME not between", value1, value2, "activeTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeIsNull() {
            addCriterion("INACTIVE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeIsNotNull() {
            addCriterion("INACTIVE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeEqualTo(Timestamp value) {
            addCriterion("INACTIVE_TIME =", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeNotEqualTo(Timestamp value) {
            addCriterion("INACTIVE_TIME <>", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeGreaterThan(Timestamp value) {
            addCriterion("INACTIVE_TIME >", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("INACTIVE_TIME >=", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeLessThan(Timestamp value) {
            addCriterion("INACTIVE_TIME <", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("INACTIVE_TIME <=", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeIn(List<Timestamp> values) {
            addCriterion("INACTIVE_TIME in", values, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeNotIn(List<Timestamp> values) {
            addCriterion("INACTIVE_TIME not in", values, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("INACTIVE_TIME between", value1, value2, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("INACTIVE_TIME not between", value1, value2, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andActiveCycleIsNull() {
            addCriterion("ACTIVE_CYCLE is null");
            return (Criteria) this;
        }

        public Criteria andActiveCycleIsNotNull() {
            addCriterion("ACTIVE_CYCLE is not null");
            return (Criteria) this;
        }

        public Criteria andActiveCycleEqualTo(Short value) {
            addCriterion("ACTIVE_CYCLE =", value, "activeCycle");
            return (Criteria) this;
        }

        public Criteria andActiveCycleNotEqualTo(Short value) {
            addCriterion("ACTIVE_CYCLE <>", value, "activeCycle");
            return (Criteria) this;
        }

        public Criteria andActiveCycleGreaterThan(Short value) {
            addCriterion("ACTIVE_CYCLE >", value, "activeCycle");
            return (Criteria) this;
        }

        public Criteria andActiveCycleGreaterThanOrEqualTo(Short value) {
            addCriterion("ACTIVE_CYCLE >=", value, "activeCycle");
            return (Criteria) this;
        }

        public Criteria andActiveCycleLessThan(Short value) {
            addCriterion("ACTIVE_CYCLE <", value, "activeCycle");
            return (Criteria) this;
        }

        public Criteria andActiveCycleLessThanOrEqualTo(Short value) {
            addCriterion("ACTIVE_CYCLE <=", value, "activeCycle");
            return (Criteria) this;
        }

        public Criteria andActiveCycleIn(List<Short> values) {
            addCriterion("ACTIVE_CYCLE in", values, "activeCycle");
            return (Criteria) this;
        }

        public Criteria andActiveCycleNotIn(List<Short> values) {
            addCriterion("ACTIVE_CYCLE not in", values, "activeCycle");
            return (Criteria) this;
        }

        public Criteria andActiveCycleBetween(Short value1, Short value2) {
            addCriterion("ACTIVE_CYCLE between", value1, value2, "activeCycle");
            return (Criteria) this;
        }

        public Criteria andActiveCycleNotBetween(Short value1, Short value2) {
            addCriterion("ACTIVE_CYCLE not between", value1, value2, "activeCycle");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("UNIT is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("UNIT =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("UNIT <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("UNIT >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("UNIT >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("UNIT <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("UNIT <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("UNIT like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("UNIT not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("UNIT in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("UNIT not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("UNIT between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("UNIT not between", value1, value2, "unit");
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

        public Criteria andCreateIdIsNull() {
            addCriterion("CREATE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCreateIdIsNotNull() {
            addCriterion("CREATE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCreateIdEqualTo(Long value) {
            addCriterion("CREATE_ID =", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotEqualTo(Long value) {
            addCriterion("CREATE_ID <>", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdGreaterThan(Long value) {
            addCriterion("CREATE_ID >", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CREATE_ID >=", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdLessThan(Long value) {
            addCriterion("CREATE_ID <", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdLessThanOrEqualTo(Long value) {
            addCriterion("CREATE_ID <=", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdIn(List<Long> values) {
            addCriterion("CREATE_ID in", values, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotIn(List<Long> values) {
            addCriterion("CREATE_ID not in", values, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdBetween(Long value1, Long value2) {
            addCriterion("CREATE_ID between", value1, value2, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotBetween(Long value1, Long value2) {
            addCriterion("CREATE_ID not between", value1, value2, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Timestamp value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Timestamp value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Timestamp> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Timestamp> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
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

        public Criteria andSupplierIdIsNull() {
            addCriterion("SUPPLIER_ID is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNotNull() {
            addCriterion("SUPPLIER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdEqualTo(String value) {
            addCriterion("SUPPLIER_ID =", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotEqualTo(String value) {
            addCriterion("SUPPLIER_ID <>", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThan(String value) {
            addCriterion("SUPPLIER_ID >", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThanOrEqualTo(String value) {
            addCriterion("SUPPLIER_ID >=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThan(String value) {
            addCriterion("SUPPLIER_ID <", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThanOrEqualTo(String value) {
            addCriterion("SUPPLIER_ID <=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLike(String value) {
            addCriterion("SUPPLIER_ID like", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotLike(String value) {
            addCriterion("SUPPLIER_ID not like", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIn(List<String> values) {
            addCriterion("SUPPLIER_ID in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotIn(List<String> values) {
            addCriterion("SUPPLIER_ID not in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdBetween(String value1, String value2) {
            addCriterion("SUPPLIER_ID between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotBetween(String value1, String value2) {
            addCriterion("SUPPLIER_ID not between", value1, value2, "supplierId");
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