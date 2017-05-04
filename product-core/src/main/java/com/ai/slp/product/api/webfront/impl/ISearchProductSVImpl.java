package com.ai.slp.product.api.webfront.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.paas.ipaas.search.vo.Result;
import com.ai.paas.ipaas.search.vo.SearchCriteria;
import com.ai.paas.ipaas.search.vo.SearchOption;
import com.ai.paas.ipaas.search.vo.Sort;
import com.ai.paas.ipaas.search.vo.Sort.SortOrder;
import com.ai.paas.ipaas.util.StringUtil;
import com.ai.slp.product.api.webfront.interfaces.ISearchProductSV;
import com.ai.slp.product.api.webfront.param.ProductData;
import com.ai.slp.product.api.webfront.param.ProductQueryRequest;
import com.ai.slp.product.api.webfront.param.ProductQueryResponse;
import com.ai.slp.product.constants.ProductHomeConstants;
import com.ai.slp.product.constants.SearchFieldConfConstants;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.search.dto.ProductSearchCriteria;
import com.ai.slp.product.search.dto.UserSearchAuthority;
import com.ai.slp.product.service.business.impl.search.ProductSearchImpl;
import com.ai.slp.product.service.business.interfaces.search.IProductSearch;
import com.ai.slp.product.util.ConvertImageUtil;
import com.ai.slp.product.util.InitDataUtil;
import com.ai.slp.product.util.ValidateUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

@Service(validation = "true")
@Component
public class ISearchProductSVImpl implements ISearchProductSV {

