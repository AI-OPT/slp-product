package com.ai.slp.product.service.business.impl;

import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.platform.common.api.area.interfaces.IGnAreaQuerySV;
import com.ai.platform.common.api.area.param.GnAreaVo;
import com.ai.slp.product.api.product.param.*;
import com.ai.slp.product.constants.CommonConstants;
import com.ai.slp.product.constants.ErrorCodeConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDef;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttr;
import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.dao.mapper.bo.product.*;
import com.ai.slp.product.service.atom.interfaces.IProdAttrValDefAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdCatDefAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.*;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IProductManagerBusiSV;
import com.ai.slp.product.service.business.interfaces.IStorageGroupBusiSV;
import com.ai.slp.product.vo.ProdRouteGroupQueryVo;
import com.ai.slp.user.api.keyinfo.interfaces.IUcKeyInfoSV;
import com.ai.slp.user.api.keyinfo.param.SearchGroupKeyInfoRequest;
import com.ai.slp.user.api.keyinfo.param.SearchGroupUserInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by jackieliu on 16/6/6.
 */
@Service
@Transactional
public class ProductManagerBusiSV implements IProductManagerBusiSV {
    private static Logger logger = LoggerFactory.getLogger(ProductManagerBusiSV.class);
    @Autowired
    IProdAttrValDefAtomSV attrValDefAtomSV;
    @Autowired
    IProductAtomSV productAtomSV;
    @Autowired
    IProductLogAtomSV productLogAtomSV;
    @Autowired
    IProdCatDefAtomSV catDefAtomSV;
    @Autowired
    IProdPictureAtomSV prodPictureAtomSV;
    @Autowired
    IProdPictureLogAtomSV prodPictureLogAtomSV;
    @Autowired
    IProdCatAttrAtomSV prodCatAttrAtomSV;
    @Autowired
    IProdAudiencesAtomSV prodAudiencesAtomSV;
    @Autowired
    IProdTargetAreaAtomSV prodTargetAreaAtomSV;
    @Autowired
    IProdSkuAttrAtomSV skuAttrAtomSV;
    @Autowired
    IProdAttrAtomSV prodAttrAtomSV;
    @Autowired
    IProdAttrLogAtomSV prodAttrLogAtomSV;
    @Autowired
    IProductBusiSV productBusiSV;
    @Autowired
    IProductStateLogAtomSV productStateLogAtomSV;
    @Autowired
    IStorageGroupBusiSV storageGroupBusiSV;
    public static List<String> editStatus = new ArrayList<>();
    //	@Autowired
//	ProductAttachMapper productAttachMapper;
    static {
        editStatus.add(ProductConstants.Product.State.ADD);
        editStatus.add(ProductConstants.Product.State.UNEDIT);
        editStatus.add(ProductConstants.Product.State.EDITED);
    }
    /**
     * 商品管理中分页查询商品信息
     *
     * @param queryReq
     * @return
     */
    @Override
    public PageInfoResponse<ProductEditUp> queryPageForEdit(ProductEditQueryReq queryReq) {
        String tenantId = queryReq.getTenantId();
        //查询所有符合条件商品
        PageInfo<Product> productPage = productAtomSV.selectPageForEdit(queryReq);
        List<ProductEditUp> editUpList = new ArrayList<>();
        for (Product product:productPage.getResult()){
            ProductEditUp productEditUp = new ProductEditUp();
            BeanUtils.copyProperties(productEditUp,product);
            //设置类目名称
            ProductCat cat = catDefAtomSV.selectById(tenantId,product.getProductCatId());
            if (cat!=null)
                productEditUp.setProductCatName(cat.getProductCatName());
            //查询主预览图
            ProdPicture prodPicture = prodPictureAtomSV.queryMainOfProd(product.getProdId());
            if (prodPicture!=null){
                productEditUp.setProPictureId(prodPicture.getProPictureId());
                productEditUp.setVfsId(prodPicture.getVfsId());
                productEditUp.setPicType(prodPicture.getPicType());
            }
             editUpList.add(productEditUp);
        }
        
        PageInfoResponse<ProductEditUp> response = new PageInfoResponse<>();
        BeanUtils.copyProperties(response,productPage);
        response.setResult(editUpList);
        return response;
    }
    /**
	 * 商品审核分页查询
	 *
	 * @param queryReq
	 * @return
	 * @author jiawen
	 */
    @Override
    public PageInfoResponse<ProductEditUp> queryPageForAudit(ProductQueryInfo queryReq) {
    	String tenantId = queryReq.getTenantId();
    	//查询所有符合条件商品
    	PageInfo<Product> productPage = productAtomSV.selectPageForAudit(queryReq);
    	List<ProductEditUp> editUpList = new ArrayList<>();
    	for (Product product:productPage.getResult()){
    		ProductEditUp productEditUp = new ProductEditUp();
    		BeanUtils.copyProperties(productEditUp,product);
    		//设置类目名称
    		ProductCat cat = catDefAtomSV.selectById(tenantId,product.getProductCatId());
    		if (cat!=null)
    			productEditUp.setProductCatName(cat.getProductCatName());
    		//查询主预览图
    		ProdPicture prodPicture = prodPictureAtomSV.queryMainOfProd(product.getProdId());
    		if (prodPicture!=null){
    			productEditUp.setProPictureId(prodPicture.getProPictureId());
    			productEditUp.setVfsId(prodPicture.getVfsId());
    			productEditUp.setPicType(prodPicture.getPicType());
    		}
    		editUpList.add(productEditUp);
    	}
    	
    	PageInfoResponse<ProductEditUp> response = new PageInfoResponse<>();
    	BeanUtils.copyProperties(response,productPage);
    	response.setResult(editUpList);
    	return response;
    }

