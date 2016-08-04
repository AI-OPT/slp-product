package com.ai.slp.product.service.business;

import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.slp.product.constants.CommonTestConstants;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.business.interfaces.IStorageGroupBusiSV;
import com.ai.slp.product.util.IPaasStorageUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * Created by jackieliu on 16/6/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IStorageGroupBusiSVTest {
    @Autowired
    IStorageGroupBusiSV groupBusiSV;
    @Autowired
    IStorageGroupAtomSV groupAtomSV;

    @Test
    public void flushStorageCacheTest(){
        StorageGroup storageGroup = groupAtomSV.queryByGroupIdAndSupplierId(
                CommonTestConstants.COMMON_TENANT_ID,"-1", "100000000009");
        groupBusiSV.flushStorageCache(storageGroup);
    }

    @Test
    public void cacheTest(){
        ICacheClient cacheClient = IPaasStorageUtils.getClient();
        String tenantId = CommonTestConstants.COMMON_TENANT_ID,groupId = "100000000001";
        //获取库存组的cacheKey
        String groupKey = IPaasStorageUtils.genMcsStorageGroupKey(tenantId,groupId);
        Map<String,String> valMap = cacheClient.hgetAll(groupKey);
        System.out.println(valMap.size());
    }
}
