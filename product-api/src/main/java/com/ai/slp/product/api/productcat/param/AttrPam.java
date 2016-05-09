package com.ai.slp.product.api.productcat.param;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.productcat.interfaces.IAttrAndValDefSV;

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
     * 属性标识
     */
    @NotNull(message = "属性ID不能为空", groups = { IAttrAndValDefSV.QueryAttr.class,
            IAttrAndValDefSV.DeleteAttr.class})
    private long attrId;
    /**
     * 操作人ID
     */
    @NotNull(message = "操作人ID不能为空", groups = { IAttrAndValDefSV.DeleteAttr.class})
    private long operId;
    /**
     * 操作时间
     */
    private Timestamp operTime;
    
    public long getAttrId() {
        return attrId;
    }
    public void setAttrId(long attrId) {
        this.attrId = attrId;
    }
    public long getOperId() {
        return operId;
    }
    public void setOperId(long operId) {
        this.operId = operId;
    }
    public Timestamp getOperTime() {
        return operTime;
    }
    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
    }

}
