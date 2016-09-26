package com.ai.slp.product.search.ses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.ai.opt.sdk.components.ses.base.AbstractSES;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.util.SearchSKUInfoUtil;
import com.alibaba.fastjson.JSON;
import com.google.gson.GsonBuilder;

/**
 * 刷新搜索引擎数据
 */
@Component
public class ProductDataSES extends AbstractSES {
	private static final Logger LOG=LoggerFactory.getLogger(ProductDataSES.class);
	private static final String MAIN_SQL = "SELECT product.BASIC_ORG_ID as basicOrgId, product.UP_TIME AS UP_TIME, " +
			"product.RECHARGE_TYPE AS rechageType, product.IS_SALE_NATIONWIDE as SaleNationwide," +
			"product.TENANT_ID as tenantid, sku.SKU_ID as skuid ,sku.SKU_NAME as skuname," +
			"product.PROD_ID as producetid,product.PRODUCT_SELL_POINT as productsellpoint," +
			"product.PROD_NAME as producetname,product.PRODUCT_CAT_ID as productCatId " +
			"FROM prod_sku sku INNER JOIN product product ON sku.PROD_ID = product.PROD_ID AND product.STATE = '5'  " +
			"LIMIT ?,?";
    private static final int MAX_SIZE = 1000;
	@Override
	public void write() throws Exception {

		ExecutorService pool=null;
		try{
			 System.out.println(">>>>>>SES刷新数据开始");
			 pool = Executors.newCachedThreadPool();
		        GsonBuilder gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
		        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:context/core-context.xml");
		        DataSource dataSource = applicationContext.getBean(DataSource.class);

		        Connection connection = dataSource.getConnection();
		        List<SKUInfo> skuInfoList;
		        int start = 0;
		        while (true) {
					//获取指定条目的sku信息
		            PreparedStatement ps = connection.prepareStatement(MAIN_SQL);
		            ps.setInt(1, start);
		            ps.setInt(2, MAX_SIZE);
		            ResultSet resultSet = ps.executeQuery();
		            skuInfoList = new ArrayList<SKUInfo>();
		            while (resultSet.next()) {
		                SKUInfo skuInfo = SearchSKUInfoUtil.fillSKUMainInfo(resultSet);
		                skuInfoList.add(skuInfo);
		            }
		            for (SKUInfo skuInfo : skuInfoList) {
		                SearchSKUInfoUtil.fillSKUInfo(connection, skuInfo);
		            }

		            List<String> sesDataList = new ArrayList<String>();
		            for (SKUInfo skuInfo : skuInfoList) {
		                sesDataList.add(gsonBuilder.create().toJson(skuInfo));
		            }
		            if(sesDataList.size()>0){
		            	Thread t =new ProductDataSESTread(sesDataList);
		            	pool.execute(t);
		            	LOG.info("SESData:"+JSON.toJSONString(sesDataList));
		            }
		            if (skuInfoList.size() < MAX_SIZE) {
		                break;
		            }

		            start += MAX_SIZE;
		        }
		        System.out.println(">>>>>>SES刷新数据结束");
		}
		catch(Exception e){
			LOG.error(e.getMessage(),e); 
		}
		finally{
            if(pool!=null){
                pool.shutdown(); 
            }
            
        }
	}

}
