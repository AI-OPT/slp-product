package com.ai.slp.product.dao.mapper.bo.product;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProductLogCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public ProductLogCriteria() {
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

        public Criteria andStorageGroupIdIsNull() {
            addCriterion("STORAGE_GROUP_ID is null");
            return (Criteria) this;
        }

        public Criteria andStorageGroupIdIsNotNull() {
            addCriterion("STORAGE_GROUP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStorageGroupIdEqualTo(String value) {
            addCriterion("STORAGE_GROUP_ID =", value, "storageGroupId");
            return (Criteria) this;
        }

        public Criteria andStorageGroupIdNotEqualTo(String value) {
            addCriterion("STORAGE_GROUP_ID <>", value, "storageGroupId");
            return (Criteria) this;
        }

        public Criteria andStorageGroupIdGreaterThan(String value) {
            addCriterion("STORAGE_GROUP_ID >", value, "storageGroupId");
            return (Criteria) this;
        }

        public Criteria andStorageGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("STORAGE_GROUP_ID >=", value, "storageGroupId");
            return (Criteria) this;
        }

        public Criteria andStorageGroupIdLessThan(String value) {
            addCriterion("STORAGE_GROUP_ID <", value, "storageGroupId");
            return (Criteria) this;
        }

        public Criteria andStorageGroupIdLessThanOrEqualTo(String value) {
            addCriterion("STORAGE_GROUP_ID <=", value, "storageGroupId");
            return (Criteria) this;
        }

        public Criteria andStorageGroupIdLike(String value) {
            addCriterion("STORAGE_GROUP_ID like", value, "storageGroupId");
            return (Criteria) this;
        }

        public Criteria andStorageGroupIdNotLike(String value) {
            addCriterion("STORAGE_GROUP_ID not like", value, "storageGroupId");
            return (Criteria) this;
        }

        public Criteria andStorageGroupIdIn(List<String> values) {
            addCriterion("STORAGE_GROUP_ID in", values, "storageGroupId");
            return (Criteria) this;
        }

        public Criteria andStorageGroupIdNotIn(List<String> values) {
            addCriterion("STORAGE_GROUP_ID not in", values, "storageGroupId");
            return (Criteria) this;
        }

        public Criteria andStorageGroupIdBetween(String value1, String value2) {
            addCriterion("STORAGE_GROUP_ID between", value1, value2, "storageGroupId");
            return (Criteria) this;
        }

        public Criteria andStorageGroupIdNotBetween(String value1, String value2) {
            addCriterion("STORAGE_GROUP_ID not between", value1, value2, "storageGroupId");
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

        public Criteria andProdNameIsNull() {
            addCriterion("PROD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProdNameIsNotNull() {
            addCriterion("PROD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProdNameEqualTo(String value) {
            addCriterion("PROD_NAME =", value, "prodName");
            return (Criteria) this;
        }

        public Criteria andProdNameNotEqualTo(String value) {
            addCriterion("PROD_NAME <>", value, "prodName");
            return (Criteria) this;
        }

        public Criteria andProdNameGreaterThan(String value) {
            addCriterion("PROD_NAME >", value, "prodName");
            return (Criteria) this;
        }

        public Criteria andProdNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROD_NAME >=", value, "prodName");
            return (Criteria) this;
        }

        public Criteria andProdNameLessThan(String value) {
            addCriterion("PROD_NAME <", value, "prodName");
            return (Criteria) this;
        }

        public Criteria andProdNameLessThanOrEqualTo(String value) {
            addCriterion("PROD_NAME <=", value, "prodName");
            return (Criteria) this;
        }

        public Criteria andProdNameLike(String value) {
            addCriterion("PROD_NAME like", value, "prodName");
            return (Criteria) this;
        }

        public Criteria andProdNameNotLike(String value) {
            addCriterion("PROD_NAME not like", value, "prodName");
            return (Criteria) this;
        }

        public Criteria andProdNameIn(List<String> values) {
            addCriterion("PROD_NAME in", values, "prodName");
            return (Criteria) this;
        }

        public Criteria andProdNameNotIn(List<String> values) {
            addCriterion("PROD_NAME not in", values, "prodName");
            return (Criteria) this;
        }

        public Criteria andProdNameBetween(String value1, String value2) {
            addCriterion("PROD_NAME between", value1, value2, "prodName");
            return (Criteria) this;
        }

        public Criteria andProdNameNotBetween(String value1, String value2) {
            addCriterion("PROD_NAME not between", value1, value2, "prodName");
            return (Criteria) this;
        }

        public Criteria andProductSellPointIsNull() {
            addCriterion("PRODUCT_SELL_POINT is null");
            return (Criteria) this;
        }

        public Criteria andProductSellPointIsNotNull() {
            addCriterion("PRODUCT_SELL_POINT is not null");
            return (Criteria) this;
        }

        public Criteria andProductSellPointEqualTo(String value) {
            addCriterion("PRODUCT_SELL_POINT =", value, "productSellPoint");
            return (Criteria) this;
        }

        public Criteria andProductSellPointNotEqualTo(String value) {
            addCriterion("PRODUCT_SELL_POINT <>", value, "productSellPoint");
            return (Criteria) this;
        }

        public Criteria andProductSellPointGreaterThan(String value) {
            addCriterion("PRODUCT_SELL_POINT >", value, "productSellPoint");
            return (Criteria) this;
        }

        public Criteria andProductSellPointGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_SELL_POINT >=", value, "productSellPoint");
            return (Criteria) this;
        }

        public Criteria andProductSellPointLessThan(String value) {
            addCriterion("PRODUCT_SELL_POINT <", value, "productSellPoint");
            return (Criteria) this;
        }

        public Criteria andProductSellPointLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_SELL_POINT <=", value, "productSellPoint");
            return (Criteria) this;
        }

        public Criteria andProductSellPointLike(String value) {
            addCriterion("PRODUCT_SELL_POINT like", value, "productSellPoint");
            return (Criteria) this;
        }

        public Criteria andProductSellPointNotLike(String value) {
            addCriterion("PRODUCT_SELL_POINT not like", value, "productSellPoint");
            return (Criteria) this;
        }

        public Criteria andProductSellPointIn(List<String> values) {
            addCriterion("PRODUCT_SELL_POINT in", values, "productSellPoint");
            return (Criteria) this;
        }

        public Criteria andProductSellPointNotIn(List<String> values) {
            addCriterion("PRODUCT_SELL_POINT not in", values, "productSellPoint");
            return (Criteria) this;
        }

        public Criteria andProductSellPointBetween(String value1, String value2) {
            addCriterion("PRODUCT_SELL_POINT between", value1, value2, "productSellPoint");
            return (Criteria) this;
        }

        public Criteria andProductSellPointNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_SELL_POINT not between", value1, value2, "productSellPoint");
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

        public Criteria andProDetailContentIsNull() {
            addCriterion("PRO_DETAIL_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andProDetailContentIsNotNull() {
            addCriterion("PRO_DETAIL_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andProDetailContentEqualTo(String value) {
            addCriterion("PRO_DETAIL_CONTENT =", value, "proDetailContent");
            return (Criteria) this;
        }

        public Criteria andProDetailContentNotEqualTo(String value) {
            addCriterion("PRO_DETAIL_CONTENT <>", value, "proDetailContent");
            return (Criteria) this;
        }

        public Criteria andProDetailContentGreaterThan(String value) {
            addCriterion("PRO_DETAIL_CONTENT >", value, "proDetailContent");
            return (Criteria) this;
        }

        public Criteria andProDetailContentGreaterThanOrEqualTo(String value) {
            addCriterion("PRO_DETAIL_CONTENT >=", value, "proDetailContent");
            return (Criteria) this;
        }

        public Criteria andProDetailContentLessThan(String value) {
            addCriterion("PRO_DETAIL_CONTENT <", value, "proDetailContent");
            return (Criteria) this;
        }

        public Criteria andProDetailContentLessThanOrEqualTo(String value) {
            addCriterion("PRO_DETAIL_CONTENT <=", value, "proDetailContent");
            return (Criteria) this;
        }

        public Criteria andProDetailContentLike(String value) {
            addCriterion("PRO_DETAIL_CONTENT like", value, "proDetailContent");
            return (Criteria) this;
        }

        public Criteria andProDetailContentNotLike(String value) {
            addCriterion("PRO_DETAIL_CONTENT not like", value, "proDetailContent");
            return (Criteria) this;
        }

        public Criteria andProDetailContentIn(List<String> values) {
            addCriterion("PRO_DETAIL_CONTENT in", values, "proDetailContent");
            return (Criteria) this;
        }

        public Criteria andProDetailContentNotIn(List<String> values) {
            addCriterion("PRO_DETAIL_CONTENT not in", values, "proDetailContent");
            return (Criteria) this;
        }

        public Criteria andProDetailContentBetween(String value1, String value2) {
            addCriterion("PRO_DETAIL_CONTENT between", value1, value2, "proDetailContent");
            return (Criteria) this;
        }

        public Criteria andProDetailContentNotBetween(String value1, String value2) {
            addCriterion("PRO_DETAIL_CONTENT not between", value1, value2, "proDetailContent");
            return (Criteria) this;
        }

        public Criteria andIsSaleAttrIsNull() {
            addCriterion("IS_SALE_ATTR is null");
            return (Criteria) this;
        }

        public Criteria andIsSaleAttrIsNotNull() {
            addCriterion("IS_SALE_ATTR is not null");
            return (Criteria) this;
        }

        public Criteria andIsSaleAttrEqualTo(String value) {
            addCriterion("IS_SALE_ATTR =", value, "isSaleAttr");
            return (Criteria) this;
        }

        public Criteria andIsSaleAttrNotEqualTo(String value) {
            addCriterion("IS_SALE_ATTR <>", value, "isSaleAttr");
            return (Criteria) this;
        }

        public Criteria andIsSaleAttrGreaterThan(String value) {
            addCriterion("IS_SALE_ATTR >", value, "isSaleAttr");
            return (Criteria) this;
        }

        public Criteria andIsSaleAttrGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SALE_ATTR >=", value, "isSaleAttr");
            return (Criteria) this;
        }

        public Criteria andIsSaleAttrLessThan(String value) {
            addCriterion("IS_SALE_ATTR <", value, "isSaleAttr");
            return (Criteria) this;
        }

        public Criteria andIsSaleAttrLessThanOrEqualTo(String value) {
            addCriterion("IS_SALE_ATTR <=", value, "isSaleAttr");
            return (Criteria) this;
        }

        public Criteria andIsSaleAttrLike(String value) {
            addCriterion("IS_SALE_ATTR like", value, "isSaleAttr");
            return (Criteria) this;
        }

        public Criteria andIsSaleAttrNotLike(String value) {
            addCriterion("IS_SALE_ATTR not like", value, "isSaleAttr");
            return (Criteria) this;
        }

        public Criteria andIsSaleAttrIn(List<String> values) {
            addCriterion("IS_SALE_ATTR in", values, "isSaleAttr");
            return (Criteria) this;
        }

        public Criteria andIsSaleAttrNotIn(List<String> values) {
            addCriterion("IS_SALE_ATTR not in", values, "isSaleAttr");
            return (Criteria) this;
        }

        public Criteria andIsSaleAttrBetween(String value1, String value2) {
            addCriterion("IS_SALE_ATTR between", value1, value2, "isSaleAttr");
            return (Criteria) this;
        }

        public Criteria andIsSaleAttrNotBetween(String value1, String value2) {
            addCriterion("IS_SALE_ATTR not between", value1, value2, "isSaleAttr");
            return (Criteria) this;
        }

        public Criteria andIsSaleNationwideIsNull() {
            addCriterion("IS_SALE_NATIONWIDE is null");
            return (Criteria) this;
        }

        public Criteria andIsSaleNationwideIsNotNull() {
            addCriterion("IS_SALE_NATIONWIDE is not null");
            return (Criteria) this;
        }

        public Criteria andIsSaleNationwideEqualTo(String value) {
            addCriterion("IS_SALE_NATIONWIDE =", value, "isSaleNationwide");
            return (Criteria) this;
        }

        public Criteria andIsSaleNationwideNotEqualTo(String value) {
            addCriterion("IS_SALE_NATIONWIDE <>", value, "isSaleNationwide");
            return (Criteria) this;
        }

        public Criteria andIsSaleNationwideGreaterThan(String value) {
            addCriterion("IS_SALE_NATIONWIDE >", value, "isSaleNationwide");
            return (Criteria) this;
        }

        public Criteria andIsSaleNationwideGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SALE_NATIONWIDE >=", value, "isSaleNationwide");
            return (Criteria) this;
        }

        public Criteria andIsSaleNationwideLessThan(String value) {
            addCriterion("IS_SALE_NATIONWIDE <", value, "isSaleNationwide");
            return (Criteria) this;
        }

        public Criteria andIsSaleNationwideLessThanOrEqualTo(String value) {
            addCriterion("IS_SALE_NATIONWIDE <=", value, "isSaleNationwide");
            return (Criteria) this;
        }

        public Criteria andIsSaleNationwideLike(String value) {
            addCriterion("IS_SALE_NATIONWIDE like", value, "isSaleNationwide");
            return (Criteria) this;
        }

        public Criteria andIsSaleNationwideNotLike(String value) {
            addCriterion("IS_SALE_NATIONWIDE not like", value, "isSaleNationwide");
            return (Criteria) this;
        }

        public Criteria andIsSaleNationwideIn(List<String> values) {
            addCriterion("IS_SALE_NATIONWIDE in", values, "isSaleNationwide");
            return (Criteria) this;
        }

        public Criteria andIsSaleNationwideNotIn(List<String> values) {
            addCriterion("IS_SALE_NATIONWIDE not in", values, "isSaleNationwide");
            return (Criteria) this;
        }

        public Criteria andIsSaleNationwideBetween(String value1, String value2) {
            addCriterion("IS_SALE_NATIONWIDE between", value1, value2, "isSaleNationwide");
            return (Criteria) this;
        }

        public Criteria andIsSaleNationwideNotBetween(String value1, String value2) {
            addCriterion("IS_SALE_NATIONWIDE not between", value1, value2, "isSaleNationwide");
            return (Criteria) this;
        }

        public Criteria andIsReplaceSellIsNull() {
            addCriterion("IS_REPLACE_SELL is null");
            return (Criteria) this;
        }

        public Criteria andIsReplaceSellIsNotNull() {
            addCriterion("IS_REPLACE_SELL is not null");
            return (Criteria) this;
        }

        public Criteria andIsReplaceSellEqualTo(String value) {
            addCriterion("IS_REPLACE_SELL =", value, "isReplaceSell");
            return (Criteria) this;
        }

        public Criteria andIsReplaceSellNotEqualTo(String value) {
            addCriterion("IS_REPLACE_SELL <>", value, "isReplaceSell");
            return (Criteria) this;
        }

        public Criteria andIsReplaceSellGreaterThan(String value) {
            addCriterion("IS_REPLACE_SELL >", value, "isReplaceSell");
            return (Criteria) this;
        }

        public Criteria andIsReplaceSellGreaterThanOrEqualTo(String value) {
            addCriterion("IS_REPLACE_SELL >=", value, "isReplaceSell");
            return (Criteria) this;
        }

        public Criteria andIsReplaceSellLessThan(String value) {
            addCriterion("IS_REPLACE_SELL <", value, "isReplaceSell");
            return (Criteria) this;
        }

        public Criteria andIsReplaceSellLessThanOrEqualTo(String value) {
            addCriterion("IS_REPLACE_SELL <=", value, "isReplaceSell");
            return (Criteria) this;
        }

        public Criteria andIsReplaceSellLike(String value) {
            addCriterion("IS_REPLACE_SELL like", value, "isReplaceSell");
            return (Criteria) this;
        }

        public Criteria andIsReplaceSellNotLike(String value) {
            addCriterion("IS_REPLACE_SELL not like", value, "isReplaceSell");
            return (Criteria) this;
        }

        public Criteria andIsReplaceSellIn(List<String> values) {
            addCriterion("IS_REPLACE_SELL in", values, "isReplaceSell");
            return (Criteria) this;
        }

        public Criteria andIsReplaceSellNotIn(List<String> values) {
            addCriterion("IS_REPLACE_SELL not in", values, "isReplaceSell");
            return (Criteria) this;
        }

        public Criteria andIsReplaceSellBetween(String value1, String value2) {
            addCriterion("IS_REPLACE_SELL between", value1, value2, "isReplaceSell");
            return (Criteria) this;
        }

        public Criteria andIsReplaceSellNotBetween(String value1, String value2) {
            addCriterion("IS_REPLACE_SELL not between", value1, value2, "isReplaceSell");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceIsNull() {
            addCriterion("IS_INVOICE is null");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceIsNotNull() {
            addCriterion("IS_INVOICE is not null");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceEqualTo(String value) {
            addCriterion("IS_INVOICE =", value, "isInvoice");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceNotEqualTo(String value) {
            addCriterion("IS_INVOICE <>", value, "isInvoice");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceGreaterThan(String value) {
            addCriterion("IS_INVOICE >", value, "isInvoice");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceGreaterThanOrEqualTo(String value) {
            addCriterion("IS_INVOICE >=", value, "isInvoice");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceLessThan(String value) {
            addCriterion("IS_INVOICE <", value, "isInvoice");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceLessThanOrEqualTo(String value) {
            addCriterion("IS_INVOICE <=", value, "isInvoice");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceLike(String value) {
            addCriterion("IS_INVOICE like", value, "isInvoice");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceNotLike(String value) {
            addCriterion("IS_INVOICE not like", value, "isInvoice");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceIn(List<String> values) {
            addCriterion("IS_INVOICE in", values, "isInvoice");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceNotIn(List<String> values) {
            addCriterion("IS_INVOICE not in", values, "isInvoice");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceBetween(String value1, String value2) {
            addCriterion("IS_INVOICE between", value1, value2, "isInvoice");
            return (Criteria) this;
        }

        public Criteria andIsInvoiceNotBetween(String value1, String value2) {
            addCriterion("IS_INVOICE not between", value1, value2, "isInvoice");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIsNull() {
            addCriterion("INVOICE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIsNotNull() {
            addCriterion("INVOICE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeEqualTo(String value) {
            addCriterion("INVOICE_TYPE =", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotEqualTo(String value) {
            addCriterion("INVOICE_TYPE <>", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeGreaterThan(String value) {
            addCriterion("INVOICE_TYPE >", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("INVOICE_TYPE >=", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLessThan(String value) {
            addCriterion("INVOICE_TYPE <", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLessThanOrEqualTo(String value) {
            addCriterion("INVOICE_TYPE <=", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLike(String value) {
            addCriterion("INVOICE_TYPE like", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotLike(String value) {
            addCriterion("INVOICE_TYPE not like", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIn(List<String> values) {
            addCriterion("INVOICE_TYPE in", values, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotIn(List<String> values) {
            addCriterion("INVOICE_TYPE not in", values, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeBetween(String value1, String value2) {
            addCriterion("INVOICE_TYPE between", value1, value2, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotBetween(String value1, String value2) {
            addCriterion("INVOICE_TYPE not between", value1, value2, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andAfterSaleIsNull() {
            addCriterion("AFTER_SALE is null");
            return (Criteria) this;
        }

        public Criteria andAfterSaleIsNotNull() {
            addCriterion("AFTER_SALE is not null");
            return (Criteria) this;
        }

        public Criteria andAfterSaleEqualTo(String value) {
            addCriterion("AFTER_SALE =", value, "afterSale");
            return (Criteria) this;
        }

        public Criteria andAfterSaleNotEqualTo(String value) {
            addCriterion("AFTER_SALE <>", value, "afterSale");
            return (Criteria) this;
        }

        public Criteria andAfterSaleGreaterThan(String value) {
            addCriterion("AFTER_SALE >", value, "afterSale");
            return (Criteria) this;
        }

        public Criteria andAfterSaleGreaterThanOrEqualTo(String value) {
            addCriterion("AFTER_SALE >=", value, "afterSale");
            return (Criteria) this;
        }

        public Criteria andAfterSaleLessThan(String value) {
            addCriterion("AFTER_SALE <", value, "afterSale");
            return (Criteria) this;
        }

        public Criteria andAfterSaleLessThanOrEqualTo(String value) {
            addCriterion("AFTER_SALE <=", value, "afterSale");
            return (Criteria) this;
        }

        public Criteria andAfterSaleLike(String value) {
            addCriterion("AFTER_SALE like", value, "afterSale");
            return (Criteria) this;
        }

        public Criteria andAfterSaleNotLike(String value) {
            addCriterion("AFTER_SALE not like", value, "afterSale");
            return (Criteria) this;
        }

        public Criteria andAfterSaleIn(List<String> values) {
            addCriterion("AFTER_SALE in", values, "afterSale");
            return (Criteria) this;
        }

        public Criteria andAfterSaleNotIn(List<String> values) {
            addCriterion("AFTER_SALE not in", values, "afterSale");
            return (Criteria) this;
        }

        public Criteria andAfterSaleBetween(String value1, String value2) {
            addCriterion("AFTER_SALE between", value1, value2, "afterSale");
            return (Criteria) this;
        }

        public Criteria andAfterSaleNotBetween(String value1, String value2) {
            addCriterion("AFTER_SALE not between", value1, value2, "afterSale");
            return (Criteria) this;
        }

        public Criteria andUpshelfTypeIsNull() {
            addCriterion("UPSHELF_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andUpshelfTypeIsNotNull() {
            addCriterion("UPSHELF_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andUpshelfTypeEqualTo(String value) {
            addCriterion("UPSHELF_TYPE =", value, "upshelfType");
            return (Criteria) this;
        }

        public Criteria andUpshelfTypeNotEqualTo(String value) {
            addCriterion("UPSHELF_TYPE <>", value, "upshelfType");
            return (Criteria) this;
        }

        public Criteria andUpshelfTypeGreaterThan(String value) {
            addCriterion("UPSHELF_TYPE >", value, "upshelfType");
            return (Criteria) this;
        }

        public Criteria andUpshelfTypeGreaterThanOrEqualTo(String value) {
            addCriterion("UPSHELF_TYPE >=", value, "upshelfType");
            return (Criteria) this;
        }

        public Criteria andUpshelfTypeLessThan(String value) {
            addCriterion("UPSHELF_TYPE <", value, "upshelfType");
            return (Criteria) this;
        }

        public Criteria andUpshelfTypeLessThanOrEqualTo(String value) {
            addCriterion("UPSHELF_TYPE <=", value, "upshelfType");
            return (Criteria) this;
        }

        public Criteria andUpshelfTypeLike(String value) {
            addCriterion("UPSHELF_TYPE like", value, "upshelfType");
            return (Criteria) this;
        }

        public Criteria andUpshelfTypeNotLike(String value) {
            addCriterion("UPSHELF_TYPE not like", value, "upshelfType");
            return (Criteria) this;
        }

        public Criteria andUpshelfTypeIn(List<String> values) {
            addCriterion("UPSHELF_TYPE in", values, "upshelfType");
            return (Criteria) this;
        }

        public Criteria andUpshelfTypeNotIn(List<String> values) {
            addCriterion("UPSHELF_TYPE not in", values, "upshelfType");
            return (Criteria) this;
        }

        public Criteria andUpshelfTypeBetween(String value1, String value2) {
            addCriterion("UPSHELF_TYPE between", value1, value2, "upshelfType");
            return (Criteria) this;
        }

        public Criteria andUpshelfTypeNotBetween(String value1, String value2) {
            addCriterion("UPSHELF_TYPE not between", value1, value2, "upshelfType");
            return (Criteria) this;
        }

        public Criteria andUpTimeIsNull() {
            addCriterion("UP_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpTimeIsNotNull() {
            addCriterion("UP_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpTimeEqualTo(Timestamp value) {
            addCriterion("UP_TIME =", value, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeNotEqualTo(Timestamp value) {
            addCriterion("UP_TIME <>", value, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeGreaterThan(Timestamp value) {
            addCriterion("UP_TIME >", value, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("UP_TIME >=", value, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeLessThan(Timestamp value) {
            addCriterion("UP_TIME <", value, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("UP_TIME <=", value, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeIn(List<Timestamp> values) {
            addCriterion("UP_TIME in", values, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeNotIn(List<Timestamp> values) {
            addCriterion("UP_TIME not in", values, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("UP_TIME between", value1, value2, "upTime");
            return (Criteria) this;
        }

        public Criteria andUpTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("UP_TIME not between", value1, value2, "upTime");
            return (Criteria) this;
        }

        public Criteria andDownTimeIsNull() {
            addCriterion("DOWN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andDownTimeIsNotNull() {
            addCriterion("DOWN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andDownTimeEqualTo(Timestamp value) {
            addCriterion("DOWN_TIME =", value, "downTime");
            return (Criteria) this;
        }

        public Criteria andDownTimeNotEqualTo(Timestamp value) {
            addCriterion("DOWN_TIME <>", value, "downTime");
            return (Criteria) this;
        }

        public Criteria andDownTimeGreaterThan(Timestamp value) {
            addCriterion("DOWN_TIME >", value, "downTime");
            return (Criteria) this;
        }

        public Criteria andDownTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("DOWN_TIME >=", value, "downTime");
            return (Criteria) this;
        }

        public Criteria andDownTimeLessThan(Timestamp value) {
            addCriterion("DOWN_TIME <", value, "downTime");
            return (Criteria) this;
        }

        public Criteria andDownTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("DOWN_TIME <=", value, "downTime");
            return (Criteria) this;
        }

        public Criteria andDownTimeIn(List<Timestamp> values) {
            addCriterion("DOWN_TIME in", values, "downTime");
            return (Criteria) this;
        }

        public Criteria andDownTimeNotIn(List<Timestamp> values) {
            addCriterion("DOWN_TIME not in", values, "downTime");
            return (Criteria) this;
        }

        public Criteria andDownTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("DOWN_TIME between", value1, value2, "downTime");
            return (Criteria) this;
        }

        public Criteria andDownTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("DOWN_TIME not between", value1, value2, "downTime");
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

        public Criteria andRechargeTypeIsNull() {
            addCriterion("RECHARGE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeIsNotNull() {
            addCriterion("RECHARGE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeEqualTo(String value) {
            addCriterion("RECHARGE_TYPE =", value, "rechargeType");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeNotEqualTo(String value) {
            addCriterion("RECHARGE_TYPE <>", value, "rechargeType");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeGreaterThan(String value) {
            addCriterion("RECHARGE_TYPE >", value, "rechargeType");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("RECHARGE_TYPE >=", value, "rechargeType");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeLessThan(String value) {
            addCriterion("RECHARGE_TYPE <", value, "rechargeType");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeLessThanOrEqualTo(String value) {
            addCriterion("RECHARGE_TYPE <=", value, "rechargeType");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeLike(String value) {
            addCriterion("RECHARGE_TYPE like", value, "rechargeType");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeNotLike(String value) {
            addCriterion("RECHARGE_TYPE not like", value, "rechargeType");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeIn(List<String> values) {
            addCriterion("RECHARGE_TYPE in", values, "rechargeType");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeNotIn(List<String> values) {
            addCriterion("RECHARGE_TYPE not in", values, "rechargeType");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeBetween(String value1, String value2) {
            addCriterion("RECHARGE_TYPE between", value1, value2, "rechargeType");
            return (Criteria) this;
        }

        public Criteria andRechargeTypeNotBetween(String value1, String value2) {
            addCriterion("RECHARGE_TYPE not between", value1, value2, "rechargeType");
            return (Criteria) this;
        }

        public Criteria andBasicOrgIdIsNull() {
            addCriterion("BASIC_ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andBasicOrgIdIsNotNull() {
            addCriterion("BASIC_ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBasicOrgIdEqualTo(String value) {
            addCriterion("BASIC_ORG_ID =", value, "basicOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicOrgIdNotEqualTo(String value) {
            addCriterion("BASIC_ORG_ID <>", value, "basicOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicOrgIdGreaterThan(String value) {
            addCriterion("BASIC_ORG_ID >", value, "basicOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("BASIC_ORG_ID >=", value, "basicOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicOrgIdLessThan(String value) {
            addCriterion("BASIC_ORG_ID <", value, "basicOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicOrgIdLessThanOrEqualTo(String value) {
            addCriterion("BASIC_ORG_ID <=", value, "basicOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicOrgIdLike(String value) {
            addCriterion("BASIC_ORG_ID like", value, "basicOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicOrgIdNotLike(String value) {
            addCriterion("BASIC_ORG_ID not like", value, "basicOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicOrgIdIn(List<String> values) {
            addCriterion("BASIC_ORG_ID in", values, "basicOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicOrgIdNotIn(List<String> values) {
            addCriterion("BASIC_ORG_ID not in", values, "basicOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicOrgIdBetween(String value1, String value2) {
            addCriterion("BASIC_ORG_ID between", value1, value2, "basicOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicOrgIdNotBetween(String value1, String value2) {
            addCriterion("BASIC_ORG_ID not between", value1, value2, "basicOrgId");
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

        public Criteria andPresaleBeginTimeIsNull() {
            addCriterion("PRESALE_BEGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPresaleBeginTimeIsNotNull() {
            addCriterion("PRESALE_BEGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPresaleBeginTimeEqualTo(Timestamp value) {
            addCriterion("PRESALE_BEGIN_TIME =", value, "presaleBeginTime");
            return (Criteria) this;
        }

        public Criteria andPresaleBeginTimeNotEqualTo(Timestamp value) {
            addCriterion("PRESALE_BEGIN_TIME <>", value, "presaleBeginTime");
            return (Criteria) this;
        }

        public Criteria andPresaleBeginTimeGreaterThan(Timestamp value) {
            addCriterion("PRESALE_BEGIN_TIME >", value, "presaleBeginTime");
            return (Criteria) this;
        }

        public Criteria andPresaleBeginTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("PRESALE_BEGIN_TIME >=", value, "presaleBeginTime");
            return (Criteria) this;
        }

        public Criteria andPresaleBeginTimeLessThan(Timestamp value) {
            addCriterion("PRESALE_BEGIN_TIME <", value, "presaleBeginTime");
            return (Criteria) this;
        }

        public Criteria andPresaleBeginTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("PRESALE_BEGIN_TIME <=", value, "presaleBeginTime");
            return (Criteria) this;
        }

        public Criteria andPresaleBeginTimeIn(List<Timestamp> values) {
            addCriterion("PRESALE_BEGIN_TIME in", values, "presaleBeginTime");
            return (Criteria) this;
        }

        public Criteria andPresaleBeginTimeNotIn(List<Timestamp> values) {
            addCriterion("PRESALE_BEGIN_TIME not in", values, "presaleBeginTime");
            return (Criteria) this;
        }

        public Criteria andPresaleBeginTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PRESALE_BEGIN_TIME between", value1, value2, "presaleBeginTime");
            return (Criteria) this;
        }

        public Criteria andPresaleBeginTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PRESALE_BEGIN_TIME not between", value1, value2, "presaleBeginTime");
            return (Criteria) this;
        }

        public Criteria andPresaleEndTimeIsNull() {
            addCriterion("PRESALE_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPresaleEndTimeIsNotNull() {
            addCriterion("PRESALE_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPresaleEndTimeEqualTo(Timestamp value) {
            addCriterion("PRESALE_END_TIME =", value, "presaleEndTime");
            return (Criteria) this;
        }

        public Criteria andPresaleEndTimeNotEqualTo(Timestamp value) {
            addCriterion("PRESALE_END_TIME <>", value, "presaleEndTime");
            return (Criteria) this;
        }

        public Criteria andPresaleEndTimeGreaterThan(Timestamp value) {
            addCriterion("PRESALE_END_TIME >", value, "presaleEndTime");
            return (Criteria) this;
        }

        public Criteria andPresaleEndTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("PRESALE_END_TIME >=", value, "presaleEndTime");
            return (Criteria) this;
        }

        public Criteria andPresaleEndTimeLessThan(Timestamp value) {
            addCriterion("PRESALE_END_TIME <", value, "presaleEndTime");
            return (Criteria) this;
        }

        public Criteria andPresaleEndTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("PRESALE_END_TIME <=", value, "presaleEndTime");
            return (Criteria) this;
        }

        public Criteria andPresaleEndTimeIn(List<Timestamp> values) {
            addCriterion("PRESALE_END_TIME in", values, "presaleEndTime");
            return (Criteria) this;
        }

        public Criteria andPresaleEndTimeNotIn(List<Timestamp> values) {
            addCriterion("PRESALE_END_TIME not in", values, "presaleEndTime");
            return (Criteria) this;
        }

        public Criteria andPresaleEndTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PRESALE_END_TIME between", value1, value2, "presaleEndTime");
            return (Criteria) this;
        }

        public Criteria andPresaleEndTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PRESALE_END_TIME not between", value1, value2, "presaleEndTime");
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