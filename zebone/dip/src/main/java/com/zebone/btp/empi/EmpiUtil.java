package com.zebone.btp.empi;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zebone.btp.Constant;

/**
 * Empi管理系统 工具类
 * 
 * @author ouyangxin
 * 
 * CreateDate: 2013-1-22
 */
public class EmpiUtil {

	/**
	 * 字符串空校验
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 字符串非空校验
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 
	 * 返回当前时间 格式为yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static String getTimeNow() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(new GregorianCalendar().getTime());
	}

	/**
	 * 根据身份证获取生日
	 * 
	 * @param icn
	 * @return
	 */
	public static String getBirthday(String icn) {
		return (isNotEmpty(icn) && icn.length() == 18) ? icn.trim()
				.substring(6, 14) : "" ;
	}

	/**
	 * 根据身份证获取性别
	 * 
	 * @param icn
	 * @return
	 */
	public static String getSex(String icn) {
		
		String sexStr = "";
		
		if (isNotEmpty(icn) && icn.length() == 18) {
			
			int sex = Integer.valueOf(icn.trim().substring(17));
			
			if (sex % 2 == 0) {
				sexStr = Constant.SEX_MAN;
			} else {
				sexStr = Constant.SEX_FEMALE;
			}
		}
		return sexStr;
	}

	/**
	 * 身份证的有效验证
	 * @param icn
	 * @return
	 */
	public static boolean icnValidate(String icn)
	{
		boolean validateFlag = false;

		//非空校验
		if(isNotEmpty(icn))
		{
			
			//校验长度 18
			if(icn.length() != 18)
			{
				return false;
			}
			
			//校验前17位是否是数字
			Pattern pattern = Pattern.compile("[0-9]*");     
	        Matcher isNum = pattern.matcher(icn.substring(0, 17));     
	        if (!isNum.matches()) {      
	            return false;     
	        }
	        
	        //校验最后一位
	        isNum = pattern.matcher(icn.substring(17));  
	        if (!isNum.matches()) {
	        	if(!"X".equalsIgnoreCase(icn.substring(17)))
	        	{
	        		return false;
	        	}
	        }
	        
	        //校验通过
	        validateFlag = true;
		}
		
		return validateFlag;
	}
	
	public static void main(String[] args) {

	}

}
