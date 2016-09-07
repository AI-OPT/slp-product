package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.product.interfaces.IProductManagerSV;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 商品审核参数
 * 
 * Date: 2016年4月26日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author lipeng16
 */
public class ProductCheckParam extends BaseInfo {
    
    private static final long serialVersionUID = 1L;
    /**
     * 状态,必填<br>
     * 0:审核未通过; 1:审核通过
     */
    @NotBlank(message = "状态不能为空",groups = {IProductManagerSV.ProductCheck.class})
    private String state;

    /**
     * 拒绝原因-被拒绝参数,拒绝状态必填
     */
    private String refuseReason;
    
    /**
     * 拒绝描述-被拒绝参数,拒绝状态必填
     */
    private String refuseDes;

    /**
     * 操作人,必填
     */
    @NotNull(message = "操作人标识不能为空",groups = {IProductManagerSV.ProductCheck.class})
    private Long operId;

    /**
     * 商品ID集合
     */
    @NotEmpty(message = "商品标识不能为空",groups = {IProductManagerSV.ProductCheck.class})
    private List<String> prodIdList;

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getRefuseDes() {
        return refuseDes;
    }

    public void setRefuseDes(String refuseDes) {
        this.refuseDes = refuseDes;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public List<String> getProdIdList() {
        return prodIdList;
    }

    public void setProdIdList(List<String> prodIdList) {
        this.prodIdList = prodIdList;
    }
}
