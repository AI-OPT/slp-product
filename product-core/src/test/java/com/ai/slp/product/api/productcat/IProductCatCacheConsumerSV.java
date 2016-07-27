package com.ai.slp.product.api.productcat;

import com.ai.opt.base.vo.BaseListResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.slp.product.api.productcat.interfaces.IProductCatCacheSV;
import com.ai.slp.product.api.productcat.param.ProductCatInfo;
import com.ai.slp.product.api.productcat.param.ProductCatUniqueReq;
import org.junit.Test;

/**
 * Created by jackieliu on 16/7/25.
 */
public class IProductCatCacheConsumerSV {

    @Test
    public void queryByCatId(){
        IProductCatCacheSV catCacheSV = DubboConsumerFactory.getService(IProductCatCacheSV.class);
        ProductCatUniqueReq uniqueReq = new ProductCatUniqueReq();
        uniqueReq.setTenantId("SLP");
        uniqueReq.setProductCatId("10000010010000");
        ProductCatInfo catInfo = catCacheSV.queryByCatId(uniqueReq);
        ResponseHeader header = catInfo.getResponseHeader();
        if (header!=null){
            System.out.println("IsSuccess="+header.getIsSuccess()+",errorCode="+header.getResultCode()
            +",errorMsg="+header.getResultMessage());
        }
        System.out.println(catInfo.getProductCatName());
    }

    @Test
    public void queryLink(){
        IProductCatCacheSV catCacheSV = DubboConsumerFactory.getService(IProductCatCacheSV.class);
        ProductCatUniqueReq uniqueReq = new ProductCatUniqueReq();
        uniqueReq.setTenantId("SLP");
        uniqueReq.setProductCatId("10000010020000");
        BaseListResponse<ProductCatInfo> listResponse = catCacheSV.queryLinkOfCatById(uniqueReq);
        ResponseHeader header = listResponse.getResponseHeader();
        if (header!=null){
            System.out.println("IsSuccess="+header.getIsSuccess()+",errorCode="+header.getResultCode()
                    +",errorMsg="+header.getResultMessage());
        }
        for (ProductCatInfo catInfo:listResponse.getResult()){
            System.out.print(catInfo.getProductCatName()+">");
        }
        System.out.print("\r\n");
    }
}
