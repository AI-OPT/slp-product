package com.ai.slp.product.service.business.interfaces;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.slp.product.api.storage.param.StorageSalePrice;

/**
 * 库存业务操作
 * Created by jackieliu on 16/5/4.
 */

public interface IStorageBusiSV {
	 /**
	  * 批量更新库存销售价
	  * 
	 * @param salePriceList
	 * @return
	 * @author lipeng16
	 */
	public int updateMultiStorageSalePrice(List<StorageSalePrice> salePriceList);
}
