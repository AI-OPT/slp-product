package com.ai.slp.product.cache.prodCat;

import com.ai.opt.sdk.cache.base.AbstractCache;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.slp.product.constants.CommonSatesConstants;
import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.dao.mapper.bo.ProductCatCriteria;
import com.ai.slp.product.dao.mapper.interfaces.ProductCatMapper;
import com.ai.slp.product.util.IPaasCatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static Logger logger = LoggerFactory.getLogger(ProdCatCache.class);
    @Autowired
    ProductCatMapper productCatMapper;

    @Override
    public void write() throws Exception {
        ICacheClient cacheClient = IPaasCatUtils.getCacheClient();
        //查询所有有效的类目信息
        ProductCatCriteria example = new ProductCatCriteria();
        example.createCriteria().andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
        List<ProductCat> catList = productCatMapper.selectByExample(example);
        //租户
        Set<String> tenantIdSet = new HashSet<>();
        for (ProductCat cat:catList){
            //每个租户执行一次
            if (!tenantIdSet.contains(cat.getTenantId())){
                tenantIdSet.add(cat.getTenantId());
            }
        }
    }

    /**
     * 清空一个租户下的类目缓存
     * @param tenantId
     */
    private void clearCacheOfTenant(String tenantId){
        //查询所有的详细信息KEY
        ICacheClient cacheClient = IPaasCatUtils.getCacheClient();
//        cacheClient.
    }
}
