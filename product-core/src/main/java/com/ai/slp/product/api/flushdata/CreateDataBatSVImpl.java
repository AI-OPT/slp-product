package com.ai.slp.product.api.flushdata;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.slp.product.api.flushdata.interfaces.ICreateDataBatSV;
import com.ai.slp.product.api.flushdata.params.CreateDataRequest;
import com.ai.slp.product.api.normproduct.param.AttrValRequest;
import com.ai.slp.product.api.normproduct.param.MarketPriceUpdate;
import com.ai.slp.product.api.normproduct.param.NormProdSaveRequest;
import com.ai.slp.product.api.product.interfaces.IProductManagerSV;
import com.ai.slp.product.api.product.param.ProdPicInfo;
import com.ai.slp.product.api.product.param.ProductCheckParam;
import com.ai.slp.product.api.product.param.ProductInfoForUpdate;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import com.ai.slp.product.api.storage.param.STOStorage;
import com.ai.slp.product.api.storage.param.STOStorageGroup;
import com.ai.slp.product.api.storage.param.StoGroupStatus;
import com.ai.slp.product.api.storage.param.StoNoSkuSalePrice;
import com.ai.slp.product.api.storage.param.StoNoSkuSalePriceReq;
import com.ai.slp.product.api.storage.param.StorageStatus;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.bo.StandedProdAttr;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.service.atom.interfaces.IStandedProdAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.business.interfaces.INormProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IProdSkuBusiSV;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageGroupBusiSV;
import com.ai.slp.product.service.business.interfaces.search.ISKUIndexBusiSV;
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
	IProductManagerSV productManagerSV;
	@Autowired
	IStorageGroupAtomSV storageGroupAtomSV;

	private static String productNameInit = "商品测试";
	private static String TENANT_ID = "changhong";
	private static int DEFAULTLENGTH = 14;
	//groupId与productId一致
	private static String productIdStart = "70000000000";

	@Override
	public void createProductBat(CreateDataRequest request) throws BusinessException, SystemException {
		if (null == request.getNumber()) {
			request.setNumber(20);
		}
		if (StringUtils.isEmpty(request.getProductIdStart())) {
			request.setProductIdStart(productIdStart);
		}
		if (StringUtils.isEmpty(request.getProductName())) {
			request.setProductName(productNameInit);
		}
		Long productId = Long.valueOf(request.getProductIdStart());
		/**
		 * 每条类目下有一定商品
		 */
		if (StringUtils.isEmpty(request.getProductCatIdStartNum())
				|| StringUtils.isEmpty(request.getProductCatIdEndNum())) {
			logger.error("类目不能为空");
			return;
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
					StorageGroup group = addNormProduct(productCat.toString(), request.getProductName(),groupId,standedProdAttrId,productId.toString());
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
					if (null == routeGroup.getRouteGroupId()) {
						continue;
					}
					// 地域
					addTargetAreaToList();
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
					continue;
				}
			}
		}
	}

	// 保存标准品
	public StorageGroup addNormProduct(String productCatId, String productName,String groupId,String standedProdAttrId,String productId) {
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
		/*AttrValRequest attrValRequest2 = new AttrValRequest();
		attrValRequest2.setAttrId(100001L);
		attrValRequest2.setAttrValId("100003");
		attrValRequests.add(attrValRequest2);*/
		request.setAttrValList(attrValRequests);
		request.setTenantId(TENANT_ID);
		request.setSupplierId("-1");
		
		//standedProdAttrId
		StandedProduct standedProduct = saveNormProd(request,Long.valueOf(standedProdAttrId),productId);
		// 添加一个库存组
		STOStorageGroup storageGroup = createSTOStorageGroup(request, standedProduct);
		StorageGroup group = storageGroupBusiSV.addGroupObj(storageGroup,groupId);
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

	// 保存库存
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

	// 编辑商品
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

	// 仓库
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

	// 路由组
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

	// 地域
	public void addTargetAreaToList() {
		IRouteTargetAreaSV routeTargetAreaSV = DubboConsumerFactory.getService(IRouteTargetAreaSV.class);
		AreaAddListRequest request = new AreaAddListRequest();
		List<AreaAddVo> voList = new ArrayList<AreaAddVo>();
		AreaAddVo vo = new AreaAddVo();
		vo.setOperId("1");
		vo.setProvCode("11");
		vo.setRouteItemId("0000000000001237");
		vo.setState("1");
		vo.setTenantId(TENANT_ID);
		voList.add(vo);
		request.setVoList(voList);
		routeTargetAreaSV.addTargetAreaToList(request);
	}

	// 审核
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

	// 市场价
	public void updateMarketPrice(String productId) {
		MarketPriceUpdate priceUpdate = new MarketPriceUpdate();
		priceUpdate.setTenantId(TENANT_ID);
		priceUpdate.setSupplierId("-1");
		priceUpdate.setProductId(productId);
		priceUpdate.setOperId(1L);
		priceUpdate.setMarketPrice(1);
		normProductSV.updateMarketPrice(priceUpdate);
	}

	// 成本价(仓库)
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

	// 销售价
	public void updateNoSkuStoSalePrice(StorageGroup group) {
		StoNoSkuSalePriceReq priceReq = new StoNoSkuSalePriceReq();
		priceReq.setTenantId(TENANT_ID);
		priceReq.setSupplierId("-1");
		priceReq.setOperId((long) 1);
		List<StoNoSkuSalePrice> salePrice = new ArrayList<>();
		StoNoSkuSalePrice skuSalePrice = new StoNoSkuSalePrice();
		skuSalePrice.setGroupId(group.getStorageGroupId());
		skuSalePrice.setPriorityNumber((short) 1);
		skuSalePrice.setSalePrice((long) 1);
		salePrice.add(skuSalePrice);
		priceReq.setStorageSalePrice(salePrice);
		storageBusiSV.updateNoSkuStoSalePrice(priceReq);
	}

	// 启用库存
	public void chargeStorageStatus(String storageId) {
		StorageStatus status = new StorageStatus();
		status.setTenantId(TENANT_ID);
		status.setSupplierId("-1");
		status.setOperId(1L);
		status.setStorageId(storageId);
		status.setState("1");
		storageSV.chargeStorageStatus(status);
	}

	// 启用库存组
	public void chargeStorageGroupStatus(String groupId) {
		StoGroupStatus groupStatus = new StoGroupStatus();
		groupStatus.setTenantId(TENANT_ID);
		groupStatus.setSupplierId("-1");
		groupStatus.setOperId(1L);
		groupStatus.setGroupId(groupId);
		groupStatus.setState("1");// 启用
		storageSV.chargeStorageGroupStatus(groupStatus);
	}

	// 保存标准品
	private StandedProduct saveNormProd(NormProdSaveRequest normProduct,long standedProdAttrId,String productId) {
		StandedProduct standedProduct = saveNormProdWithOutAttr(normProduct,productId);
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
			standedProdAttrAtomSV.installObj(prodAttr,standedProdAttrId);
		}
		return standedProduct;
	}

	private StandedProduct saveNormProdWithOutAttr(NormProdSaveRequest normProduct,String productId) {
		// 添加标准品
		StandedProduct standedProduct = new StandedProduct();
		BeanUtils.copyProperties(standedProduct, normProduct);
		standedProduct.setStandedProductName(normProduct.getProductName());
		// 添加标准品
		standedProductAtomSV.installObj(standedProduct,productId);
		return standedProduct;
	}

	/**
	 * 获得商品属性序列号
	 */
	private Short getProductAttrSerialNo() {
		return Short.valueOf("1");
	}

	// 创建库存组
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
