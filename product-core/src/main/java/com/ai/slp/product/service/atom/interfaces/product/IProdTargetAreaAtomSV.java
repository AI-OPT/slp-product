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
     * @param provCode 省份编码
     * @param cityCode 城市编码
     * @param countyCode 区县编码
     * @param hasDiscard 是否包含废弃状态
     * @return
     */
    public List<ProdTargetArea> queryByAreaCode(String tenantId,Integer provCode,Integer cityCode,Integer countyCode,boolean hasDiscard);
}
