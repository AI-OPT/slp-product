package com.ai.slp.product.search.bo;

import com.google.gson.annotations.Expose;

/**
 * Created by xin on 16-5-17.
 */
public class ProdAudiences {
    @Expose
    private String audiencecode;


    public ProdAudiences() {
        super();
    }


    public ProdAudiences(String audiencecode) {
        this.audiencecode = audiencecode;
    }
}
