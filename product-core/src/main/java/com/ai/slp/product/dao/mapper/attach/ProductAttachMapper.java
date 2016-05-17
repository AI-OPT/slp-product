package com.ai.slp.product.dao.mapper.attach;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;

/**
 * 商品属性扩展
 * 
 * Date: 2016年5月13日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public interface ProductAttachMapper {
	/**
	 * 结算管理-商城商品列表分页查询
	 */
	@SelectProvider(type = ProductPageSqlProvider.class, method = "queryProductPage")
	@Results({
			@Result(id = true, property = "prodId", column = "prod_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "prodName", column = "prod_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "productCatId", column = "product_cat_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "productCatName", column = "product_cat_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "productType", column = "product_type", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "storageGroupId", column = "storage_group_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "storageGroupName", column = "storage_group_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "standedProdId", column = "standed_prod_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "standedProdName", column = "standed_product_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
	})
	public List<ProductAttach> getProductPage(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize,
			@Param("productCatId") String productCatId, @Param("prodId") String prodId,
			@Param("prodName") String prodName, @Param("productType") String productType,
			@Param("storageGroupId") String storageGroupId, @Param("storageGroupName") String storageGroupName,
			@Param("standedProdId") Long standedProdId, @Param("standedProdName") String standedProdName,
			@Param("tenantId") String tenantId);

}
