package com.zebone.dip.check.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.dip.check.service.CheckConfService;
import com.zebone.dip.check.vo.CheckConf;

/**
 * 类描述：校验开关控制层
 * @author: caixl
 * @date： 日期：Sep 3, 2013
 * @version 1.0
 */
@Controller
public class CheckConfController {
	@Resource
	private CheckConfService checkConfService;
	
	@RequestMapping("check/checkoutIndex")
	public String checkoutIndex(ModelMap map){
		List<CheckConf> list = checkConfService.getCheckConfList();
		map.put("list", list);
		return "dip/check/checkout_index";
	}
	
	@RequestMapping("check/checkoutSave")
	@ResponseBody
	public Map<String, Object> checkoutSave(@RequestParam("data")String data){
		Map<String, Object> map = new HashMap<String, Object>();
		int result = checkConfService.checkoutSave(data);
		map.put("success", result>0?true:false);
		return map;
	}
	
	
}
