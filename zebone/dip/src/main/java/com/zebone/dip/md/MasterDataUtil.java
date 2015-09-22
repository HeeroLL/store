package com.zebone.dip.md;

import com.zebone.dip.md.vo.MDContentObject;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 主数据管理工具类
 * @author Yang Ying
 * @version 2013-7-5 下午3:05
 */
public class MasterDataUtil {

    private static Logger log = Logger.getLogger(MasterDataUtil.class);

    /**
     *
     * 根据表结构信息找到对应的类，并为对应的字段设值，封装成List<MDContentObject>返回
     *
     * @param tempList
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List process(List<HashMap<String, Object>> tempList, String fieldNames) {

        List<MDContentObject> dataList = new ArrayList<MDContentObject>(tempList.size()); // 返回结果
        // map对应的key是大写形式，需转换
        fieldNames = fieldNames.toUpperCase(); // 字段名字符串
        MDContentObject obj = null;

        try {
            for (HashMap<String, Object> map : tempList) {
                String[] v = fieldNames.split(",");
                obj = new MDContentObject();
                for (int k = 0; k < v.length; k++) {
                    v[k] = v[k].trim();
                    Method method = MDContentObject.class.getMethod(("setPro" + (k+1)),
                            String.class);
                    Object object = map.get(v[k]);
                    if(object instanceof Date){
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        object = sdf.format((Date) object);
                    }
                    method.invoke(obj, getStringValue(object));
                }
                Method method = MDContentObject.class.getMethod(("setPro0"), String.class);
                method.invoke(obj, getStringValue(map.get("ID_")));
                dataList.add(obj);
            }
        } catch (Exception e) {
            log.error("class error", e);
        }

        return dataList;
    }

    /**
     * Java反射机制创建类
     *
     * @param fieldNames
     * @param tempList
     * @return
     */
    @SuppressWarnings("unchecked")
    public static MDContentObject processObject(String fieldNames,
                                         List<HashMap<String, Object>> tempList) {
        // map对应的key是大写新式，需转换
        fieldNames = fieldNames.toUpperCase(); // 字段名字符串
        MDContentObject obj = null;
        try {
            for (HashMap<String, Object> map : tempList) {
                String[] v = fieldNames.split(",");
                obj = new MDContentObject();
                for (int k = 0; k < v.length; k++) {
                    v[k] = v[k].trim();
                    Method method = MDContentObject.class.getMethod(("setPro" + (k + 1)),
                            String.class);
                    Object object = map.get(v[k]);
                    if(object instanceof Date){
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        object = sdf.format((Date) object);
                    }
                    method.invoke(obj, getStringValue((object)));

                }
                Method method = MDContentObject.class.getMethod(("setPro0"), String.class);
                method.invoke(obj, getStringValue(map.get("ID_")));
            }
        } catch (Exception e) {
            log.error("class error", e);
        }
        return obj;
    }

    /**
     * 页面展示 为null 则显示 "" <功能详细描述>
     *
     * @param obj
     * @return
     */
    public static String getStringValue(Object obj) {
        return (null == obj ? "" : String.valueOf(obj));
    }
}
