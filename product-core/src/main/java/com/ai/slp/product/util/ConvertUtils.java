package com.ai.slp.product.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.ai.opt.sdk.components.ses.SESClientFactory;
import com.ai.paas.ipaas.search.ISearchClient;
import com.ai.slp.product.api.product.param.ProductEditUp;
import com.ai.slp.product.api.productcomment.param.CommentPageResponse;
import com.ai.slp.product.api.productcomment.param.PictureVO;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageResponse;
import com.ai.slp.product.api.storageserver.param.StorageNumRes;
import com.ai.slp.product.api.webfront.param.ProdAttrValue;
import com.ai.slp.product.api.webfront.param.ProductImage;
import com.ai.slp.product.api.webfront.param.ProductSKUAttr;
import com.ai.slp.product.api.webfront.param.ProductSKUAttrValue;
import com.ai.slp.product.api.webfront.param.ProductSKUConfigResponse;
import com.ai.slp.product.api.webfront.param.ProductSKUResponse;
import com.ai.slp.product.constants.SearchConstants;
import com.ai.slp.product.dao.mapper.bo.ProdComment;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.search.bo.AttrInfo;
import com.ai.slp.product.search.bo.ImageInfo;
import com.ai.slp.product.search.bo.ProdAttrInfo;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.search.bo.comment.CommentInfo;
import com.ai.slp.product.search.bo.comment.CommentPictrueInfo;

/**
 * 转换缓存对象 Date: 2017年3月26日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author
 */
public class ConvertUtils {

	/**
	 * 转换SKUINfo信息
	 * @param skuInfo
	 * @return
	 * @author
	 */
	public static ProductSKUResponse convertToProductSKUResponse(SKUInfo skuInfo) {
		ProductSKUResponse response = new ProductSKUResponse();
		response.setUsableNum(skuInfo.getUsablenum());
		response.setUnit(skuInfo.getUnit());
		response.setSupplierId(skuInfo.getSupplierid());
		response.setState(skuInfo.getState());
		response.setSkuName(skuInfo.getSkuname());
		response.setSkuId(skuInfo.getSkuid());
		response.setSalePrice(skuInfo.getPrice());
		response.setSaleNum(skuInfo.getSalenum());
		response.setRechargeType(skuInfo.getRechagetype());
		response.setProductSellPoint(skuInfo.getProductsellpoint());
		response.setProdName(skuInfo.getProductname());
		response.setProductCatId(skuInfo.getProductcategoryid());
		response.setProdId(skuInfo.getProductid());
		response.setCommentNum(skuInfo.getCommentnum());
		response.setProDetailContent(skuInfo.getProdetailcontent());
		/**
		 * 商品属性
		 */
		List<ProdAttrValue> prodAttrValues = new ArrayList<>();
		for (AttrInfo attrInfo : skuInfo.getAttrinfos()) {
			ProdAttrValue prodAttrValue = new ProdAttrValue();
			prodAttrValue.setAttrvalueDefId(attrInfo.getAttrvaluedefid());
			prodAttrValue.setAttrValueName(attrInfo.getAttrvalue());
			/**
			 * 商品主图
			 */
			ProductImage productImage = new ProductImage();
			if(null!=skuInfo.getImageinfo()){
				productImage.setPicType(skuInfo.getImageinfo().getImagetype());
				productImage.setVfsId(skuInfo.getImageinfo().getVfsid());
			}
			prodAttrValue.setImage(productImage);
			prodAttrValues.add(prodAttrValue);
		}
		response.setProductAttrList(prodAttrValues);
		/**
		 * 商品图片
		 */
		List<ProductImage> productImages = new ArrayList<>();
		if(!CollectionUtils.isEmpty(skuInfo.getThumbnail())){
		for (ImageInfo imageInfo : skuInfo.getThumbnail()) {
			ProductImage productImage = new ProductImage();
			productImage.setPicType(imageInfo.getImagetype());
			productImage.setVfsId(imageInfo.getVfsid());
			productImages.add(productImage);
			}
		}
		response.setProductImageList(productImages);
		return response;
	}

