package com.ai.slp.product.dao.mapper.attach;

import java.util.List;

/**
 * Created by jackieliu on 16/9/22.
 */
public interface ProdSkuMapperExt {


    public List<ProdSkuInfoSes> selectByProdId(String prodId);
}
