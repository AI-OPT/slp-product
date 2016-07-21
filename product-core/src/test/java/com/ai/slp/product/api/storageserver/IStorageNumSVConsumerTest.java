package com.ai.slp.product.api.storageserver;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.slp.product.api.storageserver.interfaces.IStorageNumSV;
import com.ai.slp.product.api.storageserver.param.StorageNumBackReq;
import com.ai.slp.product.api.storageserver.param.StorageNumRes;
import com.ai.slp.product.api.storageserver.param.StorageNumUserReq;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackieliu on 16/7/12.
 */
public class IStorageNumSVConsumerTest {

    @Test
    public void useStorageNum(){
        IStorageNumSV storageNumSV = DubboConsumerFactory.getService(IStorageNumSV.class);
        StorageNumUserReq userReq = new StorageNumUserReq();
        userReq.setTenantId("SLP");
        userReq.setSkuId("1000000000002409");
        userReq.setSkuNum(1);
        StorageNumRes numRes = storageNumSV.useStorageNum(userReq);
        System.out.println(numRes.toString());
        Map<String,Integer> skuMap = numRes.getStorageNum();
        for (Map.Entry<String,Integer> skuNum:skuMap.entrySet()){
            System.out.println("Sku storage="+skuNum.getKey()+",num="+skuNum.getValue());
        }
    }

    @Test
    public void backStorageNum(){
        IStorageNumSV storageNumSV = DubboConsumerFactory.getService(IStorageNumSV.class);
        StorageNumBackReq backReq = new StorageNumBackReq();
        backReq.setTenantId("SLP");
        backReq.setSkuId("1000000000002409");
        Map<String,Integer> userMap = new HashMap<>();
        userMap.put("100000100009",1);
        backReq.setStorageNum(userMap);
        BaseResponse baseResponse = storageNumSV.backStorageNum(backReq);
        ResponseHeader header = baseResponse.getResponseHeader();
        System.out.println(header.getResultCode()+","+header.getResultMessage());
    }
}
