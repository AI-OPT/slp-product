package com.ai.slp.product.api.prodsupply.interfaces;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.prodsupply.param.ProdSupplyForNorm;
import com.ai.slp.product.api.prodsupply.param.ProdSupplyForRoute;
import com.ai.slp.product.api.prodsupply.param.ProdSupplyQuery;
import com.ai.slp.product.api.prodsupply.param.ProdSupplySaveCostPrice;

import java.util.List;

/**
 * 供应商品处理接口<br>
 *
 * Date: 2016年4月22日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public interface IProdSupplySV {

    /**
     * 查询指定标准品下的供货商品. <br>
     *
     * @param query 查询条件
     * @return 标准品下供货商品的集合
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode PROD_SUPPLY_0100
     */
    public PageInfo<ProdSupplyForNorm> queryProdSupplyOfNorm(ProdSupplyQuery query)
        throws BusinessException,SystemException;
    @interface QueryProdSupplyOfNorm{}

    /**
     * 查询某个路由下的供货商品. <br>
     *
     * @param query 查询条件
     * @return 路由下符合条件的供货商品的集合
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode PROD_SUPPLY_0101
     */
    public PageInfo<ProdSupplyForRoute> queryProdSupplyOfRoute(ProdSupplyQuery query)
            throws BusinessException,SystemException;
    @interface QueryProdSupplyOfRoute{}

    /**
     * 批量更新提供商品成本价. <br>
     *
     * @param saveCostPriceList 查询条件
     * @return 操作结果
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiDocMethod
     * @ApiCode PROD_SUPPLY_0102
     */
    public BaseResponse updateMultCostPrice(List<ProdSupplySaveCostPrice> saveCostPriceList)
            throws BusinessException,SystemException;
    @interface UpdateMultCostPrice{}
}
