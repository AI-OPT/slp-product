package com.ai.slp.product.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.components.ses.SESClientFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.constants.SearchConstants;
import com.ai.slp.product.dao.mapper.attach.ProdSkuInfoSes;
import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.bo.product.ProdPicture;
import com.ai.slp.product.dao.mapper.bo.product.ProdSaleAll;
import com.ai.slp.product.dao.mapper.bo.product.ProdTargetArea;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.search.bo.CategoryInfo;
import com.ai.slp.product.search.bo.ImageInfo;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.search.bo.SaleAreaInfo;
import com.ai.slp.product.service.atom.interfaces.IProdCatDefAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdPictureAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSaleAllAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdTargetAreaAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.ISkuStorageAtomSV;
import com.ai.slp.product.service.business.interfaces.INormProductBusiSV;

@Service
public class FIllESDataUtil {

	@Autowired
	IProdSkuAtomSV prodSkuAtomSV;

	@Autowired
	IProdPictureAtomSV prodPictureAtomSV;
	@Autowired
	IProdTargetAreaAtomSV prodTargetAreaAtomSV;
	@Autowired
	INormProductBusiSV normProductBusiSV;
	@Autowired
	IProductAtomSV productAtomSV;
	@Autowired
	ISkuStorageAtomSV skuStorageAtomSV;
	@Autowired
	IProdSaleAllAtomSV prodSaleAllAtomSV;
	@Autowired
	IProdAttrAtomSV prodAttrAtomSV;
	@Autowired
	IProdCatDefAtomSV prodCatDefAtomSV;

