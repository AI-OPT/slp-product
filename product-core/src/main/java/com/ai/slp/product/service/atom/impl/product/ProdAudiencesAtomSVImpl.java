package com.ai.slp.product.service.atom.impl.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.bo.product.ProdAudiences;
import com.ai.slp.product.dao.mapper.bo.product.ProdAudiencesCriteria;
import com.ai.slp.product.dao.mapper.interfaces.product.ProdAudiencesMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProdAudiencesAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;

/**
 * Created by jackieliu on 16/6/2.
 */
@Component
public class ProdAudiencesAtomSVImpl implements IProdAudiencesAtomSV {
    @Autowired
    ProdAudiencesMapper audiencesMapper;

    /**
     * 查询符合用户类型和用户ID的受众信息,用户类型和用户ID不能均为空
     *
     * @param tenantId
     * @param userType
     * @param userId
     * @param hasDiscard
     * @return
     */
    @Override
    public List<ProdAudiences> queryByUserType(String tenantId,String prodId, String userType, String userId, boolean hasDiscard) {
        if (StringUtils.isBlank(userType) && StringUtils.isBlank(userId)){
        	return Collections.emptyList();
        }
        ProdAudiencesCriteria example = new ProdAudiencesCriteria();
        ProdAudiencesCriteria.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(tenantId)
                .andProdIdEqualTo(prodId);
        if (StringUtils.isNotBlank(userType)){
        	criteria.andUserTypeEqualTo(userType);
        }
        if (StringUtils.isNotBlank(userId)) {
            List<String> userIdList = new ArrayList<>();
            userIdList.add(userId);
            userIdList.add(ProductConstants.ProdAudiences.userId.USER_TYPE);
            criteria.andUserIdIn(userIdList);
        }
        else {
            criteria.andUserIdEqualTo(ProductConstants.ProdAudiences.userId.USER_TYPE);
        }
        if (!hasDiscard){
            criteria.andStateEqualTo(CommonConstants.STATE_ACTIVE);
        }
        return audiencesMapper.selectByExample(example);
    }

    @Override
    public int installAudiences(ProdAudiences prodAudiences) {
        prodAudiences.setProdAudiencesId(SequenceUtil.genProdAudiencesId());
        prodAudiences.setOperTime(DateUtils.currTimeStamp());
        return audiencesMapper.insert(prodAudiences);
    }

    @Override
    public int updateNoUser(String tenantId, String prodId, String userType,Long operId) {
        ProdAudiencesCriteria example = new ProdAudiencesCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId)
                .andProdIdEqualTo(prodId)
                .andUserTypeEqualTo(userType)
                .andStateEqualTo(CommonConstants.STATE_ACTIVE);
        ProdAudiences prodAudiences = new ProdAudiences();
        prodAudiences.setState(CommonConstants.STATE_INACTIVE);
        prodAudiences.setOperId(operId);
        prodAudiences.setOperTime(DateUtils.currTimeStamp());
        return audiencesMapper.updateByExampleSelective(prodAudiences,example);
    }

    /**
     * 查询商品指定类型的受众信息
     *
     * @param tenantId
     * @param prodId
     * @param userType
     * @param hasDiscard
     * @return
     */
    @Override
    public List<ProdAudiences> queryByUserType(String tenantId, String prodId, String userType, boolean hasDiscard) {
        if (StringUtils.isBlank(userType)){
        	return Collections.emptyList();
        }
        ProdAudiencesCriteria example = new ProdAudiencesCriteria();
        ProdAudiencesCriteria.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(tenantId)
                .andProdIdEqualTo(prodId)
                .andUserTypeEqualTo(userType);
        if (!hasDiscard){
            criteria.andStateEqualTo(CommonConstants.STATE_ACTIVE);
        }
        return audiencesMapper.selectByExample(example);
    }

    /**
     * 确认商品下某类型用户是否为全部可见
     *
     * @param tenantId
     * @param prodId
     * @param userType
     * @param hasDiscard
     * @return
     */
    @Override
    public ProdAudiences queryAllByUserType(String tenantId, String prodId, String userType, boolean hasDiscard) {
        if (StringUtils.isBlank(userType)){
        	return null;
        }
        ProdAudiencesCriteria example = new ProdAudiencesCriteria();
        
       // example.setOrderByClause("OPER_TIME desc");
        
        ProdAudiencesCriteria.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(tenantId)
                .andProdIdEqualTo(prodId)
                .andUserTypeEqualTo(userType)
                .andUserIdEqualTo(ProductConstants.ProdAudiences.userId.USER_TYPE);
        if (!hasDiscard){
            criteria.andStateEqualTo(CommonConstants.STATE_ACTIVE);
        }
        List<ProdAudiences> audList = audiencesMapper.selectByExample(example);
        return CollectionUtil.isEmpty(audList)?null:audList.get(0);
    }

    /**
     * 查询商品的所有受众信息,包括所有类型
     *
     * @param tenantId
     * @param prodId
     * @param hasDiscard
     * @return
     */
    @Override
    public List<ProdAudiences> queryOfProductByProdId(String tenantId, String prodId, boolean hasDiscard) {
        ProdAudiencesCriteria example = new ProdAudiencesCriteria();
        ProdAudiencesCriteria.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(tenantId).andProdIdEqualTo(prodId);
        if (!hasDiscard){
            criteria.andStateEqualTo(CommonConstants.STATE_ACTIVE);
        }
        return audiencesMapper.selectByExample(example);
    }
}
