package com.ai.slp.product.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.ai.slp.product.api.product.param.ProductEditUp;
import com.ai.slp.product.api.productcomment.param.PictureVO;
import com.ai.slp.product.api.productcomment.param.ProdCommentPageResponse;
import com.ai.slp.product.api.webfront.param.ProdAttrValue;
import com.ai.slp.product.api.webfront.param.ProductImage;
import com.ai.slp.product.api.webfront.param.ProductSKUAttr;
import com.ai.slp.product.api.webfront.param.ProductSKUAttrValue;
import com.ai.slp.product.api.webfront.param.ProductSKUConfigResponse;
import com.ai.slp.product.api.webfront.param.ProductSKUResponse;
import com.ai.slp.product.dao.mapper.bo.ProdComment;
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
			productImage.setPicType(skuInfo.getImageinfo().getImagetype());
			productImage.setVfsId(skuInfo.getImageinfo().getVfsid());
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

	public static ProductSKUConfigResponse convertToProductSKUConfigResponse(SKUInfo skuInfo) {
		ProductSKUConfigResponse response = new ProductSKUConfigResponse();
		List<ProductSKUAttr> productSKUAttrs = new ArrayList<>();
		List<ProductSKUAttrValue> productSKUAttrValues = new ArrayList<>();
		for (AttrInfo attrInfo : skuInfo.getAttrinfos()) {
			ProductSKUAttr productSKUAttr = new ProductSKUAttr();
			productSKUAttr.setAttrId(Long.valueOf(attrInfo.getAttrid()));
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
					ProductImage productImage = new ProductImage();
					productImage.setPicType(skuInfo.getImageinfo().getImagetype());
					productImage.setVfsId(skuInfo.getImageinfo().getVfsid());
					productSKUAttrValue.setImage(productImage);
					productSKUAttrValues.add(productSKUAttrValue);
				}
				productSKUAttr.setAttrValueList(productSKUAttrValues);
			}
			productSKUAttrs.add(productSKUAttr);
		}
		response.setProductAttrList(productSKUAttrs);
		return response;
	}

	public static ProductEditUp convertProductEditUp(SKUInfo skuInfo){
		ProductEditUp productEditUp = new ProductEditUp();
		productEditUp.setOperTime(new Timestamp(skuInfo.getOpertime()));
		productEditUp.setCreateTime(new Timestamp(skuInfo.getOpertime()));
		productEditUp.setDownTime(new Timestamp(skuInfo.getDowntime()));
		productEditUp.setPicType(skuInfo.getImageinfo().getImagetype());
		productEditUp.setProdId(skuInfo.getSkuid());
		productEditUp.setProdName(skuInfo.getProductname());
		productEditUp.setProductCatId(skuInfo.getProductcategoryid());
		productEditUp.setProductCatName(skuInfo.getProductcatname());
		productEditUp.setProductType(skuInfo.getProducttype());
		productEditUp.setProPictureId(Long.valueOf(skuInfo.getImageinfo().getVfsid()));
		productEditUp.setState(skuInfo.getState());
		productEditUp.setVfsId(skuInfo.getImageinfo().getVfsid());
		productEditUp.setTotalNum(skuInfo.getUsablenum());
		productEditUp.setSupplierId(skuInfo.getSupplierid());
		return productEditUp;
	}

	public static ProdCommentPageResponse convertProdCommentPageResponse(CommentInfo commentInfo){
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
	
	public static List<CommentInfo> convertCommentInfo(List<ProdComment> prodComments,Map<String,List<PictureVO>> pictureMap){
		List<CommentInfo> commentInfos = new ArrayList<>();
		for (ProdComment prodComment : prodComments) {
			CommentInfo commentInfo = new CommentInfo();
			commentInfo.setCommentbody(prodComment.getCommentBody());
			commentInfo.setCommentid(prodComment.getCommentId());
			commentInfo.setCommenttime(prodComment.getCommentTime().getTime());
			commentInfo.setIspictrue(prodComment.getIsPicture());
			commentInfo.setProductid(prodComment.getProdId());
			commentInfo.setReplaystate(prodComment.getReplyState());
			commentInfo.setShopscorefw(prodComment.getShopScoreFw());
			commentInfo.setShopscorewl(prodComment.getShopScoreWl());
			commentInfo.setShopscorems(prodComment.getShopScoreMs());
			commentInfo.setState(prodComment.getState());
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

}