	public void fillESData(List<String> idList) {
		if (!CollectionUtils.isEmpty(idList)) {
			/**
			 * 查询数据库组装信息
			 */
			for (String id : idList) {
				List<ProdSkuInfoSes> skuInfoSesList = prodSkuAtomSV.queryOfProdForSearch(id);
				if (CollectionUtil.isEmpty(skuInfoSesList)) {
					continue;
				}
				List<SKUInfo> skuInfoList = fillSkuInfo(skuInfoSesList);
				if (!CollectionUtil.isEmpty(skuInfoList)) {
					SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace).bulkInsert(skuInfoList);
				}
			}
		}
	}

	public List<SKUInfo> fillSkuInfo(List<ProdSkuInfoSes> skuInfoSesList) {
		List<SKUInfo> skuInfoList = new ArrayList<>();
		for (ProdSkuInfoSes prodSkuInfo : skuInfoSesList) {
			SKUInfo skuInfo = new SKUInfo();
			BeanUtils.copyProperties(skuInfo, prodSkuInfo);
			Timestamp upTime = prodSkuInfo.getProdUpTime();
			skuInfo.setUptime(upTime == null ? DateUtil.getCurrentTimeMillis() : upTime.getTime());
			// 类目
			skuInfo.setCategoryinfos(new ArrayList<CategoryInfo>());
			// 设置类目名称
			ProductCat cat = prodCatDefAtomSV.selectById(prodSkuInfo.getTenantid(), prodSkuInfo.getProductcategoryid());
			if (cat != null) {
				skuInfo.setProductcatname(cat.getProductCatName());
			}

			fetchCategory(skuInfo, prodSkuInfo.getProductcategoryid());
			// 属性
			skuInfo.setAttrinfos(prodAttrAtomSV.queryAttrOfProdId(prodSkuInfo.getProductid()));
			// 销售量
			ProdSaleAll prodSaleAll = prodSaleAllAtomSV.querySaleAllOfSku(prodSkuInfo.getTenantid(),
					prodSkuInfo.getSkuid());
			skuInfo.setSalenum(prodSaleAll == null ? 0 : prodSaleAll.getSaleNum());
			// 图片
			fillSKUImageInfo(skuInfo, prodSkuInfo.getProductcategoryid(), prodSkuInfo.getProductid(),
					prodSkuInfo.getSkuid());
			// 价格
			/*
			 * skuInfo.setPrice(skuStorageAtomSV.queryPriceOfSku(
			 * prodSkuInfo.getTenantid(),prodSkuInfo.getProductid(),prodSkuInfo.
			 * getSkuid()));
			 */
			Long priceOfSku = skuStorageAtomSV.queryPriceOfSku(prodSkuInfo.getTenantid(), prodSkuInfo.getProductid(),
					prodSkuInfo.getSkuid());
			if (priceOfSku != null) {
				skuInfo.setPrice(priceOfSku);
			}
			// 市场价
			Product selectByProductId = productAtomSV.selectByProductId(prodSkuInfo.getSkuid());
			skuInfo.setMarketprice(selectByProductId.getMarketPrice() == null ? 0 : selectByProductId.getMarketPrice());
			// 是否全国销售
			skuInfo.setSalenationwide(selectByProductId.getIsSaleNationwide());
			// 发票
			skuInfo.setIsinvoice(selectByProductId.getIsInvoice());
			// 上架类型
			skuInfo.setUpshelftype(selectByProductId.getUpshelfType());
			// 图文描述
			skuInfo.setProdetailcontent(selectByProductId.getProDetailContent());
			skuInfo.setStoragegroupid(selectByProductId.getStorageGroupId());
			// 商品状态
			if (selectByProductId.getState() != null) {
				skuInfo.setState(selectByProductId.getState());
			}
			// 标准品状态
			StandedProduct standedProduct = normProductBusiSV.queryById(prodSkuInfo.getTenantid(),
					prodSkuInfo.getProductid());
			if (standedProduct.getState() != null) {
				skuInfo.setStandprodstate(standedProduct.getState());
			}
			// 标准品的producttype(实物 虚拟)
			if (standedProduct.getProductType() == null) {
				throw new BusinessException("标准品的ProductType 为null");
			}
			skuInfo.setProducttype(standedProduct.getProductType());
			// 標準品创建时间
			skuInfo.setCreatetime(standedProduct.getCreateTime().getTime());
			skuInfo.setSupplierid(standedProduct.getSupplierId());
			// 受众
			// skuInfo.setAudiences(fillSKUAudiences(prodSkuInfo.getTenantid(),prodSkuInfo.getProductid()));
			// 销售地域
			List<SaleAreaInfo> areaInfoList = new ArrayList<>();
			// 若不是全国销售,则查询销售地域
			if (ProductConstants.Product.IsSaleNationwide.NO.equals(prodSkuInfo.getSalenationwide())) {
				areaInfoList = fillSKUSaleArea(prodSkuInfo.getTenantid(), prodSkuInfo.getProductid());
			}
			skuInfo.setSaleareainfos(areaInfoList);
			skuInfoList.add(skuInfo);
		}
		return skuInfoList;
	}

	public void fillSKUImageInfo(SKUInfo skuInfo, String prodCatId, String prodId, String skuId) {
		ProdPicture prodPicture = prodPictureAtomSV.queryMainOfProd(prodId);
		ImageInfo imageInfo = prodPicture == null ? null
				: new ImageInfo(prodPicture.getPicType(), prodPicture.getVfsId());

		skuInfo.setImageinfo(imageInfo);
		// 查询该商品其他属性值的主图
		// skuInfo.setThumbnail(prodPictureAtomSV.queryAttrValOfProd(prodId));
	}

	public List<SaleAreaInfo> fillSKUSaleArea(String tenantId, String productId) {
		List<ProdTargetArea> targetAreaList = prodTargetAreaAtomSV.searchProdTargetArea(tenantId, productId);
		List<SaleAreaInfo> saleAreaInfoList = new ArrayList<>();
		if (CollectionUtil.isEmpty(targetAreaList)) {
			return saleAreaInfoList;
		}
		for (ProdTargetArea targetArea : targetAreaList) {
			saleAreaInfoList.add(new SaleAreaInfo(targetArea.getProvCode().toString()));
		}
		return saleAreaInfoList;
	}

	private void fetchCategory(SKUInfo skuInfo, String prodCatId) {
		ProductCat productCat = prodCatDefAtomSV.selectById(prodCatId);
		if (productCat == null) {
			return;
		}

		CategoryInfo categoryInfo = new CategoryInfo(productCat.getProductCatId(), productCat.getProductCatName());
		skuInfo.addCategoryInfo(categoryInfo);
		String categoryId = productCat.getParentProductCatId();

		if (!"0".equals(categoryId)) {
			fetchCategory(skuInfo, categoryId);
		} else {
			skuInfo.setRootcategorid(categoryId);
		}
	}
}