    @Override
    public void changeRouteGroup(String tenantId, String supplierId, String prodId,
                                 String routeGroupId, Long operId) {
        Product product = productAtomSV.selectByProductId(tenantId,supplierId,prodId);
        if (product==null){
            logger.warn("未查询到指定商品,租户ID:{},商户ID:{},商品标识:{}",tenantId,supplierId,prodId);
            throw new SystemException(ErrorCodeConstants.Product.PRODUCT_NO_EXIST,"未查询到指定商品");
        }
        storageGroupBusiSV.changeRouteGroupId(tenantId,product.getStorageGroupId(),
                routeGroupId,operId);
    }

    /**
     * 查询销售商品信息和配货组信息
     *
     * @param query
     * @return
     */
    @Override
    public PageInfoResponse<ProductRouteGroupInfo> queryProdAndRouteGroup(RouteGroupQuery query) {
        ProdRouteGroupQueryVo queryVo = new ProdRouteGroupQueryVo();
        BeanUtils.copyProperties(queryVo,query);
        PageInfo<ProductRouteGroupInfo> pageInfo = productAtomSV.selectPageForRouteGroup(queryVo);
        PageInfoResponse<ProductRouteGroupInfo> pageRespone = new PageInfoResponse<ProductRouteGroupInfo>();
        pageRespone.setCount(pageInfo.getCount());
        pageRespone.setPageSize(pageInfo.getPageSize());
        pageRespone.setPageNo(pageInfo.getPageNo());
        pageRespone.setPageCount(pageInfo.getPageCount());
        pageRespone.setResult(pageInfo.getResult());
        return pageRespone;
    }

    /**
     * 审核商品
     *
     * @param productCheckParam
     */
    @Override
    public void auditProduct(ProductCheckParam productCheckParam) {
        ProductStateLog stateLog = new ProductStateLog();
        BeanUtils.copyProperties(stateLog,productCheckParam);
        stateLog.setPriorityNumber(ProductConstants.ProdStatusLog.PriorityNumber.USUAL);
        Set<String> prodIdSet = new HashSet(productCheckParam.getProdIdList());
        for (String prodId:prodIdSet){
            Product product = productAtomSV.selectByProductId(productCheckParam.getTenantId(),prodId);
            //若未找到对应商品或商品状态不是"待审核",则不处理
            if (product==null || !ProductConstants.Product.State.VERIFYING.equals(product.getState()))
                continue;
            //商品标识
            stateLog.setProdId(prodId);

            //如果是拒绝
            if (ProductConstants.Product.auditStatus.REJECT.equals(productCheckParam.getState())){
                product.setState(ProductConstants.Product.State.REJECT);
                product.setOperId(productCheckParam.getOperId());
                updateProductStatusLog(product,stateLog);
                continue;
            }

            //为审核通过
            //若为立即上架或预售,则进行上架处理
            if (ProductConstants.Product.UpShelfType.NOW.equals(product.getUpshelfType())
                    || ProductConstants.Product.UpShelfType.PRE_SALE.equals(product.getUpshelfType())){
                product.setState(ProductConstants.Product.State.IN_SALE);
                productBusiSV.changeToInSale(product,productCheckParam.getOperId());
            }else {
                product.setState(ProductConstants.Product.State.IN_STORE);
                product.setOperId(productCheckParam.getOperId());
                updateProductStatusLog(product,stateLog);
            }
        }
    }

