package com.ai.slp.product.api.webfront.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.slp.product.api.webfront.interfaces.IProductDetailSV;
import com.ai.slp.product.api.webfront.param.*;
import com.ai.slp.product.constants.ResultCodeConstants;
import com.ai.slp.product.service.business.interfaces.IProdSkuBusiSV;
import com.ai.slp.product.util.CommonCheckUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service(validation = "true")
@Component
public class IProductDetailSVImpl implements IProductDetailSV {
	private static Logger logger = LoggerFactory.getLogger(IProductDetailSVImpl.class);
	@Autowired
	IProdSkuBusiSV prodSkuBusiSV;

	@Override
	public ProductSKUResponse queryProducSKUById(ProductSKURequest skuReq) throws BusinessException, SystemException {
		logger.info("---= queryProducSKUById start time:"+System.currentTimeMillis());
		CommonCheckUtils.checkTenantId(skuReq.getTenantId(),"");
		if (StringUtils.isBlank(skuReq.getSkuId())
				&& StringUtils.isBlank(skuReq.getSkuAttrs())){
			throw new BusinessException("","SKU标识和SKU属性为空,无法处理");
		}
//		return prodSkuBusiSV.querySkuDetail(skuReq.getTenantId(),skuReq.getSkuId(),skuReq.getSkuAttrs());
		return demoResponse();
	}

	@Override
	public ProductSKUConfigResponse queryProductSKUConfig(ProductSKURequest skuReq)
			throws BusinessException, SystemException {
		CommonCheckUtils.checkTenantId(skuReq.getTenantId(),"");
		if (StringUtils.isBlank(skuReq.getSkuId())
				&& StringUtils.isBlank(skuReq.getSkuAttrs())){
			throw new BusinessException("","SKU标识和SKU属性为空,无法处理");
		}
//		return prodSkuBusiSV.querySkuAttr(skuReq.getTenantId(),skuReq.getSkuId(),skuReq.getSkuAttrs());
		return demoConfigResponse();
	}

