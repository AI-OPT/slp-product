package com.ai.slp.product.service.atom.interfaces.product;

import com.ai.slp.product.dao.mapper.bo.product.ProdPicture;

import java.util.List;

/**
 * 商品图片原子操作
 * Created by jackieliu on 16/5/31.
 */
public interface IProdPictureAtomSV {

    /**
     * 查询指定商品下某个属性值的主图
     * @return
     */
    public ProdPicture queryMainOfProdIdAndAttrVal(String prodId,String attrValId);

    /**
     * 查询指定商品下某个属性值的图片
     * @param prodId
     * @param attrValId
     * @return
     */
    public List<ProdPicture> queryProdIdAndAttrVal(String prodId,String attrValId);

    /**
     * 查询商品的图片
     * @return
     */
    public List<ProdPicture> queryPicOfProd(String prodId);
}
