package com.ai.slp.product.service.business.interfaces;

import com.ai.slp.product.api.normproduct.param.NormProductInfoResponse;
import com.ai.slp.product.api.normproduct.param.NormProductSaveRequest;

/**
 * 对标准品的相关操作
 *
 * @author liutong5
 */
public interface INormProductBusiSV {
    /**
     * 添加标准品,包括属性值
     *
     * @param normProdct
     */
    public String installNormProd(NormProductSaveRequest normProdct);

    /**
     * 更新标准品,包括属性值
     *
     * @param normProdct
     */
    public void updateNormProd(NormProductSaveRequest normProdct);

    /**
     * 查询指定的标准品嘻嘻
     * @param tenantId  租户id
     * @param productId 标准品标识
     * @return
     */
    public NormProductInfoResponse queryById(String tenantId,String productId);
}
