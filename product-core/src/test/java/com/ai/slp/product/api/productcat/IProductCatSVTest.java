package com.ai.slp.product.api.productcat;

import com.ai.opt.base.vo.BaseListResponse;
import com.ai.opt.base.vo.BaseMapResponse;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.productcat.interfaces.IProductCatSV;
import com.ai.slp.product.api.productcat.param.*;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.constants.ProductCatConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        pageQuery.setTenantId(CommonConstants.COMMON_TENANT_ID);
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
        catParam.setTenantId(CommonConstants.COMMON_TENANT_ID);
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
        System.out.println(response.getResult().size());
    }

    @Test
    public void queryAttrAndValIdByCatAndType(){
        AttrQueryForCat attrQuery = new AttrQueryForCat();
        attrQuery.setTenantId("SLP");
        attrQuery.setAttrType(ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_SALE);
        attrQuery.setProductCatId("1");
        BaseMapResponse<Long,Set<String>> response =  productCatSV.queryAttrAndValIdByCatAndType(attrQuery);
        System.out.println(response.getResult().size());
    }
}
