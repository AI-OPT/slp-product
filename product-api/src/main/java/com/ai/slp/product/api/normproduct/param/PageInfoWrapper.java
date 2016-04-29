package com.ai.slp.product.api.normproduct.param;

import com.ai.opt.base.vo.BaseResponse;

import java.util.List;

/**
 * 分页包装类
 *
 * Date: 2016年4月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author liutong5
 */
public class PageInfoWrapper<T> extends BaseResponse {
    /**
     * 请求查询的页码
     */
    private Integer pageNo = 1;

    /**
     * 每页显示条数
     */
    private Integer pageSize;

    /**
     * 结果集
     */
    private List<T> result;

    /**
     * 总条数
     */
    private int count = 0;

    /**
     * 总页数
     */
    private int pageCount;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    /**
     * 获取开始行
     *
     * @return
     * @author gucl
     */
    public int getStartRowIndex() {
        return (this.getPageNo() - 1) * this.getPageSize();
    }

    /**
     * 获取结束行
     *
     * @return
     * @author gucl
     */
    public int getEndRowIndex() {
        return this.getPageNo() * this.getPageSize();
    }

    /**
     * 获取最大页数
     *
     * @return
     * @author gucl
     */
    public int getPageCount() {
        int quotient = this.getCount() / this.getPageSize();
        int remainder = this.getCount() % this.getPageSize();
        pageCount = quotient;
        if (remainder > 0) {
            pageCount += 1;
        }
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
