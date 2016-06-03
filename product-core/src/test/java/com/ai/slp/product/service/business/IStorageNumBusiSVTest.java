package com.ai.slp.product.service.business;

import com.ai.slp.product.api.storageserver.param.StorageNumRes;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.service.business.interfaces.IStorageNumBusiSV;
import com.ai.slp.product.vo.SkuStorageVo;
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
public class IStorageNumBusiSVTest {
    @Autowired
    IStorageNumBusiSV storageNumBusiSV;

    @Test
    public void queryStorageOfSku(){
        SkuStorageVo storageVo = storageNumBusiSV.queryStorageOfSku(CommonConstants.COMMON_TENANT_ID,"1000000000002401");
        System.out.println(storageVo.toString());
    }

    @Test
    public void userStorageNum(){
        StorageNumRes numRes = storageNumBusiSV.userStorageNum(CommonConstants.COMMON_TENANT_ID,"1000000000002401",4);
    }
}
