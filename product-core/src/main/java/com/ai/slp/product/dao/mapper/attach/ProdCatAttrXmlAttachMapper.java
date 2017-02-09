package com.ai.slp.product.dao.mapper.attach;

import com.ai.slp.product.dao.mapper.bo.ProdCatAttrCriteria;

import java.util.List;

/**
 * Created by jackieliu on 16/8/17.
 */
public interface ProdCatAttrXmlAttachMapper {
//查询类目属性id
    List<String> selectCatAttrIdS(ProdCatAttrCriteria example);
}
