package com.ai.slp.product.util;

import com.ai.opt.base.exception.BusinessException;
import com.ai.slp.product.constants.ErrorCodeConstants;
import org.apache.commons.lang.StringUtils;

/**
 * 通用检查类
 * Created by jackieliu on 16/5/2.
 */
public class CommonCheckUtils {
    /**
     * 检查租户id是否为空
     */
    public static void checkTenantId(String tenantId,String errCode){
        String nullErrorCode = StringUtils.isBlank(errCode)? ErrorCodeConstants.TENANT_ID_NULL:errCode;
        if (StringUtils.isBlank(tenantId))
            throw new BusinessException(nullErrorCode,"租户标识不能为空");
    }
}
