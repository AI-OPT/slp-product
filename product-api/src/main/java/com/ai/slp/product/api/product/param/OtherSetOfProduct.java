package com.ai.slp.product.api.product.param;

import com.ai.opt.base.vo.BaseResponse;

import java.util.List;
import java.util.Map;

/**
 * 单个商品其他设置信息集合<br>
 *
 * Date: 2016年4月26日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class OtherSetOfProduct extends BaseResponse {
    private static final long serialVersionUID = 1L;

	/**
     * 个人受众信息
     */
    private ProdAudiencesInfo personAudiences;

    /**
     * 企业用户受众集合<br>
     * K:用户标识; V:受众信息
     */
    private Map<String,ProdAudiencesInfo> enterpriseMap;

    /**
     * 代理商受众集合
     * K:用户标识; V:受众信息
     */
    private Map<String,ProdAudiencesInfo> agentsMap;

    /**
     * 商品目标地域
     */
    private List<ProdTargetAreaInfo> areaInfos;
    /**
     * 商品主图图片
     */
    private List<ProdPicInfo> productPics;
    /**
     * 可上传图片属性值信息
     */
    private List<ProdAttrValInfo> attrValInfoList;
    /**
     * 属性值对应的图片信息
     * K:属性值标识,V:图片信息
     */
    private Map<String,List<ProdPicInfo>> attrValPics;

    public ProdAudiencesInfo getPersonAudiences() {
        return personAudiences;
    }

    public void setPersonAudiences(ProdAudiencesInfo personAudiences) {
        this.personAudiences = personAudiences;
    }

    public Map<String, ProdAudiencesInfo> getEnterpriseMap() {
        return enterpriseMap;
    }

    public void setEnterpriseMap(Map<String, ProdAudiencesInfo> enterpriseMap) {
        this.enterpriseMap = enterpriseMap;
    }

    public Map<String, ProdAudiencesInfo> getAgentsMap() {
        return agentsMap;
    }

    public void setAgentsMap(Map<String, ProdAudiencesInfo> agentsMap) {
        this.agentsMap = agentsMap;
    }

    public List<ProdTargetAreaInfo> getAreaInfos() {
        return areaInfos;
    }

    public void setAreaInfos(List<ProdTargetAreaInfo> areaInfos) {
        this.areaInfos = areaInfos;
    }

    public List<ProdPicInfo> getProductPics() {
        return productPics;
    }

    public void setProductPics(List<ProdPicInfo> productPics) {
        this.productPics = productPics;
    }

    public List<ProdAttrValInfo> getAttrValInfoList() {
        return attrValInfoList;
    }

    public void setAttrValInfoList(List<ProdAttrValInfo> attrValInfoList) {
        this.attrValInfoList = attrValInfoList;
    }

    public Map<String, List<ProdPicInfo>> getAttrValPics() {
        return attrValPics;
    }

    public void setAttrValPics(Map<String, List<ProdPicInfo>> attrValPics) {
        this.attrValPics = attrValPics;
    }
}
