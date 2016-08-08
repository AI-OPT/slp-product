package com.ai.slp.product.product;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import com.ai.slp.product.api.storage.param.STOStorage;
import com.ai.slp.product.api.storage.param.STOStorageGroup;
import com.ai.slp.product.service.business.interfaces.IStorageBusiSV;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class StorageBusiTest {
		@Autowired
		IStorageBusiSV storageBusiSV;
	    @Test
	    public void saveStorageTest() {
	    	STOStorage stoStorage = new STOStorage();
	    	stoStorage.setOperId(1l);
	    	stoStorage.setPriorityNumber((short)1);
	    	stoStorage.setTenantId("SLP");
	    	stoStorage.setStorageGroupId("0000000000058");
	    	stoStorage.setStorageName("1234567890");
	    	stoStorage.setTotalNum(100l);
	    	stoStorage.setWarnNum(10l);
	    	String id  = storageBusiSV.saveStorage(stoStorage);
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