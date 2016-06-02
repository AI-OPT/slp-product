package com.ai.slp.product.constants;

/**
 * Created by jackieliu on 16/5/5.
 */
public final class ProductConstants {

    public final class Product {

        public final class IsSaleAttr {
            /**
             * 具有销售属性
             */
            public static final String YES = "Y";
            /**
             * 没有销售属性
             */
            public static final String NO = "N";
        }

        public final class ActiveType {
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

        public final class basicOrgId{
            /**
             * 中国移动
             */
            public static final String MOBILE = "10";
            /**
             * 中国联通
             */
            public static final String UNICOM = "12";
            /**
             * 中国电信
             */
            public static final String TELECOM = "11";
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

        public final class IsSaleAttr {
            /**
             * 具有销售属性
             */
            public static final String YES = "Y";
            /**
             * 没有销售属性
             */
            public static final String NO = "N";
        }

        public final class SaleAttrs{
            /**
             * 属性串中属性之间分隔符
             */
            public static final String ATTR_SPLIT = ";";
            /**
             * 属性串中属性和属性值分隔符
             */
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

    public final class ProdPicture{
        public final class PicType{
            /**
             * 商品预览图
             */
            public static final String PRODUCT = "P";
            /**
             * 属性预览图
             */
            public static final String ATTR = "A";
        }
        public final class IsMainPic{
            /**
             * 是
             */
            public static final String YES = "Y";
            /**
             * 否
             */
            public static final String NO = "N";

        }
    }

    public final class ProdAttr{
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

    public final class ProdAudiences{
        public final class userType{
            /**
             * 个人
             */
            public static final String PERSON = "10";
            /**
             * 企业
             */
            public static final String ENTERPRISE = "11";
            /**
             * 代理商
             */
            public static final String AGENT = "12";
            /**
             * 供应商
             */
            public static final String SUPPLIER = "13";
        }
    }
}
