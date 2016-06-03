package com.ai.slp.product.api.webfront.param;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.slp.product.api.webfront.interfaces.IProductHomeSV;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 快充产品查询请求信息
 * Created by jackieliu on 16/6/2.
 */
public class FastProductReq extends BaseInfo {
    private static final long serialVersionUID = 1L;
    /**
     * 商品类目标识,必填<br>
     * 10000010020000:流量快充<br>
     * 10000010010000:话费快充
     */
    @NotBlank(message = "商品类目不能为空",groups = {IProductHomeSV.QueryFastProduct.class})
    private String productCatId;
    /**
     * 销售地域省份编码,必填
     */
    @NotNull(message = "销售地域编码不能为空",groups = {IProductHomeSV.QueryFastProduct.class})
    private Integer provCode;
    /**
     * 运营商,必填<br>
     * 10：中国移动<br>
     * 11：中国电信<br>
     * 12：中国联通
     */
    @NotNull(message = "运营商不能为空",groups = {IProductHomeSV.QueryFastProduct.class})
    private String basicOrgId;
    /**
     * 用户类型,必填<br>
     * 10：个人<br>
     * 11：企业<br>
     * 12：代理商<br>
     * 13：供应商
     */
    @NotNull(message = "用户类型不能为空",groups = {IProductHomeSV.QueryFastProduct.class})
    private String userType;
    /**
     * 用户ID,可选
     */
    private String userId;

    public String getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(String productCatId) {
        this.productCatId = productCatId;
    }

    public Integer getProvCode() {
        return provCode;
    }

    public void setProvCode(Integer provCode) {
        this.provCode = provCode;
    }

    public String getBasicOrgId() {
        return basicOrgId;
    }

    public void setBasicOrgId(String basicOrgId) {
        this.basicOrgId = basicOrgId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
