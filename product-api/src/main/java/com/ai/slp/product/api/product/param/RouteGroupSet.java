package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseInfo;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by jackieliu on 16/9/2.
 */
public class RouteGroupSet extends BaseInfo{
    private static final long serialVersionUID = 1L;
    /**
     * 商户标识,必填
     * -1:自营
     */
    private String supplierId;
    /**
     * 商品标识,必填
     */
    @NotBlank(message = "商品标识不能为空")
    private String prodId;

    /**
     * 路由组/配货组标识,必填
     */
    @NotBlank(message = "路由组/配货组标识不能为空")
    private String routeGroupId;

    /**
     * 操作人标识
     */
    private Long operId;

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getRouteGroupId() {
        return routeGroupId;
    }

    public void setRouteGroupId(String routeGroupId) {
        this.routeGroupId = routeGroupId;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }
}
