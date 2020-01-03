package com.emx.platform.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtils {

	public static Map<String, Object> beanToMap(Object o) {
	    Map<String, Object> hashMap = new HashMap<>();
	    try {
	        Class c = o.getClass();
	        Method m[] = c.getDeclaredMethods();
	        for(int i = 0 ; i < m.length ; i++) {
	        	if (m[i].getName().indexOf("get") == 0) {
	        		String n = m[i].getName().substring(0, 4).toLowerCase().replace("get", "").concat(m[i].getName().substring(4));
	        		hashMap.put(n, m[i].invoke(o, new Object[0]));
	        	}
	        }
	    } catch (Throwable e) { e.printStackTrace(); }
	    return hashMap;
	 }
}
