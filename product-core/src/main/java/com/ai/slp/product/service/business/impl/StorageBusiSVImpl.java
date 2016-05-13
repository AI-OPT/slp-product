package com.ai.slp.product.service.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.opt.base.exception.BusinessException;
import com.ai.slp.product.api.storage.param.StorageSalePrice;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.dao.mapper.interfaces.storage.StorageMapper;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.business.interfaces.IStorageBusiSV;

/**
 * 库存业务操作
 *
 * Created by jackieliu on 16/5/5.
 */
public class StorageBusiSVImpl implements IStorageBusiSV {
	@Autowired
	IStorageAtomSV storageAtomSV;
	@Autowired
	private transient StorageMapper storageMapper;
	@Autowired
	IStorageGroupAtomSV storageGroupAtomSV;
	
	/**
	  * 批量更新库存销售价
	  */
	@Override
	public int updateMultiStorageSalePrice(List<StorageSalePrice> salePriceList) {
		int count = 0;
		for(StorageSalePrice storageSalePrice : salePriceList){
			//查看对应的标识下是否存在库存信息
			if(storageMapper.selectByPrimaryKey(storageSalePrice.getStorageId()) == null)
				throw new BusinessException("", "找不到对应的库存信息，库存标识="+storageSalePrice.getStorageId());
			//获取当前库存对应库存组
			StorageGroup StorageGroup = storageGroupAtomSV.queryByGroupId(storageSalePrice.getTenantId(), storageSalePrice.getStorageId());
			//判断销售价是否在库存组价格区间
			if(StorageGroup.getHighSalePrice() != null && StorageGroup.getHighSalePrice() > 0 && storageSalePrice.getSalePrice() > StorageGroup.getHighSalePrice())
				throw new BusinessException("", "销售价不能大于库存组最高价,库存组最高价="+StorageGroup.getHighSalePrice());
			if(storageSalePrice.getSalePrice() < StorageGroup.getLowSalePrice())
				throw new BusinessException("", "销售价不能小于库存组最低价,库存组最低价="+StorageGroup.getLowSalePrice());
			//更新库存价格信息
			Storage storage = new Storage();
			storage.setStorageId(storageSalePrice.getStorageId());
			storage.setSalePrice(storageSalePrice.getSalePrice());
			storage.setOperId(storageSalePrice.getOperId());
			storageAtomSV.updateSaleById(storage);
			count++;
		}
		return count;
	}

}
