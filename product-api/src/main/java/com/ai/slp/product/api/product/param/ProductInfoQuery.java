package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.product.interfaces.IProductManagerSV;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 商城商品标识信息<br>
 *
 *
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class ProductInfoQuery extends BaseInfo {
    private static final long serialVersionUID = 1L;
    /**
     * 销售商（商户）标识，必填<br>
     */
    @NotBlank(message = "销售商（商户）标识不能为空")
    private String supplierId;

	/**
     * 商品标识,必填
     */
    @NotBlank(message = "商品标识不能为空")
    private String productId;

    /**
     * 操作人标识<br>
     * 进行商品操作时必填,其他操作非必填
     */
    @NotNull(message = "操作人不能为空",groups = {IProductManagerSV.ChangeToInSale.class})
    private Long operId;

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }
}
