package com.ai.slp.product.constants;

/**
 * Created by jackieliu on 16/5/5.
 */
public final class ProductConstants {
    /**
     * 固定有效期类型
     */
    public static final String ACTIVE_TYPE_FIXED = "1";

    /**
     * 灵活有效期类型
     */
    public static final String ACTIVE_TYPE_CHANGE = "2";

    /**
     * 新增状态
     */
    public static final String STATE_ADD = "0";
    /**
     * 未编辑
     */
    public static final String STATE_UNEDIT = "1";
    /**
     * 已编辑状态
     */
    public static final String STATE_EDITED = "2";
    /**
     * 审核中
     */
    public static final String STATE_VERIFYING = "3";
    /**
     * 审核拒绝
     */
    public static final String STATE_REJECT = "4";
    /**
     * 销售中
     */
    public static final String STATE_IN_SALE = "5";
    /**
     * 仓库中,(审核通过,手动下架后状态)
     */
    public static final String STATE_IN_STORE = "6";
    /**
     * 售罄下架
     */
    public static final String STATE_SALE_OUT = "61";
    /**
     * 停用下架
     */
    public static final String STATE_STOP = "62";
    /**
     * 废弃
     */
    public static final String STATE_DISCARD = "7";
}
