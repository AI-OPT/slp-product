package com.ai.slp.product.api.product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.components.idps.IDPSClientFactory;
import com.ai.opt.sdk.util.ImageByteUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.paas.ipaas.image.IImageClient;
import com.ai.slp.product.api.flushdata.interfaces.ICreateDataBatSV;
import com.ai.slp.product.api.flushdata.interfaces.IFlushDataSV;
import com.ai.slp.product.api.flushdata.params.CreateDataRequest;
import com.ai.slp.product.api.flushdata.params.FlushDataRequest;
import com.ai.slp.product.api.product.impl.IProductManagerSVImpl;
import com.ai.slp.product.api.product.interfaces.IProductManagerSV;
import com.ai.slp.product.api.product.param.OtherSetOfProduct;
import com.ai.slp.product.api.product.param.ProdNoKeyAttr;
import com.ai.slp.product.api.product.param.ProdPicInfo;
import com.ai.slp.product.api.product.param.ProdStateLog;
import com.ai.slp.product.api.product.param.ProductCheckParam;
import com.ai.slp.product.api.product.param.ProductEditQueryReq;
import com.ai.slp.product.api.product.param.ProductEditUp;
import com.ai.slp.product.api.product.param.ProductInfoForUpdate;
import com.ai.slp.product.api.product.param.ProductInfoQuery;
import com.ai.slp.product.api.product.param.ProductQueryInfo;
import com.ai.slp.product.constants.CommonTestConstants;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

