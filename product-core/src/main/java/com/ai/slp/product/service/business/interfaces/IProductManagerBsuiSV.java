package com.ai.slp.product.service.business.interfaces;

import java.util.List;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.product.param.OtherSetOfProduct;
import com.ai.slp.product.api.product.param.ProdTargetAreaInfo;
import com.ai.slp.product.api.product.param.ProductEditQueryReq;
import com.ai.slp.product.api.product.param.ProductEditUp;
import com.ai.slp.product.api.product.param.ProductInfoForUpdate;
import com.ai.slp.product.api.product.param.ProductStorageSale;
import com.ai.slp.product.api.product.param.ProductStorageSaleParam;
import com.ai.slp.product.api.product.param.TargetAreaForProd;
import com.ai.slp.product.dao.mapper.bo.product.ProdTargetArea;
import com.ai.slp.product.api.product.param.*;

/**
 * 销售商品管理
 * Created by jackieliu on 16/6/6.
 */
public interface IProductManagerBsuiSV {
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
     * @param queryReq
     * @return
     */
    public PageInfoResponse<ProductEditUp> queryInSale(ProductQueryInSale queryInSale);
}
