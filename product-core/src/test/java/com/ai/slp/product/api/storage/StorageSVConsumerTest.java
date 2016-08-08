package com.ai.slp.product.api.storage;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import com.ai.slp.product.api.storage.param.STOStorage;
import com.ai.slp.product.api.storage.param.STOStorageGroup;
import org.junit.Test;

/**
 * Created by jackieliu on 16/7/11.
 */
public class StorageSVConsumerTest {

    @Test
    public void queryStorageById(){
        IStorageSV storageSV = DubboConsumerFactory.getService(IStorageSV.class);
//        StorageRes storageRes = storageSV.queryStorageById("100100000001");
//        System.out.println(storageRes.getStorageGroupId());
    }

    @Test
    public void saveStorageTest() {
        IStorageSV storageSV = DubboConsumerFactory.getService(IStorageSV.class);
        STOStorage stoStorage = new STOStorage();
        stoStorage.setOperId(1l);
        stoStorage.setPriorityNumber((short) 1);
        stoStorage.setTenantId("SLP");
        stoStorage.setStorageGroupId("0000000000058");
        stoStorage.setStorageName("1234567890");
        stoStorage.setTotalNum(100l);
        stoStorage.setWarnNum(10l);
        BaseResponse baseResponse = storageSV.saveStorage(stoStorage);
        String id = baseResponse.getResponseHeader().getResultMessage();
        System.out.println(id + "-------------------------------");
    }

    @Test
    public void saveGroupTest() {
        IStorageSV storageSV = DubboConsumerFactory.getService(IStorageSV.class);
        STOStorageGroup storageGroup = new STOStorageGroup();
        storageGroup.setTenantId("SLP");
        storageGroup.setStorageGroupName("LP001");
        storageGroup.setStandedProdId("100000000104");
        storageGroup.setCreateId(1l);
        BaseResponse baseResponse = storageSV.createStorageGroup(storageGroup);
        String id = baseResponse.getResponseHeader().getResultMessage();
        System.out.println(id + "-------------------------------");
    }
}
