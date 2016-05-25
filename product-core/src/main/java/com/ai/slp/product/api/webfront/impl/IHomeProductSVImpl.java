package com.ai.slp.product.api.webfront.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.webfront.interfaces.IHomeProductSV;
import com.ai.slp.product.api.webfront.param.ProductHome;
import com.ai.slp.product.api.webfront.param.ProductHomeRecommend;
import com.ai.slp.product.api.webfront.param.ProductHomeRecommendResponse;
import com.ai.slp.product.api.webfront.param.ProductHomeRequest;
import com.ai.slp.product.api.webfront.param.ProductHomeResponse;

public class IHomeProductSVImpl implements IHomeProductSV {

    @Override
    public ProductHomeResponse queryDataProduct(ProductHomeRequest productHomeRequest)
            throws BusinessException, SystemException {
        ProductHome response1 = new ProductHome();
        response1.setProdId("1000000000000010");
        response1.setProdName("河北联通手机话费充值10元话费充值卡官方直充快充秒充 ");
        response1.setProductSellPoint("与三大运营商官方合作，价格最低，安全便捷，企业及个人充值首选平台");
        response1.setSalePrice(100L);
        response1.setTenantId("SLP");
        List<ProductHome> list1 = new ArrayList<ProductHome>();
        ProductHomeResponse response = new ProductHomeResponse();
        PageInfo<ProductHome> pageInfo1 = new PageInfo<ProductHome>();
        pageInfo1.setResult(list1);
        list1.add(response1);
        response.setPageInfo(pageInfo1);

        ProductHome response2 = new ProductHome();
        response2.setProdId("1000000000000010");
        response2.setProdName("河北联通手机话费充值10元话费充值卡官方直充快充秒充 ");
        response2.setProductSellPoint("与三大运营商官方合作，价格最低，安全便捷，企业及个人充值首选平台");
        response2.setSalePrice(100L);
        response2.setTenantId("SLP");
        List<ProductHome> list2 = new ArrayList<ProductHome>();
        list2.add(response2);

        ProductHome response3 = new ProductHome();
        response3.setProdId("1000000000000010");
        response3.setProdName("河北联通手机话费充值10元话费充值卡官方直充快充秒充 ");
        response3.setProductSellPoint("与三大运营商官方合作，价格最低，安全便捷，企业及个人充值首选平台");
        response3.setSalePrice(100L);
        response3.setTenantId("SLP");
        List<ProductHome> list3 = new ArrayList<ProductHome>();
        list3.add(response3);

        ProductHome response4 = new ProductHome();
        response4.setProdId("1000000000000010");
        response4.setProdName("河北联通手机话费充值10元话费充值卡官方直充快充秒充 ");
        response4.setProductSellPoint("与三大运营商官方合作，价格最低，安全便捷，企业及个人充值首选平台");
        response4.setSalePrice(100L);
        response4.setTenantId("SLP");
        List<ProductHome> list4 = new ArrayList<ProductHome>();
        list4.add(response4);

        ProductHome response5 = new ProductHome();
        response5.setProdId("1000000000000010");
        response5.setProdName("河北联通手机话费充值10元话费充值卡官方直充快充秒充 ");
        response5.setProductSellPoint("与三大运营商官方合作，价格最低，安全便捷，企业及个人充值首选平台");
        response5.setSalePrice(100L);
        response5.setTenantId("SLP");
        List<ProductHome> list5 = new ArrayList<ProductHome>();
        list5.add(response5);

        ProductHome response6 = new ProductHome();
        response6.setProdId("1000000000000010");
        response6.setProdName("河北联通手机话费充值10元话费充值卡官方直充快充秒充 ");
        response6.setProductSellPoint("与三大运营商官方合作，价格最低，安全便捷，企业及个人充值首选平台");
        response6.setSalePrice(100L);
        response6.setTenantId("SLP");
        List<ProductHome> list6 = new ArrayList<ProductHome>();
        list6.add(response6);

        ProductHome response7 = new ProductHome();
        response7.setProdId("1000000000000010");
        response7.setProdName("河北联通手机话费充值10元话费充值卡官方直充快充秒充 ");
        response7.setProductSellPoint("与三大运营商官方合作，价格最低，安全便捷，企业及个人充值首选平台");
        response7.setSalePrice(100L);
        response7.setTenantId("SLP");
        List<ProductHome> list7 = new ArrayList<ProductHome>();
        list7.add(response7);

        ProductHome response8 = new ProductHome();
        response8.setProdId("1000000000000010");
        response8.setProdName("河北联通手机话费充值10元话费充值卡官方直充快充秒充 ");
        response8.setProductSellPoint("与三大运营商官方合作，价格最低，安全便捷，企业及个人充值首选平台");
        response8.setSalePrice(100L);
        response8.setTenantId("SLP");
        List<ProductHome> list8 = new ArrayList<ProductHome>();
        list8.add(response8);
        PageInfo<ProductHome> pageInfo = new PageInfo<ProductHome>();
        pageInfo.setResult(list8);
        return response;

    }

