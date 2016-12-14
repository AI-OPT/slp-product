package com.ai.slp.product.service.atom.interfaces.product;

import java.util.List;

import com.ai.slp.product.dao.mapper.bo.product.ProdPicture;
import com.ai.slp.product.search.bo.ImageInfo;

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

    /**
     * 查询商品主图
     * @param prodId
     * @return
     */
    public ProdPicture queryMainOfProd(String prodId);

    /**
     * 废除指定商品图片
     * @param prodId
     * @param attrValId
     * @return
     */
    public int discardPic(String prodId,String attrValId,Long operId);

    /**
     * 添加指定商品图片
     * @param prodPicture
     * @return
     */
    public int installPic(ProdPicture prodPicture);

    /**
     * 查询sku的属性值的主图
     * @param catId
     * @param skuId
     * @return
     */
    public ImageInfo queryMainOfSku(String catId,String skuId);

    /**
     * 查询商品的所有属性值的主图片
     * @param prodId
     * @return
     */
    public List<ImageInfo> queryAttrValOfProd(String prodId);
    

    /**
     * 查询商品主图
     * @param list
     * @return
     */
    public List<ProdPicture> queryMainOfProdList(List<String> prodIdList);
}
