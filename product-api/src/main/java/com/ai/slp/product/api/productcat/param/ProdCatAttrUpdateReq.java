package com.ai.slp.product.api.productcat.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.productcat.interfaces.IProductCatSV;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 类目属性更新参数
 *
 * Date: 2016年8月18日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author liutong5
 */
public class ProdCatAttrUpdateReq extends BaseInfo {
    private static final long serialVersionUID = 1L;
    /**
     * 操作人ID
     */
    @NotNull(message = "操作人标识不能为空",groups = {IProductCatSV.UpdateCatAttrAndVal.class})
    private Long operId;

    private List<ProdCatAttrUpdateParam> updateParamList;

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public List<ProdCatAttrUpdateParam> getUpdateParamList() {
        return updateParamList;
    }

    public void setUpdateParamList(List<ProdCatAttrUpdateParam> updateParamList) {
        this.updateParamList = updateParamList;
    }
}
