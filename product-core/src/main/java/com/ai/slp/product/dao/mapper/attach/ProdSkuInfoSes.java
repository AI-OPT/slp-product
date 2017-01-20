package com.ai.slp.product.dao.mapper.attach;

import java.sql.Timestamp;

/**
 * Created by jackieliu on 16/9/22.
 */
public class ProdSkuInfoSes {
	/**
	 * 租户id
	 */
    private String tenantid;
    /**
	 * sku标识
	 */
    private String skuid;
    /**
	 * sku名称
	 */
    private String skuname;
    //类目标识
    private String productcategoryid;
    /**
	 * 商品标识
	 */
    private String productid;
    /**
	 * 商品名称
	 */
    private String productname;
    //卖点
    private String productsellpoint;
    //运营商标识
    private String basicorgid;
    // 充值方式
    private String rechagetype;
    // 是否全部销售
    private String salenationwide;
    //上架时间
    private Timestamp prodUpTime;

    public String getTenantid() {
        return tenantid;
    }

    public void setTenantid(String tenantid) {
        this.tenantid = tenantid;
    }

    public String getSkuid() {
        return skuid;
    }

    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }

    public String getSkuname() {
        return skuname;
    }

    public void setSkuname(String skuname) {
        this.skuname = skuname;
    }

    public String getProductcategoryid() {
        return productcategoryid;
    }

    public void setProductcategoryid(String productcategoryid) {
        this.productcategoryid = productcategoryid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductsellpoint() {
        return productsellpoint;
    }

    public void setProductsellpoint(String productsellpoint) {
        this.productsellpoint = productsellpoint;
    }

    public String getBasicorgid() {
        return basicorgid;
    }

    public void setBasicorgid(String basicorgid) {
        this.basicorgid = basicorgid;
    }

    public String getRechagetype() {
        return rechagetype;
    }

    public void setRechagetype(String rechagetype) {
        this.rechagetype = rechagetype;
    }

    public String getSalenationwide() {
        return salenationwide;
    }

    public void setSalenationwide(String salenationwide) {
        this.salenationwide = salenationwide;
    }

    public Timestamp getProdUpTime() {
        return prodUpTime;
    }

    public void setProdUpTime(Timestamp prodUpTime) {
        this.prodUpTime = prodUpTime;
    }
}
