package com.ai.slp.product.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProdCommentCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public ProdCommentCriteria() {
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

        public Criteria andCommentIdIsNull() {
            addCriterion("COMMENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andCommentIdIsNotNull() {
            addCriterion("COMMENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCommentIdEqualTo(String value) {
            addCriterion("COMMENT_ID =", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotEqualTo(String value) {
            addCriterion("COMMENT_ID <>", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdGreaterThan(String value) {
            addCriterion("COMMENT_ID >", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdGreaterThanOrEqualTo(String value) {
            addCriterion("COMMENT_ID >=", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdLessThan(String value) {
            addCriterion("COMMENT_ID <", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdLessThanOrEqualTo(String value) {
            addCriterion("COMMENT_ID <=", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdLike(String value) {
            addCriterion("COMMENT_ID like", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotLike(String value) {
            addCriterion("COMMENT_ID not like", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdIn(List<String> values) {
            addCriterion("COMMENT_ID in", values, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotIn(List<String> values) {
            addCriterion("COMMENT_ID not in", values, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdBetween(String value1, String value2) {
            addCriterion("COMMENT_ID between", value1, value2, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotBetween(String value1, String value2) {
            addCriterion("COMMENT_ID not between", value1, value2, "commentId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("ORDER_ID =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("ORDER_ID <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("ORDER_ID >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_ID >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("ORDER_ID <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("ORDER_ID <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("ORDER_ID like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("ORDER_ID not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("ORDER_ID in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("ORDER_ID not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("ORDER_ID between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("ORDER_ID not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdIsNull() {
            addCriterion("SUB_ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdIsNotNull() {
            addCriterion("SUB_ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdEqualTo(String value) {
            addCriterion("SUB_ORDER_ID =", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdNotEqualTo(String value) {
            addCriterion("SUB_ORDER_ID <>", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdGreaterThan(String value) {
            addCriterion("SUB_ORDER_ID >", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("SUB_ORDER_ID >=", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdLessThan(String value) {
            addCriterion("SUB_ORDER_ID <", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdLessThanOrEqualTo(String value) {
            addCriterion("SUB_ORDER_ID <=", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdLike(String value) {
            addCriterion("SUB_ORDER_ID like", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdNotLike(String value) {
            addCriterion("SUB_ORDER_ID not like", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdIn(List<String> values) {
            addCriterion("SUB_ORDER_ID in", values, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdNotIn(List<String> values) {
            addCriterion("SUB_ORDER_ID not in", values, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdBetween(String value1, String value2) {
            addCriterion("SUB_ORDER_ID between", value1, value2, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdNotBetween(String value1, String value2) {
            addCriterion("SUB_ORDER_ID not between", value1, value2, "subOrderId");
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

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andCommentBodyIsNull() {
            addCriterion("COMMENT_BODY is null");
            return (Criteria) this;
        }

        public Criteria andCommentBodyIsNotNull() {
            addCriterion("COMMENT_BODY is not null");
            return (Criteria) this;
        }

        public Criteria andCommentBodyEqualTo(String value) {
            addCriterion("COMMENT_BODY =", value, "commentBody");
            return (Criteria) this;
        }

        public Criteria andCommentBodyNotEqualTo(String value) {
            addCriterion("COMMENT_BODY <>", value, "commentBody");
            return (Criteria) this;
        }

        public Criteria andCommentBodyGreaterThan(String value) {
            addCriterion("COMMENT_BODY >", value, "commentBody");
            return (Criteria) this;
        }

        public Criteria andCommentBodyGreaterThanOrEqualTo(String value) {
            addCriterion("COMMENT_BODY >=", value, "commentBody");
            return (Criteria) this;
        }

        public Criteria andCommentBodyLessThan(String value) {
            addCriterion("COMMENT_BODY <", value, "commentBody");
            return (Criteria) this;
        }

        public Criteria andCommentBodyLessThanOrEqualTo(String value) {
            addCriterion("COMMENT_BODY <=", value, "commentBody");
            return (Criteria) this;
        }

        public Criteria andCommentBodyLike(String value) {
            addCriterion("COMMENT_BODY like", value, "commentBody");
            return (Criteria) this;
        }

        public Criteria andCommentBodyNotLike(String value) {
            addCriterion("COMMENT_BODY not like", value, "commentBody");
            return (Criteria) this;
        }

        public Criteria andCommentBodyIn(List<String> values) {
            addCriterion("COMMENT_BODY in", values, "commentBody");
            return (Criteria) this;
        }

        public Criteria andCommentBodyNotIn(List<String> values) {
            addCriterion("COMMENT_BODY not in", values, "commentBody");
            return (Criteria) this;
        }

        public Criteria andCommentBodyBetween(String value1, String value2) {
            addCriterion("COMMENT_BODY between", value1, value2, "commentBody");
            return (Criteria) this;
        }

        public Criteria andCommentBodyNotBetween(String value1, String value2) {
            addCriterion("COMMENT_BODY not between", value1, value2, "commentBody");
            return (Criteria) this;
        }

        public Criteria andShopScoreMsIsNull() {
            addCriterion("SHOP_SCORE_MS is null");
            return (Criteria) this;
        }

        public Criteria andShopScoreMsIsNotNull() {
            addCriterion("SHOP_SCORE_MS is not null");
            return (Criteria) this;
        }

        public Criteria andShopScoreMsEqualTo(Long value) {
            addCriterion("SHOP_SCORE_MS =", value, "shopScoreMs");
            return (Criteria) this;
        }

        public Criteria andShopScoreMsNotEqualTo(Long value) {
            addCriterion("SHOP_SCORE_MS <>", value, "shopScoreMs");
            return (Criteria) this;
        }

        public Criteria andShopScoreMsGreaterThan(Long value) {
            addCriterion("SHOP_SCORE_MS >", value, "shopScoreMs");
            return (Criteria) this;
        }

        public Criteria andShopScoreMsGreaterThanOrEqualTo(Long value) {
            addCriterion("SHOP_SCORE_MS >=", value, "shopScoreMs");
            return (Criteria) this;
        }

        public Criteria andShopScoreMsLessThan(Long value) {
            addCriterion("SHOP_SCORE_MS <", value, "shopScoreMs");
            return (Criteria) this;
        }

        public Criteria andShopScoreMsLessThanOrEqualTo(Long value) {
            addCriterion("SHOP_SCORE_MS <=", value, "shopScoreMs");
            return (Criteria) this;
        }

        public Criteria andShopScoreMsIn(List<Long> values) {
            addCriterion("SHOP_SCORE_MS in", values, "shopScoreMs");
            return (Criteria) this;
        }

        public Criteria andShopScoreMsNotIn(List<Long> values) {
            addCriterion("SHOP_SCORE_MS not in", values, "shopScoreMs");
            return (Criteria) this;
        }

        public Criteria andShopScoreMsBetween(Long value1, Long value2) {
            addCriterion("SHOP_SCORE_MS between", value1, value2, "shopScoreMs");
            return (Criteria) this;
        }

        public Criteria andShopScoreMsNotBetween(Long value1, Long value2) {
            addCriterion("SHOP_SCORE_MS not between", value1, value2, "shopScoreMs");
            return (Criteria) this;
        }

        public Criteria andShopScoreFwIsNull() {
            addCriterion("SHOP_SCORE_FW is null");
            return (Criteria) this;
        }

        public Criteria andShopScoreFwIsNotNull() {
            addCriterion("SHOP_SCORE_FW is not null");
            return (Criteria) this;
        }

        public Criteria andShopScoreFwEqualTo(Long value) {
            addCriterion("SHOP_SCORE_FW =", value, "shopScoreFw");
            return (Criteria) this;
        }

        public Criteria andShopScoreFwNotEqualTo(Long value) {
            addCriterion("SHOP_SCORE_FW <>", value, "shopScoreFw");
            return (Criteria) this;
        }

        public Criteria andShopScoreFwGreaterThan(Long value) {
            addCriterion("SHOP_SCORE_FW >", value, "shopScoreFw");
            return (Criteria) this;
        }

        public Criteria andShopScoreFwGreaterThanOrEqualTo(Long value) {
            addCriterion("SHOP_SCORE_FW >=", value, "shopScoreFw");
            return (Criteria) this;
        }

        public Criteria andShopScoreFwLessThan(Long value) {
            addCriterion("SHOP_SCORE_FW <", value, "shopScoreFw");
            return (Criteria) this;
        }

        public Criteria andShopScoreFwLessThanOrEqualTo(Long value) {
            addCriterion("SHOP_SCORE_FW <=", value, "shopScoreFw");
            return (Criteria) this;
        }

        public Criteria andShopScoreFwIn(List<Long> values) {
            addCriterion("SHOP_SCORE_FW in", values, "shopScoreFw");
            return (Criteria) this;
        }

        public Criteria andShopScoreFwNotIn(List<Long> values) {
            addCriterion("SHOP_SCORE_FW not in", values, "shopScoreFw");
            return (Criteria) this;
        }

        public Criteria andShopScoreFwBetween(Long value1, Long value2) {
            addCriterion("SHOP_SCORE_FW between", value1, value2, "shopScoreFw");
            return (Criteria) this;
        }

        public Criteria andShopScoreFwNotBetween(Long value1, Long value2) {
            addCriterion("SHOP_SCORE_FW not between", value1, value2, "shopScoreFw");
            return (Criteria) this;
        }

        public Criteria andShopScoreWlIsNull() {
            addCriterion("SHOP_SCORE_WL is null");
            return (Criteria) this;
        }

        public Criteria andShopScoreWlIsNotNull() {
            addCriterion("SHOP_SCORE_WL is not null");
            return (Criteria) this;
        }

        public Criteria andShopScoreWlEqualTo(Long value) {
            addCriterion("SHOP_SCORE_WL =", value, "shopScoreWl");
            return (Criteria) this;
        }

        public Criteria andShopScoreWlNotEqualTo(Long value) {
            addCriterion("SHOP_SCORE_WL <>", value, "shopScoreWl");
            return (Criteria) this;
        }

        public Criteria andShopScoreWlGreaterThan(Long value) {
            addCriterion("SHOP_SCORE_WL >", value, "shopScoreWl");
            return (Criteria) this;
        }

        public Criteria andShopScoreWlGreaterThanOrEqualTo(Long value) {
            addCriterion("SHOP_SCORE_WL >=", value, "shopScoreWl");
            return (Criteria) this;
        }

        public Criteria andShopScoreWlLessThan(Long value) {
            addCriterion("SHOP_SCORE_WL <", value, "shopScoreWl");
            return (Criteria) this;
        }

        public Criteria andShopScoreWlLessThanOrEqualTo(Long value) {
            addCriterion("SHOP_SCORE_WL <=", value, "shopScoreWl");
            return (Criteria) this;
        }

        public Criteria andShopScoreWlIn(List<Long> values) {
            addCriterion("SHOP_SCORE_WL in", values, "shopScoreWl");
            return (Criteria) this;
        }

        public Criteria andShopScoreWlNotIn(List<Long> values) {
            addCriterion("SHOP_SCORE_WL not in", values, "shopScoreWl");
            return (Criteria) this;
        }

        public Criteria andShopScoreWlBetween(Long value1, Long value2) {
            addCriterion("SHOP_SCORE_WL between", value1, value2, "shopScoreWl");
            return (Criteria) this;
        }

        public Criteria andShopScoreWlNotBetween(Long value1, Long value2) {
            addCriterion("SHOP_SCORE_WL not between", value1, value2, "shopScoreWl");
            return (Criteria) this;
        }

        public Criteria andCommentTimeIsNull() {
            addCriterion("COMMENT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCommentTimeIsNotNull() {
            addCriterion("COMMENT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCommentTimeEqualTo(Timestamp value) {
            addCriterion("COMMENT_TIME =", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeNotEqualTo(Timestamp value) {
            addCriterion("COMMENT_TIME <>", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeGreaterThan(Timestamp value) {
            addCriterion("COMMENT_TIME >", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("COMMENT_TIME >=", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeLessThan(Timestamp value) {
            addCriterion("COMMENT_TIME <", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("COMMENT_TIME <=", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeIn(List<Timestamp> values) {
            addCriterion("COMMENT_TIME in", values, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeNotIn(List<Timestamp> values) {
            addCriterion("COMMENT_TIME not in", values, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("COMMENT_TIME between", value1, value2, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("COMMENT_TIME not between", value1, value2, "commentTime");
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

        public Criteria andReplyStateIsNull() {
            addCriterion("REPLY_STATE is null");
            return (Criteria) this;
        }

        public Criteria andReplyStateIsNotNull() {
            addCriterion("REPLY_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andReplyStateEqualTo(String value) {
            addCriterion("REPLY_STATE =", value, "replyState");
            return (Criteria) this;
        }

        public Criteria andReplyStateNotEqualTo(String value) {
            addCriterion("REPLY_STATE <>", value, "replyState");
            return (Criteria) this;
        }

        public Criteria andReplyStateGreaterThan(String value) {
            addCriterion("REPLY_STATE >", value, "replyState");
            return (Criteria) this;
        }

        public Criteria andReplyStateGreaterThanOrEqualTo(String value) {
            addCriterion("REPLY_STATE >=", value, "replyState");
            return (Criteria) this;
        }

        public Criteria andReplyStateLessThan(String value) {
            addCriterion("REPLY_STATE <", value, "replyState");
            return (Criteria) this;
        }

        public Criteria andReplyStateLessThanOrEqualTo(String value) {
            addCriterion("REPLY_STATE <=", value, "replyState");
            return (Criteria) this;
        }

        public Criteria andReplyStateLike(String value) {
            addCriterion("REPLY_STATE like", value, "replyState");
            return (Criteria) this;
        }

        public Criteria andReplyStateNotLike(String value) {
            addCriterion("REPLY_STATE not like", value, "replyState");
            return (Criteria) this;
        }

        public Criteria andReplyStateIn(List<String> values) {
            addCriterion("REPLY_STATE in", values, "replyState");
            return (Criteria) this;
        }

        public Criteria andReplyStateNotIn(List<String> values) {
            addCriterion("REPLY_STATE not in", values, "replyState");
            return (Criteria) this;
        }

        public Criteria andReplyStateBetween(String value1, String value2) {
            addCriterion("REPLY_STATE between", value1, value2, "replyState");
            return (Criteria) this;
        }

        public Criteria andReplyStateNotBetween(String value1, String value2) {
            addCriterion("REPLY_STATE not between", value1, value2, "replyState");
            return (Criteria) this;
        }

        public Criteria andIsPictureIsNull() {
            addCriterion("IS_PICTURE is null");
            return (Criteria) this;
        }

        public Criteria andIsPictureIsNotNull() {
            addCriterion("IS_PICTURE is not null");
            return (Criteria) this;
        }

        public Criteria andIsPictureEqualTo(String value) {
            addCriterion("IS_PICTURE =", value, "isPicture");
            return (Criteria) this;
        }

        public Criteria andIsPictureNotEqualTo(String value) {
            addCriterion("IS_PICTURE <>", value, "isPicture");
            return (Criteria) this;
        }

        public Criteria andIsPictureGreaterThan(String value) {
            addCriterion("IS_PICTURE >", value, "isPicture");
            return (Criteria) this;
        }

        public Criteria andIsPictureGreaterThanOrEqualTo(String value) {
            addCriterion("IS_PICTURE >=", value, "isPicture");
            return (Criteria) this;
        }

        public Criteria andIsPictureLessThan(String value) {
            addCriterion("IS_PICTURE <", value, "isPicture");
            return (Criteria) this;
        }

        public Criteria andIsPictureLessThanOrEqualTo(String value) {
            addCriterion("IS_PICTURE <=", value, "isPicture");
            return (Criteria) this;
        }

        public Criteria andIsPictureLike(String value) {
            addCriterion("IS_PICTURE like", value, "isPicture");
            return (Criteria) this;
        }

        public Criteria andIsPictureNotLike(String value) {
            addCriterion("IS_PICTURE not like", value, "isPicture");
            return (Criteria) this;
        }

        public Criteria andIsPictureIn(List<String> values) {
            addCriterion("IS_PICTURE in", values, "isPicture");
            return (Criteria) this;
        }

        public Criteria andIsPictureNotIn(List<String> values) {
            addCriterion("IS_PICTURE not in", values, "isPicture");
            return (Criteria) this;
        }

        public Criteria andIsPictureBetween(String value1, String value2) {
            addCriterion("IS_PICTURE between", value1, value2, "isPicture");
            return (Criteria) this;
        }

        public Criteria andIsPictureNotBetween(String value1, String value2) {
            addCriterion("IS_PICTURE not between", value1, value2, "isPicture");
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

        public Criteria andOperIdEqualTo(String value) {
            addCriterion("OPER_ID =", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdNotEqualTo(String value) {
            addCriterion("OPER_ID <>", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdGreaterThan(String value) {
            addCriterion("OPER_ID >", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_ID >=", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdLessThan(String value) {
            addCriterion("OPER_ID <", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdLessThanOrEqualTo(String value) {
            addCriterion("OPER_ID <=", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdLike(String value) {
            addCriterion("OPER_ID like", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdNotLike(String value) {
            addCriterion("OPER_ID not like", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdIn(List<String> values) {
            addCriterion("OPER_ID in", values, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdNotIn(List<String> values) {
            addCriterion("OPER_ID not in", values, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdBetween(String value1, String value2) {
            addCriterion("OPER_ID between", value1, value2, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdNotBetween(String value1, String value2) {
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