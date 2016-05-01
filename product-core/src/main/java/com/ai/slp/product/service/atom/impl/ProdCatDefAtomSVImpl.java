package com.ai.slp.product.service.atom.impl;

import java.util.List;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.constants.CommonSatesConstants;
import com.ai.slp.product.service.atom.interfaces.ISysSequenceCreditAtomSV;
import com.ai.slp.product.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.dao.mapper.bo.ProductCatCriteria;
import com.ai.slp.product.dao.mapper.interfaces.ProductCatMapper;
import com.ai.slp.product.service.atom.interfaces.IProdCatDefAtomSV;

/**
 * 类目操作
 */
@Component
public class ProdCatDefAtomSVImpl implements IProdCatDefAtomSV{

    @Autowired
    ProductCatMapper productCatMapper;
    @Autowired
    ISysSequenceCreditAtomSV sequenceCreditAtomSV;

    @Override
    public PageInfo<ProductCat> queryForPage(Integer pageNo,Integer pageSize,Long parentProductCatId,
            String tenantId, String productCatId, String productCatName, String isChild) {
        ProductCatCriteria example = new ProductCatCriteria();
        ProductCatCriteria.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(tenantId))
            criteria.andTenantIdEqualTo(tenantId);
        if (StringUtils.isNotBlank(productCatId))
            criteria.andProductCatIdLike("%"+productCatId+"%");
        if (StringUtils.isNotBlank(productCatName))
            criteria.andProductCatNameLike("%"+productCatName+"%");
        if (parentProductCatId!=null)
            criteria.andParentProductCatIdEqualTo(parentProductCatId);
        if (StringUtils.isNotBlank(isChild))
            criteria.andIsChildEqualTo(isChild);
        PageInfo<ProductCat> pageInfo = new PageInfo<>();
        //设置总数
        pageInfo.setCount(productCatMapper.countByExample(example));
        if (pageNo != null && pageSize != null) {
            example.setLimitStart((pageNo - 1) * pageSize);
            example.setLimitEnd(pageSize);
        }
        pageInfo.setPageNo(pageNo);
        pageInfo.setPageSize(pageSize);
        pageInfo.setResult(productCatMapper.selectByExample(example));
        return pageInfo;
    }

    @Override
    public ProductCat selectById(String tenantId, String productCatId) {
        ProductCatCriteria example = new ProductCatCriteria();
        example.createCriteria()
                .andTenantIdEqualTo(tenantId).andProductCatIdEqualTo(productCatId)
                .andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
        List<ProductCat> productCatList = productCatMapper.selectByExample(example);
        if(productCatList == null || productCatList.isEmpty())
            return null;
        return productCatList.get(0);
    }

    @Override
    public int insertProductCat(ProductCat productCat) {
        if (productCat==null)
            return 0;
        productCat.setProductCatId(sequenceCreditAtomSV.get6SeqByName()+"");
        if (productCat.getOperTime()==null)
            productCat.setOperTime(DateUtils.currTimeStamp());
        return productCatMapper.insertSelective(productCat);
    }

    @Override
    public int updateProductCat(ProductCat productCat) {
        return productCatMapper.updateByPrimaryKeySelective(productCat);
    }

    @Override
    public int deleteProductCat(String tenantId, String productCatId) {
        ProductCatCriteria example = new ProductCatCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andProductCatIdEqualTo(productCatId);
        return productCatMapper.deleteByExample(example);
    }

}
