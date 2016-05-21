package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.product.interfaces.IProductServerSV;
import org.hibernate.validator.constraints.NotBlank;

/**
 * SKU信息查询请求信息
 * Created by jackieliu on 16/5/20.
 */
public class SkuInfoQuery extends BaseInfo {
    private static final long serialVersionUID = 1L;
	/**
     * SKU单品标识
     */
    @NotBlank(message = "SKU单品标识不能为空",groups = {IProductServerSV.QueryProducSkutById.class})
    private String skuId;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
}
