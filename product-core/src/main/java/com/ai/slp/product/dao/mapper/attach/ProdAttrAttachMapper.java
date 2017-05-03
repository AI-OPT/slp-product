package com.ai.slp.product.dao.mapper.attach;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.ai.slp.product.search.bo.AttrInfo;

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

    /**
     * 查询sku属性
     */
    @Results({
            @Result(property ="attrvaluedefid",column = "attrValueDefId",javaType=String.class,jdbcType= JdbcType.VARCHAR),
            @Result(property ="attrid",column = "attrID",javaType = String.class,jdbcType = JdbcType.VARCHAR),
            @Result(property ="attrvalue",column = "attrValue",javaType = String.class,jdbcType = JdbcType.VARCHAR),
            @Result(property ="attrname",column = "attrName",javaType = String.class,jdbcType = JdbcType.VARCHAR)
    })
    @Select("SELECT spa.ATTR_ID as attrID, spa.ATTR_VALUE_NAME as attrValue," +
            "spa.ATTRVALUE_DEF_ID as attrValueDefId, PROD_ATTR_DEF.ATTR_NAME as attrName " +
            "FROM (product inner join standed_prod_attr spa on product.STANDED_PROD_ID = spa.STANDED_PROD_ID) " +
            "inner join PROD_ATTR_DEF  on spa.ATTR_ID=PROD_ATTR_DEF.ATTR_ID " +
            "where product.PROD_ID = #{prodId} AND spa.STATE='1' AND PROD_ATTR_DEF.STATE='1' ")
    public List<AttrInfo> selectAllAttrOfSku(@Param("prodId") String prodId);
}
