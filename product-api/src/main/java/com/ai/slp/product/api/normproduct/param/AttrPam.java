package com.ai.slp.product.api.normproduct.param;

import java.sql.Date;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 单个属性查询删除参数
 * 
 * Date: 2016年5月4日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class AttrPam extends BaseInfo {
    /**
     * 属性ID
     */
    private long attrId;
    /**
     * 操作人ID
     */
    private long operId;
    /**
     * 操作时间
     */
    private Date operTime;

}
