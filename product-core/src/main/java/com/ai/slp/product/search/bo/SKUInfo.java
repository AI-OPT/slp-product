package com.ai.slp.product.search.bo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class SKUInfo {
	/**
	 * 租户
	 */
    @Expose
    private String tenantid;
    /**
	 * sku标识
	 */
    @Expose
    private String skuid;
    /**
	 * sku名称
	 */
    @Expose
    private String skuname;
    
    //叶子
    @Expose
    private String productcategoryid;
    /**
	 * 标识
	 */
    @Expose
    private String rootcategorid;
    /**
	 * 商品标识
	 */
    @Expose
    private String productid;
    /**
	 * 商品名称
	 */
    @Expose
    private String productname;
    /**
	 * 卖点
	 */
    @Expose
    private String productsellpoint = "";
    // 关键属性
    @Expose
    private List<AttrInfo> attrinfos;
    /**
	 * 可销售数
	 */
    @Expose
    private long salenum;
    /**
	 * 类目
	 */
    @Expose
    private List<CategoryInfo> categoryinfos;
    /**
	 * 图片
	 */
    @Expose
    private ImageInfo imageinfo;
    /**
	 * 价格
	 */
    @Expose
    private long price;
    // 改成三级权限
    @Expose
    private List<ProdAudiencesSes> audiences;

    //销售地域
    @Expose
    private List<SaleAreaInfo> saleareainfos;
    //
    @Expose
    private String basicorgid;
    // 充值方式
    @Expose
    private String rechagetype;
    // 是否是全国销售
    @Expose
    private String salenationwide = "";
    //图片信息
    @Expose
    private List<ImageInfo> thumbnail;
    /**
	 * 上架时间
	 */
    @Expose
    private long uptime;

    /**
   	 * 销售价格
   	 */
   @Expose
   private long marketPrice;


    public SKUInfo() {
        super();
    }

    public SKUInfo(String tenantid, String skuid, String skuname) {
        this.tenantid = tenantid;
        this.skuid = skuid;
        this.skuname = skuname;
        this.categoryinfos = new ArrayList<CategoryInfo>();
        this.audiences = new ArrayList<ProdAudiencesSes>();
        this.attrinfos = new ArrayList<AttrInfo>();
        this.saleareainfos = new ArrayList<SaleAreaInfo>();
        this.thumbnail = new ArrayList<ImageInfo>();
    }
    
    public long getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(long marketPrice) {
		this.marketPrice = marketPrice;
	}

	public void setProductid(String productid) {
        this.productid = productid;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public void setProductsellpoint(String productsellpoint) {
        this.productsellpoint = productsellpoint;
    }


    public String getProductid() {
        return productid;
    }

    public void setProductcategoryid(String productcategoryid) {
        this.productcategoryid = productcategoryid;
    }

    public String getProductcategoryid() {
        return productcategoryid;
    }

    public void addCategoryInfo(CategoryInfo categoryInfo) {
        this.categoryinfos.add(categoryInfo);
    }

    public void setSalenum(long salenum) {
        this.salenum = salenum;
    }


    public String getSkuid() {
        return skuid;
    }

    public void setImageinfo(ImageInfo imageinfo) {
        this.imageinfo = imageinfo;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void addProductAudiences(ProdAudiencesSes prodAudiences) {
        audiences.add(prodAudiences);
    }

    public String getBasicorgid() {
        return basicorgid;
    }

    public void setBasicorgid(String basicorgid) {
        this.basicorgid = basicorgid;
    }

    public void setRechagetype(String rechagetype) {
        this.rechagetype = rechagetype;
    }

    public void setSalenationwide(String salenationwide) {
        if (salenationwide == null || salenationwide.length() == 0){
            this.salenationwide = "N";
            return;
        }
        this.salenationwide = salenationwide;
    }

    public String getSalenationwide() {
        return salenationwide;
    }

    public List<SaleAreaInfo> getSaleareainfos() {
        return saleareainfos;
    }

    public String getRootcategorid() {
        return rootcategorid;
    }

    public void setRootcategorid(String rootcategorid) {
        this.rootcategorid = rootcategorid;
    }

    public void addThumbnail(ImageInfo imageInfo) {
        this.thumbnail.add(imageInfo);
    }

    public void setUptime(long uptime) {
        this.uptime = uptime;
    }

    public String getTenantid() {
        return tenantid;
    }

    public void setTenantid(String tenantid) {
        this.tenantid = tenantid;
    }

    public String getSkuname() {
        return skuname;
    }

    public void setSkuname(String skuname) {
        this.skuname = skuname;
    }

    public List<CategoryInfo> getCategoryinfos() {
        return categoryinfos;
    }

    public void setCategoryinfos(List<CategoryInfo> categoryinfos) {
        this.categoryinfos = categoryinfos;
    }

    public List<ProdAudiencesSes> getAudiences() {
        return audiences;
    }

    public void setAudiences(List<ProdAudiencesSes> audiences) {
        this.audiences = audiences;
    }

    public List<ImageInfo> getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(List<ImageInfo> thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getProductname() {
        return productname;
    }

    public String getProductsellpoint() {
        return productsellpoint;
    }

    public List<AttrInfo> getAttrinfos() {
		return attrinfos;
	}

	public void setAttrinfos(List<AttrInfo> attrinfos) {
		this.attrinfos = attrinfos;
	}

	public long getSalenum() {
        return salenum;
    }

    public ImageInfo getImageinfo() {
        return imageinfo;
    }

    public String getRechagetype() {
        return rechagetype;
    }

    public long getUptime() {
        return uptime;
    }

    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }

    public void setSaleareainfos(List<SaleAreaInfo> saleareainfos) {
        this.saleareainfos = saleareainfos;
    }
    
}
