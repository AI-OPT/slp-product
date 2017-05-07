package com.ai.slp.product.api.flushdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.components.ses.SESClientFactory;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.paas.ipaas.search.ISearchClient;
import com.ai.slp.product.api.flushdata.interfaces.ICreateDataBatSV;
import com.ai.slp.product.api.flushdata.params.CreateCommentRequest;
import com.ai.slp.product.api.flushdata.params.CreateDataRequest;
import com.ai.slp.product.api.normproduct.param.AttrValRequest;
import com.ai.slp.product.api.normproduct.param.MarketPriceUpdate;
import com.ai.slp.product.api.normproduct.param.NormProdSaveRequest;
import com.ai.slp.product.api.product.interfaces.IProductManagerSV;
import com.ai.slp.product.api.product.param.ProdPicInfo;
import com.ai.slp.product.api.product.param.ProductCheckParam;
import com.ai.slp.product.api.product.param.ProductInfoForUpdate;
import com.ai.slp.product.api.productcomment.param.PictureVO;
import com.ai.slp.product.api.productcomment.param.ProdCommentCreateRequest;
import com.ai.slp.product.api.productcomment.param.ProdCommentVO;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import com.ai.slp.product.api.storage.param.STOStorage;
import com.ai.slp.product.api.storage.param.STOStorageGroup;
import com.ai.slp.product.api.storage.param.StoGroupStatus;
import com.ai.slp.product.api.storage.param.StoNoSkuSalePrice;
import com.ai.slp.product.api.storage.param.StoNoSkuSalePriceReq;
import com.ai.slp.product.api.storage.param.StorageStatus;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.constants.ProductCommentConstants;
import com.ai.slp.product.constants.SearchConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.bo.ProdComment;
import com.ai.slp.product.dao.mapper.bo.StandedProdAttr;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.search.bo.comment.CommentInfo;
import com.ai.slp.product.service.atom.interfaces.IStandedProdAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.business.interfaces.INormProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IProdSkuBusiSV;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageGroupBusiSV;
import com.ai.slp.product.service.business.interfaces.comment.IProdCommentBusiSV;
import com.ai.slp.product.service.business.interfaces.search.ISKUIndexBusiSV;
import com.ai.slp.product.util.ConvertUtils;
import com.ai.slp.route.api.routegroupmanage.interfaces.IRouteGroupManageSV;
import com.ai.slp.route.api.routegroupmanage.param.RouteGroupAddRequest;
import com.ai.slp.route.api.routegroupmanage.param.RouteGroupAddResponse;
import com.ai.slp.route.api.routeprodsupplymanage.interfaces.IRouteProdSupplyManageSV;
import com.ai.slp.route.api.routeprodsupplymanage.param.CostPriceUpdateListRequest;
import com.ai.slp.route.api.routeprodsupplymanage.param.CostPriceUpdateVo;
import com.ai.slp.route.api.routeprodsupplymanage.param.RouteProdResponse;
import com.ai.slp.route.api.routeprodsupplymanage.param.RouteProdSupplyAddListRequest;
import com.ai.slp.route.api.routeprodsupplymanage.param.RouteProdSupplyAddRequest;
import com.ai.slp.route.api.routetargetarea.interfaces.IRouteTargetAreaSV;
import com.ai.slp.route.api.routetargetarea.param.AreaAddListRequest;
import com.ai.slp.route.api.routetargetarea.param.AreaAddVo;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;

/**
 * 批量制造商品接口
 * Date: 2017年5月3日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author
 */
@Service
@Component
public class CreateDataBatSVImpl implements ICreateDataBatSV {

	private static final Logger logger = LoggerFactory.getLogger(CreateDataBatSVImpl.class);

