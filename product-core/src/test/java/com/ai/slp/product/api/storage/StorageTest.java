package com.ai.slp.product.api.storage;

import org.junit.Test;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import com.ai.slp.product.api.storage.param.STOStorage;
import com.ai.slp.product.api.storage.param.STOStorageGroup;

public class StorageTest {
	    @Test
	    public void saveStorageTest() {
	    	IStorageSV storageSV = DubboConsumerFactory.getService(IStorageSV.class);
	    	STOStorage stoStorage = new STOStorage();
	    	stoStorage.setOperId(1l);
	    	stoStorage.setPriorityNumber((short)1);
	    	stoStorage.setTenantId("SLP");
	    	stoStorage.setProductCatId("10000010010000");
	    	stoStorage.setStorageGroupId("0000000000058");
	    	stoStorage.setStorageName("1234567890");
	    	stoStorage.setTotalNum(100l);
	    	stoStorage.setWarnNum(10l);
	    	BaseResponse baseResponse = storageSV.saveStorage(stoStorage);
	    	String id = baseResponse.getResponseHeader().getResultMessage();
	    	System.out.println(id+"-------------------------------");
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
	    	System.out.println(id+"-------------------------------");
	    }
}