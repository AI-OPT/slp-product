package com.ai.slp.product.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.paas.ipaas.search.vo.SearchCriteria;
import com.ai.paas.ipaas.search.vo.SearchOption;
import com.ai.paas.ipaas.util.StringUtil;
import com.ai.slp.product.api.product.param.ProductEditQueryReq;
import com.ai.slp.product.api.product.param.ProductQueryInfo;
import com.ai.slp.product.api.productcomment.param.CommentPageRequest;
import com.ai.slp.product.constants.SearchFieldConfConstants;

public class CriteriaUtils {

	public static List<SearchCriteria> commonConditions(ProductEditQueryReq productEditParam){
		List<SearchCriteria> searchfieldVos = new ArrayList<SearchCriteria>();
		/**
		 * 商品标识
		 */
		if (!StringUtil.isBlank(productEditParam.getProdId())) {
			searchfieldVos.add(new SearchCriteria(com.ai.slp.product.constants.SearchFieldConfConstants.PRODUCT_ID, productEditParam.getProdId(),new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		}
		
		/**
		 * 标准品标识
		 */
		if (!StringUtil.isBlank(productEditParam.getStandedProdId())) {
			searchfieldVos.add(new SearchCriteria(com.ai.slp.product.constants.SearchFieldConfConstants.PRODUCT_ID, productEditParam.getStandedProdId(),new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		}
		/**
		 * 商品名称
		 */
		if (!StringUtil.isBlank(productEditParam.getProdName())) {
			searchfieldVos.add(new SearchCriteria(com.ai.slp.product.constants.SearchFieldConfConstants.PRODUCT_NAME, productEditParam.getProdName(),new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		}
		/**
		 * 商品类型
		 */
		if (!StringUtil.isBlank(productEditParam.getProductType())) {
			searchfieldVos.add(new SearchCriteria(com.ai.slp.product.constants.SearchFieldConfConstants.PRODUCT_TYPE, productEditParam.getProductType(),new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		}
		/**
		 * 商品类型
		 */
		if (!StringUtil.isBlank(productEditParam.getSupplierId())) {
			searchfieldVos.add(new SearchCriteria(com.ai.slp.product.constants.SearchFieldConfConstants.SUPPLIER, productEditParam.getSupplierId(),new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		}
		/**
		 * 商品类目
		 */
		if (!StringUtil.isBlank(productEditParam.getProductCatId())) {
			searchfieldVos.add(new SearchCriteria(com.ai.slp.product.constants.SearchFieldConfConstants.PRODUCT_CATEGORY_ID, productEditParam.getProductCatId(),new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		}
		/**
		 * 商品状态
		 */
		if (!CollectionUtil.isEmpty(productEditParam.getStateList())) {
			SearchCriteria searchCriteria = new SearchCriteria();
			SearchOption option = new SearchOption();
			option.setSearchLogic(SearchOption.SearchLogic.must);
			option.setSearchType(SearchOption.SearchType.term);
			/**
			 * 转换类型
			 */
			List<Object> objects = new ArrayList<>();
			for(String state : productEditParam.getStateList()){
				objects.add((Object)state);
			}
			searchCriteria.setFieldValue( objects );
			searchCriteria.setField(SearchFieldConfConstants.STATE);
			searchCriteria.setOption(option);
			searchfieldVos.add(searchCriteria);
		}
		return searchfieldVos;
	}
	
	public static List<SearchCriteria> commonConditions(ProductQueryInfo productQueryInfo){
		List<SearchCriteria> searchfieldVos = new ArrayList<SearchCriteria>();
		/**
		 * 商品标识
		 */
		if (!StringUtil.isBlank(productQueryInfo.getProdId())) {
			searchfieldVos.add(new SearchCriteria(com.ai.slp.product.constants.SearchFieldConfConstants.PRODUCT_ID, productQueryInfo.getProdId(),new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		}
		/**
		 * 标准品标识
		 */
		if (!StringUtil.isBlank(productQueryInfo.getStandedProdId())) {
			searchfieldVos.add(new SearchCriteria(com.ai.slp.product.constants.SearchFieldConfConstants.PRODUCT_ID, productQueryInfo.getStandedProdId(),new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
			
		}
		/**
		 * 商品名称
		 */
		if (!StringUtil.isBlank(productQueryInfo.getProdName())) {
			searchfieldVos.add(new SearchCriteria(com.ai.slp.product.constants.SearchFieldConfConstants.PRODUCT_NAME, productQueryInfo.getProdName(),new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		}
		/**
		 * 商品类型
		 */
		if (!StringUtil.isBlank(productQueryInfo.getProductType())) {
			searchfieldVos.add(new SearchCriteria(com.ai.slp.product.constants.SearchFieldConfConstants.PRODUCT_TYPE, productQueryInfo.getProductType(),new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		}
		/**
		 * 商品类目
		 */
		if (!StringUtil.isBlank(productQueryInfo.getProductCatId())) {
			searchfieldVos.add(new SearchCriteria(com.ai.slp.product.constants.SearchFieldConfConstants.PRODUCT_CATEGORY_ID, productQueryInfo.getProductCatId(),new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		}
		/**
		 * 供应商
		 */
		if (!StringUtil.isBlank(productQueryInfo.getSupplierId())) {
			searchfieldVos.add(new SearchCriteria(com.ai.slp.product.constants.SearchFieldConfConstants.SUPPLIER, productQueryInfo.getSupplierId(),new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		}
		/**
		 * 商品状态
		 */
		if (!CollectionUtil.isEmpty(productQueryInfo.getStateList())) {
			SearchCriteria searchCriteria = new SearchCriteria();
			SearchOption option = new SearchOption();
			option.setSearchLogic(SearchOption.SearchLogic.must);
			option.setSearchType(SearchOption.SearchType.term);
			/**
			 * 转换类型
			 */
			List<Object> objects = new ArrayList<>();
			for(String state : productQueryInfo.getStateList()){
				objects.add((Object)state);
			}
			searchCriteria.setFieldValue( objects );
			searchCriteria.setField(SearchFieldConfConstants.STATE);
			searchCriteria.setOption(option);
			searchfieldVos.add(searchCriteria);
		}
		/**
		 * 时间
		 */
		if(null!=productQueryInfo.getUpStartTime()&&null==productQueryInfo.getUpEndTime()){
			SearchCriteria searchCriteria = new SearchCriteria();
			searchCriteria.setOption(new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.range));
			searchCriteria.setField(SearchFieldConfConstants.UP_TIME);
			searchCriteria.addFieldValue(productQueryInfo.getUpStartTime().getTime()+"");
			searchCriteria.addFieldValue(DateUtil.getSysDate().getTime()+"");
			searchfieldVos.add(searchCriteria);
		}
		if(null==productQueryInfo.getUpStartTime()&&null!=productQueryInfo.getUpEndTime()){
			SearchCriteria searchCriteria = new SearchCriteria();
			searchCriteria.setOption(new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.range));
			searchCriteria.setField(SearchFieldConfConstants.UP_TIME);
			searchCriteria.addFieldValue(DateUtil.getTimestamp("1970-01-01").getTime()+"");
			searchCriteria.addFieldValue(productQueryInfo.getUpEndTime().getTime()+"");
			searchfieldVos.add(searchCriteria);
		}
		if(null!=productQueryInfo.getUpStartTime()&&null!=productQueryInfo.getUpEndTime()){
			SearchCriteria searchCriteria = new SearchCriteria();
			searchCriteria.setOption(new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.range));
			searchCriteria.setField(SearchFieldConfConstants.UP_TIME);
			searchCriteria.addFieldValue(productQueryInfo.getUpStartTime().getTime()+"");
			searchCriteria.addFieldValue(productQueryInfo.getUpEndTime().getTime()+"");
			searchfieldVos.add(searchCriteria);
		}
		return searchfieldVos;
	}
	
	
	public static List<SearchCriteria> commonConditions(CommentPageRequest commentPageRequest){
		List<SearchCriteria> searchfieldVos = new ArrayList<SearchCriteria>();
		/**
		 * 状态
		 */
		searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.STATE, "1",new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		/**
		 * 好评
		 */
		if(null!=commentPageRequest.getShopScoreMs()){
			SearchCriteria searchCriteria = new SearchCriteria();
			searchCriteria.setField(SearchFieldConfConstants.SHOPSCORE_MS);
			if(commentPageRequest.getShopScoreMs() == 1){
				//criteria.andShopScoreMsLessThan(3L);
				searchCriteria.setOption(new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.range));
				searchCriteria.addFieldValue("0");
				searchCriteria.addFieldValue("3");
			}else if(commentPageRequest.getShopScoreMs() == 3){
				//criteria.andShopScoreMsEqualTo(3L);
				searchCriteria.setOption(new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring));
				searchCriteria.addFieldValue("3");
			}else{
				//criteria.andShopScoreMsGreaterThan(3L);
				searchCriteria.setOption(new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.range));
				searchCriteria.addFieldValue("3");
				searchCriteria.addFieldValue("10");
			}
			searchfieldVos.add(searchCriteria);
		}
		/**
		 * 时间
		 */
		if(null!=commentPageRequest.getCommentTimeBegin()&&null==commentPageRequest.getCommentTimeEnd()){
			SearchCriteria searchCriteria = new SearchCriteria();
			searchCriteria.setOption(new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.range));
			searchCriteria.setField(SearchFieldConfConstants.COMMENT_TIME);
			searchCriteria.addFieldValue(commentPageRequest.getCommentTimeBegin().getTime()+"");
			searchCriteria.addFieldValue(DateUtil.getSysDate().getTime()+"");
			searchfieldVos.add(searchCriteria);
		}
		if(null==commentPageRequest.getCommentTimeBegin()&&null!=commentPageRequest.getCommentTimeEnd()){
			SearchCriteria searchCriteria = new SearchCriteria();
			searchCriteria.setOption(new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.range));
			searchCriteria.setField(SearchFieldConfConstants.COMMENT_TIME);
			searchCriteria.addFieldValue(DateUtil.getTimestamp("1970-01-01").getTime()+"");
			searchCriteria.addFieldValue(commentPageRequest.getCommentTimeEnd().getTime()+"");
			searchfieldVos.add(searchCriteria);
		}
		if(null!=commentPageRequest.getCommentTimeBegin()&&null!=commentPageRequest.getCommentTimeEnd()){
			SearchCriteria searchCriteria = new SearchCriteria();
			searchCriteria.setOption(new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.range));
			searchCriteria.setField(SearchFieldConfConstants.COMMENT_TIME);
			searchCriteria.addFieldValue(commentPageRequest.getCommentTimeBegin().getTime()+"");
			searchCriteria.addFieldValue(commentPageRequest.getCommentTimeEnd().getTime()+"");
			searchfieldVos.add(searchCriteria);
		}
		/**
		 * 商品Id
		 */
		if(StringUtils.isNoneBlank(commentPageRequest.getStandedProdId())){
		searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.PRODUCT_ID, commentPageRequest.getStandedProdId(),new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		}
		/**
		 * 服务态度
		 */
		if(null!=commentPageRequest.getShopScoreFw()){
		searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.SHOPSCORE_FW, commentPageRequest.getShopScoreFw().toString(),new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		}
		/**
		 * 物流
		 */
		if(null!=commentPageRequest.getShopScoreWl()){
		searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.SHOPSCORE_WL, commentPageRequest.getShopScoreWl().toString(),new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		}
		/**
		 * 订单号
		 */
		if(StringUtils.isNoneBlank(commentPageRequest.getOrderId())){
		searchfieldVos.add(new SearchCriteria(SearchFieldConfConstants.ORDER_ID, commentPageRequest.getOrderId(),new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		}
		return searchfieldVos;
	}
}
