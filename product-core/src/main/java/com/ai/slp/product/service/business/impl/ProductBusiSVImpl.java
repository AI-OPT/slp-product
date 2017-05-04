package com.ai.slp.product.service.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.paas.ipaas.search.vo.Result;
import com.ai.paas.ipaas.search.vo.SearchCriteria;
import com.ai.paas.ipaas.search.vo.SearchOption;
import com.ai.platform.common.api.area.interfaces.IGnAreaQuerySV;
import com.ai.platform.common.api.area.param.GnAreaVo;
import com.ai.slp.product.api.normproduct.param.AttrValRequest;
import com.ai.slp.product.api.product.param.CatAttrInfoForProd;
import com.ai.slp.product.api.product.param.ProdAttrMap;
import com.ai.slp.product.api.product.param.ProdAttrValInfo;
import com.ai.slp.product.api.product.param.ProdNoKeyAttr;
import com.ai.slp.product.api.product.param.ProdTargetAreaInfo;
import com.ai.slp.product.api.product.param.Product4List;
import com.ai.slp.product.api.product.param.ProductListQuery;
import com.ai.slp.product.api.product.param.ProductRoute;
import com.ai.slp.product.api.webfront.param.FastProductInfoRes;
import com.ai.slp.product.api.webfront.param.FastProductReq;
import com.ai.slp.product.api.webfront.param.FastSkuProdInfo;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.constants.ProdAttrAndValDefConstants;
import com.ai.slp.product.constants.ProductCatConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.constants.SearchFieldConfConstants;
import com.ai.slp.product.constants.StandedProductConstants;
import com.ai.slp.product.constants.StorageConstants;
import com.ai.slp.product.dao.mapper.attach.CatAttrValAttach;
import com.ai.slp.product.dao.mapper.attach.ProdCatAttrAttch;
import com.ai.slp.product.dao.mapper.attach.ProdFastSkuAttach;
import com.ai.slp.product.dao.mapper.attach.ProductAttach;
import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDef;
import com.ai.slp.product.dao.mapper.bo.StandedProduct;
import com.ai.slp.product.dao.mapper.bo.product.ProdAttr;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.dao.mapper.bo.storage.Storage;
import com.ai.slp.product.dao.mapper.bo.storage.StorageGroup;
import com.ai.slp.product.search.bo.SKUInfo;
import com.ai.slp.product.service.atom.interfaces.IProdAttrValDefAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAttachAtomSV;
import com.ai.slp.product.service.atom.interfaces.IStandedProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdAudiencesAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdSkuLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdTargetAreaAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAttachAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductStateLogAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.ISkuStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageAtomSV;
import com.ai.slp.product.service.atom.interfaces.storage.IStorageGroupAtomSV;
import com.ai.slp.product.service.business.impl.search.ProductSearchImpl;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IProductCatBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageNumBusiSV;
import com.ai.slp.product.service.business.interfaces.search.IProductSearch;
import com.ai.slp.product.service.business.interfaces.search.ISKUIndexBusiSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.product.vo.ProductPageQueryVo;
import com.ai.slp.product.vo.SkuStorageVo;

/**
 * Created by jackieliu on 16/5/5.
 */
@Service
@Transactional
public class ProductBusiSVImpl implements IProductBusiSV {
    private static final Logger logger = LoggerFactory.getLogger(ProductBusiSVImpl.class);
    @Autowired
    IProductCatBusiSV productCatBusiSV;
    @Autowired
    IStandedProductAtomSV standedProductAtomSV;
    @Autowired
    IProductAtomSV productAtomSV;
    @Autowired
    IProductLogAtomSV productLogAtomSV;
    @Autowired
    IProdCatAttrAttachAtomSV catAttrAttachAtomSV;
    @Autowired
    IProdAttrValDefAtomSV attrValDefAtomSV;
    @Autowired
    IStorageGroupAtomSV storageGroupAtomSV;
    @Autowired
    IStorageAtomSV storageAtomSV;
    @Autowired
    IProdSkuAtomSV prodSkuAtomSV;
    @Autowired
    IProdSkuLogAtomSV prodSkuLogAtomSV;
    @Autowired
    IProdAttrAtomSV prodAttrAtomSV;
    @Autowired
    IProdSkuAttrAtomSV prodSkuAttrAtomSV;
    @Autowired
    IProdCatAttrAtomSV prodCatAttrAtomSV;
    @Autowired
    IProductAttachAtomSV productAttachAtomSV;
    @Autowired
    IProdAudiencesAtomSV prodAudiencesAtomSV;
    @Autowired
    IProdTargetAreaAtomSV prodTargetAreaAtomSV;
    @Autowired
    IStorageNumBusiSV storageNumBusiSV;
    @Autowired
    ISkuStorageAtomSV skuStorageAtomSV;
    @Autowired
    ISKUIndexBusiSV skuIndexManage;
    @Autowired
    IProductStateLogAtomSV productStateLogAtomSV;
    
