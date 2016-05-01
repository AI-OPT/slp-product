package com.ai.slp.product.api.normproduct.param;

import com.ai.slp.product.api.normproduct.interfaces.INormProductSV;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 标准品将保存信息<br>
 *
 * Date: 2016年4月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class NormProdSaveRequest extends NormProductBase {

    /**
     * 创建时间<br>
     * 创建时赋值,更新时不进行操作<br>
     *     若为空,更新时则填充服务接收时间
     */
    private Date createTime;

    /**
     * 创建人ID<br>
     * 添加时不能为空,更新时不进行操作
     */
    @NotNull(message = "创建人ID不能为空",
            groups = { INormProductSV.SaveProductInfo.class})
    private Long createId;

    /**
     * 操作人ID<br>
     * 更新时不能为空
     */
    @NotNull(message = "创建人ID不能为空",
            groups = { INormProductSV.UpdateProductInfo.class})
    private Long operId;
    /**
     * 操作时间<br>
     * 若为空,更新时则填充服务接收时间
     */
    private Date operTime;
    /**
     * 标准品属性值集合
     */
    private List<NormProdAttrValRequest> attrValList;

    public List<NormProdAttrValRequest> getAttrValList() {
        return attrValList;
    }

    public void setAttrValList(List<NormProdAttrValRequest> attrValList) {
        this.attrValList = attrValList;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }
}
