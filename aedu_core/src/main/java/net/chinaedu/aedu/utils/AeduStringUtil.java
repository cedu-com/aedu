package net.chinaedu.aedu.utils;

import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * 字符串工具类
 * 
 * @author 高焕杰
 */
public class AeduStringUtil {
    
	/**
	 * 判断字符串是否为空
	 */
	public static boolean isEmpty(String str) {
		if(str == null || str.length() <= 0 || str.trim() == null || str.trim().length() <= 0) {
		    return true;
		}
		return false;
	}
	
	public static boolean isEmpty(CharSequence str) {
		if(str == null || str.length() <= 0) {
		    return true;
		}
		return false;
	}
	
	/**
	 * 判断字符串是否不为空
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	// 获取当前日期 YYYY-MM-DD
	public static String getDate() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return sDateFormat.format(new java.util.Date());  		
	}
	
	// 获取当前日期和时间（hh:mm:ss为12小时制，HH:mm:ss为24小时制
	public static String getDatetime() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sDateFormat.format(new java.util.Date());  
	}
	
	// 多个单词组成的bean属性转成用下划线拼接的字符串
	public static String getConnectionByUnderline(String str){
	    if (str == null || "".equals(str) )return "";
	    String[] strs = str.split("(?<!^)(?=[A-Z])");
        
        StringBuffer stringBuffer = new StringBuffer();
        boolean isLast = false;
        for(String s : strs){
            if(isLast){
                stringBuffer.append("_");
            }
            stringBuffer.append(s.toLowerCase());
            isLast = true;
        }
        return stringBuffer.toString();
    }
	
	public static String getUUID(){
	    return UUID.randomUUID().toString();
	}

	public static String getHMS(String duration) {
		if (AeduStringUtil.isEmpty(duration) || "0".equals(duration)) {
			return "00:00";
		}
		int durationInt = 0;
		try {
			durationInt =  Integer.valueOf(duration);
		} catch (Exception e) {
		}
		int h = durationInt/3600;
		int m=(durationInt-h*3600)/60;
		int s=(durationInt-h*3600) % 60;
		StringBuffer buffer = new StringBuffer();

		if (h > 0) {
			buildString(buffer, h);
			buffer.append(":");
		}
		buildString(buffer, m);
		buffer.append(":");
		buildString(buffer, s);
		return buffer.toString();
	}

	private static void buildString(StringBuffer sb, int num) {
		if(num < 10) {
			sb.append('0');
		}
		sb.append(num);
	}
}
