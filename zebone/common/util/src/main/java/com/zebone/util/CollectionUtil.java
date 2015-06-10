package com.zebone.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * 集合工具类
 * 
 * @author lilin
 * @version [版本号, 2015年4月29日]
 */
public final class CollectionUtil {
    /**
     * 判断集合中每个对象是否为空
     *
     * @param cols 集合
     * @return 是否为空
     */
    public static boolean isEmptyCollection(Collection<?> cols) {
        if (cols == null || cols.isEmpty()) {
            return true;
        }
        Iterator<?> ite = cols.iterator();
        while (ite.hasNext()) {
            if (ite.next() == null) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 判断map集合中每个对象是否为空
     *
     * @param map map集合
     * @return 是否为空
     */
    public static boolean isEmptyMap(Map<String, ?> map) {
        if (map == null || map.isEmpty()) {
            return true;
        }
        for (Entry<String, ?> entry : map.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                return true;
            }
        }
        return false;
    }
}
