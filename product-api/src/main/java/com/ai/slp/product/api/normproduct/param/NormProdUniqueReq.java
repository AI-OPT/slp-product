package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.normproduct.interfaces.INormProductSV;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 标准品请求信息<br>
 *
 * Date: 2016年4月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class NormProdUniqueReq extends BaseInfo {
    private static final long serialVersionUID = 1L;
	/**
     * 标准品ID,必填
     *
     */
    @NotBlank(message = "标准品ID不能为空",
            groups = { INormProductSV.DiscardProduct.class,INormProductSV.QueryProducById.class })
    private String productId;
    /**
     * 操作人,执行废弃操作时,必填
     *
     */
    @NotNull(message = "操作人ID不能为空",
            groups = { INormProductSV.DiscardProduct.class })
    private Long operId;
    /**
     * (新增字段)
     * 商户ID--(-1:自运营)
     */
    @NotBlank(message = "商户ID不能为空")
    private String supplierId;
   
    
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
