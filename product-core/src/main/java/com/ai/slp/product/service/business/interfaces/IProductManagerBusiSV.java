package com.ai.slp.product.service.business.interfaces;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.product.param.*;

/**
 * 销售商品管理
 * Created by jackieliu on 16/6/6.
 */
public interface IProductManagerBusiSV {
    /**
     * 商品管理中分页查询商品信息
     * @param queryReq
     * @return
     */
    public PageInfoResponse<ProductEditUp> queryPageForEdit(ProductEditQueryReq queryReq);

    /**
     * 查询商品的其他设置内容
     * @param tenantId
     * @param supplierId
     * @param prodId
     * @return
     */
    public OtherSetOfProduct queryOtherSetOfProd(String tenantId,String supplierId, String prodId);

    /**
     * 更新产品编辑信息
     * @param productInfo
     */
    public void updateProdEdit(ProductInfoForUpdate productInfo);
    
    /**
     * 根据状态查询仓库中商品
     * @param productStorageSaleParam
     * @return
     */
    public PageInfoResponse<ProductStorageSale> queryStorageProdByState(ProductStorageSaleParam productStorageSaleParam) ;
    
    /**
     * 商品管理中分页查询被拒绝商品信息
     * @param productRefuseParam
     * @return
     */
	public PageInfoResponse<ProductEditUp> queryProductRefuse(ProductEditQueryReq productRefuseParam);
	
	/**
     * 查收商品的目标地域
     * @return
     */
	public PageInfoResponse<TargetAreaForProd> searchProdTargetArea(ProductEditQueryReq productEditParam);
	
	/**
     * 查询在售商品 -- 按上架时间排序
     * @param queryInSale
     * @return
     */
    public PageInfoResponse<ProductEditUp> queryInSale(ProductQueryInfo queryInSale);


    /**
	 * 商品审核分页查询
	 *
	 * @param queryReq
	 * @return
	 * @author jiawen
	 */
    public PageInfoResponse<ProductEditUp> queryPageForAudit(ProductQueryInfo queryReq);

    /**
     * 设置销售商品的路由组/配货组标识
     * @param tenantId
     * @param supplierId
     * @param prodId
     * @param routeGroupId
     * @param operId
     */
    public void changeRouteGroup(String tenantId,String supplierId,String prodId,
                                 String routeGroupId,Long operId);
}
