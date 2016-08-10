package com.ai.slp.product.service.atom.impl.product;

import com.ai.slp.product.api.product.param.ProdTargetAreaInfo;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.dao.mapper.bo.product.ProdTargetArea;
import com.ai.slp.product.dao.mapper.bo.product.ProdTargetAreaCriteria;
import com.ai.slp.product.dao.mapper.interfaces.product.ProdTargetAreaMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProdTargetAreaAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;
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
            criteria.andStateEqualTo(CommonConstants.STATE_ACTIVE);
        }
        return areaMapper.selectByExample(example);
    }

    @Override
    public int discardForProduct(String tenantId, String prodId, Long operId) {
        ProdTargetAreaCriteria example = new ProdTargetAreaCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId)
                .andProdIdEqualTo(prodId)
                .andStateEqualTo(CommonConstants.STATE_ACTIVE);
        ProdTargetArea targetArea = new ProdTargetArea();
        targetArea.setState(CommonConstants.STATE_INACTIVE);
        targetArea.setOperId(operId);
        targetArea.setOperTime(DateUtils.currTimeStamp());
        return areaMapper.updateByExampleSelective(targetArea,example);
    }

    @Override
    public int installArea(ProdTargetArea targetArea) {
        targetArea.setTargetAreaId(SequenceUtil.genProdTargetAreaId());
        targetArea.setOperTime(DateUtils.currTimeStamp());
        return areaMapper.insert(targetArea);
    }
    
    /**
     * 根据商品ID
     *查询商品目标地域
     * 
     */
    @Override
    public List<ProdTargetArea> searchProdTargetArea(String tenantId, String prodId) {
    	//根据商品ID查询目标的地域编码
    	ProdTargetAreaCriteria example = new ProdTargetAreaCriteria();
    	example.createCriteria().andTenantIdEqualTo(tenantId)
        .andProdIdEqualTo(prodId)
        .andStateEqualTo(CommonConstants.STATE_ACTIVE);
    	List<ProdTargetArea> prodTargetAreaList = areaMapper.selectByExample(example);
        //若省份,城市,区县编码均为空,则直接返回空
        if (prodTargetAreaList==null && StringUtils.isBlank(prodId)){
            return Collections.emptyList();
        }
        return prodTargetAreaList;
    }
}
