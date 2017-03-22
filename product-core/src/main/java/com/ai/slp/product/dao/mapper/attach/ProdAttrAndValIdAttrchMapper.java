package com.ai.slp.product.dao.mapper.attach;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface ProdAttrAndValIdAttrchMapper {
	
	/**
	 * 查询类目下某个类型的属性标识和属性值标识集合 (state=1 catattr 为有效状态)
	 * @param tenantId
	 * @param productCatId
	 * @param attrType
	 * @return
	 * @author Gavin
	 * @UCUSER
	 */
	@Results({@Result(property = "attrId",column = "attr_id",javaType = String.class),
			  @Result(property = "attrvalueDefId",column = "ATTRVALUE_DEF_ID",javaType = String.class),
            })
	@Select("SELECT  a.ATTR_ID,v.ATTRVALUE_DEF_ID "+
			"from prod_cat_attr a ,prod_cat_attr_value v "+ 
			"where a.CAT_ATTR_ID = v.CAT_ATTR_ID "+
			"and a.ATTR_TYPE = #{attrType} "+
			"and a.PRODUCT_CAT_ID = #{productCatId} "+
			"and a.STATE = 1 "+
			"and a.TENANT_ID = #{tenantId}"
			)
    List<ProdAttrAndValIdAttrch> queryAttrAndValIdByCatIdAndType(@Param("tenantId")String tenantId,@Param("productCatId")String productCatId,@Param("attrType")String attrType);

}
