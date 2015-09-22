package com.zebone.btp.core.util;

import org.apache.log4j.Logger;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @Company ZeBone
 * @Project medical
 * @version <1.0>
 * @Author RainZhang
 * @Date 2011/08/10
 * @description 将中文转化成相应的拼音
 * 
 * 
 */
public class ChineseToPinYin {
    private static final Logger logger = Logger.getLogger(ChineseToPinYin.class);
    
    /**
     * @param zhongwen
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination 根据中文获得拼音
     */
    public static final String split = ",";
    
    public static String chineseToPinyin(String zhongwen) {
        try {
            return getFullAndForShortSpell(zhongwen)[0].toLowerCase();
        }
        catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * @param zhongwen
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination 根据中文获得拼音与缩写以分隔符格开的内容
     */
    public static String chineseToPinyinAndShort(String zhongwen) {
        try {
            return (getFullAndForShortSpell(zhongwen)[0] + split + getFullAndForShortSpell(zhongwen)[1]).toLowerCase();
        }
        catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * @param zhongwen
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination 得到拼音及缩写
     */
    private static String[] getFullAndForShortSpell(String zhongwen)
        throws BadHanyuPinyinOutputFormatCombination {
        String fullAndForShortSpell[] = {"", ""};
        fullAndForShortSpell[0] = "";
        fullAndForShortSpell[1] = "";
        
        char[] chars = zhongwen.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            String[] pinYin = PinyinHelper.toHanyuPinyinStringArray(chars[i], getDefaultOutputFormat());
            // 当转换不是中文字符时,返回null
            if (pinYin != null) {
                
                fullAndForShortSpell[0] += capitalize(pinYin[0])[0];
                fullAndForShortSpell[1] += capitalize(pinYin[0])[1];
            }
            else {
                fullAndForShortSpell[0] += chars[i];
            }
        }
        // System.out.println(fullAndForShortSpell [1]);
        return fullAndForShortSpell;
    }
    
    /**
     * Default Format 默认输出格式
     * 
     * @return
     */
    public static HanyuPinyinOutputFormat getDefaultOutputFormat() {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 没有音调数字
        format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);// u显示
        return format;
    }
    
    /**
     * Capitalize 首字母大写
     * 
     * @param s
     * @return
     */
    public static String[] capitalize(String s) {
        String fullAndForShortSpell[] = new String[2];
        fullAndForShortSpell[0] = "";
        fullAndForShortSpell[1] = "";
        
        char ch[];
        ch = s.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char)(ch[0] - 32);
        }
        fullAndForShortSpell[1] += ch[0];
        fullAndForShortSpell[0] = new String(ch);
        if (fullAndForShortSpell[0].indexOf("u:") != -1) {
            fullAndForShortSpell[0] = fullAndForShortSpell[0].replaceAll("u:", "v");
        }
        return fullAndForShortSpell;
    }
    
    /**
     * Capitalize 首字母缩写
     * 
     * @param s
     * @return
     */
    public static String allAndFirst(String s) {
        char ch[];
        ch = s.toCharArray();
        logger.info(ch[0]);
        String newString = new String(ch);
        return newString;
    }
    
    /**
     * 汉字转拼音缩写
     * 
     * @param str //要转换的汉字字符串
     * @return String //拼音缩写
     * @author dragon
     */
    public static String getPYString(String str) {
        String tempStr = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((int)c >= 33 && (int)c <= 126) {// 字母和符号原样保留
                tempStr += String.valueOf(c);
            }
            else {// 累加拼音声母
                tempStr += getPYChar(String.valueOf(c));
            }
        }
        return tempStr;
    }
    
    /**
     * 取单个字符的拼音声母
     * 
     * @param c //要转换的单个汉字
     * @return String 拼音声母
     * @author dragon
     */
    public static String getPYChar(String c) {
        byte[] array = new byte[2];
        array = String.valueOf(c).getBytes();
        int i = (short)(array[0] - '\0' + 256) * 256 + ((short)(array[1] - '\0' + 256));
        if (i < 0xB0A1)
            return "*";
        if (i < 0xB0C5)
            return "A";
        if (i < 0xB2C1)
            return "B";
        if (i < 0xB4EE)
            return "C";
        if (i < 0xB6EA)
            return "D";
        if (i < 0xB7A2)
            return "E";
        if (i < 0xB8C1)
            return "F";
        if (i < 0xB9FE)
            return "G";
        if (i < 0xBBF7)
            return "H";
        if (i < 0xBFA6)
            return "J";
        if (i < 0xC0AC)
            return "K";
        if (i < 0xC2E8)
            return "L";
        if (i < 0xC4C3)
            return "M";
        if (i < 0xC5B6)
            return "N";
        if (i < 0xC5BE)
            return "O";
        if (i < 0xC6DA)
            return "P";
        if (i < 0xC8BB)
            return "Q";
        if (i < 0xC8F6)
            return "R";
        if (i < 0xCBFA)
            return "S";
        if (i < 0xCDDA)
            return "T";
        if (i < 0xCEF4)
            return "W";
        if (i < 0xD1B9)
            return "X";
        if (i < 0xD4D1)
            return "Y";
        if (i < 0xD7FA)
            return "Z";
        return "*";
    }
    
    public static void main(String args[])
        throws BadHanyuPinyinOutputFormatCombination {
        String py = "拼音";
        System.out.println(chineseToPinyin(py));
        System.out.println(chineseToPinyinAndShort(py));
    }
}