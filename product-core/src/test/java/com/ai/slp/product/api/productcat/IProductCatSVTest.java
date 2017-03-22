package com.ai.slp.product.api.productcat;

import com.ai.opt.base.vo.BaseListResponse;
import com.ai.opt.base.vo.BaseMapResponse;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.productcat.interfaces.IProductCatSV;
import com.ai.slp.product.api.productcat.param.*;
import com.ai.slp.product.constants.CommonTestConstants;
import com.ai.slp.product.constants.ProductCatConstants;
import com.google.gson.Gson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Created by jackieliu on 16/8/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProductCatSVTest {
    @Autowired
    IProductCatSV productCatSV;

    @Test
    public void queryPageProductCat(){
        ProductCatPageQuery pageQuery = new ProductCatPageQuery();
        pageQuery.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
        pageQuery.setPageSize(10);
        pageQuery.setPageNo(1);
        pageQuery.setIsChild("");
        pageQuery.setProductCatId("");
        pageQuery.setParentProductCatId("");
        pageQuery.setProductCatName("");
        PageInfoResponse<ProductCatInfo> catInfoRes = productCatSV.queryPageProductCat(pageQuery);
        System.out.println(catInfoRes.getResponseHeader().getIsSuccess());
    }

    @Test
    public void createProductCatTest(){
        List<ProductCatParam> pcpList = new ArrayList<>();
        ProductCatParam catParam = new ProductCatParam();
        catParam.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
        catParam.setOperId(1l);
        catParam.setProductCatName("test12");
        catParam.setIsChild("Y");
        catParam.setFirstLetter("A");
        catParam.setSerialNumber((short)2);
        pcpList.add(catParam);
        BaseResponse response = productCatSV.createProductCat(pcpList);
    }

    @Test
    public void queryAttrByCatAndType(){
        AttrQueryForCat attrQuery = new AttrQueryForCat();
        attrQuery.setTenantId("SLP");
        attrQuery.setProductCatId("1");
        attrQuery.setAttrType(ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_SALE);
        BaseListResponse<ProdCatAttrDef> response = productCatSV.queryAttrByCatAndType(attrQuery);
        //System.out.println(response.getResult().size());
        ProdCatAttrDef def = response.getResult().get(0);
        Gson gson = new Gson();
        System.out.println(gson.toJson(def));
    }

    @Test
    public void queryAttrAndValIdByCatAndType(){
        AttrQueryForCat attrQuery = new AttrQueryForCat();
        attrQuery.setTenantId("changhong");
        //attrQuery.setTenantId("SLP");
        attrQuery.setAttrType(ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_KEY);
        attrQuery.setProductCatId("00000000000050");
        BaseMapResponse<Long,Set<String>> response =  productCatSV.queryAttrAndValIdByCatAndType(attrQuery);
        System.out.println(response.getResult().size());
    }

    @Test
    public void addAttrForCatAndType(){
        ProdCatAttrAddParam addParam = new ProdCatAttrAddParam();
        Map<Long,Set<String>> attrValMap = new HashMap<>();
        attrValMap.put(17l,new HashSet<String>());
        Set<String> valSet = new HashSet<>();
        valSet.add("100001");
        valSet.add("100002");
        attrValMap.put(100001l,valSet);
        addParam.setTenantId("SLP");
        addParam.setOperId(1l);
        addParam.setProductCatId("00000000000007");
        addParam.setAttrType("1");
        addParam.setAttrAndVal(attrValMap);
        BaseResponse response = productCatSV.addAttrForCatAndType(addParam);
        System.out.println(response.getResponseHeader().getIsSuccess());
    }

    @Test
    public void deleteProductCatAttrOrVal(){
        ProdCatAttrVal attrVal = new ProdCatAttrVal();
        attrVal.setTenantId("SLP");
        attrVal.setOperId(1l);
        attrVal.setProductCatId("00000000000007");
        attrVal.setObjType("1");
        attrVal.setId("000000000008");
        BaseResponse response = productCatSV.deleteProductCatAttrOrVal(attrVal);
        System.out.println(response.getResponseHeader().getIsSuccess());
    }
}
