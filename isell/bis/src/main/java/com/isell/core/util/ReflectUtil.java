package com.isell.core.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

import org.apache.log4j.Logger;

/**
 *
 * 反射工具类
 *
 * @author lilin
 * @version [版本号, 2012-11-27]
 */
public final class ReflectUtil
{
    /**
     * log4j log
     */
    private static Logger log = Logger.getLogger(ReflectUtil.class);

    /**
     * 私有化构造方法
     */
    private ReflectUtil()
    {

    }

    /**
     * 根据类名反射生成class
     *
     * @param className 类名
     * @return class
     */
    public static Class<?> getClass(String className)
    {
        try
        {
            return Class.forName(className);
        }
        catch (ClassNotFoundException e)
        {
            log.error("Reflect class failed! Class name is " + className, e);
            throw new RuntimeException("classnotfound", e);
        }
    }

    /**
     * 获得类的实例
     *
     * @param className 类名
     * @return 实例
     */
    public static Object newInstance(String className)
    {
        return newInstance(getClass(className));
    }

    /**
     * 获得类的实例
     *
     * @param c class
     * @return 实例
     */
    public static Object newInstance(Class<?> c)
    {
        try
        {
            return c.newInstance();
        }
        catch (InstantiationException e)
        {
            log.error("New instance failed! Class is " + c.getClass(), e);
            throw new RuntimeException("newInstance", e);
        }
        catch (IllegalAccessException e)
        {
            log.error("New instance failed! Class is " + c.getClass(), e);
            throw new RuntimeException("newInstance", e);
        }
    }

    /**
     * 获取class中指定名称的method对象（只是public的）
     *
     * @param c class
     * @param methodName 方法名
     * @param params 参数
     * @return method
     */
    public static Method getMethod(Class<?> c, String methodName, Class<?>... params)
    {
        try
        {
            return c.getMethod(methodName, params);
        }
        catch (SecurityException e)
        {
            log.error("Get method failed! Class is " + c.getClass() + ", method name is " + methodName
                + ", parameters classes are " + params, e);
            throw new RuntimeException("getMethod", e);
        }
        catch (NoSuchMethodException e)
        {
            log.error("Get method failed! Class is " + c.getClass() + ", method name is " + methodName
                + ", parameters classes are " + params, e);
            throw new RuntimeException("getMethod", e);
        }
    }

    /**
     * 反射方法获取返回值
     *
     * @param methodName 方法名
     * @param obj 对象
     * @param params 方法参数
     * @return 调用方法获得的返回值
     */
    public static Object invokeMethod(String methodName, Object obj, Object... params)
    {
        // 获取参数列表
        Class<?>[] paramClasses = null;
        if (params != null)
        {
            paramClasses = new Class<?>[params.length];
            for (int i = 0; i < paramClasses.length; i++)
            {
                paramClasses[i] = params[i].getClass();
            }
        }
        Method method = getMethod(obj.getClass(), methodName, paramClasses);

        return invokeMethod(method, obj, params);
    }

    /**
     * 反射方法获取返回值
     *
     * @param method 方法
     * @param obj 对象
     * @param params 方法参数
     * @return 调用方法获得的返回值
     */
    public static Object invokeMethod(Method method, Object obj, Object... params)
    {
        try
        {
            return method.invoke(obj, params);
        }
        catch (IllegalArgumentException e)
        {
            log.error("Invoke method failed! Method is " + method + ", object is " + obj + ", parameters are " + params,
                e);
            throw new RuntimeException("invokeMethod", e);
        }
        catch (IllegalAccessException e)
        {
            log.error("Invoke method failed! Method is " + method + ", object is " + obj + ", parameters are " + params,
                e);
            throw new RuntimeException("invokeMethod", e);
        }
        catch (InvocationTargetException e)
        {
            log.error("Invoke method failed! Method is " + method + ", object is " + obj + ", parameters are " + params,
                e);
            throw new RuntimeException("invokeMethod", e);
        }
    }

    /**
     * 为类中某个成员变量赋值，该成员必须提供它的set方法
     *
     * @param obj 对象实例
     * @param fieldName 字段名称
     * @param fieldValue 字段值
     */
    public static void setFieldValue(Object obj, String fieldName, Object fieldValue)
    {
        String methodName = "set" + firstLetterToUppercase(fieldName);
        Method method = getMethod(obj.getClass(), methodName, fieldValue.getClass());
        invokeMethod(method, obj, fieldValue);
    }

    /**
     * 获取类中某个成员变量的值，该成员必须提供它的get方法
     *
     * @param obj 对象实例
     * @param fieldName 字段名称
     * @return 字段值
     */
    public static Object getFieldValue(Object obj, String fieldName)
    {
        String methodName = "get" + firstLetterToUppercase(fieldName);
        Method method = getMethod(obj.getClass(), methodName);
        return invokeMethod(method, obj);
    }

    /**
     * 首字母大写
     *
     * @param str 首字母
     * @return String
     */
    private static String firstLetterToUppercase(String str)
    {
        if (null == str)
        {
            return str;
        }
        StringBuilder builder = new StringBuilder(str.length());
        char c = str.charAt(0);
        builder.append(String.valueOf(c).toUpperCase(Locale.getDefault()));
        if (str.length() > 0)
        {
            builder.append(str.substring(1));
        }

        return builder.toString();
    }
}
