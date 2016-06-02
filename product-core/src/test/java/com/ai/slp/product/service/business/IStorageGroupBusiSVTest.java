package com.ai.slp.product.service.business;

import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.business.interfaces.IStorageGroupBusiSV;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        StorageGroup storageGroup = groupAtomSV.queryByGroupId(CommonConstants.COMMON_TENANT_ID,"100000000001");
        groupBusiSV.flushStorageCache(storageGroup);
    }
}
