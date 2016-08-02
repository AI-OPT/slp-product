package com.ai.slp.product.api.storageserver.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.storageserver.interfaces.IStorageNumSV;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 回退库存量请求信息 <br>
 *
 * Date: 2016年5月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StorageNumBackReq extends BaseInfo {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 单品SKU标识
     */
    @NotBlank(message = "单品标识不能为空",groups = IStorageNumSV.BackStorageNum.class)
    private String skuId;

    /**
     * 每个库存的回退量
     */
    @NotNull(message = "库存回退量不能为空",groups = IStorageNumSV.BackStorageNum.class)
    @NotEmpty(message = "库存回退量不能为空",groups = IStorageNumSV.BackStorageNum.class)
    private Map<String,Integer> storageNum;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public Map<String, Integer> getStorageNum() {
        return storageNum;
    }

    public void setStorageNum(Map<String, Integer> storageNum) {
        this.storageNum = storageNum;
    }
}
