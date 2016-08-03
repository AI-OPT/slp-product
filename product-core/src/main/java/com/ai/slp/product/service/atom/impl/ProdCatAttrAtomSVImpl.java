package com.ai.slp.product.service.atom.impl;

import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.constants.ProductCatConstants;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttr;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttrCriteria;
import com.ai.slp.product.dao.mapper.interfaces.ProdCatAttrMapper;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProdCatAttrAtomSVImpl implements IProdCatAttrAtomSV{

    @Autowired
    ProdCatAttrMapper prodCatAttrMapper;
    
    @Override
    public ProdCatAttr selectById(String tenantId, String productCatId) {
        ProdCatAttrCriteria example = new ProdCatAttrCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andProductCatIdEqualTo(productCatId);
        List<ProdCatAttr> prodCatAttrList = prodCatAttrMapper.selectByExample(example);
        if(prodCatAttrList == null || prodCatAttrList.isEmpty())
            return null;
        return prodCatAttrList.get(0);
    }

    @Override
    public int insertProdCatAttr(ProdCatAttr prodCatAttr) {
        if (prodCatAttr.getOperTime()==null)
            prodCatAttr.setOperTime(DateUtils.currTimeStamp());
        prodCatAttr.setCatAttrId(SequenceUtil.genProdCatAttrId());
        return prodCatAttrMapper.insertSelective(prodCatAttr);
    }

    @Override
    public List<ProdCatAttr> queryAttrsByCatId(String tenantId, String catId) {
        ProdCatAttrCriteria example = new ProdCatAttrCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andProductCatIdEqualTo(catId)
                .andStateEqualTo(CommonConstants.STATE_ACTIVE);
        return prodCatAttrMapper.selectByExample(example);
    }

    /**
     * 删除类目的指定属性
     *
     * @param tenantId
     * @param catId
     * @param attrId
     * @param operId
     * @return
     */
    @Override
    public int deleteByCatAttrId(String tenantId, String catId, Long attrId, Long operId) {
        ProdCatAttr prodCatAttr = new ProdCatAttr();
        prodCatAttr.setState(CommonConstants.STATE_INACTIVE);
        prodCatAttr.setOperId(operId);
        prodCatAttr.setOperTime(DateUtils.currTimeStamp());
        ProdCatAttrCriteria example = new ProdCatAttrCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andAttrIdEqualTo(attrId).andProductCatIdEqualTo(catId);
        return prodCatAttrMapper.updateByExampleSelective(prodCatAttr,example);
    }


    @Override
    public int deleteByCatId(String tenantId,String catAttrId,Long operId) {
        ProdCatAttr prodCatAttr = new ProdCatAttr();
        prodCatAttr.setState(CommonConstants.STATE_INACTIVE);
        prodCatAttr.setOperId(operId);
        prodCatAttr.setOperTime(DateUtils.currTimeStamp());
        ProdCatAttrCriteria example = new ProdCatAttrCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andCatAttrIdEqualTo(catAttrId);
        return prodCatAttrMapper.updateByExampleSelective(prodCatAttr,example);
    }

    /**
     * 查询类目下某个类型的属性
     *
     * @param tenantId
     * @param catId
     * @param attrType
     * @return
     */
    @Override
    public List<ProdCatAttr> queryAttrOfCatByIdAndType(String tenantId, String catId, String attrType) {
        ProdCatAttrCriteria example = new ProdCatAttrCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andProductCatIdEqualTo(catId)
                .andAttrTypeEqualTo(attrType)
                .andStateEqualTo(CommonConstants.STATE_ACTIVE);
        return prodCatAttrMapper.selectByExample(example);
    }

    /**
     * 查询类目下指定属性类型和属性标识的关联信息
     *
     * @param tenantId
     * @param catId
     * @param attrId
     * @param attrType
     * @return
     */
    @Override
    public ProdCatAttr queryByCatIdAndTypeAndAttrId(String tenantId, String catId, Long attrId, String attrType) {
        ProdCatAttrCriteria example = new ProdCatAttrCriteria();
        ProdCatAttrCriteria.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(tenantId)
                .andProductCatIdEqualTo(catId)
                .andAttrIdEqualTo(attrId)
                .andStateEqualTo(CommonConstants.STATE_ACTIVE);
        if (StringUtils.isNotBlank(attrType))
            criteria.andAttrTypeEqualTo(attrType);
        List<ProdCatAttr> attrList = prodCatAttrMapper.selectByExample(example);
        return attrList==null||attrList.isEmpty()?null:attrList.get(0);
    }

    /**
     * 更新类目的指定属性
     *
     * @param prodCatAttr
     * @return
     */
    @Override
    public int update(ProdCatAttr prodCatAttr) {
        prodCatAttr.setOperTime(DateUtils.currTimeStamp());
        return prodCatAttrMapper.updateByPrimaryKey(prodCatAttr);
    }

    @Override
    public int selectCatNumByAttrId(String tenantId, Long attrId) {
        ProdCatAttrCriteria example = new ProdCatAttrCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).
        andAttrIdEqualTo(attrId).andStateEqualTo(CommonConstants.STATE_ACTIVE);
        return prodCatAttrMapper.countByExample(example);
    }

	@Override
	public List<ProdCatAttr> queryCatAttrByAttrId(String tenantId, Long attrId) {
		if (StringUtils.isBlank(tenantId) || attrId == null) {
			return new ArrayList<ProdCatAttr>();
		}
		ProdCatAttrCriteria example = new ProdCatAttrCriteria();
		example.createCriteria().andTenantIdEqualTo(tenantId).andAttrIdEqualTo(attrId)
				.andStateEqualTo(CommonConstants.STATE_ACTIVE);
		return prodCatAttrMapper.selectByExample(example);
	}

    /**
     * 查询类目销售属性中需要上传图片的属性
     *
     * @param tenantId
     * @param catId
     * @return
     */
    @Override
    public List<ProdCatAttr> queryAttrOfPicByIdAndSale(String tenantId, String catId) {
        ProdCatAttrCriteria example = new ProdCatAttrCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId)
                .andProductCatIdEqualTo(catId)
                .andAttrTypeEqualTo(ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_SALE)
                .andIsPictureEqualTo(ProductCatConstants.ProductCatAttr.IsPicture.YES)
                .andStateEqualTo(CommonConstants.STATE_ACTIVE);
        return prodCatAttrMapper.selectByExample(example);
    }
}
