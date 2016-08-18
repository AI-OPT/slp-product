package com.ai.slp.product.api.productcat.param;

import com.ai.slp.product.api.productcat.interfaces.IProductCatSV;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 类目属性更新参数
 * 
 * Date: 2016年5月4日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author liutong5
 */
public class ProdCatAttrUpdateParam implements Serializable{

    private static final long serialVersionUID = 1L;
	/**
     * 要更新信息的唯一标识<br>
     * 若更新信息为属性,则为关系ID,若更新信息为属性值,则为商品类目属性值ID
     */
    @NotBlank(message = "唯一标识不能为空",groups = {IProductCatSV.UpdateCatAttrAndVal.class})
    private String updateObjId;
    /**
     * 更新数据的类型,必填<br>
     *     1:属性;2:属性值
     */
    @NotBlank(message = "更新数据的类型不能为空",groups = {IProductCatSV.UpdateCatAttrAndVal.class})
    private String objType;

    /**
     * 是否上传图片,销售属性类型为必填,默认为否
     */
    private String isPicture;

    /**
     * 序列号
     */
    @NotNull(message = "序列号不能为空",groups = {IProductCatSV.UpdateCatAttrAndVal.class})
    private Short serialNumber;

    public String getUpdateObjId() {
        return updateObjId;
    }

    public void setUpdateObjId(String updateObjId) {
        this.updateObjId = updateObjId;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getIsPicture() {
        return isPicture;
    }

    public void setIsPicture(String isPicture) {
        this.isPicture = isPicture;
    }

    public Short getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Short serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return ",updateObjId:"+updateObjId+",objType:"+objType+"\r\n"
                +",isPicture:"+isPicture+",serialNumber:"+serialNumber;
    }
}
