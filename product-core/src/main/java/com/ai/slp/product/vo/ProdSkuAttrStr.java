package com.ai.slp.product.vo;

import com.ai.slp.product.constants.ProductConstants;

/**
 * SKU的属性串分解类型
 * Created by jackieliu on 16/9/18.
 */
public class ProdSkuAttrStr {

	
	/**
	 * 属性标识
	 */
    private String attrId;
    /**
	 * 屬性值標識
	 */

    private String attrValId;

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }

    public String getAttrValId() {
        return attrValId;
    }

    public void setAttrValId(String attrValId) {
        this.attrValId = attrValId;
    }

    @Override
    public String toString() {
        return attrId+ ProductConstants.ProdSku.SaleAttrs.ATTRVAL_SPLIT+attrValId;
    }
}
