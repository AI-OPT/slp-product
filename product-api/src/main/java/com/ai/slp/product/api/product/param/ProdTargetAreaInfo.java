package com.ai.slp.product.api.product.param;

import java.io.Serializable;

/**
 * 商品目标地域<br>
 *
 * Date: 2016年6月14日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class ProdTargetAreaInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 区域编码
     */
    private String areaCode;
    /**
     * 区域名称
     */
    private String areaName;
    /**
     * 省份编码
     */
    private String provinceCode;
    /**
     * 商品是否已含有
     */
    private boolean isOwn;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public boolean isOwn() {
        return isOwn;
    }

    public void setOwn(boolean own) {
        isOwn = own;
    }
}
