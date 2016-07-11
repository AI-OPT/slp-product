package com.ai.slp.product.api.storage;

import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import com.ai.slp.product.api.storage.param.StorageRes;
import org.junit.Test;

/**
 * Created by jackieliu on 16/7/11.
 */
public class StorageSVConsumerTest {

    @Test
    public void queryStorageById(){
        IStorageSV storageSV = DubboConsumerFactory.getService(IStorageSV.class);
        StorageRes storageRes = storageSV.queryStorageById("100100000001");
        System.out.println(storageRes.getStorageGroupId());
    }
}
