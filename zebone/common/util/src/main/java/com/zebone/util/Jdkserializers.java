package com.zebone.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * JDK序列化/反序列化工具类
 * 
 * @author lilin
 * @version [版本号, 2015年2月13日]
 */
public final class Jdkserializers {
    /**
     * 序列化
     * 
     * @param object 对象
     * @return byte数组
     */
    public static byte[] toSerialization(Object object) {
        if (object == null) {
            return null;
        }
        ByteArrayOutputStream output = null;
        ObjectOutputStream objectOut = null;
        try {
            output = new ByteArrayOutputStream();
            objectOut = new ObjectOutputStream(output);
            objectOut.writeObject(object);
            
            return output.toByteArray();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (output != null) {
                    output.close();
                }
                if (objectOut != null) {
                    objectOut.close();
                }
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    /**
     * 反序列化
     * 
     * @param bytes byte数组
     * @return 对象
     */
    public static Object fromSerialization(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        ObjectInputStream objectIn = null;
        try {
            objectIn = new ObjectInputStream(new ByteArrayInputStream(bytes));
            return objectIn.readObject();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if (objectIn != null) {
                try {
                    objectIn.close();
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
