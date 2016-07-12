package com.ai.slp.product.service.business.impl;

import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.common.api.area.interfaces.IGnAreaQuerySV;
import com.ai.slp.common.api.area.param.GnAreaVo;
import com.ai.slp.product.api.product.param.*;
import com.ai.slp.product.constants.CommonSatesConstants;
import com.ai.slp.product.constants.ErrorCodeConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDef;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttr;
import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.dao.mapper.bo.product.*;
import com.ai.slp.product.dao.mapper.interfaces.product.ProdAttrMapper;
import com.ai.slp.product.service.atom.interfaces.IProdAttrValDefAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdCatDefAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.*;
import com.ai.slp.product.service.business.interfaces.IProductBusiSV;
import com.ai.slp.product.service.business.interfaces.IProductManagerBsuiSV;
import com.ai.slp.product.util.DateUtils;
import com.ai.slp.user.api.ucuser.intefaces.IUcUserSV;
import com.ai.slp.user.api.ucuser.param.SearchUserRequest;
import com.ai.slp.user.api.ucuser.param.SearchUserResponse;
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
public class ProductManagerBsuiSVImpl implements IProductManagerBsuiSV {
    private static Logger logger = LoggerFactory.getLogger(ProductManagerBsuiSVImpl.class);
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
    ProdAttrMapper prodAttrMapper;
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
     * 查询商品的受众信息
     *
     * @param tenantId
     * @param prodId
     * @return
     */
    @Override
    public OtherSetOfProduct queryOtherSetOfProd(String tenantId, String prodId) {
        Product product = productAtomSV.selectByProductId(tenantId,prodId);
        if (product==null){
            throw new SystemException(ErrorCodeConstants.Product.PRODUCT_NO_EXIST,
                    "未查询到指定商品,租户ID:"+tenantId+",销售商品标示:"+prodId);
        }
        OtherSetOfProduct otherSet = new OtherSetOfProduct();
        //查询个人
        List<ProdAudiences> boList = prodAudiencesAtomSV.queryByUserType(
                tenantId, prodId,ProductConstants.ProdAudiences.userType.PERSON,null,false);
        if (!CollectionUtil.isEmpty(boList)){
            ProdAudiencesInfo audiencesInfo = new ProdAudiencesInfo();
            BeanUtils.copyProperties(audiencesInfo,boList.get(0));
            IUcUserSV ucUserSV = DubboConsumerFactory.getService("iUcUserSV");
            SearchUserRequest userRequest = new SearchUserRequest();
            userRequest.setTenantId(tenantId);
            userRequest.setUserId(audiencesInfo.getUserId());
            SearchUserResponse userResponse = ucUserSV.queryBaseInfo(userRequest);
            if (userResponse!=null && userResponse.getResponseHeader().isSuccess()){
                audiencesInfo.setUserName(userResponse.getUserNickname());
            }
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

    @Override
    public void updateProdEdit(ProductInfoForUpdate productInfo) {
        String tenantId = productInfo.getTenantId(),
                productId = productInfo.getProdId();
        Product product = productAtomSV.selectByProductId(tenantId,productId);
        if (product == null){
            logger.warn("未找到对应销售商品,租户ID:{},商品标识:{}",tenantId,productId);
            throw new SystemException(ErrorCodeConstants.Product.PRODUCT_NO_EXIST,
                    "未找到对应商品信息,租户ID:"+tenantId+",商品标识:"+productId);
        }else if (!editStatus.contains(product.getState())){
            throw new SystemException("","商品没有处于可编辑状态,不允许编辑更新.");
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
                    ProductConstants.ProdAudiences.userType.PERSON, null, false);
            //为空,且全部可见
            if (CollectionUtil.isEmpty(personAudiList)) {
                ProdAudiences prodAudiences = new ProdAudiences();
                prodAudiences.setTenantId(tenantId);
                prodAudiences.setProdId(productId);
                prodAudiences.setUserId(perAudi);
                prodAudiences.setOperId(operId);
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
        //目前设置为仓库中
        product.setState(ProductConstants.Product.State.IN_STORE);
        //添加日志
        if (productAtomSV.updateById(product)>0){
            ProductLog log = new ProductLog();
            BeanUtils.copyProperties(log,product);
            productLogAtomSV.install(log);
        }
        //进行上架
        productBusiSV.changeToInSale(tenantId,productId,operId);
    }

    private Map<String,ProdAudiencesInfo> getAudiencesInfo(String tenantId,String prodId,String userType){
        List<ProdAudiences> boList = prodAudiencesAtomSV.queryByUserType(
                tenantId,prodId, userType,null,false);
        Map<String,ProdAudiencesInfo> audiencesMap = new HashMap<>();
        IUcUserSV ucUserSV = DubboConsumerFactory.getService("iUcUserSV");
        for (ProdAudiences audiences:boList){
            ProdAudiencesInfo audiencesInfo = new ProdAudiencesInfo();
            BeanUtils.copyProperties(audiencesInfo,audiences);
            SearchUserRequest userRequest = new SearchUserRequest();
            userRequest.setTenantId(tenantId);
            userRequest.setUserId(audiencesInfo.getUserId());
            SearchUserResponse userResponse = ucUserSV.queryBaseInfo(userRequest);
            if (userResponse!=null && userResponse.getResponseHeader().isSuccess()){
                audiencesInfo.setUserName(userResponse.getUserLoginName());
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
                prodAttr.setState(CommonSatesConstants.STATE_INACTIVE);
                prodAttr.setOperId(operId);
                prodAttr.setOperTime(DateUtils.currTimeStamp());
                //添加日志
                if (prodAttrMapper.updateByPrimaryKey(prodAttr)>0){
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
                prodAttr.setState(CommonSatesConstants.STATE_ACTIVE);
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
            prodAudiences.setState(CommonSatesConstants.STATE_ACTIVE);
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
                prodAudiences.setState(CommonSatesConstants.STATE_ACTIVE);
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
            targetArea.setState(CommonSatesConstants.STATE_ACTIVE);
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
            pictureLog.setState(CommonSatesConstants.STATE_INACTIVE);
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
            prodPicture.setState(CommonSatesConstants.STATE_ACTIVE);
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
}
