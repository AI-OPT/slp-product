package com.ai.slp.product.api.productcat.param;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.productcat.interfaces.IAttrAndValDefSV;

/**
 * 属性值请求信息<br>
 *
 * Date: 2016年5月2日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class AttrValUniqueReq extends BaseInfo{
    private static final long serialVersionUID = 1L;

	/**
     * 属性ID
     */
    private Long attrId;

    /**
     * 属性值ID
     */
    @NotBlank(message = "属性值ID不能为空",
            groups = {IAttrAndValDefSV.QueryAttrVal.class,
                    IAttrAndValDefSV.DeleteAttrVal.class})
    private String attrvalueDefId;
    /**
     * 操作人ID
     */
    @NotBlank(message = "操作人ID不能为空",
            groups = {IAttrAndValDefSV.DeleteAttrVal.class})
    private Long operId;

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

}
