package com.ai.slp.product.service.business;

import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.business.interfaces.IStorageGroupBusiSV;
import com.ai.slp.product.util.IPassUtils;
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
//        for (int i = 1;i<97;i++) {
//            String groupId = "1000000000"+i;//100000000001
//            if (i<10)
//                groupId = "10000000000"+i;
//            StorageGroup storageGroup = groupAtomSV.queryByGroupId(CommonConstants.COMMON_TENANT_ID, groupId);
//            groupBusiSV.flushStorageCache(storageGroup);
//        }
        StorageGroup storageGroup = groupAtomSV.queryByGroupId(
                CommonConstants.COMMON_TENANT_ID, "100000000008");
        groupBusiSV.flushStorageCache(storageGroup);
    }

    @Test
    public void cacheTest(){
        ICacheClient cacheClient = MCSClientFactory.getCacheClient(StorageConstants.IPass.McsParams.STORAGE_MCS);
        String tenantId = CommonConstants.COMMON_TENANT_ID,groupId = "100000000001";
        //获取库存组的cacheKey
        String groupKey = IPassUtils.genMcsStorageGroupKey(tenantId,groupId);
        Map<String,String> valMap = cacheClient.hgetAll(groupKey);
        System.out.println(valMap.size());
    }
}
