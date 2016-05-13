package com.ai.slp.product.api.product.param;

import java.io.Serializable;
import java.util.Set;

/**
 * 单个商品各受众类型的信息集合<br>
 *
 * Date: 2016年4月26日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class AudiencesSetOfProduct implements Serializable {
    private static final long serialVersionUID = 1L;

	/**
     * 个人受众信息
     */
    private ProdAudiences personAudiences;

    /**
     * 企业用户受众集合
     */
    private Set<ProdAudiences> enterpriseSet;
    /**
     * 代理商受众集合
     */
    private Set<ProdAudiences> agentsSet;

    public ProdAudiences getPersonAudiences() {
        return personAudiences;
    }

    public void setPersonAudiences(ProdAudiences personAudiences) {
        this.personAudiences = personAudiences;
    }

    public Set<ProdAudiences> getEnterpriseSet() {
        return enterpriseSet;
    }

    public void setEnterpriseSet(Set<ProdAudiences> enterpriseSet) {
        this.enterpriseSet = enterpriseSet;
    }

    public Set<ProdAudiences> getAgentsSet() {
        return agentsSet;
    }

    public void setAgentsSet(Set<ProdAudiences> agentsSet) {
        this.agentsSet = agentsSet;
    }
}
