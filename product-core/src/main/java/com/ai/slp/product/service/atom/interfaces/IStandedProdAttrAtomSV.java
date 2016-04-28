package com.ai.slp.product.service.atom.interfaces;

import com.ai.slp.product.dao.mapper.bo.StandedProdAttr;

import java.util.List;

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
}
