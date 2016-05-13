package com.ai.slp.product.api.productcat.param;


import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.productcat.interfaces.IAttrAndValDefSV;

import javax.validation.constraints.NotNull;

/**
 * 单个属性查询删除参数
 * 
 * Date: 2016年5月4日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public class AttrPam extends BaseInfo {
    private static final long serialVersionUID = 1L;
	/**
     * 属性标识,查询和删除时不能为空
     */
    @NotNull(message = "属性ID不能为空", groups = { IAttrAndValDefSV.QueryAttr.class,
            IAttrAndValDefSV.DeleteAttr.class})
    private Long attrId;
    /**
     * 操作人ID,删除时不能为空
     */
    @NotNull(message = "操作人ID不能为空", groups = { IAttrAndValDefSV.DeleteAttr.class})
    private Long operId;
    
    public Long getAttrId() {
        return attrId;
    }
    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }
    public Long getOperId() {
        return operId;
    }
    public void setOperId(Long operId) {
        this.operId = operId;
    }

}
