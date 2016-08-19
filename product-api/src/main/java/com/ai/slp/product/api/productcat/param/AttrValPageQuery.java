package com.ai.slp.product.api.productcat.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.productcat.interfaces.IAttrAndValDefSV;

import javax.validation.constraints.NotNull;

/**
 * 属性值分页查询请求参数<br>
 *
 * Date: 2016年5月2日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author liutong5
 */
public class AttrValPageQuery extends BaseInfo{
    private static final long serialVersionUID = 1L;

	/**
     * 属性值ID
     */
    private String attrvalueDefId;
    
    /**
     *属性标识,必填
     */
    @NotNull(message = "属性标识不能为空",
    		groups = { IAttrAndValDefSV.QueryPageAttrvalue.class})
    private Long attrId;
    
    /**
     * 请求查询的页码
     * 默认为1
     */
    private Integer pageNo = 1;

    /**
     * 每页显示条数
     */
    private Integer pageSize =20;

    /**
     * 属性值名称
     */
    private String attrValueName;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
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
}
