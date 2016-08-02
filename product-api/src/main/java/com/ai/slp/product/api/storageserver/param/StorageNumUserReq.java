package com.ai.slp.product.api.storageserver.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.storageserver.interfaces.IStorageNumSV;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * 使用库存量请求信息 <br>
 *
 * Date: 2016年5月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StorageNumUserReq extends BaseInfo {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 单品SKU标识
     */
    @NotBlank(message = "单品标识不能为空",groups = {
            IStorageNumSV.UseStorageNum.class,IStorageNumSV.AddSaleNumOfProduct.class})
    private String skuId;
    /**
     *单品数量
     */
    @Min(value = 1,message = "单品数量不能小于1",groups = {
            IStorageNumSV.UseStorageNum.class,IStorageNumSV.AddSaleNumOfProduct.class})
    private int skuNum;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public int getSkuNum() {
        return skuNum;
    }

    public void setSkuNum(int skuNum) {
        this.skuNum = skuNum;
    }
}
