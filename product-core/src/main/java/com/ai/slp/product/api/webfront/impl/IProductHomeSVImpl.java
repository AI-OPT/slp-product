package com.ai.slp.product.api.webfront.impl;

import java.util.ArrayList;
import java.util.List;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.slp.product.api.webfront.interfaces.IProductHomeSV;
import com.ai.slp.product.api.webfront.param.ProductHome;

public class IProductHomeSVImpl implements IProductHomeSV {

    @Override
    public List<ProductHome> queryFlowDataProduct() throws BusinessException, SystemException {
        List<ProductHome> list1 = new ArrayList<ProductHome>();
        for(int i=0;i<8;i++){
            ProductHome response1 = new ProductHome();
            response1.setProdId("1000000000000010");
            response1.setProdName("移动上网卡季卡 ");
            response1.setSalePrice(100L);
            response1.setTenantId("SLP");
            list1.add(response1);
        }
       return list1;
    }

    @Override
    public List<ProductHome> queryPhoneBillProduct() throws BusinessException, SystemException {
        List<ProductHome> list1 = new ArrayList<ProductHome>();
        for(int i=0;i<8;i++){
            ProductHome response1 = new ProductHome();
            response1.setProdId("1000000000000010");
            response1.setProdName("联通上网卡季卡 ");
            response1.setSalePrice(100L);
            response1.setTenantId("SLP");
            list1.add(response1);
        }
       return list1;
    }

}
