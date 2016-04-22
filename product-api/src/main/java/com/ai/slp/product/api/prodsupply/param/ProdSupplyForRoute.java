package com.ai.slp.product.api.prodsupply.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.BaseResponse;

import java.util.Date;

/**
 * 根据路由查询的供应商品信息<br>
 *
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class ProdSupplyForRoute extends BaseInfo{
    /**
     * 供应商品标识
     */
    private String supplyId;
    /**
     * 供应商品名称
     */
    private String supplyName;
    /**
     * 商品类目标识
     */
    private String productCatId;
    /**
     * 商品类目名称
     */
    private String productCatName;
    /**
     * 成本价
     */
    private Long costPrice;
    /**
     * 商品可用量
     */
    private Long usableNum;
    /**
     * 操作时间
     */
    private Date operTime;

    public String getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(String supplyId) {
        this.supplyId = supplyId;
    }

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

    public String getProductCatName() {
        return productCatName;
    }

    public void setProductCatName(String productCatName) {
        this.productCatName = productCatName;
    }

    public Long getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Long costPrice) {
        this.costPrice = costPrice;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public Long getUsableNum() {
        return usableNum;
    }

    public void setUsableNum(Long usableNum) {
        this.usableNum = usableNum;
    }
}
