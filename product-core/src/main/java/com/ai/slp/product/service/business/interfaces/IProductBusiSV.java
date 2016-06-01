package com.ai.slp.product.service.business.interfaces;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.normproduct.param.AttrMap;
import com.ai.slp.product.api.product.param.Product4List;
import com.ai.slp.product.api.product.param.ProductListQuery;
import com.ai.slp.product.api.product.param.ProductRoute;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;


/**
 * 商城商品业务操作
 * Created by jackieliu on 16/5/5.
 */
public interface IProductBusiSV {

    /**
     * 
     * 添加商城商品
     * @param group
     * @return
     */
    public int addProductWithStorageGroup(StorageGroup group, Long operId);

    /**
     * 对停用下架的商品进行上架处理
     *
     * @param tenantId
     * @param prodId
     */
    public void changeToSaleForStop(String tenantId,String prodId,Long operId);

    /**
     * 进行停用下架
     * @param tenantId
     * @param prodId
     */
    public void offSale(String tenantId, String prodId, Long operId);

    /**
     * 废弃商品
     * @param tenantId
     * @param prodId
     */
    public void discardProduct(String tenantId,String prodId,Long operId);
    
    /**
     * 分页查询商城商品信息-商城商品销售价之商城商品列表
     * 
     * @param productQuery
     * @return
     * @author lipeng16
     */
    public PageInfoResponse<Product4List> queryProductPage(ProductListQuery productQuery);

    /**
     * 查询销售商品关联的路由组ID
     * @param tenantId
     * @param productId
     * @return
     */
    public ProductRoute queryRouteGroupOfProd(String tenantId,String productId);

    /**
     * 查询商品的非关键属性
     * @return
     */
    public AttrMap queryNoKeyAttrOfProduct(String tenantId,String productId);
}
