package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.normproduct.interfaces.INormProductSV;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 标准品属性值请求信息<br>
 *
 * Date: 2016年4月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class NormProductAttrValRequest extends BaseInfo {
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
    private Long attrValId;
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
     * 操作人ID<br>
     * 不能为空
     */
    @NotNull(message = "操作ID不能为空", groups = { INormProductSV.SaveProductInfo.class })
    private Long operId;
    /**
     * 操作时间<br>
     * 为空时,则使用服务端接收时间
     */
    private Date operTime;
}
