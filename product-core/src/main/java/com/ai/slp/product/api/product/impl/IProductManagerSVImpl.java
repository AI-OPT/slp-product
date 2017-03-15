package com.ai.slp.product.api.product.impl;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.slp.product.api.product.interfaces.IProductManagerSV;
import com.ai.slp.product.api.product.param.OtherSetOfProduct;
import com.ai.slp.product.api.product.param.ProdNoKeyAttr;
import com.ai.slp.product.api.product.param.ProdStateLog;
import com.ai.slp.product.api.product.param.ProductCheckParam;
import com.ai.slp.product.api.product.param.ProductEditQueryReq;
import com.ai.slp.product.api.product.param.ProductEditUp;
import com.ai.slp.product.api.product.param.ProductInfoForUpdate;
import com.ai.slp.product.api.product.param.ProductInfoQuery;
import com.ai.slp.product.api.product.param.ProductPriorityParam;
import com.ai.slp.product.api.product.param.ProductQueryInfo;
import com.ai.slp.product.api.product.param.ProductStorageSale;
import com.ai.slp.product.api.product.param.ProductStorageSaleParam;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.constants.NormProdConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.dao.mapper.bo.product.ProdPicture;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.service.atom.interfaces.IProdCatDefAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProdPictureAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.IProductAtomSV;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IProductManagerBusiSV;
import com.ai.slp.product.service.business.interfaces.search.ISKUIndexBusiSV;
import com.ai.slp.product.util.CommonUtils;
import com.ai.slp.product.util.MQConfigUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;

/**
 * Created by jackieliu on 16/6/6.
 */
@Service(validation = "true")
@Component
public class IProductManagerSVImpl implements IProductManagerSV {
    private static final Logger LOGGER = LoggerFactory.getLogger(IProductManagerSVImpl.class);
    @Autowired
    IProductManagerBusiSV productManagerBusiSV;
    @Autowired
    IProductBusiSV productBusiSV;
    @Autowired
    ISKUIndexBusiSV skuIndexManage;
    @Autowired
    IProductAtomSV productAtomSV;
    
    @Autowired
    IProdCatDefAtomSV catDefAtomSV;
    
