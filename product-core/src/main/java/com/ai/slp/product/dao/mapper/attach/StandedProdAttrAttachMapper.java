package com.ai.slp.product.dao.mapper.attach;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 标准品属性扩展
 * Created by jackieliu on 16/8/18.
 */
public interface StandedProdAttrAttachMapper {

    /**
     * 统计某个类目下某个属性值被使用的数量
     */
    @Select("SELECT count(spa.STANDED_PROD_ATTR_ID) " +
            "FROM standed_prod_attr spa LEFT JOIN standed_product sp ON spa.STANDED_PROD_ID = sp.STANDED_PROD_ID" +
            " WHERE spa.tenant_id=#{tenantId} AND sp.tenant_id=#{tenantId} AND sp.PRODUCT_CAT_ID = #{catId} " +
            "AND spa.ATTRVALUE_DEF_ID = #{attrValDefId} AND sp.state = '1' AND spa.state='1' ")
    public int countOfAttrValOfCat(
            @Param("tenantId")String tenantId, @Param("catId") String catId, @Param("attrValDefId") String attrValDefId);
}