    IProductSearch productSearch = new ProductSearchImpl();
    
    /**
     * 添加商城商品
     *
     * @param group
     * @return
     */
    @Override
    public Product addProductWithStorageGroup(StandedProduct standedProduct, StorageGroup group, List<AttrValRequest> attrValList) {
        //查询库存组对应的标准品
        String tenantId = group.getTenantId();
        String standedProdId = group.getStandedProdId();
        //添加商品,商品名称同标准品名称
        Product product = new Product();
        product.setSupplierId(group.getSupplierId());
        product.setTenantId(tenantId);
        product.setProductCatId(standedProduct.getProductCatId());
        product.setStandedProdId(standedProdId);
        product.setStorageGroupId(group.getStorageGroupId());
        product.setProductType(standedProduct.getProductType());
        product.setProdName(standedProduct.getStandedProductName());//使用标准品名称设置为商品名称
        product.setState(ProductConstants.Product.State.UNEDIT);//未编辑状态
    	product.setProdId(standedProdId);//标准品/商品/SKU 主键一致减少查询
    	product.setOperId(standedProduct.getCreateId());
    	product.setOperTime(DateUtils.currTimeStamp());
        productAtomSV.installProduct(product);
        
        //添加商品
        if(attrValList!=null){
        	for (AttrValRequest attrValReq : attrValList) {
        		ProdAttr prodAttr = new ProdAttr();
        		BeanUtils.copyProperties(prodAttr, attrValReq);
        		prodAttr.setTenantId(product.getTenantId());
        		prodAttr.setProdId(product.getProdId());
        		prodAttr.setAttrvalueDefId(attrValReq.getAttrValId());
        		prodAttr.setAttrValueName(attrValReq.getAttrVal());
        		prodAttr.setAttrValueName2(attrValReq.getAttrVal2());
        		prodAttr.setState(CommonConstants.STATE_ACTIVE);// 设置为有效
        		prodAttr.setOperId(product.getOperId());
        		prodAttr.setOperTime(product.getOperTime());
        		//获取属性类型
        		prodAttr.setAttrType(ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_KEY);
        		/*ProdCatAttr prodCatAttr = prodCatAttrAtomSV.selectById(tenantId, attrValReq.getAttrId().toString());
        		if (prodCatAttr!=null) {
        			prodAttr.setAttrType(prodCatAttr.getAttrType());
				}else {
					logger.error("获取属性类型失败");
					return null;
				}*/
        		// 添加成功
        		prodAttrAtomSV.installProdAttr(prodAttr);
        	}
        }
        return product;
    }

    /**
     * 对停用/售罄下架的商品进行上架处理
     *
     * @param tenantId
     * @param prodId
     */
    @Override
    public void changeToSaleForStop(String tenantId, String prodId,Long operId) {
        Product product = productAtomSV.selectByProductId(prodId);
        if (product == null){
            throw new BusinessException("","未找到相关的商品信息,租户ID:"+tenantId+",商品标识:"+prodId);
        }
        if (!ProductConstants.Product.State.SALE_OUT.equals(product.getState())){
        	return;
        }
        changeToSaleForStop(product,operId);
    }


    /**
     * 商品下架,当库存组为停用时,则为停用下架,否则为售罄下架
     *
     * @param tenantId
     * @param prodId
     */
    @Override
    public void offSale(String tenantId,String supplierId, String prodId, Long operId) {
        Product product = productAtomSV.selectByProductId(prodId);
        if (product == null
                || !ProductConstants.Product.State.IN_SALE.equals(product.getState())){
            logger.warn("未找到对应商品信息,租户ID:{},商品ID:{}",product.getTenantId(),product.getProdId());
            throw new BusinessException("","未找到相关的商品信息或商品非[在售]状态");
        }
        //只处理在售商品
        StorageGroup storageGroup = storageGroupAtomSV.queryByGroupIdAndSupplierId(
                product.getTenantId(),product.getSupplierId(),product.getStorageGroupId());
        changeToStop(storageGroup,product,operId);
    }

    
    /**
     * 进行停用下架
     *
     * @param product
     * @param operId
     */
    @Override
    public void offSale(StorageGroup storageGroup,Product product, Long operId) {
        if (product == null
                || !ProductConstants.Product.State.IN_SALE.equals(product.getState())){
            throw new BusinessException("","未找到相关的商品信息或商品非[在售]状态");
        }
        changeToStop(storageGroup,product,operId);
    }

