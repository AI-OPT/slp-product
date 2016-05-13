package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.storage.interfaces.IWarnReceiveStaffSV;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 预警接收人查询入参
 * 
 * Date: 2016年5月5日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public class WarnReceStafForQuery extends BaseInfo{

    private static final long serialVersionUID = 1L;
	/**
     *库存标识,不能为空
     */
    @NotBlank(message = "库存标识不能为空",groups = {IWarnReceiveStaffSV.QueryByObjectIdOfStorage.class})
    private String storageId;

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }
    
}
