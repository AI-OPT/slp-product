package com.ai.slp.product.service.atom.interfaces;

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
     * 商品类目查询
     * 
     * @param tenantId 租户ID
     * @param productCatId 商品类目标识
     * @return
     * @author lipeng
     */
    public ProductCat selectById(String tenantId,String productCatId);
    
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
    public int deleteProductCat(String tenantId,String productCatId);
    
}
