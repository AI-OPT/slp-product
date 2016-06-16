package com.ai.slp.product.api.productcat.param;

import com.ai.opt.base.vo.BaseResponse;

import java.util.List;

/**
 * 服务返回list包装类型
 * Created by jackieliu on 16/6/2.
 */
public class ListForRes<T> extends BaseResponse {
    private static final long serialVersionUID = 1L;

    List<T> objList;

    public void setObjList(List<T> objList) {
        this.objList = objList;
    }

    public List<T> getObjList() {
        return this.objList;
    }

    public ListForRes(){}

    public ListForRes(List<T> objList){
        this.objList = objList;
    }

}
