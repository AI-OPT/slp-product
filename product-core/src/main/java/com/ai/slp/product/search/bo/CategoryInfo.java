package com.ai.slp.product.search.bo;

import com.google.gson.annotations.Expose;

/**
 * Created by xin on 16-5-13.
 */
public class CategoryInfo {
    @Expose
    private String categorid;
    private String categoryname;


    public CategoryInfo(String categorid, String categoryname) {
        this.categorid = categorid;
        this.categoryname = categoryname;
    }
}
