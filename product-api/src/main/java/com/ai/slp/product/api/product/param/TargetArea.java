package com.ai.slp.product.api.product.param;

import java.io.Serializable;

/**
 * 查询地域的出参
 * 商品目标地域<br>
 *
 * @author jiawen
 */
public class TargetArea implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 租户标识
     */
    private String tenantId;
    /**
     * 商品标识
     */
    private String prodId;
    /**
     * 商品目标地域标识
     */
    private Long targetAreaId;
    /**
     * 省份编码
     */
    private String provinceCode;
    /**
     *市编码 
     */
    private Integer cityCode;
    /**
     * 县编码
     */
    private Integer countyCode;
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public Long getTargetAreaId() {
		return targetAreaId;
	}
	public void setTargetAreaId(Long targetAreaId) {
		this.targetAreaId = targetAreaId;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public Integer getCityCode() {
		return cityCode;
	}
	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}
	public Integer getCountyCode() {
		return countyCode;
	}
	public void setCountyCode(Integer countyCode) {
		this.countyCode = countyCode;
	}
}
