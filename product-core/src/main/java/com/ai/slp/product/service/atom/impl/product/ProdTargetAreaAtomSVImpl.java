package com.ai.slp.product.service.atom.impl.product;

import com.ai.slp.product.constants.CommonSatesConstants;
import com.ai.slp.product.dao.mapper.bo.product.ProdTargetArea;
import com.ai.slp.product.dao.mapper.bo.product.ProdTargetAreaCriteria;
import com.ai.slp.product.dao.mapper.interfaces.product.ProdTargetAreaMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProdTargetAreaAtomSV;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Created by jackieliu on 16/6/2.
 */
@Component
public class ProdTargetAreaAtomSVImpl implements IProdTargetAreaAtomSV {
    @Autowired
    ProdTargetAreaMapper areaMapper;
    /**
     * 根据地域编码查询目标地域信息
     *
     * @param tenantId   租户ID
     * @param prodId  商品编码
     * @param provCode   省份编码
     * @param hasDiscard 是否包含废弃状态
     * @return
     */
    @Override
    public List<ProdTargetArea> queryByAreaCode(String tenantId, String prodId, Integer provCode, boolean hasDiscard) {
        //若省份,城市,区县编码均为空,则直接返回空
        if (provCode==null && StringUtils.isBlank(prodId)){
            return Collections.emptyList();
        }
        ProdTargetAreaCriteria example = new ProdTargetAreaCriteria();
        ProdTargetAreaCriteria.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(tenantId)
            .andProdIdEqualTo(prodId);
        if (provCode!=null)
            criteria.andProvCodeEqualTo(provCode);
        //若不包括废弃状态
        if (!hasDiscard){
            criteria.andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
        }
        return areaMapper.selectByExample(example);
    }
}
