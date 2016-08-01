package com.ai.slp.product.dao.mapper.attach;

import com.ai.slp.product.vo.StoGroupPageQueryVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by jackieliu on 16/8/1.
 */
public interface StorageGroupAttachMapper {

    /**
     * 统计数量
     * @param queryVo
     * @return
     */
    @SelectProvider(type = StorageGroupPageSqlProvider.class, method = "countSql")
    public int count(@Param("vo") StoGroupPageQueryVo queryVo);

    /**
     * 查询符合条件的库存组列表
     * @param queryVo
     * @return
     */
    @SelectProvider(type = StorageGroupPageSqlProvider.class, method = "queryPageSql")
    @Results({
            @Result(property = "tenantId", column = "TENANT_ID", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "storageGroupId", column = "STORAGE_GROUP_ID", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "storageGroupName", column = "STORAGE_GROUP_NAME", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "standedProdId", column = "STANDED_PROD_ID", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "standedProductName", column = "STANDED_PRODUCT_NAME", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "state", column = "STATE", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "serialNumber", column = "SERIAL_NUMBER", javaType = Long.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "lowSalePrice", column = "LOW_SALE_PRICE", javaType = Long.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "highSalePrice", column = "HIGH_SALE_PRICE", javaType = Long.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "createId", column = "CREATE_ID", javaType = Long.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "createTime", column = "CREATE_TIME", javaType = Timestamp.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "operId", column = "OPER_ID", javaType = Long.class, jdbcType = JdbcType.DECIMAL),
            @Result(property = "operTime", column = "OPER_TIME", javaType = Timestamp.class, jdbcType = JdbcType.TIMESTAMP)
    })
    public List<StorageGroupAttach4List> query(@Param("vo")StoGroupPageQueryVo queryVo,@Param("limitStr") String limitStr);
}
