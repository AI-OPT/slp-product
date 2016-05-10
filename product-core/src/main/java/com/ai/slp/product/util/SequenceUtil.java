package com.ai.slp.product.util;

import com.ai.opt.sdk.components.sequence.util.SeqUtil;

public final class SequenceUtil {

    private static final String PROD_ATTR_DEF$ATTR_ID$SEQ = "PROD_ATTR_DEF$ATTR_ID$SEQ";

    public static Long createAttrDefId() {
        return SeqUtil.getNewId(PROD_ATTR_DEF$ATTR_ID$SEQ);
    }

}
