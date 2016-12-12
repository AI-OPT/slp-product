package com.ai.slp.product.service.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.opt.sdk.util.BeanUtils;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.paas.ipaas.util.JSonUtil;
import com.ai.slp.product.api.productcat.param.ProductCatInfo;
import com.ai.slp.product.cache.prodCat.ProdCatCache;
import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.service.business.interfaces.IProductCatBusiSV;
import com.ai.slp.product.service.business.interfaces.IProductCatQueryBusiSV;
import com.ai.slp.product.util.IPaasCatUtils;

/**
 * Created by jackieliu on 16/7/22.
 */
@Service
public class ProductCatQueryBusiSVImpl implements IProductCatQueryBusiSV {
    private static final Logger logger = LoggerFactory.getLogger(ProductCatQueryBusiSVImpl.class);
    @Autowired
    IProductCatBusiSV productCatBusiSV;
    @Autowired
    ProdCatCache catCache;
    /**
     * 查询指定标识的类目信息
     *
     * @param tenantId
     * @param catId
     * @return
     */
    @Override
    public ProductCatInfo queryById(String tenantId, String catId) {
        ICacheClient cacheClient = IPaasCatUtils.getCacheClient();
        String catStr = cacheClient.hget(IPaasCatUtils.genMcsCatInfoKey(tenantId),catId);
        ProductCatInfo catInfo = new ProductCatInfo();
        if (StringUtils.isNotBlank(catStr)) {
            ProductCat cat = JSonUtil.fromJSon(catStr,ProductCat.class);
            BeanUtils.copyProperties(catInfo,cat);
        }//若缓存中没有,则查询数据库
        else {
            catInfo = productCatBusiSV.queryByCatId(tenantId,catId);
            catCache.flushCatInfo(tenantId,catId);
        }
        return catInfo;
    }

    /**
     * 查询类目的类目链
     *
     * @param tenantId
     * @param productCatId
     * @return
     */
    @Override
    public List<ProductCatInfo> queryLinkOfCatById(String tenantId, String productCatId) {
        List<ProductCatInfo> catInfoList = new ArrayList<>();
        queryCatFoLinkById(catInfoList,tenantId,productCatId);
        return catInfoList;
    }

    /**
     * 查询类目的下级类目
     *
     * @param tenantId
     * @param catId
     * @return
     */
    @Override
    public List<ProductCatInfo> queryChileOfCatById(String tenantId, String catId) {
        ICacheClient cacheClient = IPaasCatUtils.getCacheClient();
        String parentKey = IPaasCatUtils.genMcsCatChildKey(tenantId, catId);
        //获取下级类目标识
        Set<String> catKeySet = cacheClient.smembers(parentKey);
        return fillCatInfoSet(tenantId,catKeySet);
    }

    /**
     * 查询指定级别下的类目信息
     *
     * @param tenantId
     * @param level
     * @return
     */
    @Override
    public List<ProductCatInfo> queryByLevel(String tenantId, Short level) {
        ICacheClient cacheClient = IPaasCatUtils.getCacheClient();
        String parentKey = IPaasCatUtils.genMcsCatLevelKey(tenantId);
        Set<String> catIdSet = cacheClient.zrangeByScore(parentKey,level,level);
        return fillCatInfoSet(tenantId,catIdSet);
    }

    private List<ProductCatInfo> fillCatInfoSet(String tenantId,Set<String> catIdSet){
        ICacheClient cacheClient = IPaasCatUtils.getCacheClient();
        List<ProductCatInfo> catInfoList = new ArrayList<>();
        //获取下级类目标识
        String infoKey = IPaasCatUtils.genMcsCatInfoKey(tenantId);
        for (String childId : catIdSet) {
            String catStr = cacheClient.hget(infoKey, childId);
            if (StringUtils.isBlank(catStr)){
            	continue;
            }
            logger.info("catId={},jsonStr={}",childId,catStr);
            ProductCatInfo catInfo = new ProductCatInfo();
            ProductCat cat = JSonUtil.fromJSon(catStr, ProductCat.class);
            BeanUtils.copyProperties(catInfo, cat);
            catInfoList.add(catInfo);
        }
        return catInfoList;
    }

    private void queryCatFoLinkById(List<ProductCatInfo> catInfoList,String tenantId, String productCatId){
        ProductCatInfo catInfo = queryById(tenantId,productCatId);
        if (catInfo==null){
        	return;
        }
        //已经达到根目录
        if (catInfo.getParentProductCatId()==null || "0".equals(catInfo.getParentProductCatId())||catInfoList.size()>10){
            catInfoList.add(catInfo);
            return;
            //若不是跟类目,则继续查询
        }else{
        	queryCatFoLinkById(catInfoList,tenantId,catInfo.getParentProductCatId());
        }
        catInfoList.add(catInfo);
    }
}
