package com.ai.slp.product.vo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 标准品分页查询条件对象  
 *
 * Created by liutong5 on 16/4/28.
 */
public class StandedProdPageQueryVo {
    /**
     * 请求查询的页码<br>
     *  默认为1
     */
    private Integer pageNo;

    /**
     * 每页显示条数<br>
     * 默认为20
     */
    private Integer pageSize;
    /**
     * 租户Id，必填
     */
    private String tenantId;
    /**
     * 类目ID
     */
    private String productCatId;

    /**
     * 标准品ID
     */
    private String productId;

    /**
     * 标准品名称
     */
    private String productName;

    /**
     * 标准品状态
     * NULL:全部;1可上架;2不可上架;3待处理;4废弃
     */
    private String state;

    /**
     * 标准品类型
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
    /**
     * (新增字段)
     * 商户ID--(-1:自运营)
     */
    private String supplierId;
    

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

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    
}
