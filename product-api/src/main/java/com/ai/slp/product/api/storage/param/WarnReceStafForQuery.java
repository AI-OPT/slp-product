package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 预警接收人查询入参
 * 
 * Date: 2016年5月5日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class WarnReceStafForQuery extends BaseInfo{

    /**
     *库存标识
     */
    private String storageId;

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }
    
}
