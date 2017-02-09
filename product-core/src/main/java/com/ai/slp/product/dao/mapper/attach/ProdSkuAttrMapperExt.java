package com.ai.slp.product.dao.mapper.attach;


import com.ai.slp.product.dao.mapper.bo.product.ProdSkuAttrCriteria;

import java.util.List;

/**
 * SKU属性值扩展
 * Created by jackieliu on 16/6/14.
 */
public interface ProdSkuAttrMapperExt {
	//查询属性值id
    List<String> selectAttrValId(ProdSkuAttrCriteria example);
}
