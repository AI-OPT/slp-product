package com.ai.slp.product.search.dto;

import com.ai.paas.ipaas.search.vo.SearchOption;
import com.ai.paas.ipaas.search.vo.SearchfieldVo;
import com.ai.slp.product.search.constants.SearchMetaFieldConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xin on 16-5-25.
 */
public class ProductSearchCriteria {

    private List<SearchfieldVo> searchfieldVos;

    private String orderByField;
    private SortType sortType;
    private int maxSearchSize;
    private int startSize;

    private ProductSearchCriteria() {
        searchfieldVos = new ArrayList<SearchfieldVo>();
    }

    public static class ProductSearchCriteriaBuilder {

        private ProductSearchCriteria productSearchCriteria;

        public ProductSearchCriteriaBuilder(String saleArea, UserSearchAuthority userSearchAuthority) {
            productSearchCriteria = new ProductSearchCriteria();

            // sale Area search filed vo
            productSearchCriteria.searchfieldVos.add(new SearchfieldVo(SearchMetaFieldConfig.SALE_AREA, saleArea,
                    new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));

            //
            SearchfieldVo searchfieldVo = new SearchfieldVo();
            searchfieldVo.addSubSearchFieldVo(new SearchfieldVo(SearchMetaFieldConfig.USER_AUTHORITY, userSearchAuthority.getUsertype()
                    .getValue(), new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.term)));
            searchfieldVo.addSubSearchFieldVo(new SearchfieldVo(SearchMetaFieldConfig.SALE_NATIONWIDE, userSearchAuthority.getUsertype()
                    .getValue(), new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.term)));

            if (userSearchAuthority.getUserCode() != null && userSearchAuthority.getUserCode().length() > 0) {
                searchfieldVo.addSubSearchFieldVo(new SearchfieldVo(SearchMetaFieldConfig.USER_AUTHORITY, userSearchAuthority.getUserCode()
                        , new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.term)));
            }

            productSearchCriteria.searchfieldVos.add(searchfieldVo);

        }

        public ProductSearchCriteriaBuilder skuNameLike(String skuName) {
            productSearchCriteria.searchfieldVos.add(new SearchfieldVo(SearchMetaFieldConfig.SKUNAME,
                    skuName, new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.term)));
            return this;
        }


        public ProductSearchCriteriaBuilder sellPointLike(String sellPoint) {
            productSearchCriteria.searchfieldVos.add(new SearchfieldVo(SearchMetaFieldConfig.SELL_POINT,
                    sellPoint, new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.term)));
            return this;
        }

        public ProductSearchCriteriaBuilder rechagetypeIs(String rechagetype) {
            productSearchCriteria.searchfieldVos.add(new SearchfieldVo(SearchMetaFieldConfig.RECHAGE_TYPE,
                    rechagetype, new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.term)));
            return this;
        }

        public ProductSearchCriteriaBuilder basicorgidIs(String basicorgid) {
            productSearchCriteria.searchfieldVos.add(new SearchfieldVo(SearchMetaFieldConfig.BASIC_ORG,
                    basicorgid, new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.term)));
            return this;
        }

        public ProductSearchCriteriaBuilder orderBy(String orderByField) {
            return orderBy(orderByField, SortType.DESC);
        }

        public ProductSearchCriteriaBuilder categoryIs(String categoryId){
            productSearchCriteria.searchfieldVos.add(new SearchfieldVo(SearchMetaFieldConfig.CATEGORY,
                    categoryId, new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.term)));
            return this;
        }

        public ProductSearchCriteriaBuilder attrNameLike(String attrValue){
            productSearchCriteria.searchfieldVos.add(new SearchfieldVo(SearchMetaFieldConfig.ATTR_VALUE,
                    attrValue, new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.term)));
            return this;
        }

        public ProductSearchCriteriaBuilder orderBy(String orderByField, SortType sortType) {
            productSearchCriteria.orderByField = orderByField;
            productSearchCriteria.sortType = sortType;
            return this;
        }


        public ProductSearchCriteriaBuilder startSize(int startSize) {
            productSearchCriteria.startSize = startSize;
            return this;
        }

        public ProductSearchCriteriaBuilder maxSearchSize(int maxSearchSize) {
            productSearchCriteria.maxSearchSize = maxSearchSize;
            return this;
        }

        public ProductSearchCriteria build() {
            return productSearchCriteria;
        }

    }

    public List<SearchfieldVo> getSearchfieldVos() {
        return searchfieldVos;
    }

    public String getOrderByField() {
        return orderByField;
    }

    public SortType getSortType() {
        return sortType;
    }

    public int getMaxSearchSize() {
        return maxSearchSize;
    }

    public int getStartSize() {
        return startSize;
    }
}
