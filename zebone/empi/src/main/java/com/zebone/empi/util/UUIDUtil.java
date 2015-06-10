package com.zebone.empi.util;

import java.util.UUID;

/**
 * 产生UUID
 * @author YinCm
 * @version 2013-8-1 上午10:10:20
 */
public class UUIDUtil {

	/**
	 * 返回UUID(32位)
	 * 
	 * @return String
	 */
	public synchronized static String getUuid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}

}
