package com.ai.slp.product.service.business.interfaces.search;

import java.util.List;

import com.ai.slp.product.dao.mapper.attach.ProdSkuInfoSes;
import com.ai.slp.product.search.bo.SKUInfo;

public interface ISKUIndexBusiSV {
	/**
	 * 更新
	 */
    public boolean updateSKUIndex(String productId,long upTime);
    /**
	 * 根据skuid删除
	 */
    public boolean deleteSKUIndexBySKUId(String skuId);
    /**
	 *根据productid删除
	 */
    public boolean deleteSKUIndexByProductId(String productId);

    /**
     * 完善将要添加到搜索引擎中数据
     * @param skuInfoSesList
     * @return 若入参不为空,则返回填充后数据,否则返回空集合
     */
    public List<SKUInfo> fillSkuInfo(List<ProdSkuInfoSes> skuInfoSesList);
}
