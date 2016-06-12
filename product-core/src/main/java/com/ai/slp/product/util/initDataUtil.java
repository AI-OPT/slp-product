package com.ai.slp.product.util;

import java.util.ArrayList;
import java.util.List;

import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.common.api.area.interfaces.IGnAreaQuerySV;
import com.ai.slp.common.api.area.param.GnAreaVo;
import com.ai.slp.product.api.webfront.param.ProductAttrInfo;
import com.ai.slp.product.constants.ProductHomeConstants;

public class initDataUtil {
    
    
    public static List<ProductAttrInfo> getArea(){
        //地区
        List<ProductAttrInfo> areas = new ArrayList<ProductAttrInfo>();
        IGnAreaQuerySV iGnAreaQuerySV = DubboConsumerFactory.getService("iGnAreaQuerySV");
        areas = new ArrayList<ProductAttrInfo>();
       List<GnAreaVo>   areaList =  iGnAreaQuerySV.getProvinceList();
       ProductAttrInfo attrinfo = new ProductAttrInfo();
       attrinfo.setAttrDefId("");
       attrinfo.setAttrDefValue("全国通用");
       areas.add(attrinfo);
       if(!CollectionUtil.isEmpty(areaList)){
           for(GnAreaVo vo: areaList){
               ProductAttrInfo info = new ProductAttrInfo();
               info.setAttrDefId(vo.getAreaCode());
               info.setAttrDefValue(vo.getAreaName());
               areas.add(info);
           } 
       }
       return areas;
    }
    public static List<ProductAttrInfo> getAgent(){
        //代理商
        List<ProductAttrInfo> agents = new ArrayList<ProductAttrInfo>();
        agents = new ArrayList<ProductAttrInfo>();
       ProductAttrInfo agent = new ProductAttrInfo();
       agent.setAttrDefValue("中国移动");
       agent.setAttrDefId("100002");
       agents.add(agent);
       agent = new ProductAttrInfo();
       agent.setAttrDefValue("中国电信");
       agent.setAttrDefId("100001");
       agents.add(agent);
       agent = new ProductAttrInfo();
       agent.setAttrDefId("100003");
       agent.setAttrDefValue("中国联通");
       agents.add(agent);
       return agents;
    }
    public static List<ProductAttrInfo>getAccountsOrFlow(String proCatId){
        if(ProductHomeConstants.PHONE_BILL_PRO_CAT_ID.equals(proCatId)){
          //面额
            List<ProductAttrInfo> accounts = new ArrayList<ProductAttrInfo>();
           accounts = new ArrayList<ProductAttrInfo>();
           ProductAttrInfo acccount = new ProductAttrInfo();
           acccount.setAttrDefId("100004");
           acccount.setAttrDefValue("10元");
           accounts.add(acccount);
           acccount = new ProductAttrInfo();
           acccount.setAttrDefId("100005");
           acccount.setAttrDefValue("20元");
           accounts.add(acccount);
           acccount = new ProductAttrInfo();
           acccount.setAttrDefId("100006");
           acccount.setAttrDefValue("30元");
           accounts.add(acccount);
           acccount = new ProductAttrInfo();
           acccount.setAttrDefId("100007");
           acccount.setAttrDefValue("50元");
           accounts.add(acccount);
           acccount = new ProductAttrInfo();
           acccount.setAttrDefId("100008");
           acccount.setAttrDefValue("100元");
           accounts.add(acccount);
           acccount = new ProductAttrInfo();
           acccount.setAttrDefId("100009");
           acccount.setAttrDefValue("200元");
           accounts.add(acccount);
           acccount = new ProductAttrInfo();
           acccount.setAttrDefId("100010");
           acccount.setAttrDefValue("300元");
           accounts.add(acccount);
           acccount = new ProductAttrInfo();
           acccount.setAttrDefId("100011");
           acccount.setAttrDefValue("500元");
           accounts.add(acccount);
           return accounts;
        }else{
            //流量
            List<ProductAttrInfo> accounts = new ArrayList<ProductAttrInfo>();
            ProductAttrInfo acccount = new ProductAttrInfo();
            acccount.setAttrDefId("100047");
            acccount.setAttrDefValue("10MB");
            accounts.add(acccount);
            acccount = new ProductAttrInfo();
            acccount.setAttrDefId("100048");
            acccount.setAttrDefValue("30MB");
            accounts.add(acccount);
            acccount = new ProductAttrInfo();
            acccount.setAttrDefId("100049");
            acccount.setAttrDefValue("50MB");
            accounts.add(acccount);
            acccount = new ProductAttrInfo();
            acccount.setAttrDefId("100050");
            acccount.setAttrDefValue("70MB");
            accounts.add(acccount);
            acccount = new ProductAttrInfo();
            acccount.setAttrDefId("100051");
            acccount.setAttrDefValue("100MB");
            accounts.add(acccount);
            acccount = new ProductAttrInfo();
            acccount.setAttrDefId("100052");
            acccount.setAttrDefValue("200MB");
            accounts.add(acccount);
            acccount = new ProductAttrInfo();
            acccount.setAttrDefId("100053");
            acccount.setAttrDefValue("250MB");
            accounts.add(acccount);
            acccount = new ProductAttrInfo();
            acccount.setAttrDefId("100054");
            acccount.setAttrDefValue("500MB");
            accounts.add(acccount); 
            return accounts;
        }
      
    }

}
