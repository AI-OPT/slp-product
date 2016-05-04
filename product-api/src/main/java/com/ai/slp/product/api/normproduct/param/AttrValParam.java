package com.ai.slp.product.api.normproduct.param;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.normproduct.interfaces.IAttrAndValDefSV;

/**
 * 属性值添加修改请求参数<br>
 * 
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng
 */
public class AttrValParam extends BaseInfo {
	
	/**
	 * 属性ID
	 */
    @NotNull(message = "属性ID不能为空",
            groups = {IAttrAndValDefSV.DeleteAttrVal.class,
                    IAttrAndValDefSV.UpdateAttrVal.class})
	private long attrId;
	
	/**
	 * 属性值ID
	 */
	private String attrvalueDefId;
	
	/**
	 * 属性值名称
	 */
	@NotNull(message = "属性值不能为空",
	        groups = {IAttrAndValDefSV.AddAttrVal.class})
	private String attrValueName;
	
	/**
	 * 首字母
	 */
	private String firstLetter;
    
    /**
     * 操作人ID
     */
    private long operId;
    
    /**
     * 操作时间
     */
    private Timestamp operTime;


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
    
	public Long getAttrId() {
		return attrId;
	}

	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}

	public String getAttrvalueDefId() {
		return attrvalueDefId;
	}

	public void setAttrvalueDefId(String attrvalueDefId) {
		this.attrvalueDefId = attrvalueDefId;
	}

	public String getAttrValueName() {
		return attrValueName;
	}

	public void setAttrValueName(String attrValueName) {
		this.attrValueName = attrValueName;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}
	
}
