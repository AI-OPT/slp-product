package com.ai.slp.product.service.atom.impl;

import com.ai.opt.sdk.util.DateUtil;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.bo.StandedProductCriteria;
import com.ai.slp.product.dao.mapper.interfaces.StandedProductMapper;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.ISysSequenceCreditAtomSV;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jackieliu on 16/4/28.
 */
@Component
public class StandedProductAtomSVImpl implements IStandedProductAtomSV {
    @Autowired
    StandedProductMapper productMapper;
    @Autowired
    ISysSequenceCreditAtomSV sequenceCreditAtomSV;

    @Override
    public int installObj(StandedProduct standedProduct) {
        if (standedProduct==null)
            return 0;
        //设置标准品标识
        standedProduct.setStandedProdId(
                sequenceCreditAtomSV.gen12SeqByName()+"");
        //设置添加时间
        if (standedProduct.getCreateTime()==null)
            standedProduct.setCreateTime(DateUtil.getSysDate());
        standedProduct.setOperTime(standedProduct.getCreateTime());
        standedProduct.setOperId(standedProduct.getCreateId());
        return productMapper.insertSelective(standedProduct);
    }

    @Override
    public int updateObj(StandedProduct standedProduct) {
        if (standedProduct==null)
            return 0;
        if (standedProduct.getOperTime()==null)
            standedProduct.setOperTime(DateUtil.getSysDate());
        return productMapper.updateByPrimaryKeySelective(standedProduct);
    }

    @Override
    public StandedProduct selectById(String tenantId, String standedProdId) {
        if (StringUtils.isBlank(tenantId)||StringUtils.isBlank(standedProdId))
            return null;
        StandedProductCriteria example = new StandedProductCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andStandedProdIdEqualTo(standedProdId);
        List<StandedProduct> productList = productMapper.selectByExample(example);
        return (productList==null||productList.isEmpty())?null:productList.get(0);
    }
}