    private void updateProductStatusLog(Product product,ProductStateLog stateLog){
        if (productAtomSV.updateById(product)>0){
            ProductLog log = new ProductLog();
            BeanUtils.copyProperties(log,product);
            productLogAtomSV.install(log);
            stateLog.setState(product.getState());
            productStateLogAtomSV.insert(stateLog);
        }
    }

    /**
     *商品管理中分页查询被拒绝商品信息
     * @param productRefuseParam
     * @return
     */
    @Override
    public PageInfoResponse<ProductEditUp> queryProductRefuse(ProductEditQueryReq productRefuseParam) {
    	//获取住户ID
    	String tenantId = productRefuseParam.getTenantId();
    	//查询符合条件的商品
    	PageInfo<Product> pageInfo = productAtomSV.selectPageForEdit(productRefuseParam);
    	ArrayList<ProductEditUp> editUpList = new ArrayList<>();
    	for (Product product : pageInfo.getResult()) {
			ProductEditUp productEditUp = new ProductEditUp();
			BeanUtils.copyProperties(productEditUp,product);
			//设置类目名称
			ProductCat cat = catDefAtomSV.selectById(tenantId, product.getProductCatId());
			if (cat!=null) 
				productEditUp.setProductCatName(cat.getProductCatName());
			//查询预览主图
			ProdPicture prodPicture = prodPictureAtomSV.queryMainOfProd(product.getProdId());
			if (prodPicture!=null) {
				productEditUp.setProPictureId(prodPicture.getProPictureId());
				productEditUp.setVfsId(prodPicture.getVfsId());
				productEditUp.setPicType(prodPicture.getPicType());
			}
			//获取    拒绝原因   拒绝描述
			ProductStateLog productStateLog = productStateLogAtomSV.selectRefuseById(productRefuseParam.getProdId());
			if (productStateLog.getRefuseReason()!=null || productStateLog.getRefuseDes()!=null) {
				productEditUp.setRefuseReason(productStateLog.getRefuseReason());
				productEditUp.setRefuseDes(productStateLog.getRefuseDes());
			}
			editUpList.add(productEditUp);
		}
    	PageInfoResponse<ProductEditUp> response = new PageInfoResponse<>();
    	BeanUtils.copyProperties(response, pageInfo);
    	response.setResult(editUpList);
    	return response;
    }
    
    /**
     * 查询商品的受众信息
     *
     * @param tenantId
     * @param prodId
     * @return
     */
    @Override
    public OtherSetOfProduct queryOtherSetOfProd(String tenantId,String supplierId, String prodId) {
        Product product = productAtomSV.selectByProductId(tenantId,supplierId,prodId);
        if (product==null){
            throw new SystemException(ErrorCodeConstants.Product.PRODUCT_NO_EXIST,
                    "未查询到指定商品,租户ID:"+tenantId+",销售商品标示:"+prodId);
        }
        OtherSetOfProduct otherSet = new OtherSetOfProduct();
        //查询个人
        List<ProdAudiences> boList = prodAudiencesAtomSV.queryByUserType(
                tenantId, prodId,ProductConstants.ProdAudiences.userType.PERSON,false);
        if (!CollectionUtil.isEmpty(boList)){
            ProdAudiencesInfo audiencesInfo = new ProdAudiencesInfo();
            BeanUtils.copyProperties(audiencesInfo,boList.get(0));
            //个人为全部可见或全部不可见,没有具体用户名称
            otherSet.setPersonAudiences(audiencesInfo);
        }
        //企业
        otherSet.setEnterpriseMap(getAudiencesInfo(tenantId,prodId,ProductConstants.ProdAudiences.userType.ENTERPRISE));
        //代理商
        otherSet.setAgentsMap(getAudiencesInfo(tenantId,prodId,ProductConstants.ProdAudiences.userType.AGENT));
        //查询目标地域
        otherSet.setAreaInfos(getTargetOfProd(tenantId,prodId));
        //查询商品主图
        List<ProdPicture> picList = prodPictureAtomSV.queryPicOfProd(prodId);
        otherSet.setProductPics(getProdPicInfo(picList));
        //添加属性值图片
        getProdPicOfAttrVal(tenantId,product,otherSet);
        return otherSet;
    }

