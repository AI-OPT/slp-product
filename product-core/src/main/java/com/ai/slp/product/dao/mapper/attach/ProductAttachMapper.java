package com.ai.slp.product.dao.mapper.attach;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.ai.slp.product.api.product.param.ProductRouteGroupInfo;
import com.ai.slp.product.vo.ProdRouteGroupQueryVo;

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
			@Result(property = "standedProdName", column = "standed_product_name", javaType = String.class, jdbcType = JdbcType.VARCHAR)
	})
	public List<ProductAttach> getProductPage(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize,
			@Param("productCatId") String productCatId, @Param("prodId") String prodId,
			@Param("prodName") String prodName, @Param("productType") String productType,
			@Param("storageGroupId") String storageGroupId, @Param("storageGroupName") String storageGroupName,
			@Param("standedProdId") Long standedProdId, @Param("standedProdName") String standedProdName,
			@Param("tenantId") String tenantId);

	/**
	 * 统计符合要求的商品数量,包括路由组信息
	 * @return
     */
	@SelectProvider(type = ProductPageSqlProvider.class, method = "countProdRouteGroup")
	public int countProductAndRouteGroup(ProdRouteGroupQueryVo queryVo);

	/**
	 * 查询符合要求的商品信息,包括路由组标识
	 * @param queryVo
	 * @return
     */
	@SelectProvider(type = ProductPageSqlProvider.class, method = "queryProdRouteGroup")
	@Results({
			@Result(id = true, property = "productId", column = "prod_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "productName", column = "prod_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "routeGroupId", column = "route_group_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "standedProdId", column = "standed_prod_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "standedProdName", column = "standed_product_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "state", column = "state", javaType = String.class, jdbcType = JdbcType.VARCHAR)
	})
	public List<ProductRouteGroupInfo> queryProductAndRouteGroupPage(ProdRouteGroupQueryVo queryVo);
	
    /**
     * 修改商品状态
     * @param record
     * @return
     * @ApiDocMethod
     */
    @Update("update product set STATE=#{state} where prod_id = #{prodId} ")
	public int updateProdState(@Param("state") String state,@Param("prodId") String prodId);
    
	/**
     * 修改商品状态
     * @param record
     * @return
     * @ApiDocMethod
     */
    @Update("update product set STATE=#{state},OPER_ID=#{operId} where prod_id = #{prodId} ")
	public int updateProdStateNew(@Param("prodId") String prodId,
			@Param("state") String state,
			@Param("operId") String operId);
    
    
    /**
     * 修改商品信息
     * @param record
     * @return
     * @ApiDocMethod
     */
    @Update("update product set PROD_NAME=#{prodName},PRODUCT_TYPE=#{productType} where PROD_ID = #{prodId} ")
	public int updateProdInfo(@Param("prodId") String prodId,
			@Param("prodName") String prodName,
			@Param("productType") String productType);
    
}
