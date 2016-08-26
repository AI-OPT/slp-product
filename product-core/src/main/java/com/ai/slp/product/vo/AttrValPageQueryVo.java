package com.ai.slp.product.vo;

/**
 * Created by jackieliu on 16/8/26.
 */
public class AttrValPageQueryVo {
    /**
     *属性标识
     */
    private Long attrId;

    /**
     * 属性值标识
     */
    private String attrvalueDefId;

    /**
     * 属性值名称
     */
    private String attrValueName;
    /**
     * 请求查询的页码
     * 默认为1
     */
    private Integer pageNo = 1;

    /**
     * 每页显示条数
     * 默认为20
     */
    private Integer pageSize =20;

    /**
     * 租户Id，必填
     */
    private String tenantId;

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

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
