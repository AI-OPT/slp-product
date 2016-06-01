package com.ai.slp.product.dao.mapper.attach;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by jackieliu on 16/6/1.
 */
public interface ProdSaleAllAttachMapper {
    /**
     * 查询某个商品的销量
     *
     * @param tenantId 租户id
     * @param productId 商品id
     * @return
     */
    @Result(javaType = java.lang.Long.class)
    @Select("SELECT SUM(SALE_NUM) FROM PROD_SALE_ALL WHERE TENANT_ID=#{tenantId} and PROD_ID=#{productId} ")
    Long selectCatAttr(@Param("tenantId")String tenantId, @Param("productId")String productId);

}
