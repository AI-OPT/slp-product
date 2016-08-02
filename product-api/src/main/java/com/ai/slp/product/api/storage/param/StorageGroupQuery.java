package com.ai.slp.product.api.storage.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.storage.interfaces.IStorageSV;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 库存组信息查询条件<br>
 * 包括下属库存集合
 *
 * Date: 2016年4月21日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StorageGroupQuery extends BaseInfo {
    private static final long serialVersionUID = 1L;
    /**
     * 销售商（商户）标识，必填<br>
     */
    @NotBlank(message = "销售商（商户）标识不能为空")
    private String supplierId;
	/**
     * 库存组标识
     * 在根据库存组查询时,必填.其他情况忽略
     */
	@NotBlank(message = "库存组标识不能为空",
            groups = { IStorageSV.QueryGroupInfoById.class })
    private String groupId;
    /**
     * 标准品标识
     * 在根据标准品标识查询时,必填.其他情况忽略
     */
	@NotBlank(message = "标准品标识不能为空",
            groups = { IStorageSV.QueryGroupInfoByProductId.class })
    private String productId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
}
