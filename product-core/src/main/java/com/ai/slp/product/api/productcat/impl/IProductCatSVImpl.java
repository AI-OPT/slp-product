package com.ai.slp.product.api.productcat.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.*;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.slp.product.api.productcat.interfaces.IProductCatSV;
import com.ai.slp.product.api.productcat.param.*;
import com.ai.slp.product.service.business.interfaces.IProductCatBusiSV;
import com.ai.slp.product.util.CommonUtils;
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
@Service(validation = "true")
@Component
public class IProductCatSVImpl implements IProductCatSV {
    @Autowired
    IProductCatBusiSV productCatBusiSV;

    /**
     * 商品类目分页查询<br>
     * 
     * @param pageQuery
     * @return 商品类目查询条件
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public PageInfoResponse<ProductCatInfo> queryPageProductCat(ProductCatPageQuery pageQuery) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(pageQuery.getTenantId(),"");
        PageInfoResponse<ProductCatInfo> catInfoPageInfoWrapper = productCatBusiSV.queryProductCat(pageQuery);
        CommonUtils.addSuccessResHeader(catInfoPageInfoWrapper,"");
        return catInfoPageInfoWrapper;
    }

	/**
	 * 商品类目批量添加<br>
	 * 
	 * @param pcpList
	 * @return 服务返回基本信息
	 * @throws BusinessException
	 * @throws SystemException
	 * @author lipeng16
	 */
    @Override
    public BaseResponse createProductCat(List<ProductCatParam> pcpList)
            throws BusinessException, SystemException {
        if (!CollectionUtil.isEmpty(pcpList)){
            productCatBusiSV.addCatList(pcpList);
        }
        return CommonUtils.genSuccessResponse("");
    }

	/**
	 * 商品类目修改<br>
	 * 
	 * @param productCatParam
	 * @return 服务返回基本信息
	 * @throws BusinessException
	 * @throws SystemException
	 * @author lipeng16
	 */
    @Override
    public BaseResponse updateProductCat(ProductCatParam productCatParam)
            throws BusinessException, SystemException {
        CommonUtils.checkTenantId(productCatParam.getTenantId(),"");
        productCatBusiSV.updateByCatId(productCatParam);
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setResultCode(ExceptCodeConstants.Special.SUCCESS);
        responseHeader.setIsSuccess(true);
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

	/**
	 * 商品类目删除<br>
	 * 删除类目时，需要判断是否已经有关联的标准品（包括废弃状态的）<br>
	 * 类目一旦删除，其下包含的子类目一并删除，与选择好的属性、属性值解除关联关系<br>
	 * 
	 * @param catUniqueReq
	 * @return 服务返回基本信息
	 * @throws BusinessException
	 * @throws SystemException
	 * @author lipeng16
	 */
    @Override
    public BaseResponse deleteProductCat(ProductCatUniqueReq catUniqueReq) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(catUniqueReq.getTenantId(),"");
        productCatBusiSV.deleteByCatId(catUniqueReq.getTenantId(),catUniqueReq.getProductCatId(),
                catUniqueReq.getOperId());
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setResultCode(ExceptCodeConstants.Special.SUCCESS);
        responseHeader.setIsSuccess(true);
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
     */
    @Override
    public BaseMapResponse<Long, Set<String>> queryAttrAndValIdByCatAndType(AttrQueryForCat attrQuery) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(attrQuery.getTenantId(),"");
        Map<Long, Set<String>> map= productCatBusiSV.queryAttrAndValIdByCatIdAndType(
                attrQuery.getTenantId(),attrQuery.getProductCatId(),attrQuery.getAttrType());
        BaseMapResponse<Long, Set<String>> attrMapRes = new BaseMapResponse();
        attrMapRes.setResult(map);
        attrMapRes.setResponseHeader(new ResponseHeader(true, ExceptCodeConstants.Special.SUCCESS,"OK"));
        return attrMapRes;
    }


	/**
	 * 依据商品类目和属性类型添加类目属性<br>
	 * 类型分为:关键属性,销售属性,非关键属性
	 * 
	 * @param addCatAttrParam
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author lipeng16
	 */
    @Override
    public BaseResponse addAttrForCatAndType(ProdCatAttrAddParam addCatAttrParam)
            throws BusinessException, SystemException {
        CommonUtils.checkTenantId(addCatAttrParam.getTenantId(),"");
        //检查要添加内容是否为空
        if (addCatAttrParam.getAttrAndVal().isEmpty()){
            throw new BusinessException("","添加属性相关信息为空,不执行添加操作");
        }
        productCatBusiSV.addAttrAndValOfAttrType(addCatAttrParam);
        return CommonUtils.genSuccessResponse("");
    }

    /**
     * 删除商品类目属性或属性值
     * 
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author lipeng16
     */
    @Override
    public BaseResponse deleteProductCatAttrOrVal(ProdCatAttrVal productAttrValParam)
            throws BusinessException, SystemException {
        CommonUtils.checkTenantId(productAttrValParam.getTenantId(),"");
        productCatBusiSV.deleteAttrOrVa(productAttrValParam);
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setResultCode(ExceptCodeConstants.Special.SUCCESS);
        responseHeader.setIsSuccess(true);
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

	/**
	 * 查询指定标识的类目信息
	 *
	 * @param catUniqueReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author liutong5
	 */
    @Override
    public ProductCatInfo queryByCatId(ProductCatUniqueReq catUniqueReq)
            throws BusinessException, SystemException {
        String tenantId = catUniqueReq.getTenantId(),catId = catUniqueReq.getProductCatId();
        CommonUtils.checkTenantId(tenantId,"");
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
     */
    @Override
    public List<ProductCatInfo> queryLinkOfCatById(ProductCatUniqueReq catUniqueReq) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(catUniqueReq.getTenantId(),"");
        List<ProductCatInfo> list = productCatBusiSV.queryLinkOfCatById(catUniqueReq.getTenantId(),catUniqueReq.getProductCatId());
        return list;
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
     */
    @Override
    public BaseListResponse<ProdCatAttrDef> queryAttrByCatAndType(AttrQueryForCat attrQuery)
            throws BusinessException, SystemException {
        CommonUtils.checkTenantId(attrQuery.getTenantId(),"");
        List<ProdCatAttrDef> attrList = productCatBusiSV.queryAttrOfCatByIdAndType(
                attrQuery.getTenantId(),attrQuery.getProductCatId(),attrQuery.getAttrType());
        BaseListResponse<ProdCatAttrDef> listResponse = new BaseListResponse<>();
        listResponse.setResult(attrList);
        CommonUtils.addSuccessResHeader(listResponse,"");
        return listResponse;
    }

    /**
     * 根据名称或首字母查询
     *
     * @param catQuery 类目查询信息
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     */
    @Override
    public List<ProdCatInfo> queryCatByNameOrFirst(ProductCatQuery catQuery) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(catQuery.getTenantId(),"");
//        ListForRes<ProdCatInfo> catInfoList = new ListForRes<ProdCatInfo>(productCatBusiSV.queryByNameOrFirst(catQuery));
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
     */
    @Override
    public BaseResponse updateCatAttrAndVal(List<ProdCatAttrUpdateParam> updateParams) throws BusinessException, SystemException {
        int successNum = productCatBusiSV.updateCatAttrAndVal(updateParams);
        return CommonUtils.genSuccessResponse("总共["+updateParams.size()+"]条,更新成功["+successNum+"]条");
    }

  
}
