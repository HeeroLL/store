package com.zebone.dnode.engine.clean.util;

import java.util.List;
import java.util.Map;


/**
 * 工具类
 * @author cz
 *
 */
public class Util {
    
	/**
	 * 根据map key获取对应的值
	 * @param map
	 * @param ckey
	 * @return
	 */
	public static Object getMapValue(List<Map<String, Object>> map,String ckey) {
		Object rObj = null;
		if (map != null) {
			for (Map<String, Object> m : map) {
				for (Map.Entry<String, Object> entry : m.entrySet()) {
					String key = entry.getKey();
					Object val = entry.getValue();
					if(key.equals(ckey)){
						return val;
					}
				}
			}
		}
		return rObj;
	}
	
	/**
	 * 根据key,设定值
	 * @param map
	 * @param ckey
	 * @param val
	 * @return
	 */
	public static Object setMapValue(List<Map<String, Object>> map,String ckey, Object val) {
		Object rObj = null;
		if (map != null) {
			for (Map<String, Object> m : map) {
				for (Map.Entry<String, Object> entry : m.entrySet()) {
					String key = entry.getKey();
					if(key.equals(ckey)){
						entry.setValue(val);
					}
				}
			}
		}
		return rObj;
	}
}
