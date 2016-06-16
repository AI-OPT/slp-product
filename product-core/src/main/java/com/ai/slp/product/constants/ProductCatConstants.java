package com.ai.slp.product.constants;

/**
 * 商品类目
 *
 * Created by jackieliu on 16/5/1.
 */
public final class ProductCatConstants {

    public final class ProductCat{
        public final class IsChild{
            /**
             * 有子分类
             */
            public static final String HAS_CHILD = "Y";

            /**
             * 没有子分类
             */
            public static final String NO_CHILD = "N";
        }

        public final class ParentProductCatId {
            /**
             * 根类目标识
             */
            public static final String ROOT_CAT = "0";
        }
    }

    public final class ProductCatAttr{
        public final class AttrType{
            /**
             * 关键属性类型
             */
            public static final String ATTR_TYPE_KEY = "1";
            /**
             * 销售属性
             */
            public static final String ATTR_TYPE_SALE = "2";
            /**
             * 非关键属性
             */
            public static final String ATTR_TYPE_NONKEY = "3";
        }
        public final class IsPicture{
            public static final String YES = "Y";
            public static final String NO = "N";
        }
    }

}