    /**
     * 废弃商品
     *
     * @param tenantId
     * @param prodId
     */
    @Override
    public void discardProduct(String tenantId, String prodId,Long operId) {
        Product product = productAtomSV.selectByProductId(prodId);
        if (product == null){
            throw new BusinessException("","未找到相关的商品信息,租户ID:"+tenantId+",商品标识:"+prodId);
        }
        //设置为废弃状态
        product.setState(ProductConstants.Product.State.DISCARD);
        product.setOperId(operId);
        //添加日志
        updateProdAndStatus(product);
        //搜索中删除商品数据
        skuIndexManage.deleteSKUIndexByProductId(product.getProdId());
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
    public ProductRoute queryRouteGroupOfProd(String tenantId, String supplierId,String productId) {
        Product product = productAtomSV.selectByProductId(productId);
        if (product==null) {
            logger.warn("未查询到对应销售商品,租户ID:{},商品标识:{}",tenantId,productId);
            throw new BusinessException("", "未查询到对应销售商品,租户ID:" + tenantId + ",商品标识:" + productId);
        }
        StorageGroup storageGroup = storageGroupAtomSV.queryByGroupIdAndSupplierId(tenantId,supplierId,product.getStorageGroupId());
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

    /**
     * 查询商品的非关键属性
     *
     * @param tenantId
     * @param supplierId
     * @param productId
     * @return
     */
    @Override
/*    public ProdAttrMap queryNoKeyAttrOfProduct(String tenantId, String supplierId,String productId) {
        //查询商品信息
        Product product = productAtomSV.selectByProductId(tenantId,productId);
        if (product==null){
            logger.warn("未找到对应销售商品信息,租户ID:{},销售商品ID:{}",tenantId,productId);
            throw new BusinessException("","未找到对应销售商品信息,租户ID:"+tenantId+",销售商品ID:"+productId);
        }
		//查询es
        ArrayList<SearchCriteria> searchCriterias = new ArrayList<SearchCriteria>();
        
        if (StringUtil.isBlank(product.getTenantId())) {
        	searchCriterias.add(new SearchCriteria(SearchFieldConfConstants.TENANT_ID, 
        											product.getTenantId(), 
        											new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring))
        											);
		}
        if (StringUtil.isBlank(product.getProdId())) {
			searchCriterias.add(new SearchCriteria(SearchFieldConfConstants.PRODUCT_ID,
					product.getProdId(),
					new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		}
        searchCriterias.add(new SearchCriteria("attrtype",
        		ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_NONKEY,
        		new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
		List<Sort> sorts = new ArrayList<>();
		Result<SKUInfo> result = productSearch.search(searchCriterias, 0, 10, sorts );
		//组装数据
		for (SKUInfo sku : result.getContents()) {
			List<AttrInfo> attrinfos = sku.getAttrinfos();
			
		}
        return queryNoKeyAttrOfProduct(product);
    }*/
    public ProdAttrMap queryNoKeyAttrOfProduct(String tenantId, String supplierId,String productId) {
    	//查询商品信息
    	//Product product = productAtomSV.selectByProductId(tenantId,productId);
    	//查询es
    	List<SearchCriteria> searchCriterias = new ArrayList<SearchCriteria>();
    	searchCriterias.add(new SearchCriteria(SearchFieldConfConstants.TENANT_ID,
    			tenantId,
    			new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
    	searchCriterias.add(new SearchCriteria("productid",
    			productId,
    			new SearchOption(SearchOption.SearchLogic.must, SearchOption.SearchType.querystring)));
    	Result<SKUInfo> result = productSearch.searchByCriteria(searchCriterias, 0, 10, null);
    	if (CollectionUtil.isEmpty(result.getContents())) {
    		logger.error("查询商品失败");
    		return null;
		}
    	SKUInfo standedProduct = result.getContents().get(0);
    	
    	
    	if (standedProduct==null){
    		logger.warn("未找到对应销售商品信息,租户ID:{},销售商品ID:{}",tenantId,productId);
    		throw new BusinessException("","未找到对应销售商品信息,租户ID:"+tenantId+",销售商品ID:"+productId);
    	}
    	
    	Product product = new Product();
    	product.setTenantId(standedProduct.getTenantid());
    	product.setProductCatId(standedProduct.getProductcategoryid());
    	product.setProdId(standedProduct.getProductid());
    	
    	
    	return queryNoKeyAttrOfProduct(product);
    }

    /**
     * 查询商品的非关键属性
     *
     * @param product
     * @return
     */
    @Override
    public ProdAttrMap queryNoKeyAttrOfProduct(Product product) {
        String tenantId = product.getTenantId();
        ProdAttrMap attrMapOfNormProd = new ProdAttrMap();
        Map<Long, List<Long>> attrAndValMap = new HashMap<>();
        Map<Long, CatAttrInfoForProd> attrDefMap = new HashMap<>();
        Map<Long, ProdAttrValInfo> attrValDefMap = new HashMap<>();
        // 查询对应类目非关键属性
        List<ProdCatAttrAttch> catAttrAttches = catAttrAttachAtomSV.queryAttrOfByIdAndType(tenantId,
                product.getProductCatId(), ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_NONKEY);
        // 查询标准品对应属性的属性值
        for (ProdCatAttrAttch catAttrAttch : catAttrAttches) {
            CatAttrInfoForProd catAttrDef = new CatAttrInfoForProd();
            BeanUtils.copyProperties(catAttrDef, catAttrAttch);
            List<Long> attrValDefList = new ArrayList<>();
            attrAndValMap.put(catAttrDef.getAttrId(), attrValDefList);
            attrDefMap.put(catAttrDef.getAttrId(), catAttrDef);
            // 查询销售商品非关键属性值
            List<ProdAttr> prodAttrs = prodAttrAtomSV.queryOfProdAndAttr(tenantId,product.getProdId(),catAttrAttch.getAttrId());
            for (ProdAttr prodAttr : prodAttrs) {
                ProdAttrValInfo valDef = new ProdAttrValInfo();
                BeanUtils.copyProperties(valDef, prodAttr);
                valDef.setProductAttrValId(prodAttr.getProdAttrId());
                valDef.setProductId(prodAttr.getProdId());
                valDef.setAttrValId(prodAttr.getAttrvalueDefId());
                valDef.setAttrVal(prodAttr.getAttrValueName());
                valDef.setAttrVal2(prodAttr.getAttrValueName2());
                //查询属性值
                if (prodAttr.getAttrvalueDefId() != null) {
                    ProdAttrvalueDef attrvalueDef = attrValDefAtomSV.selectById(tenantId,prodAttr.getAttrvalueDefId());
                    if (attrvalueDef != null){
                    	valDef.setAttrVal(attrvalueDef.getAttrValueName());
                    }
                }
                attrValDefMap.put(valDef.getProductAttrValId(), valDef);
                attrValDefList.add(valDef.getProductAttrValId());
            }
        }
        attrMapOfNormProd.setAttrAndVal(attrAndValMap);
        attrMapOfNormProd.setAttrDefMap(attrDefMap);
        attrMapOfNormProd.setAttrValDefMap(attrValDefMap);
        return attrMapOfNormProd;

    }
/*    public ProdAttrMap queryNoKeyAttrOfProduct(Product product) {
    	String tenantId = product.getTenantId();
    	ProdAttrMap attrMapOfNormProd = new ProdAttrMap();
    	Map<Long, List<Long>> attrAndValMap = new HashMap<>();
    	Map<Long, CatAttrInfoForProd> attrDefMap = new HashMap<>();
    	Map<Long, ProdAttrValInfo> attrValDefMap = new HashMap<>();
    	// 查询对应类目非关键属性
    	List<ProdCatAttrAttch> catAttrAttches = catAttrAttachAtomSV.queryAttrOfByIdAndType(tenantId,
    			product.getProductCatId(), ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_NONKEY);
    	// 查询标准品对应属性的属性值
    	for (ProdCatAttrAttch catAttrAttch : catAttrAttches) {
    		CatAttrInfoForProd catAttrDef = new CatAttrInfoForProd();
    		BeanUtils.copyProperties(catAttrDef, catAttrAttch);
    		List<Long> attrValDefList = new ArrayList<>();
    		attrAndValMap.put(catAttrDef.getAttrId(), attrValDefList);
    		attrDefMap.put(catAttrDef.getAttrId(), catAttrDef);
    		// 查询销售商品非关键属性值
    		List<ProdAttr> prodAttrs = prodAttrAtomSV.queryOfProdAndAttr(tenantId,product.getProdId(),catAttrAttch.getAttrId());
    		for (ProdAttr prodAttr : prodAttrs) {
    			ProdAttrValInfo valDef = new ProdAttrValInfo();
    			BeanUtils.copyProperties(valDef, prodAttr);
    			valDef.setProductAttrValId(prodAttr.getProdAttrId());
    			valDef.setProductId(prodAttr.getProdId());
    			valDef.setAttrValId(prodAttr.getAttrvalueDefId());
    			valDef.setAttrVal(prodAttr.getAttrValueName());
    			valDef.setAttrVal2(prodAttr.getAttrValueName2());
    			//查询属性值
    			if (prodAttr.getAttrvalueDefId() != null) {
    				ProdAttrvalueDef attrvalueDef = attrValDefAtomSV.selectById(tenantId,prodAttr.getAttrvalueDefId());
    				if (attrvalueDef != null){
    					valDef.setAttrVal(attrvalueDef.getAttrValueName());
    				}
    			}
    			attrValDefMap.put(valDef.getProductAttrValId(), valDef);
    			attrValDefList.add(valDef.getProductAttrValId());
    		}
    	}
    	attrMapOfNormProd.setAttrAndVal(attrAndValMap);
    	attrMapOfNormProd.setAttrDefMap(attrDefMap);
    	attrMapOfNormProd.setAttrValDefMap(attrValDefMap);
    	return attrMapOfNormProd;
    	
    }
*/
    @Override
    public FastProductInfoRes queryFastInfoList(FastProductReq req) {
        //话费面额
        Long attrId = 100002l;
        String catId = req.getProductCatId();
        //若为流量类目,则修改流量面额属性
        if ("10000010020000".equals(catId)){
        	attrId = 100003l;
        }

        List<ProdFastSkuAttach> nationSkuList = prodSkuAtomSV.queryNationFastProd(req.getTenantId(),
                req.getProductCatId(),req.getBasicOrgId(),req.getUserType(),req.getUserId(),attrId);
        List<ProdFastSkuAttach> localSkuList = prodSkuAtomSV.queryLocalFastProd(req.getTenantId(),
                req.getProductCatId(),req.getBasicOrgId(),req.getUserType(),req.getUserId(),attrId,req.getProvCode());

        FastProductInfoRes infoRes = new FastProductInfoRes();
        infoRes.setNationMap(queryFastProd(req.getTenantId(),nationSkuList));
        infoRes.setLocalMap(queryFastProd(req.getTenantId(),localSkuList));
        return infoRes;
    }

    /**
     * 对销售商品进行上架处理
     *
     * @param tenantId
     * @param prodId
     * @param operId
     */
    @Override
    public void changeToInSale(String tenantId,String supplierId, String prodId, Long operId) {
        Product product = productAtomSV.selectByProductId(tenantId,supplierId,prodId);
        if (product == null){
            throw new BusinessException("","未找到相关的商品信息,租户ID:"+tenantId+",商品标识:"+prodId);
        }
        changeToInSale(product,operId);
    	
    }

    /**
     * 对销售商品进行上架处理
     *
     * @param product
     * @param operId
     */
    @Override
    public void changeToInSale(Product product, Long operId) {
        //进行上架处理
        product.setState(ProductConstants.Product.State.IN_SALE);
        //添加日志
        updateProdAndStatus(product);
    }

    /**
     * 对审核通过的商品进行上架处理
     *
     * @param product
     * @param operId
     */
    @Override
    public void changeToInSaleFromAudit(Product product, Long operId) {
        String tenantId = product.getTenantId();
        //若商品状态不是"待审核",则不处理
        if(!ProductConstants.Product.State.VERIFYING.equals(product.getState())){
            logger.warn("The state of product is not verifying. productId:{},state:{}",product.getProdId(),product.getState());
            return;
        }
        //若标准品不存在,或不是"可使用"状态,则直接转为下架
        StandedProduct standedProduct = standedProductAtomSV.selectById(tenantId,product.getStandedProdId());
        if (standedProduct==null){
            logger.warn("未找到指定的标准品或标准品状态为不可用,租户ID:{},商户ID:{},标准品ID:{}"
                    ,tenantId,product.getSupplierId(),product.getStandedProdId());
            throw new BusinessException("","未找到相关的商品信息");
        }

        //1.库存组不存在,或已废弃,不处理
        StorageGroup storageGroup = storageGroupAtomSV.queryByGroupIdAndSupplierId(
                tenantId,product.getSupplierId(),product.getStorageGroupId());
        if (storageGroup==null
                || StorageConstants.StorageGroup.State.DISCARD.equals(storageGroup.getState())
                || StorageConstants.StorageGroup.State.AUTO_DISCARD.equals(storageGroup.getState())){
            logger.warn("The storage group is null or discard.groupId:{},groupState:{}",
                    product.getStorageGroupId(),storageGroup==null?null:storageGroup.getState());
            throw new BusinessException("","未找到相关的库存组或库存组已废弃");
        }
        //查询当前库存组可用量
        Long usableNum = storageNumBusiSV.queryNowUsableNumOfGroup(tenantId,storageGroup.getStorageGroupId());
        //若已启用库存下的SKU库存存在未设置价格,则将商品设置为仓库中
        //或标准品状态不是"可使用"状态
        if (skuStorageAtomSV.countOfNoPrice(tenantId,storageGroup.getStorageGroupId())>0
                ||!StandedProductConstants.STATUS_ACTIVE.equals(standedProduct.getState())){
            logger.warn("The sku price of product is not full .productId:{}",product.getProdId());
            product.setState(ProductConstants.Product.State.IN_STORE);
        }
        //库存组停用或当前库存可用为零,直接切换至"售罄下架"
        else if (StorageConstants.StorageGroup.State.STOP.equals(storageGroup.getState())
                ||StorageConstants.StorageGroup.State.AUTO_STOP.equals(storageGroup.getState())
                ||usableNum==null || usableNum<=0){
            changeToStop(storageGroup,product, operId);
            return;
        }else {
            product.setUpTime(DateUtils.currTimeStamp());
            //进行上架处理
            product.setState(ProductConstants.Product.State.IN_SALE);
        }
        if (operId!=null){
        	product.setOperId(operId);
        }
        product.setUpTime(DateUtils.currTimeStamp());
        //添加日志
        updateProdAndStatus(product);
    }

    /**
     * 查询管理界面中的非关键属性
     *
     * @param tenantId
     * @param productId
     * @return
     */
    @Override
    public ProdNoKeyAttr queryNoKeyAttrForEdit(String tenantId, String productId) {
        //查询商品信息
        Product product = productAtomSV.selectByProductId(productId);
        if (product==null){
            logger.warn("未找到对应销售商品信息,租户ID:{},销售商品ID:{}",tenantId,productId);
            throw new BusinessException("","未找到对应销售商品信息,租户ID:"+tenantId+",销售商品ID:"+productId);
        }
        ProdNoKeyAttr noKeyAttr = new ProdNoKeyAttr();
        Map<Long, List<ProdAttrValInfo>> attrValDefMap = new HashMap<>();
        List<CatAttrInfoForProd> attrAndValList = new ArrayList<>();
        // 查询对应类目非关键属性
        List<ProdCatAttrAttch> catAttrAttches = catAttrAttachAtomSV.queryAttrOfByIdAndType(tenantId,
                product.getProductCatId(), ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_NONKEY);
        // 查询标准品对应属性的属性值
        for (ProdCatAttrAttch catAttrAttch : catAttrAttches) {
            CatAttrInfoForProd catAttrDef = new CatAttrInfoForProd();
            BeanUtils.copyProperties(catAttrDef, catAttrAttch);
            if (!ProdAttrAndValDefConstants.ProdAttrDef.ValueWay.SELECT.equals(catAttrAttch.getValueWay())
                    && !ProdAttrAndValDefConstants.ProdAttrDef.ValueWay.MULTI_CHOSE.equals(catAttrAttch.getValueWay())){
                List<ProdAttrValInfo> valInfoList = new ArrayList<>();
                //查询属性对应的属性值
                List<ProdAttr> prodAttrs = prodAttrAtomSV.queryOfProdAndAttr(tenantId,productId,catAttrAttch.getAttrId());
                if (!CollectionUtil.isEmpty(prodAttrs)){
                    ProdAttr prodAttr = prodAttrs.get(0);
                    ProdAttrValInfo valInfo = new ProdAttrValInfo();
                    BeanUtils.copyProperties(valInfo,prodAttr);
                    valInfo.setProductAttrValId(prodAttr.getProdAttrId());
                    valInfo.setProductId(productId);
                    valInfo.setAttrValId(prodAttr.getAttrvalueDefId());
                    valInfo.setAttrVal(prodAttr.getAttrValueName());
                    valInfo.setAttrVal2(prodAttr.getAttrValueName2());
                    valInfoList.add(valInfo);
                }
                attrValDefMap.put(catAttrDef.getAttrId(),valInfoList);
            }else {
                attrValDefMap.put(catAttrDef.getAttrId(),getAttrValsOfAttr(product, catAttrAttch.getAttrId()));
            }
            attrAndValList.add(catAttrDef);
        }
        noKeyAttr.setAttrInfoForProdList(attrAndValList);
        noKeyAttr.setAttrValMap(attrValDefMap);
        return noKeyAttr;
    }

    /**
     * 查询销售商品信息
     *
     * @param tenantId
     * @param productId
     * @return
     */
    @Override
    public Product queryByProdId(String tenantId,String supplierId, String productId) {
        Product product = productAtomSV.selectByProductId(tenantId,supplierId,productId);
        if (product==null){
            throw new BusinessException("","未查询到指定的商品信息,租户ID:"+tenantId+",商品标识:"+productId);
        }
        
        return product;
    }
/*    public ProductInfo queryByProdId(String tenantId,String supplierId, String productId) {
    	Product product = productAtomSV.selectByProductId(tenantId,supplierId,productId);
    	if (product==null){
    		throw new BusinessException("","未查询到指定的商品信息,租户ID:"+tenantId+",商品标识:"+productId);
    	}
    	ProductInfo productInfo = new ProductInfo();
    	BeanUtils.copyProperties(productInfo,product);
    	return productInfo;
    }
*/
    public Map<String,FastSkuProdInfo> queryFastProd(String tenantId,List<ProdFastSkuAttach> skuAttachList){
        Map<String,FastSkuProdInfo> prodInfoMap = new HashMap<>();

        for (ProdFastSkuAttach skuAttach:skuAttachList){
            SkuStorageVo storageVo = storageNumBusiSV.queryStorageOfSku(tenantId,skuAttach.getSkuId());
            //若此商品没有库存,则不处理
            if (storageVo==null
                    || storageVo.getUsableNum()==null
                    || storageVo.getUsableNum()<1){
                continue;
            }
            FastSkuProdInfo prodInfo = new FastSkuProdInfo();
            prodInfo.setSkuId(skuAttach.getSkuId());
            prodInfo.setSalePrice(storageVo.getSalePrice());
            prodInfoMap.put(skuAttach.getAttrValueName(),prodInfo);
        }
        return prodInfoMap;
    }

    @Override
    public void changeToSaleForStop(Product product,Long operId){
        String tenantId = product.getTenantId();
        //若商品状态不是"停用下架",也不是"售罄下架",则不进行处理
        if(!ProductConstants.Product.State.STOP.equals(product.getState()) && !ProductConstants.Product.State.SALE_OUT.equals(product.getState())){
            return;
        }
        //查询库存组是否为"启用"状态
        StorageGroup storageGroup = storageGroupAtomSV.queryByGroupIdAndSupplierId(tenantId,product.getSupplierId(),product.getStorageGroupId());
        if (storageGroup==null){
            throw new BusinessException("","对应库存组不存在,租户ID:"+tenantId+"库存组ID:"+product.getStorageGroupId());
        }
        //库存组为停用或自动停用
        if (StorageConstants.StorageGroup.State.STOP.equals(storageGroup.getState())|| StorageConstants.StorageGroup.State.AUTO_STOP.equals(storageGroup.getState())){
            //若商品为"停用下架"则不处理
            if (ProductConstants.Product.State.STOP.equals(product.getState())){
                return;
            }//若商品为"售罄下架",则变更为"停用下架"
            else if(ProductConstants.Product.State.SALE_OUT.equals(product.getState())){
                changeToStop(storageGroup,product,operId);
                return;
            }
        }
        if (!StorageConstants.StorageGroup.State.ACTIVE.equals(storageGroup.getState())&& !StorageConstants.StorageGroup.State.AUTO_ACTIVE.equals(storageGroup.getState())){
            throw new BusinessException("","库存组不是[启用]状态,租户ID:"+tenantId+"库存组ID:"+product.getStorageGroupId());
        }
        //检查缓存中商品的库存是否大于零
        Long userNum = storageNumBusiSV.queryNowUsableNumOfGroup(tenantId,product.getStorageGroupId());
        //若缓存中数据为零,则检查数据库中数据
        if (userNum==null || userNum<1){
            List<Storage> storageList = storageAtomSV.queryActive(tenantId,product.getProdId(),true);
            //若存在可用量大于零的库存,则表示有库存,则设置为1,为概数
            if (!CollectionUtil.isEmpty(storageList)){
                userNum = 1l;
            }
        }
        //若原状态为"售罄下架",且现在没有库存,则不处理
        if (userNum<1&& ProductConstants.Product.State.SALE_OUT.equals(product.getState())){
            return;
        }
        //直接切换至"售罄下架"
        if (userNum<1){
            product.setState(ProductConstants.Product.State.SALE_OUT);
        }else { //切换至上架
            product.setUpTime(DateUtils.currTimeStamp());
            product.setState(ProductConstants.Product.State.IN_SALE);
        }
        //停用/售罄下架进行上架时,没有操作人
        if (operId!=null){
        	product.setOperId(operId);
        }
        //添加日志
        updateProdAndStatus(product);
        if (userNum>0) {
            //添加搜索引擎
            //skuIndexManage.updateSKUIndex(product.getProdId(),product.getUpTime().getTime());
        }
    }

    /**
     * 变更商品为停用下架或售罄下架
     * @param group
     * @param product
     * @param operId
     */
    private void changeToStop(StorageGroup group,Product product,Long operId){
        //若库存组为"停用"或"自动停用"则设置为"停用下架"
        if (StorageConstants.StorageGroup.State.AUTO_STOP.equals(group.getState())|| StorageConstants.StorageGroup.State.STOP.equals(group.getState())) {
            product.setState(ProductConstants.Product.State.STOP);
        }
        //否则为"售罄停用"
        else{
        	product.setState(ProductConstants.Product.State.SALE_OUT);
        }
        //当库存售光时,操作者ID为null
        if (operId!=null){
        	product.setOperId(operId);
        }
        //添加日志
        updateProdAndStatus(product);
        //搜索中删除商品数据
        //skuIndexManage.deleteSKUIndexByProductId(product.getProdId());
    }


    private List<ProdAttrValInfo> getAttrValsOfAttr (Product product,long attrId){
        List<ProdAttrValInfo> valInfoList = new ArrayList<>();
        String tenantId = product.getTenantId(),prodId = product.getProdId();
        //查询属性对应的属性值集合
        List<CatAttrValAttach> valDefList = productCatBusiSV.queryAttrValOfAttrAndType(tenantId,product.getProductCatId(),attrId,ProductCatConstants.ProductCatAttr.AttrType.ATTR_TYPE_NONKEY);
        for (CatAttrValAttach valDef:valDefList){
            ProdAttrValInfo valInfo = new ProdAttrValInfo();
            valInfo.setTenantId(tenantId);
            valInfo.setProductId(prodId);
            valInfo.setAttrId(attrId);
            valInfo.setAttrValId(valDef.getAttrvalueDefId());
            valInfo.setAttrVal(valDef.getAttrValueName());
            //查询此属性值是否存在
            ProdAttr prodAttr = prodAttrAtomSV.queryByProdAndAttrAndAttrVal(
                    tenantId,prodId,attrId,valDef.getAttrvalueDefId());
            if (prodAttr!=null){
                valInfo.setProductAttrValId(prodAttr.getProdAttrId());
                valInfo.setOperId(prodAttr.getOperId());
                valInfo.setOperTime(prodAttr.getOperTime());
            }
            valInfoList.add(valInfo);
        }
        return valInfoList;
    }

    /**
     * 对销售商品进行手动下架架处理
     *
     * @param tenantId
     * @param prodId
     * @param operId
     */
	@Override
    public void changeSaleToStore(String tenantId, String supplierId, Long operId) {
		Product product = new Product();
        //修改商品"state"为IN_STORE
        product.setState(ProductConstants.Product.State.IN_STORE);
        //将商品从搜索引擎中移除
        //skuIndexManage.deleteSKUIndexByProductId(product.getProdId());
        //添加下架时间
        product.setDownTime(DateUtils.currTimeStamp());
        if (operId != null) {
            product.setOperId(operId);
        }
        //添加日志
        updateProdAndStatus(product);
		
    }

	public void updateProdStatus(String productId,String state, String operId){
		productAtomSV.updateProdStatus(productId,state,operId);
	}
	
    public void updateProdAndStatus(Product product){
    	productAtomSV.updateProdState(product.getState(),product.getProdId());
    }

    /**
     * 查询商品的目标地域
     *
     * @param tenantId
     * @param supplierId
     * @param productId
     * @return
     */
    @Override
    public List<ProdTargetAreaInfo> queryProvinceInfoOfProduct(String tenantId, String supplierId, String productId) {
        Product product = productAtomSV.selectByProductId(tenantId, supplierId, productId);
        if (product == null) {
            logger.warn("未找到对应商品信息,租户ID:{},商户ID:{},商品ID:{}",tenantId,supplierId,productId);
            throw new SystemException("", "未找到相关的商品信息");
        }
        //是否为全国销售
        boolean isNoAll = ProductConstants.Product.IsSaleNationwide.YES.equals(product.getIsSaleNationwide())?false:true;
        //确定是否为全国销售
        List<ProdTargetAreaInfo> areaInfoList = new ArrayList<>();
        //查询所有省份信息
        IGnAreaQuerySV gnAreaQuerySV = DubboConsumerFactory.getService("iGnAreaQuerySV");
        List<GnAreaVo> provAreaList = gnAreaQuerySV.getProvinceList();
        for (GnAreaVo areaVo:provAreaList){
            //不为全国销售,且不是目标地域,直接忽略
            if (isNoAll && prodTargetAreaAtomSV.countByAreaCode(
                    tenantId,productId,Integer.parseInt(areaVo.getAreaCode()),false)<1){
                continue;
            }
            ProdTargetAreaInfo areaInfo = new ProdTargetAreaInfo();
            BeanUtils.copyProperties(areaInfo,areaVo);
            areaInfo.setOwn(true);
            areaInfoList.add(areaInfo);
        }
        return areaInfoList;
    }

	@Override
	public void changeSaleToStore(String tenantId, String supplierId, String productId, Long operId) {
		Product product = productAtomSV.selectByProductId(tenantId, supplierId, productId);
		//修改商品"state"为IN_STORE
        product.setState(ProductConstants.Product.State.IN_STORE);
        //将商品从搜索引擎中移除
        //skuIndexManage.deleteSKUIndexByProductId(product.getProdId());
        //添加下架时间
        product.setDownTime(DateUtils.currTimeStamp());
        if (operId != null) {
            product.setOperId(operId);
        }
        //添加日志
        updateProdAndStatus(product);
	}

}
	
