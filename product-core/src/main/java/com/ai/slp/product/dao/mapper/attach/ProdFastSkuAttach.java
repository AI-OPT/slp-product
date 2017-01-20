package com.ai.slp.product.dao.mapper.attach;

/**
 * Created by jackieliu on 16/6/2.
 */
public class ProdFastSkuAttach {
	/**
	 *sku标识 
	 */
    private String skuId;
    /**
	 * 商品标识
	 */
    private String prodId;
    /**
	 * 标准品标识
	 */
    private String standedProdId;
    /**
	 * 库存组标识
	 */
    private String storageGroupId;
    /**
	 * 属性标识
	 */
    private Long attrId;
    /**
	 * 属性值标识
	 */
    private String attrvalueDefId;
    /**
	 * 属性值
	 */
    private String attrValueName;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getStandedProdId() {
        return standedProdId;
    }

    public void setStandedProdId(String standedProdId) {
        this.standedProdId = standedProdId;
    }

    public String getStorageGroupId() {
        return storageGroupId;
    }

    public void setStorageGroupId(String storageGroupId) {
        this.storageGroupId = storageGroupId;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public String getAttrvalueDefId() {
        return attrvalueDefId;
    }

    public void setAttrvalueDefId(String attrvalueDefId) {
        this.attrvalueDefId = attrvalueDefId;
    }

    public String getAttrValueName() {
        return attrValueName;
    }

    public void setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName;
    }

    @Override
    public String toString() {
        return "ProdFastSkuAttach{" +
                "skuId='" + skuId + '\'' +
                ", prodId='" + prodId + '\'' +
                ", standedProdId='" + standedProdId + '\'' +
                ", storageGroupId='" + storageGroupId + '\'' +
                ", attrId=" + attrId +
                ", attrvalueDefId='" + attrvalueDefId + '\'' +
                ", attrValueName='" + attrValueName + '\'' +
                '}';
    }
}
