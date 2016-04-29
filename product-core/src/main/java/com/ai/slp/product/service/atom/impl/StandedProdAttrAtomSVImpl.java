package com.ai.slp.product.service.atom.impl;

import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.constants.StandedProdAttrConstants;
import com.ai.slp.product.dao.mapper.bo.StandedProdAttr;
import com.ai.slp.product.dao.mapper.bo.StandedProdAttrCriteria;
import com.ai.slp.product.dao.mapper.bo.StandedProdAttrLog;
import com.ai.slp.product.dao.mapper.interfaces.StandedProdAttrLogMapper;
import com.ai.slp.product.dao.mapper.interfaces.StandedProdAttrMapper;
import com.ai.slp.product.service.atom.interfaces.IStandedProdAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.ISysSequenceCreditAtomSV;
import com.ai.slp.product.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 标准品属性值
 * Created by jackieliu on 16/4/28.
 */
@Component
public class StandedProdAttrAtomSVImpl implements IStandedProdAttrAtomSV {
    @Autowired
    StandedProdAttrMapper prodAttrMapper;
    @Autowired
    ISysSequenceCreditAtomSV sequenceCreditAtomSV;
    /**
     * 添加标准品属性值
     *
     * @param prodAttr
     * @return
     */
    @Override
    public int installObj(StandedProdAttr prodAttr) {
        if (prodAttr==null)
            return 0;
        //设置序列号
        prodAttr.setStandedProdAttrId(sequenceCreditAtomSV.getSeqByName());
        if (prodAttr.getOperTime()==null)
            prodAttr.setOperTime(DateUtils.currTimeStamp());
        return prodAttrMapper.insert(prodAttr);
    }

    @Override
    public int updateObj(StandedProdAttr prodAttr) {

        return 0;
    }

    /**
     * 查询租户下的某个标准品的所有属性值,只查询有效的
     * @param tenantId
     * @param standedId
     * @return
     */
    @Override
    public List<StandedProdAttr> queryByNormProduct(String tenantId, String standedId) {
        if (StringUtils.isBlank(tenantId)||StringUtils.isBlank(standedId))
            return new ArrayList<StandedProdAttr>();
        StandedProdAttrCriteria example = new StandedProdAttrCriteria();
        example.createCriteria()
                .andTenantIdEqualTo(tenantId)
                .andStandedProdIdEqualTo(standedId).andStateEqualTo(StandedProdAttrConstants.STATE_ACTIVE);
        return prodAttrMapper.selectByExample(example);
    }
}
