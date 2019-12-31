package com.emx.platform.utils;

public class StringUtil {
	public static String clearSpace(String str) {
        return str.replaceAll(" ", "");
    }

    public static String[] clearSpace(String... str) {
        String[] temps = new String[str.length];

        for(int i = 0; i < str.length; ++i) {
            temps[i] = str[i].replaceAll(" ", "");
        }

        return temps;
    }
    
    /**
	 * 判断字符串是否为空 
	 * @param str
	 * @return
	 */
	public static Boolean isEmpty(Object str) {
		if(str == null || String.valueOf(str).trim().equals("") || String.valueOf(str).trim().equalsIgnoreCase("null")) {  
	        return true;  
	    }else{  
	        return false;  
	    }  
	}
	
	public static boolean isNotEmpty(Object str) {
		return !isEmpty(str);
	}
}
