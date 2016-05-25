package com.ai.slp.product.constants;

/**
 * 库存和库存组的常量
 *
 * Created by jackieliu on 16/4/29.
 */
public final class StorageConstants {

    public final class IPass{
        public final class MdsParams{
            /**
             * 库存所用mds
             */
            public static final String STORAGE_MDS = "com.ai.opt.slp.product.storage";
            /**
             * 库存组前缀
             */
            public static final String GROUP_TAG = "GROUP_TAG";
            /**
             * 库存优先级价格前缀
             */
            public static final String SALE_PRICE_TAG = "SALE_PRICE";
            /**
             * 库存可用量
             */
            public static final String USABLE_NUM_TAG = "USABLE_NUM";
            /**
             * 促销库存
             */
            public static final String PROMOTION_STORAGE_TAG = "PROMOT_STO";
            /**
             * sku单品库存
             */
            public static final String SKU_STORAGE_TAG = "SKU_STO";
        }
    }

    public final class StorageGroup{
        public final class State{
            /**
             * 启用
             */
            public static final String ACTIVE = "1";
            /**
             * 自动启用
             */
            public static final String AUTO_ACTIVE = "11";
            /**
             * 停用
             */
            public static final String STOP = "2";
            /**
             * 自动停用
             */
            public static final String AUTO_STOP = "21";
            /**
             * 售罄停用
             */
            public static final String SOLD_OUT_STOP = "22";
            /**
             * 废弃
             */
            public static final String DISCARD = "3";
            /**
             * 自动废弃
             */
            public static final String AUTO_DISCARD = "31";
        }
    }

    public final class Storage{
        public final class State{
            /**
             * 启用
             */
            public static final String ACTIVE = "1";
            /**
             * 自动启用
             */
            public static final String AUTO_ACTIVE = "11";
            /**
             * 停用
             */
            public static final String STOP = "2";
            /**
             * 自动停用
             */
            public static final String AUTO_STOP = "21";
            /**
             * 废弃
             */
            public static final String DISCARD = "3";
            /**
             * 自动废弃
             */
            public static final String AUTO_DISCARD = "31";
        }
    }

    public final class SkuStorage{
        public final class State{
            /**
             * 启用
             */
            public static final String ACTIVE = "1";
            /**
             * 自动停用
             */
            public static final String AUTO_STOP = "21";
            /**
             * 自动废弃
             */
            public static final String AUTO_DISCARD = "31";
        }
    }
}