    /**
     * 更新产品编辑信息
     * @param productInfo
     */
    @Override
    public void updateProdEdit(ProductInfoForUpdate productInfo) {
        String tenantId = productInfo.getTenantId(),
                productId = productInfo.getProdId();
        Product product = productAtomSV.selectByProductId(tenantId,productId);
        if (product == null){
            logger.warn("未找到对应销售商品,租户ID:{},商品标识:{}",tenantId,productId);
            throw new SystemException(ErrorCodeConstants.Product.PRODUCT_NO_EXIST,
                    "未找到对应商品信息,租户ID:"+tenantId+",商品标识:"+productId);
        }//若为废弃状态,不允许编辑.
        else if (ProductConstants.Product.State.DISCARD.equals(product.getState())){
            throw new SystemException("","商品已废弃,不允许编辑更新.");
        }
        Long operId = productInfo.getOperId();
        //更新商品非关键属性信息
        updateNoKeyAttr(tenantId,productId,productInfo.getNoKeyAttrValMap(),operId);
        //更新商品受众信息
        //* 个人受众
        String perAudi = productInfo.getAudiencesPerson();
        //全部可见
        if (ProductConstants.ProdAudiences.userId.USER_TYPE.equals(perAudi)) {
            List<ProdAudiences> personAudiList = prodAudiencesAtomSV.queryByUserType(tenantId, productId,
                    ProductConstants.ProdAudiences.userType.PERSON, false);
            //为空,且全部可见
            if (CollectionUtil.isEmpty(personAudiList)) {
                ProdAudiences prodAudiences = new ProdAudiences();
                prodAudiences.setTenantId(tenantId);
                prodAudiences.setProdId(productId);
                prodAudiences.setUserId(perAudi);
                prodAudiences.setOperId(operId);
                prodAudiences.setUserType(ProductConstants.ProdAudiences.userType.PERSON);
                prodAudiences.setState(CommonConstants.STATE_ACTIVE);
                prodAudiencesAtomSV.installAudiences(prodAudiences);
            }
        }//全部不可见
        else if(ProductConstants.ProdAudiences.userId.NO_USER.equals(perAudi)) {
            //设置此类型为无效
            prodAudiencesAtomSV.updateNoUser(tenantId,productId,
                    ProductConstants.ProdAudiences.userType.PERSON,operId);
        }
        //* 企业受众
        updateGroupAudiences(tenantId,productId,ProductConstants.ProdAudiences.userType.ENTERPRISE,
                productInfo.getAudiencesEnterprise(),productInfo.getEnterpriseIds(),operId);
        //* 代理商受众
        updateGroupAudiences(tenantId,productId,ProductConstants.ProdAudiences.userType.AGENT,
                productInfo.getAudiencesAgents(),productInfo.getAgentIds(),operId);
        //更新商品图片信息
        updateProdPic(tenantId,productId,"0",productInfo.getProdPics(),operId);
        //更新属性值图片信息
        Map<String, List<ProdPicInfo>> picMap =productInfo.getAttrValPics();
        Iterator<String> attrValIterator = picMap.keySet().iterator();
        while (attrValIterator.hasNext()){
            String attrValId = attrValIterator.next();
            updateProdPic(tenantId,productId,attrValId,picMap.get(attrValId),operId);
        }
        //更新目标地域
        updateTargetArea(tenantId,productId,productInfo.getIsSaleNationwide(),productInfo.getProvCodes(),operId);
        //更新商品主信息
        BeanUtils.copyProperties(product,productInfo);
        //设置为待审核
        product.setState(ProductConstants.Product.State.VERIFYING);
        //添加日志
        productBusiSV.updateProdAndStatusLog(product);
    }