	@Override
	public ProductQueryResponse queryProductPage(ProductQueryRequest request)
			throws BusinessException, SystemException {
		// 参数校验
		ValidateUtil.validateQueryProduct(request);
		ProductQueryResponse response = new ProductQueryResponse();
		PageInfo<ProductData> pageinfo = new PageInfo<ProductData>();
		List<ProductData> results = new ArrayList<ProductData>();
		IProductSearch productSearch = new ProductSearchImpl();
		int startSize = 1;
		int maxSize = 1;
		// 最大条数设置
		if (request.getPageInfo() != null) {
			int pageNo = request.getPageInfo().getPageNo();
			int size = request.getPageInfo().getPageSize();
			if (request.getPageInfo().getPageNo() == 1) {
				startSize = 0;
			} else {
				startSize = (pageNo - 1) * size;
			}
			maxSize = size;
		}
		//排序
		List<Sort> sortList = new ArrayList<Sort>();
		List<SearchCriteria> criterias = new ArrayList<>();
		// 如果销量排序不为空
		if (!StringUtil.isBlank(request.getSaleNumOrderFlag())) {
			sortList.add(new Sort(SearchFieldConfConstants.SALE_NUM, SortOrder.DESC));
		}
		// 如果价格排序不为空
		if (!StringUtil.isBlank(request.getPriceOrderFlag())) {
			sortList.add(new Sort(SearchFieldConfConstants.PRICE, SortOrder.DESC));
		}
		// 如果地域不为空
		if (!StringUtil.isBlank(request.getAreaCode())) {
			criterias.add(new SearchCriteria(SearchFieldConfConstants.SALE_AREA, request.getAreaCode(),
					new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		}
		Result<SKUInfo> result = productSearch.searchByCriteria(criterias, startSize, maxSize, sortList);
		List<SKUInfo> skuInfos = result.getContents();
		if (!CollectionUtil.isEmpty(skuInfos)) {
			for (SKUInfo sku : skuInfos) {
				ProductData product = new ProductData();
				product.setSalePrice(sku.getPrice());
				product.setProdName(sku.getProductname());
				product.setProdId(sku.getProductid());
				product.setSkuId(sku.getSkuid());
				String imageinfo = JSON.toJSONString(sku.getImageinfo());
				if (sku.getImageinfo() != null) {
					product.setImageinfo(ConvertImageUtil.convert(imageinfo));
				}
				String images = JSON.toJSONString(sku.getThumbnail());
				if (!CollectionUtil.isEmpty(sku.getThumbnail())) {
					product.setThumbnail(ConvertImageUtil.convertThum(images));
				}
				// 添加地区、代理商、面额
				if (StringUtil.isBlank(request.getProductCatId())) {
					request.setProductCatId(ProductHomeConstants.PHONE_BILL_PRO_CAT_ID);
				}
				product.setAccountList(InitDataUtil.getAccountsOrFlow(request.getProductCatId()));
				product.setAgentList(InitDataUtil.getAgent());
				product.setAreaList(InitDataUtil.getArea());
				results.add(product);
			}
		} else {
			ProductData product = new ProductData();
			product.setAccountList(InitDataUtil.getAccountsOrFlow(ProductHomeConstants.PHONE_BILL_PRO_CAT_ID));
			product.setAgentList(InitDataUtil.getAgent());
			product.setAreaList(InitDataUtil.getArea());
			results.add(product);
		}
		pageinfo.setResult(results);
		pageinfo.setPageSize(request.getPageInfo().getPageSize());
		pageinfo.setPageNo(request.getPageInfo().getPageNo());
		pageinfo.setCount((int)result.getCount());
		response.setPageInfo(pageinfo);
		return response;
	}

	@Override
	public List<ProductData> queryHotSellProduct(ProductQueryRequest request)
			throws BusinessException, SystemException {
		// 入参校验
		ValidateUtil.validateHotProduct(request);
		List<ProductData> proList = new ArrayList<ProductData>();
		IProductSearch productSearch = new ProductSearchImpl();
		String userid = "";
		if (!StringUtil.isBlank(request.getUserId())) {
			userid = request.getUserId();
		}
		UserSearchAuthority user = new UserSearchAuthority(UserSearchAuthority.UserType.PERSONAL, userid);
		if (ProductHomeConstants.UserType.ENTERPRISE.equals(request.getUserType())) {
			user = new UserSearchAuthority(UserSearchAuthority.UserType.ENTERPRISE, userid);
		} else if (ProductHomeConstants.UserType.AGENCY.equals(request.getUserType())) {
			user = new UserSearchAuthority(UserSearchAuthority.UserType.AGENCY, userid);
		} else if (ProductHomeConstants.UserType.SUPPLY.equals(request.getUserType())) {
			user = new UserSearchAuthority(UserSearchAuthority.UserType.SUPPLY, userid);
		}
		ProductSearchCriteria productSearchCriteria;
		if (StringUtil.isBlank(request.getProductCatId())) {
			productSearchCriteria = new ProductSearchCriteria.ProductSearchCriteriaBuilder(request.getAreaCode(),
					ProductHomeConstants.TYPENATION_WIDE, user).rechargeTypeNotIs(ProductHomeConstants.FILL_TYPE)
							.tenantID(request.getTenantId()).addOrderBy(ProductHomeConstants.ORDER_SALE_NUM_NAME)
							.maxSearchSize(ProductHomeConstants.HOT_MAX_SIZE).build();
		} else {
			productSearchCriteria = new ProductSearchCriteria.ProductSearchCriteriaBuilder(request.getAreaCode(),
					ProductHomeConstants.TYPENATION_WIDE, user).rechargeTypeNotIs(ProductHomeConstants.FILL_TYPE)
							.tenantID(request.getTenantId()).categoryIdIs(request.getProductCatId())
							.addOrderBy(ProductHomeConstants.ORDER_SALE_NUM_NAME)
							.maxSearchSize(ProductHomeConstants.HOT_MAX_SIZE).build();
		}
		Result<Map<String, Object>> result = productSearch.search(productSearchCriteria);
		List<Map<String, Object>> objList = result.getContents();
		String info = JSON.toJSONString(objList);
		List<SKUInfo> skuList = JSON.parseObject(info, new TypeReference<List<SKUInfo>>() {
		});
		for (SKUInfo sku : skuList) {
			ProductData product = new ProductData();
			product.setSalePrice(sku.getPrice());
			product.setProdName(sku.getProductname());
			product.setProdId(sku.getProductid());
			product.setSkuId(sku.getSkuid());
			String imageinfo = JSON.toJSONString(sku.getImageinfo());
			if (sku.getImageinfo() != null) {
				product.setImageinfo(ConvertImageUtil.convert(imageinfo));
			}
			proList.add(product);
		}
		return proList;
	}

	@Override
	public ProductQueryResponse searchProduct(ProductQueryRequest request) throws BusinessException, SystemException {
		// 参数校验
		ValidateUtil.validateSearch(request);
		ProductQueryResponse response = new ProductQueryResponse();
		PageInfo<ProductData> pageinfo = new PageInfo<ProductData>();
		List<ProductData> results = new ArrayList<ProductData>();
		IProductSearch productSearch = new ProductSearchImpl();
		String userid = "";
		int startSize = 1;
		int maxSize = 1;
		if (!StringUtil.isBlank(request.getUserId())) {
			userid = request.getUserId();
		}
		UserSearchAuthority user = new UserSearchAuthority(UserSearchAuthority.UserType.PERSONAL, userid);
		if (ProductHomeConstants.UserType.ENTERPRISE.equals(request.getUserType())) {
			user = new UserSearchAuthority(UserSearchAuthority.UserType.ENTERPRISE, userid);
		} else if (ProductHomeConstants.UserType.AGENCY.equals(request.getUserType())) {
			user = new UserSearchAuthority(UserSearchAuthority.UserType.AGENCY, userid);
		} else if (ProductHomeConstants.UserType.SUPPLY.equals(request.getUserType())) {
			user = new UserSearchAuthority(UserSearchAuthority.UserType.SUPPLY, userid);
		}
		// 最大条数设置
		if (request.getPageInfo() != null) {
			int pageNo = request.getPageInfo().getPageNo();
			int size = request.getPageInfo().getPageSize();
			if (request.getPageInfo().getPageNo() == 1) {
				startSize = 0;
			} else {
				startSize = (pageNo - 1) * size;
			}
			maxSize = size;
		}
		ProductSearchCriteria productSearchCriteria;
		Result<Map<String, Object>> result = new Result<Map<String, Object>>();
		// 类目集合
		Result<Map<String, Long>> productCatIds = new Result<Map<String, Long>>();
		// 类目ID
		String productCatId = "";
		// 根据卖点或单品名称查询类目
		if (!StringUtil.isBlank(request.getSkuName())) {
			productSearchCriteria = new ProductSearchCriteria.ProductSearchCriteriaBuilder(request.getAreaCode(),
					ProductHomeConstants.TYPENATION_WIDE, user).tenantID(request.getTenantId())
							.skuNameLike(request.getSkuName()).build();
			productCatIds = productSearch.searchCategory(productSearchCriteria);
		}
		// 获取第一个类目
		if (!CollectionUtil.isEmpty(productCatIds.getContents())) {
			List<Map<String, Long>> proIdlist = productCatIds.getContents();
			Map<String, Long> idMap = proIdlist.get(0);
			for (String obj : idMap.keySet()) {
				productCatId = obj;
			}
		}
		if (!StringUtil.isBlank(request.getSkuName())) {
			productSearchCriteria = new ProductSearchCriteria.ProductSearchCriteriaBuilder(request.getAreaCode(),
					ProductHomeConstants.TYPENATION_WIDE, user).startSize(startSize).maxSearchSize(maxSize)
							.rechargeTypeNotIs(ProductHomeConstants.FILL_TYPE).tenantID(request.getTenantId())
							.skuNameOrSellport(request.getSkuName(), request.getSkuName()).build();
			result = productSearch.search(productSearchCriteria);
		}
		// 如果销量不为空
		if (!StringUtil.isBlank(request.getSaleNumOrderFlag()) && !StringUtil.isBlank(request.getSkuName())) {
			productSearchCriteria = new ProductSearchCriteria.ProductSearchCriteriaBuilder(request.getAreaCode(),
					ProductHomeConstants.TYPENATION_WIDE, user).startSize(startSize).maxSearchSize(maxSize)
							.rechargeTypeNotIs(ProductHomeConstants.FILL_TYPE)
							.addOrderBy(ProductHomeConstants.ORDER_SALE_NUM_NAME, SortOrder.ASC)
							.tenantID(request.getTenantId())
							.skuNameOrSellport(request.getSkuName(), request.getSkuName()).build();
			result = productSearch.search(productSearchCriteria);
		}
		// 如果价格不为空
		if (!StringUtil.isBlank(request.getPriceOrderFlag()) && !StringUtil.isBlank(request.getSkuName())) {
			if (request.getPriceOrderFlag().equals(ProductHomeConstants.ORDER_ASC_ID)) {
				productSearchCriteria = new ProductSearchCriteria.ProductSearchCriteriaBuilder(request.getAreaCode(),
						ProductHomeConstants.TYPENATION_WIDE, user).startSize(startSize).maxSearchSize(maxSize)
								.rechargeTypeNotIs(ProductHomeConstants.FILL_TYPE)
								.addOrderBy(ProductHomeConstants.ORDER_PRICE_NAME, SortOrder.ASC)
								.tenantID(request.getTenantId())
								.skuNameOrSellport(request.getSkuName(), request.getSkuName()).build();
				result = productSearch.search(productSearchCriteria);
			} else {
				productSearchCriteria = new ProductSearchCriteria.ProductSearchCriteriaBuilder(request.getAreaCode(),
						ProductHomeConstants.TYPENATION_WIDE, user).startSize(startSize).maxSearchSize(maxSize)
								.rechargeTypeNotIs(ProductHomeConstants.FILL_TYPE)
								.addOrderBy(ProductHomeConstants.ORDER_PRICE_NAME, SortOrder.DESC)
								.tenantID(request.getTenantId())
								.skuNameOrSellport(request.getSkuName(), request.getSkuName()).build();
				result = productSearch.search(productSearchCriteria);
			}

		}
		List<Map<String, Object>> reslist = result.getContents();
		String info = JSON.toJSONString(reslist);
		List<SKUInfo> skuList = JSON.parseObject(info, new TypeReference<List<SKUInfo>>() {
		});
		if (!CollectionUtil.isEmpty(skuList)) {
			for (SKUInfo sku : skuList) {
				ProductData product = new ProductData();
				product.setSalePrice(sku.getPrice());
				product.setProdName(sku.getProductname());
				product.setSkuName(sku.getSkuname());
				product.setProdId(sku.getProductid());
				product.setSkuId(sku.getSkuid());
				product.setProductCatId(productCatId);
				String imageinfo = JSON.toJSONString(sku.getImageinfo());
				if (sku.getImageinfo() != null) {
					product.setImageinfo(ConvertImageUtil.convert(imageinfo));
				}
				// product.setImageinfo(JSON.parseObject(JSON.toJSONString(sku.getImageinfo()),ProductImage.class));
				String images = JSON.toJSONString(sku.getThumbnail());
				// List<ProductImage> imageList = JSON.parseObject(images,new
				// TypeReference<List<ProductImage>>(){});
				if (!CollectionUtil.isEmpty(sku.getThumbnail())) {
					product.setThumbnail(ConvertImageUtil.convertThum(images));
				}
				// 添加地区、代理商、面额
				if (StringUtil.isBlank(productCatId)) {
					productCatId = ProductHomeConstants.PHONE_BILL_PRO_CAT_ID;
				}
				product.setAccountList(InitDataUtil.getAccountsOrFlow(productCatId));
				product.setAgentList(InitDataUtil.getAgent());
				product.setAreaList(InitDataUtil.getArea());
				results.add(product);
			}
		} else {
			ProductData product = new ProductData();
			product.setAccountList(InitDataUtil.getAccountsOrFlow(ProductHomeConstants.PHONE_BILL_PRO_CAT_ID));
			product.setAgentList(InitDataUtil.getAgent());
			product.setAreaList(InitDataUtil.getArea());
			results.add(product);
		}
		pageinfo.setResult(results);
		pageinfo.setPageSize(request.getPageInfo().getPageSize());
		pageinfo.setPageNo(request.getPageInfo().getPageNo());
		pageinfo.setCount((int)result.getCount());
		response.setPageInfo(pageinfo);
		return response;
	}

}
