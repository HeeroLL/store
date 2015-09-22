package com.zebone.dnode.engine.clean;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.zebone.dnode.engine.clean.exception.CleanException;
import com.zebone.dnode.engine.clean.exception.ErrorUtil;

/**
 * 日期转化工具
 * @author cz
 *
 */
public class DateConvert implements DataConvert {
	
	private static final String parsePatterns = "yyyy-MM-dd HH:mm:ss";
	
	private static final String errorMessage = "无效的日期格式";
	

	@Override
	public Object convert(Object value) throws CleanException{
		// TODO Auto-generated method stub
		if (value instanceof Date) {
			return value;
		} else if (value instanceof String) {
			String dateStr = value.toString();
			if(checkValidDate(dateStr)){
				try {
					DateUtils.parseDate(dateStr, parsePatterns);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					throw new CleanException(ErrorUtil.SYSTEM_ERROR, errorMessage);
				}
			}else{
				throw new CleanException(ErrorUtil.BUSINESS_CHECKING_ERROR, errorMessage);
			}
		}
		return null;
	}
    
	/**
	 * 判断是否是合法的日期
	 * @param pDateObj
	 * @return
	 */
	private boolean checkValidDate(String pDateObj) {
		boolean ret = true;
		if (pDateObj == null || pDateObj.length() != 8) {
			ret = false;
		}
		try {
			int year = new Integer(pDateObj.substring(0, 4)).intValue();
			int month = new Integer(pDateObj.substring(4, 6)).intValue();
			int day = new Integer(pDateObj.substring(6)).intValue();
			Calendar cal = Calendar.getInstance();
			cal.setLenient(false); // 允许严格检查日期格式
			cal.set(year, month - 1, day);
			cal.getTime();// 该方法调用就会抛出异常
		} catch (Exception e) {
			ret = false;
		}
		return ret;
	}

}
