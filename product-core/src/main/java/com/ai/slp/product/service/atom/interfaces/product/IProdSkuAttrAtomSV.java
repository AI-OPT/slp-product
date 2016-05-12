package com.ai.slp.product.service.atom.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdSkuAttr;
import com.ai.slp.product.dao.mapper.interfaces.product.ProdSkuAttrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jackieliu on 16/5/10.
 */
public interface IProdSkuAttrAtomSV {

    //查询商品SKU下属性值被使用次数
    public int queryAttrValNumOfSku(String tenantId,String prodId,String valId);

    /**
     * 废弃SKU单品下的属性
     *
     * @param tenantId
     * @param skuId sku单品标识
     * @param operId 操作者ID
     * @return
     */
    public int discardAttrOfSku(String tenantId,String skuId,Long operId);

    /**
     * 添加SKU属性值
     * @param prodSkuAttr
     * @return
     */
    public int createAttr(ProdSkuAttr prodSkuAttr);

}
