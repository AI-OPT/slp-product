package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 库存唯一请求信息<br>
 * 可用于库存标识的查询,删除等
 *
 * Date: 2016年8月5日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StorageUniQuery extends BaseInfo{
    /**
     * 销售商（商户）标识，必填<br>
     */
    @NotBlank(message = "销售商（商户）标识不能为空")
    private String supplierId;
    /**
     * 库存标识,必填
     */
    @NotBlank(message = "库存标识不能为空")
    private String storageId;

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }
}
