package com.ai.slp.product.service.business;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.product.param.OtherSetOfProduct;
import com.ai.slp.product.api.product.param.ProductEditQueryReq;
import com.ai.slp.product.api.product.param.ProductEditUp;
import com.ai.slp.product.constants.CommonTestConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.service.business.interfaces.IProductManagerBsuiSV;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackieliu on 16/6/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/core-context.xml")
public class IProductManagerBusiSVTest {
    @Autowired
    IProductManagerBsuiSV productManagerBsuiSV;

    @Test
    public void queryPageForEditTest(){
        ProductEditQueryReq queryReq = new ProductEditQueryReq();
        queryReq.setTenantId(CommonTestConstants.COMMON_TENANT_ID);
        List<String> stateList = new ArrayList<>();
        stateList.add(ProductConstants.Product.State.ADD);
        stateList.add(ProductConstants.Product.State.UNEDIT);
        queryReq.setStateList(stateList);
        queryReq.setProductCatId("1");//
        PageInfoResponse<ProductEditUp> response = productManagerBsuiSV.queryPageForEdit(queryReq);
        System.out.println("\r"+response.getCount()+",size="+response.getResult().size());
    }

    @Test
    public void queryOtherSetOfProdTest(){
        OtherSetOfProduct otherSet = productManagerBsuiSV.queryOtherSetOfProd(
                CommonTestConstants.COMMON_TENANT_ID,"-1","3");
        if (otherSet!=null && otherSet.getPersonAudiences()!=null){
            System.out.println(otherSet.getPersonAudiences().getUserId());
        }

    }


}
