package com.ai.slp.product.api.webfront.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.webfront.interfaces.ISearchProductSV;
import com.ai.slp.product.api.webfront.param.ProductData;
import com.ai.slp.product.api.webfront.param.ProductQueryRequest;
import com.ai.slp.product.api.webfront.param.ProductQueryResponse;
import com.alibaba.dubbo.config.annotation.Service;
@Service(validation = "true")
@Component
public class ISearchProductSVImpl implements ISearchProductSV {

    @Override
    public ProductQueryResponse queryProductPage(ProductQueryRequest request)
            throws BusinessException, SystemException {
        ProductQueryResponse response = new ProductQueryResponse();
        PageInfo<ProductData> pageinfo = new PageInfo<ProductData>();
        List<ProductData> list = new ArrayList<ProductData>();
        for(int i=0;i<10;i++){
            ProductData data = new ProductData();
            data.setCommentIdCount(234);
            data.setSalePrice(34.9);
            data.setSkuName("华为荣耀 畅玩 5X 4G 手机 破晓银 移动4G版"); 
            list.add(data);
        }
        pageinfo.setResult(list);
        pageinfo.setPageSize(request.getPageInfo().getPageSize());
        pageinfo.setPageNo(request.getPageInfo().getPageNo());
        response.setPageInfo(pageinfo);
        return response;
        
    }

    @Override
    public List<ProductData> queryHotSellProduct() throws BusinessException, SystemException {
        List<ProductData> list = new ArrayList<ProductData>();
        for(int i=0;i<5;i++){
            ProductData data = new ProductData();
            data.setCommentIdCount(234);
            data.setSalePrice(34.9);
            data.setSkuName("华为荣耀 畅玩 5X 4G 手机 破晓银 移动4G版"); 
            list.add(data);
        }
        return list;
    }

}
