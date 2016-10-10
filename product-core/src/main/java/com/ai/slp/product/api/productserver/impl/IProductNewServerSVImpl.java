package com.ai.slp.product.api.productserver.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.slp.product.api.productserver.interfaces.IProductNewServerSV;
import com.ai.slp.product.api.productserver.param.ProdInfoQuery;
import com.ai.slp.product.api.productserver.param.ProductInfoOfSku;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.util.CommonUtils;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * 
 * @author jiawen on 16/10/10.
 *
 */
@Service(validation = "true")
@Component
public class IProductNewServerSVImpl implements IProductNewServerSV{
	@Autowired
    IProductBusiSV productBusiSV;
	
	/**
	 * 根据商品编码查询商品
	 * @param 
     * @return 商品信息
     * @throws BusinessException
     * @throws SystemException
     * @author jiawen
     * @ApiDocMethod
	 */
	@Override
	public ProductInfoOfSku queryProductSkuByProdCode(ProdInfoQuery query) 
			throws BusinessException, SystemException {
		//校验租户标识
		CommonUtils.checkTenantId(query.getTenantId());
		return productBusiSV.queryByProdCode(query.getTenantId(), query.getId());
	}

}
