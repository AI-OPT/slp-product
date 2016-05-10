package com.ai.slp.product.service.atom.interfaces.product;

import com.ai.slp.product.dao.mapper.interfaces.product.ProdSkuAttrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jackieliu on 16/5/10.
 */
public interface IProdSkuAttrAtomSV {

    //查询商品SKU下属性值被使用次数
    public int queryAttrValNumOfSku(String tenantId,String prodId,String valId);

}
