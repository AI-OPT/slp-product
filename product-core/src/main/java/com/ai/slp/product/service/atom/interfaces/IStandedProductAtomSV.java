package com.ai.slp.product.service.atom.interfaces;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.vo.StandedProdPageQueryVo;

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
     * 查询租户下的某个标准品
     *
     * @param tenantId
     * @param standedProdId
     * @return
     */
    public StandedProduct selectById(String tenantId,String standedProdId);

    /**
     * 根据条件进行分页查寻
     * @return
     */
    public PageInfo<StandedProduct> queryForPage(StandedProdPageQueryVo request);

    /**
     * 查询类目下标准品数量
     *
     * @param catId
     * @return
     */
    public int queryByCatId(String catId);
}
