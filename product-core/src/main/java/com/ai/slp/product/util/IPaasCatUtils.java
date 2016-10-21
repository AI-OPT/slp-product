package com.ai.slp.product.util;

import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.slp.product.constants.IPaasConstants;
import com.ai.slp.product.constants.ProductCatConstants;

/**
 * ipaas中类目相关工具类
 * Created by jackieliu on 16/7/21.
 */
public final class IPaasCatUtils {

    private IPaasCatUtils() {
    }

    /**
     * 缓存key分割符
     */
    private static final String CACHE_KEY_SEPARATE = ":";

    public static ICacheClient getCacheClient(){
        return  MCSClientFactory.getCacheClient(IPaasConstants.McsParams.CAT_MCS);
    }

    /**
     * 产生类目详细信息的缓存key
     * A
     * @param tenantId
     * @return
     */
    public static String genMcsCatInfoKey(String tenantId){
        return ProductCatConstants.McsParam.CAT_INFO_TAG
                +CACHE_KEY_SEPARATE+tenantId;
    }

    /**
     * 产生类目级别的缓存key
     * B
     * @param tenantId
     * @return
     */
    public static String genMcsCatLevelKey(String tenantId){
        return ProductCatConstants.McsParam.CAT_LEVEL_TAG
                +CACHE_KEY_SEPARATE+tenantId;
    }

    /**
     * 类目子级类目的缓存KEY
     * C
     * @param tenantId
     * @param catId
     * @return
     */
    public static String genMcsCatChildKey(String tenantId,String catId){
        return ProductCatConstants.McsParam.CAT_CHILD_TAG
                +CACHE_KEY_SEPARATE+tenantId+CACHE_KEY_SEPARATE+catId;
    }

    /**
     * 租户下非叶子类目的缓存KEY
     * D
     * @param tenantId
     * @return
     */
    public static String genMcsCatParentKey(String tenantId){
        return ProductCatConstants.McsParam.CAT_PARENT_TAG
                +CACHE_KEY_SEPARATE+tenantId;
    }
}
