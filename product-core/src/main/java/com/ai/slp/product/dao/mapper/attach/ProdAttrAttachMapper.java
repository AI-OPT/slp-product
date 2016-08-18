package com.ai.slp.product.dao.mapper.attach;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 销售商品属性扩展
 * Created by jackieliu on 16/8/18.
 */
public interface ProdAttrAttachMapper {

    /**
     * 统计某个类目下某个属性值被使用的数量
     */
    @Select("SELECT count(pa.PROD_ATTR_ID) " +
            "FROM prod_attr pa LEFT JOIN product p ON pa.PROD_ID = p.PROD_ID" +
            " WHERE pa.tenant_id=#{tenantId} AND p.tenant_id=#{tenantId} AND p.PRODUCT_CAT_ID = #{catId} " +
            "AND pa.ATTRVALUE_DEF_ID = #{attrValDefId} AND p.state <> '7' AND pa.state='1' ")
    public int countOfAttrValOfCat(
            @Param("tenantId")String tenantId, @Param("catId") String catId, @Param("attrValDefId") String attrValDefId);
}
