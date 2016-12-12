package com.ai.slp.product.service.atom.impl;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.dao.mapper.bo.ProdAttrDef;
import com.ai.slp.product.dao.mapper.bo.ProdAttrDefCriteria;
import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDefCriteria;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttrCriteria;
import com.ai.slp.product.dao.mapper.interfaces.ProdAttrDefMapper;
import com.ai.slp.product.dao.mapper.interfaces.ProdAttrvalueDefMapper;
import com.ai.slp.product.dao.mapper.interfaces.ProdCatAttrMapper;
import com.ai.slp.product.service.atom.interfaces.IProdAttrDefAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;
import com.ai.slp.product.vo.AttrPageQueryVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 属性定义原子操作
 * Created by jackieliu on 16/4/27.
 */
@Component
public class ProdAttrDefAtomSVImpl implements IProdAttrDefAtomSV {
    @Autowired
    ProdAttrDefMapper prodAttrDefMapper;
    @Autowired
    ProdAttrvalueDefMapper prodAttrvalueDefMapper;
    @Autowired
    ProdCatAttrMapper prodCatAttrMapper;


    @Override
    public int installObj(ProdAttrDef productAttr) {
        productAttr.setAttrId(SequenceUtil.createAttrDefId());
        if(productAttr.getOperTime() == null){
            productAttr.setOperTime(DateUtils.currTimeStamp());
        }
        return prodAttrDefMapper.insertSelective(productAttr);
    }

    @Override
    public ProdAttrDef selectById(String tenantId, Long attrId) {
        ProdAttrDefCriteria example = new ProdAttrDefCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andAttrIdEqualTo(attrId).andStateEqualTo(CommonConstants.STATE_ACTIVE);
        List<ProdAttrDef> prodAttrDefList = prodAttrDefMapper.selectByExample(example);
        return (prodAttrDefList==null|| prodAttrDefList.isEmpty())?null:prodAttrDefList.get(0);
    }

    @Override
    public int deleteById(String tenantId, Long attrId, Long operId) {
        ProdAttrDef prodAttrDef = new ProdAttrDef();
        prodAttrDef.setState(CommonConstants.STATE_INACTIVE);
        prodAttrDef.setOperId(operId);
        prodAttrDef.setOperTime(DateUtils.currTimeStamp());
        
        ProdAttrDefCriteria example = new ProdAttrDefCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andAttrIdEqualTo(attrId);
        
        return prodAttrDefMapper.updateByExampleSelective(prodAttrDef, example);
    }

    @Override
    public PageInfo<ProdAttrDef> selectPageAttrs(AttrPageQueryVo attrPageQueryVo) {
        //添加查询条件参数
        ProdAttrDefCriteria example = new ProdAttrDefCriteria();
        ProdAttrDefCriteria.Criteria request = example.createCriteria();
        request.andTenantIdEqualTo(attrPageQueryVo.getTenantId());
        if(attrPageQueryVo.getAttrId() != null){
        	request.andAttrIdEqualTo(attrPageQueryVo.getAttrId());
        }

        //属性名称模糊查询
        if(StringUtils.isNoneBlank(attrPageQueryVo.getAttrName())){
           // request.andAttrNameEqualTo(attrPageQueryVo.getAttrName());
        	request.andAttrNameLike("%"+ attrPageQueryVo.getAttrName() +"%");
        }

        if(StringUtils.isNotBlank(attrPageQueryVo.getValueWay())){
            request.andValueWayEqualTo(attrPageQueryVo.getValueWay());
        }
        //设置数据的查询状态为有效状态
        request.andStateEqualTo(CommonConstants.STATE_ACTIVE);
        
        //example.setOrderByClause("OPER_TIME desc");//操作时间倒序
        
        //获取查询到的条目数
        int count = prodAttrDefMapper.countByExample(example);
        if(attrPageQueryVo.getPageNo() != null && attrPageQueryVo.getPageSize() != null){
            example.setLimitStart((attrPageQueryVo.getPageNo()-1) * attrPageQueryVo.getPageSize());
            example.setLimitEnd(attrPageQueryVo.getPageSize());
        }
        //分页返回对象设置
        PageInfo<ProdAttrDef> pageInfo = new PageInfo<ProdAttrDef>();
        pageInfo.setPageNo(attrPageQueryVo.getPageNo());
        pageInfo.setPageSize(attrPageQueryVo.getPageSize());
        pageInfo.setResult(prodAttrDefMapper.selectByExample(example));
        pageInfo.setCount(count);
            
        return pageInfo;
    }

    @Override
    public int selectAttrvalNum(String tenantId, Long attrId) {
        ProdAttrvalueDefCriteria example = new ProdAttrvalueDefCriteria();
        example.createCriteria().andTenantIdEqualTo(tenantId).andAttrIdEqualTo(attrId).andStateEqualTo(CommonConstants.STATE_ACTIVE);
        return prodAttrvalueDefMapper.countByExample(example);
    }

    @Override
    public int updateAttr(ProdAttrDef prodAttrDef) {
        prodAttrDef.setOperTime(DateUtils.currTimeStamp());
        return prodAttrDefMapper.updateByPrimaryKeySelective(prodAttrDef);
    }

    @Override
    public List<ProdAttrDef> selectAllAttrsOfFirstLetter(String tenantId) {
        ProdAttrDefCriteria example = new ProdAttrDefCriteria();
        
        //example.setOrderByClause("first_letter");
        
        example.createCriteria().andTenantIdEqualTo(tenantId).andStateEqualTo(CommonConstants.STATE_ACTIVE);
        return prodAttrDefMapper.selectByExample(example);
    }


	@Override
	public int selectNumById(String tenantId, Long attrId) {
		ProdCatAttrCriteria example = new ProdCatAttrCriteria();
		 example.createCriteria().andTenantIdEqualTo(tenantId).andAttrIdEqualTo(attrId).andStateEqualTo(CommonConstants.STATE_ACTIVE);
		return prodCatAttrMapper.countByExample(example);
	}


    
}