	@Autowired
	INormProductBusiSV normProductSV;
	@Autowired
	IStorageGroupBusiSV storageGroupBusiSV;
	@Autowired
	IStandedProductAtomSV standedProductAtomSV;
	@Autowired
	IStandedProdAttrAtomSV standedProdAttrAtomSV;
	@Autowired
	IProductBusiSV productBusiSV;
	@Autowired
	IProdSkuBusiSV prodSkuBusiSV;
	@Autowired
	ISKUIndexBusiSV skuIndexManage;
	@Autowired
	IStorageBusiSV storageBusiSV;
	@Autowired
	IStorageSV storageSV;
	@Autowired
	IProdCommentBusiSV prodCommentBusiSV;
	@Autowired
	IProductManagerSV productManagerSV;
	@Autowired
	IStorageGroupAtomSV storageGroupAtomSV;

	private static String productNameInit = "商品测试";
	private static String TENANT_ID = "changhong";
	private static int DEFAULTLENGTH = 14;

	/**
	 * 批量制造商品
	 */
	@Override
	public void createProductBat(CreateDataRequest request) throws BusinessException, SystemException {
		if (null == request.getNumber()) {
			request.setNumber(20);
		}
		if (StringUtils.isEmpty(request.getProductName())) {
			request.setProductName(productNameInit);
		}
		/**
		 * 每条类目下有一定商品
		 */
		if (StringUtils.isEmpty(request.getProductCatIdStartNum())
				|| StringUtils.isEmpty(request.getProductCatIdEndNum())) {
			logger.error("类目不能为空");
			return;
		}
		if (!StringUtils.isEmpty(request.getProductIdStart())) {
			/**
			 * 自定义商品ID
			 */
			createCustom(request);
		} else {
			/**
			 * Sequnce维护商品ID
			 */
			createSequnce(request);
		}

	}

	/**
	 * 批量制造商品评论
	 */
	@Override
	public void createCommentBat(CreateCommentRequest request) throws BusinessException, SystemException {
		final String COMMENTID = "700000000";
		if (StringUtils.isEmpty(request.getProductIdStartNum()) || StringUtils.isEmpty(request.getProductIdEndNum())) {
			logger.error("商品ID不能为空");
			return;
		}
		if(StringUtils.isEmpty(request.getCommentIdStartNum())){
			request.setCommentIdStartNum(COMMENTID);
		}
		Long commentId = Long.valueOf(request.getCommentIdStartNum());
		for (Long productId = Long.valueOf(request.getProductIdStartNum()); productId <= Long.valueOf(request.getProductIdEndNum()); productId++) {
			for (int i = 0; i < request.getNumber(); i++) {
				ProdCommentCreateRequest prodCommentCreateRequest = new ProdCommentCreateRequest();
				prodCommentCreateRequest.setOrderId("0001");
				prodCommentCreateRequest.setUserId("3da3109cdb3f4d9e");
				prodCommentCreateRequest.setTenantId("changhong");
				List<ProdCommentVO> commentList=new LinkedList<ProdCommentVO>();
				ProdCommentVO prodComment = new ProdCommentVO();
				prodComment.setCommentBody("测试商品评价：商品太好了!!");
				prodComment.setShopScoreFw(3L);
				prodComment.setShopScoreMs(2L);
				prodComment.setShopScoreWl(3L);
				prodComment.setSkuId(productId.toString());
				prodComment.setSubOrderId("0001");
				List<PictureVO> pictureList=new LinkedList<PictureVO>();
				PictureVO pictureVO=new PictureVO();
				pictureVO.setPicAddr("https://54.223.119.228:24000/iPaas-IDPS/image/58edcb61effa640007bb7e80_80x80.png");
				pictureVO.setPicName("测试图片");
				pictureVO.setSerialNumber(1L);
				pictureVO.setVfsId("57514007d601800009c0b0f4");
				pictureList.add(pictureVO);
				prodComment.setPictureList(pictureList);
				commentList.add(prodComment);
				prodCommentCreateRequest.setCommentList(commentList);
				createProdComment(prodCommentCreateRequest,commentId.toString());
				commentId++;
			}
		}
	}

