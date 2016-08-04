package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.product.interfaces.IProductSV;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 商城商品的SKU批量更新<br>
 *
 *
 * Date: 2016年5月10日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class SkuInfoMultSave extends BaseInfo{
    private static final long serialVersionUID = 1L;

    /**
     * 销售商（商户）标识，必填<br>
     */
    @NotBlank(message = "销售商（商户）标识不能为空",
            groups = { IProductSV.SaveMultSKUInfo.class})
    private String supplierId;

	/**
     * 商品标识,必填
     */
    @NotBlank(message = "商品标识不能为空",
            groups = { IProductSV.SaveMultSKUInfo.class })
    private String prodId;

    /**
     * 保存的SKU的属性和属性值
     */
    private Map<Long,List<String>> attrAndValIdMap;

    /**
     * 操作人ID,必填
     */
    @NotNull(message = "操作人不能为空",
            groups = { IProductSV.SaveMultSKUInfo.class })
    private Long operId;

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public Map<Long, List<String>> getAttrAndValIdMap() {
        return attrAndValIdMap;
    }

    public void setAttrAndValIdMap(Map<Long, List<String>> attrAndValIdMap) {
        this.attrAndValIdMap = attrAndValIdMap;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }
}
