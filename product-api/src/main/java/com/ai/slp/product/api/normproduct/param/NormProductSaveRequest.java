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
public class NormProductSaveRequest extends NormProductBase {

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
    private List<NormProductAttrValRequest> attrValList;

    public List<NormProductAttrValRequest> getAttrValList() {
        return attrValList;
    }

    public void setAttrValList(List<NormProductAttrValRequest> attrValList) {
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
}
