package com.ai.slp.product.service.business.interfaces;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.normproduct.param.*;

import java.util.Date;

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

    /**
     * 分页查询
     *
     * @return
     */
    public PageInfoWrapper<NormProductResponse> queryForPage(NormProductRequest productRequest);

    /**
     * 废弃标准品
     * @param tenantId 租户id
     * @param productId 标准品标识
     * @param operId 操作者id
     * @param operTime 操作时间,可以为空
     */
    public void discardProduct(String tenantId,String productId,Long operId,Date operTime);
}