	/**
	 * 示例返回值
	 * @return
     */
	private ProductSKUResponse demoResponse(){
		ProductSKUResponse productSKUResponse = new ProductSKUResponse();

		productSKUResponse.setCommentNum(1000L);
		productSKUResponse.setProdId("0001");
		productSKUResponse.setProdName("小米5 全网通 标准版 ");
		productSKUResponse.setProductSellPoint("套餐更实惠，千元畅机！高通晓龙650处理器，轻薄4000mAH电池，5.5英寸1080p高清大屏！5.5英寸1080p高清大屏！");
		productSKUResponse.setSaleNum(2000L);
		productSKUResponse.setSalePrice(1999l);
		productSKUResponse.setSkuId("0001");
		productSKUResponse.setSkuName("小米5 全网通 标准版");
		productSKUResponse.setUsableNum(5000L);
		productSKUResponse.setState("1");
		// 设置属性
		List<ProductSKUAttr> productAttrList = new LinkedList<ProductSKUAttr>();
		ProductSKUAttr skuAttr1 = new ProductSKUAttr();
		skuAttr1.setAttrId(1l);
		skuAttr1.setAttrName("选择颜色");
		List<ProductSKUAttrValue> attrValueList = new LinkedList<ProductSKUAttrValue>();
		ProductSKUAttrValue skuAttrValue1 = new ProductSKUAttrValue();
		skuAttrValue1.setAttrvalueDefId("1001");
		skuAttrValue1.setAttrValueName("白色");
		skuAttrValue1.setIsOwn(true);
		ProductImage image1 = new ProductImage();
		image1.setVfsId("57454f50d601800009c0b0cf");
		image1.setPicType(".jpg");
		skuAttrValue1.setImage(image1);
		attrValueList.add(skuAttrValue1);
		skuAttr1.setAttrValueList(attrValueList);
		ProductSKUAttrValue skuAttrValue2 = new ProductSKUAttrValue();
		skuAttrValue2.setAttrvalueDefId("1002");
		skuAttrValue2.setAttrValueName("黑色");
		skuAttrValue2.setIsOwn(false);
		ProductImage image2 = new ProductImage();
		image2.setVfsId("574551b4d601800009c0b0d9");
		image2.setPicType(".jpg");
		skuAttrValue2.setImage(image2);
		attrValueList.add(skuAttrValue2);
		skuAttr1.setAttrValueList(attrValueList);
		ProductSKUAttrValue skuAttrValue3 = new ProductSKUAttrValue();
		skuAttrValue3.setAttrvalueDefId("1002");
		skuAttrValue3.setAttrValueName("紫色");
		skuAttrValue3.setIsOwn(false);
		ProductImage image3 = new ProductImage();
		image3.setVfsId("57455205d601800009c0b0df");
		image3.setPicType(".jpg");
		skuAttrValue3.setImage(image3);
		attrValueList.add(skuAttrValue3);
		skuAttr1.setAttrValueList(attrValueList);
		productAttrList.add(skuAttr1);

		ProductSKUAttr skuAttr2 = new ProductSKUAttr();
		skuAttr2.setAttrId(2l);
		skuAttr2.setAttrName("选择版本");
		List<ProductSKUAttrValue> attrValueList2 = new LinkedList<ProductSKUAttrValue>();
		ProductSKUAttrValue skuAttrValue4 = new ProductSKUAttrValue();
		skuAttrValue4.setAttrvalueDefId("1004");
		skuAttrValue4.setAttrValueName("标准版");
		skuAttrValue4.setIsOwn(false);
		attrValueList2.add(skuAttrValue4);
		skuAttr2.setAttrValueList(attrValueList);
		ProductSKUAttrValue skuAttrValue5 = new ProductSKUAttrValue();
		skuAttrValue5.setAttrvalueDefId("1005");
		skuAttrValue5.setAttrValueName("高配版");
		skuAttrValue5.setIsOwn(true);
		attrValueList2.add(skuAttrValue5);
		skuAttr2.setAttrValueList(attrValueList);
		ProductSKUAttrValue skuAttrValue6 = new ProductSKUAttrValue();
		skuAttrValue6.setAttrvalueDefId("1006");
		skuAttrValue6.setAttrValueName("尊享版");
		skuAttrValue6.setIsOwn(false);
		attrValueList2.add(skuAttrValue6);
		skuAttr2.setAttrValueList(attrValueList2);
		productAttrList.add(skuAttr2);
		productSKUResponse.setProductAttrList(productAttrList);

		// 设置图片
		List<ProductImage> productImageList = new LinkedList<ProductImage>();
		ProductImage productImage1 = new ProductImage();
		productImage1.setPicType(".jpg");
		productImage1.setVfsId("57454f50d601800009c0b0cf");
		productImageList.add(productImage1);
		ProductImage productImage2 = new ProductImage();
		productImage2.setPicType(".jpg");
		productImage2.setVfsId("5745516fd601800009c0b0d5");
		productImageList.add(productImage2);
		ProductImage productImage3 = new ProductImage();
		productImage3.setPicType(".jpg");
		productImage3.setVfsId("57455191d601800009c0b0d7");
		productImageList.add(productImage3);
		productSKUResponse.setProductImageList(productImageList);

		ResponseHeader responseHeader = new ResponseHeader(true, ResultCodeConstants.SUCCESS_CODE, "查询成功");
		productSKUResponse.setResponseHeader(responseHeader);
		return productSKUResponse;
	}

	private ProductSKUConfigResponse demoConfigResponse(){
		ProductSKUConfigResponse ProductSKUConfigResponse = new ProductSKUConfigResponse();
		ResponseHeader responseHeader = new ResponseHeader(true, ResultCodeConstants.SUCCESS_CODE, "查询成功");
		ProductSKUConfigResponse.setResponseHeader(responseHeader);
		List<ProductSKUAttr> configParamterList = new LinkedList<ProductSKUAttr>();
		String[] keyArray = new String[] { "品牌", "型号", "颜色", "上市年份", "输入方式", "智能机", "操作系统版本", "CPU品牌", "CPU型号", "CPU频率" };
		String[] valueArray = new String[] { "小米（MI）", "小米手机 5", "白色", "2016年", "触控", "是", "MIUI", "Qualcomm 骁龙", "骁龙820", "最高主频 1.8GHz" };
		for (int i = 0; i < keyArray.length; i++) {
			ProductSKUAttr configParamter = new ProductSKUAttr();
			configParamter.setAttrId(new Long(i));
			configParamter.setAttrName(keyArray[i]);
			configParamter.setAttrValueList(new ArrayList<ProductSKUAttrValue>());
			ProductSKUAttrValue attrValue = new ProductSKUAttrValue();
			attrValue.setAttrvalueDefId(Integer.toString(i+5));
			attrValue.setAttrValueName2(valueArray[i]);
			configParamter.getAttrValueList().add(attrValue);
			configParamterList.add(configParamter);
		}
		ProductSKUConfigResponse.setProductAttrList(configParamterList);
		return ProductSKUConfigResponse;
	}
}
