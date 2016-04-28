package com.ai.slp.product.service.business.interfaces;

import com.ai.slp.product.api.normproduct.param.NormProductSaveRequest;

/**
 * 对标准品的相关操作
 *
 * @author liutong
 */
public interface INormProductBusiSV {
    /**
     * 添加标准品,包括属性值
     *
     * @param normProdct
     */
    public void installNormProd(NormProductSaveRequest normProdct);
}
