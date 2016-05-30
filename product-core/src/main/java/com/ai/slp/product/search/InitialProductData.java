package com.ai.slp.product.search;

import com.ai.opt.sdk.components.ses.SESClientFactory;
import com.ai.paas.ipaas.search.service.ISearchClient;
import com.ai.slp.product.search.bo.*;
import com.ai.slp.product.search.constants.Constants;
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
    private static final String FETCH_ATTR_INFO_SQL = "SELECT standed_prod_attr.ATTR_ID as attrID, standed_prod_attr.ATTR_VALUE_NAME as attrValue from product inner join standed_prod_attr on product.STANDED_PROD_ID = standed_prod_attr.STANDED_PROD_ID where product.PROD_ID = ?";
    private static final String FETCH_CATEGORY_INFO_SQL = "select PRODUCT_CAT_ID,PRODUCT_CAT_NAME,PARENT_PRODUCT_CAT_ID from product_cat a where a.PRODUCT_CAT_ID = ? ";
    private static final String FETCH_SALE_NUMBER_SQL = "select  SALE_NUM  from PROD_SALE_ALL where PROD_ID = ?";
    private static final String FETCH_IMAGE_FROM_SKU_SQL = "SELECT  PIC_TYPE, VFS_ID FROM PROD_CAT_ATTR catAttr, PROD_SKU_ATTR skuAttr, PROD_PICTURE picture WHERE catAttr.ATTR_ID = skuAttr.ATTR_ID AND skuAttr.ATTRVALUE_DEF_ID = picture.ATTRVALUE_DEF_ID AND catAttr.PRODUCT_CAT_ID = ? AND catAttr.ATTR_TYPE = '2' AND catAttr.IS_PICTURE = 'Y' AND skuAttr.SKU_ID = ? AND picture.IS_MAIN_PIC = 'Y'";
    private static final String FETCH_IMAGE_FROM_PROD_SQL = "SELECT PIC_TYPE, VFS_ID,IS_MAIN_PIC FROM PROD_PICTURE WHERE  PROD_ID = ?";
    private static final String FETCH_PRICE_SQL = "SELECT  skuStor.SALE_PRICE as SALE_PRICE FROM sku_storage skuStor, storage WHERE skuStor.STORAGE_ID = storage.STORAGE_ID AND skuStor.SKU_ID = ? AND storage.PROD_ID = ? AND skuStor.STATE = '1' AND storage.STATE LIKE '1%'";
    private static final String FETCH_AUDIENCE_SQL = "SELECT USER_TYPE,USER_ID FROM prod_audiences where PROD_ID = ? AND STATE = '1'";
    private static final String FETCH_SEAL_AREA = "select PROV_CODE from PROD_TARGET_AREA WHERE PROD_ID = ? AND STATE = '1' ";


    private static final int MAX_SIZE = 1000;

    public static void main(String[] args) throws SQLException, UnknownHostException {

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
                SKUInfo skuInfo = new SKUInfo(resultSet.getString("tenantid"), resultSet.getString("skuid"),
                        resultSet.getString("skuname"));
                skuInfo.setProductid(resultSet.getString("producetid"));
                skuInfo.setProductsellpoint(resultSet.getString("productsellpoint"));
                skuInfo.setProductname(resultSet.getString("producetname"));
                skuInfo.setProductcategoryid(resultSet.getString("productCatId"));
                skuInfo.setBasicorgid(resultSet.getString("basicOrgId"));
                skuInfo.setRechagetype(resultSet.getString("rechageType"));
                skuInfo.setSalenationwide(resultSet.getString("SaleNationwide"));
                skuInfo.setUptime(resultSet.getTimestamp("UP_TIME").getTime());
                skuInfoList.add(skuInfo);
            }


            for (SKUInfo skuInfo : skuInfoList) {
                // 属性
                ps = connection.prepareStatement(FETCH_ATTR_INFO_SQL);
                ps.setString(1, skuInfo.getProductid());
                resultSet = ps.executeQuery();
                List<AttrInfo> attrInfos = new ArrayList<AttrInfo>();
                while (resultSet.next()) {
                    AttrInfo attrInfo = new AttrInfo(resultSet.getString("attrID"));
                    attrInfo.setAttrvalue(resultSet.getString("attrValue"));
                    attrInfos.add(attrInfo);
                }
                skuInfo.setAttrInfos(attrInfos);

                //类目
                fetchCategory(skuInfo, skuInfo.getProductcategoryid(), connection);

                // 销售量
                ps = connection.prepareStatement(FETCH_SALE_NUMBER_SQL);
                ps.setString(1, skuInfo.getProductid());
                resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    skuInfo.setSalenum(resultSet.getInt("SALE_NUM"));
                }


                // 图片
                ps = connection.prepareStatement(FETCH_IMAGE_FROM_SKU_SQL);
                ps.setString(1, skuInfo.getProductcategoryid());
                ps.setString(2, skuInfo.getSkuid());
                resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    skuInfo.setImageinfo(new ImageInfo(resultSet.getString("PIC_TYPE"), resultSet.getString("VFS_ID")));
                } else {
                    ps = connection.prepareStatement(FETCH_IMAGE_FROM_PROD_SQL);
                    ps.setString(1, skuInfo.getProductid());
                    resultSet = ps.executeQuery();
                    while (resultSet.next()) {
                        if ("Y".equals(resultSet.getString("IS_MAIN_PIC"))) {
                            skuInfo.setImageinfo(new ImageInfo(resultSet.getString("PIC_TYPE"), resultSet.getString("VFS_ID")));
                        }else{
                            skuInfo.addThumbnail(new ImageInfo(resultSet.getString("PIC_TYPE"), resultSet.getString("VFS_ID")));
                        }
                    }
                }

                // 价格
                ps = connection.prepareStatement(FETCH_PRICE_SQL);
                ps.setString(1, skuInfo.getSkuid());
                ps.setString(2, skuInfo.getProductid());
                resultSet = ps.executeQuery();
                resultSet.next();
                skuInfo.setPrice(resultSet.getFloat("SALE_PRICE"));

                // 受众
                ps = connection.prepareStatement(FETCH_AUDIENCE_SQL);
                ps.setString(1, skuInfo.getProductid());
                resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    if ("-1".equals(resultSet.getString("USER_ID"))) {
                        skuInfo.addProductAudiences(new ProdAudiences(resultSet.getString("USER_TYPE")));
                    } else {
                        skuInfo.addProductAudiences(new ProdAudiences(resultSet.getString("USER_TYPE") + resultSet.getString("USER_ID")));
                    }

                }

                //销售地域
                if ("N".equals(skuInfo.getSalenationwide())) {
                    // 价格
                    ps = connection.prepareStatement(FETCH_SEAL_AREA);
                    ps.setString(1, skuInfo.getProductid());
                    resultSet = ps.executeQuery();
                    while (resultSet.next()) {
                        skuInfo.getSaleareainfos().add(new SaleAreaInfo(resultSet.getString("PROV_CODE")));
                    }
                }

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
    }

    private static void fetchCategory(SKUInfo skuInfo, String productCategoryId, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(FETCH_CATEGORY_INFO_SQL);
        ps.setString(1, productCategoryId);
        ResultSet resultSet = ps.executeQuery();
        if (!resultSet.next()) {
            return;
        }


        CategoryInfo categoryInfo = new CategoryInfo(resultSet.getString("PRODUCT_CAT_ID"),
                resultSet.getString("PRODUCT_CAT_NAME"));
        skuInfo.addCategoryInfo(categoryInfo);
        String categoryId = resultSet.getString("PARENT_PRODUCT_CAT_ID");

        if (!"0".equals(categoryId)) {
            fetchCategory(skuInfo, categoryId, connection);
        } else {
            skuInfo.setRootcategorid(categoryId);
        }
    }
}
