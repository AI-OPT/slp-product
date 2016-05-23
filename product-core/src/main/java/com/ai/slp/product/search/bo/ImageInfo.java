package com.ai.slp.product.search.bo;

import com.google.gson.annotations.Expose;

/**
 * Created by xin on 16-5-17.
 */
public class ImageInfo {
    @Expose
    private String imagetype;
    @Expose
    private String vfsid;

    public ImageInfo(String imagetype, String vfsid) {
        this.imagetype = imagetype;
        this.vfsid = vfsid;
    }
}