    private Map<String,ProdAudiencesInfo> getAudiencesInfo(String tenantId,String prodId,String userType){
        Map<String,ProdAudiencesInfo> audiencesMap = new HashMap<>();
        ProdAudiences allAud = prodAudiencesAtomSV.queryAllByUserType(tenantId,prodId,userType,false);
        if(allAud!=null){
            ProdAudiencesInfo audiencesInfo = new ProdAudiencesInfo();
            BeanUtils.copyProperties(audiencesInfo,allAud);
            audiencesMap.put(ProductConstants.ProdAudiences.userId.USER_TYPE,audiencesInfo);
            return audiencesMap;
        }
        List<ProdAudiences> boList = prodAudiencesAtomSV.queryByUserType(tenantId,prodId, userType,false);
        if (CollectionUtil.isEmpty(boList))
            return audiencesMap;
        IUcKeyInfoSV ucKeyInfoSV = DubboConsumerFactory.getService(IUcKeyInfoSV.class);
        for (ProdAudiences audiences:boList){
            ProdAudiencesInfo audiencesInfo = new ProdAudiencesInfo();
            BeanUtils.copyProperties(audiencesInfo,audiences);
            SearchGroupKeyInfoRequest request = new SearchGroupKeyInfoRequest();
            request.setTenantId(tenantId);
            request.setUserId(audiences.getUserId());
            SearchGroupUserInfoResponse infoResponse = ucKeyInfoSV.searchGroupUserInfo(request);
            if (infoResponse!=null && infoResponse.getResponseHeader().isSuccess()){
                audiencesInfo.setUserName(infoResponse.getCustName());
                audiencesInfo.setLoginAccount(infoResponse.getUserLoginName());
            }
            audiencesMap.put(audiences.getUserId(),audiencesInfo);
        }
        return audiencesMap;
    }

    /**
     * 查收商品的目标地域
     * @return
     */
    private List<ProdTargetAreaInfo> getTargetOfProd(String tenantId,String prodId){
        List<ProdTargetAreaInfo> areaInfoList = new ArrayList<>();
        //查询所有省份信息
        IGnAreaQuerySV gnAreaQuerySV = DubboConsumerFactory.getService("iGnAreaQuerySV");
        List<GnAreaVo> provAreaList = gnAreaQuerySV.getProvinceList();
        for (GnAreaVo areaVo:provAreaList){
            ProdTargetAreaInfo areaInfo = new ProdTargetAreaInfo();
            BeanUtils.copyProperties(areaInfo,areaVo);
            //是否已设置
            List<ProdTargetArea> areaList = prodTargetAreaAtomSV.queryByAreaCode(
                    tenantId,prodId,Integer.parseInt(areaVo.getAreaCode()),false);
            if (!CollectionUtil.isEmpty(areaList))
                areaInfo.setOwn(true);
            areaInfoList.add(areaInfo);
        }
        return areaInfoList;
    }

    /**
     * 获取销售商品属性值图片
     * @return
     */
    private OtherSetOfProduct getProdPicOfAttrVal(String tenantId,Product product,OtherSetOfProduct otherSet){
        Map<String,List<ProdPicInfo>> attrValPicMap = new HashMap<>();
        List<ProdAttrValInfo> valInfoList = new ArrayList<>();
        //查询运行上传图片的属性
        List<ProdCatAttr> catAttrList = prodCatAttrAtomSV.queryAttrOfPicByIdAndSale(
                tenantId,product.getProductCatId());
        for (ProdCatAttr catAttr:catAttrList){
            //查询属性对应的SKU属性值
            List<String> attrValIds = skuAttrAtomSV.queryAttrValIdByProdIdAndAttrId(
                    tenantId,product.getProdId(),catAttr.getAttrId());
            for (String attrValId:attrValIds){
                //查询属性值
                ProdAttrvalueDef attrvalueDef = attrValDefAtomSV.selectById(tenantId,attrValId);
                if (attrvalueDef==null){
                    throw new SystemException("","未找到对应的属性值信息,租户:"+tenantId+",属性值id:"+attrValId);
                }
                //查询属性值对应图片
                List<ProdPicture> pictureList = prodPictureAtomSV.queryProdIdAndAttrVal(product.getProdId(),attrValId);
                ProdAttrValInfo valInfo = new ProdAttrValInfo();
                valInfo.setTenantId(product.getTenantId());
                valInfo.setProductId(product.getProdId());
                valInfo.setAttrVal(attrvalueDef.getAttrValueName());
                valInfo.setAttrValId(attrValId);
                valInfoList.add(valInfo);
                attrValPicMap.put(valInfo.getAttrValId(),getProdPicInfo(pictureList));
            }
        }
        otherSet.setAttrValInfoList(valInfoList);
        otherSet.setAttrValPics(attrValPicMap);
        return otherSet;
    }


