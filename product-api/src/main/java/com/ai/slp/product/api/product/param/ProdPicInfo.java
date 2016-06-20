package com.ai.slp.product.api.product.param;

import com.ai.slp.product.api.product.interfaces.IProductManagerSV;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 商品图片<br>
 *
 * Date: 2016年6月14日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class ProdPicInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 商品图ID
     */
    private Long proPictureId;
    /**
     * 商品标识，必填
     */
    @NotBlank(message="商品标识不能为空",groups={IProductManagerSV.SaveProduct.class})
    private String prodId;
    /**
     * 属性值
     */
    private String attrvalueDefId;
    /**
     * 图片用处
     */
    private String picUses;
    /**
     * 文件类型，必填
     */
    @NotBlank(message="文件类型不能为空",groups={IProductManagerSV.SaveProduct.class})
    private String picType;
    /**
     * 文件附件模块ID，必填
     */
    @NotBlank(message="文件附件ID不能为空",groups={IProductManagerSV.SaveProduct.class})
    private String vfsId;
    /**
     * 是否主图，必填
     */
    @NotBlank(message="是否主图不能为空",groups={IProductManagerSV.SaveProduct.class})
    private String isMainPic;
    /**
     * 序列号，必填
     */
    @NotBlank(message="序列号不能为空",groups={IProductManagerSV.SaveProduct.class})
    private Short serialNumber;
    /**
     * 状态
     */
    private String state;
    /**
     * 图片url地址,预留字段
     */
    private String imgUrl;

    public Long getProPictureId() {
        return proPictureId;
    }

    public void setProPictureId(Long proPictureId) {
        this.proPictureId = proPictureId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getAttrvalueDefId() {
        return attrvalueDefId;
    }

    public void setAttrvalueDefId(String attrvalueDefId) {
        this.attrvalueDefId = attrvalueDefId;
    }

    public String getPicUses() {
        return picUses;
    }

    public void setPicUses(String picUses) {
        this.picUses = picUses;
    }

    public String getPicType() {
        return picType;
    }

    public void setPicType(String picType) {
        this.picType = picType;
    }

    public String getVfsId() {
        return vfsId;
    }

    public void setVfsId(String vfsId) {
        this.vfsId = vfsId;
    }

    public String getIsMainPic() {
        return isMainPic;
    }

    public void setIsMainPic(String isMainPic) {
        this.isMainPic = isMainPic;
    }

    public Short getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Short serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
