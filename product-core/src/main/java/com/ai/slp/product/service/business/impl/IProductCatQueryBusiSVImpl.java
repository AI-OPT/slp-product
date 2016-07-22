package com.ai.slp.product.service.business.impl;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.paas.ipaas.util.JSonUtil;
import com.ai.slp.product.api.productcat.param.ProductCatInfo;
import com.ai.slp.product.constants.ErrorCodeConstants;
import com.ai.slp.product.dao.mapper.bo.ProductCat;
import com.ai.slp.product.service.business.interfaces.IProductCatQueryBusiSV;
import com.ai.slp.product.util.IPaasCatUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jackieliu on 16/7/22.
 */
@Service
public class IProductCatQueryBusiSVImpl implements IProductCatQueryBusiSV {
    private static Logger logger = LoggerFactory.getLogger(IProductCatQueryBusiSVImpl.class);
    /**
     * 查询指定标识的类目信息
     *
     * @param tenantId
     * @param catId
     * @return
     */
    @Override
    public ProductCatInfo queryById(String tenantId, String catId) {
        ICacheClient cacheClient = IPaasCatUtils.getCacheClient();
        String catStr = cacheClient.hget(IPaasCatUtils.genMcsCatInfoKey(tenantId),catId);
        if (StringUtils.isBlank(catStr)) {
            logger.error("The cat is null,tenantId={},catId={}",tenantId,catId);
            throw new BusinessException(ErrorCodeConstants.ProductCat.CAT_NO_EXIST,
                    "类目不存在,租户ID:"+tenantId+",类目ID:"+catId);
        }
        ProductCat cat = JSonUtil.fromJSon(catStr,ProductCat.class);
        ProductCatInfo catInfo = new ProductCatInfo();
        BeanUtils.copyProperties(catInfo,cat);
        return catInfo;
    }

    /**
     * 查询类目的类目链
     *
     * @param tenantId
     * @param productCatId
     * @return
     */
    @Override
    public List<ProductCatInfo> queryLinkOfCatById(String tenantId, String productCatId) {
        return null;
    }
}
