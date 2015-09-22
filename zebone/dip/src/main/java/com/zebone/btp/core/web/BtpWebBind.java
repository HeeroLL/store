package com.zebone.btp.core.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * 自定义参数绑定
 * @author 宋俊杰
 *
 */
public class BtpWebBind implements WebBindingInitializer {

	private String dateStyle = "yyyy-MM-dd";;
	
	public void setDateStyle(String dateStyle){
		this.dateStyle = dateStyle;
	}
	
	@Override
	public void initBinder(WebDataBinder binder, WebRequest arg1) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateStyle);
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));

	}

}
