package com.ai.slp.product.service.atom.impl;

import java.util.List;

import com.ai.opt.base.exception.BusinessException;
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
        criteria.andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
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
        productCat.setState(CommonSatesConstants.STATE_ACTIVE);
        return productCatMapper.insertSelective(productCat);
    }

    @Override
    public int updateProductCat(ProductCat productCat) {
        if (productCat==null )
            return 0;
        if (StringUtils.isBlank(productCat.getTenantId())
                || StringUtils.isBlank(productCat.getProductCatId())) {
            throw new BusinessException("", "参数不完整,无法更新,租户ID:" +productCat.getTenantId()
                    +",类目标识:"+productCat.getProductCatId());
        }
        ProductCatCriteria example = new ProductCatCriteria();
        example.createCriteria().andTenantIdEqualTo(productCat.getTenantId())
                .andProductCatIdEqualTo(productCat.getProductCatId());
        if (productCat.getOperTime()==null)
            productCat.setOperTime(DateUtils.currTimeStamp());
        productCat.setCatLevel(null);//不允许更新等级
        productCat.setParentProductCatId(null);//不允许更新父类目
        productCat.setState(null);//不允许更新状态
        return productCatMapper.updateByExampleSelective(productCat,example);
    }

    @Override
    public int deleteProductCat(String tenantId, String productCatId) {
        ProductCatCriteria example = new ProductCatCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andProductCatIdEqualTo(productCatId);
        return productCatMapper.deleteByExample(example);
    }

    @Override
    public int queryOfParent(Long parentCatId) {
        ProductCatCriteria example = new ProductCatCriteria();
        example.createCriteria().andParentProductCatIdEqualTo(parentCatId);
        return productCatMapper.countByExample(example);
    }

    /**
     * 根据名称或首字母查询
     *
     * @param tenantId
     * @param parentCatId
     * @param query
     * @param isName
     * @return
     */
    @Override
    public List<ProductCat> queryByNameOrFirst(String tenantId, Long parentCatId, String query, Boolean isName) {
        ProductCatCriteria example = new ProductCatCriteria();
        example.setOrderByClause("SERIAL_NUMBER ");
        ProductCatCriteria.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(tenantId)
                .andStateEqualTo(CommonSatesConstants.STATE_ACTIVE);
        if (parentCatId!=null)
            criteria.andParentProductCatIdEqualTo(parentCatId);

        if (StringUtils.isNotBlank(query))
            if (isName)
                criteria.andProductCatNameLike("%" + query + "%");
            else
                criteria.andFirstLetterEqualTo(query);

        return productCatMapper.selectByExample(example);
    }

}
