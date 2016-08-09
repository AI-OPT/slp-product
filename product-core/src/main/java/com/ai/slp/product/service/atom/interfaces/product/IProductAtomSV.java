package com.ai.slp.product.service.atom.interfaces.product;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.product.param.ProductEditQueryReq;
import com.ai.slp.product.api.product.param.ProductStorageSaleParam;
import com.ai.slp.product.dao.mapper.bo.product.Product;

import java.util.List;

/**
 * 销售商品原子操作
 * Created by jackieliu on 16/5/5.
 */
public interface IProductAtomSV {

    /**
     * 添加销售商品信息
     *
     * @param product
     * @return
     */
    public int installProduct(Product product);

    /**
     * 查询指定库存组下的销售商品
     *
     * @param tenantId
     * @param groupId
     * @return
     */
    public Product selectByGroupId(String tenantId,String groupId);

    /**
     * 查询指定商品
     *
     * @param tenantId
     * @param prodId
     * @return
     */
    public Product selectByProductId(String tenantId,String prodId);

    /**
     * 查询指定商品
     *
     * @param tenantId
     * @param supplierId
     * @param prodId
     * @return
     */
    public Product selectByProductId(String tenantId,String supplierId,String prodId);

    /**
     * 根据标识更新商品信息
     *
     * @param product
     * @return
     */
    public int updateById(Product product);

    /**
     * 待编辑商品分页查询
     *
     * @param productPageQueryVo
     * @return
     * @author lipeng16
     */
    public PageInfo<Product> selectPageForEdit(ProductEditQueryReq productPageQueryVo);
    
    /**
     * 通过库存组标识查询商品
     *
     * @param tenantId
     * @param groupId
     * @return
     * @author lipeng16
     */
    public Product queryProductByGroupId(String tenantId,String groupId);
    
    /**
     * 仓库中商品分页查询
     * 
     * @param productStorageSaleParam
     * @return
     */
    public PageInfo<Product> selectStorProdByState(ProductStorageSaleParam productStorageSaleParam);
    /**
     * 查询指定商品的受众
     *
     * @param tenantId
     * @param prodId
     * @return
     */
    public List<String> selectUserTypeByProductId(String tenantId,String prodId);
}
