package com.ai.slp.product.service.atom.impl.product;

import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.bo.product.ProdPicture;
import com.ai.slp.product.dao.mapper.bo.product.ProdPictureCriteria;
import com.ai.slp.product.dao.mapper.interfaces.product.ProdPictureMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProdPictureAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jackieliu on 16/5/31.
 */
@Component
public class ProdPictureAtomSVImpl implements IProdPictureAtomSV {
    @Autowired
    ProdPictureMapper prodPictureMapper;
    /**
     * 查询指定商品下某个属性值的主图
     *
     * @param prodId
     * @param attrValId
     * @return
     */
    @Override
    public ProdPicture queryMainOfProdIdAndAttrVal(String prodId, String attrValId) {
        ProdPictureCriteria example = new ProdPictureCriteria();
        example.createCriteria().andProdIdEqualTo(prodId)
                .andAttrvalueDefIdEqualTo(attrValId)
                .andPicUsesEqualTo(ProductConstants.ProdPicture.PicType.ATTR)
                .andIsMainPicEqualTo(ProductConstants.ProdPicture.IsMainPic.YES)
                .andStateEqualTo(CommonConstants.STATE_ACTIVE);
        List<ProdPicture> pictureList = prodPictureMapper.selectByExample(example);
        return CollectionUtil.isEmpty(pictureList)?null:pictureList.get(0);
    }

    /**
     * 查询指定商品下某个属性值的图片
     *
     * @param prodId
     * @param attrValId
     * @return
     */
    @Override
    public List<ProdPicture> queryProdIdAndAttrVal(String prodId, String attrValId) {
        ProdPictureCriteria example = new ProdPictureCriteria();
        example.createCriteria().andProdIdEqualTo(prodId)
                .andAttrvalueDefIdEqualTo(attrValId)
                .andPicUsesEqualTo(ProductConstants.ProdPicture.PicType.ATTR)
                .andStateEqualTo(CommonConstants.STATE_ACTIVE);
        return prodPictureMapper.selectByExample(example);
    }

    /**
     * 查询商品的图片
     *
     * @param prodId
     * @return
     */
    @Override
    public List<ProdPicture> queryPicOfProd(String prodId) {
        ProdPictureCriteria example = new ProdPictureCriteria();
        example.createCriteria().andProdIdEqualTo(prodId)
                .andPicUsesEqualTo(ProductConstants.ProdPicture.PicType.PRODUCT)
                .andStateEqualTo(CommonConstants.STATE_ACTIVE);
        return prodPictureMapper.selectByExample(example);
    }

    /**
     * 查询商品主图
     *
     * @param prodId
     * @return
     */
    @Override
    public ProdPicture queryMainOfProd(String prodId) {
        ProdPictureCriteria example = new ProdPictureCriteria();
        example.createCriteria().andProdIdEqualTo(prodId)
                .andPicUsesEqualTo(ProductConstants.ProdPicture.PicType.PRODUCT)
                .andIsMainPicEqualTo(ProductConstants.ProdPicture.IsMainPic.YES)
                .andStateEqualTo(CommonConstants.STATE_ACTIVE);
        List<ProdPicture> pictureList = prodPictureMapper.selectByExample(example);
        return CollectionUtil.isEmpty(pictureList)?null:pictureList.get(0);
    }

    @Override
    public int discardPic(String prodId, String attrValId,Long operId) {
        ProdPictureCriteria example = new ProdPictureCriteria();
        example.createCriteria().andProdIdEqualTo(prodId)
                .andAttrvalueDefIdEqualTo(attrValId)
                .andStateEqualTo(CommonConstants.STATE_ACTIVE);
        ProdPicture prodPicture = new ProdPicture();
        prodPicture.setState(CommonConstants.STATE_INACTIVE);
        prodPicture.setOperId(operId);
        prodPicture.setOperTime(DateUtils.currTimeStamp());
        return prodPictureMapper.updateByExampleSelective(prodPicture,example);
    }

    @Override
    public int installPic(ProdPicture prodPicture) {
        prodPicture.setProPictureId(SequenceUtil.genProdPictureId());
        prodPicture.setOperTime(DateUtils.currTimeStamp());
        return prodPictureMapper.insert(prodPicture);
    }

}
