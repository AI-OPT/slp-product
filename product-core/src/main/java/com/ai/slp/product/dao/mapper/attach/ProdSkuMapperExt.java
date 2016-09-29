package com.ai.slp.product.dao.mapper.attach;

import java.util.List;
import java.util.Map;

/**
 * Created by jackieliu on 16/9/22.
 */
public interface ProdSkuMapperExt {

    /**
     * 查询商品的信息,
     * 用于更新至搜索引擎
     * @param prodId
     * @return
     */
    public List<ProdSkuInfoSes> selectByProdId(String prodId);

    /**
     * 查询所有在售商品的列表
     * @param params 包括开始条目数和结束条目数
     * @return
     */
    public List<ProdSkuInfoSes> selectActive(Map<String,Integer> params);
}
