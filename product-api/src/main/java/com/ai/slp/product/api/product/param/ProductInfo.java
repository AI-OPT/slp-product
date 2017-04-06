package com.ai.slp.product.api.product.param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 商城商品详情中对象<br>
 *
 *
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class ProductInfo extends BaseResponse {
    private static final long serialVersionUID = 1L;
	/**
     * 商品标识
     */
    private String prodId;
    /**
     * 商品类目标识
     */
    private String productCatId;
    /**
     * 标准品标识
     */
    private String standedProdId;
    /**
     * 库存组标识
     */
    private String storageGroupId;
    /**
     * 商品类型
     */
    private String productType;
    /**
     * 商品名称
     */
    private String prodName;
    /**
     * 商品卖点
     */
    private String productSellPoint;
    /**
     * 有效期类型
     */
    private String activeType;
    /**
     * 生效时间
     */
    private Timestamp activeTime;
    /**
     * 失效时间
     */
    private Timestamp inactiveTime;
    /**
     * 有效周期
     */
    private Short activeCycle;
    /**
     * 周期
     */
    private String unit;
    /**
     * 商品详情
     */
    private String proDetailContent;
    /**
     * 是否全国范围销售
     */
    private String isSaleNationwide;
    /**
     * 是否允许平台代销
     */
    private String isReplaceSell;
    /**
     * 上架类型
     */
    private String upshelfType;
    /**
     * 上架时间,若上架类型为定时上架时,才有效
     */
    private Timestamp upTime;
    /**
     * 下架时间
     */
    private Timestamp downTime;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 商品状态
     */
    private String state;
    /**
     * 操作人
     */
    private Long operId;
    /**
     * 操作时间
     */
    private Timestamp operTime;
    /**
     * 充值类型 D:直冲;C:卡
     */
    private String rechargeType;
    /**
     * 运营商<br>10:中国移动;11:中国电信;12:中国联通
     */
    private String basicOrgId;
    /**
     * 非关键属性与属性值对应关系
     */
    private Map<Long,Set<Long>> attrAndValueIds;
    /**
     *是否可开发票
     */
    private String isInvoice;
    /**
     * 预售开始时间
     */
    private Timestamp presaleBeginTime;
    /**
     * 预售结束时间
     */
    private Timestamp presaleEndTime;
    /**
     * 商品销售地域
     * @return
     * @author Gavin
     * @UCUSER
     */
    private List<SaleAreaInfoNew> saleAreaInfos;
    
    

	public List<SaleAreaInfoNew> getSaleAreaInfos() {
		return saleAreaInfos;
	}

	public void setSaleAreaInfos(List<SaleAreaInfoNew> saleAreaInfos) {
		this.saleAreaInfos = saleAreaInfos;
	}

	public String getIsInvoice() {
		return isInvoice;
	}

	public void setIsInvoice(String isInvoice) {
		this.isInvoice = isInvoice;
	}

	public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProductSellPoint() {
        return productSellPoint;
    }

    public void setProductSellPoint(String productSellPoint) {
        this.productSellPoint = productSellPoint;
    }

    public String getActiveType() {
        return activeType;
    }

    public void setActiveType(String activeType) {
        this.activeType = activeType;
    }

    public Timestamp getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Timestamp activeTime) {
        this.activeTime = activeTime;
    }

    public Timestamp getInactiveTime() {
        return inactiveTime;
    }

    public void setInactiveTime(Timestamp inactiveTime) {
        this.inactiveTime = inactiveTime;
    }

    public Short getActiveCycle() {
        return activeCycle;
    }

    public void setActiveCycle(Short activeCycle) {
        this.activeCycle = activeCycle;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProDetailContent() {
        return proDetailContent;
    }

    public void setProDetailContent(String proDetailContent) {
        this.proDetailContent = proDetailContent;
    }

    public String getIsSaleNationwide() {
        return isSaleNationwide;
    }

    public void setIsSaleNationwide(String isSaleNationwide) {
        this.isSaleNationwide = isSaleNationwide;
    }

    public String getIsReplaceSell() {
        return isReplaceSell;
    }

    public void setIsReplaceSell(String isReplaceSell) {
        this.isReplaceSell = isReplaceSell;
    }

    public String getUpshelfType() {
        return upshelfType;
    }

    public void setUpshelfType(String upshelfType) {
        this.upshelfType = upshelfType;
    }

    public Timestamp getUpTime() {
        return upTime;
    }

    public void setUpTime(Timestamp upTime) {
        this.upTime = upTime;
    }

    public Timestamp getDownTime() {
        return downTime;
    }

    public void setDownTime(Timestamp downTime) {
        this.downTime = downTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public Timestamp getOperTime() {
        return operTime;
    }

    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
    }

    public String getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(String rechargeType) {
        this.rechargeType = rechargeType;
    }

    public String getBasicOrgId() {
        return basicOrgId;
    }

    public void setBasicOrgId(String basicOrgId) {
        this.basicOrgId = basicOrgId;
    }

    public Map<Long, Set<Long>> getAttrAndValueIds() {
        return attrAndValueIds;
    }

    public void setAttrAndValueIds(Map<Long, Set<Long>> attrAndValueIds) {
        this.attrAndValueIds = attrAndValueIds;
    }

    public Timestamp getPresaleBeginTime() {
        return presaleBeginTime;
    }

    public void setPresaleBeginTime(Timestamp presaleBeginTime) {
        this.presaleBeginTime = presaleBeginTime;
    }

    public Timestamp getPresaleEndTime() {
        return presaleEndTime;
    }

    public void setPresaleEndTime(Timestamp presaleEndTime) {
        this.presaleEndTime = presaleEndTime;
    }
}
