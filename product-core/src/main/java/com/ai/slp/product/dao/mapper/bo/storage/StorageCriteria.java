package com.ai.slp.product.dao.mapper.bo.storage;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class StorageCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public StorageCriteria() {
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

        public Criteria andStorageNameIsNull() {
            addCriterion("STORAGE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStorageNameIsNotNull() {
            addCriterion("STORAGE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStorageNameEqualTo(String value) {
            addCriterion("STORAGE_NAME =", value, "storageName");
            return (Criteria) this;
        }

        public Criteria andStorageNameNotEqualTo(String value) {
            addCriterion("STORAGE_NAME <>", value, "storageName");
            return (Criteria) this;
        }

        public Criteria andStorageNameGreaterThan(String value) {
            addCriterion("STORAGE_NAME >", value, "storageName");
            return (Criteria) this;
        }

        public Criteria andStorageNameGreaterThanOrEqualTo(String value) {
            addCriterion("STORAGE_NAME >=", value, "storageName");
            return (Criteria) this;
        }

        public Criteria andStorageNameLessThan(String value) {
            addCriterion("STORAGE_NAME <", value, "storageName");
            return (Criteria) this;
        }

        public Criteria andStorageNameLessThanOrEqualTo(String value) {
            addCriterion("STORAGE_NAME <=", value, "storageName");
            return (Criteria) this;
        }

        public Criteria andStorageNameLike(String value) {
            addCriterion("STORAGE_NAME like", value, "storageName");
            return (Criteria) this;
        }

        public Criteria andStorageNameNotLike(String value) {
            addCriterion("STORAGE_NAME not like", value, "storageName");
            return (Criteria) this;
        }

        public Criteria andStorageNameIn(List<String> values) {
            addCriterion("STORAGE_NAME in", values, "storageName");
            return (Criteria) this;
        }

        public Criteria andStorageNameNotIn(List<String> values) {
            addCriterion("STORAGE_NAME not in", values, "storageName");
            return (Criteria) this;
        }

        public Criteria andStorageNameBetween(String value1, String value2) {
            addCriterion("STORAGE_NAME between", value1, value2, "storageName");
            return (Criteria) this;
        }

        public Criteria andStorageNameNotBetween(String value1, String value2) {
            addCriterion("STORAGE_NAME not between", value1, value2, "storageName");
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

        public Criteria andWarnNumIsNull() {
            addCriterion("WARN_NUM is null");
            return (Criteria) this;
        }

        public Criteria andWarnNumIsNotNull() {
            addCriterion("WARN_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andWarnNumEqualTo(Long value) {
            addCriterion("WARN_NUM =", value, "warnNum");
            return (Criteria) this;
        }

        public Criteria andWarnNumNotEqualTo(Long value) {
            addCriterion("WARN_NUM <>", value, "warnNum");
            return (Criteria) this;
        }

        public Criteria andWarnNumGreaterThan(Long value) {
            addCriterion("WARN_NUM >", value, "warnNum");
            return (Criteria) this;
        }

        public Criteria andWarnNumGreaterThanOrEqualTo(Long value) {
            addCriterion("WARN_NUM >=", value, "warnNum");
            return (Criteria) this;
        }

        public Criteria andWarnNumLessThan(Long value) {
            addCriterion("WARN_NUM <", value, "warnNum");
            return (Criteria) this;
        }

        public Criteria andWarnNumLessThanOrEqualTo(Long value) {
            addCriterion("WARN_NUM <=", value, "warnNum");
            return (Criteria) this;
        }

        public Criteria andWarnNumIn(List<Long> values) {
            addCriterion("WARN_NUM in", values, "warnNum");
            return (Criteria) this;
        }

        public Criteria andWarnNumNotIn(List<Long> values) {
            addCriterion("WARN_NUM not in", values, "warnNum");
            return (Criteria) this;
        }

        public Criteria andWarnNumBetween(Long value1, Long value2) {
            addCriterion("WARN_NUM between", value1, value2, "warnNum");
            return (Criteria) this;
        }

        public Criteria andWarnNumNotBetween(Long value1, Long value2) {
            addCriterion("WARN_NUM not between", value1, value2, "warnNum");
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