package com.ai.slp.product.service.atom.interfaces;

import java.util.List;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.dao.mapper.bo.ProductCat;

/**
 * 商品类目操作
 * 
 * Date: 2016年4月28日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public interface IProdCatDefAtomSV {

    /**
     * 根据条件进行分页查寻
     * @return
     */
    public PageInfo<ProductCat> queryForPage(Integer pageNo,Integer pageSize,String parentProductCatId,
            String tenantId,String productCatId,String productCatName,String isChild);

    /**
     * 商品类目查询,仅返回状态为有效的类目
     * 
     * @param tenantId 租户ID
     * @param productCatId 商品类目标识
     * @return
     * @author lipeng
     */
    public ProductCat selectById(String tenantId,String productCatId);

    /**
     * 查询指定类目信息,不区分状态
     *
     * @param productCatId 商品类目标识
     * @return
     * @author liutong
     */
    public ProductCat selectById(String productCatId);

    /**
     * 查询类目信息,包括所有状态
     *
     * @param tenantId
     * @param productCatId
     * @return
     */
    public ProductCat selectAllStateById(String tenantId,String productCatId);
    
    /**
     * 商品类目添加
     * 
     * @param productCat
     * @return
     * @author lipeng
     */
    public int insertProductCat(ProductCat productCat);
    
    /**
     * 商品类目修改
     * 
     * @param productCat
     * @return
     * @author lipeng
     */
    public int updateProductCat(ProductCat productCat);
    
    /**
     * 商品类目删除
     * 
     * @param tenantId
     * @param productCatId
     * @return
     * @author lipeng
     */
    public int deleteProductCat(String tenantId,String productCatId,Long operId);

    /**
     * 查询指定类目下存在子类目的数量
     *
     * @param parentCatId
     * @return
     */
    public int queryActiveOfParent(String parentCatId);

    /**
     * 根据名称或首字母查询
     *
     * @param tenantId
     * @param parentCatId
     * @param query
     * @param isName
     * @return
     */
    public List<ProductCat> queryByNameOrFirst(String tenantId,String parentCatId,String query,Boolean isName);
}
