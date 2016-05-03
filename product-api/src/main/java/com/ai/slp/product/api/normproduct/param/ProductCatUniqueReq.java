package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.normproduct.interfaces.IProductCatSV;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 类目唯一标识请求信息<br>
 *
 * Date: 2016年05月01日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class ProductCatUniqueReq extends BaseInfo {
    /**
     * 类目ID
     *
     */
    @NotNull(message = "类目标识不能为空",groups = {
            IProductCatSV.QueryByCatId.class,
            IProductCatSV.DeleteProductCat.class,
            IProductCatSV.QueryLinkOfCatById.class
    })
    private String productCatId;

    /**
     * 操作人id
     */
    @NotNull(message = "操作人ID不能为空",groups = {
            IProductCatSV.DeleteProductCat.class
    })
    private Long operId;

    /**
     * 执行操作时间
     */
    private Timestamp operTime;

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

    public Timestamp getOperTime() {
        return operTime;
    }

    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
    }
}
