package com.ai.slp.product.api.product.param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.product.interfaces.IProductManagerSV;

/**
 * 商城商品更新信息<br>
 *
 * Date: 2016年6月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class ProductInfoForUpdate extends BaseInfo {
    /**
     * 销售商（商户）标识，必填<br>
     */
    @NotBlank(message = "销售商（商户）标识不能为空",groups = {IProductManagerSV.SaveProduct.class})
    private String supplierId;
	/**
	 * 商品标识，必填
	 */
	@NotBlank(message = "商品标识不能为空",groups = {IProductManagerSV.SaveProduct.class})
	private String prodId;
	/**
	 * 商品名称，必填
	 */
	@NotBlank(message = "商品名称不能为空",groups = {IProductManagerSV.SaveProduct.class})
    private String prodName;
	/**
	 * 商品卖点
	 */
    private String productSellPoint;
    /**
     * 有效期类型，必填
     */
    @NotBlank(message = "有效期类型不能为空",groups = {IProductManagerSV.SaveProduct.class})
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
     * 单位
     */
    private String unit;
    /**
     * 商品详情，必填
     */
    @NotBlank(message = "商品详情不能为空",groups = {IProductManagerSV.SaveProduct.class})
    private String proDetailContent;
    /**
     * 目标地域是否全国，必填<br>
     * Y:是；N：否
     */
    @NotBlank(message = "是否全国范围销售不能为空",groups = {IProductManagerSV.SaveProduct.class})
    private String isSaleNationwide;
    /**
     * 是否允许平台代销，必填<br>
     * Y:是；N:否
     */
    private String isReplaceSell;
    /**
     * 上架类型，必填<br>
     * 1:审核通过后立即上架；
     * 2:审核通过后放入仓库;
     * 3:定时上架
     * 4:预售上架
     */
    @NotBlank(message = "上架类型不能为空",groups = {IProductManagerSV.SaveProduct.class})
    private String upshelfType;
    /**
     * 上架时间，对应定时上架
     */
    private Timestamp upTime;
    /**
     * 操作人ID
     */
    @NotNull(message = "操作人ID不能为空",groups = {IProductManagerSV.SaveProduct.class})
    private Long operId;
    /**
     * 是否开发票
     */
    private String isInvoice;
    /**
     * 充值类型，必填
     */
    private String rechargeType;
    /**
     * 运营商，必填
     * 
     */
    private String basicOrgId;
    /**
     * 个人受众是否可见，必填<br>
     * 0:全部不可见；-1:全部可见
     */
    @NotNull(message = "个人受众不能为空",groups = {IProductManagerSV.SaveProduct.class})
    private String audiencesPerson;
    /**
     * 企业受众，必填<br>
     * 0:全部不可见；-1:全部可见;1:部分可见
     */
    @NotBlank(message = "企业受众不能为空",groups = {IProductManagerSV.SaveProduct.class})
    private String audiencesEnterprise;
    /**
     * 代理商受众，必填<br>
     * 0:全部不可见；-1:全部可见;1:部分可见
     */
    @NotBlank(message = "代理商受众不能为空",groups = {IProductManagerSV.SaveProduct.class})
    private String audiencesAgents;
    /**
     * 商品主图图片集合
     */
    @NotEmpty(message = "商品主图图片不能为空",groups = {IProductManagerSV.SaveProduct.class})
    private List<ProdPicInfo> prodPics;

    /**
     * 商品属性值图片集合
     */
    private Map<String,List<ProdPicInfo>> attrValPics;
    /**
     * 企业受众集合,企业用户ID标识
     */
    private List<String> enterpriseIds;
    /**
     * 代理受众集合,代理商ID标识
     */
    private List<String> agentIds;
    /**
     * 目标地域的省份编码集合
     */
    private List<Long> provCodes;
    /**
     * 销售商品非关键属性
     */
    private Map<Long,List<ProdAttrValInfo>> noKeyAttrValMap;
    /**
     *
     * 预售开始时间,上架类型为预售时,必填
     */
    private Timestamp presaleBeginTime;
    /**
     *
     * 预售结束时间,上架类型为预售时,必填
     */
    private Timestamp presaleEndTime;

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
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

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
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

    public String getAudiencesPerson() {
        return audiencesPerson;
    }

    public void setAudiencesPerson(String audiencesPerson) {
        this.audiencesPerson = audiencesPerson;
    }

    public String getAudiencesEnterprise() {
        return audiencesEnterprise;
    }

    public void setAudiencesEnterprise(String audiencesEnterprise) {
        this.audiencesEnterprise = audiencesEnterprise;
    }

    public String getAudiencesAgents() {
        return audiencesAgents;
    }

    public void setAudiencesAgents(String audiencesAgents) {
        this.audiencesAgents = audiencesAgents;
    }

    public List<ProdPicInfo> getProdPics() {
        return prodPics;
    }

    public void setProdPics(List<ProdPicInfo> prodPics) {
        this.prodPics = prodPics;
    }

    public Map<String, List<ProdPicInfo>> getAttrValPics() {
        return attrValPics;
    }

    public void setAttrValPics(Map<String, List<ProdPicInfo>> attrValPics) {
        this.attrValPics = attrValPics;
    }

    public List<String> getEnterpriseIds() {
        return enterpriseIds;
    }

    public void setEnterpriseIds(List<String> enterpriseIds) {
        this.enterpriseIds = enterpriseIds;
    }

    public List<String> getAgentIds() {
        return agentIds;
    }

    public void setAgentIds(List<String> agentIds) {
        this.agentIds = agentIds;
    }

    public List<Long> getProvCodes() {
        return provCodes;
    }

    public void setProvCodes(List<Long> provCodes) {
        this.provCodes = provCodes;
    }

    public Map<Long, List<ProdAttrValInfo>> getNoKeyAttrValMap() {
        return noKeyAttrValMap;
    }

    public void setNoKeyAttrValMap(Map<Long, List<ProdAttrValInfo>> noKeyAttrValMap) {
        this.noKeyAttrValMap = noKeyAttrValMap;
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

    public String getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(String isInvoice) {
        this.isInvoice = isInvoice;
    }
}
