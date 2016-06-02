package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.product.interfaces.IProductSV;
import com.ai.slp.product.api.product.interfaces.IProductServerSV;
import org.hibernate.validator.constraints.NotBlank;

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
     * 商品标识,必填
     */
    @NotBlank(message = "商品标识不能为空",groups = {
            IProductSV.QueryProductById.class,
            IProductServerSV.QueryRouteGroupOfProd.class,
            IProductSV.QueryNoKeyAttrInfo.class})
    private String productId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
