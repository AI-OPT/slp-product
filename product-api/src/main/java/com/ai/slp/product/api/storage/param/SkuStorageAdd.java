package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 *	sku库存新增信息
 *
 * Date: 2016年5月21日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author lipeng16
 */
public class SkuStorageAdd extends BaseInfo {

	private static final long serialVersionUID = 1L;

	/**
	 * 单品SKU标识
	 */
	@NotBlank(message = "单品标识不能为空")
	private String skuId;

	/**
	 * 库存标识
	 */
	@NotBlank(message = "库存标识不能为空")
	private String storageId;

	/**
	 * 库存量
	 */
	@NotNull(message = "库存量不能为空")
	private long totalNum;

	/**
	 * 可用量
	 */
	private long usableNum;

	/**
	 * 操作人
	 */
	@NotNull(message = "操作人标识不能为空")
	private Long operId;

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}

	public long getUsableNum() {
		return usableNum;
	}

	public void setUsableNum(long usableNum) {
		this.usableNum = usableNum;
	}

	public Long getOperId() {
		return operId;
	}

	public void setOperId(Long operId) {
		this.operId = operId;
	}

}
