package com.ai.slp.product.constants;

/**
 * 商品类目
 *
 * Created by jackieliu on 16/5/1.
 */
public class ProductCatConstants {

    public static final class McsParam{
        /**
         * 缓存类目详细信息前缀
         */
        public static final String CAT_INFO_TAG = "PROD_CAT_INFO";
        /**
         * 缓存类目级别信息前缀
         */
        public static final String CAT_LEVEL_TAG = "PROD_CAT_LEVEL";
        /**
         * 缓存类目子类目信息前缀
         */
        public static final String CAT_CHILD_TAG = "PROD_CAT_CHILD";
        /**
         * 缓存中具有子类目的父类目的信息前缀
         */
        public static final String CAT_PARENT_TAG = "PROD_CAT_PARENT";
    }

    public static final class ProductCat{
        public static final class IsChild{
            /**
             * 有子分类
             */
            public static final String HAS_CHILD = "Y";

            /**
             * 没有子分类
             */
            public static final String NO_CHILD = "N";
        }

        public static final class ParentProductCatId {
            /**
             * 根类目标识
             */
            public static final String ROOT_CAT = "0";
        }
    }

    public static final class ProductCatAttr{
        public static final class AttrType{
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
        public static final class IsPicture{
            public static final String YES = "Y";
            public static final String NO = "N";
        }
    }

}
