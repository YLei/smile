package com.emx.platform.utils;

import java.util.Random;

import org.joda.time.DateTime;

public class UUIDUtil {

	public UUIDUtil() {
	}

	/**
	 * 获得一个UUID
	 * 
	 * @return String UUID
	 */
	public static String getPrimaryKey() {
		return java.util.UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 获得指定数目的UUID
	 * 
	 * @param number
	 *            int 需要获得的UUID数量
	 * @return String[] UUID数组
	 */
	public static String[] getUUID(int number) {
		if (number < 1) {
			return null;
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; i++) {
			ss[i] = getPrimaryKey();
		}
		return ss;
	}
	
	public static String getTimeLongRandom() {
		String time = new DateTime().toString("yyyyMMddHHmmss-SSS");
		Random random = new Random();
		return time + "-" + random.nextInt(999);
	}

	public static void main(String args[]) {
		for (String s : UUIDUtil.getUUID(5)) {
			System.out.println(s);
		}
	}
}
