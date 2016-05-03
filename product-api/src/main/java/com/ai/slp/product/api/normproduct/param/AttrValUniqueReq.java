package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.normproduct.interfaces.IAttrAndValDefSV;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 属性值唯一标识请求信息<br>
 *
 * Date: 2016年5月2日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class AttrValUniqueReq extends BaseInfo{
    /**
     * 属性ID
     */
    private long attrId;

    /**
     * 属性值ID
     */
    @NotNull(message = "属性值ID不能为空",
            groups = {IAttrAndValDefSV.QueryAttrVal.class,
                    IAttrAndValDefSV.DeleteAttrVal.class})
    private String attrvalueDefId;
    /**
     * 操作人ID
     */
    @NotNull(message = "操作人ID不能为空",
            groups = {IAttrAndValDefSV.DeleteAttrVal.class})
    private Long operId;

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

    public String getAttrvalueDefId() {
        return attrvalueDefId;
    }

    public void setAttrvalueDefId(String attrvalueDefId) {
        this.attrvalueDefId = attrvalueDefId;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public Timestamp getOperTime() {
        return operTime;
    }

    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
    }
}
