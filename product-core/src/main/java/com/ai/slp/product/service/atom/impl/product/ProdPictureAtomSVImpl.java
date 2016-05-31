package com.ai.slp.product.service.atom.impl.product;

import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.constants.CommonSatesConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.bo.product.ProdPicture;
import com.ai.slp.product.dao.mapper.bo.product.ProdPictureCriteria;
import com.ai.slp.product.dao.mapper.interfaces.product.ProdPictureMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProdPictureAtomSV;
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
                .andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
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
                .andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
        return prodPictureMapper.selectByExample(example);
    }
}
