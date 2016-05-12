package com.ai.slp.product.constants;

/**
 * 库存和库存组的常量
 *
 * Created by jackieliu on 16/4/29.
 */
public final class StorageConstants {

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
