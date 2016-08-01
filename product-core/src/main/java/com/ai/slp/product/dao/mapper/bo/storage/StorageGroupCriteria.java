package com.ai.slp.product.dao.mapper.bo.storage;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class StorageGroupCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public StorageGroupCriteria() {
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

        public Criteria andRouteGroupIdIsNull() {
            addCriterion("ROUTE_GROUP_ID is null");
            return (Criteria) this;
        }

        public Criteria andRouteGroupIdIsNotNull() {
            addCriterion("ROUTE_GROUP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRouteGroupIdEqualTo(String value) {
            addCriterion("ROUTE_GROUP_ID =", value, "routeGroupId");
            return (Criteria) this;
        }

        public Criteria andRouteGroupIdNotEqualTo(String value) {
            addCriterion("ROUTE_GROUP_ID <>", value, "routeGroupId");
            return (Criteria) this;
        }

        public Criteria andRouteGroupIdGreaterThan(String value) {
            addCriterion("ROUTE_GROUP_ID >", value, "routeGroupId");
            return (Criteria) this;
        }

        public Criteria andRouteGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("ROUTE_GROUP_ID >=", value, "routeGroupId");
            return (Criteria) this;
        }

        public Criteria andRouteGroupIdLessThan(String value) {
            addCriterion("ROUTE_GROUP_ID <", value, "routeGroupId");
            return (Criteria) this;
        }

        public Criteria andRouteGroupIdLessThanOrEqualTo(String value) {
            addCriterion("ROUTE_GROUP_ID <=", value, "routeGroupId");
            return (Criteria) this;
        }

        public Criteria andRouteGroupIdLike(String value) {
            addCriterion("ROUTE_GROUP_ID like", value, "routeGroupId");
            return (Criteria) this;
        }

        public Criteria andRouteGroupIdNotLike(String value) {
            addCriterion("ROUTE_GROUP_ID not like", value, "routeGroupId");
            return (Criteria) this;
        }

        public Criteria andRouteGroupIdIn(List<String> values) {
            addCriterion("ROUTE_GROUP_ID in", values, "routeGroupId");
            return (Criteria) this;
        }

        public Criteria andRouteGroupIdNotIn(List<String> values) {
            addCriterion("ROUTE_GROUP_ID not in", values, "routeGroupId");
            return (Criteria) this;
        }

        public Criteria andRouteGroupIdBetween(String value1, String value2) {
            addCriterion("ROUTE_GROUP_ID between", value1, value2, "routeGroupId");
            return (Criteria) this;
        }

        public Criteria andRouteGroupIdNotBetween(String value1, String value2) {
            addCriterion("ROUTE_GROUP_ID not between", value1, value2, "routeGroupId");
            return (Criteria) this;
        }

        public Criteria andStorageGroupNameIsNull() {
            addCriterion("STORAGE_GROUP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStorageGroupNameIsNotNull() {
            addCriterion("STORAGE_GROUP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStorageGroupNameEqualTo(String value) {
            addCriterion("STORAGE_GROUP_NAME =", value, "storageGroupName");
            return (Criteria) this;
        }

        public Criteria andStorageGroupNameNotEqualTo(String value) {
            addCriterion("STORAGE_GROUP_NAME <>", value, "storageGroupName");
            return (Criteria) this;
        }

        public Criteria andStorageGroupNameGreaterThan(String value) {
            addCriterion("STORAGE_GROUP_NAME >", value, "storageGroupName");
            return (Criteria) this;
        }

        public Criteria andStorageGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("STORAGE_GROUP_NAME >=", value, "storageGroupName");
            return (Criteria) this;
        }

        public Criteria andStorageGroupNameLessThan(String value) {
            addCriterion("STORAGE_GROUP_NAME <", value, "storageGroupName");
            return (Criteria) this;
        }

        public Criteria andStorageGroupNameLessThanOrEqualTo(String value) {
            addCriterion("STORAGE_GROUP_NAME <=", value, "storageGroupName");
            return (Criteria) this;
        }

        public Criteria andStorageGroupNameLike(String value) {
            addCriterion("STORAGE_GROUP_NAME like", value, "storageGroupName");
            return (Criteria) this;
        }

        public Criteria andStorageGroupNameNotLike(String value) {
            addCriterion("STORAGE_GROUP_NAME not like", value, "storageGroupName");
            return (Criteria) this;
        }

        public Criteria andStorageGroupNameIn(List<String> values) {
            addCriterion("STORAGE_GROUP_NAME in", values, "storageGroupName");
            return (Criteria) this;
        }

        public Criteria andStorageGroupNameNotIn(List<String> values) {
            addCriterion("STORAGE_GROUP_NAME not in", values, "storageGroupName");
            return (Criteria) this;
        }

        public Criteria andStorageGroupNameBetween(String value1, String value2) {
            addCriterion("STORAGE_GROUP_NAME between", value1, value2, "storageGroupName");
            return (Criteria) this;
        }

        public Criteria andStorageGroupNameNotBetween(String value1, String value2) {
            addCriterion("STORAGE_GROUP_NAME not between", value1, value2, "storageGroupName");
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

        public Criteria andLowSalePriceIsNull() {
            addCriterion("LOW_SALE_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andLowSalePriceIsNotNull() {
            addCriterion("LOW_SALE_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andLowSalePriceEqualTo(Long value) {
            addCriterion("LOW_SALE_PRICE =", value, "lowSalePrice");
            return (Criteria) this;
        }

        public Criteria andLowSalePriceNotEqualTo(Long value) {
            addCriterion("LOW_SALE_PRICE <>", value, "lowSalePrice");
            return (Criteria) this;
        }

        public Criteria andLowSalePriceGreaterThan(Long value) {
            addCriterion("LOW_SALE_PRICE >", value, "lowSalePrice");
            return (Criteria) this;
        }

        public Criteria andLowSalePriceGreaterThanOrEqualTo(Long value) {
            addCriterion("LOW_SALE_PRICE >=", value, "lowSalePrice");
            return (Criteria) this;
        }

        public Criteria andLowSalePriceLessThan(Long value) {
            addCriterion("LOW_SALE_PRICE <", value, "lowSalePrice");
            return (Criteria) this;
        }

        public Criteria andLowSalePriceLessThanOrEqualTo(Long value) {
            addCriterion("LOW_SALE_PRICE <=", value, "lowSalePrice");
            return (Criteria) this;
        }

        public Criteria andLowSalePriceIn(List<Long> values) {
            addCriterion("LOW_SALE_PRICE in", values, "lowSalePrice");
            return (Criteria) this;
        }

        public Criteria andLowSalePriceNotIn(List<Long> values) {
            addCriterion("LOW_SALE_PRICE not in", values, "lowSalePrice");
            return (Criteria) this;
        }

        public Criteria andLowSalePriceBetween(Long value1, Long value2) {
            addCriterion("LOW_SALE_PRICE between", value1, value2, "lowSalePrice");
            return (Criteria) this;
        }

        public Criteria andLowSalePriceNotBetween(Long value1, Long value2) {
            addCriterion("LOW_SALE_PRICE not between", value1, value2, "lowSalePrice");
            return (Criteria) this;
        }

        public Criteria andHighSalePriceIsNull() {
            addCriterion("HIGH_SALE_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andHighSalePriceIsNotNull() {
            addCriterion("HIGH_SALE_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andHighSalePriceEqualTo(Long value) {
            addCriterion("HIGH_SALE_PRICE =", value, "highSalePrice");
            return (Criteria) this;
        }

        public Criteria andHighSalePriceNotEqualTo(Long value) {
            addCriterion("HIGH_SALE_PRICE <>", value, "highSalePrice");
            return (Criteria) this;
        }

        public Criteria andHighSalePriceGreaterThan(Long value) {
            addCriterion("HIGH_SALE_PRICE >", value, "highSalePrice");
            return (Criteria) this;
        }

        public Criteria andHighSalePriceGreaterThanOrEqualTo(Long value) {
            addCriterion("HIGH_SALE_PRICE >=", value, "highSalePrice");
            return (Criteria) this;
        }

        public Criteria andHighSalePriceLessThan(Long value) {
            addCriterion("HIGH_SALE_PRICE <", value, "highSalePrice");
            return (Criteria) this;
        }

        public Criteria andHighSalePriceLessThanOrEqualTo(Long value) {
            addCriterion("HIGH_SALE_PRICE <=", value, "highSalePrice");
            return (Criteria) this;
        }

        public Criteria andHighSalePriceIn(List<Long> values) {
            addCriterion("HIGH_SALE_PRICE in", values, "highSalePrice");
            return (Criteria) this;
        }

        public Criteria andHighSalePriceNotIn(List<Long> values) {
            addCriterion("HIGH_SALE_PRICE not in", values, "highSalePrice");
            return (Criteria) this;
        }

        public Criteria andHighSalePriceBetween(Long value1, Long value2) {
            addCriterion("HIGH_SALE_PRICE between", value1, value2, "highSalePrice");
            return (Criteria) this;
        }

        public Criteria andHighSalePriceNotBetween(Long value1, Long value2) {
            addCriterion("HIGH_SALE_PRICE not between", value1, value2, "highSalePrice");
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