package com.ai.slp.product.api.productcat.param;

import javax.validation.constraints.NotNull;

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
	 * 属性ID,删除属性值时不能为空(用于查询类目关联情况)
	 */
	@NotNull(message = "属性ID不能为空",
			 groups = {IAttrAndValDefSV.DeleteAttrvalue.class})
	private Long attrId;

    /**
     * 属性值ID,查询和删除属性值时不能为空
     */
    @NotBlank(message = "属性值ID不能为空",
            groups = {IAttrAndValDefSV.QueryAttrvalue.class,
                    IAttrAndValDefSV.DeleteAttrvalue.class})
    private String attrvalueDefId;
    
    /**
     * 操作人ID,伤处属性值时不能为空
     */
    @NotNull(message = "操作人ID不能为空",
            groups = {IAttrAndValDefSV.DeleteAttrvalue.class})
    private Long operId;

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

	public Long getAttrId() {
		return attrId;
	}

	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}

}
