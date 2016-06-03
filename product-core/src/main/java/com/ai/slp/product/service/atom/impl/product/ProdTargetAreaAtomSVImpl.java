package com.ai.slp.product.service.atom.impl.product;

import com.ai.slp.product.constants.CommonSatesConstants;
import com.ai.slp.product.dao.mapper.bo.product.ProdTargetArea;
import com.ai.slp.product.dao.mapper.bo.product.ProdTargetAreaCriteria;
import com.ai.slp.product.dao.mapper.interfaces.product.ProdTargetAreaMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProdTargetAreaAtomSV;
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
     * @param provCode   省份编码
     * @param cityCode   城市编码
     * @param countyCode 区县编码
     * @param hasDiscard 是否包含废弃状态
     * @return
     */
    @Override
    public List<ProdTargetArea> queryByAreaCode(String tenantId, Integer provCode, Integer cityCode, Integer countyCode, boolean hasDiscard) {
        //若省份,城市,区县编码均为空,则直接返回空
        if (provCode==null && cityCode==null && countyCode==null){
            return Collections.emptyList();
        }
        ProdTargetAreaCriteria example = new ProdTargetAreaCriteria();
        ProdTargetAreaCriteria.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        if (provCode!=null)
            criteria.andProvCodeEqualTo(provCode);
        if (cityCode!=null)
            criteria.andCityCodeEqualTo(cityCode);
        if (countyCode!=null)
            criteria.andCountyCodeEqualTo(countyCode);
        //若不包括废弃状态
        if (!hasDiscard){
            criteria.andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
        }
        return areaMapper.selectByExample(example);
    }
}
