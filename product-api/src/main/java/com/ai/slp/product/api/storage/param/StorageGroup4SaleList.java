package com.ai.slp.product.api.storage.param;

/**
 * 虚拟库存组列表信息<br>
 * 用于显示商城商品销售价列表,包括非废弃的库存组个数
 *
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public class StorageGroup4SaleList extends StorageGroup4List {
    /**
     * 状态不为废弃的库存组的数量
     */
    private int groupNum;

    public int getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }
}
