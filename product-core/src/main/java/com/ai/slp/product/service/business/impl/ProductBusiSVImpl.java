package com.ai.slp.product.service.business.impl;

import java.util.ArrayList;
import java.util.List;

import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.api.product.param.ProductRoute;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.bo.product.ProdSku;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.business.interfaces.IProdSkuBusiSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.api.product.param.Product4List;
import com.ai.slp.product.api.product.param.ProductListQuery;
import com.ai.slp.product.constants.ProductCatConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.attach.ProdCatAttrAttch;
import com.ai.slp.product.dao.mapper.attach.ProductAttach;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.product.ProductLog;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAttachAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProdAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAttachAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.vo.ProductPageQueryVo;

/**
 * Created by jackieliu on 16/5/5.
 */
@Service
@Transactional
public class ProductBusiSVImpl implements IProductBusiSV {
    private static Logger logger = LoggerFactory.getLogger(ProductBusiSVImpl.class);
    @Autowired
    IProdSkuBusiSV prodSkuBusiSV;
    @Autowired
    IStandedProductAtomSV standedProductAtomSV;
    @Autowired
    IStandedProdAttrAtomSV standedProdAttrAtomSV;
    @Autowired
    IProductAtomSV productAtomSV;
    @Autowired
    IProductLogAtomSV productLogAtomSV;
    @Autowired
    IProdCatAttrAttachAtomSV catAttrAttachAtomSV;
    @Autowired
    IStorageGroupAtomSV storageGroupAtomSV;
    @Autowired
    IStorageAtomSV storageAtomSV;
    @Autowired
    IProdSkuAtomSV prodSkuAtomSV;
    @Autowired
    IProdSkuAttrAtomSV prodSkuAttrAtomSV;
    @Autowired
    IProdCatAttrAtomSV prodCatAttrAtomSV;
    @Autowired
    IProductAttachAtomSV productAttachAtomSV;

    /**
     * 添加商城商品
     *
     * @param group
     * @return
     */
    @Override
    public int addProductWithStorageGroup(StorageGroup group, Long operId) {
        //查询库存组对应的标准品
        String tenantId = group.getTenantId();
        String standedProdId = group.getStandedProdId();
        StandedProduct standedProduct = standedProductAtomSV.selectById(tenantId,standedProdId);
        if (standedProduct==null) {
            logger.warn("未找到对应标准品,租户ID:{},标准品标识:{}",tenantId,standedProdId);
            throw new BusinessException("","未找到对应标准品信息,租户id:"+tenantId+",标准品标识:"+standedProdId);
        }
        //查询类目是否有销售属性
        List<ProdCatAttrAttch> catAttrAttches = catAttrAttachAtomSV.queryAttrOfByIdAndType(
                tenantId,standedProduct.getProductCatId(), ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_SALE);
        boolean isSaleAttr = CollectionUtil.isEmpty(catAttrAttches)?false:true;
        //添加商品,商品名称同标准品名称
        Product product = new Product();
        product.setTenantId(tenantId);
        product.setProductCatId(standedProduct.getProductCatId());
        product.setStandedProdId(standedProdId);
        product.setStorageGroupId(group.getStorageGroupId());
        product.setProductType(standedProduct.getProductType());
        product.setProdName(standedProduct.getStandedProductName());//使用标准品名称设置为商品名称
        product.setIsSaleAttr(isSaleAttr? ProductConstants.Product.IsSaleAttr.YES: ProductConstants.Product.IsSaleAttr.NO);
        product.setState(ProductConstants.Product.State.ADD);//新增状态
        product.setOperId(group.getCreateId());
        int installNum = productAtomSV.installProduct(product);
        if (installNum > 0){
            ProductLog productLog = new ProductLog();
            BeanUtils.copyProperties(productLog,product);
            productLogAtomSV.install(productLog);
            //若没有销售属性,则添加SKU
            if (!isSaleAttr){
                ProdSku prodSku = new ProdSku();
                prodSku.setTenantId(tenantId);
                prodSku.setProdId(product.getProdId());
                prodSku.setStorageGroupId(group.getStorageGroupId());
                prodSku.setSkuName(product.getProdName());
                prodSku.setIsSaleAttr(ProductConstants.ProdSku.IsSaleAttr.NO);
                prodSku.setSerialNumber((short)0);
                prodSku.setState(ProductConstants.ProdSku.State.ACTIVE);
                prodSku.setOperId(operId);
                prodSkuBusiSV.addSku(prodSku);
            }
        }
        return installNum;
    }

    /**
     * 对停用/售罄下架的商品进行上架处理
     *
     * @param tenantId
     * @param prodId
     */
    @Override
    public void changeToSaleForStop(String tenantId, String prodId,Long operId) {
        Product product = productAtomSV.selectByGroupId(tenantId,prodId);
        if (prodId == null){
            throw new BusinessException("","未找到相关的商品信息,租户ID:"+tenantId+",商品标识:"+prodId);
        }
        //若商品状态不是"停用下架",也不是"售罄下架",则不进行处理
        if(!ProductConstants.Product.State.STOP.equals(product.getState())
                && !ProductConstants.Product.State.SALE_OUT.equals(product.getState())){
            return;
        }
        //查询库存组是否为"启用"状态
        StorageGroup storageGroup = storageGroupAtomSV.queryByGroupId(tenantId,product.getStorageGroupId());
        if (storageGroup==null
                || (!StorageConstants.StorageGroup.State.ACTIVE.equals(storageGroup.getState()))
                    && !StorageConstants.StorageGroup.State.AUTO_ACTIVE.equals(storageGroup.getState())){
            throw new BusinessException("","对应库存组不存在,或库存组不是[启用]状态,租户ID:"+tenantId
                    +"库存组ID:"+product.getStorageGroupId());
        }

        //检查商品是否有库存,若没有,则直接切换至"售罄下架"
        List<Storage> storageList = storageAtomSV.queryActive(tenantId,prodId,true);
        //若原状态为"售罄下架",且现在没有库存,则不处理
        if ((storageList==null || storageList.isEmpty())
                && ProductConstants.Product.State.SALE_OUT.equals(product.getState())){
            return;
        }
        //直接切换至"售罄下架"
        if (storageList==null || storageList.isEmpty()){
            product.setState(ProductConstants.Product.State.SALE_OUT);
        }else { //切换至上架
            product.setState(ProductConstants.Product.State.IN_SALE);
        }
        //停用/售罄下架进行上架时,没有操作人
        if (operId!=null)
            product.setOperId(operId);
        //添加日志
        if (productAtomSV.updateById(product)>0){
            ProductLog productLog = new ProductLog();
            BeanUtils.copyProperties(productLog,product);
            productLogAtomSV.install(productLog);
        }
    }


