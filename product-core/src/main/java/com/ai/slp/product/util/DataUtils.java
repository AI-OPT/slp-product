package com.ai.slp.product.util;

public class DataUtils {

	/**
	 * Long数值转换
	 * @param val
	 * @return
	 * @author Gavin
	 * @UCUSER
	 */
	public static Long getLongVal(String val){
		return getLongVal(val,null);
	}
	
	/**
	 * Long数值转换
	 * @param val
	 * @param defval
	 * @return
	 * @author Gavin
	 * @UCUSER
	 */
	public static Long getLongVal(String val,Long defval){
		try{
			return Long.valueOf(val);
		}catch(Exception e){
		}
		return defval;
	}
	
	/**
	 * Integer数值转换
	 * @param val
	 * @return
	 * @author Gavin
	 * @UCUSER
	 */
	public static Integer getIntegerVal(String val){
		return getIntegerVal(val,null);
	}
	
	/**
	 * Integer数值转换
	 * @param val
	 * @param defval
	 * @return
	 * @author Gavin
	 * @UCUSER
	 */
	public static Integer getIntegerVal(String val,Integer defval){
		try{
			return Integer.valueOf(val);
		}catch(Exception e){
		}
		return defval;
	}
	
	
	public static String toStr(Object num){
		try{
			return num.toString();
		}catch(Exception e){
		}
		return null;
	}
	
}
