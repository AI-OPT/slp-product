package com.ai.slp.product.service.business.interfaces;

import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.product.param.OtherSetOfProduct;
import com.ai.slp.product.api.product.param.ProdStateLog;
import com.ai.slp.product.api.product.param.ProductCheckParam;
import com.ai.slp.product.api.product.param.ProductEditQueryReq;
import com.ai.slp.product.api.product.param.ProductEditUp;
import com.ai.slp.product.api.product.param.ProductInfoForUpdate;
import com.ai.slp.product.api.product.param.ProductInfoQuery;
import com.ai.slp.product.api.product.param.ProductQueryInfo;
import com.ai.slp.product.api.product.param.ProductRouteGroupInfo;
import com.ai.slp.product.api.product.param.ProductStorageSale;
import com.ai.slp.product.api.product.param.ProductStorageSaleParam;
import com.ai.slp.product.api.product.param.RouteGroupQuery;
import com.ai.slp.product.api.product.param.TargetAreaForProd;
import com.ai.slp.product.dao.mapper.bo.product.Product;

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
    public PageInfo<Product> queryPageForEdit(ProductEditQueryReq queryReq);

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
	 * 查询被拒绝原因
	 * @param productRefuseParam
	 * @return
	 */
	public ProdStateLog queryRefuseByProdId(ProductInfoQuery queryInfo);
	
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
    public PageInfo<Product> queryInSale(ProductQueryInfo queryInSale);


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

    /**
     * 查询销售商品信息和配货组信息
     * @param query
     * @return
     */
    public PageInfoResponse<ProductRouteGroupInfo> queryProdAndRouteGroup(RouteGroupQuery query);

    /**
     * 审核商品
     * @param productCheckParam
     * @return 待添加搜索中的商品ID集合
     */
    public void auditProduct(ProductCheckParam productCheckParam);
}
