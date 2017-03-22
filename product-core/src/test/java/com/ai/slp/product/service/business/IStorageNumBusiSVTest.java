package com.ai.slp.product.service.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.slp.product.api.storageserver.param.StorageNumRes;
import com.ai.slp.product.constants.CommonTestConstants;
import com.ai.slp.product.service.business.interfaces.IStorageNumBusiSV;
import com.ai.slp.product.vo.SkuStorageVo;

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
        SkuStorageVo storageVo = storageNumBusiSV.queryStorageOfSku(
                CommonTestConstants.COMMON_TENANT_ID,"0000000000000238");
        System.out.println(storageVo.toString());
    }

    @Test
    public void userStorageNum(){
        StorageNumRes numRes = storageNumBusiSV.userStorageNum(
                CommonTestConstants.COMMON_TENANT_ID,"1000000000002409",1,null);
        System.out.println(numRes.toString());
    }

    @Test
    public void queryNowUsableNumOfGroup(){
        System.out.println(storageNumBusiSV.queryNowUsableNumOfGroup(
                CommonTestConstants.COMMON_TENANT_ID,"0000000000148"));
    }
}
