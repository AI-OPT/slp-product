package com.ai.slp.product.search.api.impl;

import com.ai.opt.sdk.components.ses.SESClientFactory;
import com.ai.paas.ipaas.search.vo.SearchOption;
import com.ai.paas.ipaas.search.vo.SearchfieldVo;
import com.ai.slp.product.search.api.ISKUIndexManage;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.search.constants.Constants;
import com.ai.slp.product.search.constants.SearchMetaFieldConfig;
import com.ai.slp.product.search.service.ISKUService;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xin on 16-6-1.
 */
@Component
public class SKUIndexManageImpl implements ISKUIndexManage {

    private Logger logger = LogManager.getLogger(SKUIndexManageImpl.class);

    @Autowired
    private ISKUService iskuService;

    @Override
    public boolean updateSKUIndex(String productId) {
        try {
            List<SKUInfo> skuInfoList = iskuService.getSKUInfoByProductId(productId);
            List<String> string = new ArrayList<String>();
            for (SKUInfo skuInfo : skuInfoList) {
                string.add(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(skuInfo));
            }

            SESClientFactory.getSearchClient(Constants.SearchNameSpace).bulkInsertData(string);
            return true;
        } catch (Exception e) {
            logger.error("Failed to update sku info", e);
        }

        return false;
    }

    @Override
    public boolean deleteSKUIndexBySKUId(String skuId) {
        try {
            List<SearchfieldVo> searchfieldVos = new ArrayList<SearchfieldVo>();
            searchfieldVos.add(new SearchfieldVo(SearchMetaFieldConfig.SKU_ID, skuId, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return SESClientFactory.getSearchClient(Constants.SearchNameSpace).deleteData(searchfieldVos);
        } catch (Exception e) {
            logger.error("Failed to delete sku info", e);
        }

        return false;
    }

    @Override
    public boolean deleteSKUIndexByProductId(String productId) {
        try {
            List<SearchfieldVo> searchfieldVos = new ArrayList<SearchfieldVo>();
            searchfieldVos.add(new SearchfieldVo(SearchMetaFieldConfig.PRODUCT_ID, productId, new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return SESClientFactory.getSearchClient(Constants.SearchNameSpace).deleteData(searchfieldVos);
        } catch (Exception e) {
            logger.error("Failed to delete sku info", e);
        }

        return false;
    }
}
