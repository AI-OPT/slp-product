package com.ai.slp.product.search.bo;

import com.google.gson.annotations.Expose;

/**
 * Created by xin on 16-5-17.
 */
public class ProdAudiences {
    @Expose
    private String audiencecode;
    @Expose
    private String userid;

    public ProdAudiences() {
        super();
    }


    public ProdAudiences(String audiencecode,String userid) {
        this.audiencecode = audiencecode;
        this.userid = userid;
    }


    public String getAudiencecode() {
        return audiencecode;
    }


    public void setAudiencecode(String audiencecode) {
        this.audiencecode = audiencecode;
    }


    public String getUserid() {
        return userid;
    }


    public void setUserid(String userid) {
        this.userid = userid;
    }
    
}
