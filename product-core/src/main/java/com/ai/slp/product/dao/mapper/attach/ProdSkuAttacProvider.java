package com.ai.slp.product.dao.mapper.attach;

import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.constants.ProductConstants;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * Created by jackieliu on 16/6/3.
 */
public class ProdSkuAttacProvider {

    /**
     * 获取查询全国快充产品信息
     *
     * @param param
     * @return
     */
    public String queryNationwideFastProd(Map<String, Object> param){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("SELECT a.PROD_ID,a.STANDED_PROD_ID,a.STORAGE_GROUP_ID,"+
                "c.ATTR_ID,c.ATTRVALUE_DEF_ID,c.ATTR_VALUE_NAME," + "e.SKU_ID");
        stringBuffer.append(" FROM product a ,standed_prod_attr c,prod_audiences d,prod_sku e");
        stringBuffer.append(" WHERE a.IS_SALE_NATIONWIDE = 'Y' AND a.recharge_type = 'D'");
        stringBuffer.append(" AND a.PROD_ID = d.PROD_ID AND a.STANDED_PROD_ID = c.STANDED_PROD_ID");
        stringBuffer.append(" AND a.PROD_ID = e.PROD_ID AND a.STATE = '"+ ProductConstants.Product.State.IN_SALE+"'");
        stringBuffer.append(" AND c.STATE = '" + CommonConstants.STATE_ACTIVE + "'");
        stringBuffer.append(" AND d.STATE = '" + CommonConstants.STATE_ACTIVE + "'");
        stringBuffer.append(" AND e.STATE = '" + CommonConstants.STATE_ACTIVE + "'");
        stringBuffer.append(" AND d.USER_TYPE = '"+param.get("userType")+"' AND c.ATTR_ID = "+param.get("attrId"));
        stringBuffer.append(" AND a.TENANT_ID = '"+param.get("tenantId")+"' AND c.TENANT_ID = '"+param.get("tenantId")+"'");
        stringBuffer.append(" AND d.TENANT_ID = '"+param.get("tenantId")+"' AND e.TENANT_ID = '"+param.get("tenantId")+"'");
        stringBuffer.append(" AND d.USER_ID = '"+ProductConstants.ProdAudiences.userId.USER_TYPE+"'");
        String userId = param.containsKey("userId")?(String) param.get("userId"):null;
        if (StringUtils.isNotBlank(userId)){
            stringBuffer.append(" AND d.USER_ID = '"+userId+"'");
        }
        stringBuffer.append(" AND a.product_cat_id = '"+param.get("productCatId")+"' AND a.BASIC_ORG_ID = '"+param.get("basicOrgId")+"'");
        stringBuffer.append(" GROUP BY a.PROD_ID,c.attr_id,c.attrvalue_def_id,c.attr_value_name,c.STANDED_PROD_ID,e.SKU_ID");

        return stringBuffer.toString();
    }

    /**
     * 查询指定地域快充产品
     * @param param
     * @return
     */
    public String queryLocalFastProd(Map<String, Object> param){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("SELECT a.PROD_ID,a.STANDED_PROD_ID,a.STORAGE_GROUP_ID,"+
                "c.ATTR_ID,c.ATTRVALUE_DEF_ID,c.ATTR_VALUE_NAME," + "e.SKU_ID");
        stringBuffer.append(" FROM product a, prod_target_area b, standed_prod_attr c, prod_audiences d, prod_sku e");
        stringBuffer.append(" WHERE a.IS_SALE_NATIONWIDE = '"+ProductConstants.Product.IsSaleNationwide.NO
                +"' AND a.recharge_type = '"+ProductConstants.Product.RechargeType.DIRECT+"'");
        stringBuffer.append(" AND a.PROD_ID = d.PROD_ID AND a.STANDED_PROD_ID = c.STANDED_PROD_ID AND a.PROD_ID = b.PROD_ID");
        stringBuffer.append(" AND a.PROD_ID = e.PROD_ID AND a.STATE = '"+ ProductConstants.Product.State.IN_SALE+"'");
        stringBuffer.append(" AND b.PROV_CODE = " + param.get("provCode"));
        stringBuffer.append(" AND b.STATE = '" + CommonConstants.STATE_ACTIVE + "'");
        stringBuffer.append(" AND c.STATE = '" + CommonConstants.STATE_ACTIVE + "'");
        stringBuffer.append(" AND d.STATE = '" + CommonConstants.STATE_ACTIVE + "'");
        stringBuffer.append(" AND e.STATE = '" + CommonConstants.STATE_ACTIVE + "'");
        stringBuffer.append(" AND d.USER_TYPE = '"+param.get("userType")+"' AND c.ATTR_ID = "+param.get("attrId"));
        stringBuffer.append(" AND a.TENANT_ID = '"+param.get("tenantId")+"' AND c.TENANT_ID = '"+param.get("tenantId")+"'");
        stringBuffer.append(" AND d.TENANT_ID = '"+param.get("tenantId")+"' AND e.TENANT_ID = '"+param.get("tenantId")+"'");
        stringBuffer.append(" AND d.USER_ID = '"+ProductConstants.ProdAudiences.userId.USER_TYPE+"'");
        String userId = param.containsKey("userId")?(String) param.get("userId"):null;
        if (StringUtils.isNotBlank(userId)){
            stringBuffer.append(" AND d.USER_ID = '"+userId+"'");
        }
        stringBuffer.append(" AND a.product_cat_id = '"+param.get("productCatId")+"' AND a.BASIC_ORG_ID = '"+param.get("basicOrgId")+"'");
        stringBuffer.append(" GROUP BY a.PROD_ID,c.attr_id,c.attrvalue_def_id,c.attr_value_name,c.STANDED_PROD_ID,e.SKU_ID");

        return stringBuffer.toString();
    }
}
