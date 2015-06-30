package com.sell.core.web.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.convert.converter.Converter;

public class StringToDateConverter implements Converter<String, Date> {
	private static final Log log = LogFactory.getLog(StringToDateConverter.class);
	private static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	private String dateFormatString = null;

	public void setDateFormat(String dateFormat) {
		this.dateFormatString = dateFormat;
	}

	@Override
	public Date convert(String source) {
		if (StringUtils.isEmpty(source)) {
			return null;
		}

		SimpleDateFormat dateFormat = null;
		String dateStyle = null;
		if (StringUtils.isNotEmpty(dateFormatString)) {
			dateStyle = dateFormatString;
		} else {
			dateStyle = DEFAULT_DATE_FORMAT;
		}
		dateFormat = new SimpleDateFormat(dateStyle);
		try {
			return dateFormat.parse(source);
		} catch (ParseException e) {
		    log.error(e);
			throw new IllegalArgumentException(String.format("类型转换失败，需要格式"
					+ dateStyle + "，但格式是[%s]", source));
		}
	}
}
