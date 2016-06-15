package com.ai.slp.product.service.business.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.common.api.area.interfaces.IGnAreaQuerySV;
import com.ai.slp.common.api.area.param.GnAreaVo;
import com.ai.slp.product.api.product.param.*;
import com.ai.slp.product.constants.ErrorCodeConstants;
import com.ai.slp.product.constants.ProductConstants;
import com.ai.slp.product.dao.mapper.bo.ProdAttrvalueDef;
import com.ai.slp.product.dao.mapper.bo.ProdCatAttr;
import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.dao.mapper.bo.product.ProdAudiences;
import com.ai.slp.product.dao.mapper.bo.product.ProdPicture;
import com.ai.slp.product.dao.mapper.bo.product.ProdTargetArea;
import com.ai.slp.product.dao.mapper.bo.product.Product;
import com.ai.slp.product.service.atom.interfaces.IProdAttrValDefAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdCatAttrAtomSV;
import com.ai.slp.product.service.atom.interfaces.IProdCatDefAtomSV;
import com.ai.slp.product.service.atom.interfaces.product.*;
import com.ai.slp.product.service.business.interfaces.IProductManagerBsuiSV;
import com.ai.slp.user.api.ucuser.intefaces.IUcUserSV;
import com.ai.slp.user.api.ucuser.param.SearchUserRequest;
import com.ai.slp.user.api.ucuser.param.SearchUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jackieliu on 16/6/6.
 */
@Service
@Transactional
public class ProductManagerBsuiSVImpl implements IProductManagerBsuiSV {
    @Autowired
    IProdAttrValDefAtomSV attrValDefAtomSV;
    @Autowired
    IProductAtomSV productAtomSV;
    @Autowired
    IProdCatDefAtomSV catDefAtomSV;
    @Autowired
    IProdPictureAtomSV pictureAtomSV;
    @Autowired
    IProdCatAttrAtomSV prodCatAttrAtomSV;
    @Autowired
    IProdAudiencesAtomSV prodAudiencesAtomSV;
    @Autowired
    IProdTargetAreaAtomSV prodTargetAreaAtomSV;
    @Autowired
    IProdSkuAttrAtomSV skuAttrAtomSV;
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
            ProdPicture prodPicture = pictureAtomSV.queryMainOfProd(product.getProdId());
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
            throw new BusinessException(ErrorCodeConstants.Product.PRODUCT_NO_EXIST,
                    "未查询到指定商品,租户ID:"+tenantId+",销售商品标示:"+prodId);
        }
        OtherSetOfProduct otherSet = new OtherSetOfProduct();
        //查询个人
        List<ProdAudiences> boList = prodAudiencesAtomSV.queryByUserType(
                tenantId, prodId,ProductConstants.ProdAudiences.userType.PERSON,null,false);
        if (CollectionUtil.isEmpty(boList)){
            ProdAudiencesInfo audiencesInfo = new ProdAudiencesInfo();
            BeanUtils.copyProperties(audiencesInfo,boList.get(0));
            IUcUserSV ucUserSV = DubboConsumerFactory.getService("IUcUserSV");
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
        List<ProdPicture> picList = pictureAtomSV.queryPicOfProd(prodId);
        otherSet.setProductPics(getProdPicInfo(picList));
        //添加属性值图片
        otherSet.setAttrValPics(getProdPicOfAttrVal(tenantId,product));
        return otherSet;
    }

    private Map<String,ProdAudiencesInfo> getAudiencesInfo(String tenantId,String prodId,String userType){
        List<ProdAudiences> boList = prodAudiencesAtomSV.queryByUserType(
                tenantId,prodId, userType,null,false);
        Map<String,ProdAudiencesInfo> audiencesMap = new HashMap<>();
        IUcUserSV ucUserSV = DubboConsumerFactory.getService("IUcUserSV");
        for (ProdAudiences audiences:boList){
            ProdAudiencesInfo audiencesInfo = new ProdAudiencesInfo();
            BeanUtils.copyProperties(audiencesInfo,audiences);
            SearchUserRequest userRequest = new SearchUserRequest();
            userRequest.setTenantId(tenantId);
            userRequest.setUserId(audiencesInfo.getUserId());
            SearchUserResponse userResponse = ucUserSV.queryBaseInfo(userRequest);
            if (userResponse!=null && userResponse.getResponseHeader().isSuccess()){
                audiencesInfo.setUserName(userResponse.getUserNickname());
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
    private Map<ProdAttrValInfo,List<ProdPicInfo>> getProdPicOfAttrVal(String tenantId,Product product){
        Map<ProdAttrValInfo,List<ProdPicInfo>> attrValPicMap = new HashMap<>();
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
                    throw new BusinessException("","未找到对应的属性值信息,租户:"+tenantId+",属性值id:"+attrValId);
                }
                //查询属性值对应图片
                List<ProdPicture> pictureList = pictureAtomSV.queryProdIdAndAttrVal(product.getProdId(),attrValId);
                ProdAttrValInfo valInfo = new ProdAttrValInfo();
                valInfo.setTenantId(product.getTenantId());
                valInfo.setProductId(product.getProdId());
                valInfo.setAttrVal(attrvalueDef.getAttrValueName());
                valInfo.setAttrValId(attrValId);
                attrValPicMap.put(valInfo,getProdPicInfo(pictureList));
            }
        }
        return attrValPicMap;
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
}
