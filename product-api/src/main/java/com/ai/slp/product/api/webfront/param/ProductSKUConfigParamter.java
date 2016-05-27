package com.ai.slp.product.api.webfront.param;

import java.io.Serializable;

public class ProductSKUConfigParamter implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 配置类型
	 */
	private String configType;
	
	/**
	 * 配置名称
	 */
	private String configName;
	
	/**
	 * 配置值
	 */
	private String configValue;

	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

}
