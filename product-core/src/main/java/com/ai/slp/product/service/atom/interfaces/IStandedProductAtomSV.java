package com.ai.slp.product.service.atom.interfaces;

import com.ai.slp.product.dao.mapper.bo.StandedProduct;

/**
 * 对标准品的原子操作
 *
 * Created by liutong5 on 16/4/27.
 */
public interface IStandedProductAtomSV {
    /**
     * 添加标准品信息
     *
     * @param standedProduct
     * @return
     */
    public int installObj(StandedProduct standedProduct);

    /**
     * 更新标准品信息
     *
     * @param standedProduct
     * @return
     */
    public int updateObj(StandedProduct standedProduct);

    /**
     * 删除标准品信息
     * @param tenantId
     * @param standedProdId
     * @return
     */
    public int deleteObj(String tenantId,String standedProdId);

    /**
     * 查询租户下的某个标准品
     *
     * @param tenantId
     * @param standedProdId
     * @return
     */
    public StandedProduct selectById(String tenantId,String standedProdId);
}
