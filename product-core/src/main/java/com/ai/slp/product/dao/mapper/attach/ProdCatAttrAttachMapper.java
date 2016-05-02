package com.ai.slp.product.dao.mapper.attach;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.sql.Timestamp;
import java.util.List;

/**
 * 类目属性扩展
 * Created by jackieliu on 16/5/2.
 */
public interface ProdCatAttrAttachMapper {


    /**
     * 查询类目的指定类型的属性信息
     *
     * @param tenantId 租户id
     * @param productCatId 类目id
     * @param attrType 属性类型
     * @return
     */
    @Results({@Result(id = true,property = "catAttrId",column = "cat_attr_id",javaType = String.class),
            @Result(property="tenantId",column="tenant_id",javaType=String.class,jdbcType= JdbcType.VARBINARY),
            @Result(property ="attrId",column = "attr_id",javaType = Long.class,jdbcType = JdbcType.NUMERIC),
            @Result(property ="attrName",column = "attr_name",javaType = String.class,jdbcType = JdbcType.VARCHAR),
            @Result(property ="firstLetter",column = "first_letter",javaType = String.class,jdbcType = JdbcType.VARCHAR),
            @Result(property ="valueWay",column = "value_way",javaType = String.class,jdbcType = JdbcType.VARCHAR),
            @Result(property ="state",column = "state",javaType = String.class,jdbcType = JdbcType.VARCHAR),
            @Result(property ="productCatId",column = "product_cat_id",javaType = String.class,jdbcType = JdbcType.VARCHAR),
            @Result(property ="attrType",column = "attr_type",javaType = String.class,jdbcType = JdbcType.VARCHAR),
            @Result(property ="isPicture",column = "is_picture",javaType = String.class,jdbcType = JdbcType.VARCHAR),
            @Result(property ="isNecessary",column = "is_necessary",javaType = String.class,jdbcType = JdbcType.VARCHAR),
            @Result(property ="serialNumber",column = "serial_number",javaType = Short.class,jdbcType = JdbcType.NUMERIC),
            })
    @Select("SELECT ca.tenant_id,ca.attr_id,ad.attr_name,ad.first_letter,ad.value_way,ca.state,ca.cat_attr_id,ca.product_cat_id,ca.attr_type,ca.is_picture,ca.is_necessary,ca.serial_number " +
            "FROM prod_cat_attr ca LEFT JOIN prod_attr_def ad WHER ON ca.attr_id = ad.attr_id WHERE ca.tenant_id=#{tenantId} " +
            "AND ca.product_cat_id = #{productCatId} AND ca.attr_type = #{attrType}")
    List<ProdCatAttrAttch> selectCatAttr(@Param("tenantId")String tenantId,@Param("productCatId")String productCatId,@Param("attrType")String attrType);
}
