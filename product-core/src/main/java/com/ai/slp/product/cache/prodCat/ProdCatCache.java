package com.ai.slp.product.cache.prodCat;

import com.ai.opt.sdk.cache.base.AbstractCache;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.paas.ipaas.util.JSonUtil;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.dao.mapper.bo.ProductCatCriteria;
import com.ai.slp.product.dao.mapper.interfaces.ProductCatMapper;
import com.ai.slp.product.service.atom.interfaces.IProdCatDefAtomSV;
import com.ai.slp.product.util.CharUtils;
import com.ai.slp.product.util.IPaasCatUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 商品类目缓存刷新
 * Created by jackieliu on 16/7/21.
 */
@Component
public class ProdCatCache extends AbstractCache {
    private static final Logger logger = LoggerFactory.getLogger(ProdCatCache.class);
    @Autowired
    ProductCatMapper productCatMapper;
    @Autowired
    IProdCatDefAtomSV catDefAtomSV;

    @Override
    public void write() throws Exception {
        ICacheClient cacheClient = IPaasCatUtils.getCacheClient();
        //查询所有有效的类目信息
        ProductCatCriteria example = new ProductCatCriteria();
        example.createCriteria().andStateEqualTo(CommonConstants.STATE_ACTIVE);
        List<ProductCat> catList = productCatMapper.selectByExample(example);
        //租户
        Set<String> tenantIdSet = new HashSet<>();
        logger.info("Start flush cat cache!");
        for (ProductCat cat:catList){
            String tenantId= cat.getTenantId();
            //每个租户执行一次
            if (!tenantIdSet.contains(tenantId)){
                tenantIdSet.add(tenantId);
                clearCacheOfTenant(tenantId);
            }
            flushCatInfo(cacheClient,cat);
        }
        logger.info("Flush cat cache is end!");
    }

    /**
     * 刷新单个
     * @param tenantId
     * @param catId
     */
    @Async
    public void flushCatInfo(String tenantId,String catId){
        //查询类目信息
        ProductCat cat = catDefAtomSV.selectById(tenantId,catId);
        //刷新缓存
        if (cat!=null){
        	flushCatInfo(IPaasCatUtils.getCacheClient(),cat);
        }
    }

    /**
     * 刷新单个类目缓存
     *
     * @param cacheClient
     * @param cat
     */
    private void flushCatInfo(ICacheClient cacheClient,ProductCat cat){
        String tenantId = cat.getTenantId();
        logger.info("Flush cat[{}:{}] cache.",tenantId,cat.getProductCatId());
        //A
        cacheClient.hset(IPaasCatUtils.genMcsCatInfoKey(tenantId),
                cat.getProductCatId(), JSonUtil.toJSon(cat));
        //B
        cacheClient.zadd(IPaasCatUtils.genMcsCatLevelKey(tenantId),
                cat.getCatLevel(),cat.getProductCatId());
        //C
        String parentCatId = cat.getParentProductCatId();
        if (StringUtils.isNotBlank(parentCatId)){
            String parentKey = IPaasCatUtils.genMcsCatChildKey(tenantId,cat.getProductCatId());
            cacheClient.zadd(parentKey, CharUtils.charToLowAscii(cat.getFirstLetter()),cat.getProductCatId());
            //D
            cacheClient.sadd(IPaasCatUtils.genMcsCatParentKey(tenantId),parentKey);
        }
    }

    /**
     * 清空一个租户下的类目缓存
     * @param tenantId
     */
    private void clearCacheOfTenant(String tenantId){
        logger.info("Start clean tenant [{}] cache.",tenantId);
        ICacheClient cacheClient = IPaasCatUtils.getCacheClient();
        //查询所有非叶子类目的类目信息 D
        String parentKey = IPaasCatUtils.genMcsCatParentKey(tenantId);
        Set<String> parentCatSet = cacheClient.smembers(parentKey);
        //删除C类型的节点信息
        for (String catKey:parentCatSet){
            cacheClient.expire(catKey,0);
        }
        //删除D
        cacheClient.expire(IPaasCatUtils.genMcsCatParentKey(tenantId),0);
        //删除B
        cacheClient.expire(IPaasCatUtils.genMcsCatLevelKey(tenantId),0);
        //删除A
        cacheClient.expire(IPaasCatUtils.genMcsCatInfoKey(tenantId),0);
        logger.info("Clean tenant [{}] cache is end.",tenantId);
    }
}