	/**
	 * 转换ProductSKUConfigResponse
	 * @param skuInfo
	 * @return
	 * @author
	 */
	public static ProductSKUConfigResponse convertToProductSKUConfigResponse(SKUInfo skuInfo) {
		ProductSKUConfigResponse response = new ProductSKUConfigResponse();
		List<ProductSKUAttr> productSKUAttrs = new ArrayList<>();
		List<ProductSKUAttrValue> productSKUAttrValues = new ArrayList<>();
		for (AttrInfo attrInfo : skuInfo.getAttrinfos()) {
			ProductSKUAttr productSKUAttr = new ProductSKUAttr();
			productSKUAttr.setAttrId(DataUtils.getLongVal(attrInfo.getAttrid()));
			productSKUAttr.setAttrName(attrInfo.getAttrname());
			productSKUAttr.setAttrType(attrInfo.getAttrtype());
			/**
			 * 属性值
			 */
			for (ProdAttrInfo prodAttrInfo : skuInfo.getProdattrinfos()) {
				if (attrInfo.getAttrvaluedefid().equals(prodAttrInfo.getAttrvaluedefid())) {
					ProductSKUAttrValue productSKUAttrValue = new ProductSKUAttrValue();
					productSKUAttrValue.setAttrvalueDefId(prodAttrInfo.getAttrvaluedefid().toString());
					productSKUAttrValue.setAttrValueName(prodAttrInfo.getAttrvaluename());
					productSKUAttrValue.setAttrValueName2(prodAttrInfo.getAttrvaluename2());
					if(null!=skuInfo.getImageinfo()){
						ProductImage productImage = new ProductImage();
						productImage.setPicType(skuInfo.getImageinfo().getImagetype());
						productImage.setVfsId(skuInfo.getImageinfo().getVfsid());
						productSKUAttrValue.setImage(productImage);
					}
					productSKUAttrValues.add(productSKUAttrValue);
				}
				productSKUAttr.setAttrValueList(productSKUAttrValues);
			}
			productSKUAttrs.add(productSKUAttr);
		}
		response.setProductAttrList(productSKUAttrs);
		return response;
	}

	/**
	 * 转换ProductEditUp
	 * @param skuInfo
	 * @return
	 * @author
	 */
	public static ProductEditUp convertToProductEditUp(SKUInfo skuInfo){
		ProductEditUp productEditUp = new ProductEditUp();
		productEditUp.setOperTime(new Timestamp(skuInfo.getOpertime()));
		productEditUp.setCreateTime(new Timestamp(skuInfo.getCreatetime()));
		productEditUp.setDownTime(new Timestamp(skuInfo.getDowntime()));
		productEditUp.setUpTime(new Timestamp(skuInfo.getUptime()));
		if(null!=skuInfo.getImageinfo()){
			productEditUp.setPicType(skuInfo.getImageinfo().getImagetype());
			productEditUp.setVfsId(skuInfo.getImageinfo().getVfsid());
		}
		productEditUp.setProdId(skuInfo.getSkuid());
		productEditUp.setStandedProdId(skuInfo.getSkuid());
		productEditUp.setProdName(skuInfo.getProductname());
		productEditUp.setProductCatId(skuInfo.getProductcategoryid());
		productEditUp.setProductCatName(skuInfo.getProductcatname());
		productEditUp.setProductCatName(skuInfo.getProductcatname());
		productEditUp.setProductType(skuInfo.getProducttype());
		//productEditUp.setProPictureId(DataUtils.getLongVal(skuInfo.getImageinfo().getVfsid()));
		productEditUp.setState(skuInfo.getState());
		productEditUp.setTotalNum(skuInfo.getUsablenum());
		productEditUp.setSupplierId(skuInfo.getSupplierid());
		
		
		return productEditUp;
	}

