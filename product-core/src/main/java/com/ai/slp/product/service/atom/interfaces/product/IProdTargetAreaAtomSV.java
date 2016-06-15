package com.ai.slp.product.service.atom.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdTargetArea;

import java.util.List;

/**
 * 商品目标地域原子操作
 * Created by jackieliu on 16/6/2.
 */
public interface IProdTargetAreaAtomSV {

    /**
     * 根据地域编码查询目标地域信息
     *
     * @param tenantId 租户ID
     * @param prodId 销售商品标识
     * @param provCode 省份编码
     * @param hasDiscard 是否包含废弃状态
     * @return
     */
    public List<ProdTargetArea> queryByAreaCode(String tenantId,String prodId,Integer provCode,boolean hasDiscard);

}
