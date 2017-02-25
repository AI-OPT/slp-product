package com.ai.slp.product.service.atom.impl.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.dao.mapper.bo.product.ProductStateLog;
import com.ai.slp.product.dao.mapper.bo.product.ProductStateLogCriteria;
import com.ai.slp.product.dao.mapper.bo.product.ProductStateLogCriteria.Criteria;
import com.ai.slp.product.dao.mapper.interfaces.product.ProductStateLogMapper;
import com.ai.slp.product.service.atom.interfaces.product.IProductStateLogAtomSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.util.SequenceUtil;

/**
 * @author lipeng16
 *
 */
@Component
public class ProductStateLogAtomSVImpl implements IProductStateLogAtomSV{
	@Autowired
	ProductStateLogMapper productStateLogMapper;

	/**
	 * 添加商品状态日志
	 */
	@Override
	public int insert(ProductStateLog productStateLog) {
		if(productStateLog.getOperTime()==null){
			productStateLog.setOperTime(DateUtils.currTimeStamp());
		}
		productStateLog.setLogId(SequenceUtil.genProductStateLogId());
		return productStateLogMapper.insert(productStateLog);
	}
	/**
	 * 根据ID查询   拒绝原因   拒绝描述
	 */
	@Override
	public ProductStateLog selectRefuseById(String prodId) {
		ProductStateLogCriteria example = new ProductStateLogCriteria();
		example.createCriteria().andProdIdEqualTo(prodId);
		List<ProductStateLog> productStateLogList = productStateLogMapper.selectByExample(example);
		return CollectionUtil.isEmpty(productStateLogList)?null:productStateLogList.get(0);
	}
	/**
	 * 根据ID查询   拒绝原因   拒绝描述 -- 按操作时间倒序
	 */
	@Override
	public ProductStateLog selectStateLogByProdId(String prodId) {
		ProductStateLogCriteria example = new ProductStateLogCriteria();
		
		example.setOrderByClause("OPER_TIME desc");//操作时间倒序
		
		example.createCriteria().andProdIdEqualTo(prodId);
		List<ProductStateLog> productStateLogList = productStateLogMapper.selectByExample(example);
		return CollectionUtil.isEmpty(productStateLogList)?null:productStateLogList.get(0);
	}

}
