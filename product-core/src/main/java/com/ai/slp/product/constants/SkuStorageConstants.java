package com.ai.slp.product.constants;

public class SkuStorageConstants {
	public static final class SkuStorage{
		public static final class State{
			/**
             * 1启用[新增时]
             */
            public static final String ACTIVE = "1";
            /**
             * 21自动停用[库存为0时]
             */
            public static final String AUTO_STOP = "21";
            /**
             * 31自动废弃[sku无效时]
             */
            public static final String AUTO_DISCARD = "31";
		}
	}
}
