package com.zebone.util;

/**
 * 
 * 字符工具类
 * 
 * @author lilin
 * @version [版本号, 2015年5月16日]
 */
public final class CharacterUtil {
    
    /**
     * 判断一个字节是不是中文
     * 
     * @param c 字节
     * @return 是否为中文
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
            || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
            || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
            || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
            || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
            || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }
    
    /**
     * 判断一个字节是不是双字节
     * 
     * @param c 字节
     * @return 是否为双字节
     */
    public static boolean isDoublet(char c) {
        return c > 0x80;
    }
}
