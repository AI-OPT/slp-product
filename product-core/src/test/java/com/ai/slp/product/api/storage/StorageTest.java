package com.ai.slp.product.api.storage;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.BaseListResponse;
import com.ai.opt.base.vo.BaseMapResponse;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import com.ai.slp.product.api.storage.param.STOStorage;
import com.ai.slp.product.api.storage.param.SkuStorageInfo;
import com.ai.slp.product.api.storage.param.StoGroupStatus;
import com.ai.slp.product.api.storage.param.StoNoSkuSalePrice;
import com.ai.slp.product.api.storage.param.StoNoSkuSalePriceReq;
import com.ai.slp.product.api.storage.param.StorageGroup4List;
import com.ai.slp.product.api.storage.param.StorageGroupQuery;
import com.ai.slp.product.api.storage.param.StorageGroupQueryPage;
import com.ai.slp.product.api.storage.param.StorageGroupRes;
import com.ai.slp.product.api.storage.param.StorageStatus;
import com.ai.slp.product.api.storage.param.StorageUniQuery;
import com.ai.slp.product.constants.CommonTestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class StorageTest {
    @Autowired
    IStorageSV storageSV;

    @Test
    public void queryGroup() {
        StorageGroupQueryPage groupQuery = new StorageGroupQueryPage();
        groupQuery.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
        groupQuery.setPageSize(10);
        DateTime dateTime = new DateTime(2016,5,20,0,0);//2016年5月20日0点0分
        groupQuery.setOperTimeStart(new Timestamp(dateTime.getMillis()));
        PageInfoResponse<StorageGroup4List> pageRes =  storageSV.queryGroup(groupQuery);
        if (pageRes==null)
            throw new BusinessException("","查询内容为空");
        System.out.println("\r\nTotal info:"+pageRes.getCount()+":"+pageRes.getPageSize()+":"+pageRes.getPageNo());
        int i = 1;
        for (StorageGroup4List groupAttach:pageRes.getResult()){
            System.out.println(i++);
            System.out.println(groupAttach.getStorageGroupId()+":"+groupAttach.getStorageGroupName()+":"
                    +groupAttach.getStandedProdId()+":"+groupAttach.getStandedProductName()+":"
                    +groupAttach.getStorageTotal()+":"+groupAttach.getStorageNum());
        }
    }

    @Test
    public void queryGroupInfoByNormProdId(){
        StorageGroupQuery storageGroupQuery = new StorageGroupQuery();
        storageGroupQuery.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
        storageGroupQuery.setSupplierId("-1");
//        storageGroupQuery.setProductId("0000000000000121");
        storageGroupQuery.setProductId("0000000000000613");
        BaseListResponse<StorageGroupRes> groupResList = storageSV.queryGroupInfoByNormProdId(storageGroupQuery);
        System.out.println(groupResList.getResult().size());
        System.out.println(groupResList.getResult().get(0).getStorageTotal());
    }

    @Test
    public void querySkuStorageById(){
        StorageUniQuery query = new StorageUniQuery();
        query.setTenantId("changhong");
        query.setSupplierId("-1");
        query.setStorageId("000000000000000061");
        BaseMapResponse<String, SkuStorageInfo> mapResponse = storageSV.querySkuStorageById(query);
        System.out.println(mapResponse.getResult().size());
    }

    @Test
    public void chargeStorageGroupStatus(){
        StoGroupStatus groupStatus = new StoGroupStatus();
        groupStatus.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
        groupStatus.setSupplierId("-1");
        groupStatus.setOperId(1l);
        groupStatus.setGroupId("0000000000002156");
//        groupStatus.setState("2");//停用
 //       groupStatus.setState("1");//启用
        groupStatus.setState("3");
        BaseResponse response = storageSV.chargeStorageGroupStatus(groupStatus);
        System.out.println(response.getResponseHeader().isSuccess());
    }
    
    @Test
    public void updateMultiStorageSalePriceTest(){
    	StoNoSkuSalePriceReq priceReq = new StoNoSkuSalePriceReq();
    	priceReq.setTenantId("changhong");
    	priceReq.setSupplierId("-1");
    	priceReq.setOperId((long) 1);
    	List<StoNoSkuSalePrice> salePrice = new ArrayList<>();
    	StoNoSkuSalePrice skuSalePrice = new StoNoSkuSalePrice();
    	skuSalePrice.setGroupId("0000000000002097");
    	skuSalePrice.setPriorityNumber((short) 1);
    	skuSalePrice.setSalePrice((long) 5550);
    	salePrice.add(skuSalePrice);
    	priceReq.setStorageSalePrice(salePrice);
//    	priceReq.setStorageSalePrice(salePrice);
    	storageSV.updateMultiStorageSalePrice(priceReq);
    }
    
    @Test
    public void chargeStorageStatusTest(){
    	StorageStatus status = new StorageStatus();
    	status.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
    	status.setSupplierId("-1");
    	status.setOperId(1l);
    	status.setStorageId("000000000000000268");
    	status.setState("1");
    	
//        groupStatus.setState("2");//停用
//        groupStatus.setState("1");//启用
        BaseResponse response = storageSV.chargeStorageStatus(status);
        System.out.println(response.getResponseHeader().isSuccess());
    }
    
    @Test
    public void saveStorageTest(){
    	STOStorage stoStorage = new STOStorage();
    	stoStorage.setTenantId("changhong");
    	stoStorage.setSupplierId("-1");
    	stoStorage.setStorageName("www");
    	stoStorage.setStorageGroupId("0000000000002066");
//    	stoStorage.setStorageGroupId("0000000000477");
    	stoStorage.setOperId(1l);
    	stoStorage.setTotalNum(12l);
    	stoStorage.setWarnNum(1l);
    	stoStorage.setPriorityNumber((short) 1);
    	BaseResponse saveStorage = storageSV.saveStorage(stoStorage);
    }

}