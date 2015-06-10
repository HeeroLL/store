package com.zebone.util;


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
	
}