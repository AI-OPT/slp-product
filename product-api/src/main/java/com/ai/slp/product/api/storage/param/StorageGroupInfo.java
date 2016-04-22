package com.ai.slp.product.api.storage.param;

import java.util.List;
import java.util.Map;

/**
 * 单个虚拟库存组信息<br>
 * 包括下属库存集合
 *
 * Date: 2016年4月20日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StorageGroupInfo extends STOStorageGroup {
    /**
     * 标准品名称
     */
    private String prodName;
    /**
     * 组内库存量
     */
    private long storageTotal;
    /**
     * 操作人名称
     */
    private String operName;
    /**
     * 创建人名称
     */
    private String createName;
    /**
     * 优先级顺序库存集合
     */
    private Map<Integer,List<STOStorage>> storageList;

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public long getStorageTotal() {
        return storageTotal;
    }

    public void setStorageTotal(long storageTotal) {
        this.storageTotal = storageTotal;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Map<Integer, List<STOStorage>> getStorageList() {
        return storageList;
    }

    public void setStorageList(Map<Integer, List<STOStorage>> storageList) {
        this.storageList = storageList;
    }
}
