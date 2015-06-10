package com.zebone.tool;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * 设置属性文件
 *
 * @author 杨英
 * @version 2014-8-14 下午1:57
 */
public class SetSystemProperty {
    //属性文件的路径
    static String proFilePath;

    /**
     * 采用静态方法
     */
    private static Properties props = new Properties();

    static {
        try {
            //打成jar包后获取文件路径
            proFilePath = System.getProperty("user.dir")+"/config/CliSysconfig.properties";
//            proFilePath = SetSystemProperty.class.getResource("CliSysconfig.properties").toURI().getPath();
            proFilePath = proFilePath.replaceAll("%20", " ");
            props.load(new FileInputStream(proFilePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            System.exit(-1);
        }
    }

    /**
     * 更新properties文件的键值对
     * 如果该主键已经存在，更新该主键的值；
     * 如果该主键不存在，则插件一对键值。
     *
     * @param keyName  键名
     * @param keyValue 键值
     */
    public void updateProperties(String keyName, String keyValue) {
        try {
            props.load(new FileInputStream(proFilePath));
            // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
            OutputStream fos = new FileOutputStream(proFilePath);
            props.setProperty(keyName, keyValue);
            // 以适合使用 load 方法加载到 Properties 表中的格式，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            props.store(fos, "Update '" + keyName + "' value");
        } catch (IOException e) {
            System.err.println("属性文件更新错误");
        }
    }

}
