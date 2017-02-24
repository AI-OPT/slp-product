package com.ai.slp.product.util;

import java.util.ArrayList;
import java.util.List;

import com.ai.slp.product.api.webfront.param.ProductAttrInfo;
import com.ai.slp.product.constants.ProductHomeConstants;

/**
 *地域信息工具类 
 *
 */
public final class InitDataUtil {

    private InitDataUtil() {
    }

    /**
     * 获取地域信息
     */
    public static List<ProductAttrInfo> getArea() {
        // 地区
        List<ProductAttrInfo> areas = new ArrayList<ProductAttrInfo>();
        ProductAttrInfo attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100085");
        attrinfo.setAttrDefValue("全国通用");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100013");
        attrinfo.setAttrDefValue("北京");
        areas.add(attrinfo);

        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100014");
        attrinfo.setAttrDefValue("天津");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100015");
        attrinfo.setAttrDefValue("河北");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100016");
        attrinfo.setAttrDefValue("山西");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100017");
        attrinfo.setAttrDefValue("内蒙");
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100018");
        attrinfo.setAttrDefValue("辽宁");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100019");
        attrinfo.setAttrDefValue("吉林");
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100020");
        attrinfo.setAttrDefValue("黑龙江");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100021");
        attrinfo.setAttrDefValue("上海");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100022");
        attrinfo.setAttrDefValue("江苏");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100023");
        attrinfo.setAttrDefValue("浙江");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100024");
        attrinfo.setAttrDefValue("安徽");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100025");
        attrinfo.setAttrDefValue("福建");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100026");
        attrinfo.setAttrDefValue("江西");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100027");
        attrinfo.setAttrDefValue("山东");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100028");
        attrinfo.setAttrDefValue("河南");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100029");
        attrinfo.setAttrDefValue("湖北");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100030");
        attrinfo.setAttrDefValue("湖南");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100031");
        attrinfo.setAttrDefValue("广东");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100032");
        attrinfo.setAttrDefValue("广西");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100033");
        attrinfo.setAttrDefValue("海南");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100034");
        attrinfo.setAttrDefValue("重庆");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100035");
        attrinfo.setAttrDefValue("四川");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100036");
        attrinfo.setAttrDefValue("贵州");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100037");
        attrinfo.setAttrDefValue("云南");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100038");
        attrinfo.setAttrDefValue("西藏");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100039");
        attrinfo.setAttrDefValue("陕西");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100040");
        attrinfo.setAttrDefValue("甘肃");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100041");
        attrinfo.setAttrDefValue("青海");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100042");
        attrinfo.setAttrDefValue("宁夏");
        areas.add(attrinfo);
        attrinfo = new ProductAttrInfo();
        attrinfo.setAttrDefId("100043");
        attrinfo.setAttrDefValue("新疆");
        areas.add(attrinfo);
        return areas;
    }

    /**
     * 获取运营商信息
     */
    public static List<ProductAttrInfo> getAgent() {
        // 代理商
        List<ProductAttrInfo> agents = new ArrayList<ProductAttrInfo>();
        ProductAttrInfo agent = new ProductAttrInfo();
        agent.setAttrDefValue("中国移动");
        agent.setAttrDefId("10");
        agents.add(agent);
        agent = new ProductAttrInfo();
        agent.setAttrDefValue("中国电信");
        agent.setAttrDefId("11");
        agents.add(agent);
        agent = new ProductAttrInfo();
        agent.setAttrDefId("12");
        agent.setAttrDefValue("中国联通");
        agents.add(agent);
        return agents;
    }

    /**
     * 获取面额对应的code
     * @param proCatId
     * @return
     * @author Gavin
     * @UCUSER
     */
    public static List<ProductAttrInfo> getAccountsOrFlow(String proCatId) {
        if (ProductHomeConstants.PHONE_BILL_PRO_CAT_ID.equals(proCatId)) {
            // 面额
            List<ProductAttrInfo> accounts = new ArrayList<ProductAttrInfo>();
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
        } else {
            // 流量
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
