package com.ai.slp.product.service.atom.impl;

import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.bo.StandedProductCriteria;
import com.ai.slp.product.dao.mapper.interfaces.StandedProductMapper;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.ISysSequenceCreditAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.vo.StandedProdPageQueryVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * 标准品原子操作
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
        //不允许更新创建人,创建时间,所属类目
        standedProduct.setCreateTime(null);
        standedProduct.setCreateId(null);
        standedProduct.setProductCatId(null);
        standedProduct.setOperTime(DateUtil.getSysDate());
        StandedProductCriteria example = new StandedProductCriteria();
        example.createCriteria()
                .andTenantIdEqualTo(standedProduct.getTenantId())
                .andStandedProdIdEqualTo(standedProduct.getStandedProdId());
        return productMapper.updateByExampleSelective(standedProduct,example);
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

    @Override
    public PageInfo<StandedProduct> queryForPage(StandedProdPageQueryVo request) {
        StandedProductCriteria example = new StandedProductCriteria();
        StandedProductCriteria.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(request.getTenantId());
        //类目id
        if (StringUtils.isNotBlank(request.getProductCatId()))
            criteria.andProductCatIdEqualTo(request.getProductCatId());
        //商品标识
        if (StringUtils.isNotBlank(request.getProductId()))
            criteria.andStandedProdIdLike("%"+request.getProductId()+"%");
        //商品名称
        if (StringUtils.isNotBlank(request.getProductName()))
            criteria.andStandedProductNameLike("%"+request.getProductName()+"%");
        //商品状态
        if (StringUtils.isNotBlank(request.getState()))
            criteria.andStateEqualTo(request.getState());
        //商品类型
        if (StringUtils.isNotBlank(request.getProductType()))
            criteria.andProductTypeEqualTo(request.getProductType());
        //添加时间 开始时间
        if (request.getCreateStartTime()!=null)
            criteria.andCreateTimeGreaterThanOrEqualTo(
                    DateUtils.toTimeStamp(request.getCreateStartTime()));
        //添加时间 截止时间
        if (request.getCreateEndTime()!=null)
            criteria.andCreateTimeLessThanOrEqualTo(
                    DateUtils.toTimeStamp(request.getCreateEndTime()));
        //操作时间 开始时间
        if (request.getOperStartTime()!=null)
            criteria.andOperTimeGreaterThanOrEqualTo(
                    DateUtils.toTimeStamp(request.getOperStartTime()));
        //操作时间 截止时间
        if (request.getOperEndTime()!=null)
            criteria.andOperTimeLessThanOrEqualTo(
                    DateUtils.toTimeStamp(request.getOperEndTime()));
        //操作人
        if (request.getOperId()!=null)
            criteria.andOperIdEqualTo(request.getOperId());
        PageInfo<StandedProduct> pageInfo = new PageInfo<>();
        //设置总数
        pageInfo.setCount(productMapper.countByExample(example));
        if (request.getPageNo() != null && request.getPageSize() != null) {
            example.setLimitStart((request.getPageNo() - 1) * request.getPageSize());
            example.setLimitEnd(request.getPageSize());
        }
        pageInfo.setPageNo(request.getPageNo());
        pageInfo.setPageSize(request.getPageSize());
        pageInfo.setResult(productMapper.selectByExample(example));
        return pageInfo;
    }

    @Override
    public int queryByCatId(String catId) {
        StandedProductCriteria example = new StandedProductCriteria();
        example.createCriteria().andProductCatIdEqualTo(catId);
        return productMapper.countByExample(example);
    }

    @Override
    public int updateMarketPrice(String tenantId, String standedProductId, long marketPrice, Long operId) {
        StandedProductCriteria example = new StandedProductCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andStandedProdIdEqualTo(standedProductId);
        StandedProduct standedProduct = new StandedProduct();
        standedProduct.setMarketPrice(marketPrice);
        standedProduct.setOperId(operId);
        standedProduct.setOperTime(DateUtils.currTimeStamp());
        return productMapper.updateByExampleSelective(standedProduct, example);
    }
}
