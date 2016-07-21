package com.ai.slp.product.dubbo;

import com.ai.opt.sdk.appserver.DubboServiceStart;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.slp.user.api.keyinfo.interfaces.IUcKeyInfoSV;
import com.ai.slp.user.api.keyinfo.param.SearchGroupKeyInfoRequest;
import com.ai.slp.user.api.keyinfo.param.SearchGroupUserInfoResponse;
import org.junit.Test;

public class DubboStartTest {
    @Test
    public void testDubboStart(){
        DubboServiceStart.main(null);
    }

    @Test
    public void test1(){
        int num = 9;
        System.out.println(num+":"+(-num));
        System.out.println((byte)'a');
    }

    @Test
    public void userTest(){
        IUcKeyInfoSV ucKeyInfoSV = DubboConsumerFactory.getService(IUcKeyInfoSV.class);
        SearchGroupKeyInfoRequest request = new SearchGroupKeyInfoRequest();
        request.setTenantId("SLP");
        request.setUserId("000000000000000204");
        SearchGroupUserInfoResponse infoResponse = ucKeyInfoSV.searchGroupUserInfo(request);
        System.out.println(infoResponse.toString());
    }
}
