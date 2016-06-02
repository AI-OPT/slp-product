package com.ai.slp.product.vo;

/**
 * SKU的库存和价格信息
 * Created by jackieliu on 16/6/1.
 */
public class SkuStorageVo {
    /**
     * 单品标识
     */
    private String skuId;
    /**
     * 库存可用量
     */
    private Long usableNum;
    /**
     * 销售价,单位:厘
     */
    private Long salePrice;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public Long getUsableNum() {
        return usableNum;
    }

    public void setUsableNum(Long usableNum) {
        this.usableNum = usableNum;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    @Override
    public String toString() {
        return "SkuStorageVo{" +
                "skuId='" + skuId + '\'' +
                ", usableNum=" + usableNum +
                ", salePrice=" + salePrice +
                '}';
    }
}
