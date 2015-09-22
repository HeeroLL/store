package com.zebone.util;

import java.io.UnsupportedEncodingException;

/**
 * 编码工具类
 * @author cz
 *
 */
public class EncodingTool {
	
	public static String encodeStr(String str) {  
        try {  
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            return null;  
        }  
    }

    /**
     * 对查询字段中的特殊字符进行转义
     * @param str
     * @return
     */
    public static String escapeStr(String str){
        if(str!=null && !"".equals(str)){
            for(int i=0; i<str.length(); i++){
                switch (str.charAt(i)){
                    case '/':
                        str = replaceIndex(i,str,"//");
                        i++;
                        break;
                    case '_':
                        str = replaceIndex(i,str,"/_");
                        i++;
                        break;
                    case '%':
                        str = replaceIndex(i,str,"/%");
                        i++;
                        break;
                }
            }
        }
        return str;
    }

    /**
     * 将字符串的指定位置替换成新的字符
     * @param str     原字符串
     * @return String 替换后的字符串
     */
    public static String replaceIndex(int index,String res,String str){
        return res.substring(0, index)+str+res.substring(index+1);
    }
}
