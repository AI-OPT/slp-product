package com.ai.slp.product.api.webfront.param;

import java.io.Serializable;

public class ProductImage implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 上传后的图片ID
     */
    private String vfsid;

    /**
     * 图片扩展名
     */
    private String imagetype;

    public String getVfsid() {
        return vfsid;
    }

    public void setVfsid(String vfsid) {
        this.vfsid = vfsid;
    }

    public String getImagetype() {
        return imagetype;
    }

    public void setImagetype(String imagetype) {
        this.imagetype = imagetype;
    }

   
   

}
