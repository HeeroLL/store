package com.zebone.empi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 身份证工具类
 * @author YinCM
 * @since
 */
public class IDCard {
	
	/**
	 * 通过身份证号码获取生日
	 * @param idCard
	 * @return
	 */
	public static Date getBirthday(String idCard){
		String Ai=null;
		Date date=null;
		if (idCard.length() == 18) {
			Ai = idCard.substring(0, 17);
		} else if (idCard.length() == 15) {
			Ai = idCard.substring(0, 6) + "19" + idCard.substring(6, 15);
		}else{
			return null;
		}
		String strYear = Ai.substring(6, 10);// 年份
		String strMonth = Ai.substring(10, 12);// 月份
		String strDay = Ai.substring(12, 14);// 月份
		
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = s.parse(strYear+"-"+strMonth+"-"+strDay);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
		return date;
	}
	
	/**
	 * 通过身份证号码获取年龄
	 * @param idCard
	 * @return -1代表不合法
	 */
	public static short getAge(String idCard){
		
		Date dateOfBirth = getBirthday(idCard);
		Calendar dob = Calendar.getInstance();  
		dob.setTime(dateOfBirth);  
		Calendar today = Calendar.getInstance();  
		int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);  
		if(age==-1){
			return -1;
		}
		if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
		  age--;  
		} else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
		    && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
		  age--;  
		}
		return (short)age;
	}
	
	/**
	 * 通过身份证号码获取性别，1为男，2为女
	 * @param idCard
	 * @return 1男，2女，-1未知
	 */
	public static int getSex(String idCard){
		String Ai=null;
		if (idCard.length() == 18) {
			Ai = idCard.substring(0, 17);
		} else if (idCard.length() == 15) {
			Ai = idCard.substring(0, 6) + "19" + idCard.substring(6, 15);
		}else{
			return -1;
		}
		char sexCode = idCard.charAt(16);
		if(sexCode%2==0){
			return 2;
		}else{
			return 1;
		}
	}
}
