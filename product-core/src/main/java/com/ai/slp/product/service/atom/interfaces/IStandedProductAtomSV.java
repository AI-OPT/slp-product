package com.ai.slp.product.service.atom.interfaces;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.vo.StandedProdPageQueryVo;

import java.util.List;

/**
 * 对标准品的原子操作
 *
 * Created by liutong5 on 16/4/27.
 */
public interface IStandedProductAtomSV {
    /**
     * 添加标准品信息
     *
     * @param standedProduct
     * @return
     */
    public int installObj(StandedProduct standedProduct);

    /**
     * 更新标准品信息
     *
     * @param standedProduct
     * @return
     */
    public int updateObj(StandedProduct standedProduct);
    /**
     * 更新标准品信息--更新部分
     *
     * @param standedProduct
     * @return
     */
    public int updateStandedProductInfo(StandedProduct standedProduct);

    /**
     * 查询租户下的某个标准品
     *
     * @param tenantId
     * @param standedProdId
     * @return
     */
    public StandedProduct selectById(String tenantId,String standedProdId);

    /**
     * 根据条件进行分页查寻
     * @return
     */
    public PageInfo<StandedProduct> queryForPage(StandedProdPageQueryVo request);

    /**
     * 查询类目下标准品数量
     *
     * @param tenantID
     * @param catId
     * @param hasDiscard  是否包括无效状态
     * @return
     */
    public int queryByCatId(String tenantID,String catId,boolean hasDiscard);
    
    /**
     * 更新类目下标准品的市场价
     * 
     * @param tenantId
     * @param productId
     * @param marketPrice
     * @param operId
     * @return
     * @author lipeng16
     */
    public int updateMarketPrice(String tenantId,String productId,long marketPrice,Long operId);
    
    /**
     *	通过标准品名称查ID集合(模糊查询)
     *
     * @param standedProductName
     * @return
     * @author lipeng16
     */
    public List<String> queryIdByName(String standedProductName);

    /**
     * 保存标准品
     * @param standedProduct
     * @param productId
     * @return
     * @author
     */
	int installObj(StandedProduct standedProduct, String productId);
}
