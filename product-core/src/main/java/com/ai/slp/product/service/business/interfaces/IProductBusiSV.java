package com.ai.slp.product.service.business.interfaces;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.product.param.*;
import com.ai.slp.product.api.webfront.param.FastProductInfoRes;
import com.ai.slp.product.api.webfront.param.FastProductReq;
import com.ai.slp.product.dao.mapper.bo.product.Product;
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
     * 对停用下架的商品进行上架处理
     *
     * @param product
     * @param operId
     */
    public void changeToSaleForStop(Product product, Long operId);

    /**
     * 进行停用下架
     * @param tenantId
     * @param prodId
     */
    public void offSale(String tenantId,String supplierId, String prodId, Long operId);
  
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
    public ProductRoute queryRouteGroupOfProd(String tenantId,String supplierId,String productId);

    /**
     * 查询商品的非关键属性
     * @return
     */
    public ProdAttrMap queryNoKeyAttrOfProduct(String tenantId,String supplierId, String productId);

    /**
     * 查询商品的非关键属性
     * @return
     */
    public ProdAttrMap queryNoKeyAttrOfProduct(Product product);

    /**
     * 查询相关的快充产品
     * @param req
     * @return
     */
    public FastProductInfoRes queryFastInfoList(FastProductReq req);

    /**
     * 对销售商品进行上架处理
     *
     * @param tenantId
     * @param prodId
     */
    public void changeToInSale(String tenantId,String supplierId, String prodId, Long opeId);
    
    /**
     * 手动下架
     * @param tenantId
     * @param prodId
     */
    public void changeToInStore(String tenantId, String prodId, Long operId);
    
    /**
     * 查询管理界面中的非关键属性
     * @param tenantId
     * @param productId
     * @return
     */
    public ProdNoKeyAttr queryNoKeyAttrForEdit(String tenantId, String productId);

    /**
     * 查询销售商品信息
     * @param tenantId
     * @param productId
     * @return
     */
    public ProductInfo queryByProdId(String tenantId,String supplierId, String productId);
}
