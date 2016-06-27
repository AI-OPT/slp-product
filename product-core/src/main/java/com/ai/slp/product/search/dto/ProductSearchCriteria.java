package com.ai.slp.product.search.dto;

import com.ai.paas.ipaas.search.vo.SearchOption;
import com.ai.paas.ipaas.search.vo.SearchOption.SearchLogic;
import com.ai.paas.ipaas.search.vo.SearchOption.SearchType;
import com.ai.paas.ipaas.search.vo.SearchfieldVo;
import com.ai.paas.ipaas.search.vo.SortField;
import com.ai.slp.product.constants.SearchFieldConfConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xin on 16-5-25.
 */
public class ProductSearchCriteria {

    private List<SearchfieldVo> searchfieldVos;
    private List<SortField> sortFields = new ArrayList<SortField>();
    //private String orderByField = SearchMetaFieldConfig.SALE_NUM;
    //private SortType sortType = SortType.DESC;
    private int maxSearchSize = 100;
    private int startSize = 0;

    private ProductSearchCriteria() {
        searchfieldVos = new ArrayList<SearchfieldVo>();
    }

    public static class ProductSearchCriteriaBuilder {

        private ProductSearchCriteria productSearchCriteria;

        public ProductSearchCriteriaBuilder(String saleArea, String qg,UserSearchAuthority userSearchAuthority) {
            productSearchCriteria = new ProductSearchCriteria();

           /* // sale Area search filed vo
            productSearchCriteria.searchfieldVos.add(new SearchfieldVo(SearchMetaFieldConfig.SALE_AREA, saleArea,
                    new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));

            //saleArea
            productSearchCriteria.searchfieldVos.add(new SearchfieldVo(SearchMetaFieldConfig.SALE_NATIONWIDE, qg,
                    new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.term)));*/
            //地区
            SearchfieldVo searchfieldAreaVo = new SearchfieldVo();
            searchfieldAreaVo.addSubSearchFieldVo(new SearchfieldVo(SearchFieldConfConstants.SALE_AREA,saleArea, new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.term)));
            searchfieldAreaVo.addSubSearchFieldVo(new SearchfieldVo(SearchFieldConfConstants.SALE_NATIONWIDE,qg, new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.term)));
            SearchfieldVo searchfieldVo = new SearchfieldVo();
          
            
            searchfieldVo.addSubSearchFieldVo(new SearchfieldVo(SearchFieldConfConstants.USER_AUTHORITY, userSearchAuthority.getUsertype()
                    .getValue(), new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.term)));
            searchfieldVo.addSubSearchFieldVo(new SearchfieldVo(SearchFieldConfConstants.SALE_NATIONWIDE, userSearchAuthority.getUsertype()
                    .getValue(), new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.term)));

            if (userSearchAuthority.getUserId() != null && userSearchAuthority.getUserId().length() > 0) {
                searchfieldVo.addSubSearchFieldVo(new SearchfieldVo(SearchFieldConfConstants.USER_AUTHORITY, userSearchAuthority.getUserId()
                        , new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.term)));
            }
            productSearchCriteria.searchfieldVos.add(searchfieldAreaVo);
            productSearchCriteria.searchfieldVos.add(searchfieldVo);

        }

        // 单品名字
        public ProductSearchCriteriaBuilder skuNameLike(String skuName) {
            productSearchCriteria.searchfieldVos.add(new SearchfieldVo(SearchFieldConfConstants.SKUNAME,
                    skuName, new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.querystring)));
            return this;
        }
        
        //单品名称与卖点整合
        public ProductSearchCriteriaBuilder skuNameOrSellport(String skuName,String sellPort) {
            SearchfieldVo vo = new SearchfieldVo();
            vo.addSubSearchFieldVo(new SearchfieldVo(SearchFieldConfConstants.SKUNAME, skuName, new SearchOption(SearchLogic.should, SearchType.querystring)));
            vo.addSubSearchFieldVo(new SearchfieldVo(SearchFieldConfConstants.SELL_POINT, sellPort, new SearchOption(SearchLogic.should, SearchType.querystring)));
            productSearchCriteria.searchfieldVos.add(vo);
            return this;
        }
        // 单品名字
        public ProductSearchCriteriaBuilder skuNameMust(String skuName) {
            productSearchCriteria.searchfieldVos.add(new SearchfieldVo(SearchFieldConfConstants.SKUNAME,
                    skuName, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
            return this;
        }
        // 根据单品名字获取类目
        public ProductSearchCriteriaBuilder skuNameCat(String skuName) {
            productSearchCriteria.searchfieldVos.get(0).addSubSearchFieldVo(new SearchfieldVo(SearchFieldConfConstants.SKUNAME,
                    skuName, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
            return this;
        }
        // 卖点
        public ProductSearchCriteriaBuilder sellPointLike(String sellPoint) {
            productSearchCriteria.searchfieldVos.add(new SearchfieldVo(SearchFieldConfConstants.SELL_POINT,
                    sellPoint, new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.querystring)));
            return this;
        }

        // 卖点
        public ProductSearchCriteriaBuilder sellPointMust(String sellPoint) {
            productSearchCriteria.searchfieldVos.add(new SearchfieldVo(SearchFieldConfConstants.SELL_POINT,
                    sellPoint, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
            return this;
        }

        //充值方式
        public ProductSearchCriteriaBuilder rechargeTypeIs(String rechagetype) {
            productSearchCriteria.searchfieldVos.add(new SearchfieldVo(SearchFieldConfConstants.RECHAGE_TYPE,
                    rechagetype, new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.term)));
            return this;
        }
       //充值方式not in
        public ProductSearchCriteriaBuilder rechargeTypeNotIs(String rechagetype) {
            productSearchCriteria.searchfieldVos.add(new SearchfieldVo(SearchFieldConfConstants.RECHAGE_TYPE,
                    rechagetype, new SearchOption(SearchOption.SearchLogic.must_not,SearchOption.SearchType.term)));
            return this;
        }
        // 基础运营商
        public ProductSearchCriteriaBuilder basicOrgIdIs(String basicorgid) {
            productSearchCriteria.searchfieldVos.add(new SearchfieldVo(SearchFieldConfConstants.BASIC_ORG,
                    basicorgid, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return this;
        }

        //类目
        public ProductSearchCriteriaBuilder categoryIdIs(String categoryId){
            productSearchCriteria.searchfieldVos.add(new SearchfieldVo(SearchFieldConfConstants.CATEGORY_ID,
                    categoryId, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return this;
        }
       //子类目
        public ProductSearchCriteriaBuilder productCategoryIdIs(String productcategoryid){
            productSearchCriteria.searchfieldVos.add(new SearchfieldVo(SearchFieldConfConstants.PRODUCT_CATEGORY_ID,
                    productcategoryid, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return this;
        }
        //属性值
        public ProductSearchCriteriaBuilder attrValueLike(String attrValue){
            productSearchCriteria.searchfieldVos.add(new SearchfieldVo(SearchFieldConfConstants.ATTR_VALUE,
                    attrValue, new SearchOption(SearchOption.SearchLogic.should, SearchOption.SearchType.term)));
            return this;
        }
        
        //属性ID
        public ProductSearchCriteriaBuilder attrID(String attrValue){
            productSearchCriteria.searchfieldVos.add(new SearchfieldVo(SearchFieldConfConstants.ATTR_ID,
                    attrValue, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return this;
        }
        
        //属性值定义ID
        public ProductSearchCriteriaBuilder attrValueDefID(String attrValueDefId){
            productSearchCriteria.searchfieldVos.add(new SearchfieldVo(SearchFieldConfConstants.ATTRVALUE_DEF_ID,
                    attrValueDefId, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return this;
        }
        //租户ID
        public ProductSearchCriteriaBuilder tenantID(String tenantId){
            productSearchCriteria.searchfieldVos.add(new SearchfieldVo(SearchFieldConfConstants.TENANT_ID,
                    tenantId, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return this;
        }
        // 排序
        public ProductSearchCriteriaBuilder addOrderBy(String orderByField, SortType sortType) {
            productSearchCriteria.sortFields.add(new SortField(orderByField, sortType.getValue()));
            return this;
        }

        // 排序，默认降序排，字段名称在Constants类中
        public ProductSearchCriteriaBuilder addOrderBy(String orderByField) {
            return addOrderBy(orderByField, SortType.DESC);
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

    public List<SearchfieldVo> getSearchfieldVos() {
        return searchfieldVos;
    }

    public List<SortField> getSortFields() {
        return sortFields;
    }

    public int getMaxSearchSize() {
        return maxSearchSize;
    }

    public int getStartSize() {
        return startSize;
    }
}
