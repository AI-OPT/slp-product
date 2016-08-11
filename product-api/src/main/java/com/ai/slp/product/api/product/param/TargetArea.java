package com.ai.slp.product.api.product.param;

import java.io.Serializable;

/**
 * 地域信息
 * Date: 2016年8月9日 <br>
 * @author jiawen 
 *
 */

public class TargetArea implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 商品目标地域ID
	 */
	private Long targetAreaId;
	/**
	 * 租户ID
	 */
    private String tenantId;
    /**
     * 商品标识
     */
    private String prodId;
    /**
     * 目标省
     */
    private Integer provCode;
    /**
     * 目标市
     */
    private Integer cityCode;
    /**
     * 目标县
     */
    private Integer countyCode;
	public Long getTargetAreaId() {
		return targetAreaId;
	}
	public void setTargetAreaId(Long targetAreaId) {
		this.targetAreaId = targetAreaId;
	}
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
	public Integer getProvCode() {
		return provCode;
	}
	public void setProvCode(Integer provCode) {
		this.provCode = provCode;
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
