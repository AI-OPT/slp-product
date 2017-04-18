package com.ai.slp.product.dao.mapper.attach;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 标准品扩展
 * Date: 2017年4月18日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author Gavin
 */
public interface StandedProdAttachMapper {

	/**
	 * 根据更新标准品部分属性
	 */
	@Update("update product set STANDED_PRODUCT_NAME=#{standedProductName},PRODUCT_TYPE=#{productType},STATE=#{state},OPER_TIME=#{operTime} "
			+ "where STANDED_PROD_ID = #{standedProdId} ")
	public int updateStandedProductInfo(@Param("standedProdId") String standedProdId,
			@Param("standedProductName") String standedProductName,
			@Param("productType") String productType,
			@Param("state") String state,
			@Param("operTime") Timestamp operTime);
}
