package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 虚拟库存组信息<br>
 *
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class STOStorageGroup extends BaseInfo {

    private static final long serialVersionUID = 1L;
    /**
     * 销售商（商户）标识，必填<br>
     * -1:自营平台;其他为销售商标识
     */
    @NotBlank(message = "销售商（商户）标识不能为空",
            groups = { IStorageSV.InstallStorage.class})
    private String supplierId;
	/**
     * 标准品标识<br>
     * 设置为null,则不进行更新操作
     */
    @NotBlank(message = "标准品标识不能为空",
            groups = { IStorageSV.InstallStorage.class})
    private String standedProdId;
    /**
     * 库存组名称,添加时必填<br>
     * 设置为null,则不进行更新操作
     */
    @NotBlank(message = "库存组名称不能为空",
            groups = { IStorageSV.InstallStorage.class })
    private String storageGroupName;
    /**
     * 序列号
     */
    private Short serialNumber;
    /**
     * 创建者ID<br>
     * 更新时,直接忽略
     */
    @NotNull(message = "创建者ID不能为空",
            groups = { IStorageSV.InstallStorage.class })
    private Long createId;

    public String getStandedProdId() {
        return standedProdId;
    }

    public void setStandedProdId(String standedProdId) {
        this.standedProdId = standedProdId;
    }

    public String getStorageGroupName() {
        return storageGroupName;
    }

    public void setStorageGroupName(String storageGroupName) {
        this.storageGroupName = storageGroupName;
    }

    public Short getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Short serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
    
    

}
