package com.ai.slp.product.api.webfront.param;

public class ProductImage {
    /**
     * 上传后的图片ID
     */
    private String idpsId;

    /**
     * 图片扩展名
     */
    private String extension;

    /**
     * 图片尺寸
     */
    private String size;

    public String getIdpsId() {
        return idpsId;
    }

    public void setIdpsId(String idpsId) {
        this.idpsId = idpsId;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
