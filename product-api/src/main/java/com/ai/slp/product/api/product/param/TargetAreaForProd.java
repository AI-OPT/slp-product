package com.ai.slp.product.api.product.param;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.product.interfaces.IProductManagerSV;

/**
 * 查询地域信息的商品的出参
 * 商品目标地域<br>
 * Date: 2016年8月9日 <br>
 * @author jiawen
 */
public class TargetAreaForProd implements Serializable {
    private static final long serialVersionUID = 1L;

	/**
     * 商品ID
     */
    private String prodId;
    
    /**
     * 商品类目ID
     */
    private String productCatId;
    
    /**
     * 类目名称
     */
    private String productCatName;
    
    /**
     * 商品类型
     */
    private String productType;
    /**
     * 商品类型名称，页面展示类型名称
     */
    private String productTypeName;
    
    /**
     * 商品图ID
     */
    private Long proPictureId;

    /**
     * 图片文件模块ID
     */
    private String vfsId;

    /**
     * 图片类型
     */
    private String picType;
    /**
     * 图片地址,为前端预留字段
     */
    private String picUrl;
    
    /**
     * 商品名称
     */
    private String prodName;
    
    /**
     * 总库存
     */
    private long totalNum;

    /**
     * 状态
     * 0:新增
     * 1:未编辑;2:已编辑
     * 3:审核中;4:审核未通过
     * 5:在售
     * 6:仓库中（审核通过放入、手动下架放入） 61:售罄下架 62:废弃下架
     * 7:废弃
     */
    private String state;
    /**
     * 状态名称，页面展示状态名称
     */
    private String stateName;
    /**
     * 商户标识,必填
     * -1:自营
     *  0:全部
     */
    private String supplierId;
    /**
     * 商品目标地域
     */
    private List<TargetArea> targetArea;
	
    
    public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProductCatId() {
		return productCatId;
	}
	public void setProductCatId(String productCatId) {
		this.productCatId = productCatId;
	}
	public String getProductCatName() {
		return productCatName;
	}
	public void setProductCatName(String productCatName) {
		this.productCatName = productCatName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public Long getProPictureId() {
		return proPictureId;
	}
	public void setProPictureId(Long proPictureId) {
		this.proPictureId = proPictureId;
	}
	public String getVfsId() {
		return vfsId;
	}
	public void setVfsId(String vfsId) {
		this.vfsId = vfsId;
	}
	public String getPicType() {
		return picType;
	}
	public void setPicType(String picType) {
		this.picType = picType;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public long getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public List<TargetArea> getTargetArea() {
		return targetArea;
	}
	public void setTargetArea(List<TargetArea> targetArea) {
		this.targetArea = targetArea;
	}
	
}
