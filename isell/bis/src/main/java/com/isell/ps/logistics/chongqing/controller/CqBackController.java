package com.isell.ps.logistics.chongqing.controller;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.Coder;
import com.isell.core.util.JaxbUtil;
import com.isell.ps.logistics.chongqing.bean.Goods;
import com.isell.ps.logistics.chongqing.bean.OrderBack;
import com.isell.ps.logistics.chongqing.bean.PayInfo;

@Controller
@RequestMapping("logistics/cqBackController")
public class CqBackController {
	
	@RequestMapping("backOrder")
	@ResponseBody
	public Boolean backOrder(String data){
		if(StringUtils.isBlank(data)){
			throw new RuntimeException("data is null");
		}
		try {
			data = new String(Coder.decryptBASE64(data), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(data+"backOrder-xml");
		JaxbUtil.converyToJavaBean(data, OrderBack.class);
		return true;
	}
	
	
	@RequestMapping("goodsRecord")
	@ResponseBody
	public Boolean goodsRecord(String data){
		if(StringUtils.isBlank(data)){
			throw new RuntimeException("data is null");
		}
		try {
			data = new String(Coder.decryptBASE64(data), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(data+"goodsRecord-xml");
		JaxbUtil.converyToJavaBean(data, Goods.class);
		return true;
	}
	
	@RequestMapping("payInfo")
	@ResponseBody
	public Boolean payInfo(String data){
		if(StringUtils.isBlank(data)){
			throw new RuntimeException("data is null");
		}
		try {
			data = new String(Coder.decryptBASE64(data), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(data+"payInfo-xml");
		JaxbUtil.converyToJavaBean(data, PayInfo.class);
		return true;	
	}
    
  /*  @RequestMapping("backOrder")
    @ResponseBody
    public String backOrder(String data) {
        if (StringUtils.isBlank(data)) {
            throw new RuntimeException("data is null");
        }
        try {
            data = new String(Coder.decryptBASE64(data), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(data + "backOrder-xml");
        // OrderBack ob = JaxbUtil.converyToJavaBean(data, OrderBack.class);
        return data;
    }
    
    @RequestMapping("goodsRecord")
    @ResponseBody
    public String goodsRecord(String data) {
        if (StringUtils.isBlank(data)) {
            throw new RuntimeException("data is null");
        }
        try {
            data = new String(Coder.decryptBASE64(data), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(data + "goodsRecord-xml");
        // Goods ob = JaxbUtil.converyToJavaBean(data, Goods.class);
        return data;
    }
    
    @RequestMapping("payInfo")
    @ResponseBody
    public String payInfo(String data) {
        if (StringUtils.isBlank(data)) {
            throw new RuntimeException("data is null");
        }
        try {
            data = new String(Coder.decryptBASE64(data), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(data + "payInfo-xml");
        // PayInfo ob = JaxbUtil.converyToJavaBean(data, PayInfo.class);
        return data;
    }*/
}
