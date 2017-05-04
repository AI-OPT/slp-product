package com.ai.slp.product.service.atom.interfaces.product;

import java.util.List;

import com.ai.opt.base.vo.PageInfo;
import com.ai.slp.product.api.product.param.ProductEditQueryReq;
import com.ai.slp.product.api.product.param.ProductQueryInfo;
import com.ai.slp.product.api.product.param.ProductRouteGroupInfo;
import com.ai.slp.product.api.product.param.ProductStorageSaleParam;
import com.ai.slp.product.dao.mapper.bo.product.ProdAttr;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.vo.ProdRouteGroupQueryVo;

/**
 * 销售商品原子操作
 * Created by jackieliu on 16/5/5.
 */
public interface IProductAtomSV {

    /**
     * 添加销售商品信息
     *
     * @param product
     * @return
     */
    public int installProduct(Product product);

    /**
     * 查询指定库存组下的销售商品
     *
     * @param tenantId
     * @param groupId
     * @return
     */
    public Product selectByGroupId(String tenantId,String groupId);

    /**
     * 查询指定商品
     *
     * @param prodId
     * @return
     */
    public Product selectByProductId(String prodId);
    
    /**
     * 查询指定商品
     *
     * @param tenantId
     * @param prodId
     * @return
     */
    public Product selectByProductId(String tenantId,String prodId);

    /**
     * 查询指定商品
     *
     * @param tenantId
     * @param supplierId
     * @param prodId
     * @return
     */
    public Product selectByProductId(String tenantId,String supplierId,String prodId);

    /**
     * 根据标识更新商品信息
     *
     * @param product
     * @return
     */
    public int updateById(Product product);
    
    /**
     * 根据标准品id更新商品信息
     *
     * @param product
     * @return
     */
    public int updateByStandedProdId(Product product);

    /**
     * 待编辑商品分页查询
     *
     * @param productPageQueryVo
     * @return
     * @author lipeng16
     */
    public PageInfo<Product> selectPageForEdit(ProductEditQueryReq productPageQueryVo);
    
    /**
     * 通过库存组标识查询商品
     *
     * @param tenantId
     * @param groupId
     * @return
     * @author lipeng16
     */
    public Product queryProductByGroupId(String tenantId,String groupId);
    
    /**
     * 仓库中商品分页查询
     * 
     * @param productStorageSaleParam
     * @return
     */
    public PageInfo<Product> selectStorProdByState(ProductStorageSaleParam productStorageSaleParam);
    
    /**
     * 待编辑商品分页查询
     *
     * @param queryReq
     * @return
     * @author jiawen
     */
    public PageInfo<Product> selectPageForInsale(ProductQueryInfo queryReq);

    /**
	 * 商品审核分页查询
	 *
	 * @param queryReq
	 * @return
	 * @author jiawen
	 */
    public PageInfo<Product> selectPageForAudit(ProductQueryInfo queryReq);

    /**
     * 分页查询商品信息,包括路由组标识
     * @param queryVo
     * @return
     */
    public PageInfo<ProductRouteGroupInfo> selectPageForRouteGroup(ProdRouteGroupQueryVo queryVo);
    
    /**
     * 查询符合条件的商品---按操作时间倒序
     *
     * @param productPageQueryVo
     * @return
     * @author jiawen
     */
    public PageInfo<Product> selectPageForRefuse(ProductEditQueryReq productPageQueryVo);

    /**
     * 查询除指定商品ID以外的商品是否已包含指定商品编码
     * @param tenantId
     * @param prodId
     * @param prodCode
     * @param hasDiscard
     * @return
     */
    public int countOfProdCodeOutProdId(String tenantId,String prodId,String prodCode,boolean hasDiscard);
    
    /**
     * 查询指定标准品下某个属性的属性值
     *
     * @param tenantId
     * @param standedId
     * @param attrId
     * @return
     */
    public List<ProdAttr> queryAttrVal(String tenantId,String prodId,Long attrId);
    
    
	/**
	 * 修改商品状态
	 * @param state
	 * @param prodId
	 * @return
	 * @author
	 */
	public int updateProdState(String state, String prodId);
	
	/**
	 * 修改商品状态
	 * @param productId
	 * @param state
	 * @param operId
	 * @author Gavin
	 * @UCUSER
	 */
	public int updateProdStatus(String productId,String state, String operId);
	
	/**
	 * 更新商品信息 --编辑商品
	 * @param record
	 * @return
	 * @author Gavin
	 * @UCUSER
	 */
	public int updateProdInfo(Product record);

	/**
	 * 更新商品信息服务
	 * @param prodId
	 * @param prodName
	 * @param productType
	 * @return
	 * @author
	 */
	int updateProdInfo(String prodId, String prodName, String productType);

}
