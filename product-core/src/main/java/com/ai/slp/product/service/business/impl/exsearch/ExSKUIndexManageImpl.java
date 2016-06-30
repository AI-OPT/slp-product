package com.ai.slp.product.service.business.impl.exsearch;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sdk.components.ses.SESClientFactory;
import com.ai.paas.ipaas.search.vo.SearchOption;
import com.ai.paas.ipaas.search.vo.SearchfieldVo;
import com.ai.slp.product.constants.SearchConstants;
import com.ai.slp.product.constants.SearchFieldConfConstants;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.service.atom.interfaces.search.ISKUService;
import com.ai.slp.product.service.business.interfaces.exsearch.IExSKUIndexManage;
import com.google.gson.GsonBuilder;

/**
 * 搜索信息管理
 * Created by xin on 16-6-1.
 */
@Component
public class ExSKUIndexManageImpl implements IExSKUIndexManage {

    private Logger logger = LogManager.getLogger(ExSKUIndexManageImpl.class);

    @Autowired
    private ISKUService iskuService;

    /**
     * 更新搜索信息
     * @param productId 商品标识
     * @return
     */
    @Override
    public boolean updateSKUIndex(String productId) {
        try {
            List<SKUInfo> skuInfoList = iskuService.getSKUInfoByProductId(productId);
            List<String> string = new ArrayList<String>();
            for (SKUInfo skuInfo : skuInfoList) {
                string.add(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(skuInfo));
            }

            SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace).bulkInsertData(string);
            return true;
        } catch (Exception e) {
            logger.error("Failed to update sku info", e);
        }

        return false;
    }

    /**
     * 删除指定SKU搜索信息
     * @param skuId sku标识
     * @return
     */
    @Override
    public boolean deleteSKUIndexBySKUId(String skuId) {
        try {
            List<SearchfieldVo> searchfieldVos = new ArrayList<SearchfieldVo>();
            searchfieldVos.add(new SearchfieldVo(SearchFieldConfConstants.SKU_ID, skuId,
                    new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace).deleteData(searchfieldVos);
        } catch (Exception e) {
            logger.error("Failed to delete sku info", e);
        }

        return false;
    }

    /**
     * 删除指定商品搜索信息
     * @param productId 商品标识
     * @return
     */
    @Override
    public boolean deleteSKUIndexByProductId(String productId) {
        try {
            List<SearchfieldVo> searchfieldVos = new ArrayList<SearchfieldVo>();
            searchfieldVos.add(new SearchfieldVo(SearchFieldConfConstants.PRODUCT_ID, productId,
                    new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.term)));
            return SESClientFactory.getSearchClient(SearchConstants.SearchNameSpace).deleteData(searchfieldVos);
        } catch (Exception e) {
            logger.error("Failed to delete sku info", e);
        }

        return false;
    }
}