    /**
     * 商品下架,当库存组为停用时,则为停用下架,否则为售罄下架
     *
     * @param tenantId
     * @param prodId
     */
    @Override
    public void offSale(String tenantId, String prodId, Long operId) {
        Product product = productAtomSV.selectByGroupId(tenantId,prodId);
        if (product == null){
            throw new BusinessException("","未找到相关的商品信息,租户ID:"+tenantId+",商品标识:"+prodId);
        }
        //若商品状态不是"在售",则不进行处理
        if(!ProductConstants.Product.State.IN_SALE.equals(product.getState())){
            return;
        }
        StorageGroup storageGroup = storageGroupAtomSV.queryByGroupId(tenantId,product.getStorageGroupId());
        //若库存组为"停用"或"自动停用"则设置为"停用下架"
        if (StorageConstants.StorageGroup.State.AUTO_STOP.equals(storageGroup.getState())
                || StorageConstants.StorageGroup.State.STOP.equals(storageGroup.getState())) {
            product.setState(ProductConstants.Product.State.STOP);
        }
        //否则为"售罄停用"
        else
            product.setState(ProductConstants.Product.State.SALE_OUT);
        //当库存售光时,操作者ID为null
        if (operId!=null)
            product.setOperId(operId);
        //添加日志
        if (productAtomSV.updateById(product)>0){
            ProductLog productLog = new ProductLog();
            BeanUtils.copyProperties(productLog,product);
            productLogAtomSV.install(productLog);
        }
    }

    /**
     * 废弃商品
     *
     * @param tenantId
     * @param prodId
     */
    @Override
    public void discardProduct(String tenantId, String prodId,Long operId) {
        Product product = productAtomSV.selectByGroupId(tenantId,prodId);
        if (prodId == null){
            throw new BusinessException("","未找到相关的商品信息,租户ID:"+tenantId+",商品标识:"+prodId);
        }
        //设置为废弃状态
        product.setState(ProductConstants.Product.State.DISCARD);
        product.setOperId(operId);
        //添加日志
        if (productAtomSV.updateById(product)>0){
            ProductLog productLog = new ProductLog();
            BeanUtils.copyProperties(productLog,product);
            productLogAtomSV.install(productLog);
        }
    }

    /**
     * 分页查询商城商品信息-商城商品销售价之商城商品列表
     * 
     * @param productQuery
     * @return
     * @author lipeng16
     */
	@Override
	public PageInfoResponse<Product4List> queryProductPage(ProductListQuery productQuery) {
		ProductPageQueryVo productPageQueryVo = new ProductPageQueryVo();
		BeanUtils.copyProperties(productPageQueryVo, productQuery);
		//多表联合查询商品信息
		List<ProductAttach> productAttachList = productAttachAtomSV.queryProductPageBySearch(productPageQueryVo);
		//设置结果集
		List<Product4List> product4ListList = new ArrayList<Product4List>();
		for(ProductAttach ProductAttach : productAttachList){
			Product4List product4List = new Product4List();
			BeanUtils.copyProperties(product4List, ProductAttach);
			product4ListList.add(product4List);
		}
		//新建返回类
		PageInfoResponse<Product4List> product4ListPage = new PageInfoResponse<>();
		product4ListPage.setResult(product4ListList);
		product4ListPage.setPageNo(productQuery.getPageNo());
		product4ListPage.setPageSize(productQuery.getPageSize());
		
		return product4ListPage;
	}

    /**
     * 查询销售商品关联的路由组ID
     *
     * @param tenantId
     * @param productId
     * @return
     */
    @Override
    public ProductRoute queryRouteGroupOfProd(String tenantId, String productId) {
        Product product = productAtomSV.selectByProductId(tenantId,productId);
        if (product==null) {
            logger.warn("未查询到对应销售商品,租户ID:{},商品标识:{}",tenantId,productId);
            throw new BusinessException("", "未查询到对应销售商品,租户ID:" + tenantId + ",商品标识:" + productId);
        }
        StorageGroup storageGroup = storageGroupAtomSV.queryByGroupId(tenantId,product.getStorageGroupId());
        if (storageGroup==null){
            logger.warn("未查询销售商品对应库存组,租户ID:{},商品标识:{}",tenantId,productId);
            throw new BusinessException("", "未查询销售商品对应库存组,租户ID:" + tenantId + ",商品标识:" + productId);
        }
        ProductRoute productRoute = new ProductRoute();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode(ExceptCodeConstants.Special.SUCCESS);
        productRoute.setResponseHeader(responseHeader);
        productRoute.setProductId(productId);
        productRoute.setRouteGroupId(storageGroup.getRouteGroupId());
        return productRoute;
    }

}
