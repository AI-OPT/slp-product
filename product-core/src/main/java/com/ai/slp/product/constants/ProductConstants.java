package com.ai.slp.product.constants;

/**
 * Created by jackieliu on 16/5/5.
 */
public final class ProductConstants {

    public final class Product {

        public final class activeType {
            /**
             * 固定有效期类型
             */
            public static final String FIXED = "1";

            /**
             * 灵活有效期类型
             */
            public static final String CHANGE = "2";
        }

        public final class State{
            /**
             * 新增状态
             */
            public static final String ADD = "0";
            /**
             * 未编辑
             */
            public static final String UNEDIT = "1";
            /**
             * 已编辑状态
             */
            public static final String EDITED = "2";
            /**
             * 审核中
             */
            public static final String VERIFYING = "3";
            /**
             * 审核拒绝
             */
            public static final String REJECT = "4";
            /**
             * 销售中
             */
            public static final String IN_SALE = "5";
            /**
             * 仓库中,(审核通过,手动下架后状态)
             */
            public static final String IN_STORE = "6";
            /**
             * 售罄下架
             */
            public static final String SALE_OUT = "61";
            /**
             * 停用下架
             */
            public static final String STOP = "62";
            /**
             * 废弃
             */
            public static final String DISCARD = "7";
        }
    }


    public final class ProdSkuAttr{

        public final class State {
            /**
             * 有效状态
             */
            public static final String ACTIVE = "1";
            /**
             * 无效状态
             */
            public static final String INACTIVE = "0";
        }
    }

    public final class ProdSku{
        public final class SaleAttrs{
            public static final String ATTR_SPLIT = ";";
            public static final String ATTRVAL_SPLIT = ":";
        }

        public final class State {
            /**
             * 有效状态
             */
            public static final String ACTIVE = "1";
            /**
             * 无效状态
             */
            public static final String INACTIVE = "0";
        }
    }

}
