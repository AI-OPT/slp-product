package com.ai.slp.product.search.bo;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class SKUInfo {
    @Expose
    private String tenantid;
    @Expose
    private String skuid;
    @Expose
    private String skuname;
    //叶子
    @Expose
    private String productcategoryid;
    @Expose
    private String rootcategorid;
    @Expose
    private String productid;
    @Expose
    private String productname;
    @Expose
    private String productsellpoint = "";
    // 关键属性
    @Expose
    private List<AttrInfo> attrInfos;
    @Expose
    private int salenum;
    @Expose
    private List<CategoryInfo> categoryinfos;
    @Expose
    private ImageInfo imageinfo;
    @Expose
    private float price;
    // 改成三级权限
    @Expose
    private List<ProdAudiences> audiences;

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
    //
    @Expose
    private List<ImageInfo> thumbnail;

    @Expose
    private long uptime;


    public SKUInfo(String tenantid, String skuid, String skuname) {
        this.tenantid = tenantid;
        this.skuid = skuid;
        this.skuname = skuname;
        this.categoryinfos = new ArrayList<CategoryInfo>();
        this.audiences = new ArrayList<ProdAudiences>();
        this.attrInfos = new ArrayList<AttrInfo>();
        this.saleareainfos = new ArrayList<SaleAreaInfo>();
        this.thumbnail = new ArrayList<ImageInfo>();
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

    public void setAttrInfos(List<AttrInfo> attrInfos) {
        this.attrInfos = attrInfos;
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

    public void setSalenum(int salenum) {
        this.salenum = salenum;
    }


    public String getSkuid() {
        return skuid;
    }

    public void setImageinfo(ImageInfo imageinfo) {
        this.imageinfo = imageinfo;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void addProductAudiences(ProdAudiences prodAudiences) {
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
}
