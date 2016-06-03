package com.ai.slp.product.service.atom.impl.product;

import com.ai.slp.product.constants.CommonSatesConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.bo.product.ProdAudiences;
import com.ai.slp.product.dao.mapper.bo.product.ProdAudiencesCriteria;
import com.ai.slp.product.dao.mapper.interfaces.product.ProdAudiencesMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProdAudiencesAtomSV;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Created by jackieliu on 16/6/2.
 */
@Component
public class ProdAudiencesAtomSVImpl implements IProdAudiencesAtomSV {
    @Autowired
    ProdAudiencesMapper audiencesMapper;

    /**
     * 查询符合用户类型和用户ID的受众新,用户类型和用户ID不能均为空
     *
     * @param tenantId
     * @param userType
     * @param userId
     * @param hasDiscard
     * @return
     */
    @Override
    public List<ProdAudiences> queryByUserType(String tenantId, String userType, String userId, boolean hasDiscard) {
        if (StringUtils.isBlank(userType) && StringUtils.isBlank(userId))
            return Collections.emptyList();
        ProdAudiencesCriteria example = new ProdAudiencesCriteria();
        ProdAudiencesCriteria.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(tenantId)
                .andUsrrIdEqualTo(ProductConstants.ProdAudiences.userId.USER_TYPE);
        if (StringUtils.isNotBlank(userType))
            criteria.andUserTypeEqualTo(userType);
        if (StringUtils.isNotBlank(userId))
            criteria.andUsrrIdEqualTo(userId);
        if (!hasDiscard){
            criteria.andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
        }
        return audiencesMapper.selectByExample(example);
    }
}
