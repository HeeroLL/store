package com.isell.core.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 日期工具类
 * @author 宋俊杰
 * @version 1.0
 */
public class DateUtil {
	private static Logger log = Logger.getLogger(DateUtil.class);
	
	public final static String yyyy_MM_dd = "yyyy-MM-dd";
    
    public final static String yyyyMMdd = "yyyyMMdd";
    
    public final static String yyyyMM = "yyyyMM";
    
    public final static String yyyy_MM = "yyyy-MM";
    
    public final static String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    
    public final static String yyyyMMddHHmm = "yyyyMMddHHmm";
    
    public final static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    
    public final static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    
    public final static String yyyy_MM_dd_HH = "yyyy-MM-dd HH";
    
    public final static String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	
	
	/**
	 * 根据一个格式返回当前系统时间
	 * @param format
	 * @return
	 */
	public static String getCurrentDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(new Date());
	}
	
	
	
	/**
	 * 将日期字符串转换成日期
	 * @param date 日期字符串
	 * @param format 日期字符串格式。如果没有指定，那么默认为yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date parseDate(String date, String format){
		if(StringUtils.isBlank(format)){
			format = "yyyy-MM-dd HH:mm:ss";
		}
		if(StringUtils.isNotEmpty(date)){
			try {
				return new SimpleDateFormat(format).parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	/**
	 * 将一个字符串类型的时间转换为另一种格式的字符串类型时间
	 * Example : String formattedDate = DateUtil.format("20070906112351", "yyyyMMddHHmmss", "yyyy年MM月dd日 HH时mm分ss秒")
	 * @param date 字符串时间.例如20070906112351
	 * @param sourceFormat 源时间的格式,上面为yyyyMMddHHmmss
	 * @param transFormat 要转换的目标格式
	 * @return
	 */
	public static String format(String date, String sourceFormat, String transFormat) {
		if (StringUtils.isBlank(date)) {
			return "";
		}
		
		DateFormat _formater1 = new SimpleDateFormat(sourceFormat);
		DateFormat _formater2 = new SimpleDateFormat(transFormat);
		
		try {
			return _formater2.format(_formater1.parse(date));
		}
		catch (ParseException pe) {
			return date;
		}
	}
	
	
	/**
	 * 判断一个时间是否为上午
	 * @return boolean
	 */
	public static boolean isAM(Date date) {
		log.info("判断是否为上午的时候发生异常");

		boolean isTrue = true;
		
		DateFormat df = new SimpleDateFormat("HH");
		
		try {
			int integerHour = Integer.parseInt(df.format(date));
			
			if (integerHour >= 0 && integerHour <= 12) {
				isTrue = true;
			}
			else {
				isTrue = false;
			}
		}
		catch (NumberFormatException nfe) {
			log.error("判断是否为上午的时候发生异常", nfe);
		}
		
		return isTrue;
	}
	
	/**
	 * 返回当前日期加n天后的日期
	 * @author RainZhang 2011-11-17
	 * @return
	 */
	public static String getAdvanceDay(int advanceDay){
		SimpleDateFormat format = new   SimpleDateFormat("yyyyMMdd");  
		GregorianCalendar d = new GregorianCalendar();
		d.add(Calendar.DAY_OF_MONTH, advanceDay);
		return format.format(d.getTime());
	}
	
	/**
     * 将Date时间转成字符串
     * 
     * @param format
     * @param date
     * @return
     */
    public static String dateToStr(String format, Date date) {
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        
        return simpleDateFormat.format(date);
    }
    
    /**
     * 将字符串时间改成Date类型
     * 
     * @param format
     * @param dateStr
     * @return
     */
    public static Date strToDate(String format, String dateStr) {
        
        Date date = null;
        
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return date;
    }
    
    /**
     * 给出日期添加一段时间后的日期
     * 
     * @param dateStr
     * @param plus
     * @return
     */
    public static String getPlusDays(String format, Date date, long plus) {
        if (date == null) {
            date = new Date();
        }
        long time = date.getTime() + plus * 24 * 60 * 60 * 1000;
        
        return DateUtil.dateToStr(format, new Date(time));
    }
	
	/**
	 * 生成随机四位数字
	 * 
	 * @return int
	 */
	public static int randomFour() {
		int index = (int) (Math.random() * 9000 + 1000);
		return index;
	}
	
	/**
     * 变量：日期格式化类型 - 格式:yyyy/MM/dd
     */
	public static final int DEFAULT = 0;
	public static final int YM = 1;

    /**
     * 变量：日期格式化类型 - 格式:yyyy-MM-dd
     * 
     */
    public static final int YMR_SLASH = 11;

    /**
     * 变量：日期格式化类型 - 格式:yyyyMMdd
     * 
     */
    public static final int NO_SLASH = 2;

    /**
     * 变量：日期格式化类型 - 格式:yyyyMM
     * 
     */
    public static final int YM_NO_SLASH = 3;

    /**
     * 变量：日期格式化类型 - 格式:yyyy/MM/dd HH:mm:ss
     * 
     */
    public static final int DATE_TIME = 4;

    /**
     * 变量：日期格式化类型 - 格式:yyyyMMddHHmmss
     * 
     */
    public static final int DATE_TIME_NO_SLASH = 5;

    /**
     * 变量：日期格式化类型 - 格式:yyyy/MM/dd HH:mm
     * 
     */
    public static final int DATE_HM = 6;

    /**
     * 变量：日期格式化类型 - 格式:HH:mm:ss
     * 
     */
    public static final int TIME = 7;

    /**
     * 变量：日期格式化类型 - 格式:HH:mm
     * 
     */
    public static final int HM = 8;
    
    /**
     * 变量：日期格式化类型 - 格式:HHmmss
     * 
     */
    public static final int LONG_TIME = 9;
    /**
     * 变量：日期格式化类型 - 格式:HHmm
     * 
     */
    
    public static final int SHORT_TIME = 10;

    /**
     * 变量：日期格式化类型 - 格式:yyyy-MM-dd HH:mm:ss
     */
    public static final int DATE_TIME_LINE = 12;
    
    public static String dateToStr(Date date, int type) {
        switch (type) {
        case DEFAULT:
            return dateToStr(date);
        case YM:
            return dateToStr(date, "yyyy/MM");
        case NO_SLASH:
            return dateToStr(date, "yyyyMMdd");
        case YMR_SLASH:
        	return dateToStr(date, "yyyy-MM-dd");
        case YM_NO_SLASH:
            return dateToStr(date, "yyyyMM");
        case DATE_TIME:
            return dateToStr(date, "yyyy/MM/dd HH:mm:ss");
        case DATE_TIME_NO_SLASH:
            return dateToStr(date, "yyyyMMddHHmmss");
        case DATE_HM:
            return dateToStr(date, "yyyy/MM/dd HH:mm");
        case TIME:
            return dateToStr(date, "HH:mm:ss");
        case HM:
            return dateToStr(date, "HH:mm");
        case LONG_TIME:
            return dateToStr(date, "HHmmss");
        case SHORT_TIME:
            return dateToStr(date, "HHmm");
        case DATE_TIME_LINE:
            return dateToStr(date, "yyyy-MM-dd HH:mm:ss");
        default:
            throw new IllegalArgumentException("Type undefined : " + type);
        }
    }
    public static String dateToStr(Date date,String pattern) {
       if (date == null || date.equals(""))
    	 return null;
       SimpleDateFormat formatter = new SimpleDateFormat(pattern);
       return formatter.format(date);
	 } 
	
	 public static String dateToStr(Date date) {
	     return dateToStr(date, "yyyy/MM/dd");
	 }
}