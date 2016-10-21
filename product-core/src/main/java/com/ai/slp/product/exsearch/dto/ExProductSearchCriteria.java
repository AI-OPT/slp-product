package com.ai.slp.product.exsearch.dto;

import java.util.ArrayList;
import java.util.List;

import com.ai.paas.ipaas.search.vo.SearchCriteria;
import com.ai.paas.ipaas.search.vo.SearchOption;
import com.ai.paas.ipaas.search.vo.SearchOption.SearchLogic;
import com.ai.paas.ipaas.search.vo.SearchOption.SearchType;
import com.ai.paas.ipaas.search.vo.Sort;
import com.ai.paas.ipaas.search.vo.Sort.SortOrder;
import com.ai.slp.product.constants.ExproductConstants;
import com.ai.slp.product.constants.SearchFieldConfConstants;

/**
 * 
 * Date: 2016年6月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author zhanglh
 */
public final class ExProductSearchCriteria {

    private List<SearchCriteria> searchfieldVos;
    private List<Sort> sortFields = new ArrayList<Sort>();
    private int maxSearchSize = 100;
    private int startSize = 0;

    private ExProductSearchCriteria() {
        searchfieldVos = new ArrayList<SearchCriteria>();
    }

    public static class ExProductSearchCriteriaBuilder {

        private ExProductSearchCriteria exProductSearchCriteria;

        public ExProductSearchCriteriaBuilder() {
            exProductSearchCriteria = new ExProductSearchCriteria();
        }
        //查询用户ID商品
        public ExProductSearchCriteriaBuilder userIdMust(String userId) {
            exProductSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.USER_ID,
                    userId, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
            return this;
        }
        //查询用户
        public ExProductSearchCriteriaBuilder userIdRange(String userId) {
            SearchCriteria vo = new SearchCriteria();
            vo.addSubCriteria(new SearchCriteria(SearchFieldConfConstants.USER_ID, userId, new SearchOption(SearchLogic.should, SearchType.querystring)));
            vo.addSubCriteria(new SearchCriteria(SearchFieldConfConstants.USER_ID, ExproductConstants.USER_ID, new SearchOption(SearchLogic.should, SearchType.querystring)));
            exProductSearchCriteria.searchfieldVos.add(vo);
            return this;
        }
        //查询用户类型商品
        public ExProductSearchCriteriaBuilder userTypeMust(String userType) {
            exProductSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.USER_AUTHORITY,
                    userType, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
            return this;
        }
        // 单品名字
        public ExProductSearchCriteriaBuilder skuNameLike(String skuName) {
            exProductSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.SKUNAME,
                    skuName, new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.querystring)));
            return this;
        }
        
        //单品名称与卖点整合
        public ExProductSearchCriteriaBuilder skuNameOrSellport(String skuName,String sellPort) {
            SearchCriteria vo = new SearchCriteria();
            vo.addSubCriteria(new SearchCriteria(SearchFieldConfConstants.SKUNAME, skuName, new SearchOption(SearchLogic.should, SearchType.querystring)));
            vo.addSubCriteria(new SearchCriteria(SearchFieldConfConstants.SELL_POINT, sellPort, new SearchOption(SearchLogic.should, SearchType.querystring)));
            exProductSearchCriteria.searchfieldVos.add(vo);
            return this;
        }
        // 单品名字
        public ExProductSearchCriteriaBuilder skuNameMust(String skuName) {
            exProductSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.SKUNAME,
                    skuName, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
            return this;
        }
        // 根据单品名字获取类目
        public ExProductSearchCriteriaBuilder skuNameCat(String skuName) {
            exProductSearchCriteria.searchfieldVos.get(0).addSubCriteria(new SearchCriteria(SearchFieldConfConstants.SKUNAME,
                    skuName, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
            return this;
        }
        // 卖点
        public ExProductSearchCriteriaBuilder sellPointLike(String sellPoint) {
            exProductSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.SELL_POINT,
                    sellPoint, new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.querystring)));
            return this;
        }

        // 卖点
        public ExProductSearchCriteriaBuilder sellPointMust(String sellPoint) {
            exProductSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.SELL_POINT,
                    sellPoint, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
            return this;
        }

        //充值方式
        public ExProductSearchCriteriaBuilder rechargeTypeIs(String rechagetype) {
            exProductSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.RECHAGE_TYPE,
                    rechagetype, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return this;
        }
       //充值方式not in
        public ExProductSearchCriteriaBuilder rechargeTypeNotIs(String rechagetype) {
            exProductSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.RECHAGE_TYPE,
                    rechagetype, new SearchOption(SearchOption.SearchLogic.must_not,SearchOption.SearchType.term)));
            return this;
        }
        // 基础运营商
        public ExProductSearchCriteriaBuilder basicOrgIdIs(String basicorgid) {
            exProductSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.BASIC_ORG,
                    basicorgid, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return this;
        }

        //类目
        public ExProductSearchCriteriaBuilder categoryIdIs(String categoryId){
            exProductSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.CATEGORY_ID,
                    categoryId, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return this;
        }
       //子类目
        public ExProductSearchCriteriaBuilder productCategoryIdIs(String productcategoryid){
            exProductSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.PRODUCT_CATEGORY_ID,
                    productcategoryid, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return this;
        }
        //属性值
        public ExProductSearchCriteriaBuilder attrValueLike(String attrValue){
            exProductSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.ATTR_VALUE,
                    attrValue, new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.term)));
            return this;
        }
        
        //属性ID
        public ExProductSearchCriteriaBuilder attrID(String attrValue){
            exProductSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.ATTR_ID,
                    attrValue, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return this;
        }
        
        //属性值定义ID
        public ExProductSearchCriteriaBuilder attrValueDefID(String attrValueDefId){
            exProductSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.ATTRVALUE_DEF_ID,
                    attrValueDefId, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return this;
        }
        //租户ID
        public ExProductSearchCriteriaBuilder tenantID(String tenantId){
            exProductSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.TENANT_ID,
                    tenantId, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return this;
        }
        // 排序
        public ExProductSearchCriteriaBuilder addOrderBy(String orderByField, SortOrder sort) {
            exProductSearchCriteria.sortFields.add(new Sort(orderByField, sort));
            return this;
        }

        // 排序，默认降序排，字段名称在Constants类中
        public ExProductSearchCriteriaBuilder addOrderBy(String orderByField) {
            return addOrderBy(orderByField, SortOrder.DESC);
        }

        // 开始的个数
        public ExProductSearchCriteriaBuilder startSize(int startSize) {
            exProductSearchCriteria.startSize = startSize;
            return this;
        }
       

        // 最大查询个数
        public ExProductSearchCriteriaBuilder maxSearchSize(int maxSearchSize) {
            exProductSearchCriteria.maxSearchSize = maxSearchSize;
            return this;
        }

        public ExProductSearchCriteria build() {
            return exProductSearchCriteria;
        }

    }

    public List<SearchCriteria> getSearchfieldVos() {
        return searchfieldVos;
    }

    public List<Sort> getSortFields() {
        return sortFields;
    }

    public int getMaxSearchSize() {
        return maxSearchSize;
    }

    public int getStartSize() {
        return startSize;
    }
}
