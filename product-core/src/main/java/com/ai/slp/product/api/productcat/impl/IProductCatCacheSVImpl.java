package com.ai.slp.product.api.productcat.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseListResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.slp.product.api.productcat.interfaces.IProductCatCacheSV;
import com.ai.slp.product.api.productcat.param.ProdCatLevelParam;
import com.ai.slp.product.api.productcat.param.ProductCatInfo;
import com.ai.slp.product.api.productcat.param.ProductCatUniqueReq;
import com.ai.slp.product.constants.ErrorCodeConstants;
import com.ai.slp.product.service.business.interfaces.IProductCatQueryBusiSV;
import com.ai.slp.product.util.CommonUtils;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * Created by jackieliu on 16/7/21.
 */
@Service(validation = "true")
@Component
public class IProductCatCacheSVImpl implements IProductCatCacheSV {
    private static final Logger logger = LoggerFactory.getLogger(IProductCatCacheSVImpl.class);
    @Autowired
    IProductCatQueryBusiSV productCatQueryBusiSV;
    /**
     * 查询指定标识的类目信息
     *
     * @param catUniqueReq
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode PROD_CAT_CACHE_0100
     * @RestRelativeURL prodCatCache/cat
     */
    @Override
    public ProductCatInfo queryByCatId(ProductCatUniqueReq catUniqueReq) throws BusinessException, SystemException {
        String tenantId= catUniqueReq.getTenantId(),
                catId = catUniqueReq.getProductCatId();
        CommonUtils.checkTenantId(tenantId,ErrorCodeConstants.TENANT_ID_NULL);
        ProductCatInfo catInfo = productCatQueryBusiSV.queryById(tenantId,catId);
        if (catInfo == null){
            logger.error("The cat is null,tenantId={},catId={}",tenantId,catId);
            throw new BusinessException(ErrorCodeConstants.ProductCat.CAT_NO_EXIST,
                    "类目不存在,租户ID:"+tenantId+",类目ID:"+catId);
        }
        catInfo.setResponseHeader(new ResponseHeader(true, ExceptCodeConstants.Special.SUCCESS, "OK"));
        return catInfo;
    }

    /**
     * 查询类目的类目链
     *
     * @param catUniqueReq
     * @return 从当前类目一直到根类目的类目链
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode PROD_CAT_CACHE_0101
     * @RestRelativeURL prodCatCache/linkCat
     */
    @Override
    public BaseListResponse<ProductCatInfo> queryLinkOfCatById(ProductCatUniqueReq catUniqueReq) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(catUniqueReq.getTenantId(),ErrorCodeConstants.TENANT_ID_NULL);
        List<ProductCatInfo> catInfoList = productCatQueryBusiSV.queryLinkOfCatById(
                catUniqueReq.getTenantId(),catUniqueReq.getProductCatId());
        BaseListResponse<ProductCatInfo> catResponse = new BaseListResponse<>();
        catResponse.setResult(catInfoList);
        catResponse.setResponseHeader(new ResponseHeader(true,ExceptCodeConstants.Special.SUCCESS,"OK"));
        return catResponse;
    }

    /**
     * 查询类目的下级类目集合
     *
     * @param catUniqueReq
     * @return 当前级别的下级类目集合信息
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode PROD_CAT_CACHE_0102
     * @RestRelativeURL prodCatCache/childCat
     */
    @Override
    public BaseListResponse<ProductCatInfo> queryChildOfCatById(ProductCatUniqueReq catUniqueReq) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(catUniqueReq.getTenantId(),ErrorCodeConstants.TENANT_ID_NULL);
        List<ProductCatInfo> catInfoList = productCatQueryBusiSV.queryChileOfCatById(
                catUniqueReq.getTenantId(),catUniqueReq.getProductCatId());
        BaseListResponse<ProductCatInfo> catResponse = new BaseListResponse<>();
        catResponse.setResult(catInfoList);
        catResponse.setResponseHeader(new ResponseHeader(true,ExceptCodeConstants.Special.SUCCESS,"OK"));
        return catResponse;
    }

    /**
     * 查询指定级别的类目集合
     *
     * @param levelParam
     * @return 指定级别的类目集合
     * @throws BusinessException
     * @throws SystemException
     * @author liutong5
     * @ApiCode PROD_CAT_CACHE_0103
     * @RestRelativeURL prodCatCache/levelCat
     */
    @Override
    public BaseListResponse<ProductCatInfo> queryByLevel(ProdCatLevelParam levelParam) throws BusinessException, SystemException {
        CommonUtils.checkTenantId(levelParam.getTenantId(),ErrorCodeConstants.TENANT_ID_NULL);
        List<ProductCatInfo> catInfoList = productCatQueryBusiSV.queryByLevel(
                levelParam.getTenantId(),levelParam.getCatLevel());
        BaseListResponse<ProductCatInfo> catResponse = new BaseListResponse<>();
        catResponse.setResult(catInfoList);
        catResponse.setResponseHeader(new ResponseHeader(true,ExceptCodeConstants.Special.SUCCESS,"OK"));
        return catResponse;
    }
}
