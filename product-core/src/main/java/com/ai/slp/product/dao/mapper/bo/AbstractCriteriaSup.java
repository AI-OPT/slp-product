package com.ai.slp.product.dao.mapper.bo;

/**
 * 用户设置Criteria的一些共同内容
 * Created by jackieliu on 16/3/31.
 */
public abstract class AbstractCriteriaSup {
    /**
     * 用于补充表名中变化的年月
     */
    protected String tableMonth;

    public String getTableMonth() {
        return tableMonth;
    }

    public void setTableMonth(String tableMonth) {
        this.tableMonth = tableMonth;
    }
}
