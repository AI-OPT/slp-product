package com.ai.slp.product.api.storageserver;

import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.slp.product.api.storageserver.interfaces.IStorageNumSV;
import com.ai.slp.product.api.storageserver.param.StorageNumRes;
import com.ai.slp.product.api.storageserver.param.StorageNumUserReq;
import org.junit.Test;

/**
 * Created by jackieliu on 16/7/12.
 */
public class IStorageNumSVConsumerTest {

    @Test
    public void useStorageNum(){
        IStorageNumSV storageNumSV = DubboConsumerFactory.getService(IStorageNumSV.class);
        StorageNumUserReq userReq = new StorageNumUserReq();
        userReq.setTenantId("SLP");
        userReq.setSkuId("1000000000002408");
        userReq.setSkuNum(1);
        StorageNumRes numRes = storageNumSV.useStorageNum(userReq);
        System.out.println(numRes.toString());
    }
}