    private List<ProdPicInfo> getProdPicInfo(List<ProdPicture> pictureList){
        List<ProdPicInfo> picInfoList = new ArrayList<>();
        for (ProdPicture prodPicture : pictureList){
            ProdPicInfo picInfo = new ProdPicInfo();
            BeanUtils.copyProperties(picInfo,prodPicture);
            picInfoList.add(picInfo);
        }
        return picInfoList;
    }

    /**
     * 更新非关键属性
     */
    private void updateNoKeyAttr(String tenantId,String productId,
                                 Map<Long, List<ProdAttrValInfo>> attrValMap,Long operId){
        //查询原非关键属性
        Iterator<Long> attrIdIterator = attrValMap.keySet().iterator();
        while (attrIdIterator.hasNext()) {
            Long attrId = attrIdIterator.next();
            List<ProdAttr> prodAttrList = prodAttrAtomSV.queryOfProdAndAttr(tenantId, productId, attrId);
            for (ProdAttr prodAttr:prodAttrList){
                //废弃原
                prodAttr.setState(CommonConstants.STATE_INACTIVE);
                prodAttr.setOperId(operId);
                //添加日志
                if (prodAttrAtomSV.updateByProdAttrId(prodAttr)>0){
                    ProdAttrLog prodAttrLog = new ProdAttrLog();
                    BeanUtils.copyProperties(prodAttrLog,prodAttr);
                    prodAttrLogAtomSV.installLog(prodAttrLog);
                }
            }
            List<ProdAttrValInfo> attrValInfoList = attrValMap.get(attrId);
            for (ProdAttrValInfo valInfo:attrValInfoList){
                //添加新
                ProdAttr prodAttr = new ProdAttr();
                prodAttr.setTenantId(tenantId);
                prodAttr.setProdId(productId);
                prodAttr.setAttrId(attrId);
                prodAttr.setAttrvalueDefId(valInfo.getAttrValId());
                prodAttr.setAttrValueName(valInfo.getAttrVal());
                prodAttr.setAttrValueName2(valInfo.getAttrVal2());
                prodAttr.setState(CommonConstants.STATE_ACTIVE);
                prodAttr.setOperId(operId);
                //添加日志
                if (prodAttrAtomSV.installProdAttr(prodAttr)>0){
                    ProdAttrLog prodAttrLog = new ProdAttrLog();
                    BeanUtils.copyProperties(prodAttrLog,prodAttr);
                    prodAttrLogAtomSV.installLog(prodAttrLog);
                }
            }
        }
    }
    /**
     * 更新组织类型受众,暂时包括企业和代理商
     */
    private void updateGroupAudiences(String tenantId,String productId,String userType,
                                      String audiType,List<String> userIdS,Long operId) {
        //将原来受众全部设置为无效
        //设置此类型为无效
        prodAudiencesAtomSV.updateNoUser(tenantId,productId,userType,operId);
        //1.受众为全部不可见
        if (ProductConstants.ProdAudiences.userId.NO_USER.equals(audiType)){
            return;
        }//2.受众为全部可见
        else if(ProductConstants.ProdAudiences.userId.USER_TYPE.equals(audiType)){
            ProdAudiences prodAudiences = new ProdAudiences();
            prodAudiences.setTenantId(tenantId);
            prodAudiences.setProdId(productId);
            prodAudiences.setUserType(userType);
            prodAudiences.setUserId(ProductConstants.ProdAudiences.userId.USER_TYPE);
            prodAudiences.setState(CommonConstants.STATE_ACTIVE);
            prodAudiences.setOperId(operId);
            prodAudiencesAtomSV.installAudiences(prodAudiences);
        }//3.受众为部分可见
        else if(ProductConstants.ProdAudiences.userId.PART_USER.equals(audiType)){
            for (String userId:userIdS){
                ProdAudiences prodAudiences = new ProdAudiences();
                prodAudiences.setTenantId(tenantId);
                prodAudiences.setProdId(productId);
                prodAudiences.setUserType(userType);
                prodAudiences.setUserId(userId);
                prodAudiences.setState(CommonConstants.STATE_ACTIVE);
                prodAudiences.setOperId(operId);
                prodAudiencesAtomSV.installAudiences(prodAudiences);
            }
        }
    }

