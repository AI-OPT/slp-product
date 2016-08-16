package com.ai.slp.product.api.productcat;

import com.ai.opt.base.vo.BaseListResponse;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.slp.product.api.productcat.interfaces.IProductCatSV;
import com.ai.slp.product.api.productcat.param.AttrQueryForCat;
import com.ai.slp.product.api.productcat.param.ProdCatAttrDef;
import com.ai.slp.product.constants.ProductCatConstants;
import org.junit.Test;

/**
 * Created by jackieliu on 16/8/16.
 */
public class IProductCatSVConsumerTest {

    @Test
    public void queryAttrByCatAndType(){
        IProductCatSV catSV = DubboConsumerFactory.getService(IProductCatSV.class);
        AttrQueryForCat attrQuery = new AttrQueryForCat();
        attrQuery.setTenantId("SLP");
        attrQuery.setProductCatId("1");
        attrQuery.setAttrType(ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_KEY);
        BaseListResponse<ProdCatAttrDef> catMap =  catSV.queryAttrByCatAndType(attrQuery);
        System.out.println(catMap.getResult().size());
    }
}
