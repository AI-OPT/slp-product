package com.ai.slp.product.search;

import com.ai.opt.sdk.components.ses.SESClientFactory;
import com.ai.paas.ipaas.search.service.ISearchClient;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.search.constants.Constants;
import com.ai.slp.product.search.utils.SKUInfoUtil;
import com.google.gson.GsonBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by xin on 16-5-13.
 */
public class InitialProductData {

    private static final String MAIN_SQL = "SELECT product.BASIC_ORG_ID as basicOrgId, product.UP_TIME AS UP_TIME, product.RECHARGE_TYPE AS rechageType, product.IS_SALE_NATIONWIDE as SaleNationwide,product.TENANT_ID as tenantid, sku.SKU_ID as skuid ,sku.SKU_NAME as skuname,product.PROD_ID as producetid,product.PRODUCT_SELL_POINT as productsellpoint,product.PROD_NAME as producetname,product.PRODUCT_CAT_ID as productCatId FROM prod_sku sku INNER JOIN product product ON sku.PROD_ID = product.PROD_ID AND product.STATE = '5'  LIMIT ?,?";

    private static final int MAX_SIZE = 1000;

    public static void main(String[] args) throws SQLException, UnknownHostException {
        System.out.println(">>>>>>刷新数据开始");
        GsonBuilder gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:context/core-context.xml");
        DataSource dataSource = applicationContext.getBean(DataSource.class);

        ISearchClient searchClient = SESClientFactory.getSearchClient(Constants.SearchNameSpace);
        Connection connection = dataSource.getConnection();
        List<SKUInfo> skuInfoList;
        int start = 0;
        while (true) {
            PreparedStatement ps = connection.prepareStatement(MAIN_SQL);
            ps.setInt(1, start);
            ps.setInt(2, MAX_SIZE);
            ResultSet resultSet = ps.executeQuery();
            skuInfoList = new ArrayList<SKUInfo>();
            while (resultSet.next()) {
                SKUInfo skuInfo = SKUInfoUtil.fillSKUMainInfo(resultSet);
                skuInfoList.add(skuInfo);
            }
            for (SKUInfo skuInfo : skuInfoList) {
                SKUInfoUtil.fillSKUInfo(connection, skuInfo);
            }

            List<String> string = new ArrayList<String>();
            for (SKUInfo skuInfo : skuInfoList) {
                string.add(gsonBuilder.create().toJson(skuInfo));
            }

            searchClient.bulkInsertData(string);
            if (skuInfoList.size() < MAX_SIZE) {
                break;
            }


            start += MAX_SIZE;
        }
        System.out.println(">>>>>>刷新数据结束");
    }


}