    @Override
    public ProductHomeResponse queryFlowProduct(ProductHomeRequest productHomeRequest)
            throws BusinessException, SystemException {
        ProductHome response1 = new ProductHome();
        response1.setProdId("1000000000000010");
        response1.setProdName("河北联通手机话费充值10元话费充值卡官方直充快充秒充 ");
        response1.setProductSellPoint("与三大运营商官方合作，价格最低，安全便捷，企业及个人充值首选平台");
        response1.setSalePrice(100L);
        response1.setTenantId("SLP");
        List<ProductHome> list1 = new ArrayList<ProductHome>();
        ProductHomeResponse response = new ProductHomeResponse();
        PageInfo<ProductHome> pageInfo1 = new PageInfo<ProductHome>();
        pageInfo1.setResult(list1);
        list1.add(response1);
        response.setPageInfo(pageInfo1);

        ProductHome response2 = new ProductHome();
        response2.setProdId("1000000000000010");
        response2.setProdName("河北联通手机话费充值10元话费充值卡官方直充快充秒充 ");
        response2.setProductSellPoint("与三大运营商官方合作，价格最低，安全便捷，企业及个人充值首选平台");
        response2.setSalePrice(100L);
        response2.setTenantId("SLP");
        List<ProductHome> list2 = new ArrayList<ProductHome>();
        list2.add(response2);

        ProductHome response3 = new ProductHome();
        response3.setProdId("1000000000000010");
        response3.setProdName("河北联通手机话费充值10元话费充值卡官方直充快充秒充 ");
        response3.setProductSellPoint("与三大运营商官方合作，价格最低，安全便捷，企业及个人充值首选平台");
        response3.setSalePrice(100L);
        response3.setTenantId("SLP");
        List<ProductHome> list3 = new ArrayList<ProductHome>();
        list3.add(response3);

        ProductHome response4 = new ProductHome();
        response4.setProdId("1000000000000010");
        response4.setProdName("河北联通手机话费充值10元话费充值卡官方直充快充秒充 ");
        response4.setProductSellPoint("与三大运营商官方合作，价格最低，安全便捷，企业及个人充值首选平台");
        response4.setSalePrice(100L);
        response4.setTenantId("SLP");
        List<ProductHome> list4 = new ArrayList<ProductHome>();
        list4.add(response4);

        ProductHome response5 = new ProductHome();
        response5.setProdId("1000000000000010");
        response5.setProdName("河北联通手机话费充值10元话费充值卡官方直充快充秒充 ");
        response5.setProductSellPoint("与三大运营商官方合作，价格最低，安全便捷，企业及个人充值首选平台");
        response5.setSalePrice(100L);
        response5.setTenantId("SLP");
        List<ProductHome> list5 = new ArrayList<ProductHome>();
        list5.add(response5);

        ProductHome response6 = new ProductHome();
        response6.setProdId("1000000000000010");
        response6.setProdName("河北联通手机话费充值10元话费充值卡官方直充快充秒充 ");
        response6.setProductSellPoint("与三大运营商官方合作，价格最低，安全便捷，企业及个人充值首选平台");
        response6.setSalePrice(100L);
        response6.setTenantId("SLP");
        List<ProductHome> list6 = new ArrayList<ProductHome>();
        list6.add(response6);

        ProductHome response7 = new ProductHome();
        response7.setProdId("1000000000000010");
        response7.setProdName("河北联通手机话费充值10元话费充值卡官方直充快充秒充 ");
        response7.setProductSellPoint("与三大运营商官方合作，价格最低，安全便捷，企业及个人充值首选平台");
        response7.setSalePrice(100L);
        response7.setTenantId("SLP");
        List<ProductHome> list7 = new ArrayList<ProductHome>();
        list7.add(response7);

        ProductHome response8 = new ProductHome();
        response8.setProdId("1000000000000010");
        response8.setProdName("河北联通手机话费充值10元话费充值卡官方直充快充秒充 ");
        response8.setProductSellPoint("与三大运营商官方合作，价格最低，安全便捷，企业及个人充值首选平台");
        response8.setSalePrice(100L);
        response8.setTenantId("SLP");
        List<ProductHome> list8 = new ArrayList<ProductHome>();
        list8.add(response8);
        PageInfo<ProductHome> pageInfo = new PageInfo<ProductHome>();
        pageInfo.setResult(list8);
        return response;
    }

    @Override
    public HashMap<String, String> queryProductCat(ProductHomeRequest productHomeRequest)
            throws BusinessException, SystemException {

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("1000000000000010", "10");
        map.put("1000000000000010", "20");
        map.put("1000000000000010", "30");
        map.put("1000000000000010", "40");
        map.put("1000000000000010", "50");
        map.put("1000000000000010", "60");
        map.put("1000000000000010", "70");
        return map;
    }

    @Override
    public ProductHomeRecommendResponse queryRecommendProduct(ProductHomeRequest productHomeRequest)
            throws BusinessException, SystemException {

        PageInfo<ProductHomeRecommend> pageInfo = new PageInfo<ProductHomeRecommend>();
        ProductHomeRecommendResponse response = new ProductHomeRecommendResponse();
        
        List<ProductHomeRecommend> list1 = new ArrayList<ProductHomeRecommend>();
        ProductHomeRecommend response1 = new ProductHomeRecommend();
        response1.setTenantId("SLP");
        response1.setSkuId("1000000000002401");
        response1.setSkuName("山东 移动手机 话费充值 10元 快充直充24小时自动充值 即时到帐 ");
        response1.setSalePrice(100L);
        response1.setProductSellPoint("与三大运营商官方合作，价格最低，安全便捷，企业及个人充值首选平台");
        list1.add(response1);
        pageInfo.setResult(list1);
        response.setPageInfo(pageInfo);
        return response;
    }

}
