package com.ai.slp.product.dao.mapper.attach;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * Created by jackieliu on 16/6/2.
 */
public interface ProdSkuAttachMapper {


    /**
     * 查询全国快充产品
     * @return
     */
    @SelectProvider(type = ProdSkuAttacProvider.class, method = "queryNationwideFastProd")
    @Results({
            @Result(property = "skuId", column = "sku_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "prodId", column = "prod_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "standedProdId", column = "standed_prod_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "storageGroupId", column = "storage_group_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "attrId", column = "attr_id", javaType = Long.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "attrvalueDefId", column = "attrvalue_def_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "attrValueName", column = "attr_value_name", javaType = String.class, jdbcType = JdbcType.VARCHAR)
    })
    public List<ProdFastSkuAttach> queryNationwideFast(
            @Param("tenantId")String tenantId,@Param("productCatId")String productCatId,
            @Param("basicOrgId")String basicOrgId,@Param("userType")String userType,
            @Param("userId")String userId,@Param("attrId")Long attrId);


    /**
     * 查询指定地域快充产品
     * @return
     */
    @SelectProvider(type = ProdSkuAttacProvider.class, method = "queryLocalFastProd")
    @Results({
            @Result(property = "skuId", column = "sku_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "prodId", column = "prod_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "standedProdId", column = "standed_prod_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "storageGroupId", column = "storage_group_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "attrId", column = "attr_id", javaType = Long.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "attrvalueDefId", column = "attrvalue_def_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "attrValueName", column = "attr_value_name", javaType = String.class, jdbcType = JdbcType.VARCHAR)
    })
    public List<ProdFastSkuAttach> queryLocalFast(
            @Param("tenantId")String tenantId,@Param("productCatId")String productCatId,
            @Param("basicOrgId")String basicOrgId,@Param("userType")String userType,
            @Param("userId")String userId,@Param("attrId")Long attrId,@Param("provCode")Integer provCode
    );
}
