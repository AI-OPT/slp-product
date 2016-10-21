package com.ai.slp.product.search.dto;

import java.util.ArrayList;
import java.util.List;

import com.ai.paas.ipaas.search.vo.SearchCriteria;
import com.ai.paas.ipaas.search.vo.SearchOption;
import com.ai.paas.ipaas.search.vo.SearchOption.SearchLogic;
import com.ai.paas.ipaas.search.vo.SearchOption.SearchType;
import com.ai.paas.ipaas.search.vo.Sort;
import com.ai.slp.product.constants.SearchFieldConfConstants;

/**
 * Created by xin on 16-5-25.
 */
public final class ProductSearchCriteria {

    private List<SearchCriteria> searchfieldVos;
    private List<Sort> sortFields = new ArrayList<Sort>();
    private int maxSearchSize = 100;
    private int startSize = 0;

    private ProductSearchCriteria() {
        searchfieldVos = new ArrayList<SearchCriteria>();
    }

    public static class ProductSearchCriteriaBuilder {

        private ProductSearchCriteria productSearchCriteria;
        SearchCriteria searchCriteria = new SearchCriteria();

        public ProductSearchCriteriaBuilder(String saleArea, String qg,UserSearchAuthority userSearchAuthority) {
            productSearchCriteria = new ProductSearchCriteria();
            searchCriteria = new SearchCriteria();
            //地区
            SearchCriteria areaSubCriteria = new SearchCriteria(SearchFieldConfConstants.SALE_AREA,saleArea,
                    new SearchOption(SearchLogic.should, SearchType.term));
            //全国标志
            SearchCriteria areaWideSubCriteria = new SearchCriteria(SearchFieldConfConstants.SALE_NATIONWIDE,qg,
                    new SearchOption(SearchLogic.should, SearchType.term));
            //用户
            SearchCriteria userTypeSubCriteria = new SearchCriteria(
                    SearchFieldConfConstants.USER_AUTHORITY,userSearchAuthority.getUsertype().getValue(),
                    new SearchOption(SearchLogic.should, SearchType.term));

            if (userSearchAuthority.getUserId() != null && userSearchAuthority.getUserId().length() > 0) {
                SearchCriteria userIdSubCriteria = new SearchCriteria(
                        SearchFieldConfConstants.USER_AUTHORITY,userSearchAuthority.getUserId(),
                        new SearchOption(SearchLogic.should, SearchType.term));
                searchCriteria.addSubCriteria(userIdSubCriteria);
            }

            productSearchCriteria.searchfieldVos.add(userTypeSubCriteria);
            productSearchCriteria.searchfieldVos.add(areaSubCriteria);
            productSearchCriteria.searchfieldVos.add(areaWideSubCriteria);
            productSearchCriteria.searchfieldVos.add(userTypeSubCriteria);

        }

        // 单品名字
        public ProductSearchCriteriaBuilder skuNameLike(String skuName) {
            productSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.SKUNAME,
                    skuName, new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.querystring)));
            return this;
        }
        
        //单品名称与卖点整合
        public ProductSearchCriteriaBuilder skuNameOrSellport(String skuName,String sellPort) {
            SearchCriteria vo = new SearchCriteria();
            vo.addSubCriteria(new SearchCriteria(SearchFieldConfConstants.SKUNAME, skuName, new SearchOption(SearchLogic.should, SearchType.querystring)));
            vo.addSubCriteria(new SearchCriteria(SearchFieldConfConstants.SELL_POINT, sellPort, new SearchOption(SearchLogic.should, SearchType.querystring)));
            productSearchCriteria.searchfieldVos.add(vo);
            return this;
        }
        // 单品名字
        public ProductSearchCriteriaBuilder skuNameMust(String skuName) {
            productSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.SKUNAME,
                    skuName, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
            return this;
        }
        // 根据单品名字获取类目
        public ProductSearchCriteriaBuilder skuNameCat(String skuName) {
            productSearchCriteria.searchfieldVos.get(0).addSubCriteria(new SearchCriteria(SearchFieldConfConstants.SKUNAME,
                    skuName, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
            return this;
        }
        // 卖点
        public ProductSearchCriteriaBuilder sellPointLike(String sellPoint) {
            productSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.SELL_POINT,
                    sellPoint, new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.querystring)));
            return this;
        }

        // 卖点
        public ProductSearchCriteriaBuilder sellPointMust(String sellPoint) {
            productSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.SELL_POINT,
                    sellPoint, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
            return this;
        }

        //充值方式
        public ProductSearchCriteriaBuilder rechargeTypeIs(String rechagetype) {
            productSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.RECHAGE_TYPE,
                    rechagetype, new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.term)));
            return this;
        }
       //充值方式not in
        public ProductSearchCriteriaBuilder rechargeTypeNotIs(String rechagetype) {
            productSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.RECHAGE_TYPE,
                    rechagetype, new SearchOption(SearchOption.SearchLogic.must_not,SearchOption.SearchType.term)));
            return this;
        }
        // 基础运营商
        public ProductSearchCriteriaBuilder basicOrgIdIs(String basicorgid) {
            productSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.BASIC_ORG,
                    basicorgid, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return this;
        }

        //类目
        public ProductSearchCriteriaBuilder categoryIdIs(String categoryId){
            productSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.CATEGORY_ID,
                    categoryId, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return this;
        }
       //子类目
        public ProductSearchCriteriaBuilder productCategoryIdIs(String productcategoryid){
            productSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.PRODUCT_CATEGORY_ID,
                    productcategoryid, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return this;
        }
        //属性值
        public ProductSearchCriteriaBuilder attrValueLike(String attrValue){
            productSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.ATTR_VALUE,
                    attrValue, new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.term)));
            return this;
        }
        
        //属性ID
        public ProductSearchCriteriaBuilder attrID(String attrValue){
            productSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.ATTR_ID,
                    attrValue, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return this;
        }
        
        //属性值定义ID
        public ProductSearchCriteriaBuilder attrValueDefID(String attrValueDefId){
            productSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.ATTRVALUE_DEF_ID,
                    attrValueDefId, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return this;
        }
        //租户ID
        public ProductSearchCriteriaBuilder tenantID(String tenantId){
            productSearchCriteria.searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.TENANT_ID,
                    tenantId, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return this;
        }
        // 排序
        public ProductSearchCriteriaBuilder addOrderBy(String orderByField, Sort.SortOrder order) {
            productSearchCriteria.sortFields.add(new Sort(orderByField, order));
            return this;
        }

        // 排序，默认降序排，字段名称在Constants类中
        public ProductSearchCriteriaBuilder addOrderBy(String orderByField) {
            return addOrderBy(orderByField, Sort.SortOrder.DESC);
        }

        // 开始的个数
        public ProductSearchCriteriaBuilder startSize(int startSize) {
            productSearchCriteria.startSize = startSize;
            return this;
        }
       

        // 最大查询个数
        public ProductSearchCriteriaBuilder maxSearchSize(int maxSearchSize) {
            productSearchCriteria.maxSearchSize = maxSearchSize;
            return this;
        }

        public ProductSearchCriteria build() {
            return productSearchCriteria;
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
