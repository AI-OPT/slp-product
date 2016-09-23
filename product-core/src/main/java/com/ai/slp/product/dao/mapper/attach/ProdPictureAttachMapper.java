package com.ai.slp.product.dao.mapper.attach;

import java.util.List;
import java.util.Map;

import com.ai.slp.product.search.bo.ImageInfo;

/**
 * 图片扩展
 * Created by jackieliu on 16/9/23.
 */
public interface ProdPictureAttachMapper {

    /**
     * 查询SKU的所属属性值主图
     * @param params
     * @return
     */
    public ImageInfo selectMainOfSku(Map<String,Object> params);

    /**
     * 查询商品所有的属性值的主图信
     * @param prodId
     * @return
     */
    public List<ImageInfo> selectAttrValOfProd(String prodId);
}