    @Autowired
    IProdPictureAtomSV prodPictureAtomSV;
    /**
     * 商品管理查询商品编辑状态<br>
     * 状态 1未编辑2已编辑<br>
     * 按生成时间排序<br>
     *
     * @param productEditParam
     * @return 满足条件的商品集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public PageInfoResponse<ProductEditUp> queryProductEdit(ProductEditQueryReq productEditParam) throws BusinessException, SystemException {
    	PageInfoResponse<ProductEditUp> response = new PageInfoResponse<>();
    	ResponseHeader responseHeader = null;
    	try{
        CommonUtils.checkTenantId(productEditParam.getTenantId());
        CommonUtils.checkSupplierId(productEditParam.getSupplierId());
        String tenantId = productEditParam.getTenantId();
        PageInfo<Product> products = productManagerBusiSV.queryPageForEdit(productEditParam);
        List<ProductEditUp> editUpList = new ArrayList<>();
        for (Product product:products.getResult()){
            ProductEditUp productEditUp = new ProductEditUp();
            BeanUtils.copyProperties(productEditUp,product);
            //设置类目名称
            ProductCat cat = catDefAtomSV.selectById(tenantId,product.getProductCatId());
            if (cat!=null){
            	productEditUp.setProductCatName(cat.getProductCatName());
            }
            //查询主预览图
            ProdPicture prodPicture = prodPictureAtomSV.queryMainOfProd(product.getProdId());
            if (prodPicture!=null){
                productEditUp.setProPictureId(prodPicture.getProPictureId());
                productEditUp.setVfsId(prodPicture.getVfsId());
                productEditUp.setPicType(prodPicture.getPicType());
            }
             editUpList.add(productEditUp);
        }
        BeanUtils.copyProperties(response,products);
        response.setResult(editUpList);
    	}catch(Exception e){
    		if(e instanceof BusinessException){
    			responseHeader = new ResponseHeader(false,((BusinessException) e).getErrorCode(),((BusinessException) e).getErrorMessage());
    		}else{
    			responseHeader = new ResponseHeader(false,CommonConstants.OPERATE_FAIL,"查询失败");
    		}
    		response.setResponseHeader(responseHeader);
    	}
        return response;
    }

    /**
     * 商品管理查询商品被拒绝信息-与商品审核中查询共用<br>
     * 拒绝时间为操作时间<br>
     *
     * @param productRefuseParam
     * @return 满足条件的商品集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public PageInfoResponse<ProductEditUp> queryProductRefuse(ProductEditQueryReq productRefuseParam) throws BusinessException, SystemException {
    	CommonUtils.checkTenantId(productRefuseParam.getTenantId());
        CommonUtils.checkSupplierId(productRefuseParam.getSupplierId());
    	return productManagerBusiSV.queryProductRefuse(productRefuseParam);
    }

    /**
     * 审核是否通过调用方法<br>
     * 通过上架类型 1审核通过后立即上架 2审核通过后放入仓库 3定时上架<br>
     * 判断改变为哪种状态<br>
     * 销售商品状态 4审核未通过<br>
     * 拒绝后把拒绝类型和原因存入销售商品流程日志表<br>
     *
     * @param productCheckParam
     * @return 基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public BaseResponse productCheck(ProductCheckParam productCheckParam) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(productCheckParam.getTenantId());
        productManagerBusiSV.auditProduct(productCheckParam);
        return CommonUtils.genSuccessResponse("OK");
    }

    /**
     * 商品申请优先审核调用方法<br>
     *
     * @param productPriorityParam
     * @return 基本信息
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public BaseResponse productPriority(ProductPriorityParam productPriorityParam) throws BusinessException, SystemException {
        return null;
    }

    /**
     * 查询单个商品的其他设置信息<br>
     *
     * @param productInfoQuery 单个商品的标识信息
     * @return 单个商品的受众信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     */
    @Override
    public OtherSetOfProduct queryOtherSetOfProduct(ProductInfoQuery productInfoQuery) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(productInfoQuery.getTenantId());
        OtherSetOfProduct otherSet =  productManagerBusiSV.queryOtherSetOfProd(
                productInfoQuery.getTenantId(),productInfoQuery.getSupplierId(),productInfoQuery.getProductId());
        CommonUtils.addSuccessResHeader(otherSet,"OK");
        return otherSet;
    }

    /**
     * 分页查询仓库中的商品列表<br>
     * 商品状态:6仓库中（审核通过放入） 61售罄下架 定时上架-通过上架类型判断  62废弃下架<br>
     *
     * @param productStorageSaleParam
     * @return 商品管理售中与仓库商品返回类集合
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public PageInfoResponse<ProductStorageSale> queryStorageProdByState(ProductStorageSaleParam productStorageSaleParam) throws BusinessException, SystemException {
    	CommonUtils.checkTenantId(productStorageSaleParam.getTenantId());
        return productManagerBusiSV.queryStorageProdByState(productStorageSaleParam);
    }

    /**
     * 对商品进行上架处理
     *
     * @param query
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     */
    
    @Override
    public BaseResponse changeToInSale(ProductInfoQuery query) throws BusinessException, SystemException {
    	boolean ccsMqFlag=false;
    	
    	Product product = productAtomSV.selectByProductId(query.getTenantId(),query.getSupplierId(),query.getProductId());
        if (product == null){
            throw new BusinessException("","未找到相关的商品信息,租户ID:"+query.getTenantId()+",商品标识:"+query.getProductId());
        }
    	/*  CommonUtils.checkTenantId(query.getTenantId());
        long  inSaleStart= System.currentTimeMillis();
        LOGGER.info("=====执行productBusiSV.changeToInSale进行上架操作,当前时间戳:"  + inSaleStart);
        productBusiSV.changeToInSale(query.getTenantId(),query.getSupplierId(),query.getProductId(),query.getOperId());
        long inSaleEnd = System.currentTimeMillis();
        LOGGER.info("=====执行productBusiSV.changeToInSale结束,当前时间戳:" + inSaleEnd + ", 用时:" + (inSaleEnd-inSaleStart) + "毫秒");
        return CommonUtils.addSuccessResHeader(new BaseResponse(),"");*/
	   	//从配置中心获取mq_enable
	  	ccsMqFlag=MQConfigUtil.getCCSMqFlag();
    	if (!ccsMqFlag) {
    		CommonUtils.checkTenantId(query.getTenantId());
            long  inSaleStart= System.currentTimeMillis();
            LOGGER.info("=====执行productBusiSV.changeToInSale进行上架操作,当前时间戳:"  + inSaleStart);
            productBusiSV.changeToInSale(query.getTenantId(),query.getSupplierId(),query.getProductId(),query.getOperId());
            long inSaleEnd = System.currentTimeMillis();
            LOGGER.info("=====执行productBusiSV.changeToInSale结束,当前时间戳:" + inSaleEnd + ", 用时:" + (inSaleEnd-inSaleStart) + "毫秒");
            return CommonUtils.addSuccessResHeader(new BaseResponse(),"");
		} else {
			BaseResponse response = CommonUtils.genSuccessResponse("");
			//发送消息
			MDSClientFactory.getSenderClient(NormProdConstants.MDSNS.MDS_NS_CHANGETOINSALE_TOPIC).send(JSON.toJSONString(query), 0);
			ResponseHeader responseHeader = new ResponseHeader(true,
					ExceptCodeConstants.Special.SUCCESS, "成功");
			response.setResponseHeader(responseHeader);
			return response;  
		}
    }

    /**
     * 为编辑页面查询商品非关键属性
     *
     * @param query
     * @return
     * @throws BusinessException
     * @throws SystemException
     */
    @Override
    public ProdNoKeyAttr queryNoKeyAttrOfProd(ProductInfoQuery query) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(query.getTenantId());
        return productBusiSV.queryNoKeyAttrForEdit(query.getTenantId(),query.getProductId());
    }

    /**
     * 更新商品信息
     * @param product
     * @return
     * @throws BusinessException
     * @throws SystemException
     */
    @Override
    public BaseResponse updateProduct(ProductInfoForUpdate product) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(product.getTenantId());
        productManagerBusiSV.updateProdEdit(product);
        return CommonUtils.genSuccessResponse("OK");
    }

    /**
     * 对商品进行手动下架处理
     * @param query
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author jiawen
     */
	@Override
	public BaseResponse changeToInStore(ProductInfoQuery query) throws BusinessException, SystemException {
		/*CommonUtils.checkTenantId(query.getTenantId());
        productBusiSV.changeSaleToStore(
                query.getTenantId(),query.getSupplierId(),query.getProductId(),query.getOperId());
        return CommonUtils.genSuccessResponse("");*/
		
		Product product = productAtomSV.selectByProductId(query.getTenantId(), query.getSupplierId(), query.getProductId());
        if (product == null) {
            throw new SystemException("", "未找到相关的商品信息,租户ID:" + query.getTenantId() + ",商品标识:" + query.getProductId());
        }
        //若商品不是在售,则直接返回
        if (!ProductConstants.Product.State.IN_SALE.equals(product.getState())){
        	return null;
        }
		
		boolean ccsMqFlag=false;
	   	//从配置中心获取mq_enable
	  	ccsMqFlag=MQConfigUtil.getCCSMqFlag();
		if (!ccsMqFlag) {
			CommonUtils.checkTenantId(query.getTenantId());
	        productBusiSV.changeSaleToStore(
	                query.getTenantId(),query.getSupplierId(),query.getProductId(),query.getOperId());
	        return CommonUtils.genSuccessResponse("");
		} else {
			BaseResponse response = CommonUtils.genSuccessResponse("");
			//发送消息
			MDSClientFactory.getSenderClient(NormProdConstants.MDSNS.MDS_NS_CHANGETOINSTORE_TOPIC).send(JSON.toJSONString(query), 0);
			ResponseHeader responseHeader = new ResponseHeader(true,
					ExceptCodeConstants.Special.SUCCESS, "成功");
			response.setResponseHeader(responseHeader);
			return response; 
		}
	}

	/**
	 * 查询在售商品 -- 按上架时间排序
	 * 
	 * @param queryInSale
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author jiawen
	 */
	@Override
	public PageInfoResponse<ProductEditUp> searchInSale(ProductQueryInfo queryInSale) throws BusinessException, SystemException {
		CommonUtils.checkTenantId(queryInSale.getTenantId());
        CommonUtils.checkSupplierId(queryInSale.getSupplierId());
        return productManagerBusiSV.queryInSale(queryInSale);
	}

	/**
	 * 查询商品审核
	 * 
	 * @param queryInfo
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author jiawen
	 */
	@Override
	public PageInfoResponse<ProductEditUp> searchAudit(ProductQueryInfo queryInfo)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(queryInfo.getTenantId());
        CommonUtils.checkSupplierId(queryInfo.getSupplierId());
        return productManagerBusiSV.queryPageForAudit(queryInfo);
	}

	/**
	 * 查询商品拒绝原因
	 * @param queryInfo
     * @return 满足条件的商品集合
     * @throws BusinessException
     * @throws SystemException
     * @author jiawen
	 */
	@Override
	public ProdStateLog queryRefuseByPordId(ProductInfoQuery queryInfo)
			throws BusinessException, SystemException {
		CommonUtils.checkTenantId(queryInfo.getTenantId());
		ProdStateLog stateLogRes = productManagerBusiSV.queryRefuseByProdId(queryInfo);
		CommonUtils.addSuccessResHeader(stateLogRes, "OK");
    	return stateLogRes;
	}
}
