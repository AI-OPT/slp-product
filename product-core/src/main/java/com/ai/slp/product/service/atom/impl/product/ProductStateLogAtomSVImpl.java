package com.ai.slp.product.service.atom.impl.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.slp.product.dao.mapper.bo.product.ProductStateLog;
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
		if(productStateLog.getOperTime()==null)
			productStateLog.setOperTime(DateUtils.currTimeStamp());
		productStateLog.setLogId(SequenceUtil.genProductStateLogId());
		return productStateLogMapper.insert(productStateLog);
	}

}