	/**
	 * 转换ProdCommentPageResponse
	 * @param commentInfo
	 * @return
	 * @author
	 */
	public static ProdCommentPageResponse convertToProdCommentPageResponse(CommentInfo commentInfo){
		ProdCommentPageResponse response = new ProdCommentPageResponse();
		response.setCommentBody(commentInfo.getCommentbody());
		response.setCommentId(commentInfo.getCommentid());
		response.setCommentTime(new Timestamp(commentInfo.getCommenttime()));
		response.setReplyState(commentInfo.getReplaystate());
		response.setShopScoreMs(commentInfo.getShopscorems());
		response.setTenantId(commentInfo.getTenantid());
		response.setUserId(commentInfo.getUserid());
		/**
		 * 评论图
		 */
		List<PictureVO> pictureVOs = new ArrayList<>();
		if(CollectionUtils.isEmpty(commentInfo.getCommentpictrueinfos())){
		for(CommentPictrueInfo commentPictrueInfo : commentInfo.getCommentpictrueinfos()){
			PictureVO pictureVO = new PictureVO();
			pictureVO.setPicAddr(commentPictrueInfo.getPicaddr());
			pictureVO.setVfsId(commentPictrueInfo.getVfsid());
			pictureVOs.add(pictureVO);
			}
		}
		response.setPictureList(pictureVOs);
		return response;
	}
	/**
	 * 转换CommentPageResponse
	 * @param commentInfo
	 * @return
	 * @author
	 */
	public static CommentPageResponse convertToCommentPageResponse(CommentInfo commentInfo){
		CommentPageResponse response = new CommentPageResponse();
		response.setCommentBody(commentInfo.getCommentbody());
		response.setCommentId(commentInfo.getCommentid());
		response.setCommentTime(new Timestamp(commentInfo.getCommenttime()));
		response.setShopScoreMs(commentInfo.getShopscorems());
		response.setStandedProdId(commentInfo.getProductid());
		response.setShopScoreWl(commentInfo.getShopscorewl());
		response.setShopScoreFw(commentInfo.getShopscorefw());
		response.setTenantId(commentInfo.getTenantid());
		response.setUserId(commentInfo.getUserid());
		response.setProdName(commentInfo.getProductname());
		response.setOrderId(commentInfo.getOrderid());
		return response;
	}
	
	/**
	 * 转换评论信息
	 * @param prodComments
	 * @param pictureMap
	 * @return
	 * @author
	 */
	public static List<CommentInfo> convertToCommentInfo(List<ProdComment> prodComments,Map<String,List<PictureVO>> pictureMap){
		List<CommentInfo> commentInfos = new ArrayList<>();
		for (ProdComment prodComment : prodComments) {
			CommentInfo commentInfo = new CommentInfo();
			commentInfo.setCommentbody(prodComment.getCommentBody());
			commentInfo.setCommentid(prodComment.getCommentId());
			commentInfo.setCommenttime(prodComment.getCommentTime().getTime());
			commentInfo.setIspictrue(prodComment.getIsPicture());
			commentInfo.setProductid(prodComment.getProdId());
			commentInfo.setReplaystate(prodComment.getReplyState());
			if(null!=prodComment.getShopScoreFw()){
			commentInfo.setShopscorefw(prodComment.getShopScoreFw());
			}
			if(null!=prodComment.getShopScoreWl()){
			commentInfo.setShopscorewl(prodComment.getShopScoreWl());
			}
			if(null!=prodComment.getShopScoreMs()){
			commentInfo.setShopscorems(prodComment.getShopScoreMs());
			}
			commentInfo.setState(prodComment.getState());
			commentInfo.setTenantid(prodComment.getTenantId());
			commentInfo.setUserid(prodComment.getUserId());
			/**
			 * 评论图片
			 */
			List<CommentPictrueInfo> commentPictrueInfos = new ArrayList<>();
			if(pictureMap.containsKey(commentInfo.getCommentid())){
				for(PictureVO pictureVO : pictureMap.get(commentInfo.getCommentid())){
					CommentPictrueInfo commentPictrueInfo = new CommentPictrueInfo();
					commentPictrueInfo.setCommentid(commentInfo.getCommentid());
					commentPictrueInfo.setPicaddr(pictureVO.getPicAddr());
					commentPictrueInfo.setVfsid(pictureVO.getVfsId());
					commentPictrueInfos.add(commentPictrueInfo);
				}
				commentInfo.setCommentpictrueinfos(commentPictrueInfos);
			}
			commentInfos.add(commentInfo);
		}
		return commentInfos;
	}

	/**
	 * 转化商品信息
	 * @param skuInfo
	 * @return
	 * @author
	 */
	public static Product convertToProduct(SKUInfo skuInfo){
		Product product = new Product();
		product.setBasicOrgId(skuInfo.getBasicorgid());
		product.setCreateTime(new Timestamp(skuInfo.getCreatetime()));
		product.setUpTime(new Timestamp(skuInfo.getUptime()));
		product.setDownTime(new Timestamp(skuInfo.getDowntime()));
		product.setOperTime(new Timestamp(skuInfo.getOpertime()));
		product.setIsSaleNationwide(skuInfo.getSalenationwide());
		product.setMarketPrice(skuInfo.getMarketprice());
		product.setProdId(skuInfo.getProductid());;
		product.setProdName(skuInfo.getProductname());
		product.setProductSellPoint(skuInfo.getProductsellpoint());
		product.setProductType(skuInfo.getProducttype());
		product.setRechargeType(skuInfo.getRechagetype());
		product.setState(skuInfo.getState());
		product.setProDetailContent(skuInfo.getProdetailcontent());
		product.setStorageGroupId(skuInfo.getStoragegroupid());
		product.setTenantId(skuInfo.getTenantid());
		product.setUnit(skuInfo.getUnit());
		return product;
	}
	
