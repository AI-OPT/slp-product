package com.ai.slp.product.service.business.interfaces;

import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.normproduct.param.*;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;

/**
 * 对标准品的相关操作
 *
 * @author liutong5
 */
public interface INormProductBusiSV {
    /**
     * 添加标准品,包括属性值
     *
     * @param normProduct
     * @return 标准品标识
     */
    public String installNormProd(NormProdSaveRequest normProduct);
    
    /**
     * 添加标准品,包括属性值 库存组 和 SKU
     * @param normProduct
     * @return
     */
    public String installNormProdAndPtoGroup(NormProdSaveRequest normProduct);

    /**
     * 更新标准品,包括属性值
     *
     * @param normProduct
     */
    public void updateNormProd(NormProdSaveRequest normProduct);
    
    /**
     * 查询指定的标准品嘻嘻
     * @param tenantId  租户id
     * @param productId 标准品标识
     * @return
     */
    public StandedProduct queryById(String tenantId, String productId);
    //public NormProdInfoResponse queryById(String tenantId, String productId);

    /**
     * 分页查询
     *
     * @return
     */
    public PageInfo<StandedProduct> queryForPage(NormProdRequest productRequest);
//    public PageInfoResponse<NormProdResponse> queryForPage(NormProdRequest productRequest);

    /**
     * 废弃标准品
     * @param tenantId 租户id
     * @param productId 标准品标识
     * @param operId 操作者id
     */
    public void discardProduct(String tenantId,String productId,Long operId,String supplierId);

    /**
     * 废弃标准品及管理的库存组和销售商品
     * @param tenantId 租户id
     * @param standedProdId 标准品标识
     * @param operId 操作者id
     */
    public void discardProductWithProduct(String tenantId,String standedProdId,Long operId,String supplierId);
    
    /**
     * 废弃标准品及管理的库存组和销售商品
     * @param tenantId 租户id
     * @param standedProdId 标准品标识
     * @param operId 操作者id
     */
    public void discardProduct(StandedProduct standedProduct,Long operId);

    /**
     * 查询标准品下指定类型的属性及属性值信息
     *
     * @param tenantId
     * @param standedProdId
     * @param attrType
     * @return
     */
    public AttrMap queryAttrOfProduct(String tenantId, String standedProdId, String attrType);
    
    /**
     * 更新标准品的市场价
     * 
     * @param marketPrice
     * @return
     * @author lipeng
     */
    public int updateMarketPrice(MarketPriceUpdate marketPrice);
    
    /**
     * 添加商品销售价页面查询标准品及库存组数量信息
     * 
     * @param productRequest
     * @return
     * @author lipeng
     */
    public PageInfoResponse<NormProdResponse> queryNormProductForSalePrice(NormProdRequest productRequest);

    /**
     * 查询标准品信息,包括关键属性
     *
     * @param productRequest
     * @return
     */
    public PageInfoResponse<NormProdAndKeyAttrRes> queryProdAndKeyAttr(NormProdRequest productRequest);

    /**
     * 修改标准品及库存 sku
     * @param productInfoRequest
     * @author jiaxs
     */
	public int updateNormProdAndStoGroup(NormProdSaveRequest productInfoRequest);
}
