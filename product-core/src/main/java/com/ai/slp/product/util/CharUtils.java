package com.ai.slp.product.util;

import org.apache.commons.lang.StringUtils;

/**
 * 字符轉換工具類
 * 
 * Created by jackieliu on 16/7/22.
 */
public final class CharUtils {
    private CharUtils() {
    }

    /**
     * 将字母转换为ascii编码
     * @param charStr
     * @return
     */
    public static int charToLowAscii(String charStr){
        if (StringUtils.isBlank(charStr)){
        	return 0;
        }
        charStr = charStr.toLowerCase();
        return (int)charStr.toCharArray()[0];
    }


}
