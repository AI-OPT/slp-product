package com.ai.slp.product.service.atom.interfaces;

import com.ai.slp.product.dao.mapper.bo.StandedProdAttr;

import java.util.List;
import java.util.Map;

/**
 * 标准品属性值操作
 *
 * Created by liutong5 on 16/4/27.
 */
public interface IStandedProdAttrAtomSV {

    /**
     * 添加标准品属性
     * @param prodAttr
     * @return
     */
    public int installObj(StandedProdAttr prodAttr);

    /**
     * 更新标准品属性
     * @param prodAttr
     * @return
     */
    public int updateObj(StandedProdAttr prodAttr);

    /**
     * 查询某个标准品下的属性值
     * @param tenantId
     * @param standedId
     * @return
     */
    public List<StandedProdAttr> queryByNormProduct(String tenantId,String standedId);

    /**
     * 查询某个标准品下的属性值
     *
     * @param tenantId
     * @param standedId
     * @return map,K:标准品属性值标识,v:标准品属性值对象
     */
    public Map<Long,StandedProdAttr> queryMapByNormProduct(String tenantId, String standedId);

    /**
     * 将标准品的所有属性值设置为无效
     * @param tenantId
     * @param standedId
     * @return
     */
    public int updateInactiveByNormProduct(String tenantId,String standedId,Long operId);

    /**
     * 查询某个属性关联标准品的数量
     *
     * @param tenantId
     * @param attrId
     * @return
     */
    public int queryProdNumOfAttr(String tenantId,Long attrId);
    /**
     * 查询某个属性关联标准品的数量
     *
     * @param tenantId
     * @param attrvalueDefId
     * @return
     */
    public int queryProdNumOfAttrValue(String tenantId,String attrvalueDefId);

    /**
     * 查询指定标准品下某个属性的属性值
     *
     * @param tenantId
     * @param standedId
     * @param attrId
     * @return
     */
    public List<StandedProdAttr> queryAttrVal(String tenantId,String standedId,Long attrId);

    /**
     * 查询指定类目下某个属性值被标准品关联的数量
     * @param tenantId
     * @param catId
     * @param attrValDefId
     * @return
     */
    public int countOfAttrValOfCat(String tenantId,  String catId, String attrValDefId);

    /**
     * 查询标准品下某个属性值的信息
     *
     * @param tenantId
     * @param standedId
     * @param attrValId
     * @return
     */
    public StandedProdAttr queryByProdIdAndAttrValId(String tenantId,String standedId,String attrValId);
    
    
    /**
     * 
     * @param prodAttr
     * @param productId
     * @return
     * @author Gavin
     * @UCUSER
     */
	int installObj(StandedProdAttr prodAttr, long productId);

	/**
	 * 
	 * @param prodAttr
	 * @author Gavin
	 * @UCUSER
	 */
	public int updateStandedProdAttrBySQL(StandedProdAttr prodAttr);

	/**
	 * 
	 * @param prodAttr
	 * @return
	 * @author Gavin
	 * @UCUSER
	 */
	int updateSateBySQL(StandedProdAttr prodAttr);
}
