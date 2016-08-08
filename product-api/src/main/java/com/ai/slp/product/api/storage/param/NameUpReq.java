package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 更新名称请求信息<br>
 *
 * Date: 2016年5月10日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class NameUpReq extends BaseInfo {
    private static final long serialVersionUID = 1L;
    /**
     * 销售商（商户）标识，必填<br>
     */
    @NotBlank(message = "销售商（商户）标识不能为空")
    private String supplierId;
	/**
     * 库存组/库存标识
     */
    @NotBlank(message = "标识不能为空")
    private String id;
    /**
     * 库存组名称,必填
     * 设置为null,则不进行更新操作
     */
    @NotBlank(message = "名称不能为空")
    private String name;
    /**
     * 操作者ID<br>
     * 不能为空
     */
    @NotNull(message = "操作者不能为空")
    private Long operId;

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getId() {
        return id;
    }

    public void setId(String storageGroupId) {
        this.id = storageGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }
}
