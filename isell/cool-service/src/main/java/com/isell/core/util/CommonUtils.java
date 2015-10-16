package com.isell.core.util;

import java.util.UUID;

public class CommonUtils {

	/**
	 * 生成随机四位数字
	 * 
	 * @return int
	 */
	public static int randomFour() {
		int index = (int) (Math.random() * 9000 + 1000);
		return index;
	}

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
