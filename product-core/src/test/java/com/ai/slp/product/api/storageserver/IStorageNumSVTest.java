package com.ai.slp.product.api.storageserver;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.slp.product.api.storageserver.interfaces.IStorageNumSV;
import com.ai.slp.product.api.storageserver.param.StorageNumBackReq;
import com.ai.slp.product.api.storageserver.param.StorageNumRes;
import com.ai.slp.product.api.storageserver.param.StorageNumUserReq;
import com.ai.slp.product.constants.CommonTestConstants;

/**
 * Created by jackieliu on 16/7/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IStorageNumSVTest {
    @Autowired
    IStorageNumSV storageNumSV;

    @Test
    public void useStorageNum(){
        StorageNumUserReq userReq = new StorageNumUserReq();
        userReq.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
//        userReq.setSkuId("0000000000000194");
        userReq.setSkuId("0000000000001153");
        userReq.setSkuNum(1);
        StorageNumRes numRes = storageNumSV.useStorageNum(userReq);
        System.out.println(numRes.toString());
       /* Map<String,Integer> skuMap = numRes.getStorageNum();
        for (Map.Entry<String,Integer> skuNum:skuMap.entrySet()){
            System.out.println("Sku storage="+skuNum.getKey()+",num="+skuNum.getValue());
        }*/
    }

    @Test
    public void backStorageNum(){
        IStorageNumSV storageNumSV = DubboConsumerFactory.getService(IStorageNumSV.class);
        StorageNumBackReq backReq = new StorageNumBackReq();
        backReq.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
        backReq.setSkuId("0000000000000194");
        Map<String,Integer> userMap = new HashMap<>();
        userMap.put("100000100009",1);
        backReq.setStorageNum(userMap);
        BaseResponse baseResponse = storageNumSV.backStorageNum(backReq);
        ResponseHeader header = baseResponse.getResponseHeader();
        System.out.println(header.getResultCode()+","+header.getResultMessage());
    }
}
