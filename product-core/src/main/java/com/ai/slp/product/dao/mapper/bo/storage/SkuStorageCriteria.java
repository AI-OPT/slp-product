package com.ai.slp.product.dao.mapper.bo.storage;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SkuStorageCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public SkuStorageCriteria() {
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

        public Criteria andSkuStorageIdIsNull() {
            addCriterion("SKU_STORAGE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSkuStorageIdIsNotNull() {
            addCriterion("SKU_STORAGE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSkuStorageIdEqualTo(String value) {
            addCriterion("SKU_STORAGE_ID =", value, "skuStorageId");
            return (Criteria) this;
        }

        public Criteria andSkuStorageIdNotEqualTo(String value) {
            addCriterion("SKU_STORAGE_ID <>", value, "skuStorageId");
            return (Criteria) this;
        }

        public Criteria andSkuStorageIdGreaterThan(String value) {
            addCriterion("SKU_STORAGE_ID >", value, "skuStorageId");
            return (Criteria) this;
        }

        public Criteria andSkuStorageIdGreaterThanOrEqualTo(String value) {
            addCriterion("SKU_STORAGE_ID >=", value, "skuStorageId");
            return (Criteria) this;
        }

        public Criteria andSkuStorageIdLessThan(String value) {
            addCriterion("SKU_STORAGE_ID <", value, "skuStorageId");
            return (Criteria) this;
        }

        public Criteria andSkuStorageIdLessThanOrEqualTo(String value) {
            addCriterion("SKU_STORAGE_ID <=", value, "skuStorageId");
            return (Criteria) this;
        }

        public Criteria andSkuStorageIdLike(String value) {
            addCriterion("SKU_STORAGE_ID like", value, "skuStorageId");
            return (Criteria) this;
        }

        public Criteria andSkuStorageIdNotLike(String value) {
            addCriterion("SKU_STORAGE_ID not like", value, "skuStorageId");
            return (Criteria) this;
        }

        public Criteria andSkuStorageIdIn(List<String> values) {
            addCriterion("SKU_STORAGE_ID in", values, "skuStorageId");
            return (Criteria) this;
        }

        public Criteria andSkuStorageIdNotIn(List<String> values) {
            addCriterion("SKU_STORAGE_ID not in", values, "skuStorageId");
            return (Criteria) this;
        }

        public Criteria andSkuStorageIdBetween(String value1, String value2) {
            addCriterion("SKU_STORAGE_ID between", value1, value2, "skuStorageId");
            return (Criteria) this;
        }

        public Criteria andSkuStorageIdNotBetween(String value1, String value2) {
            addCriterion("SKU_STORAGE_ID not between", value1, value2, "skuStorageId");
            return (Criteria) this;
        }

        public Criteria andSkuIdIsNull() {
            addCriterion("SKU_ID is null");
            return (Criteria) this;
        }

        public Criteria andSkuIdIsNotNull() {
            addCriterion("SKU_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSkuIdEqualTo(String value) {
            addCriterion("SKU_ID =", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotEqualTo(String value) {
            addCriterion("SKU_ID <>", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdGreaterThan(String value) {
            addCriterion("SKU_ID >", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdGreaterThanOrEqualTo(String value) {
            addCriterion("SKU_ID >=", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLessThan(String value) {
            addCriterion("SKU_ID <", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLessThanOrEqualTo(String value) {
            addCriterion("SKU_ID <=", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLike(String value) {
            addCriterion("SKU_ID like", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotLike(String value) {
            addCriterion("SKU_ID not like", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdIn(List<String> values) {
            addCriterion("SKU_ID in", values, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotIn(List<String> values) {
            addCriterion("SKU_ID not in", values, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdBetween(String value1, String value2) {
            addCriterion("SKU_ID between", value1, value2, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotBetween(String value1, String value2) {
            addCriterion("SKU_ID not between", value1, value2, "skuId");
            return (Criteria) this;
        }

        public Criteria andStorageIdIsNull() {
            addCriterion("STORAGE_ID is null");
            return (Criteria) this;
        }

        public Criteria andStorageIdIsNotNull() {
            addCriterion("STORAGE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStorageIdEqualTo(String value) {
            addCriterion("STORAGE_ID =", value, "storageId");
            return (Criteria) this;
        }

        public Criteria andStorageIdNotEqualTo(String value) {
            addCriterion("STORAGE_ID <>", value, "storageId");
            return (Criteria) this;
        }

        public Criteria andStorageIdGreaterThan(String value) {
            addCriterion("STORAGE_ID >", value, "storageId");
            return (Criteria) this;
        }

        public Criteria andStorageIdGreaterThanOrEqualTo(String value) {
            addCriterion("STORAGE_ID >=", value, "storageId");
            return (Criteria) this;
        }

        public Criteria andStorageIdLessThan(String value) {
            addCriterion("STORAGE_ID <", value, "storageId");
            return (Criteria) this;
        }

        public Criteria andStorageIdLessThanOrEqualTo(String value) {
            addCriterion("STORAGE_ID <=", value, "storageId");
            return (Criteria) this;
        }

        public Criteria andStorageIdLike(String value) {
            addCriterion("STORAGE_ID like", value, "storageId");
            return (Criteria) this;
        }

        public Criteria andStorageIdNotLike(String value) {
            addCriterion("STORAGE_ID not like", value, "storageId");
            return (Criteria) this;
        }

        public Criteria andStorageIdIn(List<String> values) {
            addCriterion("STORAGE_ID in", values, "storageId");
            return (Criteria) this;
        }

        public Criteria andStorageIdNotIn(List<String> values) {
            addCriterion("STORAGE_ID not in", values, "storageId");
            return (Criteria) this;
        }

        public Criteria andStorageIdBetween(String value1, String value2) {
            addCriterion("STORAGE_ID between", value1, value2, "storageId");
            return (Criteria) this;
        }

        public Criteria andStorageIdNotBetween(String value1, String value2) {
            addCriterion("STORAGE_ID not between", value1, value2, "storageId");
            return (Criteria) this;
        }

        public Criteria andSalePriceIsNull() {
            addCriterion("SALE_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andSalePriceIsNotNull() {
            addCriterion("SALE_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andSalePriceEqualTo(Long value) {
            addCriterion("SALE_PRICE =", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotEqualTo(Long value) {
            addCriterion("SALE_PRICE <>", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceGreaterThan(Long value) {
            addCriterion("SALE_PRICE >", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceGreaterThanOrEqualTo(Long value) {
            addCriterion("SALE_PRICE >=", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceLessThan(Long value) {
            addCriterion("SALE_PRICE <", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceLessThanOrEqualTo(Long value) {
            addCriterion("SALE_PRICE <=", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceIn(List<Long> values) {
            addCriterion("SALE_PRICE in", values, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotIn(List<Long> values) {
            addCriterion("SALE_PRICE not in", values, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceBetween(Long value1, Long value2) {
            addCriterion("SALE_PRICE between", value1, value2, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotBetween(Long value1, Long value2) {
            addCriterion("SALE_PRICE not between", value1, value2, "salePrice");
            return (Criteria) this;
        }

        public Criteria andTotalNumIsNull() {
            addCriterion("TOTAL_NUM is null");
            return (Criteria) this;
        }

        public Criteria andTotalNumIsNotNull() {
            addCriterion("TOTAL_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andTotalNumEqualTo(Long value) {
            addCriterion("TOTAL_NUM =", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumNotEqualTo(Long value) {
            addCriterion("TOTAL_NUM <>", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumGreaterThan(Long value) {
            addCriterion("TOTAL_NUM >", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumGreaterThanOrEqualTo(Long value) {
            addCriterion("TOTAL_NUM >=", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumLessThan(Long value) {
            addCriterion("TOTAL_NUM <", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumLessThanOrEqualTo(Long value) {
            addCriterion("TOTAL_NUM <=", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumIn(List<Long> values) {
            addCriterion("TOTAL_NUM in", values, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumNotIn(List<Long> values) {
            addCriterion("TOTAL_NUM not in", values, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumBetween(Long value1, Long value2) {
            addCriterion("TOTAL_NUM between", value1, value2, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumNotBetween(Long value1, Long value2) {
            addCriterion("TOTAL_NUM not between", value1, value2, "totalNum");
            return (Criteria) this;
        }

        public Criteria andUsableNumIsNull() {
            addCriterion("USABLE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andUsableNumIsNotNull() {
            addCriterion("USABLE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andUsableNumEqualTo(Long value) {
            addCriterion("USABLE_NUM =", value, "usableNum");
            return (Criteria) this;
        }

        public Criteria andUsableNumNotEqualTo(Long value) {
            addCriterion("USABLE_NUM <>", value, "usableNum");
            return (Criteria) this;
        }

        public Criteria andUsableNumGreaterThan(Long value) {
            addCriterion("USABLE_NUM >", value, "usableNum");
            return (Criteria) this;
        }

        public Criteria andUsableNumGreaterThanOrEqualTo(Long value) {
            addCriterion("USABLE_NUM >=", value, "usableNum");
            return (Criteria) this;
        }

        public Criteria andUsableNumLessThan(Long value) {
            addCriterion("USABLE_NUM <", value, "usableNum");
            return (Criteria) this;
        }

        public Criteria andUsableNumLessThanOrEqualTo(Long value) {
            addCriterion("USABLE_NUM <=", value, "usableNum");
            return (Criteria) this;
        }

        public Criteria andUsableNumIn(List<Long> values) {
            addCriterion("USABLE_NUM in", values, "usableNum");
            return (Criteria) this;
        }

        public Criteria andUsableNumNotIn(List<Long> values) {
            addCriterion("USABLE_NUM not in", values, "usableNum");
            return (Criteria) this;
        }

        public Criteria andUsableNumBetween(Long value1, Long value2) {
            addCriterion("USABLE_NUM between", value1, value2, "usableNum");
            return (Criteria) this;
        }

        public Criteria andUsableNumNotBetween(Long value1, Long value2) {
            addCriterion("USABLE_NUM not between", value1, value2, "usableNum");
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