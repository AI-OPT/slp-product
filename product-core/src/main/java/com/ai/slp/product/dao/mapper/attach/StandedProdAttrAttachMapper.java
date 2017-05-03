package com.ai.slp.product.dao.mapper.attach;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 标准品属性扩展
 * Created by jackieliu on 16/8/18.
 */
public interface StandedProdAttrAttachMapper {

    /**
     * 统计某个类目下某个属性值被使用的数量
     */
    @Select("SELECT count(spa.STANDED_PROD_ATTR_ID) " +
            "FROM standed_prod_attr spa LEFT JOIN standed_product sp ON spa.STANDED_PROD_ID = sp.STANDED_PROD_ID" +
            " WHERE spa.tenant_id=#{tenantId} AND sp.tenant_id=#{tenantId} AND sp.PRODUCT_CAT_ID = #{catId} " +
            "AND spa.ATTRVALUE_DEF_ID = #{attrValDefId} AND sp.state = '1' AND spa.state='1' ")
    public int countOfAttrValOfCat(
            @Param("tenantId")String tenantId, @Param("catId") String catId, @Param("attrValDefId") String attrValDefId);
    
    /**标准品信息
     * 更新
     * @param standedProdAttrId
     * @param attrValueName
     * @param attrValueName2
     * @return
     * @author Gavin
     * @UCUSER
     */
    @Update("update standed_prod_attr "
    		+ "set  ATTR_VALUE_NAME = #{attrValueName},ATTR_VALUE_NAME2 = #{attrValueName2} "
    		+ "where STANDED_PROD_ATTR_ID = #{standedProdAttrId} ")
	public int updateStandedProdAttrBySQL(@Param("standedProdAttrId") Long standedProdAttrId,
			@Param("attrValueName") String attrValueName,
			@Param("attrValueName2") String attrValueName2);


    /**
     * 更新标准品状态
     * @param standedProdAttrId
     * @param state
     * @param operId
     * @param operTime
     * @return
     * @author Gavin
     * @UCUSER
     */
    @Update("update standed_prod_attr "
    		+ "set STATE = #{state},  OPER_ID = #{operId},OPER_TIME = #{operTime} "
    		+ "where STANDED_PROD_ATTR_ID = #{standedProdAttrId} ")
	public int updateSateBySQL(@Param("standedProdAttrId") Long standedProdAttrId, 
			@Param("state") String state, 
			@Param("operId") Long operId, 
			@Param("operTime") Timestamp operTime);
    
    
}
