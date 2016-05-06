package com.ai.slp.product.service.business.interfaces;

import com.ai.slp.product.api.common.param.PageInfoForRes;
import com.ai.slp.product.api.normproduct.param.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public String installNormProd(NormProdSaveRequest normProdct);

    /**
     * 更新标准品,包括属性值
     *
     * @param normProdct
     */
    public void updateNormProd(NormProdSaveRequest normProdct);

    /**
     * 查询指定的标准品嘻嘻
     * @param tenantId  租户id
     * @param productId 标准品标识
     * @return
     */
    public NormProdInfoResponse queryById(String tenantId, String productId);

    /**
     * 分页查询
     *
     * @return
     */
    public PageInfoForRes<NormProdResponse> queryForPage(NormProdRequest productRequest);

    /**
     * 废弃标准品
     * @param tenantId 租户id
     * @param productId 标准品标识
     * @param operId 操作者id
     * @param operTime 操作时间,可以为空
     */
    public void discardProduct(String tenantId,String productId,Long operId,Date operTime);

    /**
     * 查询标准品下指定类型的属性及属性值信息
     *
     * @param tenantId
     * @param productId
     * @param attrType
     * @return
     */
    public AttrMapOfNormProd queryAttrOfProduct(String tenantId,String productId,String attrType);
    
    /**
     * 更新标准品的市场价
     * 
     * @param marketPrice
     * @return
     * @author lipeng
    *  @ApiCode
     */
    public int updateMarketPrice(MarketPrice4Update marketPrice);
}
