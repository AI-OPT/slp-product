package com.ai.slp.product.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import com.ai.slp.product.api.storage.param.STOStorage;
import com.ai.slp.product.api.storage.param.STOStorageGroup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class StorageTest {
	    @Autowired
	    IStorageSV storageSV;
	    @Test
	    public void saveStorageTest() {
	    	STOStorage stoStorage = new STOStorage();
	    	stoStorage.setOperId(1l);
	    	stoStorage.setPriorityNumber((short)1);
	    	stoStorage.setTenantId("SLP");
	    	stoStorage.setProductCatId("10000010010000");
	    	stoStorage.setStorageGroupId("0000000000040");
	    	stoStorage.setStorageName("1234567890");
	    	stoStorage.setTotalNum(100l);
	    	stoStorage.setWarnNum(10l);
	    	BaseResponse baseResponse = storageSV.saveStorage(stoStorage);
	    	String id = baseResponse.getResponseHeader().getResultMessage();
	    	System.out.println(id+"-------------------------------");
	    }
	    @Test
	    public void saveGroupTest() {
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