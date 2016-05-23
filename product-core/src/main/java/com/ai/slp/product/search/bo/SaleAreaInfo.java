package com.ai.slp.product.search.bo;

import com.google.gson.annotations.Expose;

/**
 * Created by xin on 16-5-20.
 */
public class SaleAreaInfo {
    @Expose
    private String provcode;

    public SaleAreaInfo(String provcode) {
        this.provcode = provcode;
    }

    public String getProvcode() {
        return provcode;
    }

    public void setProvcode(String provcode) {
        this.provcode = provcode;
    }
}
