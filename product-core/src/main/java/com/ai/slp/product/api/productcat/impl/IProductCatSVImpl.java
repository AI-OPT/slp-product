package com.ai.slp.product.api.productcat.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.slp.product.api.common.param.PageInfoForRes;
import com.ai.slp.product.api.productcat.param.ProdCatAttrDef;
import com.ai.slp.product.api.productcat.interfaces.IProductCatSV;
import com.ai.slp.product.api.productcat.param.*;
import com.ai.slp.product.service.business.interfaces.IProductCatBusiSV;
import com.ai.slp.product.util.CommonCheckUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jackieliu on 16/4/28.
 */
@Service
@Component
public class IProductCatSVImpl implements IProductCatSV {
    @Autowired
    IProductCatBusiSV productCatBusiSV;

    @Override
    public PageInfoForRes<ProductCatInfo> queryProductCat(ProductCatPageQuery pageQuery) throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(pageQuery.getTenantId(),"");
        PageInfoForRes<ProductCatInfo> catInfoPageInfoWrapper = productCatBusiSV.queryProductCat(pageQuery);
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setResultCode("");
        responseHeader.setIsSuccess(true);
        catInfoPageInfoWrapper.setResponseHeader(responseHeader);
        return catInfoPageInfoWrapper;
    }

    @Override
    public BaseResponse createProductCat(List<ProductCatParam> pcpList)
            throws BusinessException, SystemException {
        if (pcpList!=null && pcpList.size()>0){
            productCatBusiSV.addCatList(pcpList);
        }
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setResultCode("");
        responseHeader.setIsSuccess(true);
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    @Override
    public BaseResponse updateProductCat(ProductCatParam productCatParam)
            throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(productCatParam.getTenantId(),"");
        productCatBusiSV.updateByCatId(productCatParam);
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setResultCode("");
        responseHeader.setIsSuccess(true);
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    @Override
    public BaseResponse deleteProductCat(ProductCatUniqueReq catUniqueReq) throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(catUniqueReq.getTenantId(),"");
        productCatBusiSV.deleteByCatId(catUniqueReq.getTenantId(),catUniqueReq.getProductCatId(),
                catUniqueReq.getOperId());
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setResultCode("");
        responseHeader.setIsSuccess(true);
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    @Override
    public BaseResponse addAttrForCatAndType(ProdCatAttrAddParam addCatAttrParam)
            throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(addCatAttrParam.getTenantId(),"");
        //检查要添加内容是否为空
        if (addCatAttrParam.getAttrAndVal().isEmpty()){
            throw new BusinessException("","添加属性相关信息为空,不执行添加操作");
        }
        productCatBusiSV.addAttrAndValOfAttrType(addCatAttrParam);
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setResultCode("");
        responseHeader.setIsSuccess(true);
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    @Override
    public BaseResponse deleteProductCatAttrVal(ProdCatAttrVal productAttrValParam)
            throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(productAttrValParam.getTenantId(),"");
        productCatBusiSV.deleteAttrOrVa(productAttrValParam);
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setResultCode("");
        responseHeader.setIsSuccess(true);
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    @Override
    public ProductCatInfo queryByCatId(ProductCatUniqueReq catUniqueReq)
            throws BusinessException, SystemException {
        String tenantId = catUniqueReq.getTenantId(),catId = catUniqueReq.getProductCatId();
        CommonCheckUtils.checkTenantId(tenantId,"");
        if (StringUtils.isBlank(catId))
            throw new BusinessException("","类目标识不能为空");
        return productCatBusiSV.queryByCatId(tenantId,catId);
    }

    /**
     * 查询类目的类目链
     *
     * @param catUniqueReq
     * @return 从当前类目一直到根类目的类目链
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode PRODUCT_CAT_0110
     */
    @Override
    public List<ProductCatInfo> queryLinkOfCatById(ProductCatUniqueReq catUniqueReq) throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(catUniqueReq.getTenantId(),"");
        return productCatBusiSV.queryLinkOfCatById(catUniqueReq.getTenantId(),catUniqueReq.getProductCatId());
    }

    /**
     * 查询指定类目下某种类型的属性集合<br>
     * 类型分为:关键属性,销售属性,非关键属性
     *
     * @param attrQuery 查询类目信息
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode PRODUCT_CAT_0111
     */
    @Override
    public Map<ProdCatAttrDef, List<AttrValInfo>> queryAttrByCatAndType(AttrQueryForCat attrQuery)
            throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(attrQuery.getTenantId(),"");
        return productCatBusiSV.querAttrOfCatByIdAndType(
                attrQuery.getTenantId(),attrQuery.getProductCatId(),attrQuery.getAttrType());
    }

    /**
     * 根据名称或首字母查询
     *
     * @param catQuery 类目查询信息
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode PRODUCT_CAT_0112
     */
    @Override
    public List<ProductCatInfo> queryCatByNameOrFirst(ProductCatQuery catQuery) throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(catQuery.getTenantId(),"");
        return productCatBusiSV.queryByNameOrFirst(catQuery);
    }

    /**
     * 更新类目属性信息
     *
     * @param updateParams 类目属性和属性值信息
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode PRODUCT_CAT_0113
     */
    @Override
    public BaseResponse updateCatAttrAndVal(List<ProdCatAttrUpdateParam> updateParams) throws BusinessException, SystemException {
        int successNum = productCatBusiSV.updateCatAttrAndVal(updateParams);
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setResultCode("");
        responseHeader.setIsSuccess(true);
        responseHeader.setResultMessage("总共["+updateParams.size()+"]条,更新成功["+successNum+"]条");
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    /**
     * 查询指定类目下某种类型的属性标识和属性值标识的集合<br>
     * 类型分为:关键属性,销售属性,非关键属性
     *
     * @param attrQuery
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode PRODUCT_CAT_0105
     */
    @Override
    public Map<Long, Set<String>> queryAttrAndValIdByCatAndType(AttrQueryForCat attrQuery) throws BusinessException, SystemException {
        CommonCheckUtils.checkTenantId(attrQuery.getTenantId(),"");
        return productCatBusiSV.queryAttrAndValIdByCatIdAndType(
                attrQuery.getTenantId(),attrQuery.getProductCatId(),attrQuery.getAttrType());
    }

}
