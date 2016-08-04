package com.ai.slp.product.api.storage;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import com.ai.slp.product.api.storage.param.StorageGroup4List;
import com.ai.slp.product.api.storage.param.StorageGroupQueryPage;
import com.ai.slp.product.constants.CommonTestConstants;
import org.elasticsearch.common.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class StorageTest {
    @Autowired
    IStorageSV storageSV;

    @Test
    public void queryGroup() {
        StorageGroupQueryPage groupQuery = new StorageGroupQueryPage();
        groupQuery.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
        groupQuery.setPageSize(10);
        DateTime dateTime = new DateTime(2016,5,20,0,0);//2016年5月20日0点0分
        groupQuery.setOperTimeStart(new Timestamp(dateTime.getMillis()));
        PageInfoResponse<StorageGroup4List> pageRes =  storageSV.queryGroup(groupQuery);
        if (pageRes==null)
            throw new BusinessException("","查询内容为空");
        System.out.println("\r\nTotal info:"+pageRes.getCount()+":"+pageRes.getPageSize()+":"+pageRes.getPageNo());
        int i = 1;
        for (StorageGroup4List groupAttach:pageRes.getResult()){
            System.out.println(i++);
            System.out.println(groupAttach.getStorageGroupId()+":"+groupAttach.getStorageGroupName()+":"
                    +groupAttach.getStandedProdId()+":"+groupAttach.getStandedProductName()+":"
                    +groupAttach.getStorageTotal()+":"+groupAttach.getStorageNum());
        }
    }
}