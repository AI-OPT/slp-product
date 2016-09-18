package com.ai.slp.product.api.storage;

import org.junit.Test;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import com.ai.slp.product.api.storage.param.STOStorage;
import com.ai.slp.product.api.storage.param.STOStorageGroup;
import com.ai.slp.product.constants.CommonTestConstants;
import com.ai.slp.route.api.routegroupmanage.interfaces.IRouteGroupManageSV;
import com.ai.slp.route.api.routegroupmanage.param.RouteGroupStateRequest;
import com.ai.slp.route.api.routegroupmanage.param.RouteGroupStateResponse;

/**
 * Created by jackieliu on 16/7/11.
 */
public class StorageSVConsumerTest {

    @Test
    public void queryStorageById(){
        IRouteGroupManageSV iRouteQuerySV = DubboConsumerFactory.getService(IRouteGroupManageSV.class);
        RouteGroupStateRequest stateRequest = new RouteGroupStateRequest();
        stateRequest.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
        stateRequest.setRouteGroupId("IRouteGroupManageSV");
        RouteGroupStateResponse queryResult = iRouteQuerySV.findRouteGroupState(stateRequest);
        System.out.println(queryResult==null);
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
