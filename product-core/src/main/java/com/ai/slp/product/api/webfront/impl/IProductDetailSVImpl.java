package com.ai.slp.product.api.webfront.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.slp.product.api.webfront.interfaces.IProductDetailSV;
import com.ai.slp.product.api.webfront.param.ProductImage;
import com.ai.slp.product.api.webfront.param.ProductSKUAttr;
import com.ai.slp.product.api.webfront.param.ProductSKUAttrValue;
import com.ai.slp.product.api.webfront.param.ProductSKURequest;
import com.ai.slp.product.api.webfront.param.ProductSKUResponse;
import com.alibaba.dubbo.config.annotation.Service;

@Service(validation = "true")
@Component
public class IProductDetailSVImpl implements IProductDetailSV{

	@Override
	public ProductSKUResponse queryProducSKUById(ProductSKURequest productSKURequest) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		
		ProductSKUResponse productSKUResponse = new ProductSKUResponse();
		
		productSKUResponse.setCommentNum(1000L);
		productSKUResponse.setProductId("0001");
		productSKUResponse.setProductName("小米5 全网通 标准版 ");
		productSKUResponse.setProductSellPoint("套餐更实惠，千元畅机！高通晓龙650处理器，轻薄4000mAH电池，5.5英寸1080p高清大屏！5.5英寸1080p高清大屏！");
		productSKUResponse.setSaleNum(2000L);
		productSKUResponse.setSalePrice(1999D);
		productSKUResponse.setSkuId("0001");
		productSKUResponse.setSkuName("小米5 全网通 标准版");
		productSKUResponse.setUsableNum(5000L);
		productSKUResponse.setState("1");
		//设置属性
		List<ProductSKUAttr> productAttrList=new LinkedList<ProductSKUAttr>();
		ProductSKUAttr skuAttr1=new ProductSKUAttr();
		skuAttr1.setAttrId("001");
		skuAttr1.setAttrName("选择颜色");
		List<ProductSKUAttrValue> attrValueList=new LinkedList<ProductSKUAttrValue>();
		ProductSKUAttrValue skuAttrValue1 = new ProductSKUAttrValue();
		skuAttrValue1.setAttrvalueDefId(1001L);
		skuAttrValue1.setAttrValueId("001");
		skuAttrValue1.setAttrValueName("白色");
		ProductImage image1=new ProductImage();
		image1.setIdpsId("57454f50d601800009c0b0cf");
		image1.setExtension(".jpg");
		skuAttrValue1.setImage(image1);
		attrValueList.add(skuAttrValue1);
		skuAttr1.setAttrValueList(attrValueList);
		ProductSKUAttrValue skuAttrValue2 = new ProductSKUAttrValue();
		skuAttrValue2.setAttrvalueDefId(1002L);
		skuAttrValue2.setAttrValueId("002");
		skuAttrValue2.setAttrValueName("黑色");
		ProductImage image2=new ProductImage();
		image2.setIdpsId("574551b4d601800009c0b0d9");
		image2.setExtension(".jpg");
		skuAttrValue2.setImage(image2);		
		attrValueList.add(skuAttrValue2);
		skuAttr1.setAttrValueList(attrValueList);
		ProductSKUAttrValue skuAttrValue3 = new ProductSKUAttrValue();
		skuAttrValue3.setAttrvalueDefId(1003L);
		skuAttrValue3.setAttrValueId("003");
		skuAttrValue3.setAttrValueName("紫色");
		ProductImage image3=new ProductImage();
		image3.setIdpsId("57455205d601800009c0b0df");
		image3.setExtension(".jpg");
		skuAttrValue3.setImage(image3);
		attrValueList.add(skuAttrValue3);
		skuAttr1.setAttrValueList(attrValueList);
		productAttrList.add(skuAttr1);
		
		ProductSKUAttr skuAttr2=new ProductSKUAttr();
		skuAttr2.setAttrId("002");
		skuAttr2.setAttrName("选择版本");
		List<ProductSKUAttrValue> attrValueList2=new LinkedList<ProductSKUAttrValue>();
		ProductSKUAttrValue skuAttrValue4 = new ProductSKUAttrValue();
		skuAttrValue4.setAttrvalueDefId(1004L);
		skuAttrValue4.setAttrValueId("004");
		skuAttrValue4.setAttrValueName("标准版");
		attrValueList2.add(skuAttrValue4);
		skuAttr2.setAttrValueList(attrValueList);
		ProductSKUAttrValue skuAttrValue5 = new ProductSKUAttrValue();
		skuAttrValue5.setAttrvalueDefId(1005L);
		skuAttrValue5.setAttrValueId("005");
		skuAttrValue5.setAttrValueName("高配版");
		attrValueList2.add(skuAttrValue5);
		skuAttr2.setAttrValueList(attrValueList);
		ProductSKUAttrValue skuAttrValue6 = new ProductSKUAttrValue();
		skuAttrValue6.setAttrvalueDefId(1006L);
		skuAttrValue6.setAttrValueId("006");
		skuAttrValue6.setAttrValueName("尊享版");
		attrValueList2.add(skuAttrValue6);
		skuAttr2.setAttrValueList(attrValueList2);
		productAttrList.add(skuAttr2);
		productSKUResponse.setProductAttrList(productAttrList);
		
		//设置图片
		List<ProductImage> productImageList=new LinkedList<ProductImage>();
		ProductImage productImage1 = new ProductImage();
		productImage1.setExtension(".jpg");
		productImage1.setIdpsId("57454f50d601800009c0b0cf");
		productImageList.add(productImage1 );
		ProductImage productImage2 = new ProductImage();
		productImage2.setExtension(".jpg");
		productImage2.setIdpsId("5745516fd601800009c0b0d5");
		productImageList.add(productImage2 );
		ProductImage productImage3 = new ProductImage();
		productImage3.setExtension(".jpg");
		productImage3.setIdpsId("57455191d601800009c0b0d7");
		productImageList.add(productImage3 );
		productSKUResponse.setProductImageList(productImageList);
		
		return productSKUResponse;
	}

}
