package com.ai.slp.product.api.product.param;

/**
 * Created by xin on 16-5-20.
 */
public class SaleAreaInfoNew {
	/**
	 * 省份编码
	 */
    private String provcode;

    public SaleAreaInfoNew() {
        super();
    }

    public SaleAreaInfoNew(String provcode) {
        this.provcode = provcode;
    }

    public String getProvcode() {
        return provcode;
    }

    public void setProvcode(String provcode) {
        this.provcode = provcode;
    }
}
