package com.ai.slp.product.service.atom.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.constants.ProductCatConstants;
import com.ai.slp.product.dao.mapper.attach.ProdAttrAndValIdAttrch;
import com.ai.slp.product.dao.mapper.attach.ProdAttrAndValIdAttrchMapper;
import com.ai.slp.product.dao.mapper.attach.ProdCatAttrAttch;
import com.ai.slp.product.dao.mapper.attach.ProdCatAttrXmlAttachMapper;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttr;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttrCriteria;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttrValueCriteria;
import com.ai.slp.product.dao.mapper.interfaces.ProdCatAttrMapper;
import com.ai.slp.product.dao.mapper.interfaces.ProdCatAttrValueMapper;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;

@Component
public class ProdCatAttrAtomSVImpl implements IProdCatAttrAtomSV{

    @Autowired
    ProdCatAttrMapper prodCatAttrMapper;
    @Autowired
    ProdCatAttrXmlAttachMapper attrXmlAttachMapper;
    @Autowired
    ProdCatAttrValueMapper prodCatAttrValueMapper;
    @Autowired
    ProdAttrAndValIdAttrchMapper prodAttrAndValIdAttrchMapper;

    @Override
    public ProdCatAttr selectById(String tenantId, String catAttrId) {
        ProdCatAttr prodCatAttr = prodCatAttrMapper.selectByPrimaryKey(catAttrId);
        if (prodCatAttr!=null && !tenantId.equals(prodCatAttr.getTenantId())){
        	prodCatAttr = null;
        }
        return prodCatAttr;
    }

    @Override
    public int insertProdCatAttr(ProdCatAttr prodCatAttr) {
        if (prodCatAttr.getOperTime()==null){
        	prodCatAttr.setOperTime(DateUtils.currTimeStamp());
        }
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
     * 查询所有非指定的属性的关系标识
     *
     * @param tenantId
     * @param catId
     * @param attrType
     * @param attrIdList 指定的属性标识
     * @return
     */
    @Override
    public List<String> queryIdsOfNoAttrId(String tenantId, String catId, String attrType, List<Long> attrIdList) {
        ProdCatAttrCriteria example = new ProdCatAttrCriteria();
        ProdCatAttrCriteria.Criteria criteria = example.createCriteria()
                .andTenantIdEqualTo(tenantId).andProductCatIdEqualTo(catId)
                .andStateEqualTo(CommonConstants.STATE_ACTIVE).andAttrTypeEqualTo(attrType);
        if (!CollectionUtil.isEmpty(attrIdList)){
        	criteria.andAttrIdNotIn(attrIdList);
        }
        return attrXmlAttachMapper.selectCatAttrIdS(example);
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

    /**
     * 删除非指定属性对应关系
     *
     * @param tenantId
     * @param catId
     * @param attrType
     * @param attrIdList 指定的属性标识
     * @param operId     @return
     */
    @Override
    public int deleteNoAttrId(String tenantId, String catId, String attrType, List<Long> attrIdList, Long operId) {
        ProdCatAttrCriteria example = new ProdCatAttrCriteria();
        ProdCatAttrCriteria.Criteria criteria = example.createCriteria()
                .andTenantIdEqualTo(tenantId).andProductCatIdEqualTo(catId)
                .andStateEqualTo(CommonConstants.STATE_ACTIVE).andAttrTypeEqualTo(attrType);
        if (!CollectionUtil.isEmpty(attrIdList)){
        	criteria.andAttrIdNotIn(attrIdList);
        }
        ProdCatAttr catAttr = new ProdCatAttr();
        catAttr.setState(CommonConstants.STATE_INACTIVE);
        catAttr.setOperId(operId);
        catAttr.setOperTime(DateUtils.currTimeStamp());
        return prodCatAttrMapper.updateByExampleSelective(catAttr,example);
    }


    @Override
    public int deleteByCatAttrId(String tenantId, String catAttrId, Long operId) {
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
    
    @Override
    public List<ProdCatAttr> queryAttrOfCatByIdAndType(String tenantId, String catId,String attrType, String orderField){
    	ProdCatAttrCriteria example = new ProdCatAttrCriteria();
        if(StringUtils.isNotBlank(orderField)){
        	example.setOrderByClause(orderField);
        }
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
        if (StringUtils.isNotBlank(attrType)){
        	criteria.andAttrTypeEqualTo(attrType);
        }
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
    /**
     * 根据租户ID属性ID查询商品类目属性关系的数量
     */
    @Override
    public int selectCatNumByAttrId(String tenantId, Long attrId) {
        ProdCatAttrCriteria example = new ProdCatAttrCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).
        andAttrIdEqualTo(attrId).andStateEqualTo(CommonConstants.STATE_ACTIVE);
        return prodCatAttrMapper.countByExample(example);
    }

    /**
     * 根据租户ID  属性值ID查询商品类目属性值关系的数量
     */
    public int selectCatNumByAttrValueId(String tenantId, String attrvalueDefId) {
    	ProdCatAttrValueCriteria example = new ProdCatAttrValueCriteria();
    	example.createCriteria().andTenantIdEqualTo(tenantId).
        andAttrvalueDefIdEqualTo(attrvalueDefId).andStateEqualTo(CommonConstants.STATE_ACTIVE);
    	return prodCatAttrValueMapper.countByExample(example);
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
    
    /**
     * 查询类目下某个类型的属性标识和属性值标识集合
     */
    @Override
    public  List<ProdAttrAndValIdAttrch> queryAttrAndValIdByCatIdAndType(String tenantId, String productCatId, String attrType) {
        return prodAttrAndValIdAttrchMapper.queryAttrAndValIdByCatIdAndType(tenantId,productCatId,attrType);
    }
}