	/**
	 * 发表评价
	 */
	public void createProdComment(ProdCommentCreateRequest prodCommentCreateRequest,String commentId)
			throws BusinessException, SystemException {
		try {
			String userId = prodCommentCreateRequest.getUserId();
			List<ProdCommentVO> commentList = prodCommentCreateRequest.getCommentList();
			List<PictureVO> pictureList = new ArrayList<PictureVO>();
			List<ProdComment> prodComments = new ArrayList<>();
			Map<String, List<PictureVO>> pictureMap = new HashMap<>();
			if (!CollectionUtil.isEmpty(commentList)) {
				for (ProdCommentVO prodCommentVO : commentList) {
					ProdComment params = new ProdComment();
					params.setUserId(userId);
					BeanUtils.copyProperties(params, prodCommentVO);
					params.setProdId(prodCommentVO.getSkuId());
					params.setStandedProdId(prodCommentVO.getSkuId());
					params.setSupplierId("-1");

					// 封装图片评论
					params.setTenantId(prodCommentCreateRequest.getTenantId());
					params.setOrderId(prodCommentCreateRequest.getOrderId());
					pictureList = prodCommentVO.getPictureList();
					// 判断是否有图片
					params.setIsPicture(CollectionUtil.isEmpty(pictureList) ? ProductCommentConstants.HasPicture.NO
							: ProductCommentConstants.HasPicture.YSE);
					String commnentId = prodCommentBusiSV.createProdComment(params, pictureList,commentId);
					params.setCommentId(commnentId);
					prodComments.add(params);
					pictureMap.put(commnentId, pictureList);
					/**
					 * 加缓存
					 */
					List<CommentInfo> commentInfos = ConvertUtils.convertToCommentInfo(prodComments, pictureMap);
					ISearchClient searchClient = SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace_COMMENT);
					searchClient.bulkInsert(commentInfos);
					searchClient.refresh();
				}
			}
		} catch (Exception e) {
			logger.error("创建商品评价失败", e);
			return;
		}
	}

	/**
	 * 自定义序列号
	 * @param request
	 * @author
	 */
	public void createCustom(CreateDataRequest request) {
		Long productId = null;
		if (!StringUtils.isEmpty(request.getProductIdStart())) {
			productId = Long.valueOf(request.getProductIdStart());
		}

		for (Long productCatId = Long.valueOf(request.getProductCatIdStartNum()); productCatId <= Long
				.valueOf(request.getProductCatIdEndNum()); productCatId++) {
			int zeroFill = DEFAULTLENGTH - String.valueOf(productCatId).length();
			StringBuffer productCat = new StringBuffer();
			for (int i = 0; i < zeroFill; i++) {
				productCat.append("0");
			}
			productCat.append(String.valueOf(productCatId));
			for (int i = 0; i < request.getNumber(); i++) {
				try {
					productId++;
					String groupId = productId.toString();
					String standedProdAttrId = groupId;
					// 保存标准品
					StorageGroup group = addNormProduct(productCat.toString(), request.getProductName(), groupId,
							standedProdAttrId, productId.toString());
					if (null == group) {
						continue;
					}
					// 新建库存
					String storageId = saveStorage(group);
					// 编辑商品
					updateProduct(productId.toString(), request.getProductName());
					// 仓库
					String supplyId = addRouteProdSupplyList(productId.toString(), request.getProductName());
					// 路由组
					RouteGroupAddResponse routeGroup = insertRouteGroup(productId.toString(), request.getProductName());
					if (CommonConstants.OPERATE_FAIL.equals(routeGroup.getResponseHeader().getResultCode())) {
						continue;
					}
					// RouteItem
					List<String> routeItems = routeGroup.getRouteItemIds();
					// 地域
					addTargetAreaToList(routeItems);
					Thread.sleep(1000);
					// 价格
					// 市场价
					updateMarketPrice(productId.toString());
					// 成本价
					updateCostPrice(productId.toString(), supplyId);
					// 销售价
					updateNoSkuStoSalePrice(group);
					// 启用库存
					chargeStorageStatus(storageId);
					chargeStorageGroupStatus(group.getStorageGroupId());
					// 审核
					productCheck(productId.toString());
				} catch (Exception e) {
					logger.error("批量制造商品发生点问题,原因是:" + JSON.toJSONString(e.getStackTrace()));
					return;
				}
			}
		}
	}

	/**
	 * 商品序列号由sequnce表维护
	 * @param request
	 * @author
	 */
	public void createSequnce(CreateDataRequest request) {

		for (Long productCatId = Long.valueOf(request.getProductCatIdStartNum()); productCatId <= Long
				.valueOf(request.getProductCatIdEndNum()); productCatId++) {
			int zeroFill = DEFAULTLENGTH - String.valueOf(productCatId).length();
			StringBuffer productCat = new StringBuffer();
			for (int i = 0; i < zeroFill; i++) {
				productCat.append("0");
			}
			productCat.append(String.valueOf(productCatId));
			for (int i = 0; i < request.getNumber(); i++) {
				try {
					// 保存标准品
					StorageGroup group = addNormProduct(productCat.toString(), request.getProductName(), null, null,
							null);
					if (null == group) {
						continue;
					}
					// 新建库存
					String productId = group.getStandedProdId();
					String storageId = saveStorage(group);
					// 编辑商品
					updateProduct(productId, request.getProductName());
					// 仓库
					String supplyId = addRouteProdSupplyList(productId.toString(), request.getProductName());
					// 路由组
					RouteGroupAddResponse routeGroup = insertRouteGroup(productId.toString(), request.getProductName());
					if (CommonConstants.OPERATE_FAIL.equals(routeGroup.getResponseHeader().getResultCode())) {
						continue;
					}
					// RouteItem
					List<String> routeItems = routeGroup.getRouteItemIds();
					// 地域
					addTargetAreaToList(routeItems);
					// 价格
					// 市场价
					updateMarketPrice(productId.toString());
					// 成本价
					updateCostPrice(productId.toString(), supplyId);
					// 销售价
					updateNoSkuStoSalePrice(group);
					// 启用库存
					chargeStorageStatus(storageId);
					chargeStorageGroupStatus(group.getStorageGroupId());
					// 审核
					productCheck(productId.toString());
				} catch (Exception e) {
					logger.error("批量制造商品发生点问题,原因是:" + JSON.toJSONString(e.getStackTrace()));
					return;
				}
			}
		}
	}

	/**
	 * 保存标准品
	 * @param productCatId
	 * @param productName
	 * @param groupId
	 * @param standedProdAttrId
	 * @param productId
	 * @return
	 * @author
	 */
	public StorageGroup addNormProduct(String productCatId, String productName, String groupId,
			String standedProdAttrId, String productId) {
		NormProdSaveRequest request = new NormProdSaveRequest();
		request.setProductCatId(productCatId);
		request.setProductName(productName);
		request.setState("1");
		request.setProductType("2");
		request.setCreateId(1L);
		request.setOperId(1L);
		List<AttrValRequest> attrValRequests = new ArrayList<>();
		AttrValRequest attrValRequest = new AttrValRequest();
		attrValRequest.setAttrId(100001L);
		attrValRequest.setAttrValId("100003");
		attrValRequests.add(attrValRequest);
		/*
		 * AttrValRequest attrValRequest2 = new AttrValRequest();
		 * attrValRequest2.setAttrId(100001L);
		 * attrValRequest2.setAttrValId("100003");
		 * attrValRequests.add(attrValRequest2);
		 */
		request.setAttrValList(attrValRequests);
		request.setTenantId(TENANT_ID);
		request.setSupplierId("-1");

		// standedProdAttrId
		StandedProduct standedProduct = saveNormProd(request, standedProdAttrId, productId);
		// 添加一个库存组
		STOStorageGroup storageGroup = createSTOStorageGroup(request, standedProduct);
		StorageGroup group = storageGroupBusiSV.addGroupObj(storageGroup, groupId);
		// 添加商品,在商品属性表保存商品属性值
		List<AttrValRequest> attrValList = request.getAttrValList();
		Product product = productBusiSV.addProductWithStorageGroup(standedProduct, group, attrValList);
		// 添加SKU
		product.setTenantId(request.getTenantId());
		prodSkuBusiSV.createSkuProduct(product);
		// 将新增的商品添加至搜索引擎
		skuIndexManage.updateSKUIndex(product.getProdId(), product.getOperTime().getTime());
		return group;
	}

	/**
	 * 保存库存
	 * @param group
	 * @return
	 * @author
	 */
	public String saveStorage(StorageGroup group) {
		STOStorage stoStorage = new STOStorage();
		stoStorage.setTenantId(TENANT_ID);
		stoStorage.setSupplierId("-1");
		stoStorage.setStorageName("商品测试库存");
		stoStorage.setStorageGroupId(group.getStorageGroupId());
		stoStorage.setOperId(1L);
		stoStorage.setTotalNum(999999999L);
		stoStorage.setWarnNum(1L);
		stoStorage.setPriorityNumber((short) 1);
		String storageId = storageBusiSV.saveStorage(stoStorage);
		return storageId;
	}

	/**
	 * 编辑商品
	 * @param productId
	 * @param productName
	 * @author
	 */
	public void updateProduct(String productId, String productName) {
		ProductInfoForUpdate update = new ProductInfoForUpdate();
		update.setTenantId(TENANT_ID);
		update.setSupplierId("-1");
		update.setProdId(productId);
		update.setProdName(productName);
		update.setActiveType("1");
		update.setProDetailContent("商品详情描述");
		update.setIsInvoice("N");
		update.setIsSaleNationwide("N");
		update.setUpshelfType("1");
		update.setAudiencesEnterprise("1");
		update.setAudiencesPerson("-1");
		update.setAudiencesAgents("1");
		List<ProdPicInfo> piclist = new ArrayList<>();
		ProdPicInfo picInfo = new ProdPicInfo();
		picInfo.setPicType(".png");
		picInfo.setVfsId("58edcb61effa640007bb7e80");
		piclist.add(picInfo);
		update.setProdPics(piclist);
		update.setOperId(1L);
		List<Long> provCodes = new ArrayList<>();
		provCodes.add(11L);
		update.setProvCodes(provCodes);
		productManagerSV.updateProduct(update);
	}

	/**
	 * 仓库
	 * @param productId
	 * @param productName
	 * @return
	 * @author
	 */
	public String addRouteProdSupplyList(String productId, String productName) {
		IRouteProdSupplyManageSV routeProdSupplyManageSV = DubboConsumerFactory
				.getService(IRouteProdSupplyManageSV.class);
		RouteProdSupplyAddListRequest request = new RouteProdSupplyAddListRequest();
		List<RouteProdSupplyAddRequest> voList = new ArrayList<RouteProdSupplyAddRequest>();
		RouteProdSupplyAddRequest vo = new RouteProdSupplyAddRequest();
		vo.setTenantId(TENANT_ID);
		vo.setProdId(productId);
		vo.setOperId("1");
		vo.setRouteId("0000000000000513");
		vo.setProdName(productName);
		vo.setAmount(999999999);
		voList.add(vo);
		request.setRouteProdSupplyAddRequestList(voList);
		RouteProdResponse response = routeProdSupplyManageSV.addRouteProdSupply(request);
		return response.getSupplyId();
	}

	/**
	 * 路由组
	 * @param productId
	 * @param productName
	 * @return
	 * @author
	 */
	public RouteGroupAddResponse insertRouteGroup(String productId, String productName) {
		IRouteGroupManageSV routeGroupManageSV = DubboConsumerFactory.getService(IRouteGroupManageSV.class);
		RouteGroupAddRequest request = new RouteGroupAddRequest();
		request.setOperId(1L);
		request.setProductId(productId);
		request.setStandedProdId(productId);
		request.setTenantId(TENANT_ID);
		request.setStandedProdName(productName);
		return routeGroupManageSV.insertRouteGroup(request);
	}

	/**
	 * 地域
	 * @param routeItems
	 * @author
	 */
	public void addTargetAreaToList(List<String> routeItems) {
		IRouteTargetAreaSV routeTargetAreaSV = DubboConsumerFactory.getService(IRouteTargetAreaSV.class);
		AreaAddListRequest request = new AreaAddListRequest();
		List<AreaAddVo> voList = new ArrayList<AreaAddVo>();
		AreaAddVo vo = new AreaAddVo();
		vo.setOperId("1");
		vo.setProvCode("11");
		if (!CollectionUtil.isEmpty(routeItems)) {
			vo.setRouteItemId(routeItems.get(0));
		}
		vo.setState("1");
		vo.setTenantId(TENANT_ID);
		voList.add(vo);
		request.setVoList(voList);
		routeTargetAreaSV.addTargetAreaToList(request);
	}

	/**
	 * 审核
	 * @param productId
	 * @author
	 */
	public void productCheck(String productId) {
		ProductCheckParam checkParam = new ProductCheckParam();
		checkParam.setTenantId(TENANT_ID);
		checkParam.setState("6");
		checkParam.setOperId(1L);
		List<String> prodIdList = new ArrayList<>();
		prodIdList.add(productId);
		checkParam.setProdIdList(prodIdList);
		productManagerSV.productCheck(checkParam);
	}

	/**
	 * 市场价
	 * @param productId
	 * @author
	 */
	public void updateMarketPrice(String productId) {
		MarketPriceUpdate priceUpdate = new MarketPriceUpdate();
		priceUpdate.setTenantId(TENANT_ID);
		priceUpdate.setSupplierId("-1");
		priceUpdate.setProductId(productId);
		priceUpdate.setOperId(1L);
		priceUpdate.setMarketPrice(1);
		normProductSV.updateMarketPrice(priceUpdate);
	}

	/**
	 * 成本价(仓库)
	 * @param productId
	 * @param supplyId
	 * @author
	 */
	public void updateCostPrice(String productId, String supplyId) {
		IRouteProdSupplyManageSV routeProdSupplyManageSV = DubboConsumerFactory
				.getService(IRouteProdSupplyManageSV.class);
		CostPriceUpdateListRequest request = new CostPriceUpdateListRequest();
		List<CostPriceUpdateVo> voList = new ArrayList<CostPriceUpdateVo>();
		CostPriceUpdateVo vo = new CostPriceUpdateVo();
		vo.setRouteId("0000000000000513");
		vo.setStandedProdId(productId);
		vo.setSupplyId(supplyId);
		vo.setTenantId(TENANT_ID);
		vo.setCostPrice(1L);
		voList.add(vo);
		request.setVoList(voList);
		routeProdSupplyManageSV.updateCostPrice(request);
	}

	/**
	 * 销售价
	 * @param group
	 * @author
	 */
	public void updateNoSkuStoSalePrice(StorageGroup group) {
		StoNoSkuSalePriceReq priceReq = new StoNoSkuSalePriceReq();
		priceReq.setTenantId(TENANT_ID);
		priceReq.setSupplierId("-1");
		priceReq.setOperId((long) 1);
		List<StoNoSkuSalePrice> salePrice = new ArrayList<>();
		StoNoSkuSalePrice skuSalePrice = new StoNoSkuSalePrice();
		skuSalePrice.setGroupId(group.getStorageGroupId());
		skuSalePrice.setPriorityNumber((short) 1);
		skuSalePrice.setSalePrice((long) 5000);
		salePrice.add(skuSalePrice);
		priceReq.setStorageSalePrice(salePrice);
		storageBusiSV.updateNoSkuStoSalePrice(priceReq);
	}

	/**
	 * 启用库存
	 * @param storageId
	 * @author
	 */
	public void chargeStorageStatus(String storageId) {
		StorageStatus status = new StorageStatus();
		status.setTenantId(TENANT_ID);
		status.setSupplierId("-1");
		status.setOperId(1L);
		status.setStorageId(storageId);
		status.setState("1");
		storageSV.chargeStorageStatus(status);
	}

	/**
	 * 启用库存组
	 * @param groupId
	 * @author
	 */
	public void chargeStorageGroupStatus(String groupId) {
		StoGroupStatus groupStatus = new StoGroupStatus();
		groupStatus.setTenantId(TENANT_ID);
		groupStatus.setSupplierId("-1");
		groupStatus.setOperId(1L);
		groupStatus.setGroupId(groupId);
		groupStatus.setState("1");// 启用
		storageSV.chargeStorageGroupStatus(groupStatus);
	}

	/**
	 * 保存标准品
	 * @param normProduct
	 * @param standedProdAttrId
	 * @param productId
	 * @return
	 * @author
	 */
	private StandedProduct saveNormProd(NormProdSaveRequest normProduct, String standedProdAttrId, String productId) {
		StandedProduct standedProduct = saveNormProdWithOutAttr(normProduct, productId);
		// 添加标准品属性值
		List<AttrValRequest> attrValList = normProduct.getAttrValList();
		for (AttrValRequest attrValReq : attrValList) {
			StandedProdAttr prodAttr = new StandedProdAttr();
			BeanUtils.copyProperties(prodAttr, attrValReq);
			prodAttr.setTenantId(normProduct.getTenantId());
			prodAttr.setStandedProdId(standedProduct.getStandedProdId());
			prodAttr.setAttrvalueDefId(attrValReq.getAttrValId());
			prodAttr.setAttrValueName(attrValReq.getAttrVal());
			prodAttr.setAttrValueName2(attrValReq.getAttrVal2());
			prodAttr.setState(CommonConstants.STATE_ACTIVE);// 设置为有效
			prodAttr.setOperId(normProduct.getOperId());
			prodAttr.setOperTime(DateUtil.getSysDate());
			prodAttr.setSerialNumber(getProductAttrSerialNo());
			// 添加成功
			if (null == standedProdAttrId) {
				standedProdAttrAtomSV.installObj(prodAttr);
			} else {
				standedProdAttrAtomSV.installObj(prodAttr, Long.valueOf(standedProdAttrId));
			}
		}
		return standedProduct;
	}

	/**
	 * 保存标准品属性
	 * @param normProduct
	 * @param productId
	 * @return
	 * @author
	 */
	private StandedProduct saveNormProdWithOutAttr(NormProdSaveRequest normProduct, String productId) {
		// 添加标准品
		StandedProduct standedProduct = new StandedProduct();
		BeanUtils.copyProperties(standedProduct, normProduct);
		standedProduct.setStandedProductName(normProduct.getProductName());
		// 添加标准品
		if (null == productId) {
			standedProductAtomSV.installObj(standedProduct);
		} else {
			standedProductAtomSV.installObj(standedProduct, productId);
		}
		return standedProduct;
	}

	/**
	 * 获得商品属性序列号
	 */
	private Short getProductAttrSerialNo() {
		return Short.valueOf("1");
	}

	/**
	 * 创建库存组
	 * @param normProduct
	 * @param standedProduct
	 * @return
	 * @author
	 */
	private STOStorageGroup createSTOStorageGroup(NormProdSaveRequest normProduct, StandedProduct standedProduct) {
		STOStorageGroup storageGroup = new STOStorageGroup();
		storageGroup.setTenantId(normProduct.getTenantId());
		storageGroup.setCreateId(normProduct.getOperId());
		storageGroup.setStandedProdId(standedProduct.getStandedProdId());
		storageGroup.setSupplierId(normProduct.getSupplierId());
		storageGroup.setStorageGroupName(StorageConstants.StorageGroup.DEFAULT_NAME);
		// String groupId = storageGroupBusiSV.addGroup(storageGroup);
		return storageGroup;
	}

}
