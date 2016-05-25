package com.ai.slp.product.api.webfront.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.slp.product.api.webfront.interfaces.IProductHomeSV;
import com.ai.slp.product.api.webfront.param.ProductHome;
import com.alibaba.dubbo.config.annotation.Service;
@Service(validation = "true")
@Component
public class IProductHomeSVImpl implements IProductHomeSV {

    @Override
    public List<ProductHome> queryFlowDataProduct() throws BusinessException, SystemException {
        List<ProductHome> list1 = new ArrayList<ProductHome>();
        for(int i=0;i<7;i++){
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
            response1.setSalePrice(300L);
            response1.setTenantId("SLP");
            list1.add(response1);
        }
       return list1;
    }

    @Override
    public List<ProductHome> queryHotProduct() throws BusinessException, SystemException {
        List<ProductHome> list1 = new ArrayList<ProductHome>();
        for(int i=0;i<7;i++){
            ProductHome response1 = new ProductHome();
            response1.setProdId("1000000000000010");
            response1.setProdName("TP-link300M无线路由器");
            response1.setProductSellPoint("简单实现无线桥接、中继，扩展无线覆盖");
            response1.setSalePrice(99L);
            response1.setTenantId("SLP");
            list1.add(response1);
        }
       return list1;
    }

}
