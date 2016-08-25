package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseInfo;
import org.hibernate.validator.constraints.NotBlank;

import java.sql.Timestamp;

/**
 * 标准品列表查询参数<br>
 *
 * Date: 2016年4月18日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class NormProdRequest extends BaseInfo {

    private static final long serialVersionUID = 1L;

	/**
     * 请求查询的页码<br>
     *  默认为1
     */
    private Integer pageNo = 1;

    /**
     * 每页显示条数<br>
     * 默认为20
     */
    private Integer pageSize = 20;

    /**
     * 销售商(商户)标识,必填<br>
     * -1:自营平台;其余为销售商(商户)标识
     */
    @NotBlank(message = "销售商(商户)标识不能为空")
    private String supplierId;

    /**
     * 类目ID,查询废弃时,非必填,其他情况必填.
     */
    private String productCatId;

    /**
     * 标准品ID
     */
    private String standedProdId;

    /**
     * 标准品名称
     */
    private String standedProductName;

    /**
     * 标准品状态<br>
     * NULL:全部;1可使用;2不可使用;0废弃
     */
    private String state;

    /**
     * 标准品类型<br>
     * NULL:全部;1实物;2虚拟
     */
    private String productType;
    /**
     * 操作人账户标识
     */
    private Long operId;

    /**
     * 创建时间范围的开始时间
     */
    private Timestamp createStartTime;

    /**
     * 创建时间范围的截止时间
     */
    private Timestamp createEndTime;

    /**
     * 操作/废弃时间范围的开始时间
     */
    private Timestamp operStartTime;
    /**
     * 操作/废弃时间范围的截止时间
     */
    private Timestamp operEndTime;
    
    
    public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public Timestamp getCreateStartTime() {
        return createStartTime;
    }

    public void setCreateStartTime(Timestamp createStartTime) {
        this.createStartTime = createStartTime;
    }

    public Timestamp getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(Timestamp createEndTime) {
        this.createEndTime = createEndTime;
    }

    public Timestamp getOperStartTime() {
        return operStartTime;
    }

    public void setOperStartTime(Timestamp operStartTime) {
        this.operStartTime = operStartTime;
    }

    public Timestamp getOperEndTime() {
        return operEndTime;
    }

    public void setOperEndTime(Timestamp operEndTime) {
        this.operEndTime = operEndTime;
    }

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

	public String getStandedProdId() {
		return standedProdId;
	}

	public void setStandedProdId(String standedProdId) {
		this.standedProdId = standedProdId;
	}

	public String getStandedProductName() {
		return standedProductName;
	}

	public void setStandedProductName(String standedProductName) {
		this.standedProductName = standedProductName;
	}
}