/**
 * Created by jackieliu on 16/6/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProductManagerSVTest {
	private static final Logger logger = LoggerFactory.getLogger(IProductManagerSVImpl.class);
    @Autowired
    IProductManagerSV productManagerSV;
    @Autowired
    IFlushDataSV ifDataSV;
    @Autowired
    ICreateDataBatSV createDataBatSV;

    @Test
    public void createProductBat(){
    	CreateDataRequest request = new CreateDataRequest();
    	request.setNumber(5);
    	request.setProductCatIdStartNum("00000000000160");
    	request.setProductCatIdEndNum("00000000000161");
    	request.setProductName("法国是多少费");
    	createDataBatSV.createProductBat(request);
    }
    
    
    @Test
    public void testFlushProduct(){
    	FlushDataRequest request = new FlushDataRequest();
    	request.setPageNo(1);
    	request.setPageSize(200);
    	BaseResponse response = ifDataSV.flushProductData(request);
    	logger.info(JSON.toJSONString(response));
    }
    @Test
    public void testFlushComment(){
    	FlushDataRequest request = new FlushDataRequest();
    	request.setPageNo(1);
    	request.setPageSize(3);
    	BaseResponse response = ifDataSV.flushCommentData(request);
    	logger.info(JSON.toJSONString(response));
    }
    
    @Test
    public void queryOtherSetOfProductTest(){
        ProductInfoQuery infoQuery = new ProductInfoQuery();
        infoQuery.setTenantId("SLP");
        infoQuery.setProductId("1000000000000004");
        OtherSetOfProduct otherSet = productManagerSV.queryOtherSetOfProduct(infoQuery);
        System.out.println(otherSet.toString());
    }

    @Test
    public void queryNoKeyAttrOfProd(){
        ProductInfoQuery infoQuery = new ProductInfoQuery();
        infoQuery.setTenantId("SLP");
        infoQuery.setProductId("1000000000000004");
        ProdNoKeyAttr noKeyAttr = productManagerSV.queryNoKeyAttrOfProd(infoQuery);
        System.out.println(noKeyAttr.getAttrInfoForProdList().size());
    }

    /**
     * 上架测试
     */
    @Test
    public void changeToInSaleTest(){
        ProductInfoQuery infoQuery = new ProductInfoQuery();
 //       infoQuery.setTenantId("SLP");
        infoQuery.setTenantId("changhong");
        infoQuery.setSupplierId("-1");
//        infoQuery.setProductId("1000000000000093");
        infoQuery.setProductId("0000000000001959");
        BaseResponse response = productManagerSV.changeToInSale(infoQuery);
        ResponseHeader header = response.getResponseHeader();
        System.out.println(header!=null?header.isSuccess():false);
    }
    
    /**
     * 手动下架测试
     */
    @Test
    public void prodInStoreTest(){
        ProductInfoQuery infoQuery = new ProductInfoQuery();
        infoQuery.setTenantId("changhong");
        infoQuery.setSupplierId("-1");
        infoQuery.setProductId("1000000000000127");
        BaseResponse response = productManagerSV.changeToInStore(infoQuery);
        ResponseHeader header = response.getResponseHeader();
        System.out.println(header!=null?header.isSuccess():false);
    }
    
    /**
     * 查询待编辑
     */
    @Test
    public void queryProductEditTest(){
    	ProductEditQueryReq req = new ProductEditQueryReq();
    	req.setTenantId("changhong");
    	req.setSupplierId("-1");
    	PageInfoResponse<ProductEditUp> queryProductEdit = productManagerSV.queryProductEdit(req);
    	
        Gson gson = new Gson();
		System.out.println(gson.toJson(queryProductEdit));
    	
    }
    
    /**
     * 查询在售商品--按上架时间倒序
     * jiawen
     */
    @Test
    public void searchInSaleTest(){
    	ProductQueryInfo queryInSale = new ProductQueryInfo();
    	queryInSale.setTenantId("changhong");
    	queryInSale.setSupplierId("-1");
    	//List<String> stateList = new ArrayList<>();
		//stateList.add("5");
		//queryInSale.setUpStartTime(DateUtil.getTimestamp("2017-04-12 00:00:00", "yyyy-MM-dd HH:mm:ss"));
		//DateUtil.getTimestamp("2017-04-01 13:18:04", "yyyy-MM-dd HH:mm:ss");
		//queryInSale.setUpEndTime(DateUtil.getTimestamp("2017-04-11 13:18:04", "yyyy-MM-dd HH:mm:ss"));
		//queryInSale.setStateList(stateList);
    	PageInfoResponse<ProductEditUp> inSale = productManagerSV.searchInSale(queryInSale);
    	Gson gson = new Gson();
    	System.out.println(gson.toJson(inSale));
    	
    }
    /**
     * 查询审核商品
     * jiawen
     */
    @Test
    public void searchAuditTest(){
    	ProductQueryInfo queryInSale = new ProductQueryInfo();
    	queryInSale.setTenantId("changhong");
    	queryInSale.setSupplierId("-1");
    	List<String> stateList = new ArrayList<>();
    	stateList.add("4");
    	queryInSale.setStateList(stateList);
    	PageInfoResponse<ProductEditUp> inSale = productManagerSV.searchInSale(queryInSale);
    	Gson gson = new Gson();
    	System.out.println(gson.toJson(inSale));
    	
    }
    /**
     * 商品updateProduct
     * jiawen
     */
    @Test
    public void updateProductTest(){
    	ProductInfoForUpdate update = new ProductInfoForUpdate();
    	update.setTenantId("changhong");
    	update.setSupplierId("-1");
    	update.setProdId("0000000000000803");
    	update.setProdName("11-11");
    	update.setActiveType("1");
    	update.setProDetailContent("11111");
    	update.setIsInvoice("N");
    	update.setIsSaleNationwide("N");
    	update.setUpshelfType("1");
    	update.setAudiencesEnterprise("1");
    	update.setAudiencesPerson("-1");
    	update.setAudiencesAgents("1");
    	List<ProdPicInfo> piclist = new ArrayList<>();
    	ProdPicInfo picInfo = new ProdPicInfo();
    	picInfo.setPicType("1");
    	picInfo.setVfsId("111");
    	piclist.add(picInfo);
    	update.setProdPics(piclist);
    	update.setOperId(1l);
    	
    	List<Long> provCodes = new ArrayList<>();
    	provCodes.add(11l);
    	update.setProvCodes(provCodes);
    	
        BaseResponse product = productManagerSV.updateProduct(update);
    	Gson gson = new Gson();
    	System.out.println(gson.toJson(product));
    	
    }

    /**
     * 对商品进行审核
     */
    @Test
    public void productCheck(){
        ProductCheckParam checkParam = new ProductCheckParam();
        checkParam.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
        checkParam.setState("1");
        checkParam.setOperId(441l);
        List<String> prodIdList = new ArrayList<>();
        prodIdList.add("0000000000000176");
        checkParam.setProdIdList(prodIdList);
        BaseResponse response = productManagerSV.productCheck(checkParam);
        System.out.println(response.getResponseHeader().getIsSuccess());
    }
    
    /**
     * 查询被拒绝商品信息
     * jiawen
     */
    @Test
    public void queryProductRefuseTest(){
    	ProductInfoQuery queryReq = new ProductInfoQuery();
    	queryReq.setTenantId("changhong");
    	queryReq.setSupplierId("-1");
    	queryReq.setProductId("0000000000000134");
    	//获取最新的拒绝愿意  --  按操作时间倒序 获取第一条
		ProdStateLog refuse = productManagerSV.queryRefuseByPordId(queryReq);
    	if (StringUtil.isBlank(refuse.getProdId())) {
			System.out.println("没有记商品审核拒绝描述");
		}else{
			
			System.out.println(refuse.getRefuseDes());
		}
    } 
    
    /**
     * 上传图片测试
     */
    @Test
    public void addImgTest(){
    	//应用场景
    	String idpsns="slp-mall-web-idps";
    	//获取imageClient
    	IImageClient im = IDPSClientFactory.getImageClient(idpsns);
    	//图片本地路径
    	String filepath = "‪C:/Users/Gavin/Desktop/ce11.jpg";
    	try {
			FileInputStream fis=new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// 将路径转换为byte[]
		byte[] buff = ImageByteUtil.image2byte(filepath);
		// 上传图片，获取上传后的ID
		String idpsId = im.upLoadImage(buff, "qie.jpg");
		System.out.println("idpsId=" + idpsId);
		// 获取上传图片的URL
		System.out.println(im.getImageUrl(idpsId, ".jpg"));
		// 获取上传图片指定尺寸的URL
		System.out.println(im.getImageUrl("idpsId", ".jpg", "100x80"));
    	
    }
    
}
