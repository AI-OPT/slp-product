package com.ai.slp.product.api.storageserver.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.storageserver.interfaces.IStorageNumSV;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * 使用库存量请求信息 <br>
 *
 * Date: 2016年6月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StorageNumUseReq extends BaseInfo {
    private static final long serialVersionUID = 1L;

    /**
     * 单品SKU标识
     */
    @NotBlank(message = "单品标识不能为空",groups = {
            IStorageNumSV.UseStorageNum.class})
    private String skuId;
    /**
     *单品数量
     */
    @Min(value = 1,message = "单品数量不能小于1",groups = {
            IStorageNumSV.UseStorageNum.class})
    private int skuNum;
    /**
     * 用户类型
     */
    @NotBlank(message = "用户类型不能为空",groups = {
            IStorageNumSV.UseStorageNum.class})
    private String userType;
    /**
     * 用户标识
     */
    @NotBlank(message = "用户标识不能为空",groups = {
            IStorageNumSV.UseStorageNum.class})
    private String userId;

    /**
     * 销售价,单位:厘<br>
     * 用于校验和当前价格是否一致,为空时,则不进行校验
     */
    private Long salePrice;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public int getSkuNum() {
        return skuNum;
    }

    public void setSkuNum(int skuNum) {
        this.skuNum = skuNum;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }
}