	/**
	 * 转化商品信息
	 * @param skuInfo
	 * @return
	 * @author
	 */
	public static StorageNumRes convertToStorageNumRes(SKUInfo skuInfo){
		StorageNumRes storageNumRes = new StorageNumRes();
		storageNumRes.setProdId(skuInfo.getProductid());;
		storageNumRes.setSalePrice(skuInfo.getPrice());
		storageNumRes.setProductCatId(skuInfo.getProductcategoryid());
		storageNumRes.setSkuId(skuInfo.getSkuid());
		storageNumRes.setSkuName(skuInfo.getSkuname());
		storageNumRes.setStandedProdId(skuInfo.getProductid());
		storageNumRes.setStorageGroupId(skuInfo.getStoragegroupid());
		ImageInfo imageinfo = skuInfo.getImageinfo();
		if(null!=imageinfo){
			storageNumRes.setImagetype(imageinfo.getImagetype());
			storageNumRes.setVfsid(imageinfo.getVfsid());
		}
		if(null!=skuInfo.getBasicorgid()){
			storageNumRes.setBasicOrgId(skuInfo.getBasicorgid());
		}
		if(null!=skuInfo.getUpshelftype()){
			storageNumRes.setUpshelfType(skuInfo.getUpshelftype());
		}
		if(null!=skuInfo.getBasicorgid()){
			storageNumRes.setBasicOrgId(skuInfo.getBasicorgid());
		}
		if(null!=skuInfo.getUnit()){
			storageNumRes.setUnit(skuInfo.getUnit());
		}
		return storageNumRes;
	}
	
	/**
	 * 刷新评论信息
	 * @param prodComments
	 * @param pictureMap
	 * @return
	 * @author
	 */
	public static Integer flushCommentInfo(List<ProdComment> prodComments,Map<String,List<PictureVO>> pictureMap){
		Integer count=0;
		for (ProdComment prodComment : prodComments) {
			List<CommentInfo> commentInfos = new ArrayList<>();
			CommentInfo commentInfo = new CommentInfo();
			commentInfo.setCommentbody(prodComment.getCommentBody());
			commentInfo.setCommentid(prodComment.getCommentId());
			commentInfo.setCommenttime(prodComment.getCommentTime().getTime());
			commentInfo.setIspictrue(prodComment.getIsPicture());
			commentInfo.setProductid(prodComment.getProdId());
			commentInfo.setReplaystate(prodComment.getReplyState());
			if(null!=prodComment.getShopScoreFw()){
			commentInfo.setShopscorefw(prodComment.getShopScoreFw());
			}
			if(null!=prodComment.getShopScoreWl()){
			commentInfo.setShopscorewl(prodComment.getShopScoreWl());
			}
			if(null!=prodComment.getShopScoreMs()){
			commentInfo.setShopscorems(prodComment.getShopScoreMs());
			}
			commentInfo.setState(prodComment.getState());
			commentInfo.setTenantid(prodComment.getTenantId());
			commentInfo.setUserid(prodComment.getUserId());
			/**
			 * 评论图片
			 */
			List<CommentPictrueInfo> commentPictrueInfos = new ArrayList<>();
			if(pictureMap.containsKey(commentInfo.getCommentid())){
				for(PictureVO pictureVO : pictureMap.get(commentInfo.getCommentid())){
					CommentPictrueInfo commentPictrueInfo = new CommentPictrueInfo();
					commentPictrueInfo.setCommentid(commentInfo.getCommentid());
					commentPictrueInfo.setPicaddr(pictureVO.getPicAddr());
					commentPictrueInfo.setVfsid(pictureVO.getVfsId());
					commentPictrueInfos.add(commentPictrueInfo);
				}
				commentInfo.setCommentpictrueinfos(commentPictrueInfos);
			}
			commentInfos.add(commentInfo);
			ISearchClient searchClient = SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace_COMMENT);
			searchClient.bulkInsert(commentInfos);
			searchClient.refresh();
			count++;
		}
		return count;
	}
	
}