    /**
     * 更新商品目标地域
     */
    private void updateTargetArea(String tenantId,String prodId,
                                  String isSaleNationwide,List<Long> provCodes,Long operId){
        //如果为全国范围,则使原目标地域失效
        prodTargetAreaAtomSV.discardForProduct(tenantId,prodId,operId);
        if (ProductConstants.Product.IsSaleNationwide.YES.equals(isSaleNationwide)){
            return;
        }
        //部分地域
        for (Long provCode:provCodes){
            ProdTargetArea targetArea = new ProdTargetArea();
            targetArea.setTenantId(tenantId);
            targetArea.setProdId(prodId);
            targetArea.setProvCode(provCode.intValue());
            targetArea.setState(CommonConstants.STATE_ACTIVE);
            targetArea.setOperId(operId);
            prodTargetAreaAtomSV.installArea(targetArea);
        }
    }

    /**
     * 更新属性值图片信息
     */
    private void updateProdPic(String tenantId,String prodId,String attrValId,List<ProdPicInfo> picInfoList,Long operId){
        //废弃该属性值下的原图片信息
        List<ProdPicture> pictureList = prodPictureAtomSV.queryProdIdAndAttrVal(prodId,attrValId);
        prodPictureAtomSV.discardPic(prodId,attrValId,operId);
        //添加日志
        for (ProdPicture prodPicture:pictureList){
            ProdPictureLog pictureLog = new ProdPictureLog();
            BeanUtils.copyProperties(pictureLog,prodPicture);
            pictureLog.setOperId(operId);
            pictureLog.setState(CommonConstants.STATE_INACTIVE);
            prodPictureLogAtomSV.installLog(pictureLog);
        }
        //添加新图片
        for (ProdPicInfo picInfo:picInfoList){
            ProdPicture prodPicture = new ProdPicture();
            BeanUtils.copyProperties(prodPicture,picInfo);
            //属性值为0,表示为商品属性图片
            prodPicture.setPicUses("0".equals(attrValId)?
                    ProductConstants.ProdPicture.PicType.PRODUCT:ProductConstants.ProdPicture.PicType.ATTR);
            prodPicture.setOperId(operId);
            prodPicture.setState(CommonConstants.STATE_ACTIVE);
            if (prodPictureAtomSV.installPic(prodPicture)>0){
                ProdPictureLog pictureLog = new ProdPictureLog();
                BeanUtils.copyProperties(pictureLog,prodPicture);
                prodPictureLogAtomSV.installLog(pictureLog);
            }
        }
    }

	@Override
	public PageInfoResponse<ProductStorageSale> queryStorageProdByState(
			ProductStorageSaleParam productStorageSaleParam) {
		String tenantId = productStorageSaleParam.getTenantId();
        //查询所有符合条件商品
        PageInfo<Product> productPage = productAtomSV.selectStorProdByState(productStorageSaleParam);
        List<ProductStorageSale> prodStorList = new ArrayList<>();
        for (Product product:productPage.getResult()){
        	ProductStorageSale productStorageSale = new ProductStorageSale();
            BeanUtils.copyProperties(productStorageSale,product);
            //设置类目名称
            ProductCat cat = catDefAtomSV.selectById(tenantId,product.getProductCatId());
            if (cat!=null)
                productStorageSale.setProductCatName(cat.getProductCatName());
            //查询主预览图
            ProdPicture prodPicture = prodPictureAtomSV.queryMainOfProd(product.getProdId());
            if (prodPicture!=null){
                productStorageSale.setProPictureId(prodPicture.getProPictureId());
                productStorageSale.setVfsId(prodPicture.getVfsId());
                productStorageSale.setPicType(prodPicture.getPicType());
            }
            prodStorList.add(productStorageSale);
        }
        PageInfoResponse<ProductStorageSale> response = new PageInfoResponse<>();
        BeanUtils.copyProperties(response,productPage);
        response.setResult(prodStorList);
        return response;
	}
	
