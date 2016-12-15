package com.ai.slp.product.service.atom.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.constants.ProductCatConstants;
import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.dao.mapper.bo.ProductCatCriteria;
import com.ai.slp.product.dao.mapper.interfaces.ProductCatMapper;
import com.ai.slp.product.service.atom.interfaces.IProdCatDefAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;

/**
 * 类目操作
 */
@Component
public class ProdCatDefAtomSVImpl implements IProdCatDefAtomSV{
    @Autowired
    ProductCatMapper productCatMapper;

    @Override
    public PageInfo<ProductCat> queryForPage(Integer pageNo,Integer pageSize,String parentProductCatId,
            String tenantId, String productCatId, String productCatName, String isChild) {
        ProductCatCriteria example = new ProductCatCriteria();
        example.setOrderByClause("SERIAL_NUMBER ASC,OPER_TIME DESC");//顺序号正序
        ProductCatCriteria.Criteria criteria = example.createCriteria();
        criteria.andStateEqualTo(CommonConstants.STATE_ACTIVE);
        if (StringUtils.isNotBlank(tenantId)){
        	criteria.andTenantIdEqualTo(tenantId);
        }
        if (StringUtils.isNotBlank(productCatId)){
        	criteria.andProductCatIdEqualTo(productCatId);
        }
        if (StringUtils.isNotBlank(productCatName)){
        	criteria.andProductCatNameLike("%"+productCatName+"%");
        }
        if (StringUtils.isNotBlank(parentProductCatId)){
        	criteria.andParentProductCatIdEqualTo(parentProductCatId);
        }
        if (StringUtils.isNotBlank(isChild)){
        	criteria.andIsChildEqualTo(isChild);
        }
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
        ProductCat productCat = productCatMapper.selectByPrimaryKey(productCatId);
        //租户不一致或状态不为有效
        if (productCat!=null
                && (!productCat.getTenantId().equals(tenantId)
                    || !CommonConstants.STATE_ACTIVE.equals(productCat.getState()))){
        	productCat = null;
        }
        return productCat;
    }

    /**
     * 查询指定类目信息,不区分状态
     *
     * @param productCatId 商品类目标识
     * @return
     * @author liutong
     */
    @Override
    public ProductCat selectById(String productCatId) {
        return StringUtils.isBlank(productCatId)?null:productCatMapper.selectByPrimaryKey(productCatId);
    }

    /**
     * 查询类目信息,包括所有状态
     *
     * @param tenantId
     * @param productCatId
     * @return
     */
    @Override
    public ProductCat selectAllStateById(String tenantId, String productCatId) {
        ProductCat productCat = productCatMapper.selectByPrimaryKey(productCatId);
        if (productCat!=null
                && !productCat.getTenantId().equals(productCat.getTenantId())){
        	productCat = null;
        }
        return productCat;
    }

    @Override
    public int insertProductCat(ProductCat productCat) {
        if (productCat==null){
        	return 0;
        }
        productCat.setProductCatId(SequenceUtil.genProductCatId());
        //若为设置父类目标识,则为根类目
        if (StringUtils.isBlank(productCat.getParentProductCatId())){
        	productCat.setParentProductCatId(ProductCatConstants.ProductCat.ParentProductCatId.ROOT_CAT);
        }
        if (productCat.getOperTime()==null){
        	productCat.setOperTime(DateUtils.currTimeStamp());
        }
        productCat.setState(CommonConstants.STATE_ACTIVE);
        return productCatMapper.insertSelective(productCat);
    }

    @Override
    public int updateProductCat(ProductCat productCat) {
        if (productCat==null ){
        	return 0;
        }
        if (StringUtils.isBlank(productCat.getTenantId())
                || StringUtils.isBlank(productCat.getProductCatId())) {
            throw new BusinessException("", "参数不完整,无法更新,租户ID:" +productCat.getTenantId()
                    +",类目标识:"+productCat.getProductCatId());
        }
        ProductCatCriteria example = new ProductCatCriteria();
        example.createCriteria().andTenantIdEqualTo(productCat.getTenantId())
                .andProductCatIdEqualTo(productCat.getProductCatId());
        productCat.setOperTime(DateUtils.currTimeStamp());
        productCat.setCatLevel(null);//不允许更新等级
        productCat.setParentProductCatId(null);//不允许更新父类目
        productCat.setState(null);//不允许更新状态
        return productCatMapper.updateByExampleSelective(productCat,example);
    }

    @Override
    public int deleteProductCat(String tenantId, String productCatId,Long operId) {
        ProductCatCriteria example = new ProductCatCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andProductCatIdEqualTo(productCatId);
        ProductCat productCat = new ProductCat();
        productCat.setState(CommonConstants.STATE_INACTIVE);
        productCat.setOperId(operId);
        productCat.setOperTime(DateUtils.currTimeStamp());
        return productCatMapper.updateByExampleSelective(productCat,example);
    }

    @Override
    public int queryActiveOfParent(String parentCatId) {
        ProductCatCriteria example = new ProductCatCriteria();
        example.createCriteria().andParentProductCatIdEqualTo(parentCatId)
            .andStateEqualTo(CommonConstants.STATE_ACTIVE);
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
    public List<ProductCat> queryByNameOrFirst(String tenantId, String parentCatId, String query, Boolean isName) {
        ProductCatCriteria example = new ProductCatCriteria();
        
        //example.setOrderByClause("SERIAL_NUMBER ");
        
        ProductCatCriteria.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(tenantId)
                .andStateEqualTo(CommonConstants.STATE_ACTIVE);
        //若为设置父类目标识,则使用根类目
        criteria.andParentProductCatIdEqualTo(
                StringUtils.isBlank(parentCatId)? ProductCatConstants.ProductCat.ParentProductCatId.ROOT_CAT:parentCatId);

        if (StringUtils.isNotBlank(query)){
            if (isName){
            	criteria.andProductCatNameLike("%" + query + "%");
            }
            else{
            	criteria.andFirstLetterEqualTo(query);
            }
        }

        return productCatMapper.selectByExample(example);
    }

}
