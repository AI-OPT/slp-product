package com.ai.slp.product.api.productcat.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.productcat.interfaces.IProductCatCacheSV;
import com.ai.slp.product.api.productcat.interfaces.IProductCatSV;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 类目唯一标识请求信息<br>
 *
 * Date: 2016年05月01日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class ProductCatUniqueReq extends BaseInfo {
    private static final long serialVersionUID = 1L;

	/**
     * 类目ID,必填
     *
     */
    @NotBlank(message = "类目标识不能为空",groups = {
            IProductCatSV.QueryByCatId.class,
            IProductCatSV.DeleteProductCat.class,
            IProductCatSV.QueryLinkOfCatById.class,
            IProductCatCacheSV.QueryByCatId.class,
            IProductCatCacheSV.QueryChildOfCatById.class,
            IProductCatCacheSV.QueryLinkOfCatById.class
    })
    private String productCatId;

    /**
     * 操作人id,删除时必填
     */
    @NotNull(message = "操作人ID不能为空",groups = {
            IProductCatSV.DeleteProductCat.class
    })
    private Long operId;

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

}