	/**
	 *查询商品目标地域 
	 */
	public PageInfoResponse<TargetAreaForProd> searchProdTargetArea(ProductEditQueryReq productEditParam){
		String tenantId = productEditParam.getTenantId();
		//查询所有符合条件商品
        PageInfo<Product> productPage = productAtomSV.selectPageForEdit(productEditParam);
        List<TargetAreaForProd> targetAreaList = new ArrayList<>();
        for (Product product:productPage.getResult()){
            TargetAreaForProd targetAreaForProd = new TargetAreaForProd();
            BeanUtils.copyProperties(targetAreaForProd,product);
            //设置类目名称
            ProductCat cat = catDefAtomSV.selectById(tenantId,product.getProductCatId());
            if (cat!=null)
            	targetAreaForProd.setProductCatName(cat.getProductCatName());
            //查询主预览图
            ProdPicture prodPicture = prodPictureAtomSV.queryMainOfProd(product.getProdId());
            if (prodPicture!=null){
            	targetAreaForProd.setProPictureId(prodPicture.getProPictureId());
            	targetAreaForProd.setVfsId(prodPicture.getVfsId());
            	targetAreaForProd.setPicType(prodPicture.getPicType());
            }
            
            //查询目标地域
            String productId = product.getProdId();
            String tenant = product.getTenantId();
            //查询目标地域
    		List<ProdTargetArea> prodTargetAreaList = prodTargetAreaAtomSV.searchProdTargetArea(tenant, productId);
    		if (prodTargetAreaList!=null) {
    			ArrayList<TargetArea> areaList = new ArrayList<>();
    			for (ProdTargetArea prodTargetArea : prodTargetAreaList) {
    				TargetArea targetArea = new TargetArea();
    				BeanUtils.copyProperties(targetArea, prodTargetArea);
    				areaList.add(targetArea);
				}
    			targetAreaForProd.setTargetArea(areaList);
			}
    		targetAreaList.add(targetAreaForProd);
        }
        PageInfoResponse<TargetAreaForProd> response = new PageInfoResponse<>();
        BeanUtils.copyProperties(response,productPage);
        response.setResult(targetAreaList);
        return response;
    }

	/**
     * 查询在商品 -- 按上架时间排序
     * @param queryReq
     * @return
     */
	@Override
	public PageInfoResponse<ProductEditUp> queryInSale(ProductQueryInfo queryReq) {

        String tenantId = queryReq.getTenantId();
        //查询所有符合条件商品
        PageInfo<Product> productPage = productAtomSV.selectPageForInsale(queryReq);
        List<ProductEditUp> editUpList = new ArrayList<>();
        for (Product product:productPage.getResult()){
            ProductEditUp productEditUp = new ProductEditUp();
            BeanUtils.copyProperties(productEditUp,product);
            //设置类目名称
            ProductCat cat = catDefAtomSV.selectById(tenantId,product.getProductCatId());
            if (cat!=null)
                productEditUp.setProductCatName(cat.getProductCatName());
            //查询主预览图
            ProdPicture prodPicture = prodPictureAtomSV.queryMainOfProd(product.getProdId());
            if (prodPicture!=null){
                productEditUp.setProPictureId(prodPicture.getProPictureId());
                productEditUp.setVfsId(prodPicture.getVfsId());
                productEditUp.setPicType(prodPicture.getPicType());
            }
             editUpList.add(productEditUp);
        }
        
        PageInfoResponse<ProductEditUp> response = new PageInfoResponse<>();
        BeanUtils.copyProperties(response,productPage);
        response.setResult(editUpList);
        return response;
    
	}
	
}
