package com.ai.slp.product.service.atom.impl;

import com.ai.slp.product.dao.mapper.attach.CatAttrValAttach;
import com.ai.slp.product.dao.mapper.attach.ProdCatAttrAttachMapper;
import com.ai.slp.product.dao.mapper.attach.ProdCatAttrAttch;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAttachAtomSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jackieliu on 16/5/2.
 */
@Component
public class ProdCatAttrAttachAtomSVImpl implements IProdCatAttrAttachAtomSV {
	@Autowired
	ProdCatAttrAttachMapper catAttrAttachMapper;

	/**
	 * 查询指定类目下的属性信息
	 *
	 * @param tenantId
	 * @param catId
	 * @param attrType
	 * @return
	 */
	@Override
	public List<ProdCatAttrAttch> queryAttrOfByIdAndType(String tenantId, String catId, String attrType) {
		return catAttrAttachMapper.selectCatAttr(tenantId, catId, attrType);
	}

	/**
	 * 查询类目关联属性的属性值
	 *
	 * @param tenantId
	 *            租户id
	 * @param catAttrId
	 *            类目属性关联id
	 * @return
	 */
	@Override
	public List<CatAttrValAttach> queryValListByCatAttr(String tenantId, String catAttrId) {
		return catAttrAttachMapper.selectCatAttrVal(tenantId, catAttrId);
	}


}
