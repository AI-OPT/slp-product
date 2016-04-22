package com.ai.slp.product.api.storage.param;

import java.util.Date;

/**
 * 预警接收人信息<br>
 *
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class WarnReceiveStaff extends WarnReceiveStaffOper {

    /**
     * 预警人id
     */
    private long staffId;

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }
}
