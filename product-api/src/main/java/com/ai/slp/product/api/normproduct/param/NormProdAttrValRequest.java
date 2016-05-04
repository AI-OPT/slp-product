package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.normproduct.interfaces.INormProductSV;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 标准品属性值请求信息<br>
 *
 * Date: 2016年4月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class NormProdAttrValRequest extends BaseInfo {
    
    /**
     * 标准品属性值ID
     */
    private Long productAttrValId;
    
    /**
     * 属性ID<br>
     * 不能为空
     */
    @NotNull(message = "属性ID不能为空", groups = { INormProductSV.SaveProductInfo.class })
    private Long attrId;
    
    /**
     * 属性值id<br>
     * 与属性值两者不能全部为空
     */
    private String attrValId;
    
    /**
     * 属性值<br>
     * 与属性值ID两者不能全部为空
     */
    private String attrVal;
    
    /**
     * 属性值2,用于范围型属性值
     */
    private String attrVal2;
    /**
     * 序列号
     */
    private Short serialNumber;
    
    /**
     * 操作人ID<br>
     * 不能为空
     */
    @NotNull(message = "操作ID不能为空", groups = { INormProductSV.SaveProductInfo.class })
    private Long operId;
    
    /**
     * 操作时间<br>
     * 为空时,则使用服务端接收时间
     */
    private Timestamp operTime;

    public Long getProductAttrValId() {
        return productAttrValId;
    }

    public void setProductAttrValId(Long productAttrValId) {
        this.productAttrValId = productAttrValId;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public String getAttrValId() {
        return attrValId;
    }

    public void setAttrValId(String attrValId) {
        this.attrValId = attrValId;
    }

    public String getAttrVal() {
        return attrVal;
    }

    public void setAttrVal(String attrVal) {
        this.attrVal = attrVal;
    }

    public String getAttrVal2() {
        return attrVal2;
    }

    public void setAttrVal2(String attrVal2) {
        this.attrVal2 = attrVal2;
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

    public Short getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Short serialNumber) {
        this.serialNumber = serialNumber;
    }
}
