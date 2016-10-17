package com.ai.slp.product.constants;

/**
 * Created by jackieliu on 16/5/5.
 */
public final class ProductConstants {

    public static final class Product {

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

        public final class UpShelfType {
            /**
             * 立即上架
             */
            public static final String NOW = "1";

            /**
             * 放入仓库中
             */
            public static final String IN_STORE = "2";

            /**
             * 定时上架
             */
            public static final String TIMING_UP = "3";
            /**
             * 预售
             */
            public static final String PRE_SALE = "4";
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

        public final class BasicOrgId {
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

        public final class IsSaleNationwide{
            public static final String YES = "Y";
            public static final String NO = "N";
        }

        public final class RechargeType{
            public static final String DIRECT = "D";
            public static final String CARD = "C";
        }

        public final class auditStatus{
            /**
             * 通过
             */
            public static final String PASS = "1";
            /**
             * 拒绝
             */
            public static final String REJECT = "0";
        }
    }

    public static final class ProdSkuAttr{

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

    public static final class ProdSku{

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

    public static final class ProdPicture{
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

    public static final class ProdAttr{
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

    public static final class ProdAudiences{
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

        public final class userId{
            /**
             * 全部不可见
             */
            public static final String NO_USER = "0";
            /**
             * 部分可见
             */
            public static final String PART_USER = "1";
            /**
             * 此类用户全部可见
             */
            public static final String USER_TYPE = "-1";
            /**
             * 待指定
             */
            public static final String WAIT_SET = "-2";
        }
    }

    public static final class ProdStatusLog{
        public final class PriorityNumber{
            /*
                优先
             */
            public static final short PRIORITY = 1;

            /*
                普通
             */
            public static final short USUAL = 0;
        }
    }

}
