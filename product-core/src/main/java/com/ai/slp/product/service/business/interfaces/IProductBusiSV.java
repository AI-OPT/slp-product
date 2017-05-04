package com.ai.slp.product.service.business.interfaces;

import java.util.List;

import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.slp.product.api.normproduct.param.AttrValRequest;
import com.ai.slp.product.api.product.param.ProdAttrMap;
import com.ai.slp.product.api.product.param.ProdNoKeyAttr;
import com.ai.slp.product.api.product.param.ProdTargetAreaInfo;
import com.ai.slp.product.api.product.param.Product4List;
import com.ai.slp.product.api.product.param.ProductListQuery;
import com.ai.slp.product.api.product.param.ProductRoute;
import com.ai.slp.product.api.webfront.param.FastProductInfoRes;
import com.ai.slp.product.api.webfront.param.FastProductReq;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;


/**
 * 商城商品业务操作
 * Created by jackieliu on 16/5/5.
 */
public interface IProductBusiSV {

    /**
     * 添加商城商品
     * @param standedProduct
     * @param group
     * @param attrValList
     * @return
     * @author Gavin
     * @UCUSER
     */
    public Product addProductWithStorageGroup(StandedProduct standedProduct, StorageGroup group,List<AttrValRequest> attrValList);

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
     * 进行停用下架
     * @param product
     * @param operId
     */
    public void offSale(StorageGroup storageGroup,Product product,Long operId);
  
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
     * 对销售商品进行上架处理
     *
     * @param product
     * @param operId
     */
    public void changeToInSale(Product product, Long operId);

    /**
     * 对审核通过的商品进行上架处理
     *
     * @param product
     * @param operId
     */
    public void changeToInSaleFromAudit(Product product, Long operId);
    
    /**
     * 手动下架
     * @param tenantId
     * @param prodId
     */
    public void changeSaleToStore(String tenantId, String supplierId, Long operId);
    /**
     * 手动下架
     * @param tenantId
     * @param prodId
     */
    public void changeSaleToStore(String tenantId, String supplierId,String productId, Long operId);
    
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
    public Product queryByProdId(String tenantId,String supplierId, String productId);
/*    public ProductInfo queryByProdId(String tenantId,String supplierId, String productId);
*/
    /**
     * 更新商品日志和状态日志
     * @param product
     */
    public void updateProdAndStatus(Product product);

    /**
     * 查询商品的省级目标地域
     *
     * @param tenantId
     * @param supplierId
     * @param productId
     * @return
     */
    public List<ProdTargetAreaInfo> queryProvinceInfoOfProduct(String tenantId, String supplierId, String productId);
}
